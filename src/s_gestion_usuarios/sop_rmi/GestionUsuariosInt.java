package s_gestion_usuarios.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Optional;

import s_gestion_usuarios.dto.UsuarioDTO;
import cliente.sop_rmi.PafCllbckInt;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface GestionUsuariosInt extends Remote{
    public boolean registrarUsuario(UsuarioDTO objUsuario) throws RemoteException; 
    public UsuarioDTO consultarUsuario(int id) throws RemoteException;
    public void registrarCallback(PafCllbckInt objAdmin) throws RemoteException;
}