package servisi;



public enum ModelAutomobila {

	STILO,
	OCTAVIA,
	INSIGNIA;
	
	public static ModelAutomobila fromInt(int a) {
		switch (a) {
		case 1:
			return STILO;
		case 2:
			return OCTAVIA;
		default:
			return INSIGNIA;
		}
	}
	public static int toInt(ModelAutomobila modelAutomobila) {
		switch(modelAutomobila) {
		case STILO:
			return 1;
		case OCTAVIA:
			return 2;
		default:
			return 3;
		}
	}
}
