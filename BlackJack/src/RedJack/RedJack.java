package RedJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class RedJack {

	ArrayList<Card> Deck = new ArrayList<Card>();
	int punten = 0;
	int totPunten = 0;
	int kaartIDbegin = 0;
	int kaartID = 0;
	boolean spelAan = true;
	boolean rondeAan = true;
	boolean beurtAan = true;
	boolean aas = false;

	Scanner scanner = new Scanner(System.in);

	public RedJack() {
		maakDeck();
		Collections.shuffle(Deck);
		for (int i = 0; i < 52; i++) {
			System.out.print(Deck.get(i).getKaartNaam() + ", ");
		}
		System.out.println();

		spelen();
	}

	private void maakDeck() {
		char[] suits = { '\u2665', '\u2663', '\u2666', '\u2660' };
		String[] ranks = { "Aas", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Boer", "Vrouw", "Heer" };
		int[] value = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {

				Deck.add(new Card(ranks[j], suits[i], value[j]));
			}

		}

	}

	public void spelen() {

		System.out.println("Druk op enter om het spel te starten");

		scanner.nextLine();
		while (spelAan == true) {
			ronde();
		}
	}

	public void ronde() {
		Collections.shuffle(Deck);
		rondeAan = true;
		while (rondeAan == true) {
			kaartID = kaartIDbegin;
			beurtAan = true;
			System.out.println("Druk op enter om de ronde te starten");
			String gebruikerInput = scanner.nextLine();

			System.out.println("De speler begint met: " + Deck.get(kaartID).getKaartNaam() + " & "
					+ Deck.get(kaartID + 1).getKaartNaam());
			punten = Deck.get(kaartID).getValue();
			aasCheck();
			totPunten += punten;
			kaartID++;
			punten = Deck.get((kaartID)).getValue();
			aasCheck();
			totPunten += punten;
			kaartID++;
			evaluatie();

			while (beurtAan == true) {
				System.out.println("Type k voor een kaart, p om te passen of q om te stoppen met het spel.");

				gebruikerInput = scanner.nextLine();
				if (gebruikerInput.equals("K") || gebruikerInput.equals("k")) {
					kaartVragen(Deck, kaartID);
					kaartID++;
				} else if (gebruikerInput.equals("P") || gebruikerInput.equals("p")) {
					passen();

				} else if (gebruikerInput.equals("Q") || gebruikerInput.equals("q")) {
					stoppen();
				} else {
					System.out.println("Probeer opnieuw");
				}

			}

		}
	}

	private void kaartVragen(ArrayList<Card> Deck, int card) {
		punten = Deck.get(kaartID).getValue();
		System.out.println(Deck.get(kaartID).getKaartNaam());
		aasCheck();
		totPunten = totPunten + punten;
		evaluatie();
	}

	private void passen() {
		System.out.print("\nDe speler past met: ");
		for (int i = kaartIDbegin; i <= (kaartID - 1); i++) {
			System.out.print(Deck.get(i).getKaartNaam() + " ");

		}
		System.out.println("\nDe waarde van deze kaarten zijn: " + totPunten);
		if (totPunten <= 21) {
			System.out.println("De speler heeft gewonnen.\n");
		} else if (totPunten > 21) {
			System.out.println("De speler heeft verloren.\n");

		}
		eindeRonde();
	}

	private void eindeRonde() {
		totPunten = 0;
		kaartIDbegin = kaartID + 1;
		beurtAan = false;
		aas = false;
	}

	private void stoppen() {
		beurtAan = false;
		rondeAan = false;
		spelAan = false;
		System.out.println("U bent gestopt.");
	}

	private void evaluatie() {
		System.out.println("De waarde van deze kaarten zijn: " + totPunten);
		if (totPunten > 21) {
			System.out.println("De speler heeft verloren.\n");
			eindeRonde();
		} else if (totPunten == 21) {
			System.out.println("Blackjack! De speler heeft gewonnen.\n");
			eindeRonde();
		}
	}

	private void aasCheck() {
		if (Deck.get(kaartID).getValue() == 11) {
			aas = true;
		}

		if (aas == true) {
			if (totPunten + punten == 21) {
				
			} else if (totPunten == 0) {
				aas = true;
			} else if (totPunten + punten > 22) {
				totPunten = totPunten - 10;
				aas = false;
			}  else if (totPunten + punten <= 20) {
				
			}
		}

	}
}