import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    ArrayList<Player> players;
    boolean gameEnd = false;

    private Main() {

        players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
    }

    private void runGame() {

        while (!gameEnd) {
            for (int i = 1; i <= 2; i++) {
                System.out.println("Player " + i + "'s turn:\nHand:\n" + players.get(0).hand + "\nPlayer 1 caravans:");
                for (Caravan car : players.get(0).caravans) {
                    System.out.println(car.getCards() + " : " + car.getTotal());
                }
                System.out.println("Player 2 caravans:");
                for (Caravan car : players.get(1).caravans) {
                    System.out.println(car.getCards() + " : " + car.getTotal());
                }
                runMenu(players.get(i - 1));
                CaravanOperators.compareFirstCaravan(players.get(0), players.get(1));
                CaravanOperators.compareSecondCaravan(players.get(0), players.get(1));
                CaravanOperators.compareThirdCaravan(players.get(0), players.get(1));
                gameEnd = CaravanOperators.checkWin(players.get(0), players.get(1));
                if(gameEnd) {
                    break;
                }
            }
        }
    }

    private void runMenu(Player player) {

        Scanner in = new Scanner(System.in);
        String choice;
        boolean valid = false;

        do {
            System.out.println("1: Add Card to Caravan\n2: Discard Card\n3: Discard Caravan");
            choice = in.nextLine();
            switch (choice) {
                case "1" -> valid = addCard(player, in);
                case "2" -> valid = discardCard(player, in);
                case "3" -> valid = discardCaravan(player, in);
                default -> System.out.println("Invalid Input");
            }
        } while (!valid);
    }

    private boolean addCard(Player player, Scanner in) {

        boolean valid = false;
        System.out.println("Enter the card to play");
        for (int i = 1; i <= player.hand.size(); i++) {
            System.out.println(i + ": " + player.hand.get(i - 1));
        }
        String choice = in.nextLine();
        int card = validateInput(choice, player.hand.size());
        if (card == -1) {
            return false;
        } else {
            card --;

            int carNumber = 3;

            if (player.hand.get(card).getCardNumber() > 10) {
                System.out.println("Player 1 Caravans:");
                for (int i = 1; i <= 3; i++) {
                    System.out.println(i + ": " + players.get(0).caravans.get(i - 1).getCards());
                }
                System.out.println("Player 2 Caravans:");
                carNumber = 6;
                for (int i = 4; i <= 6; i++) {
                    System.out.println(i + ": " + players.get(1).caravans.get(i - 4).getCards());
                }
            } else {
                System.out.println("Caravans:");
                for (int i = 1; i <= 3; i++) {
                    System.out.println(i + ": " + player.caravans.get(i - 1).getCards());
                }
            }
            System.out.println("Choose which caravan to add to");
            choice = in.nextLine();
            int caravan = validateInput(choice, carNumber);
            if (caravan == -1) {
                return false;
            } else {
                if (player.hand.get(card).getCardNumber() < 11) {
                    valid = player.addCardToCaravan(caravan, player.hand.get(card), players.get(0), players.get(1));
                } else {
                    if (caravan <= 3) {
                        valid = players.get(0).addCardToCaravan(caravan, player.hand.get(card), players.get(0), players.get(1));
                    } else {
                        valid = players.get(1).addCardToCaravan(caravan - 3, player.hand.get(card), players.get(0), players.get(1));
                    }
                }
                if (!valid) {
                    System.out.println("Invalid move");
                } else {
                    player.removeCard(player.hand.get(card));
                }
            }
            return valid;
        }
    }

    private boolean discardCard(Player player, Scanner in) {

        System.out.println("Enter the card to discard");
        for (int i = 1; i <= player.hand.size(); i++) {
            System.out.println(i + ": " + player.hand.get(i - 1));
        }
        String choice = in.nextLine();
        int card = validateInput(choice, player.hand.size());
        if (card == -1) {
            return false;
        } else {
            card --;
            player.removeCard(player.hand.get(card));
            return true;
        }
    }

    private boolean discardCaravan(Player player, Scanner in) {
        boolean valid = false;
        System.out.println("Enter the caravan to discard");
        for (int i = 1; i <= 3; i++) {
            System.out.println(i + ": " + player.caravans.get(i - 1).getCards());
        }
        String choice = in.nextLine();
        int caravan = validateInput(choice, 3);
        if (caravan == -1) {
            return false;
        } else {
            player.caravans.get(caravan).clearCaravan();
            return true;
        }
    }

    private int validateInput(String choice, int end) {

        try {
            int input = Integer.parseInt(choice);
            if (input < 1 || input > end) {
                System.out.println("Choose a number between " + 1 + " and " + end);
            } else {
                return input;
            }
        } catch (Exception e) {
            System.out.println("Please enter numbers");
        }
        return -1;
    }

    public static void loopCaravans(Player p1, Player p2, Card card) {
        if (card.getCardNumber() == 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < p1.caravans.get(i).getCards().size(); j++) {
                    if (p1.caravans.get(i).getCards().get(j).getCardSuit().equals(card.getCardSuit())) {
                        p1.caravans.get(i).removeCard(j);
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < p2.caravans.get(i).getCards().size(); j++) {
                    if (p2.caravans.get(i).getCards().get(j).getCardSuit().equals(card.getCardSuit())) {
                        p2.caravans.get(i).removeCard(j);
                    }
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < p1.caravans.get(i).getCards().size(); j++) {
                    if (p1.caravans.get(i).getCards().get(j).getCardNumber() == (card.getCardNumber())) {
                        p1.caravans.get(i).removeCard(j);
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < p2.caravans.get(i).getCards().size(); j++) {
                    if (p2.caravans.get(i).getCards().get(j).getCardNumber() == (card.getCardNumber())) {
                        p2.caravans.get(i).removeCard(j);
                    }
                }
            }
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public static void main(String[] args) {
        System.out.println("Caravan!");
        Main game = new Main();
        game.runGame();
    }
}