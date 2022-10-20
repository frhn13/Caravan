import java.util.ArrayList;
import java.util.Scanner;

public class Caravan {

    private ArrayList<Card> cards;
    private boolean ascending;
    private int total;
    private boolean queened = false;

    public Caravan() {
        cards = new ArrayList<>();
        ascending = true;
        total = 0;
    }

    public void clearCaravan() {
        cards.clear();
        total = 0;
    }

    public void addJack(){//need to solve edge case for removing a card that has been previously doubled by a king
        int index = getIndex();
        if (cards.get(index - 1).getKingApplied() == 0) {
            total -= cards.get(index - 1).getCardNumber();
        } else {
            total -= cards.get(index - 1).getCardNumber() * cards.get(index - 1).getKingApplied();
        }
        cards.remove(index-1);
    }
    public void addKing(){
        int index = getIndex();
        if (cards.get(index - 1).getKingApplied() == 0) {
            total -= cards.get(index - 1).getCardNumber();
        } else {
            total -= cards.get(index - 1).getCardNumber() * cards.get(index - 1).getKingApplied();
        }
        cards.get(index-1).increaseKing();
        total += cards.get(index-1).getCardNumber()*cards.get(index-1).getKingApplied();
    }

    public int getIndex(){
        int index = -1;
        Scanner in = new Scanner(System.in);
        for (int i = 1; i <= cards.size(); i++) {
            System.out.println(i + ": " + cards.get(i - 1));
        }
        while (true){
            String sIndex = in.nextLine();
            try {
                index = Integer.parseInt(sIndex);
            } catch (Exception e){
                System.out.println("Enter a number i beg");
            }
            if (index > cards.size() || index < 0) {
                System.out.println("Enter a valid number");
            } else{
                break;
            }
        }
        return index;
    }


    public void addQueen(){
        ascending = !ascending;
        queened = true;
    }

    public void addJoker(Player p1, Player p2){
        Card card = cards.get(getIndex() - 1);
        CaravanOperators.loopCaravans(p1, p2, card);
    }

    public boolean addCardCheck(Card addedCard, Player p1, Player p2){
        boolean cardAdded;
        if (cards.size() == 0 && addedCard.getCardNumber() < 15){
            if (addedCard.getCardNumber() > 10){
                return false;
            }
            setCards(addedCard);
            return true;
        }
        Card lastCard = cards.get(cards.size() - 1);
        if (addedCard.getCardNumber() > 10){
            switch (addedCard.getCardNumber()) {
                case 11 -> addJack();
                case 12 -> {
                    addQueen();
                }
                case 13 -> addKing();
                case 14 -> addJoker(p1, p2);
            }
            cardAdded = true;
        }
        else if (cards.size() == 1 && cards.get(0).getCardNumber() != addedCard.getCardNumber()){
            setCards(addedCard);
            cardAdded = true;
        }else if(ascending && (lastCard.getCardNumber() < addedCard.getCardNumber() || lastCard.getCardSuit().equals(addedCard.getCardSuit()))) {
            setCards(addedCard);
            cardAdded = true;
        }
        else if (!ascending && lastCard.getCardNumber() > addedCard.getCardNumber() || lastCard.getCardSuit().equals(addedCard.getCardSuit())){
            setCards(addedCard);
            cardAdded = true;
        }
        else {
            cardAdded = false;
        }
        if (cards.size() >= 2 && !queened){
            findAscending();
        }
        return cardAdded;
    }

    public void findAscending(){
        if (!queened) {
            Card lastCard = cards.get(cards.size() - 1);
            Card secondLastCard = cards.get(cards.size() - 2);
            if (lastCard.getCardNumber() > secondLastCard.getCardNumber()) {
                setAscending(true);
            } else {
                setAscending(false);
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean getAscending() {
        return ascending;
    }

    public void setCards(Card addedCard) {
        total += addedCard.getCardNumber();
        this.cards.add(addedCard);
        queened = false;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
    public void removeCard(int cardIndex){
        total -= cards.get(cardIndex).getCardNumber();
        cards.remove(cardIndex);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
