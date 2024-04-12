package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class GearSlotDao {
    protected ConnectionManager connectionManager;
    private static GearSlotDao instance = null;
    protected GearSlotDao(){
        connectionManager = new ConnectionManager();
    }
    public static GearSlotDao getInstance() {
        if(instance==null) {
            instance = new GearSlotDao();
        }
        return instance;
    }

    public GearSlot create(GearSlot gearSlot)throws SQLException{
        String insertGearSlot = "INSERT INTO GearSlot(SlotName) VALUES(?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertGearSlot);

            insertStmt.setString(1, gearSlot.name());

            insertStmt.executeUpdate();

            return gearSlot;

        } catch(SQLException e){
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
    public GearSlot getGearSlotByTypeName(String typeName) throws SQLException {
        String selectGearSlot = "SELECT SlotName FROM GearSlot WHERE SlotName = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectGearSlot);
            selectStmt.setString(1, typeName);

            results = selectStmt.executeQuery();
            if (results.next()) {
                String foundTypeName = results.getString("SlotName");
                return GearSlot.valueOf(foundTypeName);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("GearSlot with TypeName " + typeName + " not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (results != null) {
                results.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }
    public List<GearSlot> getAllGearSlots() {
        return Arrays.asList(GearSlot.values());
    }

}
