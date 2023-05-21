# Assignment_5
## BTS Class
``` java
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<K> {
    private Node root; // Root node of the binary search tree
    private int size; // Number of elements in the binary search tree

    private class Node {
        private K key; // Key of the node
        private V value; // Value associated with the key
        private Node left, right; // Left and right child nodes of the current node

        // Constructs a node with the given key and value
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Inserts a key-value pair into the binary search tree
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    // Private helper method for inserting a key-value pair recursively
    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value); // Insert into the left subtree
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value); // Insert into the right subtree
        } else {
            node.value = value; // Update existing node's value
        }
        return node;
    }

    // Retrieves the value associated with a given key from the binary search tree
    public V get(K key) {
        Node node = get(root, key);
        return node != null ? node.value : null;
    }

    // Private helper method for retrieving a node with a given key recursively
    private Node get(Node node, K key) {
        if (node == null) {
            return null; // Key not found
        }

        if (key.compareTo(node.key) < 0) {
            return get(node.left, key); // Search in the left subtree
        } else if (key.compareTo(node.key) > 0) {
            return get(node.right, key); // Search in the right subtree
        } else {
            return node; // Key found
        }
    }


    // Deletes a node with a given key from the binary search tree
    public void delete(K key) {
        root = delete(root, key);
    }

    // Private helper method for deleting a node with a given key recursively
    private Node delete(Node node, K key) {
        if (node == null) {
            return null; // Key not found
        }

        if (key.compareTo(node.key) < 0) {
            node.left = delete(node.left, key); // Delete from the left subtree
        } else if (key.compareTo(node.key) > 0) {
            node.right = delete(node.right, key); // Delete from the right subtree
        } else {
            size--;
            if (node.left == null) {
                return node.right; // Replace with right child (or null if no right child)
            } else if (node.right == null) {
                return node.left; // Replace with left child
            } else {
                Node successor = findMinimum(node.right); // Find the minimum node in the right subtree
                node.key = successor.key; // Replace key with successor's key
                node.value = successor.value; // Replace value with successor's value
                node.right = delete(node.right, successor.key); // Delete the successor node from the right subtree
            }
        }
        return node;
    }

    // Finds the minimum node in a given subtree
    private Node findMinimum(Node node) {
        if (node.left == null) {
            return node; // Minimum node found
        }
        return findMinimum(node.left); // Continue searching in the left subtree
    }

    // Returns an iterator over the keys in the binary search tree
    public Iterator<K> iterator() {
        return new InOrderIterator();
    }

    // Private inner class for implementing the in-order iterator
    private class InOrderIterator implements Iterator<K> {
        private Node current; // Current node being visited
        private final Stack<Node> stack; // Stack for storing nodes during traversal

        public InOrderIterator() {
            current = root; // Set the current node to the root of the binary search tree
            stack = new Stack<>(); // Create a new stack to store nodes during traversal
        }

        public boolean hasNext() {
            return !stack.isEmpty() || current != null; // Checks if there are more elements to iterate over
        }

        public K next() {
            while (current != null) {
                stack.push(current); // Push nodes onto the stack while traversing to the leftmost node
                current = current.left;
            }
            if (stack.isEmpty()) {
                throw new NoSuchElementException(); // No more elements to iterate
            }
            Node node = stack.pop(); // Retrieve the next node from the stack
            current = node.right; // Move to the right subtree of the current node
            return node.key; // Return the key of the visited node
        }
    }

    // Returns the number of elements in the binary search tree
    public int size() {
        return size;
    }
}
```
## Explanation
- The BST class is a generic class that implements the Iterable interface, which allows iteration over the keys of the BST.
- The BST class has an inner class Node that represents a node in the BST. Each node contains a key-value pair and references to its left and right child nodes.
- The BST supports basic operations such as put, get, and delete. The put method inserts a key-value pair into the BST, the get method retrieves the value associated with a given key, and the delete method removes a node with a given key from the BST.
- The put, get, and delete methods are implemented recursively, using the Node references to traverse the BST based on the comparison of keys.
- The findMinimum method is a helper method that finds the minimum node in a given subtree by recursively traversing the left child nodes.
- The iterator method returns an instance of the InOrderIterator class, which implements the Iterator interface. The InOrderIterator performs an in-order traversal of the BST using a stack to keep track of nodes during the traversal.
- The hasNext method of the InOrderIterator checks if there are more nodes to be visited in the BST.
- The next method of the InOrderIterator returns the key of the next node in the in-order traversal sequence.
- The size method returns the number of elements (nodes) in the BST.

________________________________________________________________________________________________________________________________________________________________________________________________

## Main Class:
```java
public class Main {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();

        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(8, "Eight");
        tree.put(1, "One");
        tree.put(4, "Four");
        tree.put(7, "Seven");
        tree.put(9, "Nine");

        for (var key : tree) {
            System.out.println("Key: " + key + " Value: " + tree.get(key));
        }

        tree.delete(3);
        tree.delete(5);
        System.out.println();

        for (var key : tree) {
            System.out.println("Key: " + key + " Value: " + tree.get(key));
        }
    }
}
```
## Output
``` java
Key: 1 Value: One
Key: 3 Value: Three
Key: 4 Value: Four
Key: 5 Value: Five
Key: 7 Value: Seven
Key: 8 Value: Eight
Key: 9 Value: Nine

Key: 1 Value: One
Key: 4 Value: Four
Key: 7 Value: Seven
Key: 8 Value: Eight
Key: 9 Value: Nine
```
