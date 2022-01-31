package s_gestion_usuarios.sop_rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.rmi.server.UnicastRemoteObject;

import s_gestion_usuarios.dto.CredencialDTO;
import s_gestion_usuarios.dto.PersonalDTO;

public class GestionPersonalImpl extends UnicastRemoteObject implements GestionPersonalInt{

    private List<PersonalDTO> personal;

    public GestionPersonalImpl () throws RemoteException{
        this.personal = new ArrayList<>();
        String tipoId = "CC";
        int id = 6536;
        String nombrecompleto = "Josefino Eusebio Muñoz Narváez";
        String ocupacion = "Admin";
        String usuario = "admin12345";
        String clave = "12345678";
        PersonalDTO admin = new PersonalDTO(tipoId, id, nombrecompleto, ocupacion, usuario, clave);
        personal.add(admin);
    }

    @Override
    public boolean registrarPersonal(PersonalDTO objPersonal) throws RemoteException {
        System.out.println("==================================================");
        System.out.println("\tRegistro de personal");
        System.out.println("==================================================");
        boolean bandera=false;

        if(listaTieneEspacio())
        {            
            bandera = personal.add(objPersonal);
            System.out.println("Usuario registrado: \n \t identificación: " + objPersonal.getId() + ",\n \t  nombres: " + objPersonal.getNombreCompleto());
        }
        return bandera;
    }

    @Override
    public int abrirSesion(CredencialDTO objCredencial) throws RemoteException{

        PersonalDTO tmpPersonalDTO = ocupacionBuscadaCredenciales(objCredencial);

        String ocupacion = tmpPersonalDTO.getOcupacion();


        switch(ocupacion){
            case "Admin":
                return 0;
            case "Paf":
                return 1;
            case "Secretaria":
                return 2;
        }
        return -1;
    }

    @Override
    public PersonalDTO consultarPersonal(int id) throws RemoteException {
        System.out.println("==================================================");
        System.out.println("\tConsulta de personal");
        System.out.println("==================================================");
        // int position = -1;
        // position = personalPosition(id);
        // if(position != -1){
        //     return Optional.of(personal.get(position));
        // }
        // return Optional.empty();
        PersonalDTO objUsuario=null;
        int contador = 0;
        while(contador<personal.size()){
            if(personal.get(contador).getId()==id){
                
                objUsuario=personal.get(contador);

                break;
            }
            contador++;
        }
        return objUsuario; 
    }

    @Override
    public boolean editarPersonal(int id, PersonalDTO objPersonalNuevosDatos) throws RemoteException {
        System.out.println("==================================================");
        System.out.println("\tEdición de personal");
        System.out.println("==================================================");
        int position = -1;
        
        position = IdUsuarioExiste(id);
        System.out.println("esta es la posicion"+position);
        if(position != -1){
            personal.set(position, objPersonalNuevosDatos);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarPersonal(int id) throws RemoteException {
        System.out.println("==================================================");
        System.out.println("\tAdministrador - Consulta de personal");
        System.out.println("==================================================");
        int position = -1;
        position = IdUsuarioExiste(id);
        if(position != -1){
            personal.remove(position);
            return true;
        }
        return false;
    }

    public boolean listaTieneEspacio(){
        return personal.size() < 5;
    }

    public int IdUsuarioExiste(int id){
        int contador = 0;
        for (PersonalDTO tmpPersonal : personal){
            if(tmpPersonal.getId() == id){
                
                return contador;
            }
            contador ++;
        }
        return -1;
    }

    public boolean credencialUsuarioExiste(CredencialDTO objCredencial){
        String tmpUsuario=objCredencial.getUsuario();
        int contador = 0;
        while(contador < personal.size()){
            if(personal.get(contador).getUsuario()==tmpUsuario){
                return true;
            }
            contador++;
        }
        return false;
    }

    public PersonalDTO ocupacionBuscadaCredenciales(CredencialDTO objCredencial){
        if(usuarioExiste(objCredencial)){
            return null;
        }
        String tmpUsuario=objCredencial.getUsuario();
        for (PersonalDTO personalDTO : personal) {
            if(personalDTO.getUsuario().equals(tmpUsuario)){
                PersonalDTO retorno=new PersonalDTO(personalDTO.getTipo_id(), personalDTO.getId(), personalDTO.getNombreCompleto(), personalDTO.getOcupacion(), personalDTO.getUsuario(), personalDTO.getClave());
                return retorno;
            }
        }
        return null;
    }

    public boolean usuarioExiste(CredencialDTO objCredencial){
        String tmpUsuario=objCredencial.getUsuario();
        int contador = 0;
        while(contador < personal.size()){

            if(personal.get(contador).getUsuario()==tmpUsuario){
                return true;
            }
            contador++;
        }
        return false;
    }
}
