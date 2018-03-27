package bintrees;

import java.util.ArrayList;

class NodeUtils {

    /** copy tree (only children) */
    public static Node copy(Node root){
        if (root == null){
            return null;
        }
        Node newNode = new Node();
        newNode.leftChild= copy(root.leftChild);
        newNode.rightChild= copy(root.rightChild);
        return newNode;
    }

    /** Recursive calculate tree height */
    public static int treeHeight(Node root){
        if (root.leftChild == null
                && root.rightChild == null)
            return 0;
        else
            return
                    1 + Math.max(
                            treeHeight(root.leftChild),
                            treeHeight(root.rightChild));
    }

    /** Recursive generate parent ids.
     * Populate NodesByDepthList
     * Generate hashCode (for detecting duplicates)
     */
    public static void pointersAndHash(Node n){
        addDepth(n, 1);

        // sort tree-nodes from left to right and top down
        NodesByDepthList nodesByDepth = new NodesByDepthList();
        populateNodesByDepth(n, nodesByDepth);

        int i = 1;
        for(ArrayList<Node> level : nodesByDepth){
            for(Node node : level){
                node.id = i;
                if(node.leftChild != null)
                    n.hashCode += ","+node.id;
                if(node.rightChild != null)
                    n.hashCode += ","+node.id;
                i++;
            }
        }
    }

    //
    // Private methods and data structures.
    //
    /** Recursive generate node.depth information */
    private static void addDepth(Node n, int currentDepth) {
        if (n == null)
            return;
        n.depth = currentDepth;
        addDepth(n.leftChild, currentDepth+1);
        addDepth(n.rightChild, currentDepth+1);
    }

    /** Recursive populate NodesByDepthList by node.depth */
    private static void populateNodesByDepth(Node n, NodesByDepthList nodesByDepth){
        if(n == null)
            return;
        nodesByDepth.addTo(n.depth, n);
        if(n.leftChild != null)
            populateNodesByDepth(n.leftChild, nodesByDepth);
        if(n.rightChild != null)
            populateNodesByDepth(n.rightChild, nodesByDepth);
    }
    /** List of lists. Outer list idx = depth in tree */
    private static class NodesByDepthList extends ArrayList<ArrayList<Node>>{
        void addTo(int depth, Node n){
            while(size() < depth)
                add(new ArrayList<>());
            get(depth-1).add(n);
        }
    }
}
