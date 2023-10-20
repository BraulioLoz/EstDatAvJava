import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LinkedBinaryAVLTree <T extends Comparable <T>> extends LinkedBianaryTree <T> implements BinaryAVLTreeADT <T> {
    NodoAVL <T> raiz;

    public LinkedBinaryAVLTree () {
        raiz = null;
    }
    
    public NodoAVL<T> busca (T elem) { //DAMOS A BUSCAR UN ELEMENTO Y NOS REGRESA EL NODO QUE LO CONTIENE O SI NO REGRESA NULL
        if (raiz.getElem() == elem) { //COMPARAMOS CON LA RAIZ
            return raiz;
        }
        NodoAVL<T> aux = raiz;
        while (aux != null) { //RECORREMOS EL ARBOL
            if (elem.equals(aux.getElem())) { //COMPARAMOS EL ELEMENTO CON EL ELEMENTO DEL NODO
                return aux; //SI ES IGUAL REGRESAMOS EL NODO
            }
            if (elem.compareTo(aux.elem)<0) { //SI ES MÁS CHICO VAMOS A LA IZQUIERDA
                aux = (NodoAVL<T>) aux.getIzq();
            }
            if (elem.compareTo(aux.elem)>0) { //SI ES MÁS GRANDE VAMOS A LA DERECHA
                aux = (NodoAVL<T>) aux.getDer();
            }
        }
        return null; //SI NO LO ENCUENTRA REGRESA NULL
    }

    public NodoAVL <T> rota (NodoAVL <T> n) { //RECIBE UN NODO Y LO ROTAMOS
        NodoAVL<T> papa,alfa,beta,D,gamma,C,A,B;
        if (n.fe == -2 && ((NodoAVL<T>) n.getIzq()).getFe() == -1) { //CASO IZQUIERDA IZQUIERDA
            papa = (NodoAVL<T>) n.papa; //POR SI HAY PAPÁ
            alfa = n; 
            beta = (NodoAVL<T>) n.izq;
            D = (NodoAVL<T>) n.der;
            gamma = (NodoAVL<T>) beta.izq;
            C = (NodoAVL<T>) beta.der;
            A= (NodoAVL<T>) gamma.izq;
            B = (NodoAVL<T>) gamma.der;
            alfa.Cuelga2(C, 'i'); //CUELGA C A LA IZQUIERDA DE ALFA
            alfa.Cuelga2(D, 'd'); //CUELGA D A LA DERECHA DE ALFA
            beta.Cuelga2(alfa, 'd'); //CUELGA ALFA A LA DERECHA DE BETA
            beta.Cuelga2(gamma, 'i'); //CUELGA GAMMA A LA IZQUIERDA DE BETA
            if (papa == null) { //SI NO HAY PAPÁ ENTONCES ES LA RAIZ Y LA ACTUALIZAMOS
                raiz = beta;
                raiz.setPadre(null); //PONEMOS EL PAPÁ DE LA NUEVA RAÍZ COMO NULL
            } else {
                if (papa.izq == alfa) { //SI HAY PAPÁ Y EL NODO ES EL HIJO IZQUIERDO DEL PAPÁ ENTONCES CUELGA BETA A LA IZQUIERDA DEL PAPÁ
                    papa.Cuelga2(beta, 'i');
                } else {
                    papa.Cuelga2(beta, 'd'); //SI NO CUELGA BETA A LA DERECHA DEL PAPÁ
                }
            }
            actualizaFe(); //ACTUALIZAMOS EL FE DE TODOS LOS NODOS
            return beta;
        }   
        if (n.fe == 2 && ((NodoAVL<T>) n.getDer()).getFe() == 1) { //CASO DERECHA DERECHA
            papa = (NodoAVL<T>) n.papa; //POR SI HAY PAPÁ
            alfa = n;
            A = (NodoAVL<T>) n.izq;
            beta = (NodoAVL<T>) n.der;
            B = (NodoAVL<T>) beta.izq;
            gamma = (NodoAVL<T>) beta.der;
            C = (NodoAVL<T>) gamma.izq;
            D = (NodoAVL<T>) gamma.der;
            alfa.Cuelga2(A, 'i');
            alfa.Cuelga2(B, 'd');
            beta.Cuelga2(alfa, 'i');
            beta.Cuelga2(gamma, 'd');
            if (papa == null) { //SI NO HAY PAPÁ ENTONCES ES LA RAIZ Y LA ACTUALIZAMOS
                raiz = beta;
                raiz.setPadre(null); //PONEMOS EL PAPÁ DE LA NUEVA RAÍZ COMO NULL
            } else {
                if (papa.izq == alfa) { //SI HAY PAPÁ Y EL NODO ES EL HIJO IZQUIERDO DEL PAPÁ ENTONCES CUELGA BETA A LA IZQUIERDA DEL PAPÁ
                    papa.Cuelga2(beta, 'i');
                } else {
                    papa.Cuelga2(beta, 'd'); //SI NO CUELGA BETA A LA DERECHA DEL PAPÁ
                }
            }
            actualizaFe(); //ACTUALIZAMOS EL FE DE TODOS LOS NODOS
            return beta;
        }
        if (n.fe == -2 && ((NodoAVL<T>) n.getIzq()).getFe() == 1) { //CASO IZQUIERDA DERECHA
            papa = (NodoAVL<T>) n.papa; 
            alfa = n;
            beta = (NodoAVL<T>) n.izq;
            D = (NodoAVL<T>) n.der;
            A = (NodoAVL<T>) beta.izq;
            gamma = (NodoAVL<T>) beta.der;
            B = (NodoAVL<T>) gamma.izq;
            C = (NodoAVL<T>) gamma.der;
            alfa.Cuelga2(C, 'i');
            beta.Cuelga2(B, 'd');
            gamma.Cuelga2(alfa, 'd');
            gamma.Cuelga2(beta, 'i');
            if (papa == null) { //SI NO HAY PAPÁ ENTONCES ES LA RAIZ Y LA ACTUALIZAMOS 
                raiz = gamma;
                raiz.setPadre(null);
            } else {
                if (papa.izq == alfa) {
                    papa.Cuelga2(gamma, 'i');
                } else {
                    papa.Cuelga2(gamma, 'd');
                }
            }
            actualizaFe(); //ACTUALIZAMOS EL FE DE TODOS LOS NODOS
            return gamma;
        }
        if (n.fe == 2 && ((NodoAVL<T>) n.getDer()).getFe() == -1) { //CASO DERECHA IZQUIERDA
            papa = (NodoAVL<T>) n.papa;
            alfa = n;
            A = (NodoAVL<T>) n.izq;
            beta = (NodoAVL<T>) n.der;
            gamma = (NodoAVL<T>) beta.izq;
            D = (NodoAVL<T>) beta.der;
            B = (NodoAVL<T>) gamma.izq;
            C = (NodoAVL<T>) gamma.der;
            alfa.Cuelga2(B,'d');
            beta.Cuelga2(C,'i');
            gamma.Cuelga2(alfa, 'i');
            gamma.Cuelga2(beta, 'd');
            if (papa == null) {
                raiz = gamma;
                raiz.setPadre(null);
            } else {
                if (papa.izq == alfa) {
                    papa.Cuelga2(gamma, 'i');
                } else {
                    papa.Cuelga2(gamma, 'd');
                }
            }
            actualizaFe(); //ACTUALIZAMOS EL FE DE TODOS LOS NODOS
            return gamma;
        } 
        if (n.fe == 2 && ((NodoAVL<T>) n.getDer()).getFe() == 0 && ((NodoAVL<T>) n.getIzq()).getFe() == 0) { //CASO donde el nodo tiene fe 2 y sus hijos tienen fe 0
            papa = (NodoAVL<T>) n.papa;
            alfa = n;
            A = (NodoAVL<T>) n.izq;
            beta = (NodoAVL<T>) n.der;
            gamma = (NodoAVL<T>) beta.der;
            B = (NodoAVL<T>) beta.izq;
            C = (NodoAVL<T>) gamma.izq;
            D = (NodoAVL<T>) gamma.der;
            alfa.Cuelga2(B,'d');
            beta.Cuelga2(alfa,'i');
            //beta.Cuelga2(gamma,'d');
            if (papa == null) {
                raiz = beta;

            } else {
                if (papa.izq == alfa) {
                    papa.Cuelga2(beta, 'i');
                } else {
                    papa.Cuelga2(beta, 'd');
                }
            }
            actualizaFe();
            return gamma;
        }
        if (n.fe == -2 && ((NodoAVL<T>) n.getDer()).getFe() == 0 && ((NodoAVL<T>) n.getIzq()).getFe() == 0) { //CASO donde el nodo tiene fe -2 y sus hijos tienen fe 0
            papa = (NodoAVL<T>) n.papa;
            alfa = n;
            beta = (NodoAVL<T>) n.izq;
            D = (NodoAVL<T>) n.der;
            gamma = (NodoAVL<T>) beta.izq;
            C = (NodoAVL<T>) beta.der;
            A = (NodoAVL<T>) gamma.izq;
            B = (NodoAVL<T>) gamma.der;
            alfa.Cuelga2(C,'i');
            beta.Cuelga2(alfa,'d');
            beta.Cuelga2(gamma,'i');
            if (papa == null) {
                raiz = beta;
            } else {
                if (papa.izq == alfa) {
                    papa.Cuelga2(beta, 'i');
                } else {
                    papa.Cuelga2(beta, 'd');
                }
            }
            actualizaFe();
            return gamma;
        }
        if (quienTieneFe(raiz) != null) {
            return rota(quienTieneFe(raiz));
        }
        return null;
    }

    @Override
    public void inserta(T elem) {
        NodoAVL<T> nuevo = new NodoAVL<T>(elem); //CREAMOS UN NUEVO NODO
        if (raiz == null) {
            raiz = nuevo;
            cont++;
            return;
        }
        NodoAVL<T> actual = raiz;
        while (actual != null) { //RECORREMOS EL ARBOL
            if (elem.compareTo(actual.elem) <= 0) { //SI EL ELEMENTO ES MENOR O IGUAL AL ELEMENTO DEL NODO
                if (actual.getIzq() == null) { //SI EL HIJO IZQUIERDO ES NULO ENTONCES CUELGA EL NUEVO NODO
                    actual.Cuelga2(nuevo, 'i');
                    cont++;
                    actualizaFe(); //ACTUALIZAMOS EL FE DE TODOS LOS NODOS
                    if (actual.papa != null) { //SI EL PAPÁ NO ES NULO
                        if (((NodoAVL<T>)actual.papa).getFe() == -2) { //SI EL FE DEL PAPÁ ES -2 ENTONCES ROTAMOS
                                actual = rota(((NodoAVL<T>)actual.papa)); 
                                return; 
                        }
                        if (((NodoAVL<T>)actual.papa).getFe() == 2) { //SI EL FE DEL PAPÁ ES 2 ENTONCES ROTAMOS
                                actual = rota(((NodoAVL<T>)actual.papa)); 
                                return; 
                        }
                        if (actual.papa.papa != null) { //SI EL ABUELO NO ES NULO
                            if (((NodoAVL<T>)actual.papa.papa).getFe() == -2 || ((NodoAVL<T>)actual.papa.papa).getFe() == 2) { //Verificamos si el abuelo es nullo
                                actual = rota(((NodoAVL<T>)actual.papa.papa)); 
                                return; 
                            }
                        }
                    } 
                    return;
                }else { 
                    actual = (NodoAVL<T>) actual.izq; //SI NO ES NULO ENTONCES AVANZAMOS
                } 
            }else { //SI EL ELEMENTO ES MAYOR AL ELEMENTO DEL NODO
                if (actual.getDer() == null) { //SI EL HIJO DERECHO ES NULO ENTONCES CUELGA EL NUEVO NODO
                    actual.Cuelga2(nuevo, 'd');
                    cont++;
                    actualizaFe(); //ACTUALIZAMOS EL FE DE TODOS LOS NODOS
                    if (actual.papa != null) { //SI EL PAPÁ NO ES NULO
                        if (((NodoAVL<T>)actual.papa).getFe() == -2) { 
                                actual = rota(((NodoAVL<T>)actual.papa)); 
                                return; 
                        }
                        if (((NodoAVL<T>)actual.papa).getFe() == 2) { 
                                actual = rota(((NodoAVL<T>)actual.papa)); 
                                return; 
                        }
                        if (actual.papa.papa != null) { //SI EL ABUELO NO ES NULO
                            if (((NodoAVL<T>)actual.papa.papa).getFe() == 2 ||((NodoAVL<T>)actual.papa.papa).getFe() == -2 ) {
                                actual = rota(((NodoAVL<T>)actual.papa.papa));
                                return;
                            }
                        }
                    }
                    return;
                }else {
                    actual = (NodoAVL<T>) actual.der; //SI NO ES NULO ENTONCES AVANZAMOS
                }
            }
        }
    }

    @Override
    public void borra(T elem) { 
        NodoAVL<T> ele = busca(elem); //BUSCAMOS EL ELEMENTO
        if (ele == null) { //SI NO LO ENCUENTRA NO HACE NADA
            return;
        }
        NodoAVL<T> papa = (NodoAVL<T>) ele.papa; //BUSCAMOS EL PAPÁ
        NodoAVL<T> aux, quienFe;
        if (ele.izq == null && ele.der == null) { //SI EL NODO NO TIENE HIJOS
            if (papa == null) {
                raiz = null;
                cont--;
                return;
            }
            if (papa.izq == ele) { //SI EL NODO ES EL HIJO IZQUIERDO DEL PAPÁ
                papa.izq = null;
                cont--;
                actualizaFe();
                quienFe = quienTieneFe(raiz); //BUSCAMOS SI ALGUIEN TIENE FE 2 O -2
                if (quienFe != null) {
                    quienFe = rota(quienFe); //SI LO HAY ROTAMOS
                    return;
                }
                return;
            } else { //SI EL NODO ES EL HIJO DERECHO DEL PAPÁ  
                papa.der = null;
                cont--;
                actualizaFe();
                quienFe = quienTieneFe(raiz);
                if (quienFe != null) {
                    quienFe = rota(quienFe);
                    return;
                }
                return;
            }
        } 
        if (ele.izq == null && ele.der != null) { //SI EL NODO TIENE UN HIJO DERECHO
            if (papa == null) { //SI EL NODO ES LA RAIZ
                raiz = (NodoAVL<T>) ele.der;
                cont--;
                return;
            }
            if (papa.izq == ele) { //SI EL NODO ES EL HIJO IZQUIERDO DEL PAPÁ
                papa.Cuelga2((NodoAVL<T>)ele.der, 'i');
                cont--;
                actualizaFe();
                quienFe = quienTieneFe(raiz); //BUSCAMOS SI ALGUIEN TIENE FE 2 O -2
                if (quienFe != null) { 
                    quienFe = rota(quienFe); //SI LO HAY ROTAMOS
                    return;
                }
                return;
            } else {
                papa.Cuelga2((NodoAVL<T>)ele.der, 'd'); //SI EL NODO ES EL HIJO DERECHO DEL PAPÁ
                cont--;
                actualizaFe();
                quienFe = quienTieneFe(raiz);
                if (quienFe != null) {
                    quienFe = rota(quienFe);
                    return;
                }
                return;
            }
        }
        if (ele.izq != null && ele.der == null) { //SI EL NODO TIENE UN HIJO IZQUIERDO
            if (papa == null) { //SI EL NODO ES LA RAIZ
                raiz = (NodoAVL<T>) ele.izq;
                cont--;
                return;
            }
            if (papa.izq == ele) { //SI EL NODO ES EL HIJO IZQUIERDO DEL PAPÁ
                papa.Cuelga2((NodoAVL<T>)ele.izq, 'i');
                cont--;
                actualizaFe();
                quienFe = quienTieneFe(raiz); //BUSCAMOS SI ALGUIEN TIENE FE 2 O -2
                if (quienFe != null) {
                    quienFe = rota(quienFe); //SI LO HAY ROTAMOS
                    return;
                }
                return;
            } else {
                papa.Cuelga2((NodoAVL<T>)ele.izq, 'd'); //SI EL NODO ES EL HIJO DERECHO DEL PAPÁ
                cont--;
                actualizaFe();
                quienFe = quienTieneFe(raiz);
                if (quienFe != null) { //BUSCAMOS SI ALGUIEN TIENE FE 2 O -2
                    quienFe = rota(quienFe); 
                    return;
                }
                return;
            }
        } 
        if (ele.izq != null && ele.der != null) { //SI EL NODO TIENE DOS HIJOS
            aux = (NodoAVL<T>) ele.izq; //BUSCAMOS EL NODO MÁS GRANDE DEL SUBÁRBOL IZQUIERDO
            if (aux.der == null) { //SI EL NODO MÁS GRANDE DEL SUBÁRBOL IZQUIERDO NO TIENE HIJO DERECHO
                ele.elem = aux.elem; //CAMBIAMOS EL ELEMENTO DEL NODO A BORRAR POR EL ELEMENTO DEL NODO MÁS GRANDE DEL SUBÁRBOL IZQUIERDO
                ele.izq = aux.izq; //PONEMOS A LA IZQUIERDA DEL ELEMENTO (YA CON EL ELEMENTE DEL NODO MÁS GRANDE DEL SUBÁRBOL IZQUIERDO) EL HIJO IZQUIERDO DE SU ANTECESOR INORDEN
                if (aux.izq != null) {
                    aux.izq.papa = ele;
                }
                cont--;
                actualizaFe(); //ACTUALIZAMOS EL FE DE TODOS LOS NODOS
                quienFe = quienTieneFe(raiz); //BUSCAMOS SI ALGUIEN TIENE FE 2 O -2
                if (quienFe != null) {
                    quienFe = rota(quienFe);
                    return;
                }
                return;
            }
            while (aux.der != null) { //SI EL NODO MÁS GRANDE DEL SUBÁRBOL IZQUIERDO TIENE HIJO DERECHO
                aux = (NodoAVL<T>) aux.der;
            }
            ele.elem = aux.elem; //CAMBIAMOS EL ELEMENTO DEL NODO A BORRAR POR EL ANTECESOR DEL ELE INORDEN
            papa = (NodoAVL<T>) aux.papa;
            papa.der = aux.izq;
            if (aux.izq != null) { //SI EL ANTECESOR DEL ELE INORDEN TIENE HIJO IZQUIERDO LE ASIGNAMOS A SU PAPA SU ÚNICO HIJO
                aux.izq.papa = papa;
            }
            cont--;
            actualizaFe(); //ACTUALIZAMOS EL FE DE TODOS LOS NODOS
            quienFe = quienTieneFe(raiz); //BUSCAMOS SI ALGUIEN TIENE FE 2 O -2
            if (quienFe != null) {
                quienFe = rota(quienFe); //SI LO HAY ROTAMOS
                return;
            }
            return;
        }
    }

    public NodoAVL<T> quienTieneFe(NodoAVL<T> raiz) { //MÉTODO AUXILIAR QUE NOS PERMITIRÁ VERIFICAR EN TODO EL ÁRBOL SI ALGÚN NODO TIENE FE 2 O -2
        NodoAVL <T> izq, der;
        izq = quienTieneFeI(raiz); //BUSCAMOS EN EL SUBÁRBOL IZQUIERDO
        der = quienTieneFeD(raiz); //BUSCAMOS EN EL SUBÁRBOL DERECHO
        if (izq != null) {
            return izq;
        } else {
            if (der != null) {
                return der;
            }
        }
        return null;
    }

    private NodoAVL<T> quienTieneFeI(NodoAVL<T> actual) { //MÉTODO AUXILIAR QUE BUSCARÁ EN EL SUBÁRBOL IZQUIERDO 
        if (actual == null) {
            return null;
        }
        if (actual.getFe() == 2 || actual.getFe() == -2) {
            return actual;
        }
        return quienTieneFe((NodoAVL<T>) actual.getIzq()); //SI NO ENCUENTRA CON EL NODO DADO, REGRESA EL HIJO IZQUIERDO PARA BUSCAR EN SU SUBAROL IZQ Y DER.

    }

    private NodoAVL<T> quienTieneFeD(NodoAVL<T> actual) { //MÉTODO AUXILIAR QUE BUSCARÁ EN EL SUBÁRBOL DERECHO
        if (actual == null) {
            return null;
        }
        if (actual.getFe() == 2 || actual.getFe() == -2) {
            return actual;
        }
        return quienTieneFe((NodoAVL<T>) actual.getDer()); //SI NO ENCUENTRA CON EL NODO DADO, REGRESA EL HIJO DERECHO PARA BUSCAR EN SU SUBAROL IZQ Y DER.
    }

    public String porNivel() { //MÉTODO QUE IMPRIME EL ÁRBOL POR NIVEL
        ArrayList<String> lista = new ArrayList<String>(); //RESPUESTA, LISTA QUE VA A CONTENER LOS DATOS DE LOS NODOS Y SU FE GUARDADOS POR NIVEL 
        Queue<NodoAVL<T>> cola = new LinkedList<NodoAVL<T>>(); 
        NodoAVL<T> actual = raiz; 
        cola.add(raiz); //AGREGAMOS LA RAIZ A LA COLA
        while (cola.size() != 0) { //MIENTRAS LA COLA NO ESTÉ VACÍA
            actual = cola.remove(); 
            /* lista.add("Elemento: " + actual.elem.toString() +" fe: "+ actual.fe + "\n"); */
            lista.add ("( " + actual.elem.toString() + ", " + actual.fe + " )"); //AGREGAMOS COMO STRING AL ELEMENTO Y A SU FE
            if (actual.izq != null) {
                cola.add((NodoAVL<T>) actual.izq); 
            }
            if (actual.der != null) {
                cola.add((NodoAVL<T>) actual.der);
            }
        }
        return lista.toString();
    }


    public int altura() { //METODO QUE SIMPLEMENTE MANDA A LLAMAR AL METODO alturaR CON LA RAIZ PARA SABER LA ALTURA COMPLETA DEL ARBOL ENTERO
        return alturaR(raiz);
    }

    private int alturaR(NodoAVL<T> actual) { //MÉTODO QUE NOS PERMITE SABER LA ALTURA DE UN NODO
        if (actual == null) { //SI EL NODO ES NULO REGRESA 0
            return 0;
        }
        int izq = alturaR((NodoAVL<T>) actual.getIzq()); //SI NO ES NULO BUSCA LA ALTURA DEL HIJO IZQUIERDO
        int der = alturaR((NodoAVL<T>) actual.getDer()); //SI NO ES NULO BUSCA LA ALTURA DEL HIJO DERECHO
        return Math.max(izq, der) + 1; //REGRESA EL MÁXIMO DE LA ALTURA DEL HIJO IZQUIERDO Y DERECHO MÁS 1
    }

    public int balance(NodoAVL<T> n) { //MÉTODO QUE NOS PERMITE SABER EL BALANCE DE UN NODO
        if (n == null) {
            return 0;
        }
        int izq = alturaR((NodoAVL<T>) n.getIzq()); //ALTURA DEL HIJO IZQUIERDO 
        int der = alturaR((NodoAVL<T>) n.getDer()); //ALTURA DEL HIJO DERECHO
        return der - izq; //REGRESA LA RESTA DE LA ALTURA DEL HIJO DERECHO MENOS LA ALTURA DEL HIJO IZQUIERDO
    }

    public void actualizaFe(NodoAVL<T> n) { //MÉTODO QUE NOS PERMITE ACTUALIZAR EL FE DE UN NODO DADO Y SUS HIJOS 
        NodoAVL<T> actual = n;
        if (actual == null) {
            return;
        }
        actual.setFe(balance(actual)); //ACTUALIZAMOS EL FE DEL NODO
        actualizaFe((NodoAVL<T>) actual.getIzq()); //ACTUALIZAMOS EL FE DEL HIJO IZQUIERDO DE FORMA RECURSIVA 
        actualizaFe((NodoAVL<T>) actual.getDer()); //ACTUALIZAMOS EL FE DEL HIJO DERECHO DE FORMA RECURSIVA
    }

    public void actualizaFe() { //MÉTODO QUE NOS PERMITE ACTUALIZAR EL FE DE TODOS LOS NODOS 
        actualizaFe(raiz);
    }


    public void printTree() {
        if (raiz == null) {
            System.out.println("The tree is empty.");
            return;
        }
    
        int treeHeight = altura();
        int maxWidth = (int) (Math.pow(2, treeHeight + 1)) - 1;
    
        ArrayList<ArrayList<String>> treeLevels = new ArrayList<>();
        Queue<NodoAVL<T>> queue = new LinkedList<>();
        queue.add(raiz);
    
        for (int level = 0; level <= treeHeight; level++) {
            int levelSize = (int) Math.pow(2, level);
            int padding = maxWidth / (levelSize * 2);
            ArrayList<String> levelNodes = new ArrayList<>();
    
            for (int i = 0; i < levelSize; i++) {
                if (!queue.isEmpty()) {
                    NodoAVL<T> node = queue.poll();
                    if (node == null) {
                        levelNodes.add(" ");
                        queue.add(null);
                        queue.add(null);
                    } else {
                        levelNodes.add(node.elem.toString());
                        queue.add((NodoAVL<T>) node.izq);
                        queue.add((NodoAVL<T>) node.der);
                    }
                } else {
                    levelNodes.add(" ");
                    queue.add(null);
                    queue.add(null);
                }
    
                for (int j = 0; j < padding; j++) {
                    levelNodes.add(" ");
                }
            }
            treeLevels.add(levelNodes);
        }
    
        // Print the tree
        for (ArrayList<String> levelNodes : treeLevels) {
            for (String node : levelNodes) {
                System.out.print(node);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        LinkedBinaryAVLTree<Integer> clase = new LinkedBinaryAVLTree<Integer>();

        clase.inserta(100);
        //System.out.println("Árbol despues de insertar100: \n" + clase.porNivel());
        clase.inserta(300);
        //System.out.println("Árbol despues de insertar300: \n" + clase.porNivel());
        clase.inserta(400);
        //System.out.println("Árbol despues de insertar400: \n" + clase.porNivel());
        clase.inserta(50);
        //System.out.println("Árbol despues de insertar50: \n" + clase.porNivel());
        clase.inserta(200);
        //System.out.println("Árbol despues de insertar200: \n" + clase.porNivel());
        clase.inserta(250);
        //System.out.println("Árbol despues de insertar250: \n" + clase.porNivel());
        clase.inserta(75);
        //System.out.println("Árbol despues de insertar75: \n" + clase.porNivel());
        clase.inserta(350);
        //System.out.println("Árbol despues de insertar350: \n" + clase.porNivel());
        clase.inserta(500);
        //System.out.println("Árbol despues de insertar500: \n" + clase.porNivel());
        clase.inserta(375);
        //System.out.println("Árbol despues de insertar375: \n" + clase.porNivel());
        
        //System.out.println("Árbol despues de insertar: \n" + clase.porNivel());
        System.out.println("Imprimir: \n");
        clase.printTree();

        clase.borra(500);
        //System.out.println("Árbol despues de borrar500: \n" + clase.porNivel());
        clase.borra(400);
        //System.out.println("Árbol despues de borrar400: \n" + clase.porNivel());
        clase.borra(200);
        //System.out.println("Árbol despues de borrar200: \n" + clase.porNivel());

        //System.out.println("Árbol despues de borrar: \n" + clase.porNivel());
        System.out.println("Imprimir: \n");
        clase.printTree();

        System.out.println("Imprimir: \n");
        clase.printTree();


        /* LinkedBinaryAVLTree<Integer> arbol = new LinkedBinaryAVLTree<Integer>();
        arbol.inserta(10);
        arbol.inserta(5);
        arbol.inserta(15);
        arbol.inserta(3);
        arbol.inserta(7);
        arbol.inserta(1);
        arbol.inserta(4);
        arbol.inserta(6);
        arbol.inserta(8);
        arbol.inserta(20);
        arbol.inserta(17);

        System.out.println(arbol.porNivel());

        arbol.borra(17);
        System.out.println("Despues de quitar 17 \n" + arbol.porNivel());
        arbol.borra(3);
        System.out.println("Despues de quitar 3 \n" + arbol.porNivel());
        arbol.borra(4);
        System.out.println("Despues de quitar 4  \n" + arbol.porNivel());

        arbol.borra(10);
        System.out.println("Despues de quitar 10 \n" + arbol.porNivel());
        arbol.borra(8);
        System.out.println("Despues de quitar 8 \n" + arbol.porNivel());
        arbol.borra(7);
        System.out.println("Despues de quitar 7 \n" + arbol.porNivel());

        arbol.borra (1050320);
        System.out.println("Despues de quitar 1050320 \n" + arbol.porNivel());

        /* NodoAVL<Integer> aux = arbol.quienTieneFe(arbol.raiz);
        if (aux != null) {
            System.out.println(aux.elem + ", Su fe es: " + aux.fe + ", Aux.der.fe es: " + aux.der.fe + ", Aux.izq.fe es: " + aux.izq.fe);
        } else {
            System.out.println("No hay nodos con fe 2 o -2");
        } */

        /* arbol.inserta(9);
        System.out.println("Despues de insertar 9 " + arbol.porNivel()); */
    }   
}