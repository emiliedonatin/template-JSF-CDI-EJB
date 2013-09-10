package fr.treeptik.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Employe;
import fr.treeptik.service.EmployeService;

@ManagedBean(name="employeMB")
public class EmployeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private FacesContext facesContext;

    @Inject
    private EmployeService employeService;

    private Employe employe;
    
    private ListDataModel<Employe> dataModel =new ListDataModel<>();

    @PostConstruct
    public void init() {
        setEmploye(new Employe());
    }

    public void register() throws Exception {
        try {
            getEmployeService().save(getEmploye());
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            init();
        } catch (Exception e) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(), "Registration unsuccessful");
            facesContext.addMessage(null, m);
        }
    }
    
    public String updateEmployee() throws ServiceException{
		
    	employe = dataModel.getRowData();
    	
    	return "create";
    	
    }
    
    public String deleteEmployee() throws Exception{
    	
    	employe = dataModel.getRowData();
    	getEmployeService().remove(employe);
    	employe = new Employe();
    	
    	return "list";
    		
    }
    
    public String listEmploye(){
    	return "list";
    }
    
    public ListDataModel<Employe> getEmployeList() throws ServiceException{
    	
    }

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public ListDataModel<Employe> getDataModel() {
		return dataModel;
	}

	public void setDataModel(ListDataModel<Employe> dataModel) {
		this.dataModel = dataModel;
	}

	public EmployeService getEmployeService() {
		return employeService;
	}

	public void setEmployeService(EmployeService employeService) {
		this.employeService = employeService;
	}

}
