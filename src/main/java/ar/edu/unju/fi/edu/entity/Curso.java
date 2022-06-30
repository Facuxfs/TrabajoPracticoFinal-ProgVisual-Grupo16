package ar.edu.unju.fi.edu.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "curso")
public class Curso implements Serializable{

	private static final long serialVersionUID = 6298686649297630634L;

	@Positive(message = "El numero de cuit debe ser positivo")
	@Min(value = 1000, message = "El Codigo debe contener como minimo 4 digitos")
	@Id
	@Column(name = "c_codigo")
	private long codigo;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "c_titulo")
	private String titulo;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "e_categoria")
	private String categoria;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "Ingrese una fecha valida")
	@Column(name = "c_fechai")
	private LocalDate fecha_inicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "Ingrese una fecha valida")
	@Column(name = "c_fechaf")
	private LocalDate fecha_fin;
	
	@Positive(message = "El numero de cuit debe ser positivo")
	@Min(value = 1, message = "La cantidad de horas debe contener como minimo 2 digitos")
	@Column(name = "c_cant_horas")
	private int cant_horas;
	
	@Column(name = "c_modalidad")
	private String modalidad;
	
	private boolean estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "e_cuit")
	private Empleador profesor;

	/*
	 * Constructor no parametrizado
	 */
	public Curso() {
		this.estado = true;
	}
	
	/*
	 * Constructor con parametro Empleador
	 */
	public Curso(Empleador profesor) {
		this.estado = true;
		this.profesor = profesor;
	}

	/*
	 * Constructor parametrizado 
	 */
	public Curso(
			@Positive(message = "El numero de cuit debe ser positivo") @Min(value = 1000, message = "El Codigo debe contener como minimo 4 digitos") long codigo,
			@NotEmpty(message = "El campo no puede estar vacio") String titulo,
			@NotEmpty(message = "El campo no puede estar vacio") String categoria,
			@PastOrPresent(message = "Ingrese una fecha valida") LocalDate fecha_inicio,
			@PastOrPresent(message = "Ingrese una fecha valida") LocalDate fecha_fin,
			@Positive(message = "El numero de cuit debe ser positivo") @Min(value = 1, message = "La cantidad de horas debe contener como minimo 2 digitos") int cant_horas,
			String modalidad) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.categoria = categoria;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.cant_horas = cant_horas;
		this.modalidad = modalidad;
		
		this.estado = true;
	}

	/*
	 * Metodos accesores
	 */
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public LocalDate getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(LocalDate fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public LocalDate getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(LocalDate fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public int getCant_horas() {
		return cant_horas;
	}

	public void setCant_horas(int cant_horas) {
		this.cant_horas = cant_horas;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public Empleador getProfesor() {
		return profesor;
	}

	public void setProfesor(Empleador profesor) {
		this.profesor = profesor;
	}

	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", titulo=" + titulo + ", categoria=" + categoria + ", fecha_inicio="
				+ fecha_inicio + ", fecha_fin=" + fecha_fin + ", cant_horas=" + cant_horas + ", modalidad=" + modalidad
				+ "]";
	}
}