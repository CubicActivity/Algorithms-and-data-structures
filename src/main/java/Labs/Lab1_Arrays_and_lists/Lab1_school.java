//  Дадена е еднострана поврзана листа чии што јазли содржат по еден String.
//  Дополнително, даден е и уште еден природен број L.
//  Во дадената листа потребно е сите јазли коишто содржат String со должина L да се поместат на крај на листата, задржувајќи го и оригиналниот распоред на елементите.
//
//  Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N,
//      па во следните N редови се дадени самите елементи (String-ови) од листата. На крај, во последниот ред е даден бројот L.
//
//  Излез: На излез треба да се испечати листата пред и после промената.
//
//  You are given a single linked list with String nodes. Additionally, you are given one more integer L. You need to move all nodes from the list that contain a String with exactly L characters to the end of the list, keeping the original order of elements as well.
//
//  Input: In the first line from the input the number of elements in the list is given - N, and then in the next N lines the elements themselves. In the last line, the integer L is given.
//
//  Output: The list before and after the transformation
//
//  For example:
//
// Input:
//  3
//  Sky
//  Blue
//  Cloud
//  5
//
// Result:
//  Sky->Blue->Cloud
//  Sky->Blue->Cloud
//
// Input:
// 4
// Apple
// Banana
// Pear
// Watermelon
// 6
//
// Result:
//Apple->Banana->Pear->Watermelon
//Apple->Pear->Watermelon->Banana

package Labs.Lab1_Arrays_and_lists;

import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}


class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
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
            SLLNode<E> tmp = first;
            ret += tmp.element;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "->" + tmp.element;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }
    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ!=null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
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

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}


public class Lab1_school {
    public static void main(String[] args) {

        SLL<String> list = new SLL<String>();
        int n;
        Scanner sc = new Scanner(System.in);

        n=sc.nextInt();

        sc.nextLine();
        String s;
        for(int i=0;i<n;i++){
            s = sc.nextLine();
            list.insertLast(s);
        }

        int dolzina;
        dolzina=sc.nextInt();

//print linkedlist
        SLLNode<String> tmp = list.getFirst();
        for(int i=0;i<list.size();i++){


            if(i==list.size()-1){
                System.out.print(tmp.element);

            }else System.out.print(tmp.element+"->");

            tmp=tmp.succ;
        }System.out.println();

        // order the strings by increasing length

        if(list.size()>1){

            int index=0;

            for(int j=0;j<list.size();j++){

                SLLNode<String> node = list.getFirst();
                for(int k=0;k<index;k++){
                    node=node.succ;
                }

                //check the length of the string
                if(node.element.length()==dolzina){

                    list.insertLast(node.element);
                    list.delete(node);



                    //set the last element then shift everything to the left from the i+ index
                }else index++;



            }
        }




        //print 2nd time

        SLLNode<String> tmp1 = list.getFirst();
        for(int i=0;i<list.size();i++){


            if(i==list.size()-1){
                System.out.print(tmp1.element);

            }else System.out.print(tmp1.element+"->");

            tmp1=tmp1.succ;
        }System.out.println();


    }
}