package ar.edu.unju.fi.edu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	
	//@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "exp_lab")
	private String experienciaLaboral;
	
	//@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "edu")
	private String educacion;
	
	//@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "idioma")
	private String idiomas;
	
	//@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "con_inf")
	private String conocimientosInformaticos;
	
	//@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name="datos_of")
	private String datosOficiales;
	
	@Column(name = "estado")
	private Boolean estado;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ciudadano_id")
	private Ciudadano ciudadano;

	/*
	 * Constructor no parametrizado
	 */
	public Curriculum() {
		
	}
	
	/*
	 * Constructor parametrizado
	 */
	public Curriculum(@NotEmpty(message = "El campo no puede estar vacio") String experienciaLaboral,
			@NotEmpty(message = "El campo no puede estar vacio") String educacion,
			@NotEmpty(message = "El campo no puede estar vacio") String idiomas,
			@NotEmpty(message = "El campo no puede estar vacio") String conocimientosInformaticos,
			@NotEmpty(message = "El campo no puede estar vacio") String datosOficiales) {
		super();
		this.experienciaLaboral = experienciaLaboral;
		this.educacion = educacion;
		this.idiomas = idiomas;
		this.conocimientosInformaticos = conocimientosInformaticos;
		this.datosOficiales = datosOficiales;
	}

	/*
	 * Metodos accesores
	 */
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
	
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	public Ciudadano getCiudadano() {
		return ciudadano;
	}

	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}

	@Override
	public String toString() {
		return "Curriculum [id=" + id + ", experienciaLaboral=" + experienciaLaboral + ", educacion=" + educacion
				+ ", idiomas=" + idiomas + ", conocimientosInformaticos=" + conocimientosInformaticos
				+ ", datosOficiales=" + datosOficiales + "]";
	}	
}