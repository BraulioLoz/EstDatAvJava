public class NodoBin<T extends Comparable <T>> {
    T elem;
    NodoBin<T> izq;
    NodoBin<T> der;
    NodoBin<T> papa;
    int fe;

    public NodoBin(T elemento) {
        elem = elemento;
        izq = der = papa = null;
    }

    public NodoBin<T> getPadre() {
        return papa;
    }
    public T getElem() {
        return elem;
    }
    public NodoBin<T> getIzq() {
        return izq;
    }

    public NodoBin<T> getDer() {
        return der;
    }

    public void setElem(T elemN) {
        elem = elemN;
    }
    public void setIzq(NodoBin<T> izqN) {
        izq = izqN;
    }

    public void setDer(NodoBin<T> derN) {
        der = derN;
    }

    public int numDescendientes() {
        int resp = 0;
        if (izq != null) {
            resp += izq.numDescendientes() + 1;
        }
        if (der != null) {
            resp += der.numDescendientes() + 1;
        }
        return resp;
    }

    public void cuelga (NodoBin<T> n) {
        if ( n == null) {
            return;
        }
        if (n.getElem().compareTo(elem) <=0) {
            izq = n;
        } else {
            der = n;
        }
        n.setPadre(this);
    }

    public void setPadre(NodoBin<T> nodoBin) {
        papa = nodoBin;
    }


    


    
}

    

