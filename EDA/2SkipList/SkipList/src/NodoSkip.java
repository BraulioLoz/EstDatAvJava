public class NodoSkip<T> {
    T elem;
    NodoSkip<T> arriba, abajo, izq, der;
    int nivel;

    public NodoSkip(T elem) {
        this.elem = elem;
        arriba = abajo = izq = der = null;
    }

}
