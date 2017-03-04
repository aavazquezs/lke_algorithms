package cu.uci.gitae.mdem.irt.clases;

import cu.uci.gitae.mdem.utils.DataSetRow;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by mdem on 2/11/17.
 */
public class DataLoadEasy {
    private List<Item> items;
    private File dataset;
    private List<DataSetRow> dataSetRows;

    public DataLoadEasy(String url) {
        this.items = new LinkedList<>();
        this.dataset=new File(url);
        dataSetRows=new LinkedList<>();
    }

    public void loadItems() throws IOException {
        BufferedReader b= new BufferedReader(new InputStreamReader(new FileInputStream(dataset)));
        String line=b.readLine();
        while((line=b.readLine())!=null){
            String [] parametros=line.split("\t");
            Random random=new Random();
            items.add(new Item(Double.parseDouble(parametros[2]),Double.parseDouble(parametros[3]),Double.parseDouble(parametros[4]),random.nextInt(2)));
        }
    }

    public void loadItems2(String url,int limite) throws IOException {
        this.dataset=new File(url);
        BufferedReader b= new BufferedReader(new InputStreamReader(new FileInputStream(dataset)));
        String line=b.readLine();
        int it=0;
        while((line=b.readLine())!=null&&it<limite){
            Random random=new Random();
            items.add(new Item(random.nextDouble(),random.nextDouble(),random.nextDouble(),random.nextInt(2)));
            it++;
        }
    }

    public void loadItemsDataSetRow(String url,int limite) throws IOException {
        this.dataset=new File(url);
        BufferedReader b= new BufferedReader(new InputStreamReader(new FileInputStream(dataset)));
        String line=b.readLine();
        int it=0;
        while((line=b.readLine())!=null&&it<limite){
            String [] parameters=line.split("\t");
            Integer count = parameters.length;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.S]");
            DataSetRow row = new DataSetRow();
            for (int i = 0; i < count; i++) {
                /*switch (i) {
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
                            LocalDateTime stepStartTime = LocalDateTime.parse(parameters[6], formatter);
                            row.setStepStartTime(stepStartTime);
                        }
                        break;
                    case 7:
                        if (parameters[7].equalsIgnoreCase("")) {
                            row.setFirstTransactionTime(null);
                        } else {
                            LocalDateTime firstTransactionTime = LocalDateTime.parse(parameters[7], formatter);
                            row.setFirstTransactionTime(firstTransactionTime);
                        }
                        break;
                    case 8:
                        if (parameters[8].equalsIgnoreCase("")) {
                            row.setCorrectTransactionTime(null);
                        } else {
                            LocalDateTime correctTransactionTime = LocalDateTime.parse(parameters[8], formatter);
                            row.setCorrectTransactionTime(correctTransactionTime);
                        }
                        break;
                    case 9:
                        if (parameters[9].equalsIgnoreCase("")) {
                            row.setStepEndTime(null);
                        } else {
                            LocalDateTime stepEndTime = LocalDateTime.parse(parameters[9], formatter);
                            row.setStepEndTime(stepEndTime);
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
                        row.setKcSubSkills(parameters[17]);
                        break;
                    case 18:
                        row.setOpportunitySubSkills(parameters[18]);
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
                }*/
            }
            dataSetRows.add(row);
            it++;
        }
    }

    public List<DataSetRow> getDataSetRows() {
        return dataSetRows;
    }

    public List<Item> getItems() {
        return items;
    }
}
