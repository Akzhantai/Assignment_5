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
