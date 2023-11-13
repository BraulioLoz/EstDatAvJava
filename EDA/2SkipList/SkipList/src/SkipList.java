public class SkipList<T extends Comparable<T>> {

    NodoSkip<T> cabeza, cola;
    int cont;
    int maxNivel;
    int nivelActual;

    public SkipList() {
        cabeza = new NodoSkip<T>(null);
        cola = new NodoSkip<T>(null);
        cabeza.der = cola;
        cola.izq = cabeza;
        cabeza.nivel = cola.nivel = 0;
        maxNivel = nivelActual = 1;
        cont = 0;
    }

    public NodoSkip<T> busca(T elem) {
        NodoSkip<T> actual = cabeza.der;
        boolean acabe = false;
        while (actual != null && actual.elem != null && !acabe) {
            if (actual.der != null && actual.der.elem != null && actual.der.elem.compareTo(elem) <= 0) {
                actual = actual.der;
            } else {
                if (actual.abajo != null) {
                    actual = actual.abajo;
                } else {
                    acabe = true;
                }
            }
        }
        return actual;
    }

    public void elimLiga(NodoSkip<T> nod) {
        nod.izq.der = nod.der;
        nod.der.izq = nod.izq;
    }

    public void borra(T elem) {
        NodoSkip<T> nod = busca(elem);
        if (nod.elem.compareTo(elem) == 0) {
            borr(nod);
            cont--;
        }
        if (nivelActual > Math.log(cont + 1) + 1) {
            cabeza = cabeza.abajo;
            cola = cola.abajo;
            elimNiv(cabeza);
            maxNivel--;
        }
    }

    private void borr(NodoSkip<T> nod) {
        if (nod != null) {
            elimLiga(nod);
            borr(nod.arriba);
        }
    }

    public void elimNiv(NodoSkip<T> nod) {
        if (nod != null) {
            nod.arriba = null;
            elimNiv(nod.der);
        }
    }

    public void inserta(T elem) {
        NodoSkip<T> nod = new NodoSkip<T>(elem);
        NodoSkip<T> aux = busca(elem);
        cuelgaDer(aux, nod); // agregas abajo
        cont++;
        int nivel = 1;
        while (Math.random() < 0.5) {
            nivel++;
            if (nivel < Math.log((cont + 1) + 1)) {
                NodoSkip<T> nuevo = new NodoSkip<T>(elem);
                nuevo.abajo = nod;
                nod.arriba = nuevo; // agregas arriba
                nod = nuevo;
                if (nivel >= maxNivel) {
                    agregaNiv();
                }
                while (aux.arriba == null) {
                    aux = aux.izq;
                }
                aux = aux.arriba;
                nod.arriba = new NodoSkip<T>(elem);
                nod.arriba.abajo = nod;
                nod = nod.arriba;
                cuelgaDer(aux, nod);
                nivel++;
            } else {
                break;
            }
        }
    }

    private void agregaNiv() {
        NodoSkip<T> nuevaCabeza = new NodoSkip<T>(null);
        NodoSkip<T> nuevaCola = new NodoSkip<T>(null);
        nuevaCabeza.der = nuevaCola;
        nuevaCabeza.abajo = cabeza;
        cabeza.arriba = nuevaCabeza;
        nuevaCola.izq = nuevaCabeza;
        nuevaCola.abajo = cola;
        cola.arriba = nuevaCola;
        cabeza = nuevaCabeza;
        cola = nuevaCola;
        maxNivel++;
    }

    private void cuelgaDer(NodoSkip<T> nod1, NodoSkip<T> nod2) {
        nod2.izq = nod1;
        nod2.der = nod1.der;
        nod1.der.izq = nod2;
        nod1.der = nod2;
    }

    // te amo :)
}
