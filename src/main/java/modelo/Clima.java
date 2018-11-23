package modelo;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@NamedQuery(name="Clima.findAll", query="SELECT c FROM Clima c")
public class Clima {
	
	@Id
	@SequenceGenerator(name="CLIMA_IDCLIMA_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIMA_IDCLIMA_GENERATOR")
	private Integer idclima;

	private Boolean estado;

	@Column(name="fecha_hora")
	private Timestamp fechaHora;

	private float humedad;

	private float temperatura;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name="idciudad")
	private Ciudad ciudad;

	public Clima() {
	}

	public Integer getIdclima() {
		return this.idclima;
	}

	public void setIdclima(Integer idclima) {
		this.idclima = idclima;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Timestamp getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	public float getHumedad() {
		return this.humedad;
	}

	public void setHumedad(float humedad) {
		this.humedad = humedad;
	}

	public float getTemperatura() {
		return this.temperatura;
	}

	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

}