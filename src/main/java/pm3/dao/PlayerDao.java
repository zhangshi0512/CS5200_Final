package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class PlayerDao {
    protected ConnectionManager connectionManager;
    private static PlayerDao instance = null;
    protected PlayerDao(){
        connectionManager = new ConnectionManager();
    }
    public static PlayerDao getInstance() {
        if(instance==null) {
            instance = new PlayerDao();
        }
        return instance;
    }

    public Player create(Player player) throws SQLException{

        String insertPlayer = "INSERT INTO Player(`Name`,EmailAddress) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPlayer, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1,player.getName());
            insertStmt.setString(2,player.getEmailAddress());

            insertStmt.executeUpdate();


            resultKey = insertStmt.getGeneratedKeys();
            int playerID = -1;
            if(resultKey.next()) {
                playerID = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            player.setPlayerID(playerID);
            return player;

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

    public Player getPlayerById(int playerID) throws SQLException {
        String selectPlayer = "SELECT PlayerID, Name, EmailAddress FROM Player WHERE PlayerID = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPlayer);
            selectStmt.setInt(1, playerID);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultPlayerID = results.getInt("PlayerID");
                String name = results.getString("Name");
                String emailAddress = results.getString("EmailAddress");
                Player player = new Player(resultPlayerID, name, emailAddress);
                return player;
            }
        } catch (SQLException e) {
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

    public Player updatePlayerEmail(int playerID, String newEmailAddress) throws SQLException {
        String updatePlayer = "UPDATE Player SET EmailAddress = ? WHERE PlayerID = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updatePlayer);
            updateStmt.setString(1, newEmailAddress);
            updateStmt.setInt(2, playerID);
            updateStmt.executeUpdate();

            return getPlayerById(playerID);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(updateStmt != null) {
                updateStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
    }

    public boolean delete(int playerID) throws SQLException {
        String deletePlayer = "DELETE FROM Player WHERE PlayerID = ?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePlayer);
            deleteStmt.setInt(1, playerID);

            int affectedRows = deleteStmt.executeUpdate();

            // Return true if the deletion was successful (i.e., one row was affected).
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(deleteStmt != null) {
                deleteStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
    }


}	
	
