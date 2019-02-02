

import java.util.Random;
import java.util.Scanner;

public class blackjack {
	private Scanner scan = new Scanner(System.in);
	private int money = 100;
	private static Random rand = new Random();
	private int playerHand = 0;
	private int dealerHand = 0;
	private int bet = -1;
	// rand.nextInt(10 ) + 1
	private int[] cards = { 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8,
			8, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
	private int coun = 0;

	public void bet(String result) {

		if (result == "win") {
			money = money + bet;
		}
		if (result == "lost") {
			money = money - bet;
		}

		if (money <= 0) {
			System.out.println(lost());
		} else {

			System.out.println("\nYou have " + money + "$ left \n    How much would you like to bet?");
			bet = scan.nextInt();
			if (bet > money) {
				System.out.println("Not enough funds, try again");
				bet("try");
			}
			if (bet == 0) {
				System.out.println(end());
			} else {
				playerHand();
			}

		}

	}

	public String lost() {
		return "You have ran out of money, Sorry.";
	}

	public String end() {
		return "Thank you for playing!";
	}

	public int randomHand() {

		coun++;

		for (int i = 0; i < 52; i++) {
			int ran = new Random().nextInt(cards.length);
			int tem = cards[i];
			cards[i] = cards[ran];
			cards[ran] = tem;

		}

		return cards[coun];

	}

	public void playerHand() {
		playerHand = randomHand();
		playerHand = randomHand() + playerHand;

		dealerHand = randomHand();
		System.out.println("The dealer's current card is: " + dealerHand);

		System.out.print("You have a " + playerHand + " Would you like to add(1) or stop(2)?\n");
		int temp = scan.nextInt();

		if (temp == 1) {
			while ((playerHand < 21) && (temp == 1)) {
				playerHand = playerHand + randomHand();

				if (playerHand > 21) {
					System.out.println("Your count is now: " + playerHand + " You lost ");
					bet("lost");
				} else {
					System.out.print("You have  " + playerHand + " Would you like to add(1) or stop(2)?\n");
					temp = scan.nextInt();
				}
			}
		}

		dealerHand = dealerHand + randomHand();
		// System.out.println("The dealer's hand is: " + dealerHand);
		while (dealerHand < 17) {
			dealerHand = dealerHand + randomHand();
			// System.out.println("The dealer's hand is now: " + dealerHand);

		}
		if ((dealerHand > 21) || (playerHand > dealerHand)) {
			System.out.println("The dealer's hand is: " + dealerHand + " \n " + " Your hand is: " + playerHand
					+ " Congratulations you win!");
			bet("win");
		} else if (dealerHand > playerHand) {
			System.out.println("The dealer's hand is: " + dealerHand + " \n " + " Your hand is: " + playerHand
					+ " \n You lost! \n Better luck next game !");
			bet("lost");
		} else {
			System.out.println("It's a tie! ");
			bet("tie");
		}
	}

	public static void main(String args[]) {
		System.out.println("Welcome to the Blackjack Game. \n Good Luck!");
		blackjack runner = new blackjack();
		runner.bet("none");

	}

}