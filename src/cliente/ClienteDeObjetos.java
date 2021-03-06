package cliente;

import s_gestion_usuarios.sop_rmi.GestionPersonalInt;
import s_gestion_usuarios.sop_rmi.GestionUsuariosInt;
import s_gestion_usuarios.dto.PersonalDTO;
import s_gestion_usuarios.dto.UsuarioDTO;
import s_gestion_usuarios.dto.CredencialDTO;
import cliente.sop_rmi.PafCllbckImpl;
import cliente.utilidades.UtilidadesConsola;
import cliente.utilidades.UtilidadesRegistroC;
import java.rmi.RemoteException;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ClienteDeObjetos{
    private static GestionUsuariosInt objRemoto;
    private static GestionPersonalInt objRemoto2;

    public static void main (String[] args){
        int numPuertoRMIRegistry =0;
        String direccionIpRMIRegistry ="";
        // System.out.println("Cual es la direccion ip donde se encuentra el rmiREgistry");
        // direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        // System.out.println("Cual es el numero de puerto por el cual escucha el rmiREgistry");
        // numPuertoRMIRegistry = UtilidadesConsola.leerEntero();
        direccionIpRMIRegistry= "localhost";
        numPuertoRMIRegistry=2022;

        objRemoto = (GestionUsuariosInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry,"ObjetoRemotoUsuarios");
        objRemoto2 = (GestionPersonalInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry,"ObjetoRemotoPersonal");
        MenuPrincipal();
    }
    

    private static void MenuPrincipal()
    {
        int opcion = 0;
        do{
            System.out.println("==Menu Inicio==");
            System.out.println("1. Abrir Sesion Gestores");	
            System.out.println("2. Abrir Sesion Usuarios");			
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero();

             switch(opcion)
            {
                case 1:
                    System.out.println("Ingrese el usuario");
                    String varCrUsuario = UtilidadesConsola.leerCadena();
                    System.out.println("Ingrese la clave");
                    String varCrClave = UtilidadesConsola.leerCadena();
                    CredencialDTO objCredencial = new CredencialDTO(varCrUsuario,varCrClave);
 
                    int sesion = -1;
                    try{
                        System.out.println(objCredencial);
                        sesion = objRemoto2.abrirSesion(objCredencial);
                        
                    }catch(RemoteException e){
                        System.out.println("La operacion no se pudo completar, intente nuevamente...");
                    }

                    switch(sesion){
                        case 0:         
                            OpcionAdmin();
                            break;
                        case 1:
                            try{    
                                System.out.println("REGISTRANDO CALLBACK");
                                PafCllbckImpl objAdmin = new PafCllbckImpl();
                                objRemoto.registrarCallback(objAdmin);                  
                            }
                            catch(RemoteException e){
                                System.out.println("La operacion callback no se pudo completar, intente nuevamente...");
                            }                      
                            OpcionPaf();
                            break;
                        case 2:
                            OpcionSecre();
                            break;
                        case -1:
                            System.out.println("Usuario no encontrado");
                            break;
                        default:
                            System.out.println("Opci??n incorrecta");
                        }
                break;

                case 2:
                    System.out.println("Salir...");
                break;

                case 3:
                    System.out.println("Salir...");
                break;
                
                default:
                    System.out.println("Opci??n incorrecta");
                break;
            }

        }while(opcion != 3);
        
    }

    private static void OpcionAdmin(){
        int opcionAdmin=0;
        
        do
        {
            System.out.println("==Menu==");
            System.out.println("1. Registrar personal");			
            System.out.println("2. Consultar personal");
            System.out.println("3. Editar personal");
            System.out.println("4. Eliminar personal");
            System.out.println("5. Salir");

            opcionAdmin = UtilidadesConsola.leerEntero();

            switch(opcionAdmin)
            {
                case 1:
                        Opcion1();
                        break;
                case 2:
                        Opcion2();
                        break;	
                case 3:
                        Opcion3();
                        break;
                case 4:
                        Opcion4();
                        break;
                case 5:
                        System.out.println("Salir...");
                        break;
                default:
                        System.out.println("Opci??n incorrecta");
            }

        }while(opcionAdmin != 5);
    }


    private static void OpcionPaf(){
        int opcionPaf=0;
             
        do
        {
            System.out.println("==Menu PAF==");
            System.out.println("1. Valorar PAF");			
            System.out.println("2. Registrar Asistencia");
            System.out.println("3. Salir");

            opcionPaf = UtilidadesConsola.leerEntero();

            switch(opcionPaf)
            {
                case 1:
                        System.out.println("por implementar");
                        break;
                case 2:
                        System.out.println("por implementar");
                        break;	
                case 3:
                        System.out.println("Salir...");
                        break;
                default:
                        System.out.println("Opci??n incorrecta");
            }

        }while(opcionPaf != 3);
    }

     private static void OpcionSecre(){
         int opcionSecre = 0;
        do
        {
            System.out.println("==Menu SECRETARIA==");
            System.out.println("1. Registrar usuario");			
            System.out.println("2. Consultar usuario");
            System.out.println("3. Salir");

            opcionSecre = UtilidadesConsola.leerEntero();

            switch(opcionSecre)
            {
                case 1:
                        Opcion1Secre();
                        break;
                case 2:
                        Opcion2Secre();
                        break;	
                case 3:
                        System.out.println("Salir...");
                        break;
                default:
                        System.out.println("Opci??n incorrecta");
            }

        }while(opcionSecre != 3);
    }

    private static void Opcion1(){
        try
        {
            System.out.println("==Registro del Cliente==");
            boolean bandera=false;
            int opcionTI = 0;
            String varTipoIdentificacion="";

                System.out.println("==TIPO DE IDENTIFICACION==");
                System.out.println("1. Cedula de Ciudadania");			
                System.out.println("2. Tarjeta de Identidad");
                System.out.println("3. Pasaporte");
                


                opcionTI = UtilidadesConsola.leerEntero();

                if(opcionTI==1){
                    varTipoIdentificacion="CC";
                }else if(opcionTI==2){
                    varTipoIdentificacion="TI";
                }else if(opcionTI==3){
                    varTipoIdentificacion="PP";
                }else{
                    bandera=true;
                }


            System.out.println("Ingrese el numero de identificacion");
            int varId = UtilidadesConsola.leerEntero();
            if (varId < 0){
                bandera = true;
            }

            System.out.println("Ingrese el nombre completo ");
            String varNombres = UtilidadesConsola.leerCadena();

            System.out.println("Ingrese la ocupacion del nuevo usuario ");
            String varOcupacion="";

                System.out.println("==TIPO DE OCUPACION==");
                System.out.println("1. Secretaria");			
                System.out.println("2. Profesional de acondicionamiento fisico");
                

                opcionTI = UtilidadesConsola.leerEntero();


                if(opcionTI==1){
                    varOcupacion="Secretaria";
                }else if(opcionTI==2){
                    varOcupacion="Paf";
                }else{
                    bandera=true;
                }

            System.out.println("Ingrese el usuario ");
            String varUsuario = UtilidadesConsola.leerCadena();

            if (varUsuario.length()<8){
                bandera=true;
            }


            System.out.println("Ingrese la contrase??a ");
            String varClave = UtilidadesConsola.leerCadena();

            if (varClave.length()<8){
                bandera=true;
            }
            if(!bandera){

                PersonalDTO objPersonal= new PersonalDTO(varTipoIdentificacion, varId, varNombres,varOcupacion,varUsuario,varClave);

                boolean valor = objRemoto2.registrarPersonal(objPersonal);
                if(valor){
                        System.out.println("Registro realizado satisfactoriamente...");
                }else{
                    System.out.println("no se pudo realizar el registro...");
                }

            }else{
                System.out.println("datos erroneos");
            }
           
        }
        catch(RemoteException e)
        {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }

    private static void Opcion2(){	
        int id = -1;
        try
        {
            System.out.println("========================");
            System.out.println("==Consulta de personal==");
            System.out.println("========================");

            System.out.println("Digite el id del personal a buscar");

            id = UtilidadesConsola.leerEntero();

            PersonalDTO personal  = objRemoto2.consultarPersonal(id);

            if(personal != null){
                System.out.println(personal.getTipo_id());
                System.out.println(personal.getId());
                System.out.println(personal.getUsuario());
                System.out.println(personal.getNombreCompleto());
                System.out.println(personal.getOcupacion());
            }else{
                System.out.println("N O   E X I S T E");
            }
            
        }
        catch(RemoteException e)
        {
            System.out.println("La operaci??n no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }	
    }
    

    private static void Opcion3(){	
        int id = -1;
        int opcionTI = 0;
        boolean bandera=false;
        try
        {
            System.out.println("========================");
            System.out.println("==Editar personal==");
            System.out.println("========================");

            System.out.println("Digite el id del personal a editar");

            id = UtilidadesConsola.leerEntero();

            PersonalDTO personal  = objRemoto2.consultarPersonal(id);

            if (personal != null){
                System.out.println(personal.getTipo_id());
                System.out.println(personal.getId());
                System.out.println(personal.getUsuario());
                System.out.println(personal.getNombreCompleto());
                System.out.println(personal.getOcupacion());

                System.out.println("========================");
                System.out.println("==Editar personal==");
                System.out.println("========================");

                System.out.println("Ingrese el nombre completo ");
                String varNombres = UtilidadesConsola.leerCadena();

                System.out.println("Ingrese la ocupacion del nuevo usuario ");
                String varOcupacion="";

                    System.out.println("==TIPO DE OCUPACION==");
                    System.out.println("1. Secretaria");			
                    System.out.println("2. Profesional de acondicionamiento fisico");
                    

                    opcionTI = UtilidadesConsola.leerEntero();


                    if(opcionTI==1){
                        varOcupacion="Secretaria";
                    }else if(opcionTI==2){
                        varOcupacion="Paf";
                    }else{
                        bandera=true;
                    }

                System.out.println("Ingrese el usuario ");
                String varUsuario = UtilidadesConsola.leerCadena();

                if (varUsuario.length()<8){
                    bandera=true;
                }


                System.out.println("Ingrese la contrase??a ");
                String varClave = UtilidadesConsola.leerCadena();

                if (varClave.length()<8){
                    bandera=true;
                }
                if(!bandera){
                    PersonalDTO objPersonalE= new PersonalDTO(personal.getTipo_id(), personal.getId(), varNombres,varOcupacion,varUsuario,varClave);
                    boolean varRetorno = objRemoto2.editarPersonal(id, objPersonalE);
                    if(varRetorno){
                        System.out.println("Registro realizado satisfactoriamente...");
                    }else{
                        System.out.println("no se pudo realizar el registro...");
                    }

                }else{
                    System.out.println("datos erroneos");
                }

            }
            
        }
        catch(RemoteException e)
        {
            System.out.println("La operaci??n no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }	
    }

    private static void Opcion4(){	
        int id = -1;
        boolean confirmacion;
        try
        {
            System.out.println("========================");
            System.out.println("==Borrar personal==");
            System.out.println("========================");

            System.out.println("Digite el id del personal a eliminar");

            id = UtilidadesConsola.leerEntero();
            PersonalDTO personal  = objRemoto2.consultarPersonal(id);

            if(personal != null){
                System.out.println(personal.getTipo_id());
                System.out.println(personal.getId());
                System.out.println(personal.getUsuario());
                System.out.println(personal.getNombreCompleto());
                System.out.println(personal.getOcupacion());
            }
            System.out.println("Digite el 1 para borrar o 0 para cancelar");

            int varConf = UtilidadesConsola.leerEntero();

            if(varConf==1){

                confirmacion=objRemoto2.eliminarPersonal(id);
                if (confirmacion){
                    System.out.println("Eliminado con exito");
                }else{
                    System.out.println("Eliminacion fallida");
                }
            }else{
                System.out.println("GRACIAS");
            }

        }
        catch(RemoteException e)
        {
            System.out.println("La operaci??n no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }	
    }
    
    
    private static void Opcion1Secre(){
        try
        {
            System.out.println("==Registro del Usuario==");
            boolean bandera=false;
            int opcionTI = 0;
            String varDepenFac="";
            String varTipoUsuario="";
            String varFechaIngreso="";

            System.out.println("Ingrese el nombre completo ");
            String varNombres = UtilidadesConsola.leerCadena();


//#region numero id
            System.out.println("Ingrese el numero de identificacion");
            int varId = UtilidadesConsola.leerEntero();
            if (varId < 0){
                bandera = true;
            }

//#endregion

//#region dependenci

            System.out.println("==DEPENDENCIA/FACULTAD==");
            System.out.println("1. Dependencias");			
            System.out.println("2. Facultad");

            opcionTI = UtilidadesConsola.leerEntero();

            if(opcionTI==1){
                varDepenFac="Dependencias";
            }else if(opcionTI==2){
                varDepenFac="Facultad";
            }else {
                bandera=true;
            }

//#endregion

//#region TIPO USER

            System.out.println("==DEPENDENCIA/FACULTAD==");
            System.out.println("1. ADMINISTRATIVOS");			
            System.out.println("2. DOCENTES");

            opcionTI = UtilidadesConsola.leerEntero();

            if(opcionTI==1){
                varTipoUsuario="ADMINISTRATIVOS";
            }else if(opcionTI==2){
                varTipoUsuario="DOCENTES";
            }else {
                bandera=true;
            }

//#endregion
            
//#region date

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
            varFechaIngreso = dtf.format(LocalDateTime.now());

//#endregion

//#region patologia

            System.out.println("Ingrese la patologia ");
            String varPatologia = UtilidadesConsola.leerCadena();
//#endregion

//#region user
            System.out.println("Ingrese el usuario ");
            String varUsuario = UtilidadesConsola.leerCadena();

            if (varUsuario.length()<8){
                bandera=true;
            }
//#endregion

//#region contra
            System.out.println("Ingrese la contrase??a ");
            String varClave = UtilidadesConsola.leerCadena();

            if (varClave.length()<8){
                bandera=true;
            }
//#endregion
            if(!bandera){

                UsuarioDTO objUsuario= new UsuarioDTO(varNombres, varId, varDepenFac,varTipoUsuario,varFechaIngreso,varPatologia, varUsuario,varClave);

                boolean valor = objRemoto.registrarUsuario(objUsuario);
                if(valor)
                        System.out.println("Registro realizado satisfactoriamente...");
                else
                        System.out.println("no se pudo realizar el registro...");

            }else{
                System.out.println("datos erroneos");
            }
           
        }
        catch(RemoteException e)
        {
            System.out.println("La operacion no se pudo completar, intente nuevamente..."+e);
        }
    }

    private static void Opcion2Secre(){	
        int id = -1;
        try
        {
            System.out.println("========================");
            System.out.println("==Consulta de usuarios==");
            System.out.println("========================");

            System.out.println("Digite el id del usuario a buscar");

            id = UtilidadesConsola.leerEntero();

            UsuarioDTO usuario  = objRemoto.consultarUsuario(id);

            if (usuario != null){
                System.out.println(usuario.getNombreCompleto());
                System.out.println(usuario.getId());
                System.out.println(usuario.getDependencia());
                System.out.println(usuario.getTipoUsuario());
                System.out.println(usuario.getFechaIngreso());
                System.out.println(usuario.getUsuario());
            }
            
        }
        catch(RemoteException e)
        {
            System.out.println("La operaci??n no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }	
    }

    
}