package br.com.contabilizei.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

@SuppressWarnings({"unchecked","rawtypes"})
public abstract class GenericDAO<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("contabilizei-pu");
	private EntityManager em;
	private Class<T> entityClass;
	
	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	private void beginTransaction() {
		this.em = emf.createEntityManager();
		this.em.getTransaction().begin();
	}

	private void commit() {
		this.em.getTransaction().commit();
	}
	
	private void closeTransaction() {
		this.em.close();
	}

	private void commitAndCloseTransaction() {
		commit();
		closeTransaction();
	}

	public void rollback() {
		this.em.getTransaction().rollback();
	}

	public void flush() {
		this.em.flush();
	}

	public void joinTransaction() {
		this.em = emf.createEntityManager();
		this.em.joinTransaction();
	}
	
	public void save(T entity) {
		beginTransaction();
		this.em.persist(entity);
		commitAndCloseTransaction();
	}

	public void delete(Long id, Class<T> clazz) {
		beginTransaction();
		T entityToBeRemoved = this.em.getReference(clazz, id);
		this.em.remove(entityToBeRemoved);
		commitAndCloseTransaction();
	}

	public T update(T entity) {
		beginTransaction();
		T entityUpdated = (T) this.em.merge(entity);
		commitAndCloseTransaction();
		return entityUpdated;
	}

	public T find(Integer entityID) {
		beginTransaction();
		T entity = (T) this.em.find(this.entityClass, entityID);
		beginTransaction();
		return entity;
	}

	public T find(Long entityID) {
		beginTransaction();
		T entity = (T) this.em.find(this.entityClass, entityID);
		closeTransaction();
		return entity;
	}

	public T findReferenceOnly(int entityID) {
		beginTransaction();
		T entity = (T) this.em.getReference(this.entityClass, Integer.valueOf(entityID));
		closeTransaction();
		return entity;
	}

	public List<T> findAll() {
		beginTransaction();
		CriteriaQuery cq = this.em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(this.entityClass));
		List<T> result = this.em.createQuery(cq).getResultList();
		closeTransaction();
		return result;
	}

	protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
		T result = null;
		try {
			beginTransaction();
			Query query = this.em.createNamedQuery(namedQuery);
			if ((parameters != null) && (!parameters.isEmpty())) {
				populateQueryParameters(query, parameters);
			}
			result = (T) query.getSingleResult();
			closeTransaction();
		} catch (NoResultException e) {
			System.out.println("No result found for named query: " + namedQuery);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	protected List<T> findManyResults(String namedQuery, Map<String, Object> parameters) {
		
		beginTransaction();
		
		List<T> result = null;
		try {
			Query query = this.em.createNamedQuery(namedQuery);
			if ((parameters != null) && (!parameters.isEmpty())) {
				populateQueryParameters(query, parameters);
			}
			result = query.getResultList();
		} catch (NoResultException e) {
			System.out.println("No result found for named query: " + namedQuery);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}
		
		closeTransaction();
		
		return result;
	}
	
	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter((String) entry.getKey(), entry.getValue());
		}
	}

}
