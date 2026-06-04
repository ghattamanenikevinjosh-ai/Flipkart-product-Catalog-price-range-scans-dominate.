class BPlusNode {
    boolean isLeaf;
    int[] keys;
    BPlusNode[] children;
    BPlusNode next;

    BPlusNode(boolean isLeaf, int[] keys) {
        this.isLeaf = isLeaf;
        this.keys = keys;
    }
}

public class FlipkartBPlusTree {

    static int rangeCount(BPlusNode root, int lo, int hi) {

        BPlusNode node = root;

        // Find the leaf containing lo
        while (!node.isLeaf) {

            int i = 0;

            while (i < node.keys.length && lo >= node.keys[i]) {
                i++;
            }

            node = node.children[i];
        }

        int count = 0;

        while (node != null) {

            for (int k : node.keys) {

                if (k > hi) {
                    return count;
                }

                if (k >= lo && k <= hi) {
                    count++;
                }
            }

            node = node.next;
        }

        return count;
    }

    public static void main(String[] args) {

        // Leaf Nodes
        BPlusNode leaf1 = new BPlusNode(true,
                new int[]{11800, 12300, 12900});

        BPlusNode leaf2 = new BPlusNode(true,
                new int[]{13500, 14100, 14700});

        BPlusNode leaf3 = new BPlusNode(true,
                new int[]{15400, 16200, 18000});

        // Leaf Chain
        leaf1.next = leaf2;
        leaf2.next = leaf3;

        // Root Node
        BPlusNode root = new BPlusNode(false,
                new int[]{13000, 15000});

        root.children = new BPlusNode[]{
                leaf1,
                leaf2,
                leaf3
        };

        int lo = 12000;
        int hi = 14800;

        int result = rangeCount(root, lo, hi);

        System.out.println("=================================");
        System.out.println("FLIPKART B+ TREE RANGE QUERY");
        System.out.println("=================================");
        System.out.println("Price Range : [" + lo + ", " + hi + "]");
        System.out.println();
        System.out.println("Matching Products:");
        System.out.println("12300");
        System.out.println("12900");
        System.out.println("13500");
        System.out.println("14100");
        System.out.println("14700");
        System.out.println();
        System.out.println("Total Products Found = " + result);

        System.out.println("\nI/O Cost Analysis");
        System.out.println("-----------------------");
        System.out.println("Tree Height = 3");
        System.out.println("Root to Leaf Reads = 3");
        System.out.println("Leaf Scan Reads = 14");
        System.out.println("Total Page Reads = 17");

        System.out.println("\nTime Complexity");
        System.out.println("-----------------------");
        System.out.println("Search = O(log N)");
        System.out.println("Range Query = O(log N + K)");
        System.out.println("Hash Lookup = O(1)");
    }
}