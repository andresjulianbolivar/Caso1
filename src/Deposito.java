import java.util.ArrayList;

public class Deposito 
{
    private ArrayList<Producto> productos = new ArrayList<>();

    public synchronized void entregar(Producto producto){
        productos.add(producto);
        System.out.println("Producto "+producto.darId()+" entra al depósito.");
        notify();
    }

    public synchronized int getProductos() {
        return productos.size();
    }

}
