public class Productor extends Thread
{
    private static BuzonReproceso buzonReproceso;
    private static BuzonRevision buzonRevision;

    private Producto producto;

    public void procesarProducto(Producto producto)
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
                //toca ver si al buzon le cabe el producto, tiene un limite, cual?
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
            //toca ver si al buzon le cabe el producto, tiene un limite, cual?
            buzonRevision.almacenar(producto);
        }
    }

    public static void inicializarMonitores(BuzonReproceso nBuzonReproceso, BuzonRevision nBuzonRevision)
    {
        buzonReproceso = nBuzonReproceso;
        buzonRevision = nBuzonRevision;
    }

    public void run()
    {
        while(buzonReproceso.darFaltanProductos())
        {
            producto = buzonReproceso.reprocesar();
            procesarProducto(producto);
        }
    }
}
