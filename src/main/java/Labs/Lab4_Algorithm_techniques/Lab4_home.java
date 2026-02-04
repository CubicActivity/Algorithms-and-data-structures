//Дадена е сортирана низа од N броеви и број М кој го бараме во таа низа. Со помош на методата „раздели па владеј“ да се имплементира бинарно пребарување, да се најде бараниот број во таа низа и да се испечати неговата позиција. Доколку бројот не е во низата да е испечати "Ne postoi".
//
//Влез: Првиот број од влезот е големината на низата N и бараниот број М, а потоа во следниот ред се елементите на низата.
//
//Излез: Позицијата на која се наоѓа бараниот број, или "Ne postoi" доколку бројот не се наоѓа во низата.
//
///
//
//We are given an array of N numbers. Using the method "divide and conquer" write a binary search algorithm that searches for a given number M. If the number is found, print the position of the number in the array. If the number is not in the array, print "Ne postoi".
//
//Input: The first number in the input is the size of the array N, and the number we are searching M. Then in the next line are the array elements.
//
//Output: The position of the number we are searching, or "Ne postoi" if no such number exists in the array.
//
//For example:
//Input 	Result
//
//5 3
//1 2 3 4 5
//
//
//
//2
//
//7 7
//1 2 3 4 5 6 7
//
//
//
//6

package Labs.Lab4_Algorithm_techniques;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;


public class Lab4_home {

    static int recursion(int lower, int upper, int target, Integer[] middle, int index){
        if(middle[index]==target){
            return index;
        }
        else if(middle[index]<target){
            lower=index;
            index=index+(int)Math.ceil((upper-lower)/2.0);

        }else {
            upper=index;
            index=index-(int)Math.ceil( (upper-lower)/2.0);
        }

        return recursion(lower, upper, target, middle, index);

    }

    public static void main(String[] args) {
        int len, target;

        Scanner sc = new Scanner(System.in);
        len=sc.nextInt();
        target = sc.nextInt();

        boolean containsTarget=false;

        Integer [] arr = new Integer[len];
        for(int i=0;i<len;i++){
            arr[i]=sc.nextInt();
            if(arr[i]==target)containsTarget=true;
        }

        boolean found=false;
        int middle=(len/2);

        int tmp=middle;

        int lower=0,upper=len-1;


        if(containsTarget)
            System.out.println(recursion(lower,upper,target, arr, tmp));
        else {
            System.out.println("Ne postoi");
        }
//        while(!found){
//            System.out.println("Current middle is" + tmp);
//            System.out.println("currnt arr[tmp]"+arr[tmp]);
//            if(arr[tmp]==target){
//                found=true;
//                System.out.println(tmp);
//            }
//            else if(arr[tmp]>target){
//                upper=tmp;
//                tmp=tmp-(int)Math.ceil( (upper-lower)/2.0);
//            }else{
//                lower=tmp;
//                tmp=tmp+(int)Math.ceil((upper-lower)/2.0);
//
//
//            }
//
//
//        }

    }
}