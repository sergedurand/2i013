package controleur;

import observeurs.*;

public interface UpdateEventListener {

	void add(ObserveurSwing o);

	void manageUpdate(int sleep);
}
