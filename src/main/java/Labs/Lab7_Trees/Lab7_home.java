//Во следната задача треба да изградите неподредено (општо) дрво со N јазли, за кое ќе треба да одговорите на Q прашања од видот „колку лисја има поддрвото на избран јазол“.
//
//Секој јазол ќе има уникатен индекс од 1 до N. Коренот на дрвото секогаш ќе има индекс 1. Сите деца ќе имаат индекси поголеми од индексите на родителите.
//
//Влезот ќе содржи N+Q редови од видот
//
//    root 1 - Треба да го поставите коренот на дрвото да биде јазелот со индекс 1
//
//    add parent_index child_index - Треба да додадете дете јазел со индекс child_index на јазелот со индекс parent_index
//
//    ask node_index - Треба да одговорите колку листови има во поддрвото на јазелот со индекс node_index
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
//11 5
//root 1
//add 1 2
//add 2 3
//ask 1
//add 1 4
//add 2 5
//add 3 6
//ask 2
//add 3 7
//ask 1
//add 1 8
//add 4 9
//add 2 10
//add 4 11
//ask 2
//ask 1
//
//
//Прикажано е изгледот на дрвото при секое од 5те прашања во влезот
//
//прашање 1
//
//    прашање 1: ask 1: Во поддрвото на јазелот 1 има 1 лист.
//    прашање 2: ask 2: Во поддрвото на јазелот 2 има 2 листови.
//    прашање 3: ask 1: Во поддрвото на јазелот 1 има 4 листови.
//    прашање 4: ask 2: Во поддрвото на јазелот 2 има 4 листови
//    прашање 5: ask 1: Во поддрвото на јазелот 1 има 7 листови
//
//
//
//1
//2
//4
//4
//7
//
//
//------
//
//
//In the following task you need to build an unordered (general) tree with N nodes, for which you will need to answer Q questions of the type "how many leaves does the subtree of a selected node have".
//
//Each node will have a unique index from 1 to N. The root of the tree will always have an index 1. All children will have indices greater than the parent indices.
//
//The input will contain N+Q rows of the type
//
//    root 1 - You need to set the root of the tree to be the node with index 1
//
//    add parent_index child_index - You need to add a child node with index child_index to the node with index parent_index
//
//    ask node_index - You need to answer how many leaves are in the subtree of the node with index node_index
//
//Example and structure of the input:
//
//Input
//
//
//Explanation
//
//
//Output
//
//11 5
//root 1
//add 1 2
//add 2 3
//ask 1
//add 1 4
//add 2 5
//add 3 6
//ask 2
//add 3 7
//ask 1
//add 1 8
//add 4 9
//add 2 10
//add 4 11
//ask 2
//ask 1
//
//
//The tree view is shown for each of the 5 questions in the input
//
//question 1
//
//    question 1: ask 1: In the subtree of node 1 there is 1 leaf.
//    question 2: ask 2: In the subtree of node 2 there are 2 leaves.
//    question 3: ask 1: In the subtree of node 1 there are 4 leaves.
//    Question 4: ask 2: In the subtree of node 2 there are 4 leaves
//    Question 5: ask 1: In the subtree of node 1 there are 7 sheets
//
//
//
//1
//2
//4
//4
//7
//
//For example:
//Input 	Result
//
//11 5
//root 1
//add 1 2
//add 2 3
//ask 1
//add 1 4
//add 2 5
//add 3 6
//ask 2
//add 3 7
//ask 1
//add 1 8
//add 4 9
//add 2 10
//add 4 11
//ask 2
//ask 1
//
//
//
//1
//2
//4
//4
//7

package Labs.Lab7_Trees;
import java.util.Scanner;
import java.util.Iterator;

// Tree interface
interface Tree<E> {
    // Accessors
    public Tree.Node<E> root();

    public Tree.Node<E> parent(Tree.Node<E> node);

    public int childCount(Tree.Node<E> node);

    // Transformers
    public void makeRoot(E elem);

    public Tree.Node<E> addChild(Tree.Node<E> node, E elem);

    public void remove(Tree.Node<E> node);

    // Iterators
    public Iterator<E> children(Tree.Node<E> node);

