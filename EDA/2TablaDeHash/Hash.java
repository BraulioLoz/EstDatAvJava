package TablaDeHash;

public class Hash<T extends Comparable<T>> {
    NodoHash<T> tabla[];
    int cont;
    float factor_de_carga = (float) 0.7; // Esto es para que no se llene la tabla de hash y se vuelva lenta la busqueda
                                         // y la insercion de elementos

    public boolean busca(T elem) { // O(1)
        NodoHash<T> actual;
        int pos = fuHash(elem) % tabla.lengh;
        actual = tabla[pos].sig;
        while (actual != null && acthal.getElem().compareTo(elem) != 0) {
            actual = actual.sig;
        }
        return actual != null;
    }

    public void inserta(T elem) { // O(1)
        int pos;
        NodoHash<T> nuevo = new NodoHash(elem);
        pos = fuHash(elem) % tabla.lengh;
        nuevo.sig = tabla[pos].sig;
        tabla[pos].sig = nuevo;
        cont++;
        if (cont / tabla.lengh >= factor_de_carga) {
            expande();
        }

    }

}
