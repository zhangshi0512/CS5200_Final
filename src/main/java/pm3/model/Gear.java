package pm3.model;

public class Gear extends Item{
  protected String GearType;
  protected String EquippableSlot;

  protected Integer RequiredLevel;
  protected Integer DefenseRating;
  protected Integer MagicDefenceRating;

  protected Integer StrengthBonus;
  protected Integer VitalityBonus;
  protected Integer CriticalHitBonus;
  protected Integer DeterminationBonus;
  protected Integer DexterityBonus;
  protected Integer MindBonus;
  protected Integer TenacityBonus;
  protected Integer SkillSpeedBonus;
  protected Integer CriticalHitRateBonus;

  public Gear(Integer itemID, String name, Integer maxStackSize, Boolean forSale, Integer itemLevel,
      String gearType, String equippableSlot, Integer requiredLevel, Integer defenseRating,
      Integer magicDefenceRating) {
    super(itemID, name, maxStackSize, forSale, itemLevel);
    GearType = gearType;
    EquippableSlot = equippableSlot;
    RequiredLevel = requiredLevel;
    DefenseRating = defenseRating;
    MagicDefenceRating = magicDefenceRating;
  }

  public Gear(String name, Integer maxStackSize, Boolean forSale, Integer itemLevel,
      String gearType,
      String equippableSlot, Integer requiredLevel, Integer defenseRating,
      Integer magicDefenceRating) {
    super(name, maxStackSize, forSale, itemLevel);
    GearType = gearType;
    EquippableSlot = equippableSlot;
    RequiredLevel = requiredLevel;
    DefenseRating = defenseRating;
    MagicDefenceRating = magicDefenceRating;
  }

  public String getGearType() {
    return GearType;
  }

  public void setGearType(String gearType) {
    GearType = gearType;
  }

  public String getEquippableSlot() {
    return EquippableSlot;
  }

  public void setEquippableSlot(String equippableSlot) {
    EquippableSlot = equippableSlot;
  }

  public Integer getRequiredLevel() {
    return RequiredLevel;
  }

  public void setRequiredLevel(Integer requiredLevel) {
    RequiredLevel = requiredLevel;
  }

  public Integer getDefenseRating() {
    return DefenseRating;
  }

  public void setDefenseRating(Integer defenseRating) {
    DefenseRating = defenseRating;
  }

  public Integer getMagicDefenceRating() {
    return MagicDefenceRating;
  }

  public void setMagicDefenceRating(Integer magicDefenceRating) {
    MagicDefenceRating = magicDefenceRating;
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

  public Integer getCriticalHitBonus() {
    return CriticalHitBonus;
  }

  public void setCriticalHitBonus(Integer criticalHitBonus) {
    CriticalHitBonus = criticalHitBonus;
  }

  public Integer getDeterminationBonus() {
    return DeterminationBonus;
  }

  public void setDeterminationBonus(Integer determinationBonus) {
    DeterminationBonus = determinationBonus;
  }

  public Integer getDexterityBonus() {
    return DexterityBonus;
  }

  public void setDexterityBonus(Integer dexterityBonus) {
    DexterityBonus = dexterityBonus;
  }

  public Integer getMindBonus() {
    return MindBonus;
  }

  public void setMindBonus(Integer mindBonus) {
    MindBonus = mindBonus;
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

  public Integer getCriticalHitRateBonus() {
    return CriticalHitRateBonus;
  }

  public void setCriticalHitRateBonus(Integer criticalHitRateBonus) {
    CriticalHitRateBonus = criticalHitRateBonus;
  }
}
