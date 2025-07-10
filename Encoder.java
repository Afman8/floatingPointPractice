import java.util.BitSet;
import java.util.Optional;

public class Encoder {
    private double number;
    private int exponentLength;
    private int mantissaLength;

    public Encoder(double number, int exponentLength, int mantissaLength) {
        this.number = number;
        this.exponentLength = exponentLength;
        this.mantissaLength = mantissaLength;
        encode((double) number, exponentLength, mantissaLength);

    }

    public String encode(double number, int exponentLength, int mantissaLength) {
        String sign;
        if (number < 0){
            sign = "1";
        }
        else
            sign = "0";
        double[] arrPow = new double[37];
        double[] arrRes = new double[mantissaLength + exponentLength + 1];
        int[] exArr = new int[4];
        int[] negEx = new int[37];
        for (int i = 20, j = 0; i > -17; i--, j++) {
            double temp = Math.pow(2, i);
            arrPow[j] = temp;
            negEx[j] = i;
            if (i == -2)
            {
                 }
        }

        double temp = (double) number;
        int[] mantRange = {-1, -2, -3, -4};
        if (temp < 0)
            temp = -temp;
        outerloop:
        for (int i = 0, j = 0, k = 0; i < arrPow.length; i++, k++) {
            if (temp >= arrPow[i]) {
                temp = temp - arrPow[i];
                if (arrPow[i] < 1)
                {exArr[j] = negEx[i];
                    }
                else
                {exArr[j] = BitSet.valueOf(new long[]{(long) arrPow[i]}).nextSetBit(0);}



                j++;
                for (int z = 0; z < arrPow.length; z++) {
                    if (temp == arrPow[z]) {
                        if (arrPow[z] < 1)
                        {exArr[j] = negEx[z];
                            }
                        else
                        { exArr[j] = BitSet.valueOf(new long[]{(long) arrPow[z]}).nextSetBit(0);}
                        break outerloop;

                    }
                }




            }
        }

        int exponent = exArr[0] - -1;
        String exponentStr = excessEncode(exponent, exponentLength);

        int[] mantArr = new int[mantissaLength];
        mantArr[0] = -1;

        for (int i = 1; i < mantArr.length - 1; i++){
            int tempo = exArr[i] - exponent;
            int j = 0;
            for (int k = 0; k < mantRange.length; k++){
                if (tempo == mantRange[k]){
                    j = k;
                }

            }
            mantArr[j] = tempo;


        }

        double pos = -1;
        double[] arrInt = new double[mantissaLength];
        for (int i = 0; i < arrInt.length; i++){
            arrInt[i] = pos;
            pos--;

        }



        char[] arr = new char[mantissaLength];
        arr[0] = '1';
        for (int i = 1; i < mantArr.length; i++){
            if (mantArr[i] == arrInt[i]) {
                arr[i] = '1';
            }
            else
                arr[i] = '0';
        }
        String mantissa = new String(arr);

        String res = sign + exponentStr + mantissa;
        System.out.println("Answer: " + res);
        return res;





    }

    public String excessEncode(int exponent, int exponentLength){
        double conv = exponent + Math.pow(2, (exponentLength - 1));
        char[] arr = new char[exponentLength];
        double[] arrInt = new double[exponentLength];
        int pos = 0;
        for (int i = arrInt.length - 1; i >= 0; i--){
            arrInt[i] = Math.pow(2, pos);
            pos++;
        }
        for (int i = 0; i < arrInt.length; i++){
            if (conv >= arrInt[i]){
                conv = conv - arrInt[i];
                arr[i] = '1';
            }
            else
                arr[i] = '0';


        }
        String res = new String(arr);
        return res;

    }


}
