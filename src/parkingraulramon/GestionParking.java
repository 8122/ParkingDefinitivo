package parkingraulramon;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GestionParking {

    public static void main(String[] args) {

        Parking parking = new Parking("Cementerio de coches");
        int opcion = 0;

        Scanner entradaInt = new Scanner(System.in);
        Scanner entradaStr = new Scanner(System.in);

        do {
            System.out.println("     ***********************");
            System.out.println("     *****     MENU    *****");
            System.out.println("     ***********************");
            System.out.println("\t1. Alquilar plaza.");
            System.out.println("\t2. Abandonar plaza.");
            System.out.println("\t3. Calcular ganancias.");
            System.out.println("\t4. Cerrar programa.");
            System.out.print("Elija una opción: ");
            try{
                opcion = entradaInt.nextInt();
            }catch (InputMismatchException e){
                entradaInt.nextLine();
                System.out.println("ERROR: No ha introducido un numero entero.");
                opcion = 5;
            }catch (Exception e){
                System.out.println("ERROR INESPERADO.");
                opcion = 5;
            }
            switch (opcion) {
                case 1: //Alquilar plaza
                    //Creamos el objeto vehiculo
                    String matricula = null;
                    String modelo = null;
                    String color = null;
                    String nif = null;
                    int telefono = 0;
                    char tipoVehiculo;
                    String tipo = null;
                    Vehiculo v = null;
                    String numPlaza = null;
                    int numInt = 0;//numero de plaza en enteros
                    
                    try{
                        System.out.println("Datos del vehiculo:");
                        System.out.print("Introduzca matricula (mínimo 7 carácteres): ");
                        matricula = entradaStr.nextLine();
                        if(matricula.length()<7){
                            System.out.println("No ha introducido correctamente la matricula. Minimo 7 digitos");
                            break;
                        }
                        System.out.print("Introduzca modelo: ");
                        modelo = entradaStr.nextLine();
                        if(modelo.length()<3){
                            System.out.println("No ha introducido correctamente el modelo");
                            modelo = "sin definir";
                        }
                        System.out.print("Introduzca color (minimo 3 caracteres): ");
                        color = entradaStr.nextLine();
                        if(modelo.length()<3){
                            System.out.println("No ha introducido correctamente el color");
                            color = "sin definir";
                        }
                        System.out.print("Introduzca NIF del propietario (9 caracteres): ");
                        nif = entradaStr.nextLine();
                        if(nif.length()<9){
                            System.out.println("No ha introducido correctamente el NIF");
                            break;
                        }
                        System.out.print("Introduzca telefono (9 digitos): ");
                        telefono = entradaInt.nextInt();
                        if(telefono >= 100000000 && telefono < 1000000000){
                            System.out.println("No ha introducido correctamente el telefono");
                        }
                        
                        //Según el tipo de vehiculo creamos un objeto Coche o Moto
                        boolean hayTipo = false;
                        do{
                            System.out.print("Introduzca tipo de vehiculo (C/M): ");
                            tipoVehiculo = entradaStr.next().charAt(0);
                            if(tipoVehiculo != 'C' && tipoVehiculo != 'M'){
                                System.out.println("No ha introducido correctamente el tipo de vehiculo."
                                        + "\nDebe introducir C o M.");
                                break;
                            }
                            if (tipoVehiculo == 'C') {
                                Scanner entradaStr2 = new Scanner(System.in);
                                System.out.print("Longitud coche (corto/largo): ");
                                tipo = entradaStr2.nextLine().toLowerCase();
                                if(tipo.equalsIgnoreCase("c") || tipo.equals("corto")){
                                    tipo = "corto";
                                }else if(tipo.equalsIgnoreCase("l") || tipo.equals("largo")){
                                    tipo = "largo";
                                }else{
                                    System.out.println("No ha introducido correctamente la longitud coche");
                                    break;
                                }
                                v = new Coche(tipo, matricula, nif);
                                hayTipo = true;
                            }
                            if (tipoVehiculo == 'M') {
                                v = new Moto(matricula, nif);
                                hayTipo = true;
                            } 
                        }while(hayTipo = false);
                        //a?adimos el resto de atributos que no estan en el constructor
                        v.setModelo(modelo);
                        v.setColor(color);
                        v.setTelefono(telefono);
                    }catch (InputMismatchException e){
                        entradaInt.nextLine();
                        System.out.println("ERROR: No ha introducido un numero telefono con numeros enteros."
                                + "\nVuelva a intentarlo.");
                        break;
                    }catch (NullPointerException e){
                        entradaStr.nextLine();
                        System.out.println("ERROR: No se ha introducido los datos del vehiculo correctamente vehiculo correctamente.");
                        break;
                    }catch (Exception e){
                        System.out.println("ERROR INESPERADO.");
                        break;
                    }
                    

                    //alquilamos la plaza
                    numPlaza = parking.alquilar(v);
                    if (numPlaza != null) {
                        System.out.println("\n\nN? plaza designada al vehiculo matricula" + v.getMatricula() + " es: " + numPlaza + "\n");
                    } else {
                        System.out.println("No hay plazas disponibles.");
                    }
                    break;
                case 2: //Abandonar plaza
                    try{
                        int opcion2 = 0;
                        Scanner entradaInt2 = new Scanner(System.in);
                        System.out.print("Introduzca numero de plaza: ");
                        numInt = entradaInt2.nextInt();
                        opcion2 = parking.darBaja(numInt);
                        switch (opcion2) {
                            case 0:
                                System.out.println("Se ha dado de baja el alquiler.");
                                break;
                            case 1:
                                System.out.println("No existe ninguna plaza con ese número.");
                                break;
                            case 2:
                                System.out.println("La plaza no esta ocupada.");
                        }
                    }catch (InputMismatchException e){
                        System.out.println("ERROR 1: No se ha introducido un entero en numero plaza.");
                        break;
                    }
                    break;
                case 3: //calcular ganancias
                    System.out.println("\nSe ha obtenido unas ganancias de: " + parking.ganancias() + " €\n");
                    break;
                case 4://Cerrar programa y listar plaza libres
                    List<Plaza> listaCoches = parking.listarPlazas("libres", 'C');
                    List<Plaza> listaMotos = parking.listarPlazas("libres", 'M');
                    LocalDate hoy = LocalDate.now();
                    String listaC = "";
                    String listaM = "";
                    System.out.println("\nListado de plazas libres de Coche a fecha " + hoy.toString());
                    for (Plaza p : listaCoches) {
                        listaC += "\t" + p.toString() + "\n";
                    }
                    System.out.print(listaC);
                    System.out.println("\nListado de plazas libres de Moto a fecha " + hoy.toString());
                    for (Plaza p : listaMotos) {
                        listaM += "\t" + p.toString() + "\n";
                    }
                    System.out.print(listaM);
                    break;
                case 5:
                    System.out.println("Vuelva a intentarlo.");
                    break;
                default:
                    System.out.println("No ha elegido una opcion correcta.");
            }
        } while (opcion != 4);
    }
}
