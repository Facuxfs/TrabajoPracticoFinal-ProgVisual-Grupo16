package ar.edu.unju.fi.edu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "CVs")
public class Curriculum implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="cur_id")
	private Long id;
	
	@NotEmpty
	@Column(name = "exp_lab")
	private String experienciaLaboral;
	
	@NotEmpty
	@Column(name = "edu")
	private String educacion;
	
	@NotEmpty
	@Column(name = "idioma")
	private String idiomas;
	
	@NotEmpty
	@Column(name = "con_inf")
	private String conocimientosInformaticos;
	
	@NotEmpty
	@Column(name="datos_of")
	private String datosOficiales;
	
	@Column(name = "estado")
	private Boolean estado;

	public Boolean getEstado() {
		return estado;
	}



	public void setEstado(Boolean estado) {
		this.estado = estado;
	}




	
	
	public Curriculum(Long id, @NotEmpty String experienciaLaboral, @NotEmpty String educacion,
			@NotEmpty String idiomas, @NotEmpty String conocimientosInformaticos, @NotEmpty String datosOficiales,
			Boolean estado) {
		super();
		this.id = id;
		this.experienciaLaboral = experienciaLaboral;
		this.educacion = educacion;
		this.idiomas = idiomas;
		this.conocimientosInformaticos = conocimientosInformaticos;
		this.datosOficiales = datosOficiales;
		this.estado = estado;
	}



	public Curriculum() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExperienciaLaboral() {
		return experienciaLaboral;
	}

	public void setExperienciaLaboral(String experienciaLaboral) {
		this.experienciaLaboral = experienciaLaboral;
	}

	public String getEducacion() {
		return educacion;
	}

	public void setEducacion(String educacion) {
		this.educacion = educacion;
	}

	public String getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	public String getConocimientosInformaticos() {
		return conocimientosInformaticos;
	}

	public void setConocimientosInformaticos(String conocimientosInformaticos) {
		this.conocimientosInformaticos = conocimientosInformaticos;
	}

	public String getDatosOficiales() {
		return datosOficiales;
	}

	public void setDatosOficiales(String datosOficiales) {
		this.datosOficiales = datosOficiales;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Curriculum [id=" + id + ", experienciaLaboral=" + experienciaLaboral + ", educacion=" + educacion
				+ ", idiomas=" + idiomas + ", conocimientosInformaticos=" + conocimientosInformaticos
				+ ", datosOficiales=" + datosOficiales + "]";
	}
	
	
}
