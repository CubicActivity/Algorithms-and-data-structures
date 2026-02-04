/*
Дадена е низа со N природни броеви. Треба да се сортира низата со помош на таканареченото shaker (cocktail) сортирање. Ова сортирање е варијација на сортирањето со меурчиња (bubble sort) со тоа што во секоја итерација низата се изминува два пати. Во првото поминување најмалиот елемент се поместува на почетокот на низата, а при второто најголемиот елемент се поместува на крајот.

Во првиот ред од влезот даден е бројот на елементи во низата N, а во вториот ред се дадени броевите. На излез треба да се испечати низата по секое изминување во посебен ред.

Име на класата: ShakerSort

-------------------

Given a sequence of N natural numbers. The array should be sorted using the so-called shaker (cocktail) sort. This sort is a variation of the bubble sort in that in each iteration the array is traversed twice. In the first pass, the smallest element is moved to the beginning of the array, and in the second pass, the largest element is moved to the end.

In the first line of the input, the number of elements in the array N is given, and in the second line, the numbers are given. The output should print the string after each pass in a separate line.

Class Name: ShakerSort

For example:
Input 	Result

8
6 10 13 5 8 17 2 5



2 6 10 13 5 8 17 5
2 6 10 5 8 13 5 17
2 5 6 10 5 8 13 17
2 5 6 5 8 10 13 17
2 5 5 6 8 10 13 17
2 5 5 6 8 10 13 17
2 5 5 6 8 10 13 17
2 5 5 6 8 10 13 17

 */

package Practice_tasks._5_Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShakerSort {

    static void shakerSort(int a[], int n)
    {
        boolean changes=true;
        while(changes){
            changes=false;
            for(int k=n-1;k>0;k--){
                if(a[k]<a[k-1]){
                    int tmp=a[k-1];
                    a[k-1]=a[k];
                    a[k]=tmp;

                    changes=true;
                }
            }
            for(int l=0;l<n;l++)
                System.out.print(a[l]+" ");
            System.out.println();



            for(int k=0;k<n-1;k++){
                if(a[k]>a[k+1]){
                    int tmp=a[k+1];
                    a[k+1]=a[k];
                    a[k]=tmp;

                    changes=true;
                }
            }
            for(int l=0;l<n;l++)
                System.out.print(a[l]+" ");
            System.out.println();



        }



        // Vasiot kod tuka
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        shakerSort(a,n);
    }
}