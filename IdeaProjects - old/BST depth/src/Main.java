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
public class Main {
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