package s_gestion_usuarios;

import s_gestion_usuarios.sop_rmi.GestionPersonalImpl;
import s_gestion_usuarios.sop_rmi.GestionUsuariosImpl;
import s_gestion_usuarios.utilidades.UtilidadesConsola;
import s_gestion_usuarios.utilidades.UtilidadesRegistroS;
import java.rmi.RemoteException;

public class ServidorDeObjetos01{

    public static void main(String args[]) throws RemoteException{

        int numPuertoRMIRegistry =0;
        String direccionIpRMIRegistry ="";
        // System.out.println("Cual es la direccion ip donde se encuentra el rmiREgistry");
        // direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        // System.out.println("Cual es el numero de puerto por el cual escucha el rmiREgistry");
        // numPuertoRMIRegistry = UtilidadesConsola.leerEntero();
        direccionIpRMIRegistry= "localhost";
        numPuertoRMIRegistry=2022;

        GestionUsuariosImpl objRemoto = new GestionUsuariosImpl();
        GestionPersonalImpl objRemoto2 = new GestionPersonalImpl();
       // objRemoto.consultarReferenciaRemota(direccionIpRMIRegistry,numPuertoRMIRegistry);
        try{
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto,direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoUsuarios");
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto2, direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoPersonal");
        }catch(Exception e){
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto"+ e.getMessage());
        }
    }
}