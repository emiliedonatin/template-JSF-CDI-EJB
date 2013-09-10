package fr.treeptik.dao;

import java.util.List;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Employe;

public interface EmployeDAO {

	Employe save(Employe employe) throws DAOException;
	Employe update (Employe employe) throws DAOException;
	void remove (Employe employe) throws DAOException;
	List<Employe> findAll() throws DAOException;
	Employe findById(Integer id) throws DAOException;

}