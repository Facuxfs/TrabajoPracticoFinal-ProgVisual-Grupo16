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
import javax.validation.constraints.Positive;

import ar.edu.unju.fi.edu.entity.Empleador;

@Entity
@Table(name = "oferta_laboral")
public class OfertaLaboral implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ol_id")
	private long id;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Positive(message = "El numero de cuit debe ser positivo")
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
	private String tareas_princiaples;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Positive(message = "El numero de jornada debe ser positivo")
	@Column(name = "ol_jornada")
	private int jornada;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "ol_requisitos")
	private String requisitos;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Positive(message = "El salario debe ser positivo")
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
		
	}
	
	/*
	 * Constructor parametrizado
	 */
	public OfertaLaboral(
			@NotEmpty(message = "El campo no puede estar vacio") @Positive(message = "El numero de cuit debe ser positivo") int cant_vacantes,
			@NotEmpty(message = "El campo no puede estar vacio") String puesto_req,
			@NotEmpty(message = "El campo no puede estar vacio") String disp_horaria,
			@NotEmpty(message = "El campo no puede estar vacio") String tareas_princiaples,
			@NotEmpty(message = "El campo no puede estar vacio") @Positive(message = "El numero de jornada debe ser positivo") int jornada,
			@NotEmpty(message = "El campo no puede estar vacio") String requisitos,
			@NotEmpty(message = "El campo no puede estar vacio") @Positive(message = "El salario debe ser positivo") Double salario,
			@NotEmpty(message = "El campo no puede estar vacio") String beneficios, boolean disponible) {
		super();
		this.cant_vacantes = cant_vacantes;
		this.puesto_req = puesto_req;
		this.disp_horaria = disp_horaria;
		this.tareas_princiaples = tareas_princiaples;
		this.jornada = jornada;
		this.requisitos = requisitos;
		this.salario = salario;
		this.beneficios = beneficios;
		this.disponible = disponible;
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

	public String getTareas_princiaples() {
		return tareas_princiaples;
	}

	public void setTareas_princiaples(String tareas_princiaples) {
		this.tareas_princiaples = tareas_princiaples;
	}

	public int getJornada() {
		return jornada;
	}

	public void setJornada(int jornada) {
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

	@Override
	public String toString() {
		return "OfertaLaboral [id=" + id + ", cant_vacantes=" + cant_vacantes + ", puesto_req=" + puesto_req
				+ ", disp_horaria=" + disp_horaria + ", tareas_princiaples=" + tareas_princiaples + ", jornada="
				+ jornada + ", requisitos=" + requisitos + ", salario=" + salario + ", beneficios=" + beneficios
				+ ", disponible=" + disponible + "]";
	}
}