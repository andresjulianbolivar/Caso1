public class Inspector extends Thread
{
    private static BuzonReproceso buzonReproceso;
    private static BuzonRevision buzonRevision;
    private static Deposito deposito;

    private static int pedido;
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
        if (deposito.getProductos()==pedido)
        {
            producto.setMensaje("Fin");
            buzonReproceso.agregar(producto);
            buzonReproceso.setFin(true);
            System.out.println("Se ha alcanzado el pedido.");
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
        while (!buzonRevision.darTerminar()) { //variable FIN? pendiente
            if (buzonReproceso.darFin())
            {
                producto = buzonRevision.sacar();
                if (producto == null)
                {
                    buzonRevision.setTerminar(true);
                }
                else
                {
                    revisarProducto(producto);
                }
            }
            else
            {
                while((producto=buzonRevision.sacar())==null)
                {
                    Thread.yield();
                }
                revisarProducto(producto);
            }
        }
    }
}
