package pm3.dao;

import pm3.model.Consumable;
import pm3.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ConsumableDao extends ItemDao{
  private static ConsumableDao instance = null;
  protected ConsumableDao() {
    super();
  }
  public static ConsumableDao getInstance() {
    if(instance == null) {
      instance = new ConsumableDao();
    }
    return instance;
  }

  public Consumable create(Consumable consumable) throws SQLException {
    if(consumable.getItemID() == null){
      Item item = new Item(consumable.getName(), consumable.getMaxStackSize(),consumable.getForSale(), consumable.getItemLevel());
      item.setVendorPrice(consumable.getVendorPrice());
      Item created_item = super.create(item);
      consumable.setItemID(created_item.getItemID());
    }

    String insertConsumable = "INSERT INTO Consumable(ConsumableID,`Description`,TenacityBonus"
        + ",VitalityBonus,DeterminationBonus,TenacityCap,VitalityCap,DeterminationCap"
        + ") VALUES(?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertConsumable);
      insertStmt.setInt(1, consumable.getItemID());
      insertStmt.setString(2, consumable.getDescription());
      if(consumable.getTenacityBonus() == null){
        insertStmt.setNull(3, Types.DECIMAL);
      }else{
        insertStmt.setDouble(3, consumable.getTenacityBonus());
      }
      if(consumable.getVitalityBonus() == null){
        insertStmt.setNull(4, Types.DECIMAL);
      }else{
        insertStmt.setDouble(4, consumable.getVitalityBonus());
      }
      if(consumable.getDeterminationBonus() == null){
        insertStmt.setNull(5, Types.DECIMAL);
      }else{
        insertStmt.setDouble(5, consumable.getDeterminationBonus());
      }
      if(consumable.getTenacityCap() == null){
        insertStmt.setNull(6, Types.INTEGER);
      }else{
        insertStmt.setInt(6, consumable.getTenacityCap());
      }
      if(consumable.getVitalityCap() == null){
        insertStmt.setNull(7, Types.INTEGER);
      }else{
        insertStmt.setInt(7, consumable.getVitalityCap());
      }
      if(consumable.getDeterminationCap() == null){
        insertStmt.setNull(8, Types.INTEGER);
      }else{
        insertStmt.setInt(8, consumable.getDeterminationCap());
      }
      insertStmt.executeUpdate();

      return consumable;
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

  public Consumable getConsumableByID(int ConsumableID) throws SQLException{
    Item item = super.getItemByID(ConsumableID);
    if(item == null){
      return null;
    }
    String selectConsumable =
        "SELECT ConsumableID,`Description`,TenacityBonus,VitalityBonus,DeterminationBonus,"
            + "TenacityCap,VitalityCap,DeterminationCap FROM Consumable WHERE ConsumableID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectConsumable);
      selectStmt.setInt(1, ConsumableID);
      results = selectStmt.executeQuery();
      if(results.next()) {
        String Description = results.getString("Description");
        Double TenacityBonus = results.getDouble("TenacityBonus");
        Double VitalityBonus = results.getDouble("VitalityBonus");
        Double DeterminationBonus = results.getDouble("DeterminationBonus");
        Integer TenacityCap = results.getInt("TenacityCap");
        Integer VitalityCap = results.getInt("VitalityCap");
        Integer DeterminationCap = results.getInt("DeterminationCap");

        Consumable consumable = new Consumable(ConsumableID, item.getName(), item.getMaxStackSize(),
            item.getForSale(), item.getItemLevel(),Description);
        consumable.setVendorPrice(item.getVendorPrice());
        consumable.setVitalityBonus(VitalityBonus);
        consumable.setDeterminationBonus(DeterminationBonus);
        consumable.setTenacityBonus(TenacityBonus);
        consumable.setTenacityCap(TenacityCap);
        consumable.setVitalityCap(VitalityCap);
        consumable.setDeterminationCap(DeterminationCap);

        return consumable;
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
