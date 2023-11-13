import networkx as nx
import pandas as pd
import matplotlib.pyplot as plt
import warnings
import heapdict as Q

warnings.filterwarnings("ignore")


class Grafo:
    def __init__(self):
        self.G = {}
        self.visitados = {}

    def __str__(self):
        return str(self.G)

    def insertaD(self, v1, v2, peso=None):  # D de dirigido
        if v1 not in self.G:
            self.G[v1] = {}
        if v2 not in self.G:
            self.G[v2] = {}
        self.G[v1][v2] = peso

    def inserta(self, v1, v2, peso=None):
        self.insertaD(v1, v2, peso)
        self.insertaD(v2, v1, peso)

    def DFS(self):
        visitados = {}
        for i in self.G.keys():
            visitados[i] = False
        lista = []
        for u in visitados:
            if not visitados[u]:
                self.DFSR(u, lista, visitados)
        # return str(lista)

    def DFSR(self, v, lista, visitados):
        if visitados[v] == True:
            return
        visitados[v] = True
        lista = lista + [v]
        for u in self.G[v]:
            if not visitados[u]:
                self.DFSR(u, lista, visitados)
        print(lista)
        # return lista

    def bfs(self):
        visitados = {}
        for i in self.G.keys():
            visitados[i] = False
        lista = []
        for v in visitados:
            if not visitados[v]:
                self.BFS(v, lista, visitados)
        print(lista)
        # return str(lista)

    def BFS(self, v, lista, visitados):
        cola = [v]
        visitados[v] = True
        while len(cola) > 0:
            dato = cola.pop(0)
            lista = lista + [dato]
            for u in self.G[dato]:
                if not self.G[dato]:
                    visitados[u] = True
                    cola.append(u)
     
    def Prin (V):
        for u in self.G:
            
    
            
        


g = Grafo()
g.inserta("A", "B", 5)
g.inserta("A", "C", 7)
g.inserta("B", "C", 1)
g.inserta("B", "D", 2)
g.DFS()
# g.BFS()
