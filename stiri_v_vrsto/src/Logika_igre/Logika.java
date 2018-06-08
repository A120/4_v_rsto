package Logika_igre;

import java.util.LinkedList;
import java.util.List;

public class Logika {
	public static final int M = 6; /* Vi�ina igralne plo��e. */
	public static final int N = 7; /* �irina igralne plo��e. */
	public static final int A = 4; /* Dol�ina vrste za zmago. */
	
	public Igralec naPotezi; /* Tisti igralec, ki je na potezi. */
	public Polje[][] plosca;
	
	 /**
	  * Vsi možni četvorci na plošči.
	  */
	public static final List<Cetvorec> cetvorci = new LinkedList<Cetvorec>();
	
	{ /* Se izvede, ko se požene program */
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
	 * Na začetku so vsa polja prazna, na potezi je O.
	 */	
	public Logika() {
		naPotezi = Igralec.O;
		Polje [][] plosca = new Polje[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
	}

	/**
	 * Vrne kopijo igre.
	 */
	public Logika(Logika igra) {
		plosca = new Polje[M][N];
		naPotezi = igra.naPotezi;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = igra.plosca[i][j];
			}
		}
	}
	
	/**
	 * Vrne ploščo.
	 */
	public Polje[][] vrniPlosco() {
		return plosca;
	}
	
	/**
	 * Vrne seznam možnih potez.
	 */
	public List<Poteza> moznePoteze() {
		LinkedList<Poteza> p = new LinkedList<Poteza>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (plosca[i][j] == Polje.PRAZNO) {
					p.add(new Poteza(i, j));
				}
			}
		}
		return p;
	}
	
	/**
	 * Vrne true, če je v stolpcu še prosto mesto, in odigra potezo p;
	 * vrne false sicer.
	 */
	public boolean odigrajPotezo(Poteza p) {		
		int x = p.vrniX();
		int y = p.vrniY();
		if (plosca[x][y] == Polje.PRAZNO) {
			switch (naPotezi) {
				case O: plosca[x][y] = Polje.O;
				case X: plosca[x][y] = Polje.X;
				default: System.out.println("Nih�e ni na potezi");
			naPotezi = naPotezi.nasprotnik();
			return true;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Ali je četvorec zmagovalen, oz. ali so vsa polja četvorca enaka p?
	 */
	
	public Cetvorec zmagovalniCetvorec() {
		for (Cetvorec z : cetvorci) {
			Igralec mozni_zmagovalec = kdoZmaga(z);
			if (mozni_zmagovalec != null) {
				return z;
			} else {
				continue;
			}
		}
		return null;
	}
	
	/**
	 * Vrne igralca, ki je zmagal, ali null, če četvorec ni zmagovalni.
	 */
	
	public Igralec kdoZmaga(Cetvorec z) {
		int stevec_O = 0;
		int stevec_X = 0;
		for (int i = 0; i < A && (stevec_O == 0 || stevec_X == 0); i++) {
			switch (plosca[z.x[i]][z.y[i]]) {
			case O: stevec_O += 1; break;
			case X: stevec_X += 1; break;
			case PRAZNO: break;
			}
		}
		if (stevec_O == A) {
			return Igralec.O;
		} else if (stevec_X == A) {
			return Igralec.X;
		} else {
			return null;
		}
	}
	
	/**
	 * Ali je igre konec? Konec je, ko na plošči obstaja četvorec,
	 * katerega vsa polja so O ali X.
	 */
	
	public Stanje stanjeIgre() {
		Cetvorec z = zmagovalniCetvorec();
		if (z != null) {
			switch (kdoZmaga(z)) {
			case O: return Stanje.ZMAGA_O;
			case X: return Stanje.ZMAGA_X;
			}
		}
		/* Ker je pri tej igri mogoč tudi remi, je treba preveriti, če je kakšno polje še nezasedeno. */
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
		}
		/* Zmagovalca ni, vsa polja pa so neprazna. */
		return Stanje.NEODLOCENO;
	}
}
