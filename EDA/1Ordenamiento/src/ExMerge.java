import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ExMerge {

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
                lecArchivo.next() = arc1.get(i); //1RA MITAD DEL ARCHIVO
                i++;
            }
            int j = 0;
            while (!OutOfMemoryError.class.isInstance(lecArchivo) || !lecArchivo.hasNext() && arc2.get(j) != null) {
                lecArchivo.next() = arc2.get(j); //2DA MITAD DEL ARCHIVO
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

}
