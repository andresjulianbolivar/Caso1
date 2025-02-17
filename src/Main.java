import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class Main 
{
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de operarios (para equipo de calidad y producción): ");
        int operarios = scanner.nextInt();

        System.out.print("Ingrese el número de productos que debe producir el sistema: ");
        int nPedido = scanner.nextInt();

        System.out.print("Ingrese el límite del buzón de revisión: ");
        int limite = scanner.nextInt();

        // Crear Deposito
        Deposito deposito = new Deposito();

        // Crear Buzon Revision
        BuzonRevision revision = new BuzonRevision(limite);

        // Crear Buzon Revision
        BuzonReproceso reproceso = new BuzonReproceso();

        //Crear Barrera para los productores
        CyclicBarrier barrera = new CyclicBarrier(operarios);

        Inspector.setStatics(nPedido, reproceso, revision, deposito);

        Productor.inicializarMonitores(reproceso, revision, barrera);

        ArrayList<Productor> productores = new ArrayList<Productor>();
        ArrayList<Inspector> inspectores = new ArrayList<Inspector>();

        // Crear Inspectores y correrlos
        for (int i = 0; i < operarios; i++) {
            Inspector inspector = new Inspector();
            Productor productor = new Productor();

            productores.add(productor);
            inspectores.add(inspector);

            inspector.start();
            productor.start();
        }

        for (int i = 0; i < operarios; i++)
        {
            inspectores.get(i).join();
            productores.get(i).join();
        }

        scanner.close();

        System.out.println("Productor creados: "+reproceso.darContadorId());
        System.out.println("Productos en depósito: "+deposito.getProductos());
        System.out.println("Productos en buzón de revisión: "+revision.getProductos());
        System.out.println("Productos en buzón de reproceso: "+reproceso.getProductos());
    }
}
