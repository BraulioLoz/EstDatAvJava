public interface BinaryAVLTreeADT <T extends Comparable<T>> extends BianaryTreeADT<T> {
    public void inserta (T elem);
    public void borra (T elem);
    public NodoAVL<T> rota(NodoAVL<T> n);
    public NodoAVL<T> busca(T elem);
    
    
    
}
