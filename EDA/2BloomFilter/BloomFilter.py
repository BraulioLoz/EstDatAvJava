import hashlib as hl
import numpy as np
import math as mth
import pandas as pd
import random as rnd
import matplotlib.pyplot as plt


class BloomFilter:
    def __init__(self, m=10, k=1):
        # en realidad estas tres líneas de código son una "funcion"
        # de cuantos veces haremos la llamada a md5 y cuantos cachos tomamos de el
        self.bits = mth.ceil(mth.log(m, 2))
        # cuantos cachos de caracteres voy a tomar del objeto
        self.caracteres_hexa = mth.ceil(self.bits / 4)
        # cuantos procesos voy a hacer, dividimos entre 32
        # porque ese es el tamaño de hl.md5().hexdigest()
        # la complejidad de insertar: O(procesosMD5) en realidad es O(k)
        self.procesosMD5 = mth.ceil(k * self.caracteres_hexa / 32)
        self.m = m  # tamaño del arreglo
        self.k = k  # numero de funciones hash
        self.bloom = np.zeros(m, dtype=bool)  # arreglo de tamaño m

    def calculaKposiciones(self, objeto: str):
        # Regresa k posiciones para poner en True en el bloom
        posiciones = []
        hash = ""
        # al hacer este for estoy repitiendo mi string
        # digamos que mi resultado de hl.md5(cadena2.encode('utf-8')).hexdigest()
        # es: 1983adf (no es real porque en realidad es de tamaño 32)
        # despues de esto mi hash seria 1983adf1983adf...1983adf
        for procesos in range(self.procesosMD5):
            cadena2 = objeto + str(procesos)
            # la funcion hl.md5() necesita recibir el string encoded
            # luego regresa un objeto al que podemso aplicar hexdigest
            hexa = hl.md5(cadena2.encode("utf-8")).hexdigest()
            hash += hexa
        ## k * caracteres_hexa me dice cuantos caracteres voy a tomar
        ## la manera en la que iteramos es sobre bloques caracteres_hexa
        ## hace sentido porque k * caracteres_hexa/caracteres_hexa = k
        for i in range(0, self.k * self.caracteres_hexa, self.caracteres_hexa):
            # int(hexa, 16) tiene que tener modulo m para que quepa en el arreglo
            valor = int(hash[i : i + self.caracteres_hexa], 16) % self.m
            posiciones.append(valor)
        return posiciones

    def inserta(self, objeto: str):
        posiciones = self.calculaKposiciones(objeto)

        for pos in posiciones:
            self.bloom[pos] = True

    def busca(self, objeto: str):
        posiciones = self.calculaKposiciones(objeto)
        i = 0
        found = True
        while i < len(posiciones) and found:
            found = self.bloom[posiciones[i]]
            i += 1

        return found


def generaStrAzar(n):
    res = ""
    for i in range(n):
        res += chr(rnd.randint(65, 91))
    return res


def bloomF(
    n, porFalsosP
):  # |n| es el numero de elementos a insertar, |porFalsosP| es el porcentaje de falsos positivos
    diccionario = {"m": [], "n": []}
    # PONERLO EN TÉRMINOS DE N Y M, PARA MÁS EJECUTIVO, HACER UN FOR QUE VAYA RECORIENDO TODO LOS DATOS DE CIERTA CANTIDAD A n
    for cuantos in range(10, n + 1):
        datos = [
            generaStrAzar(20) for i in range(cuantos * 2)
        ]  # generamos n strings aleatorios
        particion1 = datos[: int(cuantos)]
        particion2 = datos[int(cuantos) :]
        for elem in particion1:
            if elem in particion2:
                print("no es particionn en " + str(cuantos))
                break
        print("Para " + str(cuantos) + " datos")
        for m in range(10, 700):
            bandera2 = True
            for k in range(1, 12):
                bf = BloomFilter(m, k)
                for elem in particion1:
                    bf.inserta(elem)
                falsosPositivos = 0
                for elemento in particion2:
                    if bf.busca(elemento):
                        falsosPositivos += 1
                if (falsosPositivos / len(particion2)) <= porFalsosP:
                    print(
                        "Porcentaje de falsos positivos: "
                        + str(falsosPositivos / len(particion2))
                        + " con m: "
                        + str(m)
                        + " y k: "
                        + str(k)
                    )
                    diccionario["n"].append(cuantos)
                    diccionario["m"].append(m)
                    bandera2 = False
                if bandera2 == False:
                    break
            if bandera2 == False:
                break

    var = pd.DataFrame(diccionario)
    var.to_csv("Prueba.csv", index=False)
    df = pd.read_csv("Prueba.csv")
    n = df["n"]
    m = df["m"]
    plt.figure(figsize=(10, 6))
    plt.plot(n, m, marker="o")
    plt.xlabel("Tamaño n")
    plt.ylabel("Valor m")
    plt.title(
        "Gráfica de tamaño m en función de n datos y porcentaje de falsos positivos de "
        + str(porFalsosP * 100)
        + "%"
    )
    plt.grid(True)
    plt.show()
    # plt.savefig("grafica.png")


# bloomF(100, 0.01)
bloomF(100, 0.2)
# bloomF(100, 0.5)


""" print("Para 5 por ciento de falsos positivos")
bloomF(100, 0.05)
print("Para 7.5 por ciento de falsos positivos")
bloomF(100, 0.075)
print("Para 10 por ciento de falsos positivos")
bloomF(100, 0.1)
print("Para 12.5 por ciento de falsos positivos")
bloomF(100, 0.15)
print("Para 15 por ciento de falsos positivos")
bloomF(100, 0.2)
print("Para 17.5 por ciento de falsos positivos")
bloomF(100,0.3) """
