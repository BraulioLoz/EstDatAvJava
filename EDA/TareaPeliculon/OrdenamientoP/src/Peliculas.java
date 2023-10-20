import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Peliculas<T extends Comparable<T>> {
    
    public static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static <T> void selecDir (T [] arr) {
        int min;
        // int comparaciones=0;
        for (int i= 0;i<arr.length-1; i++) {
            min = i;
            for (int j = i +1; j < arr.length; j++) {
                if (((Integer) arr[j]).compareTo((Integer) arr[min]) < 0) {
                    min = j;
                    // comparaciones++;
                }
            }
            swap(arr,min, i);
        }
        // System.out.println("Num de comparaciones de SeleccionDirecta: "+comparaciones);
    }

    public static <T> void insertionSort(T[] arr) {
        int n = arr.length;
        int comparaciones=0;
        for (int i = 1; i < n; i++) {
            T key = arr[i];
            int j = i - 1;

            // Mover los elementos del arreglo que son mayores que key
            // a una posición adelante de su posición actual
            while (j >= 0 && ((Integer)arr[j]).compareTo( (Integer) key) > 0) {
                arr[j + 1] = arr[j];
                j--;
                comparaciones++;
            }
            arr[j + 1] = key;
        }
        // System.out.println("Num de comparaciones de InsertionSort: "+comparaciones);
    }


    public static <T> void bubbleSort(T[] arr) {
        int comparaciones=0;
        for (int i = arr.length-1; i > 0; i--) {
            for (int j = 0; j < i -1; j++) {
                if (((Integer) arr[j]).compareTo((Integer) arr[j+1]) > 0) {
                    swap(arr, j, j+1);
                    comparaciones++;
                }
            }
        }
        // System.out.println("Num de comparaciones de BubbleSort: "+comparaciones);
    }

    public static <T> void quick(T[] arr, int min, int max) {
        
        if (min>= max) {
            return;
        }else {
            int pivote = particion(arr, min, max);
            quick(arr, min, pivote-1);
            quick(arr, pivote+1, max);
        }
    }

    private static <T> int particion (T[]arr, int min, int max) {
        T pivote = arr[max];
        int temp = min-1;
        for (int i = min; i<max; i++) {
            if (((Integer) arr[i]).compareTo((Integer) pivote) < 0) {
                temp +=1;
                swap(arr, temp, i);
            }
        }
        swap(arr, temp+1, max);
        return temp+1;
    }


    public static <T> void merge(T[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int mid = inicio + fin / 2;

            // Ordenar la mitad izquierda y la mitad derecha del arreglo
            merge(arr, inicio, mid);
            merge(arr, mid+1, inicio);

            // Combinar las mitades ordenadas
            mergeT(arr, inicio, mid, fin);
        }
    }

    public static <T> void mergeT(T[]arr,int inicio, int mid, int fin) { 
        
        //double mid = Math.floor(Math.random()*fin);
        int n1 = (mid - inicio) + 1;
        int n2 = fin - mid;
        Object[] izq = new Object [n1];
        Object[] der = new Object [n2];
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
            if (((Integer) izq[i]).compareTo((Integer) der[j]) <= 0) {   
                arr[k] = (T) izq[i];
                i++;
                //comparaciones++;
            }else {
                arr[k] = (T) der[j];
                j++;
                // comparaciones++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = (T) izq[i];
            i++;
            k++;
        }
        while (j< n2) {
            arr[k] = (T) der[j];
            j++;
            k++;
        }
        //System.out.println(comparaciones);
    }


    public static <T> void mixcoac(String file) {
        File aux1 = new File("archivo1");
        File aux2 = new File("archivo2");
        File aux3 = new File("archivo3");
        File aux4 = new File("archivo4");
        File ordenado = new File("ordenado");
        File principal = new File(file);
        Scanner lecArchivo, laux1, laux2;
        try {
            lecArchivo = new Scanner(principal);
            while (lecArchivo.hasNext()) {
                laux1 = new Scanner(aux1);
                laux2 = new Scanner(aux2);
                laux1.next(lecArchivo.next());
                laux1.next(lecArchivo.next());
                laux2.next(lecArchivo.next());
                laux2.next(lecArchivo.next());

                mix(new Scanner(aux1), new Scanner(aux2), aux3); //Guardamos aux1 y aux2 en un aux3 ordenado 

                laux1 = new Scanner(aux1);
                laux2 = new Scanner(aux2);
                laux1.next(lecArchivo.next());
                laux1.next(lecArchivo.next());
                laux2.next(lecArchivo.next());
                laux2.next(lecArchivo.next());

                mix(new Scanner(aux1), new Scanner(aux2), aux4); //Guardamos aux1 y aux2 en un aux4 ordenado

                mix(new Scanner(aux3), new Scanner(aux4), ordenado); //Guardamos aux3 y aux4 en un archivo ordenado
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
        } 
    }

    private static <T> void mix(Scanner archivo1, Scanner archivo2, File arcivoC) { 
        /* Ordenar el primer archivo primero, despues usar un scanner simultaneo para ir haciendo compareTo y meter los datos ordenados */
        ArrayList <T> auxList = new ArrayList<>();
        while (archivo1.hasNext()){
            auxList.add((T)archivo1.next());
        }
        while (archivo2.hasNext()) {
            auxList.add((T)archivo2.next());
        }
        auxList.toArray();
        mergeSort(auxList);
        try {
            FileWriter escritor = new FileWriter(arcivoC);
            for (int i = 0; i<auxList.size(); i++) {
                escritor.write(auxList.get(i).toString());
            }
            escritor.close();
        } catch (Exception e) {
            System.out.println("Error al escribir el archivo");
        } 
    }

    public void ordenaArch (String arch) {
        File entrada = new File(arch); 
        Scanner lecArchivo; 
        ArrayList <Object> arc1 = new ArrayList <Object> ();
        ArrayList <Object> arc2 = new ArrayList <Object> ();
        try { 
            lecArchivo = new Scanner(entrada);
            while (!OutOfMemoryError.class.isInstance(lecArchivo) || !lecArchivo.hasNext()) {
                arc1.add(lecArchivo.next()); //1RA MITAD DEL ARCHIVO
                arc2.add(lecArchivo.next()); //2DA MITAD DEL ARCHIVO
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
        merge(arc1); //Ordenamos la primera mitad
        merge(arc2); //Ordenamos la segunda mitad

        merge2(arc1, arc2); //Ordenamos las 2 mitades combinadas
        merge2(arc1, arc2); //Ordenamos las 2 mitades combinadas por segunda vez 
        try {
            lecArchivo = new Scanner(entrada);
            int i = 0;
            while (!OutOfMemoryError.class.isInstance(lecArchivo) || !lecArchivo.hasNext() && arc1.get(i) != null) {
                int k = 0;
                while (!OutOfMemoryError.class.isInstance(lecArchivo) || !lecArchivo.hasNext() && arc1.get(i) != null) {
                    arc1.set(i, lecArchivo.next()); //1RA MITAD DEL ARCHIVO
                    i++;
                }
                int j = 0;
                while (!OutOfMemoryError.class.isInstance(lecArchivo) || !lecArchivo.hasNext() && arc2.get(j) != null) {
                    String value = lecArchivo.next();
                    arc2.set(j, value); //2DA MITAD DEL ARCHIVO
                    j++;
                }
                j++;
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
    }

    private void merge2 (ArrayList<T> arc1, ArrayList<T> arc2) {
        int siz1 = arc1.size(); //TAMAÑO DE LA 1RA MITAD
        int siz2 = arc2.size(); //TAMAÑO DE LA 2DA MITAD

        Object[] arr11 = new Object[siz1/2]; //1RA MITAD DE LA 1RA MITAD
        Object[] arr12 = new Object[siz1/2]; //2DA MITAD DE LA 1RA MITAD
        Object[] arr21 = new Object[siz2/2]; //1RA MITAD DE LA 2DA MITAD
        Object[] arr22 = new Object[siz2/2]; //2DA MITAD DE LA 2DA MITAD

        for (int i = 0; i<siz1/2; i++) { //Llenamos con la primera mitad
            arr11[i] = arc1.get(i);  //ORDENADO YA
            arr12[i] = arc1.get(i+siz1/2);  //ORDENADO YA
        }
        for (int j = 0; j<siz2/2; j++) { //Llenamos con la segunda mitad
            arr21[j] = arc2.get(j);  //ORDENADO YA
            arr22[j] = arc2.get(j+siz2/2);  //ORDENADO YA
        }
        
        Object [] aux1 = new Object[siz1]; //Auxiliar para la 1ra mitad general
        Object [] aux2 = new Object[siz2]; //Auxiliar para la 2da mitad general

        for (int i = 0; i<siz1; i++) { //COMBINAMOS LAS PRIMERAS 2 MITADES 
            aux1[i] = arr11[i];  //1ra parte del aux con la 1ra mitad ordenada de la 1ra mitad
            aux1[i+siz1/2] = arr21[i];  //2da parte del aux con la 1ra mitad ordenada de la 2da mitad
        }
        for (int j = 0; j<siz2; j++) {
            aux2[j] = arr21[j];  //1ra parte del aux con la 2da mitad ordenada de la 1ra mitad
            aux2[j+siz2/2] = arr22[j];  //2da parte del aux con la 2da mitad ordenada de la 2da mitad
        }
        merge(aux1); //Ordenamos la primera mitad combinada 
        merge(aux2); //Ordenamos la segunda mitad combinada

        arc1 = aux1.toArray(new Object[0]);
        arc2 = aux2.toArray(new Object[0]);
    }






    public static void main(String[] args) {
        
        File entrada; 
        //"C:\Users\Braulio LC\Documents\EDA\TareaPeliculon\OrdenamientoP\movie_titles2.txt"
        entrada = new File ("/Users/Braulio LC/Documents/EDA/TareaPeliculon/OrdenamientoP/movie_titles2.txt");

        Scanner lecArchivo;
        int tamaño = 10000;
        Object[] clave = new Object [tamaño];

        try {
            lecArchivo = new Scanner (entrada);

            for (int i = 0; i<tamaño;i++) {
                String line = lecArchivo.nextLine();
                int clav = Integer.parseInt(line.split(",")[0]);
                clave [i] = clav;
            }

            lecArchivo.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //System.out.println(Arrays.toString(clave));


        /* -------------------- 10 DATOS -------------------- */
        Object[] clave1 = new Object[10];
        for (int i = 0; i<10;i++) {
            clave1[i]= clave[i];
        }

        
        /* -------------------- 100 DATOS -------------------- */
        Object[] clave2 = new Object[100];
        for (int i =0; i<100;i++) {
            clave2[i] = clave[i];
        }


        /* -------------------- 250 DATOS -------------------- */
        Object[] clave3 = new Object[250];
        for (int i =0; i<250;i++) {
            clave3[i] = clave[i];
        }


        /* -------------------- 500 DATOS -------------------- */
        Object[] clave4 = new Object[500];
        for (int i=0; i<500;i++) {
            clave4[i] = clave[i]; 
        }


        /* -------------------- 750 DATOS -------------------- */
        Object [] clave5 = new Object[750];
        for (int i = 0; i<750;i++) {
            clave5[i] = clave[i];
        }


        /* -------------------- DATOS ORDENADOS -------------------- */
        insertionSort(clave1);
        insertionSort(clave2);
        insertionSort(clave3);
        insertionSort(clave4);
        insertionSort(clave5);

        long startTime;
        long endTime;
        /* Selecion directa */
        /*long startTime = System.currentTimeMillis();
        selecDir(clave1);
        long endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        selecDir(clave2);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        //selecDir(clave3);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        //selecDir(clave4);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        //selecDir(clave5);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();



        /* Insertsion sort */
        /*startTime = System.currentTimeMillis();
        insertionSort(clave1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        insertionSort(clave2);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        insertionSort(clave3);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        insertionSort(clave4);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        insertionSort(clave5);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();



        /* bubble sort */
        /* startTime = System.currentTimeMillis();
        bubbleSort(clave1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */

        /* startTime = System.currentTimeMillis();
        bubbleSort(clave2);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */

        /* startTime = System.currentTimeMillis();
        bubbleSort(clave3);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */

        /* startTime = System.currentTimeMillis();
        bubbleSort(clave4);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */

        /* startTime = System.currentTimeMillis();
        bubbleSort(clave5);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */



        /* quick sort */
        /* startTime = System.currentTimeMillis(); */
        //quick(clave1, 0, clave1.length-1);
        /* endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();  */

        /*startTime = System.currentTimeMillis();
        quick(clave2, 0, clave2.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */

        /*startTime = System.currentTimeMillis();
        quick(clave3, 0, clave3.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */

        /*startTime = System.currentTimeMillis();
        quick(clave4, 0, clave4.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */

        /*startTime = System.currentTimeMillis();
        quick(clave5, 0, clave5.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */



        /* merge sort */
        /*startTime = System.currentTimeMillis();
        merge(clave1, 0, clave1.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */
        
        /* startTime = System.currentTimeMillis();
        merge(clave2, 0, clave2.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */
        
        /* startTime = System.currentTimeMillis();
        merge(clave3, 0, clave3.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */
        
        /* startTime = System.currentTimeMillis();
        merge(clave4, 0, clave4.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */
        
        /*startTime = System.currentTimeMillis();
        merge(clave5, 0, clave5.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */
        



        /* -------------------- DATOS INVERSOS -------------------- */
        /* for(int i = 0; i < clave1.length / 2; i++) {
            swap(clave1, i, clave1.length - i - 1);
        }
        for(int i = 0; i < clave2.length / 2; i++) {
            swap(clave2, i, clave2.length - i - 1);
        }
        for(int i = 0; i < clave3.length / 2; i++) {
            swap(clave3, i, clave3.length - i - 1);
        }
        for(int i = 0; i < clave4.length / 2; i++) {
            swap(clave4, i, clave4.length - i - 1);
        }
        for(int i = 0; i < clave5.length / 2; i++) {
            swap(clave5, i, clave5.length - i - 1);
        } */

        /* Selecion directa */
        /* startTime = System.currentTimeMillis();
        selecDir(clave1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        selecDir(clave2);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        selecDir(clave3);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        selecDir(clave4);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        selecDir(clave5);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */



        /* Insertsion sort */
        /* startTime = System.currentTimeMillis();
        insertionSort(clave1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        insertionSort(clave2);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        insertionSort(clave3);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        insertionSort(clave4);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();*/

        /*startTime = System.currentTimeMillis();
        insertionSort(clave5);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();*/



        /* bubble sort */
        /* startTime = System.currentTimeMillis();
        bubbleSort(clave1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        bubbleSort(clave2);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        bubbleSort(clave3);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        bubbleSort(clave4);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();*/

        /*startTime = System.currentTimeMillis();
        bubbleSort(clave5);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */



        /* quick sort */
        /* startTime = System.currentTimeMillis();
        quick(clave1, 0, clave1.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); 

        startTime = System.currentTimeMillis();
        quick(clave2, 0, clave2.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        quick(clave3, 0, clave3.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        quick(clave4, 0, clave4.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();*/

        /* startTime = System.currentTimeMillis();
        quick(clave5, 0, clave5.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();  */



        /* merge sort */
        /* startTime = System.currentTimeMillis();
        merge(clave1, 0, clave1.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        merge(clave2, 0, clave2.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        merge(clave3, 0, clave3.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        merge(clave4, 0, clave4.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */

        /*startTime = System.currentTimeMillis();
        merge(clave5, 0, clave5.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */





        /* -------------------- DATOS ALEATORIOS -------------------- */
        /* ArrayList <Object> claves11 = new ArrayList<Object>();
        for (int i = 0; i<clave1.length;i++) {
            claves11.add(clave1[i]);
        }
        Collections.shuffle(claves11);
        claves11.toArray(clave1);

        ArrayList <Object> claves22 = new ArrayList<Object>();
        for (int i = 0; i<clave2.length;i++) {
            claves22.add(clave2[i]);
        }
        Collections.shuffle(claves22);
        claves22.toArray(clave2);

        ArrayList <Object> claves33 = new ArrayList<Object>();
        for (int i = 0; i<clave3.length;i++) {
            claves33.add(clave3[i]);
        }
        Collections.shuffle(claves33);
        claves33.toArray(clave3);

        ArrayList <Object> claves44 = new ArrayList<Object>();
        for (int i = 0; i<clave4.length;i++) {
            claves44.add(clave4[i]);
        }
        Collections.shuffle(claves44);
        claves44.toArray(clave4);

        ArrayList <Object> claves55 = new ArrayList<Object>();
        for (int i = 0; i<clave5.length;i++) {
            claves55.add(clave5[i]);
        }
        Collections.shuffle(claves55);
        claves55.toArray(clave5); */



        /* Selecion directa */
        /* startTime = System.currentTimeMillis();
        selecDir(clave1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        selecDir(clave2);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        selecDir(clave3);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        selecDir(clave4);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        selecDir(clave5);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */


        /* Insertsion sort */
        /* startTime = System.currentTimeMillis();
        insertionSort(clave1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        insertionSort(clave2);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        insertionSort(clave3);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        insertionSort(clave4);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        insertionSort(clave5);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */



        /* bubble sort */
        /*startTime = System.currentTimeMillis();
        bubbleSort(clave1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        bubbleSort(clave2);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        bubbleSort(clave3);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        bubbleSort(clave4);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        bubbleSort(clave5);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();*/

        /* quick sort */
        /*startTime = System.currentTimeMillis();
        quick(clave1, 0, clave1.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); 

        startTime = System.currentTimeMillis();
        quick(clave2, 0, clave2.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        quick(clave3, 0, clave3.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        quick(clave4, 0, clave4.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        quick(clave5, 0, clave5.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); */

        /* merge sort */
        /*startTime = System.currentTimeMillis();
        merge(clave1, 0, clave1.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        merge(clave2, 0, clave2.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        merge(clave3, 0, clave3.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        merge(clave4, 0, clave4.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        merge(clave5, 0, clave5.length-1);
        endTime = (System.currentTimeMillis() - startTime);
        System.out.println(endTime);
        System.out.println(); 

        /* System.out.println("\n"+"AQUI ESTA EL ARREGLO ORDENADO");
        System.out.println("\n"+Arrays.toString(clave1)); */
        
    }
}
