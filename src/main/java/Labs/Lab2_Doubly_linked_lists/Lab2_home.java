//За дадена двојно поврзана листа од N цели броеви,
// треба да се најде бројот на елементи такви што просекот на елементите од пред него во листата е поголем од просекот на елементи после него во листата.
//
//Влез: Првиот број од влезот е бројот на елементи во листата N, а потоа во следниот ред се дадени самите елементи одделени со празно место.
//
//Излез: Бројот на елементи што го задоволуваат условот.
//
//For a given doubly-linked list with N integers, you need to find the number of elements such that the average of all elements before it is bigger than the average of all elements after it in the list.
//
//Input: The first number in the input is the number of integers in the list N, then in the next line the elements are given, separated by spaces.
//
//Output: The number of elements that satisfy the condition.
//
//For example:
// Input:
//
// 5
// 1 2 3 4 5
//
//
// Result:
// 0
//
// Input:
// 4
// 5 4 3 2
//
//
// Result:
// 2


package Labs.Lab2_Doubly_linked_lists;
import java.util.Scanner;


public class Lab2_home {

    public static void main(String[] args) {

        int n;
        Scanner s = new Scanner(System.in);

        n = s.nextInt();

        DLL<Integer> list= new DLL<Integer>();


        for(int i=0;i<n;i++){
            list.insertLast(s.nextInt());
        }

        int result=0;
        //check if left side avg from index is > right side avg and increment index

        for(int i=0;i<n;i++){

            int lsum=0,rsum=0,lcount=0,rcount=0;
            float lavg=0, ravg=0;
            DLLNode<Integer> tmp =list.getFirst();

            for(int j=0;j<i;j++){
                lcount++;
                lsum+=tmp.element;
                tmp=tmp.succ;
//                System.out.println(i+" left is " + lsum + " "+lcount);
            }
            tmp=tmp.succ;
            if(lcount>0)
                lavg= (float) lsum /lcount;
            else continue;

            for(int j=i+1;j<n;j++){
                rcount++;
                rsum+=tmp.element;
                tmp=tmp.succ;
//                System.out.println(i+" right is " + rsum + " "+rcount);
            }

            if(rcount>0)
                ravg= (float) rsum /rcount;
            else continue;


            if(lavg>ravg){
//                System.out.println(lavg+"is greater than "+ravg + " at iteration "+i);
                result++;
            }
//            System.out.println("Incr");
        }

//        DLLNode<Integer> tmp =list.getFirst();
//        for(int i=0;i<n;i++){
//            System.out.println(tmp.element);
//            tmp=tmp.succ;
//        }

        System.out.println(result);

    }
}