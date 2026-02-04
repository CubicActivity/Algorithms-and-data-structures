//Колку најмалку пати треба да одземеме квадрат на цел број за некој број Х да стане 0?
//
//Влез: Цел број Х од 1 до 10^5.
//
//Излез: Бараниот резултат.
//
//Примери:
//
//Влез 	Излез 	Објаснување
//
//13
//
//
//
//2
//
//
//
//13 - 2*2  = 9 ;   9 - 3*3  = 0. (одземавме 2 пати)
//
//21
//
//
//
//3
//
//
//
//21 - 2*2  = 17;  17 - 4*4 = 1;  1 - 1*1 = 0. (одземавме 3 пати)
//
//25
//
//
//
//1
//
//
//
//25 - 5*5 = 0 . (одземавме 1 пат)
//
//32
//
//
//
//16
//
//
//
//32 - 4*4 = 16;  16 - 4*4 = 0. (одземавме 2 пати)
//
//---------------------------------------------------------------------------
//
//What is the minimum number of times we need to subtract the square of an integer for a number X to become 0?
//
//Input: Integer X from 1 to 10^5.
//
//Output: The desired result.
//
//Examples:
//
//Input	Output	Explanation
//
//13
//
//
//
//2
//
//
//
//13 - 2*2  = 9 ;   9 - 3*3  = 0. (we subtracted 2 times)
//
//21
//
//
//
//3
//
//
//
//21 - 2*2  = 17;  17 - 4*4 = 1;  1 - 1*1 = 0. (we subtracted 3 times)
//
//25
//
//
//
//1
//
//
//
//25 - 5*5 = 0 . (we subtracted 1 time)
//
//32
//
//
//
//16
//
//
//
//32 - 4*4 = 16;  16 - 4*4 = 0. (we subtracted 2 times)
//
//
//For example:
//Input 	Result
//
//13
//
//
//
//2
//
//21
//
//
//
//3
//
//32
//
//
//
//2
//
//12
//
//
//
//3
//
//25
//
//
//
//1
//
//18
//
//
//
//2

package Practice_tasks._3_Algorithm_techniques;

//What is the minimum number of times we need to subtract the square of an integer for a number X to become 0?


import java.util.Arrays;
import java.util.Scanner;

public class Exercise_8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int X = input.nextInt();
        int result;

        int [] dyno = new int[X + 1];
        Arrays.fill(dyno, 100000000);
        dyno[0] = 0;

        for(int i = 1; i <= X; i++){
            for(int j = 1; j*j <= i; j++){
                dyno[i] = Math.min(dyno[i], dyno[i - j*j] + 1);
            }
        }
        result = dyno[X];

        System.out.println(result);
    }
}