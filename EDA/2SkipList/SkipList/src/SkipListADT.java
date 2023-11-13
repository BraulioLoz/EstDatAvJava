public interface SkipListADT <T extends Comparable<T>>{

    /* public void inserta (T elem);
    public T borra (T elem); */
    public NodoSkip<T> busca (T elem);
    /* public boolean contiene (T elem);
    public boolean esVacia ();
    public int size ();
    public T getMin ();
    public T getMax ();
    public T getElem (int i); */
    

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
