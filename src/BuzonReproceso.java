import java.util.ArrayList;

public class BuzonReproceso 
{
    private ArrayList<Producto> productos;
    private boolean faltanProductos = true;
    private int contadorId = 0;

    public synchronized void agregar(Producto producto)
    {

    }

    public synchronized Producto reprocesar()
    {
        if (productos.size()==0)
        {
            return null;
        }
        else
        {
            Producto nProducto = productos.remove(0);
            if (nProducto.darMensaje() != null)
            {
                faltanProductos = false;
            }
        return nProducto;
        }
    }

    public synchronized boolean darFaltanProductos()
    {
        return faltanProductos;
    }

    public synchronized int darContadorId()
    {
        int id = contadorId;
        contadorId++;
        return id;
    }
}
