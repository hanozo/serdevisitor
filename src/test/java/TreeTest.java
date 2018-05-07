import data.structure.Node;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.entities.Employee;

import java.io.IOException;

public class TreeTest {

    private static final Logger logger = LoggerFactory.getLogger(TreeTest.class);

    private static Node<Employee> tree;

    @BeforeClass
    public static void setup() {

        try {
            tree = Demo.getEmployeeTree();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Test
    public void assertFindEmployee() {
        Employee alon = Employee.newBuilder()
                .setName("Alon")
                .build();

        Assert.assertTrue(tree.contains(alon));
    }
}
