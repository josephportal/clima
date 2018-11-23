package vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import controlador.ClimaControlador;
import modelo.Regresion;

@ManagedBean(name = "temperaturaCiudadService", eager = true)
@ApplicationScoped
public class TemperaturaCiudadService {

	private List<Object[]> datos;

	@PostConstruct
	public void init() {
		List<Object[]> grad = new ArrayList<>();
		Object[] gra;
		
		ClimaControlador dao = new ClimaControlador();
		List<Object[]> data = dao.consultarTempxCiudad(5);
		ClimaControlador daos;
		for (int k = 0; k < 5; k++) {
			daos = new ClimaControlador();
			List<Object[]> clim = daos.consultarTemperatura("Bogota", "2018-11-11", "2018-11-12");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			double[] fechax = new double[(clim.size() / 2)];
			double[] fechay = new double[(clim.size() / 2)];
			int a = 0, b = 0;

			for (int i = 0; i < clim.size(); i++) {
				System.out.println(clim.get(i)[0] + "\t" + clim.get(i)[1] + " ------ " + i);
				if (format.format((Date) clim.get(i)[1]).equals(format.format((Date) clim.get(0)[1]))) {
					fechax[a] = (float) clim.get(i)[0];
					a++;
				} else {
					fechay[b] = (float) clim.get(i)[0];
					b++;
				}
			}

			System.out.println(fechax.length + " - " + fechay.length);
			Regresion regresion = new Regresion(fechax, fechay);
			System.out.println("Indice de correlación: " + regresion.correlacion());
			System.out.println((double) Math.round(regresion.correlacion() * 10d) / 10d);
			gra = new Object[1];
			gra[0] = ((double) Math.round(regresion.correlacion() * 10d) / 10d)*k;
			grad.add(gra);
		}
		
		
		Object[] da;
		datos = new ArrayList<>();
		System.out.println(data.size());
		for (int i = 0; i < data.size(); i++) {
			da = new Object[3];
			da[0] = data.get(i)[0];
			da[1] = data.get(i)[1];
			da[2] = grad.get(i)[0];
			datos.add(da);
		}
		
		for (int i = 0; i < datos.size(); i++) {
			System.out.println(datos.get(i)[0]+" - "+datos.get(i)[1]+" - "+datos.get(i)[2]);
		}
		
	}


	public List<Object[]> getDatos() {
		return datos;
	}

	public void setDatos(List<Object[]> datos) {
		this.datos = datos;
	}

}
