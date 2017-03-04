package cu.uci.gitae.mdem.utils;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by angel on 16/02/17.
 */
public class DataLoadTest extends TestCase {

    private File dataset = new File("/media/Datos/PROYECTO/Datasets/kddcup_challenge/algebra_2008_2009_train.txt");

    public void testLoadDataSetByStudent() throws Exception {
        long init, finish;
        init = System.currentTimeMillis();
        DataLoad dl = new DataLoad(dataset);
        long cantidad = dl.loadDataSetByStudent("stu_e41db96ccd");
        finish = System.currentTimeMillis();
        System.out.println("Number of rows loaded: " + cantidad);
        System.out.println("Time used: " + (finish - init));
        Assert.assertEquals(cantidad, 3725);
    }

    public void testLoadStudentsId() throws IOException {
        long init, finish;
        init = System.currentTimeMillis();
        DataLoad dl = new DataLoad(dataset);
        Set<String> ids = dl.loadStudentsId();
        finish = System.currentTimeMillis();
        System.out.println("Number of studentsId loaded: " + ids.size());
        System.out.println("Time used: " + (finish - init));
        Assert.assertEquals(ids.size(), 3310);
    }

    public void testLoadSkillbyStudentId() throws IOException {
        DataLoad dl = new DataLoad(dataset);
        Set<String> skills = dl.loadSkillByStudentId("stu_e41db96ccd");
        System.out.print("Number of skills for student stu_e41db96ccd: ");
        System.out.println(skills.size());
        for (String skill : skills) {
            System.out.println(skill);
        }
    }

    public void testLoadDataSetByStudentAndSkill() throws IOException {
        DataLoad dl = new DataLoad(dataset);
        long cant = dl.loadDataSetByStudentAndSkill("stu_e41db96ccd", "Convert across system");
        System.out.print("Cantidad de filas para el estudiante stu_e41db96ccd con la habilidad \'C    onvert across system\": ");
        System.out.println(cant);

        File fichero = new File("/home/vazquez/Desktop/rows.txt");
        PrintWriter pw = new PrintWriter(new FileOutputStream(fichero));
        pw.println("ProblemName\t\tCorrects\tIncorrects\tCorrectFirstAtemp");
        for(DataSetRow row: dl.getDataSetRows()){
            pw.println(row.getProblemName()+"\t"+row.getCorrects()+"\t"+row.getIncorrects()
                    +"\t"+row.getCorrectFirstAttempt());
        }
        pw.close();
    }

    public void testLoadDataSetMap() throws IOException {
        DataLoad dl = new DataLoad(dataset, (long) 100000);
        long cantidad = dl.loadDataSetMap();
        System.out.println("Number of rows loaded: " + cantidad);
        Map<String, List<DataSetRow>> map = dl.getDataSetMap();
        Iterator<String> it = map.keySet().iterator();
        System.out.println("\nAnonStudentId");
        System.out.println("-------------");
        System.out.println("Number of diferent Students: " + map.keySet().size());
        while (it.hasNext()) {
            String key = it.next();
            System.out.println("ID: " + key + " -> Number of rows: " + map.get(key).size());
        }
        System.out.println("-----------------------------");
    }

    public void testLoadStudentsIdSingleFile() throws IOException {
        DataLoad dl =new DataLoad(dataset);
        File fichero = new File("/home/angel/PhDdata/studentsID.txt");
        Set<String> studentsId = dl.loadStudentsIdSingleFile(fichero);
        assertEquals(3310,studentsId.size());
        System.out.println(studentsId.size());
    }
}