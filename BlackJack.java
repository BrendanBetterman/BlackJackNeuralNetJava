
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack{  
    public static void main(String[] args){
        //Neural Network
        Layer layer = new Layer(15,6);
        Layer layer2 = new Layer(6,2);
        double[][] bestWeight1 =layer.weights;
        double[][] bestWeight2 =layer2.weights;
        double lowestLoss = 100;
        ActivationReLU ReLU = new ActivationReLU();
        ActivationSoftmax softMax = new ActivationSoftmax();
        Loss lossC = new Loss();
        ArrayList<double[]> batch = new ArrayList<double[]>();
        ArrayList<Double> Targets = new ArrayList<Double>();

        Train trainer = new Train(layer,layer2);
        //BlackJack
        int wins = 0;
        GameLoop game = new GameLoop();
        for (int u =0; u < 1000; u++){
            game.start();
            if (u%10 ==0){
                game.cards.shuffle();
            }
            while (game.inGame){
                double[] noSuit = game.cards.getDrawnNoSuit();
                double cardV = game.Player.getHandValue();
                double cardO = game.House.getValueHouse();
                double[] sample = new double[noSuit.length+1+1];
                for(int k=0; k<noSuit.length; k++){
                    sample[k] = noSuit[k];
                }
                sample[noSuit.length] = cardV;
                sample[noSuit.length+1] = cardO;
                batch.add(sample);
                //run network to see if it needs to hit or hold.
                double[][] tempinput = new double[1][];
                tempinput[0] = sample;
                layer.forward(tempinput);
                ReLU.forward(layer.output);
                layer2.forward(ReLU.output);
                softMax.forward(layer2.output);
                double tempTarget;
                if(softMax.probabilites[0][1] > softMax.probabilites[0][0]){
                    //System.out.println(game.hit() +"Did I win "+ game.won);
                    game.hit();
                    if(game.won){//see if it this action is a win or a loss. add to target list
                        //wins+=1;
                        tempTarget = 0;

                    }else{
                        tempTarget =1;
                    } 
                }else{
                    game.hold();
                    if(game.won){//see if it this action is a win or a loss. add to target list
                        wins+=1;
                        tempTarget = 1;
                    }else{
                        tempTarget=0;
                    } 
                }
                Targets.add(tempTarget);
            }
        }
        System.out.println("Wins :"+wins);
        //batch convert to double[][]
        double[][] input = new double[batch.size()][];
        for(int k=0; k<batch.size(); k++){
            input[k] = batch.get(k);
        }
        double[] target = new double[Targets.size()];
        for(int k=0; k<Targets.size(); k++){
            target[k] = Targets.get(k);
        }
        batch.clear();

        for (int i =0; i < 10; i++){
            if(i%2 ==0){
                System.out.println(i);
            }
            //Modify Weights
            for (int k=0; k< 100; k++){
                lowestLoss = trainer.forward(input, target,lowestLoss);
            }
            
        }
        for (int i=0; i<100; i++){
        wins =0;
        for (int u =0; u < 100; u++){
            game.start();
            if (u%10 ==0){
                game.cards.shuffle();
            }
            while (game.inGame){
                double[] noSuit = game.cards.getDrawnNoSuit();
                double cardV = game.Player.getHandValue();
                double cardO = game.House.getValueHouse();
                double[] sample = new double[noSuit.length+1+1];
                for(int k=0; k<noSuit.length; k++){
                    sample[k] = noSuit[k];
                }
                sample[noSuit.length] = cardV;
                sample[noSuit.length+1] = cardO;
                batch.add(sample);
                //run network to see if it needs to hit or hold.
                double[][] tempinput = new double[1][];
                tempinput[0] = sample;
                layer.forward(tempinput);
                ReLU.forward(layer.output);
                layer2.forward(ReLU.output);
                softMax.forward(layer2.output);
 
                if(softMax.probabilites[0][1] > softMax.probabilites[0][0]){
                    game.hit();
                    if(game.won){//see if it this action is a win or a loss. add to target list
                       wins +=1; 
                    } 
                }else{
                    game.hold();
                    if(game.won){//see if it this action is a win or a loss. add to target list
                        wins +=1;
                    } 
                }
                
            }
        }
        System.out.println("Wins " + wins);
    }
        
    }
}
