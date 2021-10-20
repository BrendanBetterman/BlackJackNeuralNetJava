
public class Layer{
    public double[][] weights;
    double[] bias ;
    public double[][] output;
    public Layer(int numInputs,int numNeurons){
        double[][] weights = new double[numNeurons][numInputs];//hiddenLayer
        for(int i =0; i <numNeurons; i++){
            weights[i] = NumJa.randArray(numInputs);
        }
        this.weights = weights;
        this.bias = NumJa.randArray(numNeurons);
    }
    public void forward(double[][] inputs){
        output = NumJa.dotSum(NumJa.dotProduct(inputs,NumJa.transpose(this.weights)),bias);
    }
    public void dump(){
        NumJa.printArray(output);
    }
    public void dumpweight(){
        NumJa.printArray(this.weights);
        System.out.print("\n");
        NumJa.printArray(this.bias);
    }

}