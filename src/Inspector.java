import java.util.ArrayList;

public class Inspector extends Thread
{
    private static BuzonReproceso buzonReproceso;
    private static BuzonRevision buzonRevision;
    private static Deposito deposito;

    private static int numProductosAProducir;
    private int productosAprobados;
    private int productosRechazados;
    private Producto producto;

    public static void setStatics(int nPedido, BuzonReproceso reproceso, BuzonRevision revision, Deposito nDeposito) {
        pedido = nPedido;
        buzonReproceso = reproceso;
        buzonRevision = revision;
        deposito = nDeposito;
    }

    public void revisarProducto(Producto producto)
    {
        int aleatorio = (int) (Math.random() * 100);
        if (aleatorio % 7 == 0) {
            if (productosRechazados < Math.floor(numProductosAProducir * 0.1)) {
                buzonReproceso.agregar(producto);
                productosRechazados++;
            }
            else {
                ArrayList<Producto> prodsDeposito = deposito.getProductos();
                prodsDeposito.add(producto);
                deposito.setProductos(prodsDeposito);
                productosAprobados++;
            }
        }
        else {
            ArrayList<Producto> prodsDeposito = deposito.getProductos();
            prodsDeposito.add(producto);
            deposito.setProductos(prodsDeposito);
            productosAprobados++;
        }

    }

    public void consultarBuzonRevision()
    {
        Producto producto = buzonRevision.sacar();
        if (producto != null) {
            revisarProducto(producto);
        }
    }

    public static void inicializarMonitores(BuzonReproceso nBuzonReproceso, BuzonRevision nBuzonRevision, Deposito nDeposito)
    {
        buzonReproceso = nBuzonReproceso;
        buzonRevision = nBuzonRevision;
        deposito = nDeposito;
    }

    public void run()
    {
        while (productosAprobados != numProductosAProducir) { //variable FIN? pendiente
            consultarBuzonRevision();
            try {
            Thread.sleep(1000); //??
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
