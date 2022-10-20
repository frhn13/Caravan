public class CaravanOperators {
    public static boolean checkWin(Player p1, Player p2) {
        boolean gameEnd = false;
        if((p1.isSoldCaravan1() || p2.isSoldCaravan1()) && (p1.isSoldCaravan2() || p2.isSoldCaravan2()) &&
                (p1.isSoldCaravan3() || p2.isSoldCaravan3())) {
            winner(p1, p2);
            gameEnd = true;
        }
        return gameEnd;
    }

    public static void compareFirstCaravan(Player p1, Player p2) {
        if(21 <= p1.caravans.get(0).getTotal() && 26 >= p1.caravans.get(0).getTotal() &&
                (p2.caravans.get(0).getTotal() < p1.caravans.get(0).getTotal() || p2.caravans.get(0).getTotal() > 26)) {
            p1.setSoldCaravan1(true);
            p2.setSoldCaravan1(false);
        }
        else if(21 <= p2.caravans.get(0).getTotal() && 26 >= p2.caravans.get(0).getTotal() &&
                (p1.caravans.get(0).getTotal() < p2.caravans.get(0).getTotal() || p1.caravans.get(0).getTotal() > 26)) {
            p1.setSoldCaravan1(false);
            p2.setSoldCaravan1(true);
        }
        else {
            p1.setSoldCaravan1(false);
            p2.setSoldCaravan1(false);
        }
    }
    public static void compareSecondCaravan(Player p1, Player p2) {
        if(21 <= p1.caravans.get(1).getTotal() && 26 >= p1.caravans.get(1).getTotal() &&
                (p2.caravans.get(1).getTotal() < p1.caravans.get(1).getTotal() || p2.caravans.get(1).getTotal() > 26)) {
            p1.setSoldCaravan2(true);
            p2.setSoldCaravan2(false);
        }
        else if(21 <= p2.caravans.get(1).getTotal() && 26 >= p2.caravans.get(1).getTotal() &&
                (p1.caravans.get(1).getTotal() < p2.caravans.get(1).getTotal() || p1.caravans.get(1).getTotal() > 26)) {
            p1.setSoldCaravan2(false);
            p2.setSoldCaravan2(true);
        }
        else {
            p1.setSoldCaravan2(false);
            p2.setSoldCaravan2(false);
        }
    }
    public static void compareThirdCaravan(Player p1, Player p2) {
        if(21 <= p1.caravans.get(2).getTotal() && 26>=p1.caravans.get(2).getTotal() &&
                (p2.caravans.get(2).getTotal() < p1.caravans.get(2).getTotal() || p2.caravans.get(2).getTotal() > 26)) {
            p1.setSoldCaravan3(true);
            p2.setSoldCaravan3(false);
        }
        else if(21 <= p2.caravans.get(2).getTotal() && 26 >= p2.caravans.get(2).getTotal() &&
                (p1.caravans.get(2).getTotal() < p2.caravans.get(2).getTotal() || p1.caravans.get(2).getTotal() > 26)) {
            p1.setSoldCaravan3(false);
            p2.setSoldCaravan3(true);
        }
        else {
            p1.setSoldCaravan3(false);
            p2.setSoldCaravan3(false);
        }
    }
    public static void winner(Player p1, Player p2) {
        boolean player1Wins = (p1.isSoldCaravan1() && p1.isSoldCaravan2()) || (p1.isSoldCaravan1() && p1.isSoldCaravan3()) ||
                (p1.isSoldCaravan2() && p1.isSoldCaravan3());
        String winner = player1Wins ? "Player 1 Wins!" : "Player 2 Wins!";
        System.out.println(winner);
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
}
