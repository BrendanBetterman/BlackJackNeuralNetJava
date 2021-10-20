
public class ActivationReLU{
    public double[][] output;
    public void forward(double[][] inputs){
        output= new double[inputs.length][inputs[0].length];
        for(int i =0; i<inputs.length; i++){
            for(int k=0; k<inputs[0].length; k++){
                output[i][k] = NumJa.ReLU(inputs[i][k]);
            }
        }
    }
    public void dump(){
        NumJa.printArray(output);
    }
}