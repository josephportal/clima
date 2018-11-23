package dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Ciudad;
import modelo.Clima;

public class ClimaDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public Clima consultarClimaActual(String ciudad) {
		Clima cl = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("clima");
			EntityManager em = emf.createEntityManager();
			Query query = em.createNativeQuery("select * from clima cl join ciudad cd on cl.idciudad=cd.idciudad"
					+ " where cd.nombre = ?1 order by fecha_hora DESC LIMIT 1", Clima.class);
			query.setParameter(1, ciudad);
			List<Clima> lista = query.getResultList();

			for (Clima c : lista) {
				cl = new Clima();
				cl.setTemperatura(c.getTemperatura());
				cl.setHumedad(c.getHumedad());
				cl.setFechaHora(c.getFechaHora());
				System.out.println("Temperatura: " + c.getTemperatura());
				System.out.println("Humedad: " + c.getHumedad());
				System.out.println("Fecha Actual: " + c.getFechaHora());
			}

			em.close();
			emf.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return cl;
	}

	// Map<Double, Double>
	@SuppressWarnings("unchecked")
	public List<Object[]> consultarClima(String ciudad, String fecha) {
		// Map<Double, Double> resultm = null;
		List<Object[]> resultList = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("clima");
			EntityManager em = emf.createEntityManager();
			Query query = em.createNativeQuery(
					"select extract(hour from fecha_hora) as hora, avg(cl.temperatura) as temp, avg(cl.humedad) as hum from clima cl join ciudad cd "
							+ "on cl.idciudad=cd.idciudad where cd.nombre like ?1 and date(cl.fecha_hora) = '" + fecha
							+ "' group by hora order by hora asc");
			query.setParameter(1, ciudad);

			resultList = query.getResultList();

			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consultarTempxCiudad(int cantidad) {
		List<Object[]> resultList = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("clima");
			EntityManager em = emf.createEntityManager();
			Query query = em.createNativeQuery("select nf.nombre,cli.temperatura from clima cli join "
					+ "(select cd.nombre,max(fecha_hora) as fecha from clima cl join ciudad cd on cl.idciudad=cd.idciudad "
					+ "group by cd.nombre) nf on fecha_hora=nf.fecha order by nf.nombre LIMIT " + cantidad);

			resultList = query.getResultList();

			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return resultList;
	}

	@SuppressWarnings("unchecked")
	public void registrarClima(int temperatura, int humedad, Integer idCiudad) {
		int maxid = 0;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("clima");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNativeQuery("select idclima from clima", Clima.class);
			List<Clima> cli = query.getResultList();

			for (Clima c : cli) {
				maxid = c.getIdclima();
			}

			if (maxid != 0) {
				maxid += 1;
			}

			Clima cl = new Clima();
			cl.setIdclima(maxid);
			cl.setTemperatura(temperatura);
			cl.setHumedad(humedad);
			Ciudad cd = new Ciudad();
			cd.setIdciudad(idCiudad);
			cl.setCiudad(cd);
			Timestamp fecha = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
			cl.setFechaHora(fecha);
			cl.setEstado(true);

			em.persist(cl);
			em.getTransaction().commit();

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consultarTemperatura(String ciudad, String fecha1, String fecha2) {
		List<Object[]> resultList = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("clima");
			EntityManager em = emf.createEntityManager();
			Query query = em.createNativeQuery("select cl.temperatura, cl.fecha_hora from clima cl join ciudad cd on cl.idciudad=cd.idciudad "
							+ "where cd.nombre like ?1 and date(fecha_hora) between '" + fecha1 + "' and '" + fecha2
							+ "' order by cl.fecha_hora;");
			query.setParameter(1, ciudad);

			resultList = query.getResultList();


			
			em.close();
			emf.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return resultList;
	}

}
