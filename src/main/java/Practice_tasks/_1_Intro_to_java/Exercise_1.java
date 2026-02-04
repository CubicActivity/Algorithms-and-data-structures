//За дадена низа од случајни броеви кои се внесуваат од стандарден влез, да се направи преместување на сите нули на почеток на низата. На стандарден излез да се испечати трансформираната низа.
//
///
//
//For a given array of random numbers given from standard input, perform a shift of all zeros at the beginning of the sequence. Print the transformed array to standard output.
//
//For example:
//Input 	Result
//
//12
//1 9 8 4 0 0 2 7 0 6 0 9
//
//
//
//Transformiranata niza e:
//0 0 0 0 1 9 8 4 2 7 6 9

package Practice_tasks._1_Intro_to_java;
import java.util.Scanner;

public class Exercise_1
{
    static void pushZerosToBeginning(int arr[], int n)
    {
        int index=0;

        for(int i=0;i<n;i++){
            if(arr[i]==0){
                index++;
            }
        }
        int[] lino3 = new int[n];
        int i=0;
        for(;i<index;i++){
            lino3[i]=0;
        }
        for(int j=0;j<n;j++){
            if(arr[j]!=0){
                lino3[i]=arr[j];
                i++;
            }
        }
        arr=lino3;
        System.out.println("Transformiranata niza e:");
        for(i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
    }

    public static void main (String[] args)
    {
        int n;
        Scanner lino = new Scanner(System.in);
        n=lino.nextInt();
        int[] lin2 = new int[n];

        int temp;

        for(int i=0;i<n;i++){
            temp=lino.nextInt();
            lin2[i]=temp;
        }

        pushZerosToBeginning(lin2,n);


    }
}