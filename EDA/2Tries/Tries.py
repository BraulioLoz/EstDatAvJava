class NodoTrie:
    def __init__(self):
        self.hijos = {}
        self.fin = False  # nos dice si es el final de una palabra


class Trie:
    def __init__(self):
        self.raiz = NodoTrie()
        self.cont = 0

    def inserta(self, palabra):  # O(n) n = longitud de la palabra a insertar
        actual = self.raiz  # nodo actual
        for letra in palabra:  # recorremos la palabra
            if letra in actual.hijos.keys():  # si la letra esta en los hijos
                actual = actual.hijos[letra]  # actualizamos el nodo actual
            else:  # si no esta en los hijos
                actual.hijos[letra] = NodoTrie()  # creamos un nuevo nodo
                actual = actual.hijos[letra]  # actualizamos el nodo actual
        actual.fin = True
        self.cont += 1

    def busca(self, palabra):  # O(n) n = longitud de la palabra a buscar
        actual = self.raiz
        flag = True
        for letra in palabra:
            if letra in actual.hijos.keys():
                actual = actual.hijos[letra]
            else:
                flag = False
        return actual.fin and flag

    def buscaR(self, actual, palabra):
        if len(palabra) == 0:
            return actual.fin
        sig = palabra[0]
        if sig not in actual.hijos.keys():
            return False
        return self.buscaR(actual.hijos[sig], palabra[1:])

    def borra(self, palabra):  # O(n) n = longitud de la palabra a borrar
        return self.borraR(self.raiz, palabra)

    def borraR(self, actual, palabra):
        if len(palabra) == 0:
            actual.fin = False
            self.cont -= 1
            if len(actual.hijos.keys()) == 0:
                return True
            else:
                return False
        sig = palabra[0]
        if sig not in actual.hijos.keys():
            return False
        resp = self.buscaR(actual.hijos[sig], palabra[1:])
        if not resp:
            return False
        actual.hijos.pop(sig)
        return len(actual.hijos.keys()) == 0 and actual.fin == False

    def str(self):  # O(n) n = numero de palabras en el trie
        return self.strR(self.raiz, "")

    def strR(self, nodoTrie, palabra):
        if nodoTrie.fin:
            print(palabra)
        for letra in nodoTrie.hijos.keys():
            self.strR(nodoTrie.hijos[letra], palabra + letra)

    def imprimirOrden(self):  # O(n) n = numero de palabras en el trie
        self.imprimirOrdenR(self.raiz, "")

    def imprimirOrdenR(self, NodoTrie, palabra):
        if NodoTrie.fin:
            print(palabra)
        for letra in sorted(NodoTrie.hijos.keys()):
            self.imprimirOrdenR(NodoTrie.hijos[letra], palabra + letra)

    """ def autoCompletar(self):
         """


trie = Trie()
trie.inserta("hola")
trie.inserta("arbol")
trie.inserta("zorro")
trie.inserta("Perro")
trie.inserta("vida")
trie.inserta("perro")

""" print(trie.busca("arbol"))
print(trie.borra("arbol"))
print(trie.inserta("bol"))
print(trie.busca("bol"))
print(trie.borra("bol"))
print(trie.busca("bol"))
print(trie.busca("arbol")) """
# print(trie.str())
print(trie.imprimirOrden())
