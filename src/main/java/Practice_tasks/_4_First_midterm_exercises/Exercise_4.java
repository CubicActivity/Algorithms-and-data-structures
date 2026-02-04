/*

Најди ја најдолгата опаѓачка секвенца во една низа. Броевите во секвенцата не мора да се наоѓаат на последователни индекси во низата.

/

Find the longest decreasing sequence in an array. The numbers in the sequence don't need to be on consecutive indices in the array.

For example:
Input 	Result

8
1 2 3 4 5 6 7 8



1

 */

package Practice_tasks._4_First_midterm_exercises;

import java.util.Scanner;


public class LDS {
//Longest decreasing sequence

    private static int najdolgaOpagackaSekvenca(int[] a) {

        int [] dp = new int[a.length];
        int maxLength=1;

        for(int i=0;i<a.length;i++){
            dp[i]=1; //for the very same element
            for(int j=0;j<i;j++){
                if(a[j]>a[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            maxLength = Math.max(maxLength,dp[i]);
        }

        return maxLength;
        // Vasiot kod tuka

    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}
