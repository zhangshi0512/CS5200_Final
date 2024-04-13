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

  public Weapon(Integer itemID, String name, Integer maxStackSize, Boolean forSale,
      Integer itemLevel,
      String weaponType, Integer requiredLevel, Integer damageDone, Double autoAttack,
      Double attackDelay) {
    super(itemID, name, maxStackSize, forSale, itemLevel);
    weaponType = weaponType;
    requiredLevel = requiredLevel;
    damageDone = damageDone;
    autoAttack = autoAttack;
    attackDelay = attackDelay;
  }

  public Weapon(String name, Integer maxStackSize, Boolean forSale, Integer itemLevel,
      String weaponType, Integer requiredLevel, Integer damageDone, Double autoAttack,
      Double attackDelay) {
    super(name, maxStackSize, forSale, itemLevel);
    weaponType = weaponType;
    requiredLevel = requiredLevel;
    damageDone = damageDone;
    autoAttack = autoAttack;
    attackDelay = attackDelay;
  }

  public String getWeaponType() {
    return weaponType;
  }

  public void setWeaponType(String weaponType) {
    weaponType = weaponType;
  }

  public Integer getRequiredLevel() {
    return requiredLevel;
  }

  public void setRequiredLevel(Integer requiredLevel) {
    requiredLevel = requiredLevel;
  }

  public Integer getDamageDone() {
    return damageDone;
  }

  public void setDamageDone(Integer damageDone) {
    damageDone = damageDone;
  }

  public Double getAutoAttack() {
    return autoAttack;
  }

  public void setAutoAttack(Double autoAttack) {
    autoAttack = autoAttack;
  }

  public Double getAttackDelay() {
    return attackDelay;
  }

  public void setAttackDelay(Double attackDelay) {
    attackDelay = attackDelay;
  }

  public Integer getStrengthBonus() {
    return strengthBonus;
  }

  public void setStrengthBonus(Integer strengthBonus) {
    strengthBonus = strengthBonus;
  }

  public Integer getVitalityBonus() {
    return vitalityBonus;
  }

  public void setVitalityBonus(Integer vitalityBonus) {
    vitalityBonus = vitalityBonus;
  }

  public Integer getDeterminationBonus() {
    return determinationBonus;
  }

  public void setDeterminationBonus(Integer determinationBonus) {
    determinationBonus = determinationBonus;
  }

  public Integer getDirectHitRateBonus() {
    return directHitRateBonus;
  }

  public void setDirectHitRateBonus(Integer directHitRateBonus) {
    directHitRateBonus = directHitRateBonus;
  }

  public Integer getTenacityBonus() {
    return tenacityBonus;
  }

  public void setTenacityBonus(Integer tenacityBonus) {
    tenacityBonus = tenacityBonus;
  }

  public Integer getSkillSpeedBonus() {
    return skillSpeedBonus;
  }

  public void setSkillSpeedBonus(Integer skillSpeedBonus) {
    skillSpeedBonus = skillSpeedBonus;
  }

  public Integer getCriticalHitBonus() {
    return criticalHitBonus;
  }

  public void setCriticalHitBonus(Integer criticalHitBonus) {
    criticalHitBonus = criticalHitBonus;
  }
}
