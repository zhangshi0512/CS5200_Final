package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeaponTypeDao {
    protected ConnectionManager connectionManager;
    private static WeaponTypeDao instance = null;
    protected WeaponTypeDao(){
        connectionManager = new ConnectionManager();
    }
    public static WeaponTypeDao getInstance() {
        if(instance==null) {
            instance = new WeaponTypeDao();
        }
        return instance;
    }

    public WeaponType create(WeaponType weaponType)throws SQLException{
        String insertWeaponType = "INSERT INTO WeaponType(TypeName) VALUES(?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertWeaponType);

            insertStmt.setString(1, weaponType.name());

            insertStmt.executeUpdate();

            return weaponType;

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
    public WeaponType getWeaponTypeByName(String typeName) throws SQLException {
        String selectWeaponType = "SELECT TypeName FROM WeaponType WHERE TypeName = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectWeaponType);
            selectStmt.setString(1, typeName);

            results = selectStmt.executeQuery();
            if(results.next()) {
                String foundTypeName = results.getString("TypeName");
                return WeaponType.valueOf(foundTypeName);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("No WeaponType found with TypeName: " + typeName);
            return null;
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(results != null) {
                results.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public List<WeaponType> getAllWeaponTypes() {
        return new ArrayList<>(Arrays.asList(WeaponType.values()));
    }


}
