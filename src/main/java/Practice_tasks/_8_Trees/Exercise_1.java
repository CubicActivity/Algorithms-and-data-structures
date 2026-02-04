/*
Во следната задача треба да изградите неподредено (општо) дрво со N јазли, за кое ќе треба да одговорите на Q прашања од видот „колку лисја има поддрвото на избран јазол“.

Секој јазол ќе има уникатен индекс од 1 до N. Коренот на дрвото секогаш ќе има индекс 1. Сите деца ќе имаат индекси поголеми од индексите на родителите.

Влезот ќе содржи N+Q редови од видот

    root 1 - Треба да го поставите коренот на дрвото да биде јазелот со индекс 1

    add parent_index child_index - Треба да додадете дете јазел со индекс child_index на јазелот со индекс parent_index

    ask node_index - Треба да одговорите колку листови има во поддрвото на јазелот со индекс node_index

Пример и структура на влезот:

Влез


Објаснување


Излез

11 5
root 1
add 1 2
add 2 3
ask 1
add 1 4
add 2 5
add 3 6
ask 2
add 3 7
ask 1
add 1 8
add 4 9
add 2 10
add 4 11
ask 2
ask 1


Прикажано е изгледот на дрвото при секое од 5те прашања во влезот

прашање 1

    прашање 1: ask 1: Во поддрвото на јазелот 1 има 1 лист.
    прашање 2: ask 2: Во поддрвото на јазелот 2 има 2 листови.
    прашање 3: ask 1: Во поддрвото на јазелот 1 има 4 листови.
    прашање 4: ask 2: Во поддрвото на јазелот 2 има 4 листови
    прашање 5: ask 1: Во поддрвото на јазелот 1 има 7 листови



1
2
4
4
7


------


In the following task you need to build an unordered (general) tree with N nodes, for which you will need to answer Q questions of the type "how many leaves does the subtree of a selected node have".

Each node will have a unique index from 1 to N. The root of the tree will always have an index 1. All children will have indices greater than the parent indices.

The input will contain N+Q rows of the type

    root 1 - You need to set the root of the tree to be the node with index 1

    add parent_index child_index - You need to add a child node with index child_index to the node with index parent_index

    ask node_index - You need to answer how many leaves are in the subtree of the node with index node_index

Example and structure of the input:

Input


Explanation


Output

11 5
root 1
add 1 2
add 2 3
ask 1
add 1 4
add 2 5
add 3 6
ask 2
add 3 7
ask 1
add 1 8
add 4 9
add 2 10
add 4 11
ask 2
ask 1


The tree view is shown for each of the 5 questions in the input

question 1

    question 1: ask 1: In the subtree of node 1 there is 1 leaf.
    question 2: ask 2: In the subtree of node 2 there are 2 leaves.
    question 3: ask 1: In the subtree of node 1 there are 4 leaves.
    Question 4: ask 2: In the subtree of node 2 there are 4 leaves
    Question 5: ask 1: In the subtree of node 1 there are 7 sheets



1
2
4
4
7

For example:
Input 	Result

11 5
root 1
add 1 2
add 2 3
ask 1
add 1 4
add 2 5
add 3 6
ask 2
add 3 7
ask 1
add 1 8
add 4 9
add 2 10
add 4 11
ask 2
ask 1



1
2
4
4
7
 */

package Practice_tasks._8_Trees;

import java.util.*;


import java.util.NoSuchElementException;

import java.util.Iterator;

interface Tree<E> {
    // //////////Accessors ////////////

    public Tree.Node<E> root();

    public Tree.Node<E> parent(Tree.Node<E> node);

    public int childCount(Tree.Node<E> node);

    // //////////Transformers ////////////
    public void makeRoot(E elem);

    public Tree.Node<E> addChild(Tree.Node<E> node, E elem);

    public void remove(Tree.Node<E> node);

    // //////////Iterator ////////////
    public Iterator<E> children(Tree.Node<E> node);

    // //////Inner interface for tree nodes ////////
    public interface Node<E> {

        public E getElement();

        public void setElement(E elem);

    }
}


class SLLTree<E> implements Tree<E> {

    // SLLNode is the implementation of the Node interface
    class SLLNode<P> implements Node<P> {

        // Holds the links to the needed nodes
        SLLNode<P> parent, sibling, firstChild;

        // Hold the data
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

    public Node<E> root() {
        return root;
    }

    public Tree.Node<E> parent(Tree.Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Tree.Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        curr.firstChild = tmp;
        tmp.parent = curr;
        return tmp;
    }

    public void remove(Tree.Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list
                // and remove it
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

    class SLLTreeIterator<T> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }


    public Iterator<E> children(Tree.Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }

    void printTreeRecursive(Node<E> node, int level) {
        if (node == null)
            return;
        int i;
        SLLNode<E> tmp;

        for (i = 0; i < level; i++)
            System.out.print("  ");
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>) node).firstChild;
        while (tmp != null) {
            printTreeRecursive(tmp, level + 1);
            tmp = tmp.sibling;
        }
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

    public int countMaxChildren() {
        return countMaxChildrenRecursive(root);
    }

    int countMaxChildrenRecursive(SLLNode<E> node) {
        int t = childCount(node);
        SLLNode<E> tmp = node.firstChild;
        while (tmp != null) {
            t = Math.max(t, countMaxChildrenRecursive(tmp));
            tmp = tmp.sibling;
        }
        return t;
    }




}

class prog {
    public static void main(String[] args) {

        SLLTree<Integer> DRVOEDNO= new SLLTree<>();

        HashMap<Integer, SLLTree.Node<Integer>> MAPA = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();

        int k=sc.nextInt();
        sc.nextLine();

        for(int i=0;i<n+k;i++){
            String str = sc.nextLine();
            String [] parts = str.split(" ");

            if(parts[0].equals("root")){

                int KOREN = Integer.parseInt(parts[1]);
                DRVOEDNO.makeRoot(KOREN);
                MAPA.put(KOREN ,DRVOEDNO.root);
//                System.out.println(KOREN+"is the root" + MAPA.get(KOREN));

            }else if(parts[0].equals("add")){

                int parent = Integer.parseInt(parts[1]);
                int child = Integer.parseInt(parts[2]);

//                System.out.println("ADDED "+ child+" to"+MAPA.get(parent));
                SLLTree.Node<Integer> node = DRVOEDNO.addChild(MAPA.get(parent),child);
                MAPA.put(node.getElement(),node);
//                DRVOEDNO.printTree();
            }else if(parts[0].equals("ask")){
                //count leaf nodes for said thing by doing dfs through each of its children recursively
                System.out.println(countLeafs(DRVOEDNO, MAPA.get(Integer.parseInt(parts[1])),MAPA) );

            }

        }


    }

    static int countLeafs(SLLTree<Integer> drvo, SLLTree.Node<Integer> currNode, HashMap<Integer, SLLTree.Node<Integer>> mapa) {
        if (currNode == null) return 0;

        // If the node has no children, it's a leaf
        if (drvo.childCount(currNode) == 0) return 1;

        int leafs = 0;
        Iterator<Integer> childIterator = drvo.children(currNode); // Get the iterator once

        while (childIterator.hasNext()) {
            Integer childValue = childIterator.next(); // Get the child's value
            SLLTree.Node<Integer> childNode = mapa.get(childValue); // Get the corresponding node
            leafs += countLeafs(drvo, childNode, mapa); // Recursively count the leafs
        }

        return leafs;
    }


}
