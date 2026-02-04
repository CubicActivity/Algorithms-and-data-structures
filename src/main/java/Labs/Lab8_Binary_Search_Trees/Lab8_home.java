//Дадено ви е бинарно пребарувачко дрво со N цели броеви.
//
//Напишете функција со која за бинарно пребарувачко дрво и избран елемент Т од дрвото:
//
//    ќе најдете на која длабочина се наоѓа елементот T во дрвото
//
//Ќе треба да ја искористите таа функција Q пати при градењето на дрвото.
//
//Влезот ќе содржи N+Q редови од видот
//
//    insert value - Треба да ја вметнете вредноста value во дрвото.
//
//    ask value - Треба да одговорите на која длабочина во дрвото се наоѓа јазелот со вредност value
//
//Пример и структура на влезот:
//
//Влез
//
//
//Објаснување
//
//
//Излез
//
//10 7
//insert 6
//insert 3
//insert 7
//ask 3
//ask 6
//insert 4
//insert 1
//insert 2
//insert 5
//insert 9
//ask 3
//ask 9
//insert 8
//insert 10
//ask 8
//ask 4
//ask 5
//
//
//
//Прикажано е изгледот на дрвото при секое од 7те прашања во влезот
//
//
//
//2
//1
//2
//3
//4
//3
//4
//
//
//
//----------
//
//You are given a binary search tree with N integers.
//
//Write a function that, for a binary search tree and a selected element T from the tree will:
//
//    find the depth of element T in the tree
//
//You will need to use that function Q times when building the tree.
//
//The input will contain N+Q rows of the type
//
//    insert value - You need to insert the value value into the tree.
//
//    ask value- You need to answer at what depth in the tree is the node with value value
//
//Sample input:
//
//Input
//
//
//Explanation
//
//
//Output
//
//10 7
//insert 6
//insert 3
//insert 7
//ask 3
//ask 6
//insert 4
//insert 1
//insert 2
//insert 5
//insert 9
//ask 3
//ask 9
//insert 8
//insert 10
//ask 8
//ask 4
//ask 5
//
//
//
//The tree view is shown for each of the 7 questions in the input
//
//
//
//2
//1
//2
//3
//4
//3
//4
//
//For example:
//Input 	Result
//
//10 7
//insert 6
//insert 3
//insert 7
//ask 3
//ask 6
//insert 4
//insert 1
//insert 2
//insert 5
//insert 9
//ask 3
//ask 9
//insert 8
//insert 10
//ask 8
//ask 4
//ask 5
//
//
//
//2
//1
//2
//3
//4
//3
//4

package Labs.Lab8_Binary_Search_Trees;
import java.util.Scanner;

class BNode<E extends Comparable<E>> {
    public E info;
    public BNode<E> left;
    public BNode<E> right;
    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }
    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
}
class BinarySearchTree<E extends Comparable<E>> {
    private BNode<E> root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(E x) {
        root = insert(x, root);
    }

    private BNode<E> insert(E x, BNode<E> t) {
        if (t == null) {
            t = new BNode<>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        }
        return t;
    }
    public int findDepth(E x) {
        return findDepth(x, root, 1);
    }
    private int findDepth(E x, BNode<E> t, int depth) {
        if (t == null) {
            return -1;
        }
        if (x.compareTo(t.info) == 0) {
            return depth;
        } else if (x.compareTo(t.info) < 0) {
            return findDepth(x, t.left, depth + 1); //ako e pomal od node-ot, barame nalevo za dlabochina+1
        } else {
            return findDepth(x, t.right, depth + 1); //isto kako za desno
        }
    }
}
public class Lab8_home {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        sc.nextLine();

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        for (int i = 0; i < n + q; i++) {
            String[] parts = sc.nextLine().split(" ");
            if (parts[0].equals("insert")) {
                int value = Integer.parseInt(parts[1]);
                bst.insert(value);
            } else if (parts[0].equals("ask")) {
                int value = Integer.parseInt(parts[1]);
                int depth = bst.findDepth(value);
                System.out.println(depth);
            }
        }
    }
}