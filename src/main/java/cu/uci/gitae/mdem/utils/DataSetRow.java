package cu.uci.gitae.mdem.utils;

/**
 * Created by angel on 28/07/16.
 */
public class DataSetRow {
    /**
     * The row number
     */
    private Integer row;
    /**
     * Unique, anonymous identifier for a student
     */
    private String anonStudentId;
    /**
     * The hierarchy of curriculum levels containing the problem.
     */
    private String problemHierarchy;
    /**
     * Unique identifier for a problem
     */
    private String problemName;
    /**
     * The total number of times the student encountered the problem so far
     */
    private Integer problemView;
    /**
     * The step name is unique within each problem, but there may be collisions between different problems,
     * so the only unique identifier for a step is the pair of problem_name and step_name.
     */
    private String stepName;
    /**
     * The starting time of the step. Can be null.
     */
    private String stepStartTime;
    /**
     * The time of the first transaction toward the step
     */
    private String firstTransactionTime;
    /**
     * The time of the correct attempt toward the step, if there was one.
     */
    private String correctTransactionTime;
    /**
     * The time of the last transaction toward the step.
     */
    private String stepEndTime;
    /**
     * The elapsed time of the step in seconds, calculated by adding all of the durations for transactions that were
     * attributed to the step. Can be null (if step start time is null).
     */
    private Double stepDuration;
    /**
     * The step duration (in seconds) if the first attempt for the step was correct.
     */
    private Double correctStepDuration;
    /**
     * The step duration if the first attempt for the step was an error (incorrect attempt or hint request).
     */
    private Double errorStepDuration;
    /**
     * The tutor's evaluation of the student's first attempt on the step — 1 if correct, 0 if an error.
     */
    private Integer correctFirstAttempt;
    /**
     * Total number of incorrect attempts by the student on the step.
     */
    private Integer incorrects;
    /**
     * Total number of hints requested by the student for the step.
     */
    private Integer hints;
    /**
     * Total correct attempts by the student for the step. (Only increases if the step is encountered more than once.)
     */
    private Integer corrects;
    /**
     * the identified skills that are used in a problem, where available. A step can have multiple KCs assigned to it.
     * Multiple KCs for a step are separated by ~~ (two tildes).
     */
    private String[] kcSubSkills;
    /**
     * Opportunity describes practice by knowledge component, the corresponding opportunities are similarly separated
     * by ~~.
     */
    private String[] opportunitySubSkills;
    /**
     * TODO poner estos otros atributos igual que kcSubSkills y opportunitySubKills
     */
    private String kcKTracedSkills;
    private String opportunityKTracedSkills;
    private String kcRules;
    private String opportunityRules;

    public DataSetRow() {
    }

