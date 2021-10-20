public class Loss{
    //CategoricalCrossentropy
    double[] outputArray;
    double outputSample;
  

    public static double loss(double[] in,double target){
        int tar = (int)Math.round(target);
        if (Math.abs(in[tar]) < 0.0000001){
            if(in[tar] < 0){
                return -Math.log(0.0000001);
            }else{
                return -Math.log(-0.0000001);
            }
        }
        return -Math.log(in[tar]);
    }
    
   public static double[] lossbatch(double[][] in, double[] target){
       double[] out = new double[in.length];
        for(int i =0; i<in.length; i++){
            out[i] = loss(in[i],target[i]);
        }
    //return NumJa.mean(out);
    return out;
   }
    public void forward(double[][] in,double[]target){
        outputArray = lossbatch(in,target);
        outputSample = NumJa.mean(outputArray);
    }
}