package pm3.model;

public class GearTypeJob {
    protected Job job;
    protected GearType gearType;

    public GearTypeJob(Job job, GearType gearType){
        this.job = job;
        this.gearType = gearType;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }
}
