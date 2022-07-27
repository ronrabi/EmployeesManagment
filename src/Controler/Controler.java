package Controler;

import DoniMerkushin_RonRabinovich.Company;
import DoniMerkushin_RonRabinovich.Department;
import DoniMerkushin_RonRabinovich.Role;
import DoniMerkushin_RonRabinovich.Worker;
import IMVC.IControler;
import IMVC.IModel;
import IMVC.IView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controler implements IControler {
	IModel iModel;
	IView iView;

	@Override
	public IModel getiModel() {
		return iModel;
	}

	@Override
	public IView getiView() {
		return iView;
	}

	@Override
	public void setModel(IModel m) {
		this.iModel = m;
	}

	@Override
	public void setView(IView v) {
		this.iView = v;
	}

	@Override
	public void createHardCoded() {
		iModel.createHardCoded();
	}

	@Override
	public ArrayList<Department> getAllDepartments() {
		return iModel.getAllDepartments();
	}

	@Override
	public boolean addWorker(Worker worker, int departmentNumber) {
		return iModel.addWorker(worker, departmentNumber);
	}

	@Override
	public boolean addRole(Role role) {
		return iModel.addRole(role);
	}

	@Override
	public boolean addDepartment(Department department) {
		return iModel.addDepartment(department);
	}

	@Override
	public Company getCompany() {
		return iModel.getCompany();
	}

	@Override
	public int getRoleIndex(String roleName) {
		return iModel.getRoleIndex(roleName);
	}

	@Override
	public String printProfitDapartment() {
		return iModel.printProfitDapartment();
	}

	@Override
	public String printProfitWorker() {
		return iModel.printProfitWorker();
	}

	@Override
	public boolean cheakNameRoleExist(String workerName) {
		return iModel.cheakNameRoleExist(workerName);
	}

	@Override
	public void CreateSetCompanyName(String name) {
		iModel.CreateSetCompanyName(name);
	}

	@Override
	public void saveToFile(Company e1) throws FileNotFoundException, IOException {
		iModel.saveToFile(e1);
	}

	@Override
	public Company readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		return iModel.readFromFile();
	}

	@Override
	public void setCompany(Company company) {
		iModel.setCompany(company);
	}
}
