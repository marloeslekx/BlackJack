package RedJack;

class Card {
	String rank;
	char type;
	int value;

	Card(String rank, char type, int value) {
		this.rank = rank;
		this.type = type;
		this.value = value;

	}

	public String getKaartNaam() {
		return this.type + this.rank;
	}

	public int getValue() {
		return this.value;
	}
}