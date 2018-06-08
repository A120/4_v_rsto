package inteligenca;

import Logika_igre.*;

/**
 * Objekt sestavljen iz Poteze in ocene le-te. Če je poteza null, je igre konec,
 * rezultat pa je spravljen v oceni.
 */

public class OcenjenaPoteza {
	public Poteza poteza;
	public int ocena;
	
	public OcenjenaPoteza(Poteza poteza, int ocena) {
		this.poteza = poteza;
		this.ocena = ocena;
	}
}
