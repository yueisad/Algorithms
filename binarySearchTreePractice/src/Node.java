public class Node {
    Node left, right;

    int data;

    public Node(int data) {
        this.data = data;
    }
// insert function in BST
    public void insert(int value) {
        if (value <= data) {
            if (left == null) {
                left = new Node(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new Node(value);
            } else {
                right.insert(value);
            }
        } 
    }

// find function in BST

    public boolean contains(int value) {
        if (value == data) {
            return true;
        } else if (value < data) {
            if (left == null) {
                return false;
            } else {
                return left.contains(value);
            }
        } else {
            if (right == null) {
                return false;
            } else {
                return right.contains(value);
            }
        }
    }


// inorder traversal ~ ABC order
    public void printInOrder() { //left
        if (left != null) {
            left.printInOrder();
        }
        System.out.println(data); //root

        if (right != null) { //right
            right.printInOrder();
        }
    }

}
