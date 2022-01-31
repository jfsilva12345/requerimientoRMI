package s_gestion_usuarios.dto;

import java.io.Serializable;

public class CredencialDTO implements Serializable{


    private String usuario;
    private String clave;

    public CredencialDTO(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}


    
}