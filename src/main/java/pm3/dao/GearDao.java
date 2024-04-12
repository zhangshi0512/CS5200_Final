package pm3.dao;

import pm3.model.Gear;
import pm3.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class GearDao extends ItemDao{
  private static GearDao instance = null;
  protected GearDao() {
    super();
  }
  public static GearDao getInstance() {
    if(instance == null) {
      instance = new GearDao();
    }
    return instance;
  }

  public Gear create(Gear gear) throws SQLException {
    if(gear.getItemID() == null){
      Item item = new Item(gear.getName(), gear.getMaxStackSize(),gear.getForSale(), gear.getItemLevel());
      item.setVendorPrice(gear.getVendorPrice());
      Item created_item = super.create(item);
      gear.setItemID(created_item.getItemID());
    }

    String insertGear = "INSERT INTO Gear(GearID,GearType,EquippableSlot,RequiredLevel,DefenseRating"
        + ",MagicDefenseRating,StrengthBonus,VitalityBonus,CriticalHitBonus,DeterminationBonus"
        + ",DexterityBonus,MindBonus,TenacityBonus,SkillSpeedBonus,CriticalHitRateBonus"
        + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertGear);
      insertStmt.setInt(1, gear.getItemID());
      insertStmt.setString(2, gear.getGearType());
      insertStmt.setString(3, gear.getEquippableSlot());
      insertStmt.setInt(4, gear.getRequiredLevel());
      insertStmt.setInt(5, gear.getDefenseRating());
      insertStmt.setInt(6, gear.getMagicDefenceRating());
      if(gear.getStrengthBonus() == null){
        insertStmt.setNull(7, Types.INTEGER);
      }else {
        insertStmt.setInt(7, gear.getStrengthBonus());
      }
      if(gear.getVitalityBonus() == null) {
        insertStmt.setNull(8, Types.INTEGER);
      }else{
        insertStmt.setInt(8, gear.getVitalityBonus());
      }
      if(gear.getCriticalHitBonus() == null) {
        insertStmt.setNull(9, Types.INTEGER);
      }else {
        insertStmt.setInt(9, gear.getCriticalHitBonus());
      }
      if(gear.getDeterminationBonus() == null) {
        insertStmt.setNull(10, Types.INTEGER);
      }else {
        insertStmt.setInt(10, gear.getDeterminationBonus());
      }
      if(gear.getDexterityBonus() == null) {
        insertStmt.setNull(11, Types.INTEGER);
      }else {
        insertStmt.setInt(11, gear.getDexterityBonus());
      }
      if(gear.getMindBonus() == null) {
        insertStmt.setNull(12, Types.INTEGER);
      }else {
        insertStmt.setInt(12, gear.getMindBonus());
      }
      if(gear.getTenacityBonus() == null) {
        insertStmt.setNull(13, Types.INTEGER);
      }else {
        insertStmt.setInt(13, gear.getTenacityBonus());
      }
      if(gear.getSkillSpeedBonus() == null) {
        insertStmt.setNull(14, Types.INTEGER);
      }else {
        insertStmt.setInt(14, gear.getSkillSpeedBonus());
      }
      if(gear.getCriticalHitRateBonus() == null) {
        insertStmt.setNull(15, Types.INTEGER);
      }else {
        insertStmt.setInt(15, gear.getCriticalHitRateBonus());
      }
      insertStmt.executeUpdate();

      return gear;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
    }
  }

  public Gear getGearByID(int GearID) throws SQLException{
    Item item = super.getItemByID(GearID);
    if(item == null){
      return null;
    }
    String selectGear =
        "SELECT GearID,GearType,EquippableSlot,RequiredLevel,DefenseRating"
            + ",MagicDefenseRating,StrengthBonus,VitalityBonus,CriticalHitBonus,DeterminationBonus"
            + ",DexterityBonus,MindBonus,TenacityBonus,SkillSpeedBonus"
            + ",CriticalHitRateBonus FROM Gear WHERE GearID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectGear);
      selectStmt.setInt(1, GearID);
      results = selectStmt.executeQuery();
      if(results.next()) {
        String GearType = results.getString("GearType");
        String EquippableSlot = results.getString("EquippableSlot");
        Integer RequiredLevel = results.getInt("RequiredLevel");
        Integer DefenseRating = results.getInt("DefenseRating");
        Integer MagicDefenseRating = results.getInt("MagicDefenseRating");
        Integer StrengthBonus = results.getInt("StrengthBonus");
        Integer VitalityBonus = results.getInt("VitalityBonus");
        Integer CriticalHitBonus = results.getInt("CriticalHitBonus");
        Integer DeterminationBonus = results.getInt("DeterminationBonus");
        Integer DexterityBonus = results.getInt("DexterityBonus");
        Integer MindBonus = results.getInt("MindBonus");
        Integer TenacityBonus = results.getInt("TenacityBonus");
        Integer SkillSpeedBonus = results.getInt("SkillSpeedBonus");
        Integer CriticalHitRateBonus = results.getInt("CriticalHitRateBonus");

        Gear gear = new Gear(GearID, item.getName(), item.getMaxStackSize(), item.getForSale(),
            item.getItemLevel(),GearType,EquippableSlot,RequiredLevel,DefenseRating,
            MagicDefenseRating);
        gear.setVendorPrice(item.getVendorPrice());
        gear.setStrengthBonus(StrengthBonus);
        gear.setVitalityBonus(VitalityBonus);
        gear.setCriticalHitBonus(CriticalHitBonus);
        gear.setDeterminationBonus(DeterminationBonus);
        gear.setDexterityBonus(DexterityBonus);
        gear.setMindBonus(MindBonus);
        gear.setTenacityBonus(TenacityBonus);
        gear.setSkillSpeedBonus(SkillSpeedBonus);
        gear.setCriticalHitRateBonus(CriticalHitRateBonus);

        return gear;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }

}
