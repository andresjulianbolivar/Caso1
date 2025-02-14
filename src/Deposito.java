import java.util.ArrayList;

public class Deposito 
{
    private ArrayList<Producto> productos = new ArrayList<>();

    public synchronized void entregar(Producto producto){
        productos.add(producto);
        notify();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

}
