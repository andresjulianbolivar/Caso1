public class Inspector extends Thread
{
    private static BuzonReproceso buzonReproceso;
    private static BuzonRevision buzonRevision;
    private static Deposito deposito;

    private static int pedido;
    private int productosAprobados;
    private int productosRechazados;
    private Producto producto;

    public void revisarProducto(Producto producto)
    {

    }

    public static void inicializarMonitores(BuzonReproceso nBuzonReproceso, BuzonRevision nBuzonRevision, Deposito nDeposito)
    {
        buzonReproceso = nBuzonReproceso;
        buzonRevision = nBuzonRevision;
        deposito = nDeposito;
    }

    public void run()
    {
        
    }
}
