import java.util.Scanner;

// Tree interface
interface Tree<E> {
    public Tree.Node<E> root();
    public Tree.Node<E> parent(Tree.Node<E> node);
    public void makeRoot(E elem);
    public Tree.Node<E> addLeft(Tree.Node<E> node, E elem);
    public Tree.Node<E> addRight(Tree.Node<E> node, E elem);
    public void remove(Tree.Node<E> node);

    interface Node<E> {
        public E getElement();
        public void setElement(E elem);
    }
}

// BinaryTree class
class BinaryTree<E> implements Tree<E> {
    static class BinaryTreeNode<P> implements Node<P> {
        BinaryTreeNode<P> parent, left, right;
        P element;

        public BinaryTreeNode(P o) {
            element = o;
            parent = left = right = null;
        }

        public P getElement() {
            return element;
        }

        public void setElement(P o) {
            element = o;
        }
    }

    protected BinaryTreeNode<E> root;

    public BinaryTree() {
        root = null;
    }

    @Override
    public Node<E> root() {
        return root;
    }

    @Override
    public Node<E> parent(Node<E> node) {
        return ((BinaryTreeNode<E>) node).parent;
    }

    @Override
    public void makeRoot(E elem) {
        root = new BinaryTreeNode<>(elem);
    }

    @Override
    public Node<E> addLeft(Node<E> node, E elem) {
        BinaryTreeNode<E> tmp = new BinaryTreeNode<>(elem);
        BinaryTreeNode<E> curr = (BinaryTreeNode<E>) node;
        curr.left = tmp;
        tmp.parent = curr;
        return tmp;
    }

    @Override
    public Node<E> addRight(Node<E> node, E elem) {
        BinaryTreeNode<E> tmp = new BinaryTreeNode<>(elem);
        BinaryTreeNode<E> curr = (BinaryTreeNode<E>) node;
        curr.right = tmp;
        tmp.parent = curr;
        return tmp;
    }

    @Override
    public void remove(Node<E> node) {
        BinaryTreeNode<E> curr = (BinaryTreeNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.left == curr) {
                curr.parent.left = null;
            } else {
                curr.parent.right = null;
            }
        } else {
            root = null;
        }
    }

    public Node<E> findNode(E value, Node<E> currentNode) {
        if (currentNode == null) return null;
        if (currentNode.getElement().equals(value)) return currentNode;
        Node<E> leftResult = findNode(value, ((BinaryTreeNode<E>) currentNode).left);
        if (leftResult != null) return leftResult;
        return findNode(value, ((BinaryTreeNode<E>) currentNode).right);
    }
}

// Main program
class Prog {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        sc.nextLine();

        BinaryTree<String> tree = new BinaryTree<>();

        for (int i = 0; i < N + Q; i++) {
            String[] command = sc.nextLine().split(" ");
            String action = command[0];

            if (action.equals("root")) {
                String rootValue = command[1];
                tree.makeRoot(rootValue);
            } else if (action.equals("addLeft")) {
                String parentValue = command[1];
                String childValue = command[2];
                BinaryTree.BinaryTreeNode<String> parentNode = (BinaryTree.BinaryTreeNode<String>) tree.findNode(parentValue, tree.root());
                tree.addLeft(parentNode, childValue);
            } else if (action.equals("addRight")) {
                String parentValue = command[1];
                String childValue = command[2];
                BinaryTree.BinaryTreeNode<String> parentNode = (BinaryTree.BinaryTreeNode<String>) tree.findNode(parentValue, tree.root());
                tree.addRight(parentNode, childValue);
            } else if (action.equals("ask")) {
                String queryValue = command[1];
                BinaryTree.BinaryTreeNode<String> queryNode = (BinaryTree.BinaryTreeNode<String>) tree.findNode(queryValue, tree.root());
                int leafCount = countLeaves(tree, queryNode);
                System.out.println(leafCount);
            }
        }

        sc.close();
    }

    public static <E> int countLeaves(BinaryTree<E> tree, BinaryTree.BinaryTreeNode<E> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeaves(tree, node.left) + countLeaves(tree, node.right);
    }
}
