package pm3.dao;

import pm3.model.*;
import pm3.model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CharacterJobDao {
    protected ConnectionManager connectionManager;
    private static CharacterJobDao instance = null;
    protected CharacterJobDao(){
        connectionManager = new ConnectionManager();
    }
    public static CharacterJobDao getInstance() {
        if(instance==null) {
            instance = new CharacterJobDao();
        }
        return instance;
    }

    public CharacterJob create(CharacterJob characterJob) throws SQLException{
        String insertCharacterJob = "INSERT INTO CharacterJob(CharacterID,JobID,Level) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCharacterJob);

            insertStmt.setInt(1,characterJob.getCharacter().getCharacterID());
            insertStmt.setInt(2,characterJob.getJob().getJobID());
            insertStmt.setInt(3,characterJob.getLevel());

            insertStmt.executeUpdate();

            return characterJob;
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
    public CharacterJob getByCharacterIdAndJobId(int characterId, int jobId) throws SQLException {
        String selectCharacterJob = "SELECT CharacterID, JobID, Level FROM CharacterJob WHERE CharacterID = ? AND JobID = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCharacterJob);
            selectStmt.setInt(1, characterId);
            selectStmt.setInt(2, jobId);

            results = selectStmt.executeQuery();
            if(results.next()) {
                int level = results.getInt("Level");
                Character character = CharacterDao.getInstance().getCharacterById(characterId);
                Job job = JobDao.getInstance().getJobById(jobId);

                CharacterJob characterJob = new CharacterJob(character, job, level);
                return characterJob;
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
    public CharacterJob updateCharacterJobLevel(int characterId, int jobId, int newLevel) throws SQLException {
        String updateCharacterJob = "UPDATE CharacterJob SET Level = ? WHERE CharacterID = ? AND JobID = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCharacterJob);
            updateStmt.setInt(1, newLevel);
            updateStmt.setInt(2, characterId);
            updateStmt.setInt(3, jobId);

            updateStmt.executeUpdate();
            return getByCharacterIdAndJobId(characterId, jobId);
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


}
