package DoniMerkushin_RonRabinovich;

import java.io.Serializable;

public class Preference implements Serializable {
	public enum Prefy {
		EARLIER, LATER, DEFAULT, FREELANCER
	};

	private int beginHour;
	private int endHour;
	private boolean isFree = false;

	public Preference() {
		beginHour = 8;
		endHour = 17;
	}

	public Preference(int begin, int end) {
		setBegin(begin);
		setEnd(end);
	}

	public Prefy[] getPrefy() {
		return Prefy.values();
	}

	public void setWorkHours(Prefy preference, int indicator) {
		setNotFree();
		if (preference == Prefy.DEFAULT) {
			this.beginHour = 8;
			this.endHour = 17;
		} else if (preference == Prefy.EARLIER) {
			this.beginHour -= indicator;
			this.endHour -= indicator;
		} else {
			this.beginHour += indicator;
			this.endHour += indicator;
		}

		if (this.beginHour < 0) // in case we have negative hours
			this.beginHour += 24;
		if (this.endHour < 0)
			this.endHour += 24;

		if (this.beginHour >= 24) // in case we have hours bigger than 23
			this.beginHour -= 24;
		if (this.endHour >= 24)
			this.endHour -= 24;
	}

	public void setEnd(int end) {
		this.endHour = end;
	}

	public void setBegin(int begin) {
		this.beginHour = begin;
	}

	public int getBegin() {
		return this.beginHour;
	}

	public int getEnd() {
		return this.endHour;
	}

	public void setFree() {
		isFree = true;

	}

	public boolean isFree() {
		return isFree;
	}

	public void setNotFree() {
		isFree = false;
	}
}
