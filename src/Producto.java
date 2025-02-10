public class Producto 
{
    private String id;
    private String mensaje;

    public Producto(String id)
    {
        this.id = id;
    }

    public void setMensaje(String mensaje)
    {
        this.mensaje = mensaje;
    }

    public String darId()
    {
        return id;
    }

    public String darMensaje()
    {
        return mensaje;
    }
}
