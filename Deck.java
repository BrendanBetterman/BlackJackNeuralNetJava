
import java.util.Random;
public class Deck{
    private String[] suit = {"S","D","C","H"};
    private String[] cards = {"A","J","K","Q","2","3","4","5","6","7","8","9","10"};
    private int numberOfDecks = 4;

    public String[] deck = new String[suit.length * cards.length];
    public int[] drawn = new int[deck.length];
    
    public Deck(){
        for(int s = 0; s < this.suit.length; s ++){
            for (int c = 0; c < this.cards.length; c++){
                this.deck[this.cards.length*s + c] = ( this.cards[c] + this.suit[s]);
            }
        }
    }
    public double[] getDrawnNoSuit(){
        double[] out = new double[drawn.length/suit.length];
        for (int i =0; i <drawn.length /suit.length; i++){
            for(int k=0; k< suit.length; k++){
                out[i] += drawn[i*suit.length+k];
            }
            
        }
        return out;
    }
    public void numOfDecks(int temp){
        this.numberOfDecks = temp;
    }
    public void shuffle(){
        for(int i = 0; i < this.drawn.length; i++){
            this.drawn[i] = 0;
        }
    }
    public String drawCard(){
        Random random = new Random();
        int tempCard = random.nextInt(deck.length);
        if (this.drawn[tempCard] < this.numberOfDecks){
            this.drawn[tempCard] ++;
            return this.deck[tempCard];
        }else{
            return this.drawCard();
        }
    }
}


