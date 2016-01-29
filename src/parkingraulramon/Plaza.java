package parkingraulramon;

import java.text.DecimalFormat;

public class Plaza {

    private Vehiculo vehiculo = null;
    private int numPlaza;
    private String sotano;
    private char tipo;

    public Plaza(int numPlaza, String sotano) {
        this.numPlaza = numPlaza;
        this.sotano = sotano;
    }

    public String getSotano() {
        return sotano;
    }

    public void setSotano(String sotano) {
        this.sotano = sotano;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getNumPlaza() {
        return numPlaza;
    }

    public void setNumPlaza(int numPlaza) {
        this.numPlaza = numPlaza;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int precio() {
        int precio = 0;
        if (this.vehiculo instanceof Coche) {
            Coche coche = (Coche) this.vehiculo;
            if (coche.getTipo().equals("corto")) {
                precio = 40;
            }
            if (coche.getTipo().equals("largo")) {
                precio = 55;
            }
        }
        if (this.vehiculo instanceof Moto) {
            Moto moto = (Moto) this.vehiculo;
            precio = 25;
        }
        return precio;
    }

    @Override
    public String toString() {
        DecimalFormat dosDig = new DecimalFormat("00");
        String resultado = null;
        if (this.vehiculo == null) {
            resultado = "Numero plaza: " + dosDig.format(this.numPlaza) + " Sótano: " + this.sotano;
        } else {
            resultado = "Numero plaza: " + dosDig.format(this.numPlaza) + " Sótano: " + this.sotano
                    + "\nVehiculo: " + this.vehiculo.toString()
                    + "\nPrecio: " + this.precio();
        }
        return resultado;
    }
}
