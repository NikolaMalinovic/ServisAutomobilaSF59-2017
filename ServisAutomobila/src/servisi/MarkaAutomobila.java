package servisi;

public enum MarkaAutomobila {

	FIAT,
	SKODA,
	OPEL;
	
	
	public static MarkaAutomobila fromInt(int a) {
		switch (a) {
		case 1:
			return FIAT;
		case 2:
			return SKODA;
		default:
			return OPEL;
		}
	}
	public static int toInt(MarkaAutomobila markaAutomobila) {
		switch(markaAutomobila) {
		case FIAT:
			return 1;
		case SKODA:
			return 2;
		default:
			return 3;
		}
	}
}
