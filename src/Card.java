public class Card {

    private String cardSuit;
    private int cardNumber;//11 for jack, 12 for queen, 13 for king, 14 for joker
    private int kingApplied;

    public Card(String cardSuit, int cardNumber) {
        this.cardSuit = cardSuit;
        this.cardNumber = cardNumber;
        this.kingApplied = 0;
    }
    public String getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(String cardSuit) {
        this.cardSuit = cardSuit;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getKingApplied() {
        return kingApplied;
    }

    public void setKingApplied(int kingApplied) {
        this.kingApplied = kingApplied;
    }

    public void increaseKing() {
        this.kingApplied += 2;
    }

    @Override
    public String toString() {
        String returnString = "";
        if (cardNumber == 1){
            return "Ace of " + cardSuit;
        }
        if(cardNumber>10)
            switch (cardNumber) {
                case 11 -> returnString += "Jack of " + cardSuit;
                case 12 -> returnString += "Queen of " + cardSuit;
                case 13 -> returnString += "King of " + cardSuit;
                case 14 -> returnString += "Joker";
            }
        else
            returnString += cardNumber + " of " + cardSuit;

        if (kingApplied != 0){
            returnString += " -King: " + kingApplied;
        }

        return returnString;
    }
}
