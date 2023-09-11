package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.GameItem;


/**
 * @author torirem - aaustin5
 * CIS175 - Fall 2023
 * Sep 10, 2023
 */
public class GameItemHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("GamesToPlay");

	public void insertItem(GameItem toAdd) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toAdd);
		em.getTransaction().commit();
		em.close();
		
	}

	public void deleteItem(GameItem toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<GameItem> typedQuery = em.createQuery("select g from GameItem g where g.title = :selectedTitle and g.genre = :selectedGenre", GameItem.class);
		
		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
		typedQuery.setParameter("selectedGenre", toDelete.getGenre());
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		GameItem result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		
	}

	public List<GameItem> searchForItemByTitle(String gameTitle) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<GameItem> typedQuery = em.createQuery("select g from GameItem g where g.title = :selectedTitle", GameItem.class);
		typedQuery.setParameter("selectedTitle", gameTitle);
		List<GameItem> foundTitles = typedQuery.getResultList();
		em.close();
		return foundTitles;
	}

	public List<GameItem> searchForItemByGenre(String gameGenre) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<GameItem> typedQuery = em.createQuery("select g from GameItem g where g.genre = :selectedGenre", GameItem.class);
		typedQuery.setParameter("selectedGenre", gameGenre);
		List<GameItem> foundGenres = typedQuery.getResultList();
		em.close();
		return foundGenres;
	}

	public GameItem searchForItemById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		GameItem gameID = em.find(GameItem.class, idToEdit);
		em.close();
		return gameID;
	}

	public void cleanUp() {
		// TODO Auto-generated method stub
		emfactory.close();
	}

	public List<GameItem> showAllItems() {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		List<GameItem> allItems = em.createQuery("SELECT gm FROM GameItem gm").getResultList();
		return allItems;
	}

	public void updateItem(GameItem toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}


}
