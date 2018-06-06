package Logika_igre;

public enum Polje {
	PRAZNO,
	O,
	X;
	
	public String stringPolja() {
		switch (this) {
		default: return "...";
		case PRAZNO: return " ";
		case O: return "O";
		case X: return "X";
		}
	}
}