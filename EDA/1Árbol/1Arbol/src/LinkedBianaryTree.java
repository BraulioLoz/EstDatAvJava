
import java.util.*;

public class LinkedBianaryTree <T extends Comparable <T>> implements BianaryTreeADT<T> {
    NodoBin <T> raiz;
    int cont;

    public LinkedBianaryTree () {
        raiz = null;
        cont = 0;
    }

    @Override
    public boolean isEmpty() {
        return cont == 0;
    }

    @Override
    public int size() {
        return cont;
    }

    @Override
    public boolean find(T elemento) {
        NodoBin<T> a = findR(raiz, elemento);
        return a != null;

    }
    private NodoBin<T> findR (NodoBin<T> nodo, T elemento) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getElem().equals(elemento)) {
            return nodo;
        }
        NodoBin<T> resp = findR(nodo.izq, elemento);
        if (resp == null) {
            resp = findR (nodo.der, elemento);
        }
        return resp;
    }

    @Override
    public Iterator<T> preorden() {
        ArrayList <T> lista = new ArrayList<T>();
        preordenR(raiz, lista);
        return lista.iterator();
    }

    private void preordenR (NodoBin<T> actual, ArrayList<T> lista) {
        if (actual == null) {
            return;
        }
        lista.add(actual.getElem());
        preordenR(actual.getIzq(), lista);
        preordenR(actual.getDer(), lista);
    }

    public Iterator <T> preordenI () {
        Stack<NodoBin<T>> pila = new Stack<NodoBin<T>>();
        NodoBin<T> actual = raiz;
        ArrayList<T> lista = new ArrayList<T>();
        pila.push(raiz);
        while (pila.size() != 0) {
            while (actual != null) {
                actual = pila.pop();
                lista.add(actual.getElem());
                if (actual.getDer() != null) {
                    pila.push(actual.getDer());
                }
                if (actual.getIzq() != null) {
                    pila.push(actual.getIzq());
                }
            }
        }
        return lista.iterator();
    }
//CAMBIAR A ARRAYLIST
    public ArrayList<T> porNiV () {
        ArrayList <T> lista = new ArrayList<T>();
        Queue<NodoBin<T>> cola = new LinkedList<NodoBin<T>>();
        NodoBin<T> actual = raiz;
        cola.add(raiz);
        while (cola.size() != 0) {
            actual = cola.remove();
            lista.add(actual.getElem());
            if (actual.getIzq() != null) {
                cola.add(actual.getIzq());
            }
            if (actual.getDer() != null) {
                cola.add(actual.getDer());
            }
        }
        return lista;
    }

    
    public Iterator <T> inorden () {
        ArrayList <T> lista = new ArrayList<T>();
        inordenR(raiz, lista);
        return lista.iterator();
    }
    private void inordenR(NodoBin<T> actual, ArrayList<T> lista) {
        if (actual == null) {
            return;
        }
        inordenR(actual.getIzq(), lista);
        lista.add(actual.getElem());
        inordenR(actual.getDer(), lista);
    }


    public int calculaAltura () {
        return calculaAlturaR(raiz);
    }
    private int calculaAlturaR (NodoBin <T> actual) {
        if (actual == null) {
            return 0;
        }
        int izq = calculaAlturaR(actual.getIzq());
        int der = calculaAlturaR(actual.getDer());
        return Math.max(izq, der) + 1;
    }


    public void insert (T data) {
        NodoBin<T> nuevo = new NodoBin<T>(data);
        if (raiz == null) {
            raiz = nuevo;
            cont++;
        }
        NodoBin<T> actual = raiz;
        while (actual != null) {
            if (data.compareTo(actual.getElem()) <= 0) {
                if (actual.getIzq() == null) {
                    actual.izq = nuevo;
                    nuevo.papa = actual;
                    cont++;
                }else {
                    actual = actual.getIzq();
                }
            }else {
                if (actual.getDer() == null) {
                    actual.der = nuevo;
                    nuevo.papa = actual;
                    cont++;
                }else {
                    actual = actual.getDer();
                }
            }

        }

    }

    @Override
    public void delete(T data) {
        NodoBin<T> actual = busca(data);
        if (actual == null) {
            return;
        }
        if (actual.getIzq() == null && actual.getDer() == null) {
            if (actual == raiz) {
                raiz = null;
            } else {
                NodoBin<T> papa = actual.getPadre();
                if (papa.getElem().compareTo(actual.getElem()) <=0) {
                    papa.setDer(null);
                } else {
                    papa.setIzq(null);
                }
            }
            cont--;
        }
        if (actual.getIzq() == null || actual.getDer() == null) {
            if (actual == raiz) {
                if (actual.getIzq() == null) {
                    raiz = actual.getDer();
                } else {
                    raiz = actual.getIzq();
                }
            }
            NodoBin<T> papa = actual.getPadre();
            if (papa.getElem().compareTo(actual.getElem()) <=0) {
                if (actual.getIzq() == null) {
                    papa.setDer(actual.getDer());
                } else {
                    papa.setDer(actual.getIzq());
                }
            } else {
                if (actual.getIzq() == null) {
                    papa.setIzq(actual.getDer());
                } else {
                    papa.setIzq(actual.getIzq());
                }
            }
            cont--;
        }
        if (actual.getIzq() != null && actual.getDer() != null) {
            NodoBin<T> sucesor = actual.getDer();
            while (sucesor.getIzq() != null) {
                sucesor = sucesor.getIzq();
            }
            actual.setElem(sucesor.getElem());
            NodoBin<T> papa = sucesor.getPadre();
            if (sucesor.der == null) {
                papa.setIzq(null);
            } else {
                papa.cuelga(sucesor.getDer());
            }
            cont--;
        }
        
    }

    private NodoBin<T> busca(T data) {
        NodoBin<T> actual = raiz;
        while (actual != null) {
            if (data.equals(actual.getElem())) {
                return actual;
            }
            if (data.compareTo(actual.getElem()) < 0) {
                actual = actual.getIzq();
            } else {
                actual = actual.getDer();
            }
        }
        return null;
    }

    @Override
    public void deleteAll() {
        raiz = null;
        cont = 0;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    @Override
    public void deleteAll(T data) {
        NodoBin<T> actual = busca(data);
        if (actual == null) {
            return;
        } else {
            delete(data);
            deleteAll(data);
        }
    }

    @Override
    public void deleteAll(T data, int n) {
        NodoBin<T> actual = busca(data);
        if (actual == null) {
            return;
        } else {
            delete(data);
            if (n > 1) {
                deleteAll(data, n-1);
            }
        }
    }
}
