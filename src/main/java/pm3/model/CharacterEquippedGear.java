package pm3.model;

public class CharacterEquippedGear {
  protected String SlotName;
  protected Integer CharacterID;
  protected Integer GearID;

  public CharacterEquippedGear(String slotName, Integer characterID, Integer gearID) {
    SlotName = slotName;
    CharacterID = characterID;
    GearID = gearID;
  }

  public String getSlotName() {
    return SlotName;
  }

  public void setSlotName(String slotName) {
    SlotName = slotName;
  }

  public Integer getCharacterID() {
    return CharacterID;
  }

  public void setCharacterID(Integer characterID) {
    CharacterID = characterID;
  }

  public Integer getGearID() {
    return GearID;
  }

  public void setGearID(Integer gearID) {
    GearID = gearID;
  }
}
