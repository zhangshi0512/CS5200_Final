package pm3.dao;

import pm3.model.CharacterEquippedGear;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CharacterEquippedGearDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static CharacterEquippedGearDao instance = null;
  protected CharacterEquippedGearDao() {
    connectionManager = new ConnectionManager();
  }
  public static CharacterEquippedGearDao getInstance() {
    if(instance == null) {
      instance = new CharacterEquippedGearDao();
    }
    return instance;
  }

  public CharacterEquippedGear create(CharacterEquippedGear characterEquippedGear) throws SQLException {
    String insertCharacterEquippedGear = "INSERT INTO ChacterEquippedGear(SlotName,CharacterID,GearID) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCharacterEquippedGear);
      insertStmt.setString(1, characterEquippedGear.getSlotName());
      insertStmt.setInt(2, characterEquippedGear.getCharacterID());
      insertStmt.setDouble(3, characterEquippedGear.getGearID());
      insertStmt.executeUpdate();

      return characterEquippedGear;
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


  public CharacterEquippedGear getCharacterEquippedGearBySlotNameAndID(String SlotName, int CharacterID) throws SQLException {
    String selectCharacter = "SELECT SlotName,CharacterID,GearID FROM ChacterEquippedGear "
        + "WHERE SlotName=? AND CharacterID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCharacter);
      selectStmt.setString(1, SlotName);
      selectStmt.setInt(2, CharacterID);
      results = selectStmt.executeQuery();
      if(results.next()) {
        Integer GearID = results.getInt("GearID");
        CharacterEquippedGear characterEquippedGear = new CharacterEquippedGear(SlotName,CharacterID,GearID);
        return characterEquippedGear;
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
