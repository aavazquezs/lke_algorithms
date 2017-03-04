package cu.uci.gitae.mdem.utils;
/*import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;*/

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by angel on 28/07/16.
 */
public class DataLoad {
    private List<DataSetRow> dataSetRows;
    private File dataset;
    private Long limit;
    private HashMap<String, List<DataSetRow>> dataSetMap;

    public DataLoad() {
        dataSetRows = new ArrayList<>();
        dataSetMap = new HashMap<>();
        this.limit = Long.MAX_VALUE;
    }

    public DataLoad(File dataset) {
        this.dataset = dataset;
        dataSetRows = new LinkedList<>();
        dataSetMap = new HashMap<>();
        this.limit = Long.MAX_VALUE;
    }

    public DataLoad(File dataset, Long limit) {
        this.dataset = dataset;
        this.limit = limit;
        this.dataSetRows = new LinkedList<>();
    }

    public Stream<DataSetRow> getDatasetStream() {
        return this.dataSetRows.stream();
    }

    public void setDataset(File dataset) {
        this.dataset = dataset;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long loadDataSetMap() throws IOException {
        this.dataSetMap = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.dataset)));
        bufferedReader.readLine();
        String line;
        long counter = 0;
        try {
            while ((line = bufferedReader.readLine()) != null && counter < limit) {
                DataSetRow row = this.loadRow(line);
                if (this.dataSetMap.containsKey(row.getAnonStudentId())) {
                    this.dataSetMap.get(row.getAnonStudentId()).add(row);
                } else {
                    List<DataSetRow> list = new ArrayList<>();
                    list.add(row);
                    this.dataSetMap.put(row.getAnonStudentId(), list);
                }
                counter++;
            }
        } catch (OutOfMemoryError e) {
            System.out.println(counter);
            e.printStackTrace();
        }
        return counter;

    }

    public Long loadDataSetWithLimit() throws IOException {
        this.dataSetRows = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.dataset)));
        bufferedReader.readLine();
        String line;
        long counter = 0;
        try {
            while ((line = bufferedReader.readLine()) != null && counter < limit) {
                DataSetRow row = this.loadRow(line);
                dataSetRows.add(row);
                counter++;
            }
        } catch (OutOfMemoryError e) {
            System.out.println(counter);
            e.printStackTrace();
        }

        return counter;
    }

    public Long loadDataSet() throws IOException {
        this.dataSetRows = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.dataset)));
        bufferedReader.readLine();
        String line;
        long counter = 0;
        while ((line = bufferedReader.readLine()) != null) {
            DataSetRow row = this.loadRow(line);
            dataSetRows.add(row);
            counter++;
        }
        return counter;
    }

    public Long loadDataSetByStudent(String studentID) throws IOException {
        this.dataSetRows = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.dataset)));
        bufferedReader.readLine();
        String line;
        long counter = 0;
        while ((line = bufferedReader.readLine()) != null) {
            DataSetRow row = this.loadRow(line);
            if (row.getAnonStudentId().equalsIgnoreCase(studentID)) {
                dataSetRows.add(row);
                counter++;
            }
        }
        return counter;
    }

    public Set<String> loadStudentsId() throws IOException {
        this.dataSetRows = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.dataset)));
        bufferedReader.readLine();
        String line;
        long counter = 0;
        Set<String> studentsId = new TreeSet<>();
        while ((line = bufferedReader.readLine()) != null) {
            studentsId.add(this.loadRow(line).getAnonStudentId());
        }
        return studentsId;
    }

    public Set<String> loadSkillByStudentId(String id) throws IOException {
        Set<String> skills = new TreeSet<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.dataset)));
        bufferedReader.readLine();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            DataSetRow row = this.loadRow(line);
            if (row.getAnonStudentId().equals(id)) {
                String[] kcs = row.getKcSubSkills();
                if (kcs != null) {
                    for (int i = 0; i < kcs.length; i++) {
                        if (!kcs[i].equals("") && !skills.contains(kcs[i])) {
                            skills.add(kcs[i]);
                        }
                    }
                }
            }
        }

        return skills;
    }

    public Long loadDataSetByStudentAndSkill(String studentID, String skill) throws IOException{
        this.dataSetRows = new ArrayList<>();
        long counter=0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.dataset)));
        bufferedReader.readLine();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            DataSetRow row = this.loadRow(line);
            if(row.getAnonStudentId().equals(studentID)){
                String[] kcs = row.getKcSubSkills();
                if(kcs !=null){
                    boolean encontrado = false;
                    for (int i = 0; i < kcs.length && !encontrado; i++) {
                        if(kcs[i].equals(skill)){
                            this.dataSetRows.add(row);
                            encontrado = true;
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }

    public Set<String> loadStudentsIdSingleFile(File single) throws FileNotFoundException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(single)));
        Set<String> set = new TreeSet<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            set.add(line);
        }
        return set;
    }

    //------------------------------------------------------------

    private DataSetRow loadRow(String line) {
        String[] parameters = line.split("\t");
        Integer count = parameters.length;

        DataSetRow row = new DataSetRow();
        for (int i = 0; i < count; i++) {
            switch (i) {
                case 0:
                    row.setRow(new Integer(parameters[0]));
                    break;
                case 1:
                    row.setAnonStudentId(parameters[1]);
                    break;
                case 2:
                    row.setProblemHierarchy(parameters[2]);
                    break;
                case 3:
                    row.setProblemName(parameters[3]);
                    break;
                case 4:
                    row.setProblemView(new Integer(parameters[4]));
                    break;
                case 5:
                    row.setStepName(parameters[5]);
                    break;
                case 6:
                    if (parameters[6].equalsIgnoreCase("")) {
                        row.setStepStartTime(null);
                    } else {
                        row.setStepStartTime(parameters[6]);
                    }
                    break;
                case 7:
                    if (parameters[7].equalsIgnoreCase("")) {
                        row.setFirstTransactionTime(null);
                    } else {
                        row.setFirstTransactionTime(parameters[7]);
                    }

                    break;
                case 8:
                    if (parameters[8].equalsIgnoreCase("")) {
                        row.setCorrectTransactionTime(null);
                    } else {
                        row.setCorrectTransactionTime(parameters[8]);
                    }
                    break;
                case 9:
                    if (parameters[9].equalsIgnoreCase("")) {
                        row.setStepEndTime(null);
                    } else {
//                            LocalDateTime stepEndTime = ;
//                            row.setStepEndTime(LocalDateTime.parse(parameters[9], formatter));
                        row.setStepEndTime(parameters[9]);
                    }

                    break;
                case 10:
                    if (parameters[10].equalsIgnoreCase("")) {
                        row.setStepDuration(-1.0);
                    } else {
                        row.setStepDuration(new Double(parameters[10]));
                    }
                    break;
                case 11:
                    if (parameters[11].equalsIgnoreCase("")) {
                        row.setCorrectStepDuration(-1.0);
                    } else {
                        row.setCorrectStepDuration(new Double(parameters[11]));
                    }
                    break;
                case 12:
                    if (parameters[12].equalsIgnoreCase("")) {
                        row.setErrorStepDuration(-1.0);
                    } else {
                        row.setErrorStepDuration(new Double(parameters[12]));
                    }
                    break;
                case 13:
                    if (parameters[13].equalsIgnoreCase("")) {
                        row.setCorrectFirstAttempt(-1);
                    } else {
                        row.setCorrectFirstAttempt(new Integer(parameters[13]));
                    }
                    break;
                case 14:
                    if (parameters[14].equalsIgnoreCase("")) {
                        row.setIncorrects(-1);
                    } else {
                        row.setIncorrects(new Integer(parameters[14]));
                    }
                    break;
                case 15:
                    if (parameters[15].equalsIgnoreCase("")) {
                        row.setHints(-1);
                    } else {
                        row.setHints(new Integer(parameters[15]));
                    }
                    break;
                case 16:
                    if (parameters[16].equalsIgnoreCase("")) {
                        row.setCorrects(-1);
                    } else {
                        row.setCorrects(new Integer(parameters[16]));
                    }
                    break;
                case 17:
                    String[] subSkills = parameters[17].split("~~");
                    row.setKcSubSkills(subSkills);
                    break;
                case 18:
                    String[] opportunitySubSkills = parameters[18].split("~~");
                    row.setOpportunitySubSkills(opportunitySubSkills);
                    break;
                case 19:
                    row.setKcKTracedSkills(parameters[19]);
                    break;
                case 20:
                    row.setOpportunityKTracedSkills(parameters[20]);
                    break;
                case 21:
                    row.setKcRules(parameters[21]);
                    break;
                case 22:
                    row.setOpportunityRules(parameters[22]);
                    break;
            }
        }
        return row;
    }

    public Integer getDatasetSize() {
        return this.dataSetRows.size();
    }

    public List<DataSetRow> getDataSetRows() {
        return dataSetRows;
    }

    public HashMap<String, List<DataSetRow>> getDataSetMap() {
        return dataSetMap;
    }
}
