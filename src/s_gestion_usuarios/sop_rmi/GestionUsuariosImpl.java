package s_gestion_usuarios.sop_rmi;

import s_gestion_usuarios.dto.UsuarioDTO;
import cliente.sop_rmi.PafCllbckInt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Optional;

public class GestionUsuariosImpl extends UnicastRemoteObject implements GestionUsuariosInt{

    private static final String ARCHIVO_USUARIOS = "usuarios.txt";
    private ArrayList<UsuarioDTO> usuarios;
    private PafCllbckInt objCllBck;
    
    public GestionUsuariosImpl() throws RemoteException{
        super();
        this.usuarios = new ArrayList<>();
    }

    @Override
    public boolean registrarUsuario(UsuarioDTO objUsuario) throws RemoteException{
        System.out.println("==========================================================");
        System.out.println("\tRegistro de usuarios");
        System.out.println("==========================================================");

        objCllBck.informarIngreso(objUsuario.getNombreCompleto(), objUsuario.getId());

        try {
            FileOutputStream ficheroSalida = new FileOutputStream(ARCHIVO_USUARIOS);
            ObjectOutputStream objetoSalida = new ObjectOutputStream(ficheroSalida);
            objetoSalida.writeObject(objUsuario);

            objetoSalida.close();
            System.out.println("-----------Usuario registrado----------- \n \t identificación: " + objUsuario.getId() + ",\n \t  nombres: " + objUsuario.getNombreCompleto());
            return true;
        } catch (Exception e) {
            System.err.println("Error -> " + e.getMessage());
            return false;
        }
    }

    @Override
    public UsuarioDTO consultarUsuario(int id) throws RemoteException{
        
        System.out.println("==========================================================");
        System.out.println("\tConsulta de usuarios");
        System.out.println("==========================================================");

        try {

            FileInputStream ficheroEntrada = new FileInputStream(ARCHIVO_USUARIOS);
            ObjectInputStream objetoEntrada = new ObjectInputStream(ficheroEntrada); 

            usuarios.add((UsuarioDTO)objetoEntrada.readObject());
            objetoEntrada.close();

            for(UsuarioDTO tmpUsuarioDTO : usuarios){
                if(tmpUsuarioDTO.getId() == id){
                    return tmpUsuarioDTO;
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error -> " + e.getMessage());
        }
        return null;
    }

    @Override   
    public void registrarCallback(PafCllbckInt objAdmin) throws RemoteException{
        objCllBck=objAdmin;
    }
}
