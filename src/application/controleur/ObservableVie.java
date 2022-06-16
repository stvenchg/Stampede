package application.controleur;

import application.vue.VieVue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObservableVie implements ChangeListener<Number>{

	private VieVue vieVue;

	
	public ObservableVie(VieVue vieVue) {
		this.vieVue = vieVue;
	}
	
	@Override
	public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
		int nV = arg2.intValue();
		
		if (nV <= 0) {
			System.out.println("Fin de game Théorique");
			this.vieVue.setVisible(false);
		} else {
			vieVue.setImage(nV-1);
			this.vieVue.setVisible(true);
		}
	}

}