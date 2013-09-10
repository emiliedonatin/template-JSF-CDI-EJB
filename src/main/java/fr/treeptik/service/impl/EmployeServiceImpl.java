package fr.treeptik.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.treeptik.dao.EmployeDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Employe;
import fr.treeptik.service.EmployeService;

@Stateless
public class EmployeServiceImpl implements EmployeService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private EmployeDAO dao;

	@Override
	public Employe save(Employe employe) throws ServiceException {
		log.info("Registering " + employe.getNom());
		try {
			dao.save(employe);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return employe;
	}

	@Override
	public Employe update(Employe employe) throws ServiceException {
		try {
			employe = dao.update(employe);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}

		return employe;
	}

	@Override
	public void remove(Employe employe) throws ServiceException {
		try {
			dao.remove(employe);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}

	@Override
	public List<Employe> findAll() throws ServiceException {

		List<Employe> employes = new ArrayList<>();

		try {
			employes = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return employes;
	}

	@Override
	public Employe findById(Integer id) throws ServiceException {
		
		Employe employe = null;

		try {
			employe = dao.findById(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}

		return employe;
	}
}
