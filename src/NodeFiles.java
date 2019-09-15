import java.io.File;

public class NodeFiles {
    private File file;

    public NodeFiles() {
    }

    public NodeFiles(File file) {
        this.file = file;
    }

    public File getJsonFile() {
        return file;
    }
}
