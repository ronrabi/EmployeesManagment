package DoniMerkushin_RonRabinovich;

import java.io.Serializable;

public abstract class Worker implements Profitable, Serializable {
	protected int id;
	protected String name;
	protected Preference jobPref;
	protected int beginHour; // self preference
	protected int endHour; // self preference
	protected String roleName;
	protected double profit = 0;
	protected static int serialId = 1000;

	public Worker(String name, int begin, int end, String roleName) {
		this.id = serialId++;
		this.name = name;
		this.roleName = roleName;
		this.jobPref = new Preference();
		this.beginHour = begin;
		this.endHour = end;
	}

	public String getName() {
		return name;
	}

	public void changeWorkHours(int prefyIndex, int indicator) {
		if (prefyIndex == 3) { // freelancer
			jobPref.setBegin(this.beginHour);
			jobPref.setEnd(this.endHour);
			jobPref.setFree();
		} else
			jobPref.setWorkHours(Preference.Prefy.values()[prefyIndex], indicator);
	}

	public double getProfit() {
		return profit;
	}

	public boolean equals(Object worker) {
		if (!(worker instanceof Worker))
			return false;

		return ((Worker) worker).getId() == id;
	}

	public int getId() {
		return id;
	}

	public String getRole() {
		return roleName;
	}

	public void setRole(String roleName) {
		this.roleName = roleName;
	}

	public String toString() {
		return getClass().getSimpleName() + " [name=" + name + " ,ID=" + id + " , role=" + roleName + "]";
	}

	public void updateProfit() {
		if (jobPref.isFree()) { // if work method is FREELANCER then all hours are pref for worker -> 0.1*8
			this.profit = 0.8;
		} else {
			int prefHoursCounter = 0;
			int beginJob = jobPref.getBegin();
			int endJob = jobPref.getEnd();
			int beginSelf = beginHour;
			int endSelf = endHour;
			if (endSelf < beginSelf)
				endSelf += 24;
			if (endJob < beginJob)
				endJob += 24;
			for (int i = beginJob; i < endJob; i++) {
				for (int j = beginSelf; j < endSelf; j++) {
					if (i == j || i + 24 == j || j + 24 == i)
						prefHoursCounter++;
				}
			}
			if (prefHoursCounter == 9)
				prefHoursCounter--;
			this.profit = ((8 - prefHoursCounter) * (-0.2)) + ((prefHoursCounter) * 0.2); // pref hour-> +0.2 , NOT
																							// pref-> -0.2

		}

	}

}
