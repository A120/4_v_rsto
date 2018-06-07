package inteligenca;

import Logika_igre.*;

public class Ocena {
	public static final int ZMAGA = 1000000; // Nedosegljivo veliko število.
	public static final int NEODLOCENO = 0;
	public static final int PORAZ = -ZMAGA; // Če nekdo zmaga, njegov nasprotnik izgubi; sešteti se mora v 0.
	public static final int A = Logika.A;
	
	public int vrednostCetvorca(int i) {
		assert (i < A);
		return (1000 * (A - i) );
	}
}
