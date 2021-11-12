
import java.util.*;

public abstract class CardGame {


    // Constants for Card names
    final String SPADES = "Spades";
    final String HEARTS = "Hearts";
    final String DIAMONDS = "Diamonds";
    final String CLUBS = "Clubs";

    final String[] suits = {SPADES, HEARTS, DIAMONDS, CLUBS};

    // Constants for Card Values
    final int ACE_NUM = 0;
    final int JACK_NUM = 10;
    final int QUEEN_NUM = 11;
    final int KING_NUM = 12;

    // Constant String name for card names
    final String ACE_STR = "Ace";
    final String JACK_STR = "Jack";
    final String QUEEN_STR = "Queen";
    final String KING_STR = "King";

    // Constant for Card Score
    final int ACE_SCORE = 11;
    final int JACK_SCORE = 10;
    final int QUEEN_SCORE = 10;
    final int KING_SCORE = 10;

    private Map<Integer, String> rankMap = new HashMap<>();
    private Map<String, Integer> scoreMap = new HashMap<>();

    // Private methods for cards in deck, cards in suit, and next card
    private int numberOfCardsInDeck;
    private int cardsInSuit;
    private int nextCard = 0;

    private final ArrayList<Card> deck = new ArrayList<>();

    public Map<Integer, String> getRankMap() {
        return Collections.unmodifiableMap(rankMap);
    }

    // Getter for Cards in deck
    public int getNumberOfCardsInDeck() {
        return numberOfCardsInDeck;
    }

    // Getter for cards in suit
    public int getCardsInSuit() {
        return cardsInSuit;
    }

    // Getter for next card
    public int getNextCard() {
        return nextCard;
    }

    public List<Card> getDeck() {
        return Collections.unmodifiableList(deck);
    }

    private void setRankMap(Map<Integer, String> rankMap) {
        this.rankMap = rankMap;
    }

    private void setScoreMap(Map<String, Integer> scoreMap) {
        this.scoreMap = scoreMap;
    }


    // Private setters for num of cards and cards in suit
    private void setNumberOfCardsInDeck(int numberOfCardsInDeck) {
        this.numberOfCardsInDeck = numberOfCardsInDeck;
    }

    private void setCardsInSuit(int cardsInSuit) {
        this.cardsInSuit = cardsInSuit;
    }

    private void setNextCard(int nextCard) {
        this.nextCard = nextCard;
    }

    //*** 8) Create a private method named "setDeck" that takes no arguments
    //***    and returns no value.
    //***    a) The method body consists of the following lines of code:
    //***       A) Declare a local variable of datatype int named "cardsInDeck".
    //***          1) Invoke the method "getNumberOfCardsInDeck" and assign its
    //***             return value to variable "cardsInDeck".
    //***       C) Invoke the method "clear" on property "deck".
    //***       D) Write a for-loop that iterates variable "cardsInDeck" times
    //***          [HINT: the for-loop's control variable needs to start at zero.]
    //***          and executes the following line of code:
    //***          1) Invoke the "add" method on property "deck" passing the
    //***             following as the one argument:
    //***             a) A new "Card" instance; passing the for-loop's control
    //***                variable to the "Card" constructor.
    // Method for setting up card deck
    private void setDeck() {
        int cardsInDeck = getNumberOfCardsInDeck();
        deck.clear();
        for (int i = 0; i < cardsInDeck; i++) {
            // While i is less than cards in deck add card
            deck.add(new Card(i));
        }
    }

    // Method for dealing card to player
    public void dealCard(Player p) {
        int nextCardIndex = getNextCard();
        Card nextCard = getDeck().get(nextCardIndex);
        p.addCard2Hand(nextCard);
        setNextCard(++nextCardIndex);
    }

    public void shuffleDeck() {

        // Returns number of cards
        int cardsInDeck = getNumberOfCardsInDeck();
        // Index.
        int index;
        // Temp card
        Card temp;
        // For loop to handle shuffling of deck
        for (int i = 0; i < cardsInDeck; i++) {
            index = (int) (Math.random() * cardsInDeck);
            temp = deck.get(i);
            temp = deck.set(i, deck.get(index));
            temp = deck.set(index, temp);
        }
    }

    public CardGame(int numCards) {

        setNumberOfCardsInDeck(numCards);

        // Set cards divide by suit array length
        setCardsInSuit(numCards / suits.length);

        Map<Integer, String> rankMap = new HashMap<>();
        rankMap.put(ACE_NUM, ACE_STR);
        rankMap.put(JACK_NUM, JACK_STR);
        rankMap.put(QUEEN_NUM, QUEEN_STR);
        rankMap.put(KING_NUM, KING_STR);

        this.setRankMap(rankMap);

        Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put(ACE_STR, ACE_SCORE);
        scoreMap.put(JACK_STR, JACK_SCORE);
        scoreMap.put(QUEEN_STR, QUEEN_SCORE);
        scoreMap.put(KING_STR, KING_SCORE);

        this.setScoreMap(scoreMap);

        // Invokes the method "setDeck".
        setDeck();

    }


    public class Card {
        private String rank;
        private String suit;
        private int score;

        public String getRank() {
            return rank;
        }

        public String getSuit() {
            return suit;
        }

        public int getScore() {
            return score;
        }

        private void setRank(String rank) {
            this.rank = rank;
        }

        private void setSuit(String suit) {
            this.suit = suit;
        }

        private void setScore(int score) {
            this.score = score;
        }

        public Card(int cardNum) {

            // Rank num
            int rankNum = cardNum % cardsInSuit;

            if (rankMap.containsKey(rankNum))
                setRank(rankMap.get(rankNum));
            else
                setRank(String.valueOf(rankNum + 1));

            // String suit set to cardnum divided by cards in suit
            setSuit(suits[cardNum / cardsInSuit]);

            if (scoreMap.containsKey(getRank()))
                setScore(scoreMap.get(getRank()));
            else
                setScore(rankNum + 1);
        }

        @Override
        public String toString() {
            return "\t" + rank + " of " + suit;
        }
    }

    public static class Player {
        private String name;
        private int currentScore;
        private final ArrayList<Card> hand = new ArrayList<>();

        public String getName() {
            return name;
        }

        public int getCurrentScore() {
            return currentScore;
        }

        public List<Card> getHand() {
            return Collections.unmodifiableList(hand);
        }

        private void setName(String name) {
            this.name = name;
        }

        private void setCurrentScore(int currentScore) {
            this.currentScore = currentScore;
        }

        // Method to obtain card in hand
        public Card getCard(int cardIndex) {
            return hand.get(cardIndex);
        }

        // Method to add card to hand
        public void addCard2Hand(Card c) {
            hand.add(c);
            setCurrentScore(getCurrentScore() + c.getScore());
        }

        // Method to display hand with tostring
        public void displayFormattedHand() {
            for (int i = 0; i < hand.size(); i++) {
                System.out.println(hand.get(i).toString());
            }
        }

        // Player constructor
        public Player() {
            // New Scanner object for user input
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter your name: ");
            String playerName = input.nextLine();
            // Player name stored
            setName(playerName);

        }

        public Player(String name) {
            setName(name);
        }
    }

    public abstract void determineOutcome(Player p, Player d);
}