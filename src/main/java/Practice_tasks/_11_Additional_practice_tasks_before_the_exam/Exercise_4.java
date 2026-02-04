/*
Дадено е пополнето дрво во кое секоја врска има тежина 2. За секој пар јазли потребно е да се најде најкраткото растојание меѓу нив. Доколку не постои решение испечатете "Error".

/

You are given a complete tree where each edge has a weight of 2. For each pair of nodes you should find the shortest distance between them. If there is no solution for some pair you should print "Error".

For example:
Input 	Result

8
0 A ROOT
1 B LEFT 0
2 C LEFT 1
3 D RIGHT 1
4 E RIGHT 0
5 F RIGHT 4
6 G LEFT 5
7 H RIGHT 5
3
A D
E E
G Z



4
0
ERROR

 */
package Practice_tasks._11_Additional_practice_tasks_before_the_exam;

import java.util.Scanner;
import java.util.NoSuchElementException;

class BNode<E> {
    public E info;
    public BNode<E> left;
    public BNode<E> right;

    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode<E> parent;

    public BNode(E info, BNode<E> parent) {
        this.parent = parent;
        this.info = info;
        left = null;
        right = null;
    }

    public BNode(E info) {
        this.parent = null;
        this.info = info;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

    @SuppressWarnings("unchecked")
    public BNode() {
    }
}

class BTree<E> {

    public BNode<E> root;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public void makeRoot(E elem) {
        root = new BNode<>(elem);
    }

    public void makeRootNode(BNode<E> node) {
        root = node;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {
        BNode<E> tmp = new BNode<>(elem);
        if (where == BNode.LEFT) {
            if (node.left != null)  // Already exists
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // Already exists
                return null;
            node.right = tmp;
        }
        return tmp;
    }

    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {
        if (where == BNode.LEFT) {
            if (node.left != null)  // Already exists
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // Already exists
                return null;
            node.right = tmp;
        }
        return tmp;
    }

    class isFound{
        boolean found=false;
        int depth;

        public isFound(boolean foun,int d){
            found=foun;
            depth=d;
        }

    }

    public int findDepth(BNode<E> node, E target, int depth) {
        if (node == null) {
            return -1; // Node not found
        }
        if (node.info.equals(target)) {
            return depth; // Return depth if the target is found
        }

        // Search in left and right subtrees
        int left = findDepth(node.left, target, depth + 1);
        if (left != -1) {
            return left; // Found in the left subtree
        }

        return findDepth(node.right, target, depth + 1); // Check right subtree
    }

    public int findDistance(E from, E to) {
        int depthFrom = findDepth(root, from, 0);
        int depthTo = findDepth(root, to, 0);

        if (depthFrom == -1 || depthTo == -1) {
            return -1; // Either or both nodes are missing
        }

        if (from.equals(to)) {
            return 0; // If the nodes are the same, distance is 0
        }

        return (depthFrom + depthTo); // Add depths for the distance
    }
}


interface Stack<E> {
    public boolean isEmpty();

    public E peek();

    public void clear();

    public void push(E x);

    public E pop();
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty() {
        return (depth == 0);
    }

    public E peek() {
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth - 1];
    }

    public void clear() {
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }

    public void push(E x) {
        elems[depth++] = x;
    }

    public E pop() {
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

class prog {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int i, j, k;
        int index;
        String action;

        int N = sc.nextInt();
        sc.nextLine();

        @SuppressWarnings("unchecked")
        BNode<String>[] nodes = (BNode<String>[]) new BNode[N];
        BTree<String> tree = new BTree<>();

        for (i = 0; i < N; i++)
            nodes[i] = new BNode<>();

        for (i = 0; i < N; i++) {
            String[] line = sc.nextLine().split(" ");

            index = Integer.parseInt(line[0]);
            nodes[index].info = line[1];
            action = line[2];
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(line[3])], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(line[3])], BNode.RIGHT, nodes[index]);
            } else {
                tree.makeRootNode(nodes[index]);
            }
        }

        int cases = sc.nextInt();
        sc.nextLine();

        for (int l = 0; l < cases; l++) {
            String[] query = sc.nextLine().split(" ");
            String from = query[0];
            String to = query[1];

            int distance = tree.findDistance(from, to);
            if (distance == -1) {
                System.out.println("ERROR");
            } else {
                System.out.println(distance * 2); // Multiply by 2 for edge weights
            }
        }

        sc.close();
    }
}
