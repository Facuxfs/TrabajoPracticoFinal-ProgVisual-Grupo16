package ar.edu.unju.fi.edu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "oferta_laboral")
public class OfertaLaboral implements Serializable{

	private static final long serialVersionUID = -3933069521430583169L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ol_id")
	private long id;
	
	@PositiveOrZero(message = "La cantidad de vacantes debe ser positiva")
	@Column(name = "ol_nvacantes")
	private int cant_vacantes;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "ol_puestoreq")
	private String puesto_req;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "ol_horario")
	private String disp_horaria;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "ol_tareas")
	private String tareas_p;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "ol_jornada")
	private String jornada;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "ol_requisitos")
	private String requisitos;
	
	@PositiveOrZero(message = "El salario debe ser positivo")
	@Column(name = "ol_salario")
	private Double salario;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "ol_beneficios")
	private String beneficios;
	
	@Column(name = "disp")
	private boolean disponible;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "e_cuit")
	private Empleador contacto;	

	/*
	 * Constructor no parametrizado
	 */
	public OfertaLaboral() {
		this.disponible = true;
	}
	
	/*
	 * Constructor con parametro Empleador
	 */
	public OfertaLaboral(Empleador contacto) {
		this.disponible = true;
		this.contacto = contacto;
	}
	
	/*
	 * Constructor parametrizado
	 */
	public OfertaLaboral(
			@PositiveOrZero(message = "El numero de cuit debe ser positivo") int cant_vacantes,
			@NotEmpty(message = "El campo no puede estar vacio") String puesto_req,
			@NotEmpty(message = "El campo no puede estar vacio") String disp_horaria,
			@NotEmpty(message = "El campo no puede estar vacio") String tareas_p,
			@NotEmpty(message = "El campo no puede estar vacio") String jornada,
			@NotEmpty(message = "El campo no puede estar vacio") String requisitos,
			@PositiveOrZero(message = "El salario debe ser positivo") Double salario,
			@NotEmpty(message = "El campo no puede estar vacio") String beneficios) {
		super();
		this.cant_vacantes = cant_vacantes;
		this.puesto_req = puesto_req;
		this.disp_horaria = disp_horaria;
		this.tareas_p = tareas_p;
		this.jornada = jornada;
		this.requisitos = requisitos;
		this.salario = salario;
		this.beneficios = beneficios;
		
		this.disponible = true;
	}
	
	/*
	 * Metodos accesores
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCant_vacantes() {
		return cant_vacantes;
	}

	public void setCant_vacantes(int cant_vacantes) {
		this.cant_vacantes = cant_vacantes;
	}

	public String getPuesto_req() {
		return puesto_req;
	}

	public void setPuesto_req(String puesto_req) {
		this.puesto_req = puesto_req;
	}

	public String getDisp_horaria() {
		return disp_horaria;
	}

	public void setDisp_horaria(String disp_horaria) {
		this.disp_horaria = disp_horaria;
	}

	public String getTareas_p() {
		return tareas_p;
	}

	public void setTareas_p(String tareas_princiaples) {
		this.tareas_p = tareas_princiaples;
	}

	public String getJornada() {
		return jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	public Empleador getContacto() {
		return contacto;
	}

	public void setContacto(Empleador contacto) {
		this.contacto = contacto;
	}

	@Override
	public String toString() {
		return "OfertaLaboral [id=" + id + ", cant_vacantes=" + cant_vacantes + ", puesto_req=" + puesto_req
				+ ", disp_horaria=" + disp_horaria + ", tareas_p=" + tareas_p + ", jornada="
				+ jornada + ", requisitos=" + requisitos + ", salario=" + salario + ", beneficios=" + beneficios
				+ ", disponible=" + disponible + "]";
	}
}