public class NodoAVL <T extends Comparable <T>> extends NodoBin<T>  {

    
    int fe;
    public NodoAVL(T elemento) {
        super(elemento);
        fe = 0;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public int getFe() {
        return fe;
    }
    public void Cuelga2 (NodoAVL<T>n, char direccion) {
        if (direccion == 'i') {
            izq = n;
        } else {
            if (direccion == 'd') {
                der = n;
            }
        }
        if (n!= null) {
            n.setPadre(this);
        }
    }


    
    
}
