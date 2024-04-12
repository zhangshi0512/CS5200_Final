package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class GearTypeDao {
    protected ConnectionManager connectionManager;
    private static GearTypeDao instance = null;
    protected GearTypeDao(){
        connectionManager = new ConnectionManager();
    }
    public static GearTypeDao getInstance() {
        if(instance==null) {
            instance = new GearTypeDao();
        }
        return instance;
    }

    public GearType create(GearType gearType)throws SQLException{
        String insertGearType = "INSERT INTO GearType(TypeName) VALUES(?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertGearType);

            insertStmt.setString(1, gearType.name());

            insertStmt.executeUpdate();

            return gearType;

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
    public GearType getGearTypeByTypeName(String typeName) throws SQLException {
        try {
            return GearType.valueOf(typeName);
        } catch (IllegalArgumentException e) {
            System.out.println("GearType with TypeName " + typeName + " not found.");
            return null;
        }
    }
    public List<GearType> getAllGearTypes() {
        return Arrays.asList(GearType.values());
    }
}
