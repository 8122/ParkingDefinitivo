package parkingraulramon;

public class Vehiculo {

    private String matricula;
    private String color;
    private String modelo;
    private String nif;
    private int telefono;

    public Vehiculo(String matricula, String nif) {
        this.matricula = matricula;
        this.nif = nif;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        if (telefono >= 100000000 && telefono < 1000000000) {
            this.telefono = telefono;
        }else{
            this.telefono = 0;
        }
    }

    @Override
    public String toString() {
        return this.matricula + " " + this.modelo + " " + this.color;
    }

}
