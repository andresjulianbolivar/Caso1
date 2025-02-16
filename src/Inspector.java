public class Inspector extends Thread
{
    private static BuzonReproceso buzonReproceso;
    private static BuzonRevision buzonRevision;
    private static Deposito deposito;

    private static int pedido;
    private int productosRechazados;
    private Producto producto;
    private boolean fin=false;

    public static void setStatics(int nPedido, BuzonReproceso reproceso, BuzonRevision revision, Deposito nDeposito) {
        pedido = nPedido;
        buzonReproceso = reproceso;
        buzonRevision = revision;
        deposito = nDeposito;
    }

    public void revisarProducto(Producto producto)
    {
        if (deposito.getProductos()>=pedido)
        {
            if (!fin)
            {
                producto.setMensaje("Fin");
                buzonReproceso.agregar(producto);
                System.out.println("Se ha alcanzado el pedido.");
                fin = true;
            }
            else
            {
                deposito.entregar(producto);
            }
        }
        else
        {
            int aleatorio = (int) (Math.random() * 100)+1;
            if (aleatorio % 7 == 0) {
                if (productosRechazados < Math.floor(pedido * 0.1)) {
                    buzonReproceso.agregar(producto);
                    productosRechazados++;
                    System.out.println("Producto "+producto.darId()+" rechazado.");
                }
                else {
                    //ArrayList<Producto> prodsDeposito = deposito.getProductos();
                    deposito.entregar(producto);
                    //deposito.setProductos(prodsDeposito)
                }
            }
            else {
                //ArrayList<Producto> prodsDeposito = deposito.getProductos();
                deposito.entregar(producto);
                //deposito.setProductos(prodsDeposito);
            }
            try {
            Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run()
    {
        while(!buzonRevision.darTerminar())
        {
            while((producto=buzonRevision.sacar())==null && !buzonRevision.darTerminar())
            {
                Thread.yield();
            }
            if (!buzonRevision.darTerminar())
            {
                revisarProducto(producto);
            }
        }
    }
}
