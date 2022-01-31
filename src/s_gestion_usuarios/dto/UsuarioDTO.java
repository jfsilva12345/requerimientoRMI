package s_gestion_usuarios.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable{
    private String nombreCompleto;
    private int id;
    private String dependencia;
    private String tipoUsuario;
    private String fechaIngreso;
    private String patologia;
    private String usuario;
    private String clave;
    
    public UsuarioDTO(String nombreCompleto, int id, String dependencia, String tipoUsuario, String fechaIngreso,
            String patologia, String usuario, String clave) {
        this.nombreCompleto = nombreCompleto;
        this.id = id;
        this.dependencia = dependencia;
        this.tipoUsuario = tipoUsuario;
        this.fechaIngreso = fechaIngreso;
        this.patologia = patologia;
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

    public String getUsuario() {
        return usuario;
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
