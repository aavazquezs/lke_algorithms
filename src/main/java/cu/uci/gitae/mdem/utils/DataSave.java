package cu.uci.gitae.mdem.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by vazquez on 2/23/17.
 */
public class DataSave {
    private List<DataSetRow> data;

    public DataSave(List<DataSetRow> data) {
        this.data = data;
    }

    public DataSave() {
        this.data = new ArrayList<>();
    }

    public void saveStudentsIds(File fichero, Set<String> studentsID) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(fichero));
        for (String id: studentsID) {
            pw.println(id);
        }
        pw.close();
    }

    public void saveStudentSkillsById(File fichero, Set<String> skills) throws FileNotFoundException{
        PrintWriter pw = new PrintWriter(new FileOutputStream(fichero));
        for (String skill: skills) {
            pw.println(skill);
        }
        pw.close();
    }
}
