package Logika_igre;

public enum Igralec {
	O,
	X;
	
	public Igralec nasprotnik() {
		if (this == O) {
			return X;
		} else {
			return O;
		}
	}
}
