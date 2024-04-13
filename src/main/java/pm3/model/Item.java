package pm3.model;

public class Item {
  protected Integer ItemID;
  protected String Name;
  protected Integer MaxStackSize;
  protected Double VendorPrice;
  protected Boolean ForSale;
  protected Integer ItemLevel;

  public Item(){}
  public Item(Integer itemID, String name, Integer maxStackSize, Boolean forSale,
      Integer itemLevel) {
    ItemID = itemID;
    Name = name;
    MaxStackSize = maxStackSize;
    ForSale = forSale;
    ItemLevel = itemLevel;
  }

  public Item(String name, Integer maxStackSize, Boolean forSale, Integer itemLevel) {
    Name = name;
    MaxStackSize = maxStackSize;
    ForSale = forSale;
    ItemLevel = itemLevel;
  }

  public Integer getItemID() {
    return ItemID;
  }

  public void setItemID(Integer itemID) {
    ItemID = itemID;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public Integer getMaxStackSize() {
    return MaxStackSize;
  }

  public void setMaxStackSize(Integer maxStackSize) {
    MaxStackSize = maxStackSize;
  }

  public Double getVendorPrice() {
    return VendorPrice;
  }

  public void setVendorPrice(Double vendorPrice) {
    VendorPrice = vendorPrice;
  }

  public Boolean getForSale() {
    return ForSale;
  }

  public void setForSale(Boolean forSale) {
    ForSale = forSale;
  }

  public Integer getItemLevel() {
    return ItemLevel;
  }

  public void setItemLevel(Integer itemLevel) {
    ItemLevel = itemLevel;
  }
}
