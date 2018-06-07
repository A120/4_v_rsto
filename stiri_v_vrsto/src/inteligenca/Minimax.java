package inteligenca;

import Logika_igre.*;
import GUI.*;

import javax.swing.SwingWorker;

public class Minimax extends SwingWorker<Poteza, Object> {
	private Okno master; // Glavno okno
	private Igralec jaz;
	private int globina; // Koliko potez vnaprej razmišljamo.
	
	public Minimax(Okno master, Igralec jaz, int globina) {
		this.master = master;
		this.jaz = jaz;
		this.globina = globina;
	}
	
	protected Poteza doInBackground() throws Exception {
		Logika igra = master.kopirajIgro();
		//TODO
	}
	
	
}
