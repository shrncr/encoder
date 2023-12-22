/*
 * Sara Hrnciar
 * Dr. K
 * Program 5
 * Encoder class
 * Does ziv encoding. Maybe I could add other encoding methods later
 */

import java.util.ArrayList;

public class Encoder {
    public Encoder(){
    }

    public String ZivEncode(String message){ //combines the the steps. idk why exactly i made so many subroutines but what's done is done.
        ArrayList<String> uniqueList = getUnique(message); //get the list of unique substrs
        String[] toRet = new String[uniqueList.size()]; //returns an array of encoded reps of the unique items
        int largestSize = uniqueList.get(0).length();
        for (int i = 1; i<uniqueList.size(); i++){
            if (largestSize < uniqueList.get(i).length()){
                largestSize = uniqueList.get(i).length();
            }
        } //finds the largest substring in the list
        int minBits = (int)((Math.log(uniqueList.size())) / Math.log(2) + 1); //use that to find the min bits to repr each substr
        //toRet[0] = "1";
        System.out.println(minBits);
        toRet[0] = "0";//(new Numb("1", 10, 2, 1)).convertBasetoR();
        toRet[1] = "1";//(new Numb("2", 10, 2, 1)).convertBasetoR();
        int whichOneAmI = -1;
        String finalEncode;
        String cur;

        for (int i = 2; i<uniqueList.size(); i++){
            cur = uniqueList.get(i);
            int count = i-1;
            while (count>=0){ //from the current substr, look backwards to find which one cur is based off of. ex. fourth substring will look at third, then second, then first item to determine.
                if (uniqueList.get(count).equals(cur.substring(0,cur.length()-1))){
                    //must turn count into binary
                    whichOneAmI = count;
                    break;
                }
                count-=1;
            }
            finalEncode = (new Numb(Integer.toString(whichOneAmI + 1), 10, 2, minBits)).convertBasetoR();
            finalEncode+=cur.charAt(cur.length()-1);
            toRet[i] = finalEncode;
        }
        String encodedString = "";
        for (String thing:toRet){ //string version for final encoded msg
            encodedString+=thing;
        }
        return encodedString;
    }

    public ArrayList<String> getUnique(String message){ //returns a list of all of the unique substrings of the list
        
        int length = message.length();
        int charsLeft = length;
        ArrayList<String> unique = new ArrayList<>();
        String remainingString;
        String uniqueInstance;

        int count = 0;
        while (charsLeft != 0){ //while we still have chars in the original msg to look at
            remainingString = message.substring(length-charsLeft, length); //find whats left to look at 
            uniqueInstance = remainingString.substring(0,1); //initial unique str
            count = 1;
            for (String instance:unique){ //check to make sure you cant add more chars to the unique string
                if (instance.equals(uniqueInstance)){
                    count++;
                    if (count>remainingString.length()){
                        count = remainingString.length();
                    }
                    uniqueInstance = remainingString.substring(0,count);
                }
            }
            count = 0; //reset count
            charsLeft -= uniqueInstance.length(); //keep track of how many chars there are left to look at
            unique.add(uniqueInstance); //add to the arr
        }
        return unique;
    }
}
