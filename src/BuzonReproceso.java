import java.util.ArrayList;

public class BuzonReproceso 
{
    private ArrayList<Producto> productos;
    private boolean faltanProductos = true;
    private boolean fin = false;
    private int contadorId = 0;

    public synchronized void agregar(Producto producto)
    {
        productos.add(producto);
        System.out.println("Producto "+producto.darId()+" agregado al buzón de reproceso.");
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
            System.out.println("Producto "+nProducto.darId()+" sacado del buzón de reproceso.");
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

    public synchronized boolean darFin()
    {
        return fin;
    }

    public synchronized void setFin(boolean nFin)
    {
        fin = nFin;
    }
}
