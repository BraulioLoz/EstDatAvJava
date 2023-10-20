package TablaDeHash;

public class Hash <T extends Comparable <T>>{
    NodoHash<T> tabla[];
    int cont;
    float factor_de_carga = (float) 0.7;


    public boolean busca(T elem) {
        NodoHash<T> actual;
        int pos = fuHash(elem)%tabla.lengh;
        actual = tabla[pos].sig;
        while (actual != null && acthal.getElem().compareTo(elem) != 0) {
            actual = actual.sig;
        }
        return actual != null;
    }

    public void inserta (T elem) {
        int pos;
        NodoHash<T> nuevo = new NodoHash(elem);
        pos = fuHash(elem)%tabla.lengh;
        nuevo.sig = tabla[pos].sig;
        tabla[pos].sig = nuevo;
        cont++;
        if (cont/tabla.lengh >= factor_de_carga) {
            expande();
        }
        
    }

}
