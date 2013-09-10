package fr.treeptik.service;

import java.util.List;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Employe;

public interface EmployeService {

	Employe save(Employe employe) throws ServiceException;
	Employe update (Employe employe) throws ServiceException;
	void remove (Employe employe) throws ServiceException;
	List<Employe> findAll() throws ServiceException;
	Employe findById (Integer id) throws ServiceException;
	
}