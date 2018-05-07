package visitor.contract;

public interface Visitor<T> {
    void visit(T node);
}
