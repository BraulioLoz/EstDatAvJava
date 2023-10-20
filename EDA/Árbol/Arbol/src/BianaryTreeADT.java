import java.util.Iterator;

public interface BianaryTreeADT<T extends Comparable<T>> {

    public boolean isEmpty();
    public int size();
    public boolean find(T elemento);
    public Iterator<T> preorden();
    public void insert(T data);
    public void delete(T data);
    public void deleteAll();
    public void deleteAll(T data);
    public void deleteAll(T data, int n);



    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