    public Iterable<Tree.Node<E>> childrenNodes(Tree.Node<E> node);

    // Inner interface for tree nodes
    public interface Node<E> {
        public E getElement();

        public void setElement(E elem);
    }
}

// SLLTree class
class SLLTree<E> implements Tree<E> {

    // Node implementation
    static class SLLNode<P> implements Node<P> {
        SLLNode<P> parent, sibling, firstChild;
        P element;

        public SLLNode(P o) {
            element = o;
            parent = sibling = firstChild = null;
        }

        public P getElement() {
            return element;
        }

        public void setElement(P o) {
            element = o;
        }
    }

    protected SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    @Override
    public Node<E> root() {
        return root;
    }

    @Override
    public Node<E> parent(Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    @Override
    public int childCount(Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    @Override
    public void makeRoot(E elem) {
        root = new SLLNode<>(elem);
    }

    @Override
    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        curr.firstChild = tmp;
        tmp.parent = curr;
        return tmp;
    }

    @Override
    public void remove(Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                curr.parent.firstChild = curr.sibling;
            } else {
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    @Override
    public Iterator<E> children(Node<E> node) {
        return new SLLTreeIterator(((SLLNode<E>) node).firstChild);
    }

    @Override
    public Iterable<Node<E>> childrenNodes(Node<E> node) {
        return new NodeIterable(((SLLNode<E>) node).firstChild);
    }

    public Node<E> findNode(E value, Node<E> currentNode) {
        if (currentNode == null) {
            return null;
        }
        if (currentNode.getElement().equals(value)) {
            return currentNode;
        }
        SLLNode<E> child = ((SLLNode<E>) currentNode).firstChild;
        while (child != null) {
            Node<E> result = findNode(value, child);
            if (result != null) {
                return result;
            }
            child = child.sibling;
        }
        return null;
    }

    class SLLTreeIterator implements Iterator<E> {
        SLLNode<E> current;

        public SLLTreeIterator(SLLNode<E> node) {
            current = node;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            SLLNode<E> tmp = current;
            current = current.sibling;
            return tmp.getElement();
        }
    }

    class NodeIterable implements Iterable<Node<E>> {
        Node<E> start;

        public NodeIterable(Node<E> start) {
            this.start = start;
        }

        @Override
        public Iterator<Node<E>> iterator() {
            return new NodeIterator(start);
        }
    }

    class NodeIterator implements Iterator<Node<E>> {
        Node<E> current;

        public NodeIterator(Node<E> node) {
            current = node;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Node<E> next() {
            Node<E> tmp = current;
            current = ((SLLNode<E>) current).sibling;
            return tmp;
        }
    }
}

// Main program class
class prog {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // Number of nodes
        int Q = sc.nextInt(); // Number of queries
        sc.nextLine(); // Consume the newline

        SLLTree<Integer> tree = new SLLTree<>();

        for (int i = 0; i < N + Q; i++) {
            String[] command = sc.nextLine().split(" ");
            String action = command[0];

            if (action.equals("root")) {
                int rootIndex = Integer.parseInt(command[1]);
                tree.makeRoot(rootIndex);
            } else if (action.equals("add")) {
                int parentValue = Integer.parseInt(command[1]);
                int childValue = Integer.parseInt(command[2]);
                SLLTree.SLLNode<Integer> parentNode = (SLLTree.SLLNode<Integer>) tree.findNode(parentValue, tree.root());
                tree.addChild(parentNode, childValue);
            } else if (action.equals("ask")) {
                int queryValue = Integer.parseInt(command[1]);
                SLLTree.SLLNode<Integer> queryNode = (SLLTree.SLLNode<Integer>) tree.findNode(queryValue, tree.root());
                int leafCount = countLeaves(tree, queryNode);
                System.out.println(leafCount);
            }
        }

        sc.close();
    }

    public static <E> int countLeaves(SLLTree<E> tree, SLLTree.SLLNode<E> node) {
        if (node == null) {
            return 0;
        }
        if (node.firstChild == null) {
            return 1;
        }

        int count = 0;
        SLLTree.SLLNode<E> child = node.firstChild;
        while (child != null) {
            count += countLeaves(tree, child);
            child = child.sibling;
        }
        return count;
    }
}
