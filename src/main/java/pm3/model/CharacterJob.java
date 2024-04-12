package pm3.model;

public class CharacterJob {
    protected Character character;
    protected Job job;
    protected int level;

    public CharacterJob(Character character, Job job) {
        this.character = character;
        this.job = job;
    }

    public CharacterJob(int level) {
        this.level = level;
    }

    public CharacterJob(Character character, Job job, int level) {
        this.character = character;
        this.job = job;
        this.level = level;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
