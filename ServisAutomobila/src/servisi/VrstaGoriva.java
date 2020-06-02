package servisi;

public enum VrstaGoriva {

	BENZIN,
	DIZEL,
	GAS;
	
	
	public static VrstaGoriva fromInt(int a) {
		switch (a) {
		case 1:
			return BENZIN;
		case 2:
			return DIZEL;
		default:
			return GAS;
		}
	}
	public static int toInt(VrstaGoriva vrstaGoriva) {
		switch(vrstaGoriva) {
		case BENZIN:
			return 1;
		case DIZEL:
			return 2;
		default:
			return 3;
		}
	}
}
