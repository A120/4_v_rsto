package Logika_igre;

import java.util.LinkedList;
import java.util.List;

public class Logika {
	public static final int M = 6; /* Vi�ina igralne plo��e. */
	public static final int N = 7; /* �irina igralne plo��e. */
	public static final int A = 4; /* Dol�ina vrste za zmago. */
	
	public Igralec naPotezi; /* Igralec je tisti, ki je na potezi. */
	
	 /**
	  * Vsi mo�ni �etvorci na plo��i.
	  */
	private static final List<Cetvorec> cetvorci = new LinkedList<Cetvorec>();
	
	{ /* Se izvede, ko se po�ene program */
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				for (int [] smer : new int [][] {{1, 0}, {0, 1}, {1, 1}}) {
					int smer_i = smer[0];
					int smer_j = smer[1];
					if ((i + (A - 1) * smer_i < M) && (j + (A - 1) * smer_j < N)) {
						int [] x = new int [A];
						int [] y = new int [A];
						for (int k = 0; k < A; k++) {
							x[k] = i + smer_i * k;
							y[k] = j + smer_j * k;
						}
						cetvorci.add(new Cetvorec(x, y));
					}
				}
			}
		}
	}
	
	/**
	 * Na za�etku so vsa polja prazna, na potezi je O.
	 */
	
	public void novaIgra() {
		naPotezi = Igralec.O;
		Polje [][] plosca = new Polje[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
	}
	/**
	 * Ali je �etvorec zmagovalen, oz. ali so vsa polja �etvorca enaka p?
	 */
	
	public boolean zmagovalni_cetvorec(Cetvorec z, Polje p) {
		for (int i = 0; i < A; i++) {
			if (plosca[z.x[i]][z.y[i]] != p) {
				return false;
			}
		return true;
		}
	}
	
	/**
	 * Ali je igre konec? Konec je, ko na plo��i obstaja �etvorec,
	 * katerega vsa polja so O ali X.
	 */
	
	public stanjeIgre() {
		for (Cetvorec z : cetvorci) {
			if (zmagovalni_cetvorec(z, Polje.O)) {
				Stanje stanje = Stanje.ZMAGA_O;
				return stanje;
			}
			else if (zmagovalni_cetvorec(z, Polje.X)) {
				Stanje stanje = Stanje.ZMAGA_X;
				return stanje;
			}
		}
		/* Ker je pri tej igri mogo� tudi remi, je treba preveriti, �e je kak�no polje �e nezasedeno. */
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (plosca [i][j] == Polje.PRAZNO) {
					if (naPotezi == Igralec.O) {
						return Stanje.NA_POTEZI_O;
					} else {
						return Stanje.NA_POTEZI_X;
					}
				}
			}
			/* Zmagovalca ni, vsa polja pa so neprazna. */
			return Stanje.NEODLOCENO;
		}
	}
	
}
