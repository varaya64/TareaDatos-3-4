
public class Avl {
    private NodeAVL root;
    //CREAMOS CONSTRUCTOR
    public void AVL(){
        root=null;
    }
    public NodeAVL obtenerRaiz(){
        return root;
    }
    //BUSCAR NODO EN EL ARBOL
    public NodeAVL search(int d, NodeAVL r){
        if(root==null){//VERIFICAMOS QUE NUESTRO ARBOL TENGA ALGUN CONTENIDO
            return null;//SI NO ES ASI RETORNAMOS UN VALOR NULO
        }else if(r.dato==d){
            return r; //CUANDO ENCONTREMOS EL VALOR RETORNAMOS EL NODO
        }else if(r.dato<d){
            //SI NUESTRO VALOR DEL NODO ES MENOR QUE NUESTRO DATO RETORNAMOS NUESTRO NODO DERECHO
            return search(d,r.hijoDerecho);
        }
        else{
            //SI NO RETORNAMOS NUESTRO NODO IZQUIERDO
            return search(d,r.hijoIzquierdo);
        }

    }
    //METODO PARA OBTENER EL FACTOR DE EQUILIBRIO
    public int getHigh(NodeAVL x){
        if(x==null){
            return -1;
        }else{
            return x.fe;
        }
    }
    //ROTACION SIMPLE IZQUIERDA
    public NodeAVL rotLeft(NodeAVL c){
    	NodeAVL auxiliar=c.hijoIzquierdo;
        c.hijoIzquierdo=auxiliar.hijoDerecho;
        auxiliar.hijoDerecho=c;
        c.fe=Math.max(getHigh(c.hijoIzquierdo), getHigh(c.hijoDerecho))+1;
        auxiliar.fe=Math.max(getHigh(auxiliar.hijoIzquierdo), getHigh(auxiliar.hijoDerecho))+1;
        return auxiliar;
    }
    //ROTACION SIMPLE DERECHA
    public NodeAVL rotRigth(NodeAVL c){
    	NodeAVL aux = c.hijoDerecho;
        c.hijoDerecho = aux.hijoIzquierdo;
        aux.hijoIzquierdo=c;
        c.fe = Math.max(getHigh(c.hijoIzquierdo), getHigh(c.hijoDerecho))+1;
        aux.fe = Math.max(getHigh(aux.hijoIzquierdo), getHigh(aux.hijoDerecho))+1;
        return aux;
    }
    //ROTACION DOBLE A LA IZQUIERDA
    public NodeAVL doubleLeft(NodeAVL c){
    	NodeAVL temp;
        c.hijoIzquierdo = rotRigth(c.hijoIzquierdo);
        temp = rotRigth(c);
        return temp;
    }
    //ROTACION DOBLE A LA DERECHA
    public NodeAVL doubleRigth(NodeAVL c){
    	NodeAVL temp;
        c.hijoDerecho = rotLeft(c.hijoDerecho);
        temp = rotRigth(c);
        return temp;
    }
    //METODO PARA INSERTAR AVL
    public NodeAVL insertAVL(NodeAVL n,NodeAVL subAr){
    	NodeAVL newFat = subAr;
        if(n.dato < subAr.dato){
            if(subAr.hijoIzquierdo == null){
                subAr.hijoIzquierdo = n;
            }else{
                subAr.hijoIzquierdo = insertAVL(n,subAr.hijoIzquierdo);
                if((getHigh(subAr.hijoIzquierdo) - getHigh(subAr.hijoDerecho) == 2)){
                    if(n.dato<subAr.hijoIzquierdo.dato){
                        newFat = rotLeft(subAr);
                    }else{
                        newFat = doubleLeft(subAr);
                    }
                }
            }
        }else if(n.dato > subAr.dato){
            if(subAr.hijoDerecho == null){
                subAr.hijoDerecho = n;
            }else{
                subAr.hijoDerecho = insertAVL(n, subAr.hijoDerecho);
                if((getHigh(subAr.hijoDerecho) - getHigh(subAr.hijoIzquierdo)==2)){
                    if(n.dato > subAr.hijoDerecho.dato){
                        newFat = rotRigth(subAr);
                    }else{
                        newFat = doubleRigth(subAr);
                    }
                }
            }
        }else{
            System.out.println("Nodo Duplicado");
        }
        //ACTUALIZAR ALTURA
        if((subAr.hijoIzquierdo==null) && (subAr.hijoDerecho != null)){
            subAr.fe = subAr.hijoDerecho.fe + 1;
        }else if((subAr.hijoDerecho == null) && (subAr.hijoIzquierdo != null)){
            subAr.fe = subAr.hijoIzquierdo.fe + 1;
        }else{
            subAr.fe = Math.max(getHigh(subAr.hijoIzquierdo), getHigh(subAr.hijoDerecho)) + 1;
        }
        return newFat;
    }

    public void insert(int d){
    	NodeAVL n = new NodeAVL(d);
        if(root == null){
            root = n ;
        }else{
            root = insertAVL(n , root);
        }
    }

    public void preOrder(NodeAVL r){
        if(r!=null){
            System.out.print(r.dato+", ");
            preOrder(r.hijoIzquierdo);
            preOrder(r.hijoDerecho);
        }
    }

}

