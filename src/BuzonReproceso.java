import java.util.ArrayList;

public class BuzonReproceso 
{
    private ArrayList<Producto> productos;
    private boolean faltanProductos = true;

    public synchronized void agregar(Producto producto)
    {

    }

    public synchronized Producto reprocesar()
    {
        Producto nProducto = productos.remove(0);
        if (nProducto != null)
        {
            if (nProducto.darMensaje() != null)
            {
                faltanProductos = false;
            }
        }
        return nProducto;
    }

    public synchronized boolean darFaltanProductos()
    {
        return faltanProductos;
    }
}
