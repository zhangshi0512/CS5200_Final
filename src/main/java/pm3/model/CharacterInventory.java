package pm3.model;

public class CharacterInventory {
    protected int inventorySlotID;
    protected Character character;
    protected Item item;
    protected Integer stackSize;

    public CharacterInventory(Character character) {
        this.character = character;
    }

    public int getInventorySlotID() {
        return inventorySlotID;
    }

    public void setInventorySlotID(int inventorySlotID) {
        this.inventorySlotID = inventorySlotID;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getStackSize() {
        return stackSize;
    }

    public void setStackSize(Integer stackSize) {
        this.stackSize = stackSize;
    }
}
