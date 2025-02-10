import java.util.ArrayList;

public class BuzonRevision 
{
    private int limite;
    private ArrayList<Producto> productos;

    public synchronized void almacenar(Producto producto)
    {
        while(productos.size()==limite)
        {
            try
            {
                wait();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        productos.add(producto);
        //notifyAll(); ??
    }

    public synchronized Producto sacar()
    {
        while (productos.size() == 0) {
            try {
                wait();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        Producto producto = productos.remove(0);
        //notifyAll(); ??
        return producto;
    }
}