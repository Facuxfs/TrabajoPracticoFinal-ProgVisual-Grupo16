package ar.edu.unju.fi.edu.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "empleador")
public class Empleador implements Serializable{
	
	private static final long serialVersionUID = -6326069709113079285L;

	@Positive(message = "El numero de cuit debe ser positivo")
	@Min(value = 1000, message = "El N° de Cuit debe contener como minimo 4 caracteres")
	@Id
	@Column(name = "e_cuit")
	private long cuit;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Size(min = 4, max = 20, message = "La contraseña debe estar entre")
	@Column(name = "e_password")
	private String contrasenia;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "e_raz_social")
	private String razon_social;
	
	@NotEmpty
	@Column(name = "e_nomcom")
	private String nombre_comercial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "Ingrese una fecha valida")
	@Column(name = "e_finicio")
	private LocalDate fecha_inicio;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Email
	@Column(name = "e_email")
	private String email;
	
	@Min(value = 100000, message = "El numero de telefono debe contener como minimo 6 digitos")
	@Positive(message = "El numero de telefono debe ser positivo")
	@Column(name = "e_tel")
	private long telefono;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "e_domicilio")
	private String domicilio;
	
	@Column(name = "e_prov")
	private String provincia;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "e_pweb")
	private String pagina_web;
	
	@NotEmpty(message = "El campo no puede estar vacio")
	@Column(name = "e_descripcion")
	private String descripcion;
	
	@Column
	private boolean estado;

	@OneToMany(mappedBy = "contacto", cascade = CascadeType.ALL)
	private List<OfertaLaboral> ofertas = new ArrayList<OfertaLaboral>();

	/*
	 * Constructor no parametrizado
	 */
	public Empleador() {
		this.estado = true;
	}
	
	/*
	 * Constructor parametrizado
	 */
	public Empleador(
			@Positive(message = "El numero de cuit debe ser positivo") @Min(value = 1000, message = "El N° de Cuit debe contener como minimo 4 caracteres") long cuit,
			@NotEmpty(message = "El campo no puede estar vacio") @Size(min = 4, max = 20, message = "La contraseña debe estar entre") String contrasenia,
			@NotEmpty(message = "El campo no puede estar vacio") String razon_social, @NotEmpty String nombre_comercial,
			@PastOrPresent(message = "Ingrese una fecha valida") LocalDate fecha_inicio,
			@NotEmpty(message = "El campo no puede estar vacio") @Email String email,
			@Min(value = 100000, message = "El numero de telefono debe contener como minimo 6 digitos") @Positive(message = "El numero de telefono debe ser positivo") long telefono,
			@NotEmpty(message = "El campo no puede estar vacio") String domicilio,
			 String provincia,
			@NotEmpty(message = "El campo no puede estar vacio") String pagina_web,
			@NotEmpty(message = "El campo no puede estar vacio") String descripcion) {
		super();
		this.cuit = cuit;
		this.contrasenia = contrasenia;
		this.razon_social = razon_social;
		this.nombre_comercial = nombre_comercial;
		this.fecha_inicio = fecha_inicio;
		this.email = email;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.provincia = provincia;
		this.pagina_web = pagina_web;
		this.descripcion = descripcion;
	}

	/*
	 * Metodos accesores
	 */
	public long getCuit() {
		return cuit;
	}	

	public void setCuit(long cuit) {
		this.cuit = cuit;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getNombre_comercial() {
		return nombre_comercial;
	}

	public void setNombre_comercial(String nombre_comercial) {
		this.nombre_comercial = nombre_comercial;
	}

	public LocalDate getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(LocalDate fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPagina_web() {
		return pagina_web;
	}

	public void setPagina_web(String pagina_web) {
		this.pagina_web = pagina_web;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Empleador [cuit=" + cuit + ", contrasenia=" + contrasenia + ", razon_social=" + razon_social
				+ ", nombre_comercial=" + nombre_comercial + ", fecha_inicio=" + fecha_inicio + ", email=" + email
				+ ", telefono=" + telefono + ", domicilio=" + domicilio + ", provincia=" + provincia + ", pagina_web="
				+ pagina_web + ", descripcion=" + descripcion + "]";
	}
}