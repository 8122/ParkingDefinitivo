package parkingraulramon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Parking {

    private String nombre;
    private String direccion;
    private Map<String, Plaza> listadoPlazas = new HashMap<>();
    
 
        
    public Parking(String nombre) {
        this.nombre = nombre;    
        
        Vehiculo c1 = new Coche("largo", "4562-FGF", "48562144S");
        Vehiculo c2 = new Coche("corto", "4478-FRF", "55412552R");
        Vehiculo m1 = new Moto("7412-EEE", "45522141B");
        Vehiculo c3 = new Coche("corto", "8874-LLK", "27452225B");

        Plaza p1 = new Plaza(1, "Primero");
        Plaza p2 = new Plaza(2, "Primero");
        Plaza p3 = new Plaza(3, "Primero");
        Plaza p4 = new Plaza(4, "Primero");
        Plaza p5 = new Plaza(5, "Primero");
        Plaza p6 = new Plaza(6, "Segundo");
        Plaza p7 = new Plaza(7, "Segundo");
        Plaza p8 = new Plaza(8, "Segundo");
        Plaza p9 = new Plaza(9, "Segundo");
        Plaza p10 = new Plaza(10, "Segundo");

        p1.setTipo('C');
        p2.setTipo('C');
        p3.setTipo('C');
        p4.setTipo('M');
        p5.setTipo('C');
        p6.setTipo('C');
        p7.setTipo('C');
        p8.setTipo('M');
        p9.setTipo('M');
        p10.setTipo('C');

        this.listadoPlazas.put("101", p1);
        this.listadoPlazas.put("102", p2);
        this.listadoPlazas.put("103", p3);
        this.listadoPlazas.put("104", p4);
        this.listadoPlazas.put("105", p5);
        this.listadoPlazas.put("206", p6);
        this.listadoPlazas.put("207", p7);
        this.listadoPlazas.put("208", p8);
        this.listadoPlazas.put("209", p9);
        this.listadoPlazas.put("210", p10);

        p1.setVehiculo(c2);
        p7.setVehiculo(c1);
        p4.setVehiculo(m1);
        p2.setVehiculo(c3);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Map<String, Plaza> getListadoPlazas() {
        return listadoPlazas;
    }

    public void setListadoPlazas(Map<String, Plaza> listadoPlazas) {
        this.listadoPlazas = listadoPlazas;
    }

    public String alquilar(Vehiculo v) {
        boolean fin = false;
        String numPlaza = null;
        Iterator<String> it = listadoPlazas.keySet().iterator();
        while (it.hasNext() && fin == false) {
            String snn = it.next();
            Plaza plaza = this.getListadoPlazas().get(snn);
            if (plaza.getVehiculo() == null) {
                if (v instanceof Coche && plaza.getTipo() == 'C') {
                    plaza.setVehiculo(v);
                    fin = true;
                    numPlaza = Integer.toString(plaza.getNumPlaza());
                }
                if (v instanceof Moto && plaza.getTipo() == 'M') {
                    plaza.setVehiculo(v);;
                    fin = true;
                    numPlaza = Integer.toString(plaza.getNumPlaza());
                }
            }
        }
        return numPlaza;
    }

    public int darBaja(int numPlaza) {
        boolean fin = false;
        int baja = 1;
        Iterator<String> it = listadoPlazas.keySet().iterator();
        while (it.hasNext() && fin == false) {
            String snn = it.next();
            Plaza plaza = listadoPlazas.get(snn);
            if (numPlaza == plaza.getNumPlaza()) {
                if (plaza.getVehiculo() != null) {
                    plaza.setVehiculo(null);
                    baja = 0;
                } else {
                    baja = 2;
                }
                fin = true;
            }
        }
        return baja;
    }

    public List<Plaza> listarPlazas(String estado, char tipoVehiculo) {
        List<Plaza> listado = new ArrayList<>();
        List<Plaza> ocupadasCoches = new ArrayList<>();
        List<Plaza> libresCoches = new ArrayList<>();
        List<Plaza> ocupadasMotos = new ArrayList<>();
        List<Plaza> libresMotos = new ArrayList<>();
        Iterator<String> it = listadoPlazas.keySet().iterator();
        while (it.hasNext()) {
            String snn = it.next();
            Plaza plaza = listadoPlazas.get(snn);
            if (plaza.getVehiculo() != null && plaza.getTipo() == 'C') {
                ocupadasCoches.add(plaza);
            }
            if (plaza.getVehiculo() == null && plaza.getTipo() == 'C') {
                libresCoches.add(plaza);
            }
            if (plaza.getVehiculo() != null && plaza.getTipo() == 'M') {
                ocupadasMotos.add(plaza);
            }
            if (plaza.getVehiculo() == null && plaza.getTipo() == 'M') {
                libresMotos.add(plaza);
            }
        }
        if (estado.equals("ocupadas") && tipoVehiculo == 'C') {
            listado = ocupadasCoches;
        }
        if (estado.equals("libres") && tipoVehiculo == 'C') {
            listado = libresCoches;
        }
        if (estado.equals("ocupadas") && tipoVehiculo == 'M') {
            listado = ocupadasMotos;
        }
        if (estado.equals("libres") && tipoVehiculo == 'M') {
            listado = libresMotos;
        }
        return listado;
    }

    public int ganancias() {
        int beneficios = 0;
        List<Plaza> listadoCoches = new ArrayList<>();
        listadoCoches = listarPlazas("ocupadas", 'C');
        List<Plaza> listadoMotos = new ArrayList<>();
        listadoMotos = listarPlazas("ocupadas", 'M');
        for (Plaza plaza : listadoCoches) {
            beneficios += plaza.precio();
        }
        for (Plaza plaza : listadoMotos) {
            beneficios += plaza.precio();
        }
        return beneficios;
    }
}
