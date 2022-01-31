package cliente.sop_rmi;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;


public class PafCllbckImpl extends UnicastRemoteObject implements PafCllbckInt{

    public PafCllbckImpl() throws RemoteException{
        super();
    }

    @Override
    public void informarIngreso(String nombreC, int id) throws RemoteException{
        System.out.println("\t NUEVO USUARIO A VALORAR");
        System.out.println("\t Usuario: \n[ "+ nombreC +" ] Identificado con id: [ " + id +" ]" );
    }
}