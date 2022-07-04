package ar.edu.unju.fi.edu.entity;

import java.io.Serializable;


import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ciudadano")
public class Ciudadano implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "El campo no puede estar vacio")
	@Size(min = 4, max = 20, message = "Ingrese su nombre y apellido")
	@Column(name = "nombre")
	private String nombre;

	@Positive(message = "El numero de dni debe ser positivo")
	@Min(value = 1000000, message = "Ingrese un dni valido")
	@Column(name = "ciudadano_dni")
	private int dni;

	@Column(name = "estado")
	private Boolean estado;

	@NotEmpty(message = "El campo no puede estar vacio")
	@Email
	@Column(name = "e_email")
	private String email;

	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "est_civil")
	private String estadoCivil;

	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "prov")
	private String provincia;

	@Min(value = 100000, message = "El numero de telefono debe contener como minimo 6 digitos")
	@Positive(message = "El numero de telefono debe ser positivo")
	@Column(name = "e_tel")
	private Long telefono;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "Ingrese una fecha valida")
	@Column(name = "fecha_nac")
	private LocalDate fechaNacimineto;

	@NotEmpty(message = "El campo no puede estar vacio")
	@Size(min = 4, max = 20, message = "La contraseña debe tener entre 4 a 20 caracteres")
	@Column(name = "contra")
	private String contrasenia;

	@OneToOne(mappedBy = "ciudadano", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Curriculum cv;

	@ManyToMany(mappedBy = "postulantes")
	private List<OfertaLaboral> postulaciones;
  
	@ManyToMany(mappedBy = "inscriptos")
	private List<Curso> inscripciones;
	
	/*
	 * Constructor no parametrizado
	 */
	public Ciudadano() {
		
	}
	
	/*
	 * Constructor parametrizado
	 */
	public Ciudadano(Long id,
			@NotEmpty @Size(min = 4, max = 20, message = "Ingrese su nombre y apellido") String nombre,
			@Min(value = 1000000, message = "Ingrese un dni valido") int dni, Boolean estado,
			@NotEmpty @Email String email, @NotEmpty String estadoCivil, @NotNull String provincia,
			@Min(value = 100000000, message = "Ingrese un numero telefonico valido") Long telefono,
			LocalDate fechaNacimineto,
			@NotNull @Size(min = 4, max = 20, message = "La contraseña debe tener entre 4 a 20 caracteres") String contrasenia,
			Curriculum cv) {
		super();

		this.nombre = nombre;
		this.dni = dni;
		this.estado = estado;
		this.email = email;
		this.estadoCivil = estadoCivil;
		this.provincia = provincia;
		this.telefono = telefono;
		this.fechaNacimineto = fechaNacimineto;
		this.contrasenia = contrasenia;
		this.cv = cv;
	}
	
	public boolean getEdad() {
		LocalDate fhoy = LocalDate.now();
		
		Period edad = Period.between(this.fechaNacimineto, fhoy);
		
		return (edad.getYears() >= 18);
	}
	
	/*  
	 * Metodos accesores
	 */
	public List<Curso> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(List<Curso> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public List<OfertaLaboral> getPostulaciones() {
		return postulaciones;
	}

	public void setPostulaciones(List<OfertaLaboral> postulaciones) {
		this.postulaciones = postulaciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFechaNacimineto() {
		return fechaNacimineto;
	}

	public void setFechaNacimineto(LocalDate fechaNacimineto) {
		this.fechaNacimineto = fechaNacimineto;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Curriculum getCv() {
		return cv;
	}

	public void setCv(Curriculum cv) {
		this.cv = cv;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Ciudadano [ email=" + email + ", estadoCivil=" + estadoCivil + ", provincia=" + provincia
				+ ", telefono=" + telefono + ", fechaNacimineto=" + fechaNacimineto + ", contrasenia=" + contrasenia
				+ ", cv=" + cv + "]";
	}
}