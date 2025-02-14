import java.util.ArrayList;

public class BuzonRevision 
{
    private int limite;
    private ArrayList<Producto> productos = new ArrayList<Producto>();
    private boolean terminar = false;

    public BuzonRevision(int limite) {
        this.limite = limite;
    }

    public synchronized boolean darTerminar()
    {
        return terminar;
    }

    public synchronized void setTerminar(boolean nTerminar)
    {
        terminar = nTerminar;
    }

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
        System.out.println("Producto "+producto.darId()+" almacenado en el buzón de revisión.");
    }

    public synchronized Producto sacar()
    {
        if (productos.size()==0)
        {
            return null;
        }
        else
        {
            Producto producto = productos.remove(0);
            System.out.println("Producto "+producto.darId()+" sacado del buzón de revisión.");
            notify();
            return producto;
        }
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    
}