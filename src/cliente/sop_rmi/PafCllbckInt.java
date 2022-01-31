package cliente.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PafCllbckInt extends Remote{
    public void informarIngreso(String nombreC, int id) throws RemoteException;
}