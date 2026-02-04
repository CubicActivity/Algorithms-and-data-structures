//        За дадена низа од N цели броеви, да се избришат елементите со вредност помала од просекот на сите елементи во низата.
//        На пример за низата 1, 2, 3, 4, 5 просекот е (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3, што значи треба да се избришат елементите 1 и 2,
//        што значи низата после оваа трансформација ќе биде 3, 4, 5.
//
//        Влез: Првиот број од влезот е бројот на елементи во низата N,
//        а потоа во следниот ред се дадени самите елементи одделени со празно место.
//
//        Излез: Низата пред и после направената трансформација.
//
//        For a given array with N integers, all the elements that are lower than the average of the whole array need to be deleted.
//        For example for the array 1, 2, 3, 4, 5 the average is (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3 which means that the elements 1 and 2 should be deleted,
//        so the array after the transformation will be 3, 4, 5.

//        Input: The first number in the input is the number of integers in the array N, then in the next line the elements are given, separated by spaces.
//        Output: The array before and after the transformation.
//
//        For example:
//        Input:
//        5
//        1 2 3 4 5
//
//        Result:
//        {1,2,3,4,5}
//        {3,4,5}
//
package Labs.Lab1_Arrays_and_lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab1_home {
    public static void main(String[] args) {

        int n;
        double average=0;
        Scanner s = new Scanner(System.in);
        n = s.nextInt();

        List <Integer> niza1 = new ArrayList<Integer>(n);

        for(int i=0;i<n;i++){
            int x;
            x=s.nextInt();
            niza1.add(x);

            average+=x;
        }
        average/=n;

        for(int i=0;i<niza1.size();i++){
            if(niza1.size()==1){
                System.out.println("{"+niza1.get(i)+"}");
                break;
            }else{
                if(i==0) System.out.print("{"+niza1.get(i));
                else if(i==niza1.size()-1)System.out.println(","+niza1.get(i)+"}");
                else System.out.print(","+niza1.get(i));
            }
        }


        List<Integer> temp = new ArrayList<Integer>(6);
        for(int i=0;i<niza1.size();i++){

            if(niza1.get(i)>=average) {
                temp.add(niza1.get(i));
            }
        }

        niza1=temp;

        for(int i=0;i<niza1.size();i++){
            if(niza1.size()==1){
                System.out.println("{"+niza1.get(i)+"}");
                break;
            }else{
                if(i==0) System.out.print("{"+niza1.get(i));
                else if(i==niza1.size()-1)System.out.println(","+niza1.get(i)+"}");
                else System.out.print(","+niza1.get(i));
            }
        }


    }
}