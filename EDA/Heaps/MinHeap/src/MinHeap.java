public class MinHeap <T extends Comparable<T>> {

    T datos [];
    int cont;

    public void inserta (T elem) { //n logn y borrar n logn y heapsort n logn como merge sort
        int aux; 
        cont++;
        if (cont >= datos.length) {
            expande();
        } 
        datos[cont] = elem;
        aux = cont;
        while (aux>1&& datos[aux>>1].compareTo(elem) > 0) {
            datos[aux] = datos[aux>>1];
            datos[aux>>1] = elem;
            aux >>= 1;
        }
    }

    public T borra (T elem) {
        T resp = datos[1];
        datos[1] = datos [cont];
        cont--;
        int aux = 1;
        while (aux<= cont) {
            if (aux<<1 <= cont) { //hijo izq
                if ((aux<<1)+1 <=cont) { //hijo derecho
                    if (datos[aux<<1].compareTo(datos[(aux<<1)+1]) < 0) {
                        if (datos[aux].compareTo(datos[aux<<1]) > 0) {
                            swap(datos,aux,aux<<1);
                            aux= aux<<1;
                        } 
                    } else {
                        if (datos[aux].compareTo(datos[(aux<<1)+1]) >0){
                            swap(datos,aux,(aux<<1)+1);
                            aux= (aux<<1)+1;
                        } 
                    }
                }
                if (datos[aux].compareTo(datos[aux<<1]) >0 ) {
                    swap (datos, aux, aux<<1);
                } else {
                    break;
                }
            }
        }
        return resp;
    }    



    private void swap(T[] datos2, int aux, int i) {
        T temp = datos2[aux];
        datos2[aux] = datos2[i];
        datos2[i] = temp;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
