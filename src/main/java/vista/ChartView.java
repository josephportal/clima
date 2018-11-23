package vista;

import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.chart.MeterGaugeChartModel;

import modelo.Clima;
import vista.ClimaService;

@ManagedBean
public class ChartView extends Thread implements Serializable {

	private static final long serialVersionUID = 1L;
	private MeterGaugeChartModel meterGaugeModel1;
	
	private Clima cl;
	private String opc;
	private int valor;
	
	@ManagedProperty("#{climaService}")
	private ClimaService service;
	
	@PostConstruct
	public void init() {
		
		cl = service.getClimaService();
		opc = service.getOpcion();
		
		if (opc.equals("t")) {
			valor = (int) cl.getTemperatura();
		}
		if(opc.equals("h")) {
			valor = (int) cl.getHumedad();
		}
		
		createMeterGaugeModels();
	}

	public MeterGaugeChartModel getMeterGaugeModel1() {
		return meterGaugeModel1;
	}

	@SuppressWarnings("serial")
	private MeterGaugeChartModel initMeterGaugeModel() {
		List<Number> intervals = new ArrayList<Number>() {
			{
				
				if (opc.equals("t")) {
					add(50);
				}else {
					add(100);
				}
				
			}
		};

		return new MeterGaugeChartModel(valor, intervals);
	}

	private void createMeterGaugeModels() {
		meterGaugeModel1 = initMeterGaugeModel();
		
		if (opc.equals("t")) {
			meterGaugeModel1.setGaugeLabel("Temp: "+valor+"°");
		}else {
			meterGaugeModel1.setGaugeLabel("Humedad: "+valor);
		}
		
	}

	public ClimaService getService() {
		return service;
	}

	public void setService(ClimaService service) {
		this.service = service;
	}

}