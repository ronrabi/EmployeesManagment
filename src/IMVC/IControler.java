package IMVC;

import DoniMerkushin_RonRabinovich.Company;
import DoniMerkushin_RonRabinovich.Department;
import DoniMerkushin_RonRabinovich.Role;
import DoniMerkushin_RonRabinovich.Worker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface IControler {
	public IModel getiModel();

	public IView getiView();

	public void setModel(IModel m);

	public void setView(IView v);

	public void createHardCoded();

	public ArrayList<Department> getAllDepartments();

	public boolean addWorker(Worker worker, int departmentNumber);

	public boolean addRole(Role role);

	public boolean addDepartment(Department department);

	public Company getCompany();

	public int getRoleIndex(String roleName);

	public String printProfitDapartment();

	public String printProfitWorker();

	public boolean cheakNameRoleExist(String workerName);

	public void CreateSetCompanyName(String name);

	public void saveToFile(Company e1) throws FileNotFoundException, IOException;

	public Company readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException;

	public void setCompany(Company company);

}
