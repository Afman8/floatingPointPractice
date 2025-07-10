import java.math.BigDecimal;

public class Decoder {
    private String decimalStr;
    private int exponentLength;
    private int mantissaLength;

    public Decoder(String decimalStr, int exponentLength, int mantissaLength){
        this.decimalStr = decimalStr;
        this.exponentLength = exponentLength;
        this.mantissaLength = mantissaLength;
        decode(decimalStr,exponentLength, mantissaLength);
    }

    public double decode(String decimalStr, int exponentLength, int mantissaLength){

        char[] mantissaArrTemp = new char[mantissaLength];
        int[] mantissaArr = null;
        int num = 0;

        double exponent = excessDecode((0 + exponentLength), 0, decimalStr);
        for (int i = mantissaArrTemp.length - 1, j = decimalStr.length() - 1; i >= 0; i--, j--){
            mantissaArrTemp[i] = decimalStr.charAt(j);

        }

        for (int i = -1, j = mantissaArrTemp.length - 1; j >= 0; j--, i--){
            if ( mantissaArrTemp[j] == '1')
                num += 1;
        }
        mantissaArr = new int[num];

        for (int y = mantissaArr.length - 1, i = -1, j = 0; j < mantissaArrTemp.length; j++, i--){
            if ( mantissaArrTemp[j] == '1'){
                mantissaArr[y] = i;
                y--;
            }

        }
        double result = 0.0;
        for (int i = mantissaArr.length - 1; i >= 0; i--){
            result += Math.pow(2, mantissaArr[i]);



        }

        double temp2 = Math.pow(2, exponent);
        result = temp2 * result;

        if (decimalStr.charAt(0) == '1')
        {

            return -result;}
        else
        {
            return result;}


    }

    public double excessDecode(int start, int end, String decimalStr){

           if (decimalStr.charAt(end + 1) == '1'){
                return positiveExcessDecode(start, end, decimalStr);

            }
            else{
                return negativeExcessDecode(start, end, decimalStr);
            }

    }

    public double positiveExcessDecode(int start, int end, String decimalStr){
       int pos = 0;
       double res = 0;

        for (int i = start; i > end; i--){
            if (decimalStr.charAt(i) == '1'){
                res += Math.pow(2, pos);
            }
            pos += 1;
        }

        res = (res - Math.pow(2, start - 1));
        return res;
    }

    public double negativeExcessDecode(int start, int end, String decimalStr){
        char[] arr = new char[start];
        char[] temp = new char[start];
        temp[0] = '1'; // negative always begins with 0 so first num is always 1

        for (int i = start, j = arr.length - 1; i > end; i--, j--){
            arr[j] = decimalStr.charAt(i);

        }
        // + 1 method
        for (int i = arr.length - 1; i > 0; i--){
           if (arr[i] == '1'){
               temp[i] = '1';
           }
           else
               temp[i] = '0';
        }
        // invert all bits
        for (int i = temp.length - 1; i >= 0; i--){
            if (temp[i] == '1')
                temp[i] = '0';
            else
                temp[i] = '1';
        }
        int carry = 0;
        if (temp[temp.length - 1] == '1') {
            carry = 1;
            temp[temp.length - 1]  = '0';
        }
        else { carry = 0;
            temp[temp.length - 1]  = '1';
        }
        // add 000...0001
        for (int i = temp.length - 2; i >= 0; i--){
            if (temp[i] == '1' && carry == 1){
                temp[i] = '0';
                carry = 1;
            }
            else if (temp[i] == '0' && carry == 1){
                temp[i] = '1';
                carry = 0;
            }




        }
        double result = positiveExcessDecode(temp);
        double negativeResult = result; // This was - before but then with removing it exponent became -
        return negativeResult;
    }

    public double positiveExcessDecode(char[] arr){
        int pos = 0;
        double res = 0;

        for (int i = arr.length - 1; i >= 0; i--){
            if (arr[i] == '1'){
                res += Math.pow(2, pos);
            }
            pos += 1;
        }



        return -res;
    }


    }


