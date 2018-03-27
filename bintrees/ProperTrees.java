package bintrees;

import java.util.*;

public class ProperTrees {
    // set excludes duplicates by Node.hashCode() & equals()
    Set<Node> rTrees = new HashSet<>();

    ProperTrees(int height){
        properBintrees(height);
    }

    private void properBintrees(int height){
        // 1.
        Set<Node> sTrees = new HashSet<>(); // hashCode excludes duplicates
        Node n = new Node();
        NodeUtils.pointersAndHash(n);
        sTrees.add(n);
        // 2.
        for(int i = 1; i <= height; i++){
            // 3.
            rTrees.clear();
            // 4.
            for(Node t1 : sTrees){
                if(NodeUtils.treeHeight(t1) != i-1)
                    continue; // jump to next
                // 5.
                for(Node t2 : sTrees){
                    int treeHeight = NodeUtils.treeHeight(t2);
                    if(treeHeight > i-2
                            && treeHeight != 0
                            && treeHeight != 1)
                        continue;

                    rTrees.add(btree(t1, t2)); // 6.
                    rTrees.add(btree(t2, t1)); // 7.
                }
                // 8.
                for(Node t2 : sTrees){
                    if(NodeUtils.treeHeight(t2) != i-1) continue;
                    rTrees.add(btree(t1, t2)); // 9.
                }
            }
            // 10.
            sTrees.addAll(rTrees);
        }
    }

    /** Create new tree. Calculate ids and parent pointers */
    private Node btree(Node t1, Node t2){
        Node newRoot = new Node();
        newRoot.leftChild = NodeUtils.copy(t1);
        newRoot.rightChild = NodeUtils.copy(t2);

        // generate hash to enable duplicate matching
        NodeUtils.pointersAndHash(newRoot);

        return newRoot;
    }
    public Set<Node> getR() {
        return rTrees;
    }
}

