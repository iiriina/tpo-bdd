package negocio;

public class Factura {

    private int nro;
    private Pedido pedido;

    public Factura(int nroFact, Pedido pedido){
        this.nro = nroFact;
        this.pedido = pedido;
    }

    //setters

    public void setNro(int n){
        this.nro = n;
    }

    public void setPedido(Pedido p){
        this.pedido = p;
    }

    //getters

    public int getNro(){
        return this.nro;
    }

    public Pedido getPedido(){
        return this.pedido;
    }

}