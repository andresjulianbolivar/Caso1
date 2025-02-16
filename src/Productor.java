import java.util.concurrent.CyclicBarrier;

public class Productor extends Thread
{
    private static BuzonReproceso buzonReproceso;
    private static BuzonRevision buzonRevision;
    private static CyclicBarrier barrera;

    private Producto producto;

    public void procesarProducto(Producto producto)
    {
        if (buzonReproceso.darFaltanProductos())
        {
            if (producto != null)
            {
                if (producto.darMensaje()==null)
                {
                    int tiempo = (int) (Math.random()*100+1);
                    try 
                    {
                        Thread.sleep(tiempo);
                    } 
                    catch (InterruptedException e) 
                    {
                        e.printStackTrace();
                    }
                    System.out.println("Producto "+producto.darId()+" reprocesado.");
                    buzonRevision.almacenar(producto);
                }
            }
            else
            {
                producto = new Producto(buzonReproceso.darContadorId());
                int tiempo = (int) (Math.random()*500+1);
                try 
                {
                    Thread.sleep(tiempo);
                } 
                catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
                System.out.println("Producto "+producto.darId()+" creado.");
                buzonRevision.almacenar(producto);
            }
        }
    }

    public static void inicializarMonitores(BuzonReproceso nBuzonReproceso, BuzonRevision nBuzonRevision, CyclicBarrier nBarrera)
    {
        buzonReproceso = nBuzonReproceso;
        buzonRevision = nBuzonRevision;
        barrera = nBarrera;
    }

    public void run()
    {
        while(buzonReproceso.darFaltanProductos())
        {
            producto = buzonReproceso.reprocesar();
            procesarProducto(producto);
        }
        try 
        {
            barrera.await();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (!buzonRevision.darTerminar())
        {
            buzonRevision.setTerminar(true);
        }
    }
}
