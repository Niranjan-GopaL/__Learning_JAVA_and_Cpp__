import java.util.LinkedList;
import java.util.Queue;

public class Tree {

    private Tree leftChild = null;
    private Tree rightChild = null ;
    private int data;


    public Tree(int data, Tree left, Tree right){
        this.data       = data;
        this.leftChild  = left;
        this.rightChild = right;
    }


    public void preOrder() {
        System.out.print(data + " ");
        if (leftChild != null) 
            leftChild.preOrder();
        
        if (rightChild != null) 
            rightChild.preOrder();
        
    }


    public void postOrder() {
        if (leftChild != null) 
            leftChild.preOrder();
        
        if (rightChild != null) 
            rightChild.preOrder();
        
        System.out.print(data + " ");
    }



    public void inOrder() {
        if (leftChild != null) 
            leftChild.preOrder();
        
        System.out.print(data + " ");
        if (rightChild != null) 
            rightChild.preOrder();
        
    }


    public void levelOrder() {
        Queue<Tree> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            Tree node = queue.poll();
            System.out.print(node.data + " ");

            if (node.leftChild != null) 
                queue.add(node.leftChild);
            

            if (node.rightChild != null) 
                queue.add(node.rightChild);
            
        }
    }



    // Attaches a node to the left child of the leftmost node
    public void insert(int value) {
        if (leftChild != null) 
            leftChild.insert(value);
        else
            leftChild = new Tree(value, null, null);
    }


    // No idea how to use global variables to optimise
    private int getSize(Tree node) {
        if (node == null) 
            return 0;
        
        return 1 + getSize(node.leftChild) + getSize(node.rightChild);
    }

    
    // Checks if a tree has a value or note
    public boolean contains(int value){
        if (this.data == value) 
           return true; 

        return ((leftChild == null) ? false : leftChild.contains(value)) || 
              ((rightChild == null) ? false : rightChild.contains(value));
    }


    private int getHeight(Tree node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = getHeight(node.leftChild);
        int rightHeight = getHeight(node.rightChild);
        return Math.max(leftHeight, rightHeight) + 1;
    }
      


    public int findMax() {
        int max = data;
    
        if (leftChild != null) {
            int leftMax = leftChild.findMax();
            max = Math.max(max, leftMax);
        }
    
        if (rightChild != null) {
            int rightMax = rightChild.findMax();
            max = Math.max(max, rightMax);
        }
    
        return max;
    }
    
    public int findMin() {
        int min = data;
    
        if (leftChild != null) {
            int leftMin = leftChild.findMin();
            min = Math.min(min, leftMin);
        }
    
        if (rightChild != null) {
            int rightMin = rightChild.findMin();
            min = Math.min(min, rightMin);
        }
    
        return min;
    }



    public static void main(String[] args) {
        
        // extension of previous code
        Tree node6 = new Tree(6, null, null);
        Tree node5 = new Tree(5, node6, null);
        Tree node4 = new Tree(4, null, null);
        Tree node2 = new Tree(2, node4, node5);
        Tree node3 = new Tree(3, null, null);
        Tree node1 = new Tree(1,node2,node3);

        
        node1.preOrder();
        System.out.println();

        node1.inOrder();
        System.out.println();

        node1.postOrder();
        System.out.println();

        node1.levelOrder();
        System.out.println();


        System.out.println(node1.contains(6));
        System.out.println(node1.findMax());
        System.out.println(node1.findMin());


        System.out.println(node1.getHeight(node1));
        System.out.println(node1.getSize(node1));
        node1.insert(7);
        System.out.println(node1.getSize(node1));
    }
    
}

