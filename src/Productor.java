public class Productor extends Thread
{
    private static BuzonReproceso buzonReproceso;
    private static BuzonRevision buzonRevision;

    private Producto producto;

    public void procesarProducto(Producto producto)
    {

    }

    public static void inicializarMonitores(BuzonReproceso nBuzonReproceso, BuzonRevision nBuzonRevision)
    {
        buzonReproceso = nBuzonReproceso;
        buzonRevision = nBuzonRevision;
    }

    public void run()
    {

    }
}
