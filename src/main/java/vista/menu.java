package vista;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import dao.CiudadDao;
import dao.ClimaDao;
import javassist.compiler.ast.ASTList;
import modelo.Ciudad;
import modelo.Clima;
import modelo.Regresion;

public class menu {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		/*
		 * List<Ciudad> ciudades; List<String> cities=null;
		 * 
		 * CiudadDao dao = new CiudadDao(); ciudades = dao.mostrarCiudad();
		 * 
		 * for (int i = 0; i < ciudades.size(); i++) { cities.add(i,
		 * ciudades.get(i).getNombre()); }
		 * 
		 * for (int i = 0; i < cities.size(); i++) { System.out.println(cities.get(i));
		 * }
		 */

		ClimaDao dao = new ClimaDao();
		/*
		 * dao.consultarClima("Bogota","2018-10-12",13); for (int i = 1; i < 25; i++) {
		 * if (dao.consultarClima("Bogota","2018-10-12",i) != null) {
		 * System.out.println(dao.consultarClima("Bogota","2018-10-12",i));
		 * System.out.println("--- "+i); } }
		 */

		/*
		 * List<Double> promTemp = new ArrayList<>(); Double prom;
		 * 
		 * 
		 * 
		 * for (int i = 0; i < 24; i++) { prom = dao.consultarClima("Bogota",
		 * "2018-10-12", i + 1); if (prom != null) {
		 * System.out.println("**************************** " + i + " ** " + prom);
		 * promTemp.add(i, prom); } else { promTemp.add(i, 0.0); } }
		 * 
		 * for (int i = 0; i < promTemp.size(); i++) {
		 * System.out.println(promTemp.get(i)); } promTemp.clear();
		 * System.out.println("Size "+promTemp.size());
		 * 
		 * 
		 * 
		 * /* for (int i = 0; i < climas.size(); i++) { suma +=
		 * climas.get(i).getTemperatura() / climas.size(); }
		 * 
		 * System.out.println(suma);
		 */

		/*
		 * Map<Double, Double> temp = new HashMap<Double, Double>(); temp =
		 * dao.consultarClima("Bogota", "2018-10-12");
		 * 
		 * Double[][] val = new Double[temp.size()][2]; List<Double[][]> lista = new
		 * ArrayList<>(); int k = 0; for (Map.Entry<Double, Double> entry :
		 * temp.entrySet()) { System.out.println("clave=" + entry.getKey().intValue() +
		 * ", valor=" + entry.getValue()); val[k][0] = entry.getKey(); val[k][1] =
		 * entry.getValue();
		 * 
		 * k++; }
		 * 
		 * lista.add(val);
		 * 
		 * for (int i = 0; i < val.length; i++) {
		 * System.out.println(lista.get(0)[i][0]); }
		 * System.out.println("***********"+lista.size());
		 * 
		 * for (int i = 0; i < val.length; i++) { System.out.println(val[i][0] + " - " +
		 * val[i][1]); }
		 */

		// Collections.sort();

		// Crear otro for para recorreo y sacar el valor menor / y agregar un break

		/*
		 * Map<Double, Double> temp = new HashMap<Double, Double>(); temp =
		 * dao.consultarClima("Bogota", "2018-10-12"); Map<Double, Double> treeMap = new
		 * TreeMap<>(temp); Double[][] val = new Double[temp.size()][2];
		 * List<Double[][]> lista = new ArrayList<>(); int k = 0; for (Map.Entry<Double,
		 * Double> entry : treeMap.entrySet()) { System.out.println("clave=" +
		 * entry.getKey().intValue() + ", valor=" + entry.getValue()); val[k][0] =
		 * entry.getKey(); val[k][1] = entry.getValue();
		 * 
		 * k++; }
		 * 
		 * lista.add(val);
		 * 
		 * int c = 0; for (int i = 1; i <= 24; i++) { if (c < val.length) { if (i ==
		 * lista.get(0)[c][0]) { System.out.println(i+" - "+lista.get(0)[c][1]); c++; }
		 * else { System.out.println(i + "- " + 0); } } else { System.out.println(i +
		 * "- " + 0); } }
		 */

		/*
		 * List<Object[]> clim; clim = dao.consultarClima("Chapinero", "2018-10-12");
		 * 
		 * int c = 0; for (int i = 1; i <= 24; i++) { if (c < clim.size()) { if (i ==
		 * (Double)clim.get(c)[0]) {
		 * System.out.println(i+" - "+clim.get(c)[1]+" - "+clim.get(c)[2]); c++; } else
		 * { System.out.println(i + "- " + 0); } } else { System.out.println(i + "- " +
		 * 0); } }
		 * 
		 * 
		 * System.out.println("\n"); for (int j = 0; j < clim.size(); j++) {
		 * System.out.println(clim.get(j)[0]+" * "+clim.get(j)[1]+" * "+clim.get(j)[2]);
		 * }
		 */

		List<Object[]> datos;
		datos = dao.consultarTempxCiudad(5);
		List<Object[]> grado = new ArrayList<>();
		Object[] gra;
		
		for (int k = 0; k < 5; k++) {

			List<Object[]> clim = dao.consultarTemperatura("Bogota", "2018-10-11", "2018-10-12");
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
			gra = new Object[1];
			gra[0] = ((double) Math.round(regresion.correlacion() * 10d) / 10d)*k;
			grado.add(gra);
		}
		
		Object[] da;
		List<Object[]> data = new ArrayList<>();
		System.out.println(datos.size());
		for (int i = 0; i < datos.size(); i++) {
			da = new Object[3];
			da[0] = datos.get(i)[0];
			da[1] = datos.get(i)[1];
			da[2] = grado.get(i)[0];
			data.add(i,da);
		}
		
		
		
		

		System.out.println("***********************************"+data.size());
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i)[2]);
		}
		
		
		
		
		
		
		
		// Una fábrica de bebidas refrescantes observa sus ventas y la temperatura de la
		// calle
		// double[] ventas = {9, 11, 15, 16, 20, 24, 27, 29, 22, 20, 14, 9};
		// double[] temperatura = {5, 7, 10, 12, 16, 20, 23, 27, 19, 14, 9, 6};

	}

}
