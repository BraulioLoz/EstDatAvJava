
public class LinkedBianarySearchTree <T extends Comparable <T>> extends LinkedBianaryTree <T> implements BinarySearchTreeADT <T> {


    private NodoBin<T> busca(T elem) {
        NodoBin<T> actual = raiz;
        boolean encontre = false;
        while (encontre && actual != null) {
            if (actual.getElem().equals(elem)) {
                encontre = true;
            } else if (actual.getElem().compareTo(elem) > 0) {
                actual = actual.getIzq();
            } else {
                actual = actual.getDer();
            }
        }
        return actual;
    }

    @Override
    public void inserta(T elem) {
        NodoBin<T> nuevo = new NodoBin<T>(elem);
        if (raiz == null) {
            raiz = nuevo;
            cont++;
            return;
        }
        NodoBin<T> actual = raiz;
        while (actual != null) {
            if (actual.getElem().compareTo(elem) > 0) {
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
    public void borra(T elem) {
        NodoBin<T> actual = busca (elem);
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

    @Override
    public T findMin() {
        NodoBin<T> actual = raiz;
        while (actual.getIzq() != null) {
            actual = actual.getIzq();
        }
        return actual.getElem();
    }

    @Override
    public T findMax() {
        NodoBin<T> actual = raiz;
        while (actual.getDer() != null) {
            actual = actual.getDer();
        }
        return actual.getElem();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

}