import java.util.Random;
public class Train{
    public double[][] BW1;
    public double[][] BW2;
    public ActivationReLU ReLU = new ActivationReLU();
    public ActivationSoftmax softMax = new ActivationSoftmax();
    public Loss lossC = new Loss();
    public Layer l1;
    public Layer l2;
    public Random random = new Random();
    public Train(Layer l1,Layer l2){
        BW1 = l1.weights;
        BW2 = l2.weights;
        this.l1 = l1;
        this.l2 = l2;
    }
    public double[][] changeSlot(double[][] in, int[] slot){
        in[slot[0]][slot[1]] += (0.01 * random.nextDouble() -0.5);
        return in;
    }
    public double proprogate(double[][]input,double[] target ,double lowestLoss){
        this.l1.forward(input);
        ReLU.forward(this.l1.output);
        this.l2.forward(ReLU.output);
        softMax.forward(this.l2.output);
        lossC.forward(softMax.probabilites,target);
        double tempLoss = lossC.outputSample;
        
        if (tempLoss < lowestLoss){
            
            lowestLoss = tempLoss;
            this.BW1 = this.l1.weights;
            this.BW2 = this.l2.weights;
        }
        return lowestLoss;
    }
   
    public double forward(double[][] batch,double[] target,double lloss){
        for (int i = 0; i < this.l1.weights.length; i++){
            for (int k=0; k< this.l1.weights[0].length; k ++){
                this.l1.weights = changeSlot(this.BW1, new int[]{i,k});
                double tempLoss = proprogate(batch,target,lloss);
                if (tempLoss < lloss - .0001){
                    System.out.println("New Lowest Found " + tempLoss + "  " + lloss);
                    lloss= tempLoss;
                }
            }
        }
        for (int i = 0; i < this.l2.weights.length; i++){
            for (int k=0; k< this.l2.weights[0].length; k ++){
                this.l2.weights = changeSlot(this.BW2, new int[]{i,k});
                double tempLoss = proprogate(batch,target,lloss);
                if (tempLoss < lloss -.0001){
                    System.out.println("New Lowest Found " + tempLoss);
                    lloss= tempLoss;
                }
                
            }
        }
        return lloss;
    }
}