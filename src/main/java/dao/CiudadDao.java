package dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Ciudad;

public class CiudadDao {
	
	@SuppressWarnings("unchecked")
	public List<Ciudad> mostrarCiudad() {
		List<Ciudad> lista = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("clima");
			EntityManager em = emf.createEntityManager();

			Query query = em.createNativeQuery("select idciudad,nombre,estado from ciudad", Ciudad.class);
			lista = query.getResultList();

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ciudad> listarPorCiudad(String nombre) {
		List<Ciudad> lista = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("clima");
			EntityManager em = emf.createEntityManager();

			Query query = em.createNativeQuery("select idciudad from ciudad where upper(nombre) like upper(?1)", Ciudad.class);
			query.setParameter(1, nombre);
			lista = query.getResultList();
			
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public void registrarCiudad(String nombre, boolean estado) {
		int maxid = 0;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("clima");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNativeQuery("select max(idciudad) as idciudad from ciudad", Ciudad.class);
			List<Ciudad> cli = query.getResultList();

			for (Ciudad c : cli) {
				maxid = c.getIdciudad();
			}

			if (maxid != 0) {
				maxid += 1;
			}

			Ciudad cd = new Ciudad();
			cd.setIdciudad(maxid);
			cd.setNombre(nombre);
			cd.setEstado(estado);

			em.persist(cd);
			em.getTransaction().commit();

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
