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
    }

    public synchronized Producto sacar()
    {
        return null;
    }
}
