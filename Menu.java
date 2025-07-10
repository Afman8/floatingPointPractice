import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class Menu {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        boolean check = false;
        boolean deep = false;
        System.out.println("floatingPointPractice");
        System.out.println("Follows 8-bit normalised floating point representation");
        System.out.println("exponent = 3, mantissa = 4");
            try {


                    System.out.println("Please select:");
                    System.out.println("1 : Decode");
                    System.out.println("2 : Encode");

                    int in = kbd.nextInt();
                    if (in == 1) {
                        deep = true;
                        String res = Menu.decode(deep);


                            System.out.println("Enter 3 to reveal answer");

                            if (kbd.nextInt() == 3) {
                                Menu.decodeAns(res);
                                check = true;
                            } else {
                                check = false;
                            }
                    } else if (in == 2) {
                        double num = Menu.encode();
                            System.out.println("Enter 3 to reveal answer");
                            deep = false;
                            if (kbd.nextInt() == 3) {
                                Menu.encodeAns(num);
                                check = true;
                            } else {
                                check = false;
                            }

                    }








            }
            catch(InputMismatchException e){

                    System.out.println("Please enter a number specified");
                    kbd.nextLine();
                    System.exit(0);



            }

       if (check) {
           System.out.println("Thank you!");
           System.exit(0);
       }
       else {
           System.out.println("Please enter a number specified");
           System.exit(0);
       }

    }

    public static String decode(boolean deep){
        Random ran = new Random();
        String res = "";
        for (int i = 0; i < 8; i++) {
            if (i == 4) {
                res = res + "1";
            } else {
                res = res + String.valueOf(ran.nextInt(2));
            }

        }

    if (deep) {
        System.out.println("Decode: " + res);
    } return res;
     }
    public static void decodeAns(String res){
        Decoder test = new Decoder(res, 3, 4);
        System.out.println("Answer: " + test.decode(res, 3, 4));
    }

    public static double encode(){
        boolean deep = false;
        String resStr = decode(deep);
        Decoder temp = new Decoder(resStr, 3, 4);
        double res = temp.decode(resStr, 3, 4);
        System.out.println("Encode: " + res);
        return res;
    }

    public static void encodeAns(double num) {
        Encoder test = new Encoder((double)num, 3, 4);
    }

}

