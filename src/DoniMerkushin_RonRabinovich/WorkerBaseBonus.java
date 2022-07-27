package DoniMerkushin_RonRabinovich;

import java.io.Serializable;

public class WorkerBaseBonus extends Worker implements Serializable {

	public WorkerBaseBonus(String name, int begin, int end, String roleName) {
		super(name, begin, end, roleName);
	}

}
