
public class NodeAVL {
    int dato,fe;//DECLARAMOS EL DATO Y EL FACTOR DE EQUILIBRIO
  
    NodeAVL hijoIzquierdo;
    NodeAVL hijoDerecho;
    
    public NodeAVL(int d){
        this.dato=d;
        this.fe=0;
        this.hijoIzquierdo=null;
        this.hijoDerecho=null;
    }
}
