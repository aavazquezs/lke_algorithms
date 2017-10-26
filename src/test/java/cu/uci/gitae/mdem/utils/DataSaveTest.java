package cu.uci.gitae.mdem.utils;

import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by vazquez on 2/23/17.
 */
public class DataSaveTest extends TestCase {

    File dataset = new File("/media/Datos/Usuarios/Angel/PhD/Datasets/algebra_2008_2009_train.txt");
    Set<String> studentsSet = new TreeSet<>();

    public void testSaveStudentsIds() throws Exception {
        DataLoad dl = new DataLoad(dataset);
        Set<String> ids = dl.loadStudentsId();
        studentsSet.addAll(ids);
        DataSave ds = new DataSave();
        File fichero = new File("${PROJECT_LOC}/datasets/studentsID.txt");
        ds.saveStudentsIds(fichero,ids);
    }

    public void testSaveStudentSkillsById() throws IOException {
        DataLoad dl = new DataLoad(dataset);
        String folder = "${PROJECT_LOC}/datasets/studentsSkills/";
        DataSave ds = new DataSave();
        //PrintWriter pw;
        File sset = new File("${PROJECT_LOC}/datasets/studentsID.txt");
        studentsSet = dl.loadStudentsIdSingleFile(sset);
        for (String student: studentsSet) {
            File fichero = new File(folder+student+".txt");
            Set<String> skills = dl.loadSkillByStudentId(student);
            ds.saveStudentSkillsById(fichero,skills);
        }
    }

}