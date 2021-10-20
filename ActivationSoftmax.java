
public class ActivationSoftmax{
    public double[][] expValue;
    public double[][] probabilites;

    public double[][] expValue(double[][] in){
        double E = 2.71828182846;
        double[][]expValue = new double[in.length][in[0].length];
        for (int i=0; i<in.length; i++){
            for(int k=0; k< in[0].length; k++){
                expValue[i][k] = Math.pow(E,in[i][k]);
            }
        }
        return expValue;
    }
    public double[][] normalize(double[][] in){
        double[][] output = new double[in.length][in[0].length];
        
        double[][] normBase = NumJa.sumColumn(in);
        output = NumJa.divRowByCol(in,normBase);
        return output;
    }
    public double[][] softMax(double[][] in){
        double[][] exp = expValue(in);
        return normalize(exp);
    }

    public void forward(double[][] inputs){
        expValue = softMax(inputs);
        //probabilites = NumJa.sumColumn(expValue);
        probabilites = NumJa.divRowByCol(expValue,NumJa.sumColumn(expValue));
    }
    public void dump(){
        NumJa.printArray(expValue);
    }
}