package parkingraulramon;

public class Coche extends Vehiculo {

    private String tipo;
    
    public Coche(String tipo, String matricula, String nif) {
        super(matricula, nif);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
