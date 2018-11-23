package vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.SelectEvent;

import dao.ClimaDao;
import modelo.Clima;

@ManagedBean(name = "climaService", eager = true)
@ApplicationScoped
public class ClimaService {

	private Clima clima;
	private List<Object[]> promTemp;
	
	private String ciudad = "Bogota";
	private String fecha = "2018/10/12";
	private String opcion="";

	public void onCitySelect() {
		System.out.println("SELECIONADO:::::::::::::::: "+ciudad);
	}
	
	public void onDateSelect(SelectEvent event) {
		Date date = (Date) event.getObject();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        fecha = format.format(date);
        System.out.println("GRAFICA:::::::FECHA:::::::"+fecha);
		System.out.println("GRAFICA:::::::CIUDAD::::::"+ciudad);
		ClimaDao dao = new ClimaDao();
		promTemp = dao.consultarClima(ciudad, fecha);
	}
	
	public void ciudadTemp() {
		opcion = "t";
        System.out.println("VERTEMP:::::::FECHA:::::::"+fecha);
		System.out.println("VERTEMP:::::::CIUDAD::::::"+ciudad);
		init();
	}
	
	public void ciudadHum() {
		opcion = "h";
        System.out.println("VERHUM:::::::FECHA:::::::"+fecha);
		System.out.println("VERHUM:::::::CIUDAD::::::"+ciudad);
		init();
	}

	@PostConstruct
	public void init() {
		//para verTemperatura y Humedad actual
		ClimaDao dao = new ClimaDao();
		clima = dao.consultarClimaActual(ciudad);
		System.out.println("OLEEE: "+clima.getIdclima());
		System.out.println("OLEEE: "+clima.getTemperatura());
		
		
		//para ver la grafica
		promTemp = new ArrayList<>();
		
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getOpcion() {
		return opcion;
	}
	
	public Clima getClimaService() {
		return clima;
	}

	public List<Object[]> getTempPromService() {
		return promTemp;
	}
}
