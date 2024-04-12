package pm3.model;

public class Weapon extends Item{
  protected String WeaponType;
  protected Integer RequiredLevel;
  protected Integer DamageDone;
  protected Double AutoAttack;
  protected Double AttackDelay;
  protected Integer StrengthBonus;
  protected Integer VitalityBonus;
  protected Integer DeterminationBonus;
  protected Integer DirectHitRateBonus;
  protected Integer TenacityBonus;
  protected Integer SkillSpeedBonus;
  protected Integer CriticalHitBonus;

  public Weapon(Integer itemID, String name, Integer maxStackSize, Boolean forSale,
      Integer itemLevel,
      String weaponType, Integer requiredLevel, Integer damageDone, Double autoAttack,
      Double attackDelay) {
    super(itemID, name, maxStackSize, forSale, itemLevel);
    WeaponType = weaponType;
    RequiredLevel = requiredLevel;
    DamageDone = damageDone;
    AutoAttack = autoAttack;
    AttackDelay = attackDelay;
  }

  public Weapon(String name, Integer maxStackSize, Boolean forSale, Integer itemLevel,
      String weaponType, Integer requiredLevel, Integer damageDone, Double autoAttack,
      Double attackDelay) {
    super(name, maxStackSize, forSale, itemLevel);
    WeaponType = weaponType;
    RequiredLevel = requiredLevel;
    DamageDone = damageDone;
    AutoAttack = autoAttack;
    AttackDelay = attackDelay;
  }

  public String getWeaponType() {
    return WeaponType;
  }

  public void setWeaponType(String weaponType) {
    WeaponType = weaponType;
  }

  public Integer getRequiredLevel() {
    return RequiredLevel;
  }

  public void setRequiredLevel(Integer requiredLevel) {
    RequiredLevel = requiredLevel;
  }

  public Integer getDamageDone() {
    return DamageDone;
  }

  public void setDamageDone(Integer damageDone) {
    DamageDone = damageDone;
  }

  public Double getAutoAttack() {
    return AutoAttack;
  }

  public void setAutoAttack(Double autoAttack) {
    AutoAttack = autoAttack;
  }

  public Double getAttackDelay() {
    return AttackDelay;
  }

  public void setAttackDelay(Double attackDelay) {
    AttackDelay = attackDelay;
  }

  public Integer getStrengthBonus() {
    return StrengthBonus;
  }

  public void setStrengthBonus(Integer strengthBonus) {
    StrengthBonus = strengthBonus;
  }

  public Integer getVitalityBonus() {
    return VitalityBonus;
  }

  public void setVitalityBonus(Integer vitalityBonus) {
    VitalityBonus = vitalityBonus;
  }

  public Integer getDeterminationBonus() {
    return DeterminationBonus;
  }

  public void setDeterminationBonus(Integer determinationBonus) {
    DeterminationBonus = determinationBonus;
  }

  public Integer getDirectHitRateBonus() {
    return DirectHitRateBonus;
  }

  public void setDirectHitRateBonus(Integer directHitRateBonus) {
    DirectHitRateBonus = directHitRateBonus;
  }

  public Integer getTenacityBonus() {
    return TenacityBonus;
  }

  public void setTenacityBonus(Integer tenacityBonus) {
    TenacityBonus = tenacityBonus;
  }

  public Integer getSkillSpeedBonus() {
    return SkillSpeedBonus;
  }

  public void setSkillSpeedBonus(Integer skillSpeedBonus) {
    SkillSpeedBonus = skillSpeedBonus;
  }

  public Integer getCriticalHitBonus() {
    return CriticalHitBonus;
  }

  public void setCriticalHitBonus(Integer criticalHitBonus) {
    CriticalHitBonus = criticalHitBonus;
  }
}
