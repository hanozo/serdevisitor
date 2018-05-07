import data.structure.Node;
import json.serde.JsonDeserializer;
import json.serde.JsonSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.entities.Employee;
import visitor.contract.FileSnapshotVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Demo {

    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws IOException {

        Node<Employee> tree = getEmployeeTree();

        try (FileSnapshotVisitor visitor = new FileSnapshotVisitor(Paths.get("/tmp/tree/out-tree"), new JsonSerializer<>())) {

            tree.acceptInOrder(visitor);
        }

    }

    public static Node<Employee> getEmployeeTree() throws IOException {

        Node<Employee> tree = new Node<>();

        Deserializer<Employee> de = new JsonDeserializer<>(Employee.class);

        ClassLoader classLoader = Demo.class.getClassLoader();

        Path path = Paths.get(classLoader.getResource("b-tree").getPath());

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.lines()
                    .map(json -> de.deserialize("", json.getBytes()))
                    .forEach(tree::insert);
        }
        return tree;
    }
}
