package fr.treeptik.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.treeptik.dao.EmployeDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Employe;

@Stateless
public class EmployeDAOImpl implements EmployeDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	// @EJB : c'est une autre manière de faire une injection mais on ne peut
	// injecter que des EJB (sinon on a @inject)

	@Override
	public Employe save(Employe employe) throws DAOException {
		log.info("Registering " + employe.getNom());
		em.persist(employe);
		return employe;
	}

	@Override
	public Employe update(Employe employe) throws DAOException {

		Employe emp = null;

		try {
			emp = em.merge(employe);
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return emp;
	}

	@Override
	public void remove(Employe employe) throws DAOException {

		try {

			Query query = em.createQuery("Delete from Employe e where e.id= :id");
			query.setParameter("id", employe.getId());
			query.executeUpdate();

		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e);
		}

	}

	@Override
	public List<Employe> findAll() throws DAOException {

		List<Employe> employes = null;

		try {

			TypedQuery<Employe> query = em.createQuery("Select e from Employe e", Employe.class);
			employes = query.getResultList();

		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return employes;
	}

	@Override
	public Employe findById(Integer id) throws DAOException {

		Employe emp = null;

		try {

			emp = em.find(Employe.class, id);

		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e);
		}

		return emp;
	}
}
