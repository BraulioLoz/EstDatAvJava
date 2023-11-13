package Ordenamiento.src;

/**
 * Ordenamiento
 */
public class Ordenamiento  {


    public static void selecDir (int [] arr) {
        int min;
        for (int i= 0;i<arr.length-1; i++) {
            min = i;
            for (int j = i +1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr,min, i);
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Mover los elementos del arreglo que son mayores que key
            // a una posición adelante de su posición actual
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }


    public static void bubbleSort(int[] arr) {
        for (int i = arr.length-1; i > 0; i--) {
            for (int j = 0; j < i -1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Intercambiar los elementos usando una función de swap
                    swap(arr, j, j + 1);
                }
            }
        }
    }
    

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quick(int[] arr, int min, int max, int n) {
        if (min>= max) {
            return;
        }else {
            int pivote = particion(arr, min, max);
            n = particionN(arr, min, max, n);
            quick(arr, min, pivote-1,n);
            quick(arr, pivote+1, max,n);
            System.out.println("Los pasos son: "+n);
        }
        
    }

    private static int particion (int[]arr, int min, int max) {
        int pivote = arr[max];
        int temp = min-1;
        for (int i = min; i<max; i++) {
            if (arr[i]<pivote) {
                temp +=1;
                swap(arr, temp, i);
            } 
        }
        swap (arr, temp+1, max);
        return temp+1;
    }


    private static int particionN (int[]arr, int min, int max,int n) {
        int pivote = arr[max];
        int temp = min-1;
        for (int i = min; i<max; i++) {
            n+=1;
            if (arr[i]<pivote) {
                temp +=1;
                swap(arr, temp, i);
            } 
        }
        swap (arr, temp+1, max);
        return n;
    }

    public static <T extends Comparable <T>>void quickT(T[] arr, int min, int max) {
        if (min>= max) {
            return;
        }else {
            int pivote = particionT(arr, min, max);
            quickT(arr, min, pivote-1);
            quickT(arr, pivote+1, max);
        }
        
    }

    private static <T extends Comparable<T>> int particionT (T[]arr, int min, int max) {
        T pivote = arr[max];
        int temp = min-1;
        for (int i = min; i<max; i++) {
            if (arr[i].compareTo(pivote)<0) {
                temp +=1;
                swapT(arr, temp, i);
            } 
        }
        swapT(arr, temp+1, max);
        return temp+1;
    }

    public static <T> void swapT(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static <T extends Comparable <T>>void merge(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int mid = inicio + fin / 2;

            // Ordenar la mitad izquierda y la mitad derecha del arreglo
            merge(arr, inicio, mid);
            merge(arr, mid+1, inicio);

            // Combinar las mitades ordenadas
            mergeT(arr, inicio, mid, fin);
        }
    }

    public static <T extends Comparable <T>> void mergeT(int[]arr,int inicio, int mid, int fin) { 
        //double mid = Math.floor(Math.random()*fin);
        int n1 = mid - inicio + 1;
        int n2 = fin - mid;
        int[] izq = new int [n1];
        int[] der = new int [n2];
        for (int i = 0; i< n1; i++) {
            izq[i] = arr[inicio + i];
        }
        for (int j = 0; j< n2; j++) {
            der[j] = arr[mid + 1 + j];
        }
        int i = 0;
        int j = 0;
        int k = inicio;
        while ( i < n1 && j < n2 ) {
            if (izq[i] <= (der[j])) {   
                arr[k] = izq[i];
                i++;
            }else {
                arr[k] = der[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = izq[i];
            i++;
            k++;
        }
        while (j< n2) {
            arr[k] = der[j];
            j++;
            k++;
        }
        
    }

    

    public static void main(String[] args) {
        int [] arr= {12,9,7,1,-10,6};
        //quick(arr, 0, 5,0);
        for (int i : arr) {
            System.out.print(i+", ");
        }
        System.out.println("\n");
        merge(arr,0, arr.length-1);
        for (int i : arr) {
            System.out.print(i+", ");
        }


    }

    

    
    


}