    public DataSetRow(Integer row, String anonStudentId, String problemHierarchy, String problemName, Integer problemView, String stepName, String stepStartTime, String firstTransactionTime, String correctTransactionTime, String stepEndTime, Double stepDuration, Double correctStepDuration, Double errorStepDuration, Integer correctFirstAttempt, Integer incorrects, Integer hints, Integer corrects, String[] kcSubSkills, String[] opportunitySubSkills, String kcKTracedSkills, String opportunityKTracedSkills, String kcRules, String opportunityRules) {
        this.row = row;
        this.anonStudentId = anonStudentId;
        this.problemHierarchy = problemHierarchy;
        this.problemName = problemName;
        this.problemView = problemView;
        this.stepName = stepName;
        this.stepStartTime = stepStartTime;
        this.firstTransactionTime = firstTransactionTime;
        this.correctTransactionTime = correctTransactionTime;
        this.stepEndTime = stepEndTime;
        this.stepDuration = stepDuration;
        this.correctStepDuration = correctStepDuration;
        this.errorStepDuration = errorStepDuration;
        this.correctFirstAttempt = correctFirstAttempt;
        this.incorrects = incorrects;
        this.hints = hints;
        this.corrects = corrects;
        this.kcSubSkills = kcSubSkills;
        this.opportunitySubSkills = opportunitySubSkills;
        this.kcKTracedSkills = kcKTracedSkills;
        this.opportunityKTracedSkills = opportunityKTracedSkills;
        this.kcRules = kcRules;
        this.opportunityRules = opportunityRules;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public String getAnonStudentId() {
        return anonStudentId;
    }

    public void setAnonStudentId(String anonStudentId) {
        this.anonStudentId = anonStudentId;
    }

    public String getProblemHierarchy() {
        return problemHierarchy;
    }

    public void setProblemHierarchy(String problemHierarchy) {
        this.problemHierarchy = problemHierarchy;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public Integer getProblemView() {
        return problemView;
    }

    public void setProblemView(Integer problemView) {
        this.problemView = problemView;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getStepStartTime() {
        return stepStartTime;
    }

    public void setStepStartTime(String stepStartTime) {
        this.stepStartTime = stepStartTime;
    }

    public String getFirstTransactionTime() {
        return firstTransactionTime;
    }

    public void setFirstTransactionTime(String firstTransactionTime) {
        this.firstTransactionTime = firstTransactionTime;
    }

    public String getCorrectTransactionTime() {
        return correctTransactionTime;
    }

    public void setCorrectTransactionTime(String correctTransactionTime) {
        this.correctTransactionTime = correctTransactionTime;
    }

    public String getStepEndTime() {
        return stepEndTime;
    }

    public void setStepEndTime(String stepEndTime) {
        this.stepEndTime = stepEndTime;
    }

    public Double getStepDuration() {
        return stepDuration;
    }

    public void setStepDuration(Double stepDuration) {
        this.stepDuration = stepDuration;
    }

    public Double getCorrectStepDuration() {
        return correctStepDuration;
    }

    public void setCorrectStepDuration(Double correctStepDuration) {
        this.correctStepDuration = correctStepDuration;
    }

    public Double getErrorStepDuration() {
        return errorStepDuration;
    }

    public void setErrorStepDuration(Double errorStepDuration) {
        this.errorStepDuration = errorStepDuration;
    }

    public Integer getCorrectFirstAttempt() {
        return correctFirstAttempt;
    }

    public void setCorrectFirstAttempt(Integer correctFirstAttempt) {
        this.correctFirstAttempt = correctFirstAttempt;
    }

    public Integer getIncorrects() {
        return incorrects;
    }

    public void setIncorrects(Integer incorrects) {
        this.incorrects = incorrects;
    }

    public Integer getHints() {
        return hints;
    }

    public void setHints(Integer hints) {
        this.hints = hints;
    }

    public Integer getCorrects() {
        return corrects;
    }

    public void setCorrects(Integer corrects) {
        this.corrects = corrects;
    }

    public String[] getKcSubSkills() {
        return kcSubSkills;
    }

    public void setKcSubSkills(String[] kcSubSkills) {
        this.kcSubSkills = kcSubSkills;
    }

    public String[] getOpportunitySubSkills() {
        return opportunitySubSkills;
    }

    public void setOpportunitySubSkills(String[] opportunitySubSkills) {
        this.opportunitySubSkills = opportunitySubSkills;
    }

    public String getKcKTracedSkills() {
        return kcKTracedSkills;
    }

    public void setKcKTracedSkills(String kcKTracedSkills) {
        this.kcKTracedSkills = kcKTracedSkills;
    }

    public String getOpportunityKTracedSkills() {
        return opportunityKTracedSkills;
    }

    public void setOpportunityKTracedSkills(String opportunityKTracedSkills) {
        this.opportunityKTracedSkills = opportunityKTracedSkills;
    }

    public String getKcRules() {
        return kcRules;
    }

    public void setKcRules(String kcRules) {
        this.kcRules = kcRules;
    }

    public String getOpportunityRules() {
        return opportunityRules;
    }

    public void setOpportunityRules(String opportunityRules) {
        this.opportunityRules = opportunityRules;
    }
}
