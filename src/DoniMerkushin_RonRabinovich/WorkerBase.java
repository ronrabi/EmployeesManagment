package DoniMerkushin_RonRabinovich;

import java.io.Serializable;

public class WorkerBase extends Worker implements Serializable {

	public WorkerBase(String name, int beginHour, int endHour, String roleName) {
		super(name, beginHour, endHour, roleName);

	}

}
