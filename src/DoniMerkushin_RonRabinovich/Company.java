package DoniMerkushin_RonRabinovich;

import java.io.Serializable;
import java.util.ArrayList;

import DoniMerkushin_RonRabinovich.Preference.Prefy;

public class Company implements Profitable, Serializable {
	private String name;
	private ArrayList<Worker> allWorkers;
	private ArrayList<Department> allDepartments;
	private ArrayList<Role> allRoles;
	private double profit = 0;
	private static final long serialVersionUID = 6529685098267757690L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAllWorkers(ArrayList<Worker> allWorkers) {
		this.allWorkers = allWorkers;
	}

	public void setAllDepartments(ArrayList<Department> allDepartments) {
		this.allDepartments = allDepartments;
	}

	public void setAllRoles(ArrayList<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public Company(String name) {
		this.name = name;
		this.allDepartments = new ArrayList<Department>();
		this.allWorkers = new ArrayList<Worker>();
		this.allRoles = new ArrayList<Role>();
	}

	public ArrayList<Worker> getAllWorkers() {
		return allWorkers;
	}

	public ArrayList<Role> getAllRoles() {
		return allRoles;
	}

	public boolean addRole(Role role) {
		if (isExistRole(role.getName()))
			return false;

		for (int i = 0; i < allDepartments.size(); i++) {
			allDepartments.get(i).addRole(new Role(role));
		}
		allRoles.add(new Role(role));
		return true;
	}

	public boolean isExistRole(String role) {
		for (int i = 0; i < allRoles.size(); i++) {
			if (allRoles.get(i).getName().equalsIgnoreCase(role))
				return true;
		}
		return false;
	}

	public boolean addWorker(Worker worker, int departmentNumber) { // modify

		allWorkers.add(worker);
		addWorkerToRole(worker);
		allDepartments.get(departmentNumber - 1).addWorker(worker);
		return true;
	}

	private void addWorkerToRole(Worker worker) {
		for (int i = 0; i < allRoles.size(); i++) {
			if (allRoles.get(i).getName().equalsIgnoreCase(worker.getRole()))
				allRoles.get(i).addWorker(worker);
		}

	}

	public boolean addDepartment(Department department) {
		for (int i = 0; i < allRoles.size(); i++) {
			department.addRole(new Role(allRoles.get(i)));
		}
		allDepartments.add(department);
		return true;
	}

	public String presentDepartments() {
		StringBuffer sb = new StringBuffer("Presenting all departments (" + allDepartments.size() + "):\n");
		for (int i = 0; i < allDepartments.size(); i++)
			sb.append("department " + (i + 1) + ":\n" + allDepartments.get(i) + "\n");

		return sb.toString();

	}

	public ArrayList<Department> getAllDepartments() {
		return allDepartments;
	}

	public String presentRoles() {
		StringBuffer sb = new StringBuffer("Presenting all Roles:\n");
		for (int i = 0; i < allRoles.size(); i++)
			sb.append(i + 1 + ")" + allRoles.get(i).getName() + "\n");

		return sb.toString();
	}

	public String toProfits() { // case 5
		// if cases 3 , 4 were not pressed yet!
		updateProfit();
		StringBuffer sb = new StringBuffer("Company :" + name + " : " + profit + "\nAll Departements:\n");
		for (int i = 0; i < allDepartments.size(); i++) {
			sb.append((i + 1) + " - " + allDepartments.get(i).getProfit() + "\n");
		}
		sb.append("All Workers:\n");
		for (int i = 0; i < allWorkers.size(); i++) {
			sb.append(allWorkers.get(i).getName() + " : " + allWorkers.get(i).getProfit() + "\n");
		}
		return sb.toString();
	}

	public String toString() { // case 2
		StringBuffer sb = new StringBuffer("Company name: " + name + "\n");
		sb.append(presentDepartments());
		return sb.toString();
	}

	@Override
	public void updateProfit() { // calculates and sums up all departments profits
		double profit = 0;
		for (int i = 0; i < allDepartments.size(); i++) {
			allDepartments.get(i).updateProfit();
			profit += allDepartments.get(i).getProfit();
		}

		this.profit = profit;

	}

	public String showPreferences() {
		Prefy[] preferences = Preference.Prefy.values();
		StringBuffer sb = new StringBuffer("Presenting all preferences:\n");
		for (int i = 0; i < preferences.length; i++) {
			sb.append((i + 1) + "- " + preferences[i].name() + "\n");
		}
		return sb.toString();
	}

	public void changeRoleHours(int roleIndex, int prefyIndex, int indicator) { // case 3
		if (!allRoles.get(roleIndex).getChangeable()) {
			for (int i = 0; i < allDepartments.size(); i++) {
				if (allDepartments.get(i).getRoles().get(roleIndex).getWorkerList().size() != 0)
					if (!allDepartments.get(i).getChangeable())
						return;
			}
		}
		for (int i = 0; i < allDepartments.size(); i++) {
			if (allDepartments.get(i).getChangeable()) {
				allDepartments.get(i).getRoles().get(roleIndex).changeWorkHours(prefyIndex, indicator);
			}
		}
		updateProfit();

	}

	public void changeDepHours(int departmentIndex, int prefyIndex, int indicator) { // case 4
		allDepartments.get(departmentIndex).changeWorkHours(prefyIndex, indicator);
		updateProfit();
	}

}
