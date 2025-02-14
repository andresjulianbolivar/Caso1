public class Main 
{
    private int operarios;
    public static void main(String[] args) throws Exception {
        // Crear Deposito
        Deposito deposito = new Deposito();

        // Crear Buzon Revision
        BuzonRevision revision = new BuzonRevision(10);

        // Crear Buzon Revision
        BuzonReproceso reproceso = new BuzonReproceso();

        // Crear Inspectores y correrlos
        for (int i = 1; i < 6; i++) {
            Inspector inspector = new Inspector();
            inspector.start();
        }
        Inspector.setStatics(100, reproceso, revision, deposito);

        // Crear Productores y correrlos
        for (int i = 1; i < 6; i++) {
            Productor productor = new Productor();
            productor.start();
        }
        Productor.inicializarMonitores(reproceso, revision);
    }
}
