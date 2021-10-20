import java.util.Random;

public class NumJa{
    public static double[] randArray(int size){
        Random random = new Random();
        double[] out = new double[size];
        for(int i=0; i<size;i++){
            out[i] = random.nextDouble() -0.5;
        }
        return out;
    }
    public static double[][] rand2dArray(int x,int y){
        Random random = new Random();
        double[][] out = new double[x][y];
        for(int i=0; i<x;i++){
            for(int k=0; k<y;k++){
                out[i][k] = 0.05 * random.nextDouble() -0.5;
            }
            
        }
        return out;
    }
    public static double[][] add2dArray(double[][] a,double[][]b){
        for (int i=0; i<a.length; i++){
            for (int k=0; k<a[0].length; k++){
                a[i][k] += b[i][k];
            }
        }
        return a;
    }
    public static void printArray(double[] array){
       
        for(int i=0; i<array.length; i++){
            System.out.print(array[i]);
            if (i<array.length -1){
                System.out.print(", ");
            }else{
                System.out.print("\n");
            }
        }
    }
    public static void printArray(double[][] array){
        for(int i=0; i<array.length; i++){
            printArray(array[i]);
        }
    }
    public static double dotProduct(double[] input, double weight){
        double sum =0;
        for (int i = 0; i < input.length; i++){
            sum+= input[i] * weight;
        }
        return sum;
    }
    public static double dotProduct(double[] input, double[] weight){
        double sum =0;
        for (int i = 0; i < input.length; i++){
            sum+= input[i] * weight[i];
        }
        return sum;
    }
    public static double[] dotDivide(double[][] weight, double input){
        double[] output = new double[weight.length];
        for(int i =0; i<weight.length; i++){
            for(int k=0; k<weight[0].length; k++){
                output[i] = weight[i][k] / input;
            }
        }
        return output;
    }
    public static double[] dotProduct(double[][] weight, double[] input){
        double[] output = new double[weight.length];
        for(int i =0; i<weight.length; i++){
            output[i] = dotProduct(weight[i],input);
        }
        return output;
    }
    public static double[] dotProduct(double[][] weight, double input){
        double[] output = new double[weight.length];
        for(int i =0; i<weight.length; i++){
            output[i] = dotProduct(weight[i],input);
        }
        return output;
    }
    public static double[][] dotProduct(double a[][], double b[][]){
        if(a.length == 0) return new double[0][0];
        if(a[0].length != b.length) return null; //invalid dims
     
        int n = a[0].length;
        int m = a.length;
        int p = b[0].length;
        double ans[][] = new double[m][p];
     
        for(int i = 0;i < m;i++){
           for(int j = 0;j < p;j++){
              for(int k = 0;k < n;k++){
                 ans[i][j] += a[i][k] * b[k][j];
              }
           }
        }
        return ans;
        }
    public static double mean(double[]in){
        return NumJa.sum(in)/in.length;
    }
    public static double[][] divRowByCol(double[][]a,double[][]b){
        double[][] out = new double[a.length][a[0].length];
        for(int i =0; i<a.length; i++){
            for(int u=0; u<a[0].length; u++){
                out[i][u] = a[i][u] / b[i][0];
            }
        }

        return out;
    }
    public static double[][] sumColumn(double[][]in){
        double[][]out = new double[in.length][1];
        for(int i =0; i<in.length; i++){
            out[i][0] = sum(in[i]);
        }
        return out;
    }
    public static double sum(double[][]in){
        double sum=0;
        for(int i=0; i<in.length;i++){
            sum+=sum(in[i]);
        }
        return sum;
    }
    public static double sum(double[]in){
        double sum=0;
        for(int i=0; i<in.length;i++){
            sum+=in[i];
        }
        return sum;
    }
    public static double[][] transpose(double[][] input){
        double[][] output = new double[input[0].length][input.length];
        for (int i=0; i< input[0].length;i++){
            for (int k=0; k<input.length;k++){
                output[i][k] = input[k][i];
            }
        }
        return output;
    }
    public static double[] dotSum(double[] input, double[] bias){
        double[] output = new double[input.length];
        for(int i = 0; i< input.length; i++){
            output[i] = input[i] + bias[i];
        }
        return output;
    }
    public static double[][] dotSum(double[][] input, double[] bias){
       
        double[][] output = new double[input.length][input[0].length];
        for(int i = 0; i< input.length; i++){
            output[i] = dotSum(input[i],bias);
        }
        return output;
    }
    public static double[][] dotAdd(double[][] in, double in2){
        for(int i=0; i<in.length; i++){
            for(int k=0; k<in[0].length; k++){
                in[i][k] += in2;
            }
        }
        return in;

    }
    public static double ReLU(double summedInput){
        return Math.max(0,summedInput);
    }
    
}