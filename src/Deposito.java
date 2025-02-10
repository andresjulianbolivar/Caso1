import java.util.ArrayList;

public class Deposito 
{
    private ArrayList<Producto> productos;

    public synchronized void entregar(Producto producto)
    {
        
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

}
