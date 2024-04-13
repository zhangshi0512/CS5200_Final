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
	        Item item = new Item(consumable.getName(), consumable.getMaxStackSize(), consumable.getForSale(), consumable.getItemLevel());
	        item.setVendorPrice(consumable.getVendorPrice());
	        Item created_item = super.create(item);
	        consumable.setItemID(created_item.getItemID());
	    }

	    String insertConsumable = "INSERT INTO Consumable(ConsumableID, `Description`, TenacityBonus, "
	        + "VitalityBonus, DeterminationBonus, TenacityCap, VitalityCap, DeterminationCap) "
	        + "VALUES(?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE Description=VALUES(Description), "
	        + "TenacityBonus=VALUES(TenacityBonus), VitalityBonus=VALUES(VitalityBonus), "
	        + "DeterminationBonus=VALUES(DeterminationBonus), TenacityCap=VALUES(TenacityCap), "
	        + "VitalityCap=VALUES(VitalityCap), DeterminationCap=VALUES(DeterminationCap);";

	    Connection connection = null;
	    PreparedStatement insertStmt = null;
	    try {
	        connection = connectionManager.getConnection();
	        insertStmt = connection.prepareStatement(insertConsumable);
	        insertStmt.setInt(1, consumable.getItemID());
	        insertStmt.setString(2, consumable.getDescription());
	        setDoubleOrNull(insertStmt, 3, consumable.getTenacityBonus());
	        setDoubleOrNull(insertStmt, 4, consumable.getVitalityBonus());
	        setDoubleOrNull(insertStmt, 5, consumable.getDeterminationBonus());
	        setIntOrNull(insertStmt, 6, consumable.getTenacityCap());
	        setIntOrNull(insertStmt, 7, consumable.getVitalityCap());
	        setIntOrNull(insertStmt, 8, consumable.getDeterminationCap());

	        insertStmt.executeUpdate();
	        return consumable;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        closeResources(connection, insertStmt);
	    }
	}

	private void setDoubleOrNull(PreparedStatement stmt, int parameterIndex, Double value) throws SQLException {
	    if (value == null) {
	        stmt.setNull(parameterIndex, Types.DECIMAL);
	    } else {
	        stmt.setDouble(parameterIndex, value);
	    }
	}

	private void setIntOrNull(PreparedStatement stmt, int parameterIndex, Integer value) throws SQLException {
	    if (value == null) {
	        stmt.setNull(parameterIndex, Types.INTEGER);
	    } else {
	        stmt.setInt(parameterIndex, value);
	    }
	}

	private void closeResources(Connection connection, PreparedStatement stmt) throws SQLException {
	    if (stmt != null) {
	        stmt.close();
	    }
	    if (connection != null) {
	        connection.close();
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
