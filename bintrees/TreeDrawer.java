package bintrees;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Set;

class TreeDrawer extends VBox {
    TreeDrawer(Set<Node> allTrees){
        int treeNbrForLabel = 1;
        for(Node tree : allTrees){
            Group treeGroup = new Group();
            Label treeNbr = new Label(treeNbrForLabel++ + ".");
            treeNbr.setStyle("-fx-font: 26px \"Serif\";");
            treeGroup.getChildren().add(treeNbr);

            draw(tree, treeGroup);
            getChildren().add(treeGroup);
            getChildren().add(new Label(tree.hashCode));
        }
    }

    public void draw(Node rootNode, Group treeGroup){
        NodeCircle rootCircle = new NodeCircle();
        treeGroup.getChildren().add(rootCircle);
        treeGroup.getChildren().add(new NodeId(rootCircle, rootNode.id));
        rootCircle.setCenterX(100);

        generateChildCircles(rootNode, rootCircle, rootCircle, treeGroup);
    }

    /** recursively generate child-circles */
    private void generateChildCircles(Node node, NodeCircle circle, NodeCircle rootCircle, Group g){
        if (node.leftChild != null && node.rightChild != null){
            circle.leftChild = new NodeCircle();
            circle.rightChild = new NodeCircle();
            g.getChildren().add(line(circle, circle.leftChild));
            g.getChildren().add(line(circle, circle.rightChild));
            g.getChildren().addAll( circle.leftChild, circle.rightChild);

            g.getChildren().add(new NodeId(circle.leftChild, node.leftChild.id));
            g.getChildren().add(new NodeId(circle.rightChild, node.rightChild.id));

            circle.position(rootCircle);
            generateChildCircles(node.leftChild, circle.leftChild, rootCircle, g);
            generateChildCircles(node.rightChild, circle.rightChild, rootCircle, g);
        }
    }

    private Line line(NodeCircle parent, NodeCircle child){
        Line l = new Line();
        l.startXProperty().bind(parent.centerXProperty());
        l.startYProperty().bind(parent.centerYProperty());
        l.endXProperty().bind(child.centerXProperty());
        l.endYProperty().bind(child.centerYProperty());
        return l;
    }
}

class NodeCircle extends Circle {
    NodeCircle leftChild;
    NodeCircle rightChild;
    NodeCircle(){
        setRadius(6);
    }
    public void position(NodeCircle root){
        double yOffset = 40;
        DoubleBinding depth = centerYProperty().subtract(root.centerYProperty());
        DoubleBinding siblingXOffset = new SimpleDoubleProperty(50)
                .subtract(depth.divide(yOffset*2).multiply(40));
        leftChild.centerXProperty().bind(centerXProperty().subtract(siblingXOffset));
        rightChild.centerXProperty().bind(centerXProperty().add(siblingXOffset));
        leftChild.centerYProperty().bind(centerYProperty().add(yOffset));
        rightChild.centerYProperty().bind(centerYProperty().add(yOffset));
    }
}

class NodeId extends Label {
    NodeId(NodeCircle ci, int i){
        setText(i+"");
        translateXProperty().bind(ci.centerXProperty().add(7));
        translateYProperty().bind(ci.centerYProperty().add(6));
    }
}
