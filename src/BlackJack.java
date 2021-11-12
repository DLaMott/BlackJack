
public class BlackJack extends CardGame {

    final String DEALER_NAME = "Dealer";
    final int DEALER_HIT_STAY_THRESHOLD = 16;

    private final BlackJackPlayer player = new BlackJackPlayer();
    private final BlackJackPlayer dealer = new BlackJackPlayer(DEALER_NAME);

    enum Winner {DEALER, PLAYER, TIE}

    private Winner winner;

    public BlackJackPlayer getPlayer() {
        return player;
    }

    public BlackJackPlayer getDealer() {
        return dealer;
    }

    public BlackJack.Winner getWinner() {
        return winner;
    }

    private void setWinner(Winner winner) {
        this.winner = winner;
    }

    public void dealCardWithHit(Player p) {
        dealCard(p);

        // Reference Variable for BlackJackPlayer
        BlackJackPlayer bjp;
        bjp = (BlackJackPlayer) p;

        // Variable for storing number of hits
        int hits = bjp.getNumberHits();

        // Hits preIncremented
        bjp.setNumberHits(++hits);

    }

    public void determineOutcome(Player p, Player d) {


        //reference variable of BlackJackPlayer casting player p
        BlackJackPlayer bjp = (BlackJackPlayer) p;

        //reference variable of BlackJackPlayer casting player d
        BlackJackPlayer bjd = (BlackJackPlayer) d;

        // If structure to determine if player or dealer has bust
        if (bjp.isBust() == true) {
            setWinner(Winner.DEALER);
        } else if (bjd.isBust() == true) {
            setWinner(Winner.PLAYER);
        } else if (bjd.getCurrentScore() > bjp.getCurrentScore()) {
            setWinner(Winner.DEALER);
        } else if (bjp.getCurrentScore() > bjd.getCurrentScore()) {
            setWinner(Winner.PLAYER);
        } else {
            setWinner(Winner.TIE);
        }

    }

    public BlackJack(int numCards) {
        super(numCards);
    }

    public static class BlackJackPlayer extends Player {
        final int BUST_SCORE = 21;

        private int numberHits;
        private boolean bust;

        public int getNumberHits() {
            return numberHits;
        }

        public boolean isBust() {
            return bust;
        }

        private void setNumberHits(int numberHits) {
            this.numberHits = numberHits;
        }

        private void setBust(boolean bust) {
            this.bust = bust;
        }

        public boolean checkBust() {
            boolean bust = false;

            // If statement to determine if score is higher than bust limit
            if (getCurrentScore() > BUST_SCORE) {
                // Bust set to true
                bust = true;
                // Method setbust called passing result
                setBust(bust);
            }

            return bust;
        }

        public BlackJackPlayer() {

        }

        public BlackJackPlayer(String name) {
            super(name);
        }
    }
}