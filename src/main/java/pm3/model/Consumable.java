package pm3.model;

public class Consumable extends Item{
  protected String Description;
  protected Double TenacityBonus;
  protected Double VitalityBonus;
  protected Double DeterminationBonus;
  protected Integer TenacityCap;
  protected Integer VitalityCap;
  protected Integer DeterminationCap;

  public Consumable(Integer itemID, String name, Integer maxStackSize, Boolean forSale,
      Integer itemLevel, String description) {
    super(itemID, name, maxStackSize, forSale, itemLevel);
    Description = description;
  }

  public Consumable(String name, Integer maxStackSize, Boolean forSale, Integer itemLevel,
      String description) {
    super(name, maxStackSize, forSale, itemLevel);
    Description = description;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }

  public Double getTenacityBonus() {
    return TenacityBonus;
  }

  public void setTenacityBonus(Double tenacityBonus) {
    TenacityBonus = tenacityBonus;
  }

  public Double getVitalityBonus() {
    return VitalityBonus;
  }

  public void setVitalityBonus(Double vitalityBonus) {
    VitalityBonus = vitalityBonus;
  }

  public Double getDeterminationBonus() {
    return DeterminationBonus;
  }

  public void setDeterminationBonus(Double determinationBonus) {
    DeterminationBonus = determinationBonus;
  }

  public Integer getTenacityCap() {
    return TenacityCap;
  }

  public void setTenacityCap(Integer tenacityCap) {
    TenacityCap = tenacityCap;
  }

  public Integer getVitalityCap() {
    return VitalityCap;
  }

  public void setVitalityCap(Integer vitalityCap) {
    VitalityCap = vitalityCap;
  }

  public Integer getDeterminationCap() {
    return DeterminationCap;
  }

  public void setDeterminationCap(Integer determinationCap) {
    DeterminationCap = determinationCap;
  }
}
