public interface BinarySearchTreeADT <T extends Comparable<T>> extends BianaryTreeADT<T> {
    public void inserta (T elem);
    public void borra (T elem);
    public T findMin();
    public T findMax(); 
}

