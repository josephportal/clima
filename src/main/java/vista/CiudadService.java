package vista;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import dao.CiudadDao;
import modelo.Ciudad;

@ManagedBean(name = "ciudadService", eager = true)
@ApplicationScoped
public class CiudadService {
	
	private List<String> ciudades = new ArrayList<>();

	@PostConstruct
	public void init() {
		CiudadDao daoc = new CiudadDao();
		List<Ciudad> ciudad = daoc.mostrarCiudad();
		for (int i = 0; i < ciudad.size(); i++) {
			ciudades.add(i, ciudad.get(i).getNombre());
		}
	}

	public List<String> getCiudadesService() {
		return ciudades;
	}
}
