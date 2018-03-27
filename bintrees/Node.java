package bintrees;

import java.util.Objects;

class Node {
    public Node leftChild;
    public Node rightChild;
    public String hashCode = "0";
    public int id;
    public int depth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return hashCode.equals(node.hashCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashCode);
    }
}

