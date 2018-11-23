package vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import modelo.Ciudad;
import vista.CiudadService;

@ManagedBean
@ViewScoped
public class SelectOneMenuView implements Serializable {

	private static final long serialVersionUID = 1L;
	private Ciudad ciudad;
	private List<String> ciudades = new ArrayList<>();

	@ManagedProperty("#{ciudadService}")
	private CiudadService service;

	@PostConstruct
	public void init() {
		ciudades = service.getCiudadesService();
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public List<String> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<String> ciudades) {
		this.ciudades = ciudades;
	}

	public CiudadService getService() {
		return service;
	}

	public void setService(CiudadService service) {
		this.service = service;
	}

}