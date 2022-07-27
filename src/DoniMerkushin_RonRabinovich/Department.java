package DoniMerkushin_RonRabinovich;

import java.io.Serializable;
import java.util.ArrayList;

public class Department implements Profitable, Changeable, Syncable, Serializable {
	private ArrayList<Role> allRoles;
	private double profit = 0;
	private boolean changeable;

	public Department(boolean changeable) {
		allRoles = new ArrayList<Role>();
		if (changeable)
			setChangleable();
		else
			setSyncable();
	}

	public boolean addWorker(Worker worker) {
		for (int i = 0; i < allRoles.size(); i++) {
			if (worker.getRole().equalsIgnoreCase(allRoles.get(i).getName())) {
				allRoles.get(i).addWorker(worker);
				return true;
			}
		}
		return false; // error , role does not exist
	}

	public boolean getChangeable() {
		return this.changeable;
	}

	public boolean addRole(Role role) {
		if (!existRole(role))
			allRoles.add(new Role(role));

		return true;
	}

	private boolean existRole(Role role) {
		for (int i = 0; i < allRoles.size(); i++) {
			if (allRoles.get(i).equals(role))
				return true;
		}
		return false;
	}

	public ArrayList<Role> getRoles() {
		return allRoles;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("Presenting all Roles:\n");
		for (int i = 0; i < allRoles.size(); i++)
			sb.append(i + 1 + "-" + allRoles.get(i) + "\n");

		return sb.toString();
	}

	@Override
	public void updateProfit() {
		double profit = 0;
		for (int i = 0; i < allRoles.size(); i++) {
			allRoles.get(i).updateProfit();
			profit += allRoles.get(i).getProfit();
		}
		this.profit = profit;

	}

	public void changeWorkHours(int prefyIndex, int indicator) {
		if (!this.changeable) { // we need that all roles employees in the department can change work hours
			for (int i = 0; i < allRoles.size(); i++) {
				if (!allRoles.get(i).getChangeable() && allRoles.get(i).getWorkerList().size() > 0)
					return;
			}
		}
		for (int i = 0; i < allRoles.size(); i++) {
			if (allRoles.get(i).getChangeable())
				allRoles.get(i).changeWorkHours(prefyIndex, indicator);
		}

	}

	public double getProfit() {
		return profit;
	}

	@Override
	public void setSyncable() {
		changeable = false;

	}

	@Override
	public void setChangleable() {
		changeable = true;

	}

}
