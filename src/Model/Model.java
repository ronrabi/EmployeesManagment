package Model;

import DoniMerkushin_RonRabinovich.*;
import IMVC.IControler;
import IMVC.IModel;

import java.io.*;
import java.util.ArrayList;

public class Model implements IModel {
	Company company;
	IControler iControler;

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public void setControler(IControler C) {

		this.iControler = C;

	}

	public Company getCompany() {
		return company;
	}

	@Override
	public void createHardCoded() {

		// hard coded
		company.addDepartment(new Department(true));
		company.addDepartment(new Department(true));
		company.addDepartment(new Department(false));
		company.addRole(new Role("Programmer", true));
		company.addRole(new Role("Cleaner", true));
		company.addRole(new Role("HR", false));

		WorkerBase w1 = new WorkerBase("Benny", 9, 18, "Programmer");
		company.addWorker(w1, 1);
		WorkerBaseBonus w2 = new WorkerBaseBonus("Itzik", 12, 21, "Programmer");
		company.addWorker(w2, 2);
		WorkerBase w3 = new WorkerBase("Yael", 6, 15, "Programmer");
		company.addWorker(w3, 1);

		WorkerHourly w4 = new WorkerHourly("Roni", 14, 23, "HR");
		company.addWorker(w4, 1);
		WorkerBase w5 = new WorkerBase("Ori", 8, 17, "HR");
		company.addWorker(w5, 3);

		WorkerBaseBonus w6 = new WorkerBaseBonus("Hezi", 9, 18, "Cleaner");
		company.addWorker(w6, 2);
		WorkerBaseBonus w7 = new WorkerBaseBonus("Liron", 7, 16, "Cleaner");
		company.addWorker(w7, 2);
		WorkerHourly w8 = new WorkerHourly("Rinat", 13, 22, "Cleaner");
		company.addWorker(w8, 1);

	}

	@Override
	public ArrayList<Department> getAllDepartments() {

		return company.getAllDepartments();

	}

	@Override
	public boolean addWorker(Worker worker, int departmentNumber) {

		company.getAllWorkers().add(worker);
		addWorkerToRole(worker);
		company.getAllDepartments().get(departmentNumber - 1).addWorker(worker);
		System.out.println("add Worker");
		return true;
	}

	private boolean isExist(Worker worker) {
		for (int i = 0; i < company.getAllWorkers().size(); i++) {
			if (company.getAllWorkers().get(i).equals(worker))
				return true;
		}
		return false;
	}

	private void addWorkerToRole(Worker worker) {
		for (int i = 0; i < company.getAllRoles().size(); i++) {
			if (company.getAllRoles().get(i).getName().equalsIgnoreCase(worker.getRole()))
				company.getAllRoles().get(i).addWorker(worker);
		}

	}

	@Override
	public boolean addRole(Role role) {
		if (isExistRole(role.getName()))
			return false;

		for (int i = 0; i < company.getAllDepartments().size(); i++) {
			company.getAllDepartments().get(i).addRole(new Role(role));
		}
		company.getAllRoles().add(new Role(role));
		return true;
	}

	public boolean isExistRole(String role) {
		for (int i = 0; i < company.getAllRoles().size(); i++) {
			if (company.getAllRoles().get(i).getName().equalsIgnoreCase(role))
				return true;
		}
		return false;
	}

	@Override
	public boolean addDepartment(Department department) {

		for (int i = 0; i < company.getAllRoles().size(); i++) {
			department.addRole(new Role(company.getAllRoles().get(i)));
		}
		ArrayList<Department> arrayListD = company.getAllDepartments();
		arrayListD.add(department);
		company.setAllDepartments(arrayListD);

		return true;
	}

	public int getRoleIndex(String roleName) {
		int index = 0;
		for (int i = 0; i < company.getAllDepartments().get(0).getRoles().size(); i++) {
			if (company.getAllDepartments().get(0).getRoles().get(i).getName() == roleName) {
				index = i;
			}

		}
		return index;
	}

	public String printProfitWorker() {

		company.updateProfit();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < company.getAllWorkers().size(); i++) {
			sb.append(company.getAllWorkers().get(i).getName() + " : " + company.getAllWorkers().get(i).getProfit()
					+ "\n");
		}
		return sb.toString();
	}

	public String printProfitDapartment() {

		company.updateProfit();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < company.getAllDepartments().size(); i++) {
			sb.append("Dapartment " + (i + 1) + " - " + company.getAllDepartments().get(i).getProfit() + "\n");
		}
		return sb.toString();
	}

	public boolean cheakNameRoleExist(String workerName) {
		boolean bol = false;
		for (int i = 0; i < company.getAllRoles().size(); i++) {
			if (company.getAllRoles().get(i).getName().equalsIgnoreCase(workerName)) {
				bol = true;
			}
		}
		return bol;

	}

	public void CreateSetCompanyName(String name) {
		company = new Company(name);

	}

	public void saveToFile(Company e1) throws FileNotFoundException, IOException {
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Company.txt"));
		o.writeObject(e1);
		o.close();
		System.out.println("File saved successfully!");
	}

	public Company readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream i = new ObjectInputStream(new FileInputStream("Company.txt"));
		Company companytemp = (Company) i.readObject();
		i.close();
		company = companytemp;
		System.out.println("File restored successfully! \r\n");
		return company;
	}

}
