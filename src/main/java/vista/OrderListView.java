package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class OrderListView {

	@ManagedProperty("#{temperaturaCiudadService}")
	private TemperaturaCiudadService service;

	private List<Object> ciudades;
	private Object ciudad;

	@PostConstruct
	public void init() {
		//ciudades = service.getTemperaturaCiudad();
		
	}
	
	
	
	

	public Object getCiudad() {
		return ciudad;
	}





	public void setCiudad(Object ciudad) {
		this.ciudad = ciudad;
	}





	public TemperaturaCiudadService getService() {
		return service;
	}

	public void setService(TemperaturaCiudadService service) {
		this.service = service;
	}

	public List<Object> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Object> ciudades) {
		this.ciudades = ciudades;
	}

}