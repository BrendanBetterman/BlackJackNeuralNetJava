
public class GameLoop{
    public Deck cards = new Deck();
    private int money;
    public Hand Player;
    public Hand House = new Hand();
    private int bet;
    public boolean inGame = true;
    public boolean won = false;
    public GameLoop(){
        this.money = 2000;
        this.bet = 0;
        this.Player = new Hand();
        
    }
    
    public String checkCards(Hand Hand,String delimiter){
        String output ="";
        String[] hand = Hand.getHand();
        for (int i =0; i<Hand.size(); i++){
            output += hand[i] + delimiter;
        }
        return output;
    }
    public String checkCards(){
        return checkCards(Player," ");
    }
    public String start(){
        inGame = true;
        String output;
        if (Player.empty()){
            for (int i = 0; i<=1; i++){
                this.hit(Player);
                this.hit(House);
            }
            output = "House: ??, " + House.getHand()[1] + "\n";
            output += "You: " + checkCards(Player,", ");
            return output;
        }
        return "The Game was Already Started";
    }
    public String quit(){
        return "Quiting";
    }
    public String getBalance(){
        return "Your Balance is $" + money;
    }
    public String setBet(int amount){
        if (Player.empty()){
            if (amount > this.money){
                return "Insufficient Funds";
            }else{
                this.money -= amount;
                this.bet += amount;
                return "Bet Has Been Made.";
            }
        }
        return "Game Already Started";
    }
    public String hit(Hand Hand){
        String output = cards.drawCard();
        Hand.addCard(output);
        return output;
    }
    public boolean hit(){
        String temp = this.hit(Player);
        if (hasBusted()){
            this.hold();
            return false;
        }
        return true;
    }
    private void houseAI(){
        int handValue = House.getHandValue();
        if (House.hasAce() && handValue == 17){
            this.hit(House);
        }else if (handValue < 16){
            this.hit(House);
        }
    }
    public boolean hasBusted(Hand Hand){
        if (!Hand.empty()){
            return Hand.getHandValue() > 21;
        }
        return false;
    }
    public boolean hasBusted(){
        if (hasBusted(Player)){
            this.inGame=false;
            return true;
        }else{
            return false;
        }
    }
    public String hold(){
        String output;
        if (Player.empty()){
            return "Game Not Started";
        }
        this.houseAI();
        output = "House " + checkCards(House, ", ") +"\n";
        if ((hasBusted(Player) && hasBusted(House)) 
        || Player.getHandValue() == House.getHandValue()){
            output += "tie";
            this.won = true;
            this.money += this.bet;
        }else if (hasBusted(Player) 
        || Player.getHandValue() < House.getHandValue()){
            output += "lost";
        }else{
            output += "win";
            this.won = true;
            this.money += this.bet*2;
        }
        inGame = false;
        House.clear();
        Player.clear();
        this.bet = 0;
        return output;
    }
}