package pm3.dao;

import pm3.model.Gear;
import pm3.model.Item;
import pm3.model.Weapon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class WeaponDao extends ItemDao{
  private static WeaponDao instance = null;
  protected WeaponDao() {
    super();
  }
  public static WeaponDao getInstance() {
    if(instance == null) {
      instance = new WeaponDao();
    }
    return instance;
  }

  public Weapon create(Weapon weapon) throws SQLException {
    if(weapon.getItemID() == null){
      Item item = new Item(weapon.getName(), weapon.getMaxStackSize(),weapon.getForSale(), weapon.getItemLevel());
      item.setVendorPrice(weapon.getVendorPrice());
      Item created_item = super.create(item);
      weapon.setItemID(created_item.getItemID());
    }

    String insertWeapon = "INSERT INTO Weapon(WeaponID,WeaponType,RequiredLevel,DamageDone"
        + ",AutoAttack,AttackDelay,StrengthBonus,VitalityBonus,DeterminationBonus,DirectHitRateBonus"
        + ",SkillSpeedBonus,TenacityBonus,CriticalHitBonus"
        + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertWeapon);
      insertStmt.setInt(1, weapon.getItemID());
      insertStmt.setString(2, weapon.getWeaponType());
      insertStmt.setInt(3, weapon.getRequiredLevel());
      insertStmt.setInt(4, weapon.getDamageDone());
      insertStmt.setDouble(5, weapon.getAutoAttack());
      insertStmt.setDouble(6, weapon.getAttackDelay());
      if(weapon.getStrengthBonus() == null) {
        insertStmt.setNull(7, Types.INTEGER);
      }else{
        insertStmt.setInt(7, weapon.getStrengthBonus());
      }
      if(weapon.getVitalityBonus() == null) {
        insertStmt.setNull(8, Types.INTEGER);
      }else{
        insertStmt.setInt(8, weapon.getVitalityBonus());
      }
      if(weapon.getDeterminationBonus() == null) {
        insertStmt.setNull(9, Types.INTEGER);
      }else{
        insertStmt.setInt(9, weapon.getDeterminationBonus());
      }
      if(weapon.getDirectHitRateBonus() == null) {
        insertStmt.setNull(10, Types.INTEGER);
      }else{
        insertStmt.setInt(10, weapon.getDirectHitRateBonus());
      }
      if(weapon.getSkillSpeedBonus() == null) {
        insertStmt.setNull(11, Types.INTEGER);
      }else{
        insertStmt.setInt(11, weapon.getSkillSpeedBonus());
      }
      if(weapon.getTenacityBonus() == null) {
        insertStmt.setNull(12, Types.INTEGER);
      }else{
        insertStmt.setInt(12, weapon.getTenacityBonus());
      }
      if(weapon.getCriticalHitBonus() == null) {
        insertStmt.setNull(13, Types.INTEGER);
      }else{
        insertStmt.setInt(13, weapon.getCriticalHitBonus());
      }
      insertStmt.executeUpdate();

      return weapon;
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
  public Weapon getWeaponByID(int WeaponID) throws SQLException{
    Item item = super.getItemByID(WeaponID);
    if(item == null){
      return null;
    }
    String selectWeapon =
        "SELECT WeaponID,WeaponType,RequiredLevel,DamageDone"
            +",AutoAttack,AttackDelay,StrengthBonus,VitalityBonus,DeterminationBonus"
            + ",DirectHitRateBonus,SkillSpeedBonus,TenacityBonus,CriticalHitBonus "
            + "FROM Weapon WHERE WeaponID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectWeapon);
      selectStmt.setInt(1, WeaponID);
      results = selectStmt.executeQuery();
      if(results.next()) {
        String WeaponType = results.getString("WeaponType");
        Integer RequiredLevel = results.getInt("RequiredLevel");
        Integer DamageDone = results.getInt("DamageDone");
        Double AutoAttack = results.getDouble("AutoAttack");
        Double AttackDelay = results.getDouble("AttackDelay");
        Integer StrengthBonus = results.getInt("StrengthBonus");
        Integer VitalityBonus = results.getInt("VitalityBonus");
        Integer CriticalHitBonus = results.getInt("CriticalHitBonus");
        Integer DeterminationBonus = results.getInt("DeterminationBonus");
        Integer DirectHitRateBonus = results.getInt("DirectHitRateBonus");
        Integer TenacityBonus = results.getInt("TenacityBonus");
        Integer SkillSpeedBonus = results.getInt("SkillSpeedBonus");

        Weapon weapon = new Weapon(WeaponID, item.getName(), item.getMaxStackSize(),
            item.getForSale(), item.getItemLevel(), WeaponType, RequiredLevel, DamageDone,
            AutoAttack, AttackDelay);
        weapon.setVendorPrice(item.getVendorPrice());
        weapon.setStrengthBonus(StrengthBonus);
        weapon.setVitalityBonus(VitalityBonus);
        weapon.setCriticalHitBonus(CriticalHitBonus);
        weapon.setDeterminationBonus(DeterminationBonus);
        weapon.setDirectHitRateBonus(DirectHitRateBonus);
        weapon.setTenacityBonus(TenacityBonus);
        weapon.setSkillSpeedBonus(SkillSpeedBonus);

        return weapon;
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
