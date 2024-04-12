package pm3.model;

public class WeaponTypeJob {
    protected Job job;
    protected WeaponType weaponType;

    public WeaponTypeJob(Job job, WeaponType weaponType){
        this.job = job;
        this.weaponType = weaponType;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public WeaponType getWeaponType() {
        return this.weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }
}
