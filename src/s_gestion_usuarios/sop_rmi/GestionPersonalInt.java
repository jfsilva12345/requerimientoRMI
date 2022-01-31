package s_gestion_usuarios.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Optional;

import s_gestion_usuarios.dto.CredencialDTO;
import s_gestion_usuarios.dto.PersonalDTO;

public interface GestionPersonalInt extends Remote{
    public boolean registrarPersonal(PersonalDTO objPersonal) throws RemoteException; 
    public int abrirSesion(CredencialDTO objCredencial) throws RemoteException;
    public PersonalDTO consultarPersonal(int id) throws RemoteException;
    public boolean editarPersonal(int id, PersonalDTO objPersonalNuevosDatos) throws RemoteException;
    public boolean eliminarPersonal(int id) throws RemoteException;
}
