//Даден е код на модифициран програмски јазик каде функциите се претставени со отворен и затворен таг ("imeFunkcija" и "endimeFunkcija"). Ваша задача е да дефинирате дали даден програмски код е валиден.
//
//
//Пример валиден код:
//
//func1
//
//func2
//
//endfunc2
//
//func3
//
//endfunc3
//
//endfunc1
//
//
//Пример невалиден код (испуштен е затворен таг за func3):
//
//func1
//
//func2
//
//endfunc2
//
//func3
//
//endfunc1
//
//
//Влез: Код со модифициран програмски јазик, каде секој таг е напишан во нов ред. Се внесуваат тагови се додека не се внесе "x".
//
//
//Излез: "Valid" - доколку е валиден кодот, "Invalid" - доколку не е валиден кодот.
//
///
//
//We are given code in a modified programming language is given, where functions are represented by an opening and closing tag ("functionName" and "endfunctionName"). Your task is to determine whether the given code is valid.
//
//
//Example of valid code:
//
//func1
//
//func2
//
//endfunc2
//
//func3
//
//endfunc3
//
//endfunc1
//
//Example of invalid code (missing closing tag for func3):
//
//func1
//
//func2
//
//endfunc2
//
//func3
//
//endfunc1
//
//
//Input: Code in a modified programming language, where each tag is written in a new line. Tags are entered until "x" is entered.
//
//Output: "Valid" if the code is valid, "Invalid" if the code is not valid.
//
//For example:
//Input 	Result
//
//func1
//func2
//endfunc2
//func3
//endfunc3
//endfunc1
//x
//
//
//
//Valid
//
//func1
//func2
//endfunc2
//func3
//endfunc1
//x
//
//
//
//Invalid

package Labs.Lab5_One_dimensional_data_structures;
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


public class Lab5_home {

    public static void main(String[] args){

        boolean valid=true;

        DLL<Character> opening = new DLL<Character>();

        Scanner sc = new Scanner(System.in);

        String input="func1";

        while(input.charAt(0)!='x'){

            input=sc.nextLine();
//            System.out.println("INPUTEED"+input);

            if(input.charAt(0)=='f'){
                opening.insertLast(input.charAt(4));
//                System.out.println("F added "+ (input.charAt(4)-'0'));
            }
            else if(input.charAt(0)=='e'){

                if(opening.getLast()==null) {
                    valid=false;
                    break;
                }
//                System.out.println("e check "+ (input.charAt(7)) + " and "+opening.getLast());
                if((input.charAt(7))==opening.getLast().element){
//                    System.out.println("removed");
                    opening.deleteLast();
                }
                else{
                    valid=false;
                    break;
                }
            }
        }

        if(opening.getFirst()!=null || !valid){
            System.out.println("Invalid");
        }else{
            if(valid)System.out.println("Valid");
        }




    }
}