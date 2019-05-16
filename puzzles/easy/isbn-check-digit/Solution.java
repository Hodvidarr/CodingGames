import java.util.*;
import java.io.*;
import java.math.*;

/**
 *    https://www.codingame.com/ide/puzzle/isbn-check-digit
 * by Hodvidar
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<String> invalids = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String ISBN = in.nextLine();
            if(!isCorrect(ISBN))
            {
                System.err.println("isCorrect("+ISBN+") == FALSE");
                invalids.add(ISBN);
            }
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        System.out.println(invalids.size()+" invalid:");
        for(String s : invalids)
            System.out.println(s);
    }
    
    private static boolean isCorrect(String aISBN)
    {
        System.err.println("isCorrect("+aISBN+")");
        int length = aISBN.length();
        if(length != 13 && length != 10)
            return false;
            
        System.err.println("length = "+length);
        
        if(!aISBN.matches("[0-9]+"))
        {
            if (length != 10)
                return false;
            if(!aISBN.substring(0, 9).matches("[0-9]+"))
                return false;
            if(!aISBN.substring(9, 10).equals("X"))
                return false;
        }

        // 10
        if(length == 10)
        {
            int m = 10;
            int prod = 0;
            int lastDigit = -1;
            for(char c : aISBN.toCharArray())
            {
                int a=Character.getNumericValue(c);
                if(m == 1)
                {
                   lastDigit = (c == 'X') ? 10 : a;
                   break;
                }
                // System.err.println("prod= "+prod+" + a*m:"+a+"*"+m);
                prod += a * m;
                
                m--;
            }
            System.err.println("prod= "+prod);
            int code = prod % 11;
            System.err.println("prod % 11= "+code);
            code = (code == 0) ? 0 : 11 - code;
            System.err.println("(10)  code = "+code);
            return code == lastDigit;
        }
        
        // 13
        int m = 0;
        int prod = 0;
        int lastDigit = -1;
        for(char c : aISBN.toCharArray())
        {
            int a=Character.getNumericValue(c);
            if(m == 12)
            {
               lastDigit = a;
               break;
            }
            int i = (m%2 == 0) ? 1 : 3;
            // System.err.println("prod= "+prod+" + a*i:"+a+"*"+i);
            prod += a * i;
            m++;
        }
        System.err.println("prod= "+prod);
        int code = prod % 10;
        System.err.println("prod % 10= "+code);
        code = (code == 0) ? 0 : 10 - code;
        System.err.println("(13)  code = "+code);
        return code == lastDigit;
    }
    
}
