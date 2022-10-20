import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Player {
    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<Card> hand = new ArrayList<>();
    ArrayList<Caravan> caravans = new ArrayList<>();
    private boolean soldCaravan1 = false, soldCaravan2 = false, soldCaravan3 = false;

    public Player() {
        populateCards();
        for (int i = 0; i < 8; i++) {
            hand.add(deck.get(0));
            deck.remove(0);
        }
        for (int i = 0; i < 3; i++) {
            caravans.add(new Caravan());
        }
    }

    public void populateCards() {
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        deck.add(new Card("Clubs", 14));
        deck.add(new Card("Hearts", 14));
        for (String suit : suits) {
            for (int i = 1; i <= 13; i++) {
                deck.add(new Card(suit, i));
            }
        }
        Collections.shuffle(deck);
    }

    public void removeCard(Card removeCard){
        hand.remove(removeCard);
        if (hand.size() < 5 && deck.size() > 0){
            hand.add(deck.get(0));
            deck.remove(0);
        }
    }

    public boolean addCardToCaravan(int caravanNumber, Card card, Player p1, Player p2) {
        caravanNumber-=1;
        return caravans.get(caravanNumber).addCardCheck(card, p1, p2);
    }

    public boolean isSoldCaravan1() {
        return soldCaravan1;
    }

    public void setSoldCaravan1(boolean soldCaravan1) {
        this.soldCaravan1 = soldCaravan1;
    }

    public boolean isSoldCaravan2() {
        return soldCaravan2;
    }

    public void setSoldCaravan2(boolean soldCaravan2) {
        this.soldCaravan2 = soldCaravan2;
    }

    public boolean isSoldCaravan3() {
        return soldCaravan3;
    }

    public void setSoldCaravan3(boolean soldCaravan3) {
        this.soldCaravan3 = soldCaravan3;
    }
}