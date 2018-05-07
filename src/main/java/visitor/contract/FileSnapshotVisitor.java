package visitor.contract;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.entities.Employee;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileSnapshotVisitor implements Visitor<Employee>, Closeable {

    private static final Logger logger = LoggerFactory.getLogger(FileSnapshotVisitor.class);

    private final Serializer<Employee> serializer;
    private final OutputStream stream;

    public FileSnapshotVisitor(Path path, Serializer<Employee> serializer) throws IOException {
        this.serializer = serializer;
        stream = Files.newOutputStream(path, StandardOpenOption.CREATE);
    }

    @Override
    public void visit(Employee node) {
        byte[] payload = serializer.serialize("", node);
        try {
            stream.write(payload);
            stream.write('\n');
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }
}
