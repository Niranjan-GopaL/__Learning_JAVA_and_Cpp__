import java.util.LinkedList;
import java.util.Queue;

public class Tree__ {
    private int data;
    private Tree__ leftChild;
    private Tree__ rightChild;

    public Tree__(int data, Tree__ leftChild, Tree__ rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public void preOrder() {
        System.out.print(data + " ");
        if (leftChild != null) {
            leftChild.preOrder();
        }
        if (rightChild != null) {
            rightChild.preOrder();
        }
    }

    public void inOrder() {
        if (leftChild != null) {
            leftChild.inOrder();
        }
        System.out.print(data + " ");
        if (rightChild != null) {
            rightChild.inOrder();
        }
    }

    public void postOrder() {
        if (leftChild != null) {
            leftChild.postOrder();
        }
        if (rightChild != null) {
            rightChild.postOrder();
        }
        System.out.print(data + " ");
    }

    public void levelOrder() {
        Queue<Tree__> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            Tree__ node = queue.poll();
            System.out.print(node.data + " ");

            if (node.leftChild != null) {
                queue.add(node.leftChild);
            }

            if (node.rightChild != null) {
                queue.add(node.rightChild);
            }
        }
    }

    public int getSize() {
        return countNodes(this);
    }

    private int countNodes(Tree__ node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.leftChild) + countNodes(node.rightChild);
    }

    public int getHeight() {
        return calculateHeight(this);
    }

    private int calculateHeight(Tree__ node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = calculateHeight(node.leftChild);
        int rightHeight = calculateHeight(node.rightChild);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean contains(int value) {
        if (data == value) {
            return true;
        }
        boolean foundInLeft = (leftChild != null) && leftChild.contains(value);
        boolean foundInRight = (rightChild != null) && rightChild.contains(value);
        return foundInLeft || foundInRight;
    }

    public int findMax() {
        if (rightChild == null) {
            return data;
        }
        return rightChild.findMax();
    }

    public int findMin() {
        if (leftChild == null) {
            return data;
        }
        return leftChild.findMin();
    }

    public void insert(int value) {
        if (value < data) {
            if (leftChild == null) {
                leftChild = new Tree__(value, null, null);
            } else {
                leftChild.insert(value);
            }
        } else {
            if (rightChild == null) {
                rightChild = new Tree__(value, null, null);
            } else {
                rightChild.insert(value);
            }
        }
    }

    public static void main(String[] args) {
        // Create a sample binary tree__
        Tree__ root = new Tree__(10, new Tree__(5, new Tree__(3, null, null), new Tree__(7, null, null)),
                new Tree__(15, new Tree__(12, null, null), new Tree__(20, null, null)));

        // Perform various tree__ operations and traversals
        System.out.println("Pre-order traversal:");
        root.preOrder();

        System.out.println("\nIn-order traversal:");
        root.inOrder();

        System.out.println("\nPost-order traversal:");
        root.postOrder();

        System.out.println("\nLevel-order traversal:");
        root.levelOrder();

        System.out.println("\nTree__ Size: " + root.getSize());
        System.out.println("Tree__ Height: " + root.getHeight());
        System.out.println("Tree__ contains 7: " + root.contains(7));
        System.out.println("Maximum value in the tree__: " + root.findMax());
        System.out.println("Minimum value in the tree__: " + root.findMin());

        // Insert a new value into the tree__
        int newValue = 8;
        root.insert(newValue);
        System.out.println("Tree__ contains " + newValue + ": " + root.contains(newValue));

        // Perform in-order traversal after insertion
        System.out.println("In-order traversal after insertion:");
        root.inOrder();
    }
}
