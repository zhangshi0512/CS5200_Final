package pm3.model;

public class Weapon extends Item{
  protected String weaponType;
  protected int requiredLevel;
  protected int damageDone;
  protected double autoAttack;
  protected double attackDelay;
  protected int strengthBonus;
  protected int vitalityBonus;
  protected int determinationBonus;
  protected int directHitRateBonus;
  protected int tenacityBonus;
  protected int skillSpeedBonus;
  protected int criticalHitBonus;

  public Weapon() {
    super();
  }

  public Weapon(Integer itemID, String name, Integer maxStackSize, Boolean forSale,
                Integer itemLevel,
                String weaponType, Integer requiredLevel, Integer damageDone, Double autoAttack,
                Double attackDelay) {
    super(itemID, name, maxStackSize, forSale, itemLevel);
    this.weaponType = weaponType;
    this.requiredLevel = requiredLevel;
    this.damageDone = damageDone;
    this.autoAttack = autoAttack;
    this.attackDelay = attackDelay;
  }

  public Weapon(String name, Integer maxStackSize, Boolean forSale, Integer itemLevel,
                String weaponType, Integer requiredLevel, Integer damageDone, Double autoAttack,
                Double attackDelay) {
    super(name, maxStackSize, forSale, itemLevel);
    this.weaponType = weaponType;
    this.requiredLevel = requiredLevel;
    this.damageDone = damageDone;
    this.autoAttack = autoAttack;
    this.attackDelay = attackDelay;
  }

  public String getWeaponType() {
    return this.weaponType;
  }

  public void setWeaponType(String weaponType) {
    this.weaponType = weaponType;
  }

  public Integer getRequiredLevel() {
    return this.requiredLevel;
  }

  public void setRequiredLevel(Integer requiredLevel) {
    this.requiredLevel = requiredLevel;
  }

  public Integer getDamageDone() {
    return this.damageDone;
  }

  public void setDamageDone(Integer damageDone) {
    this.damageDone = damageDone;
  }

  public Double getAutoAttack() {
    return this.autoAttack;
  }

  public void setAutoAttack(Double autoAttack) {
    this.autoAttack = autoAttack;
  }

  public Double getAttackDelay() {
    return this.attackDelay;
  }

  public void setAttackDelay(Double attackDelay) {
    this.attackDelay = attackDelay;
  }

  public Integer getStrengthBonus() {
    return this.strengthBonus;
  }

  public void setStrengthBonus(Integer strengthBonus) {
    this.strengthBonus = strengthBonus;
  }

  public Integer getVitalityBonus() {
    return this.vitalityBonus;
  }

  public void setVitalityBonus(Integer vitalityBonus) {
    this.vitalityBonus = vitalityBonus;
  }

  public Integer getDeterminationBonus() {
    return this.determinationBonus;
  }

  public void setDeterminationBonus(Integer determinationBonus) {
    this.determinationBonus = determinationBonus;
  }

  public Integer getDirectHitRateBonus() {
    return this.directHitRateBonus;
  }

  public void setDirectHitRateBonus(Integer directHitRateBonus) {
    this.directHitRateBonus = directHitRateBonus;
  }

  public Integer getTenacityBonus() {
    return this.tenacityBonus;
  }

  public void setTenacityBonus(Integer tenacityBonus) {
    this.tenacityBonus = tenacityBonus;
  }

  public Integer getSkillSpeedBonus() {
    return this.skillSpeedBonus;
  }

  public void setSkillSpeedBonus(Integer skillSpeedBonus) {
    this.skillSpeedBonus = skillSpeedBonus;
  }

  public Integer getCriticalHitBonus() {
    return this.criticalHitBonus;
  }

  public void setCriticalHitBonus(Integer criticalHitBonus) {
    this.criticalHitBonus = criticalHitBonus;
  }
}
