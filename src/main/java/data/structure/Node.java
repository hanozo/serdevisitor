package data.structure;

import visitor.contract.AcceptVisitor;
import visitor.contract.Visitor;

/**
 * @param <T>
 */
public class Node<T extends Comparable<T> & AcceptVisitor<T>> {

    private Node<T> left, right;
    private T data;

    public Node(T data) {
        this.data = data;
    }

    public Node() {

    }

    /**
     * @param data
     */
    public void insert(T data) {
        if (this.data == null) {
            this.data = data;
        } else if (data.compareTo(this.data) > 0) {
            if (right == null) {
                right = new Node<>(data);
            } else {
                right.insert(data);
            }
        } else {
            if (left == null) {
                left = new Node<>(data);
            } else {
                left.insert(data);
            }
        }
    }

    public boolean contains(T data) {

        if (data.compareTo(this.data) == 0) {
            return true;
        } else if (data.compareTo(this.data) > 0) {
            if (right == null) {
                return false;
            } else {
                return right.contains(data);
            }
        } else {
            if (left == null) {
                return false;
            } else {
                return left.contains(data);
            }
        }
    }

    public void acceptInOrder(Visitor<T> visitor) {
        if (left != null) left.acceptInOrder(visitor);
        this.data.accept(visitor);
        if (right != null) right.acceptInOrder(visitor);
    }
}
