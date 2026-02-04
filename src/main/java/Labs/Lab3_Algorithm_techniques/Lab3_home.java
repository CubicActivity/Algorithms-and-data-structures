//Дадени се Н цифри. Да се напише алгоритам кој ќе го даде најголемиот можен број составен од тие цифри.
//Влез: Првиот број од влезот е бројот на цифри N, а потоа во следниот ред се цифрите.
//Излез: Најголемиот број кој може да се состави од тие цифри
//
//We are given N digits. Write an algorithm that composes the largest possible number from those digits.
//Input: The first number in the input is the number of digits N, then in the next line are the given digits.
//Output: The maximum possible number containing those digits
//
//For example:
// Input:
// 5
// 1 2 3 4 5
// Result:
// 54321
//
// Input:
// 7
// 9 7 3 7 9 3 1
// Result:
// 9977331

package Labs.Lab3_Algorithm_techniques;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Lab3_home {
    public static void main(String[] args) {

        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        Integer [] Arr = new Integer[n];

        for(int i=0;i<n;i++){
            Arr[i]=sc.nextInt();

        }

        Arrays.sort(Arr, Collections.reverseOrder());

        for(int i=0;i<n;i++){
            System.out.print(Arr[i]);
        }

    }
}