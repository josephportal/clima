package vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.*;


import vista.ClimaService;

@ManagedBean
public class GraficaView  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private LineChartModel lineModel1;
	private LineChartModel lineModel2;
	private LineChartModel zoomModel;
	private CartesianChartModel combinedModel;
	private CartesianChartModel fillToZero;
	private LineChartModel areaModel;
	private BarChartModel barModel;
	private HorizontalBarChartModel horizontalBarModel;
	private PieChartModel pieModel1;
	private PieChartModel pieModel2;
	private DonutChartModel donutModel1;
	private DonutChartModel donutModel2;
	private MeterGaugeChartModel meterGaugeModel1;
	private MeterGaugeChartModel meterGaugeModel2;
	private BubbleChartModel bubbleModel1;
	private BubbleChartModel bubbleModel2;
	private OhlcChartModel ohlcModel;
	private OhlcChartModel ohlcModel2;
	private PieChartModel livePieModel;
	private LineChartModel animatedModel1;
	private BarChartModel animatedModel2;
	private LineChartModel multiAxisModel;
	private LineChartModel dateModel;
	private List<Object[]> temp;


	@ManagedProperty("#{climaService}")
	private ClimaService servicio;
	
	@PostConstruct
	public void init() {
		temp = servicio.getTempPromService();

		createLineModels();
		
		createPieModels();
		createDonutModels();
		createBubbleModels();
		createOhlcModels();
		createFillToZero();
		createMeterGaugeModels();
		
		createAnimatedModels();
		
		createDateModel();
		temp.clear();
	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	public LineChartModel getLineModel2() {
		return lineModel2;
	}

	public LineChartModel getZoomModel() {
		return zoomModel;
	}

	public CartesianChartModel getCombinedModel() {
		return combinedModel;
	}

	public CartesianChartModel getAreaModel() {
		return areaModel;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public MeterGaugeChartModel getMeterGaugeModel1() {
		return meterGaugeModel1;
	}

	public MeterGaugeChartModel getMeterGaugeModel2() {
		return meterGaugeModel2;
	}

	public DonutChartModel getDonutModel1() {
		return donutModel1;
	}

	public DonutChartModel getDonutModel2() {
		return donutModel2;
	}

	public CartesianChartModel getFillToZero() {
		return fillToZero;
	}

	public BubbleChartModel getBubbleModel1() {
		return bubbleModel1;
	}

	public BubbleChartModel getBubbleModel2() {
		return bubbleModel2;
	}

	public OhlcChartModel getOhlcModel() {
		return ohlcModel;
	}

	public OhlcChartModel getOhlcModel2() {
		return ohlcModel2;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}

	public LineChartModel getAnimatedModel1() {
		return animatedModel1;
	}

	public BarChartModel getAnimatedModel2() {
		return animatedModel2;
	}

	public LineChartModel getMultiAxisModel() {
		return multiAxisModel;
	}

	public LineChartModel getDateModel() {
		return dateModel;
	}
	


	public List<Object[]> getTemp() {
		return temp;
	}

	public void setTemp(List<Object[]> temp) {
		this.temp = temp;
	}

	public ClimaService getServicio() {
		return servicio;
	}

	public void setServicio(ClimaService servicio) {
		this.servicio = servicio;
	}

	public PieChartModel getLivePieModel() {
		int random1 = (int) (Math.random() * 1000);
		int random2 = (int) (Math.random() * 1000);

		livePieModel.getData().put("Candidate 1", random1);
		livePieModel.getData().put("Candidate 2", random2);

		livePieModel.setTitle("Votes");
		livePieModel.setLegendPosition("ne");

		return livePieModel;
	}


	private void createLineModels() {
		lineModel1 = initLinearModel();
		lineModel1.setTitle("Linear Chart");
		lineModel1.setLegendPosition("e");
		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);

		
		zoomModel = initLinearModel();
		zoomModel.setTitle("Zoom");
		zoomModel.setZoom(true);
		zoomModel.setLegendPosition("e");
		yAxis = zoomModel.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);
	}







	private void createOhlcModels() {
		createOhlcModel1();
		
	}

	private void createOhlcModel1() {
		ohlcModel = new OhlcChartModel();

		ohlcModel.add(new OhlcChartSeries(2007, 143.82, 144.56, 136.04, 136.97));
		ohlcModel.add(new OhlcChartSeries(2008, 138.7, 139.68, 135.18, 135.4));
		ohlcModel.add(new OhlcChartSeries(2009, 143.46, 144.66, 139.79, 140.02));
		ohlcModel.add(new OhlcChartSeries(2010, 140.67, 143.56, 132.88, 142.44));
		ohlcModel.add(new OhlcChartSeries(2011, 136.01, 139.5, 134.53, 139.48));
		ohlcModel.add(new OhlcChartSeries(2012, 124.76, 135.9, 124.55, 135.81));
		ohlcModel.add(new OhlcChartSeries(2013, 123.73, 129.31, 121.57, 122.5));

		ohlcModel.setTitle("OHLC Chart");
		ohlcModel.getAxis(AxisType.X).setLabel("Year");
		ohlcModel.getAxis(AxisType.Y).setLabel("Price Change $K/Unit");
	}

	private void createBubbleModels() {
		bubbleModel1 = initBubbleModel();
		bubbleModel1.setTitle("Bubble Chart");
		bubbleModel1.getAxis(AxisType.X).setLabel("Price");
		Axis yAxis = bubbleModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(250);
		yAxis.setLabel("Labels");

	}

	private BubbleChartModel initBubbleModel() {
		BubbleChartModel model = new BubbleChartModel();

		model.add(new BubbleChartSeries("Acura", 70, 183, 55));
		model.add(new BubbleChartSeries("Alfa Romeo", 45, 92, 36));
		model.add(new BubbleChartSeries("AM General", 24, 104, 40));
		model.add(new BubbleChartSeries("Bugatti", 50, 123, 60));
		model.add(new BubbleChartSeries("BMW", 15, 89, 25));
		model.add(new BubbleChartSeries("Audi", 40, 180, 80));
		model.add(new BubbleChartSeries("Aston Martin", 70, 70, 48));

		return model;
	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Temperatura");
		
		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Humedad");
		
		
		int c = 0;
		for (int i = 1; i <= 24; i++) {
			if (c < temp.size()) {
				if (i == (Double)temp.get(c)[0]) {
					System.out.println(i+" - "+temp.get(c)[1]+" - "+temp.get(c)[2]);
					series1.set(i, (Double)temp.get(c)[1]);
					series2.set(i, (Double)temp.get(c)[2]);
					c++;
				} else {
					series1.set(i, 0);
					series2.set(i, 0);
					System.out.println(i + "- " + 0);
				}
			} else {
				series1.set(i, 0);
				series2.set(i, 0);
				System.out.println(i + "- " + 0);
			}
		}

		model.addSeries(series1);
		model.addSeries(series2);

		return model;
	}

	private void createPieModels() {
		createPieModel1();
		
		createLivePieModel();
	}

	private void createPieModel1() {
		pieModel1 = new PieChartModel();

		pieModel1.set("Brand 1", 540);
		pieModel1.set("Brand 2", 325);
		pieModel1.set("Brand 3", 702);
		pieModel1.set("Brand 4", 421);

		pieModel1.setTitle("Simple Pie");
		pieModel1.setLegendPosition("w");
		pieModel1.setShadow(false);
	}

	private void createDonutModels() {
		donutModel1 = initDonutModel();
		donutModel1.setTitle("Donut Chart");
		donutModel1.setLegendPosition("w");
	}

	private DonutChartModel initDonutModel() {
		DonutChartModel model = new DonutChartModel();

		Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
		circle1.put("Brand 1", 150);
		circle1.put("Brand 2", 400);
		circle1.put("Brand 3", 200);
		circle1.put("Brand 4", 10);
		model.addCircle(circle1);

		Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
		circle2.put("Brand 1", 540);
		circle2.put("Brand 2", 125);
		circle2.put("Brand 3", 702);
		circle2.put("Brand 4", 421);
		model.addCircle(circle2);

		Map<String, Number> circle3 = new LinkedHashMap<String, Number>();
		circle3.put("Brand 1", 40);
		circle3.put("Brand 2", 325);
		circle3.put("Brand 3", 402);
		circle3.put("Brand 4", 421);
		model.addCircle(circle3);

		return model;
	}

	private void createLivePieModel() {
		livePieModel = new PieChartModel();

		livePieModel.set("Candidate 1", 540);
		livePieModel.set("Candidate 2", 325);
	}

	private void createFillToZero() {
		fillToZero = new CartesianChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		series1.set("4, -3, 3, 6, 2, -2", 0);

		fillToZero.addSeries(series1);
	}

	private MeterGaugeChartModel initMeterGaugeModel() {
		@SuppressWarnings("serial")
		List<Number> intervals = new ArrayList<Number>() {
			{
				add(20);
				add(50);
				add(120);
				add(220);
			}
		};

		return new MeterGaugeChartModel(140, intervals);
	}

	private void createMeterGaugeModels() {
		meterGaugeModel1 = initMeterGaugeModel();
		meterGaugeModel1.setTitle("MeterGauge Chart");
		meterGaugeModel1.setGaugeLabel("km/h");
		meterGaugeModel1.setGaugeLabelPosition("bottom");

	}

	private void createAnimatedModels() {
		animatedModel1 = initLinearModel();
		animatedModel1.setTitle("Line Chart");
		animatedModel1.setAnimate(true);
		animatedModel1.setLegendPosition("se");
		Axis yAxis = animatedModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(100);
		
		/*
		Axis xAxis = animatedModel1.getAxis(AxisType.X);
		xAxis.setMin(0);
		xAxis.setMax(25);*/
	}

	private void createDateModel() {
		dateModel = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		series1.set("2014-01-01", 51);
		series1.set("2014-01-06", 22);
		series1.set("2014-01-12", 65);
		series1.set("2014-01-18", 74);
		series1.set("2014-01-24", 24);
		series1.set("2014-01-30", 51);

		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Series 2");

		series2.set("2014-01-01", 32);
		series2.set("2014-01-06", 73);
		series2.set("2014-01-12", 24);
		series2.set("2014-01-18", 12);
		series2.set("2014-01-24", 74);
		series2.set("2014-01-30", 62);

		dateModel.addSeries(series1);
		dateModel.addSeries(series2);

		dateModel.setTitle("Zoom for Details");
		dateModel.setZoom(true);
		dateModel.getAxis(AxisType.Y).setLabel("Values");
		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		axis.setMax("2014-02-01");
		axis.setTickFormat("%b %#d, %y");

		dateModel.getAxes().put(AxisType.X, axis);
	}
}
