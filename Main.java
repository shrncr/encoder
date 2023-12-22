/*
 * Dr K
 * Program 5
 * Ziv encoding
 * Main file
 */


import java.util.*;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the string youd like to encode.");
        String message = sc.nextLine();

        String binaryRep = ""; //some reuable vars
        char let;
        Numb letNumb;
        String letBinaryRep;

        for (int i = 0; i <message.length(); i++){ //turn each char of the message into num obj so you can convert its char val to binary
            let = (message.charAt(i));
            letNumb = new Numb(Integer.toString((int)let), 10,2,8);
            letBinaryRep = letNumb.convertBasetoR();
            binaryRep+= letBinaryRep;
        }

        Encoder encoder = new Encoder();
        String encoded = encoder.ZivEncode(binaryRep);  //zivvyencode the binary representation of the users inp

        while (encoded.length()%8 != 0) { //make sure final answ is / by 8
            encoded = "0" + encoded;
        }
        
        String encodedChars = "";
        char curChar;
        for (int i=8;i<=encoded.length();i+=8){ //turn each set of 8 bits into 8bit char repr
            curChar = (char)(new Numb(encoded.substring(i-8, i),2,10,8).convertRToBase());
            encodedChars+=curChar;
        }
        //outpu
        System.out.print("original message in char: ");
        System.out.println(message);
        System.out.print("original message in binary: ");
        System.out.println(binaryRep);
        System.out.print("encoded message in binary (with zeros @ front): ");
        System.out.println(encoded);
        System.out.print("encoded message turned to char: ");
        System.out.println(encodedChars);
    }
    
    
}
