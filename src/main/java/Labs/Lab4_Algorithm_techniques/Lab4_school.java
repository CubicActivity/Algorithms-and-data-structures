//За дадена низа од броеви да се најде максималниот производ кој се формира со множење на броевите од некоја растечка подниза на таа низа.
//
//Влез: На влез во првиот ред е даден бројот на броеви во низата, а во вториот ред е дадена низата од броеви.
//
//Излез: На излез треба да се испечати максималниот производ кој се бара во задачата.
//
//Пример:
//
//Влез:
//
//6
//
//3 100 4 5 150 6
//
//Излез: 45000
//
//Максималниот производ се формира од растечката подниза 3, 100, 150. Да се забележи дека најдолгата растечка подниза е друга, 3, 4, 5, 6.
//
//
//--------------------------------------------------------------------------------------------------------------
//
//For a given array of integers, find the maximum product formed by multiplication of the numbers of an increasing subarray of the given array.
//
//Input: The first line of the input contains N, the number of elements of the array. The second line of the input contains the array.
//
//Output: The maximum product formed in the described way.
//
//Example:
//
//Input:
//
//6
//
//3 100 4 5 150 6
//
//Output: 45000
//
//The maximum product is formed from the increasing subarray 3, 100, 150. Note that the longest increasing subarray is 3, 4, 5, 6.
//
//
//For example:
//Input 	Result
//
//6
//3 100 4 5 150 6
//
//
//
//45000
//
//8
//10 22 9 33 21 50 41 60
//
//
//
//21780000

package Labs.Lab4_Algorithm_techniques;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Lab4_school {
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        int arr[] = new int[N];
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            arr[i]=Integer.parseInt(pomniza[i]);
        }
        // Vasiot kod tuka

//Can only take numbers that are larger than the current one!

        if(arr.length==1){
            System.out.println(arr[0]);
        }

        if(arr.length>=2) {
            int max = 1;
            int localMax = 1;

            for (int j = 0; j < N; j++) {
                localMax = arr[j];
                int prev = arr[j];
                for (int i = j + 1; i < N; i++) {


                    if (arr[i] > prev && arr[i] >= arr[j]) {

                        localMax *= arr[i];
                        prev = arr[i];
                        // System.out.println(localMax+"E lokalniot maksimum pomnz prev so "+arr[i]);
                        if (localMax > max) {
                            max = localMax;
                        }
                    }

                }

            }

            System.out.println(max);
        }

    }

}