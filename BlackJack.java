
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack{  
    public static void main(String[] args){
        //Neural Network
        Layer layer = new Layer(15,6);
        Layer layer2 = new Layer(6,2);
        ActivationReLU ReLU = new ActivationReLU();
        ActivationSoftmax softMax = new ActivationSoftmax();
        Loss lossC = new Loss();
        ArrayList<double[]> batch = new ArrayList<double[]>();
        ArrayList<Double> Targets = new ArrayList<Double>();
        //BlackJack
        GameLoop game = new GameLoop();

        
        for (int i =0; i < 100; i++){
            System.out.print(game.start());
            if (i%10 ==0){
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

             

                NumJa.printArray(softMax.probabilites[0]);
                double tempTarget;
                if(softMax.probabilites[0][1] > softMax.probabilites[0][0]){
                    if(game.hit()){//see if it this action is a win or a loss. add to target list
                        tempTarget = 1;
                    }else{
                        tempTarget =0;
                    } 
                }else{
                    game.hold();
                    if(game.won){//see if it this action is a win or a loss. add to target list
                        tempTarget = 0;
                    }else{
                        tempTarget=1;
                    } 
                }
                Targets.add(tempTarget);
            }
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
            //Modify Weights
            
        }
    }
}
