package pm3.model;

public class Character {
    protected int characterID;
    protected Player player;
    protected String firstName;
    protected String lastName;
    protected int maxHP;
    protected int maxMP;
    protected Job currentJob;
    protected String jobName;
    protected Weapon mainHandWeapon;
    protected int strength;
    protected int dexterity;
    protected int vitality;
    protected int intelligence;
    protected int mind;
    protected int criticalHit;
    protected int determination;
    protected int directHitRate;
    protected int defense;
    protected int magicDefense;
    protected int attackPower;
    protected int skillSpeed;
    protected int attackMagicPotency;
    protected int healingMagicPotency;
    protected int spellSpeed;
    protected int averageItemLevel;
    protected int tenacity;
    protected int piety;

    public Character(int characterID) {
        this.characterID = characterID;
    }

    public Character(int characterID, Player player, String firstName, String lastName, int maxHP, int maxMP, Job currentJob, Weapon mainHandWeapon, int strength, int dexterity, int vitality, int intelligence, int mind, int criticalHit, int determination, int directHitRate, int defense, int magicDefense, int attackPower, int skillSpeed, int attackMagicPotency, int healingMagicPotency, int spellSpeed, int averageItemLevel, int tenacity, int piety) {
        this.characterID = characterID;
        this.player = player;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maxHP = maxHP;
        this.maxMP = maxMP;
        this.currentJob = currentJob;
        this.mainHandWeapon = mainHandWeapon;
        this.strength = strength;
        this.dexterity = dexterity;
        this.vitality = vitality;
        this.intelligence = intelligence;
        this.mind = mind;
        this.criticalHit = criticalHit;
        this.determination = determination;
        this.directHitRate = directHitRate;
        this.defense = defense;
        this.magicDefense = magicDefense;
        this.attackPower = attackPower;
        this.skillSpeed = skillSpeed;
        this.attackMagicPotency = attackMagicPotency;
        this.healingMagicPotency = healingMagicPotency;
        this.spellSpeed = spellSpeed;
        this.averageItemLevel = averageItemLevel;
        this.tenacity = tenacity;
        this.piety = piety;
    }

    public Character(Player player, String firstName, String lastName, int maxHP, int maxMP, Job currentJob, Weapon mainHandWeapon, int strength, int dexterity, int vitality, int intelligence, int mind, int criticalHit, int determination, int directHitRate, int defense, int magicDefense, int attackPower, int skillSpeed, int attackMagicPotency, int healingMagicPotency, int spellSpeed, int averageItemLevel, int tenacity, int piety) {
        this.player = player;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maxHP = maxHP;
        this.maxMP = maxMP;
        this.currentJob = currentJob;
        this.mainHandWeapon = mainHandWeapon;
        this.strength = strength;
        this.dexterity = dexterity;
        this.vitality = vitality;
        this.intelligence = intelligence;
        this.mind = mind;
        this.criticalHit = criticalHit;
        this.determination = determination;
        this.directHitRate = directHitRate;
        this.defense = defense;
        this.magicDefense = magicDefense;
        this.attackPower = attackPower;
        this.skillSpeed = skillSpeed;
        this.attackMagicPotency = attackMagicPotency;
        this.healingMagicPotency = healingMagicPotency;
        this.spellSpeed = spellSpeed;
        this.averageItemLevel = averageItemLevel;
        this.tenacity = tenacity;
        this.piety = piety;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public void setMaxMP(int maxMP) {
        this.maxMP = maxMP;
    }

    public Job getCurrentJob() {
        return currentJob;
    }
    
    // Add a getter for the jobName
    public String getJobName() {
        return jobName;
    }

    // Add a setter for the jobName
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }

    public Weapon getMainHandWeapon() {
        return mainHandWeapon;
    }

    public void setMainHandWeapon(Weapon mainHandWeapon) {
        this.mainHandWeapon = mainHandWeapon;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getMind() {
        return mind;
    }

    public void setMind(int mind) {
        this.mind = mind;
    }

    public int getCriticalHit() {
        return criticalHit;
    }

    public void setCriticalHit(int criticalHit) {
        this.criticalHit = criticalHit;
    }

    public int getDetermination() {
        return determination;
    }

    public void setDetermination(int determination) {
        this.determination = determination;
    }

    public int getDirectHitRate() {
        return directHitRate;
    }

    public void setDirectHitRate(int directHitRate) {
        this.directHitRate = directHitRate;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMagicDefense() {
        return magicDefense;
    }

    public void setMagicDefense(int magicDefense) {
        this.magicDefense = magicDefense;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getSkillSpeed() {
        return skillSpeed;
    }

    public void setSkillSpeed(int skillSpeed) {
        this.skillSpeed = skillSpeed;
    }

    public int getAttackMagicPotency() {
        return attackMagicPotency;
    }

    public void setAttackMagicPotency(int attackMagicPotency) {
        this.attackMagicPotency = attackMagicPotency;
    }

    public int getHealingMagicPotency() {
        return healingMagicPotency;
    }

    public void setHealingMagicPotency(int healingMagicPotency) {
        this.healingMagicPotency = healingMagicPotency;
    }

    public int getSpellSpeed() {
        return spellSpeed;
    }

    public void setSpellSpeed(int spellSpeed) {
        this.spellSpeed = spellSpeed;
    }

    public int getAverageItemLevel() {
        return averageItemLevel;
    }

    public void setAverageItemLevel(int averageItemLevel) {
        this.averageItemLevel = averageItemLevel;
    }

    public int getTenacity() {
        return tenacity;
    }

    public void setTenacity(int tenacity) {
        this.tenacity = tenacity;
    }

    public int getPiety() {
        return piety;
    }

    public void setPiety(int piety) {
        this.piety = piety;
    }
}

