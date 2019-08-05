package RansomeWare;

import java.util.ArrayList;
import java.util.List;

public class AvoidedDir {
    public List<String> avoidDir = new ArrayList<>();

    public AvoidedDir() {
        avoidDir.add("windows");
        avoidDir.add("library");
        avoidDir.add("boot");
        avoidDir.add("local");
        avoidDir.add("program files");
        avoidDir.add("programdata");
        avoidDir.add("$");
    }
}
