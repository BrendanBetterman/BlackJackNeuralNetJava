
import java.util.ArrayList;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class Hand{
    private ArrayList<String> cards = new ArrayList<String>();
    public Hand(){}
    private int getValue(String card){
        char temp = card.charAt(1);
        char whitespace = " ".charAt(0);
            if (Character.isLetter(temp)){
                temp = card.charAt(0);
                if (Character.isDigit(temp)){
                    return Character.getNumericValue(temp); 
                }else{
                    if (card.charAt(0) == "A".charAt(0)){
                        return 11;
                    }else{
                        return 10;
                    }
                }
            }else{
                int a = 10 * Character.getNumericValue(card.charAt(0));
                int b = Character.getNumericValue(card.charAt(1));
                return a+b;
            }
        }
    public int getValueHouse(){
        return getValue(cards.get(1));
    }
    public int getHandValue(){
        int total = 0;
        int temp;
        int [] ace = new int[16];
        int aceIndex = 0;
        for (int i =0; i < this.cards.size(); i++){
            temp = this.getValue(this.cards.get(i));
            if (temp == 11){
                ace[aceIndex++] = 11;  
            }else{
                total += this.getValue(this.cards.get(i));
            }
        }
        for(int i = 0; i < ace.length; i++){
            temp = ace[i];
            if (temp == 11){
                total += temp;
                if (total > 21 || (total == 21 && i < ace.length -2)){
                    total -= 11;
                    total +=1;
                }
            }
        }
        return total;
    }
    public void addCard(String newCard){
        cards.add(newCard);
    }
    public void clear(){
        cards.clear();
    }
    public boolean hasAce(){
        for (int i =0; i <cards.size(); i++){
            if (getValue(cards.get(i)) == 11){
                return true;
            }
        }
        return false;
    }
    public int size(){
        return cards.size();
    }
    public boolean empty(){
        return cards.isEmpty();
    }
    public String[] getHand(){
        String[] hand = new String[cards.size()];
        for (int i=0; i<cards.size(); i++){
            hand[i] = cards.get(i);
        }
        return hand;
    }
}