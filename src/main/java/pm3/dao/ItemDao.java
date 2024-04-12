package pm3.dao;

import pm3.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class ItemDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static ItemDao instance = null;
  protected ItemDao() {
    connectionManager = new ConnectionManager();
  }
  public static ItemDao getInstance() {
    if(instance == null) {
      instance = new ItemDao();
    }
    return instance;
  }

  public Item create(Item item) throws SQLException{
    String insertItem = "INSERT INTO Item(`Name`,MaxStackSize,VendorPrice,ForSale,ItemLevel) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertItem, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, item.getName());
      insertStmt.setInt(2, item.getMaxStackSize());
      if(item.getVendorPrice() == null){
        insertStmt.setNull(3, Types.DECIMAL);
      }else insertStmt.setDouble(3, item.getVendorPrice());
      insertStmt.setBoolean(4, item.getForSale());
      insertStmt.setInt(5, item.getItemLevel());
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int itemID = -1;
      if(resultKey.next()) {
        itemID = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      item.setItemID(itemID);

      return item;
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


  public Item getItemByID(int ItemID) throws SQLException {
    String selectItem = "SELECT `Name`,MaxStackSize,VendorPrice,ForSale,ItemLevel FROM Item WHERE ItemID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectItem);
      selectStmt.setInt(1, ItemID);
      results = selectStmt.executeQuery();
      if(results.next()) {
        String name = results.getString("Name");
        Integer MaxStackSize = results.getInt("MaxStackSize");
        Double VendorPrice = results.getDouble("VendorPrice");
        Boolean ForSale = results.getBoolean("ForSale");
        Integer ItemLevel = results.getInt("ItemLevel");
        Item item = new Item(ItemID, name, MaxStackSize, ForSale, ItemLevel);
        item.setVendorPrice(VendorPrice);
        return item;
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

  public Item delete(Item item) throws SQLException {
    String delteItem = "DELETE FROM Item WHERE ItemID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(delteItem);
      deleteStmt.setInt(1, item.getItemID());
      deleteStmt.executeUpdate();

      // Return null so the caller can no longer operate on the Persons instance.
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }

}
