package pm3.model;

public class Job {
    protected int jobID;
    protected String name;
    protected int levelCap;

    public Job(int jobID){
        this.jobID = jobID;
    }

    public Job(int jobID, String name,int levelCap){
        this.jobID = jobID;
        this.name = name;
        this.levelCap = levelCap;
    }

    public Job(String name,int levelCap){
        this.name = name;
        this.levelCap = levelCap;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevelCap() {
        return levelCap;
    }

    public void setLevelCap(int levelCap) {
        this.levelCap = levelCap;
    }
}
