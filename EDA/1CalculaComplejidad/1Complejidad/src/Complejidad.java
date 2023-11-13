import java.util.ArrayList;

public class Complejidad {

    public static int calculaMin (int [] arr) { //COMPLEJIDAD n
        int n = 0;
        if (arr.length == 0) {
            System.out.println("La complejidad es de 0");;
        } 
        int min = arr[0];
        n+=1;
        for (int i = 1; i<arr.length;i++) {
            n += 1;
            if ( arr[i]<min) {
                min = arr[i];
            }
        }
        System.out.println("Los pasos que se hicieron fueron: "+n);
        return min;
    }
  
    public static ArrayList<Integer> interseccion (int[] arr1, int[] arr2) { // m*n
        int n = 0;
        ArrayList <Integer> resp = new ArrayList<Integer> ();
        for (int i = 0; i<arr1.length;i++) {
            for (int j =0 ; j<arr2.length;j++) {
                n+=1;
                if (arr1[i] == (arr2[j])) {
                    resp.add(arr1[i]);
                }
            }
        }
        System.out.println("Los pasos que se hicieron fueron: "+n);
        return resp;
    }

    public static void permuta ( String s, String s2) { //COMPLEJIDAD DE n!
        String resto; char c;
        if (s.length () == 0) {
            System.out.println(s2);
            return;
        }
        for (int i= 0; i< s.length(); i++) {
            c = s.charAt(i);
            resto = s.substring (0, i) + s.substring (i+1, s.length());
            permuta (resto, s2 + c);
        }
    }

    public static int distancia (String s1, String s2) { //COMPLEJIDAD m*n
        return distanciaR(s1,s2,s1.length(),s2.length());
    }
    private static int distanciaR (String s1, String s2, int a, int b) {
        if ( a == 0) {
            return b;
        }
        if ( b == 0) {
            return a;
        }
        if (s1.charAt(a-1) == s2.charAt(b-1)) {
            return distanciaR (s1,s2,a-1,b-1);

        }
        int inserta = distanciaR (s1,s2,a,b-1);
        int delete = distanciaR (s1,s2,a-1,b);
        int sus = distanciaR(s1, s2, a-1, b-1);

        return 1+ Math.min(Math.min(inserta, delete), sus);
    }

    


    public static void main(String[] args) throws Exception {
        int arr1 [] = {5,2,0,4,-1}; //5
        int arr2 [] = {100,1000,2,1100,-1, -7,8,55}; //8

        int arr3 [] = {5,2,0,4,-1,8,4}; //7
        int arr4 [] = {100,1000,2,1100,-1, -7,8,55}; //8

        int arr5 [] = {5,2,0,4,-1,8,4,2,-6}; //9
        int arr6 [] = {100,1000,2,1100,-1, -7,8,55}; //8

        int arr7 [] = {5,2,0,4,-1,8,4,48,61,10,0}; //11
        int arr8 [] = {100,1000,2,1100,-1, -7,8,55}; //8

        int arr9 [] = {5,2,0,4,-1,8,4,4956,16846,1561,15615,1561,516}; //13
        int arr10 [] = {100,1000,2,1100,-1, -7,8,55}; //8

        int arr11 [] = {5,2,0,4,-1,8,4,0,1,2,3,4,5,6,8}; //15
        int arr12 [] = {100,1000,2,1100,-1, -7,8,55}; //8



        System.out.println("El minimo de arr1 es: "+calculaMin(arr1)+"\n");
        System.out.println("El minimo de arr2 es: "+calculaMin(arr2)+"\n");
        System.out.println("La interseccoin es: "+ interseccion(arr1, arr2)+"\n");
        System.out.println("La interseccoin es: "+ interseccion(arr3, arr4)+"\n");
        System.out.println("La interseccoin es: "+ interseccion(arr5, arr6)+"\n");
        System.out.println("La interseccoin es: "+ interseccion(arr7, arr8)+"\n");
        System.out.println("La interseccoin es: "+ interseccion(arr9, arr10)+"\n");
        System.out.println("La interseccoin es: "+ interseccion(arr11, arr12)+"\n");

        permuta("hola", ""+"\n");
        System.out.println(distancia("Fernando", "nando")+"\n");
        System.out.println(distancia("Fernando", "ando")+"\n");
        System.out.println(distancia("Fernando", "ndo")+"\n");
        System.out.println(distancia("Fernando", "do")+"\n");
    }
}
