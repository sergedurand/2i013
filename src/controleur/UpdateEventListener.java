package controleur;

import observeurs.*;

public interface UpdateEventListener {
    public void manageUpdate();

	void add(ObserveurSwing o);
}
