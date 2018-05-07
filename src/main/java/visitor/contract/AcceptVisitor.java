package visitor.contract;

public interface AcceptVisitor<T> {
    void accept(Visitor<T> visitor);
}
