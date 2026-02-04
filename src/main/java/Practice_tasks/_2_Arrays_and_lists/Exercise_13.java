//За дадена двојно поврзана листа од N цели броеви, треба да се најде бројот на елементи такви што просекот на елементите од пред него во листата е поголем од просекот на елементи после него во листата.
//
//Влез: Првиот број од влезот е бројот на елементи во листата N, а потоа во следниот ред се дадени самите елементи одделени со празно место.
//
//Излез: Бројот на елементи што го задоволуваат условот.
//
///
//
//For a given doubly-linked list with N integers, you need to find the number of elements such that the average of all elements before it is bigger than the average of all elements after it in the list.
//
//Input: The first number in the input is the number of integers in the list N, then in the next line the elements are given, separated by spaces.
//
//Output: The number of elements that satisfy the condition.
//
//For example:
//Input 	Result
//
//5
//1 2 3 4 5
//
//
//
//0
//
//4
//5 4 3 2
//
//
//
//2

package Practice_tasks._2_Arrays_and_lists;

import java.util.ArrayList;
import java.util.Scanner;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}


class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while(current!=null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if(tmp!=null && tmp.pred!=null) {
            first=tmp.pred;
        }
    }
}

public class Exercise_13 {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();

        DLL<Integer> list = new DLL<Integer>();

        for(int i =0;i<n;i++){
            list.insertLast(sc.nextInt());
        }


        DLLNode<Integer> tmp = list.getFirst();




        // left sum > big sum for x elements, find x
        int counter=0;
        while(tmp!=null){

            DLLNode<Integer> tmpLeft=tmp.pred;
            DLLNode<Integer> tmpRight=tmp.succ;

            int leftSum=0,rightSum=0,leftN=0,rightN=0;



            while(tmpLeft!=null){
                leftN++;
                leftSum+=tmpLeft.element;

                tmpLeft=tmpLeft.pred;

            }

            while(tmpRight!=null){
                rightN++;
                rightSum+=tmpRight.element;

                tmpRight=tmpRight.succ;
            }

            double leftAvg=0;
            double rightAvg=0;
            if(leftN>0 && rightN>0){
                leftAvg=(double)leftSum/leftN;
                rightAvg= (double)rightSum / rightN;
                if(leftAvg>rightAvg){
//                    System.out.println("lft avg is "+leftAvg+" right avg is "+rightAvg);
                    counter++;
                }
            }




            tmp=tmp.succ;
        }





        System.out.println(counter);



    }
}
