import java.util.Scanner;

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

        Inspector.setStatics(nPedido, reproceso, revision, deposito);

        Productor.inicializarMonitores(reproceso, revision);

        // Crear Inspectores y correrlos
        for (int i = 0; i < operarios; i++) {
            Inspector inspector = new Inspector();
            Productor productor = new Productor();

            inspector.start();
            productor.start();
        }

        scanner.close();
    }
}
