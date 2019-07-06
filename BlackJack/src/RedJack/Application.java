package RedJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		RedJack game = new RedJack();
	}
}

class RedJack {

	ArrayList<Card> Deck = new ArrayList<Card>();
	int totPunten = 0;
	int card = 0;
	
	Scanner scanner = new Scanner(System.in);

	public RedJack() {
		maakDeck();
		Collections.shuffle(Deck);
		for (int i = 0; i < 13; i++) {
			System.out.print(Deck.get(i).getKaartNaam() + ", ");
		}
		System.out.println();
		Collections.shuffle(Deck);
		spelen();
	}

	private void maakDeck() {
		String[] suits = { "Harten", "Klaver", "Ruiten", "Schoppen" };
		String[] ranks = { "Aas", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Boer", "Vrouw", "Heer" };
		int[] value = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

		// ArrayList<Card> Deck = new ArrayList<Card>();

		for (int j = 0; j < 13; j++) {
			for (int i = 0; i < 4; i++) {
				Deck.add(new Card(ranks[j], suits[i], value[j]));
			}

		}

	}

	public void spelen() {
		
		boolean aan = true;

		System.out.println("Om een kaart te vragen toets K.\n" + "Om te passen toets P.\n" + "Om te stoppen toets Q.");
		while (aan == true) {
			System.out.println("Toets K, P of Q.");
			
			String gebruikerInput = scanner.nextLine();
			
			if (gebruikerInput.equals("K") || gebruikerInput.equals("k")) {
				kaartVragen(Deck, card);
				card++;
			} else if (gebruikerInput.equals("P") || gebruikerInput.equals("p")) {
				passen();
			} else if (gebruikerInput.equals("Q") || gebruikerInput.equals("q")) {
				stoppen();
				aan = false;
			} else {
				System.out.println("Probeer opnieuw");
			}

		}
	}

	public void kaartVragen(ArrayList<Card> Deck, int card) {
		int punten = Deck.get(card).getValue();
		System.out.println(Deck.get(card).getKaartNaam());
		totPunten = totPunten + punten;
		System.out.println("De totale punten: " + totPunten);
	}

	private void passen() {
		card--;
		for (int i = 0; i <= card; i++) {
			System.out.print(Deck.get(i).getKaartNaam() + " ");
		}
		System.out.println("\nGepast, de totale punten: " + totPunten);
	}

	private void stoppen() {
		System.out.println("U bent gestopt.");
	}

}

class Card {
	String rank;
	String type;
	int value;

	Card(String rank, String type, int value) {
		this.rank = rank;
		this.type = type;
		this.value = value;

	}

	public String getKaartNaam() {
		return this.type + " " + this.rank;
	}

	public int getValue() {
		return this.value;
	}
}
