package DoniMerkushin_RonRabinovich;

import java.io.Serializable;
import java.util.ArrayList;

public class Role implements Profitable, Changeable, Syncable, Serializable {
	private String roleName;
	private ArrayList<Worker> workerList;
	private double profit = 0;
	private boolean changeable;

	public Role(String roleName, boolean changeable) {
		this.roleName = roleName;
		this.workerList = new ArrayList<Worker>();
		if (changeable)
			setChangleable();
		else
			setSyncable();
	}

	public Role(Role role) { // copy constructor
		this.roleName = role.getName();
		this.workerList = new ArrayList<Worker>();
	}

	public boolean getChangeable() {
		return this.changeable;
	}

	public boolean equals(Object role) {
		if (!(role instanceof Role))
			return false;
		return roleName.equalsIgnoreCase(((Role) role).getName());
	}

	public String getName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("Role name: " + roleName + "\n");
		sb.append("has " + workerList.size() + " workers\n");
		for (int i = 0; i < workerList.size(); i++) {
			sb.append(i + 1 + ")" + workerList.get(i) + "\n");
		}
		return sb.toString();
	}

	public boolean addWorker(Worker worker) {
		workerList.add(worker);
		return true;
	}

	@Override
	public void updateProfit() {
		double profit = 0;
		for (int i = 0; i < workerList.size(); i++) {
			workerList.get(i).updateProfit();
			profit += workerList.get(i).getProfit();
		}
		this.profit = profit;

	}

	public ArrayList<Worker> getWorkerList() {
		return workerList;
	}

	public void changeWorkHours(int prefyIndex, int indicator) {
		for (int i = 0; i < workerList.size(); i++) {
			workerList.get(i).changeWorkHours(prefyIndex, indicator);
		}
		updateProfit();

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
