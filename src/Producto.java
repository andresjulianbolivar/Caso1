public class Producto 
{
    private int id;
    private String mensaje;

    public Producto(int id)
    {
        this.id = id;
    }

    public void setMensaje(String mensaje)
    {
        this.mensaje = mensaje;
    }

    public int darId()
    {
        return id;
    }

    public String darMensaje()
    {
        return mensaje;
    }
}
