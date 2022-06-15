package ar.edu.unju.fi.edu.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Ciudadanos")
public class Ciudadano implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="cur_id")
	private Long id;
	
	@NotEmpty
	@Size(min = 4, max = 20, message = "Ingrese su nombre y apellido")
	private String nombre;


	@Min(value = 1000000, message = "Ingrese un dni valido")
	private int dni;
	
	@Column(name = "Ciud_estado")
	private Boolean estado;

	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	@Column(name = "est_civil")
	private String estadoCivil;
	
	@NotNull
	@Column(name = "prov")
	private String provincia;
	
	@Min(value = 100000000, message = "Ingrese un numero telefonico valido")
	@Column
	private Long telefono;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "fecha_nac")
	private LocalDate fechaNacimineto;
	
	@NotNull
	@Size(min = 4, max = 20, message = "La contraseña debe tener entre 4 a 20 caracteres")
	@Column(name = "contra")
	private String contrasenia;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cur_id")
	private Curriculum cv;


	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

	public Ciudadano(Long id,
			@NotEmpty @Size(min = 4, max = 20, message = "Ingrese su nombre y apellido") String nombre,
			@Min(value = 1000000, message = "Ingrese un dni valido") int dni, Boolean estado,
			@NotEmpty @Email String email, @NotEmpty String estadoCivil, @NotNull String provincia,
			@Min(value = 100000000, message = "Ingrese un numero telefonico valido") Long telefono,
			LocalDate fechaNacimineto,
			@NotNull @Size(min = 4, max = 20, message = "La contraseña debe tener entre 4 a 20 caracteres") String contrasenia,
			Curriculum cv) {
		super();
		this.id = id;
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
	
	
	public Ciudadano() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "Ciudadano [id=" + id + ", email=" + email + ", estadoCivil=" + estadoCivil + ", provincia=" + provincia
				+ ", telefono=" + telefono + ", fechaNacimineto=" + fechaNacimineto + ", contrasenia=" + contrasenia
				+ ", cv=" + cv + "]";
	} 
	
}
