import java.util.ArrayList;

public class Deposito 
{
    private ArrayList<Producto> productos = new ArrayList<>();

    public synchronized void entregar(Producto producto){
        productos.add(producto);
        notify();
    }
}
