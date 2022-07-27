package IMVC;

import DoniMerkushin_RonRabinovich.Company;
import DoniMerkushin_RonRabinovich.Department;
import DoniMerkushin_RonRabinovich.Role;
import DoniMerkushin_RonRabinovich.Worker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface IModel {
	public void setControler(IControler C);

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

	public Company readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException;

	public void saveToFile(Company e1) throws FileNotFoundException, IOException;

	public void setCompany(Company company);
}
