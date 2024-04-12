package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JobDao {
    protected ConnectionManager connectionManager;
    private static JobDao instance = null;
    protected JobDao(){
        connectionManager = new ConnectionManager();
    }
    public static JobDao getInstance() {
        if(instance==null) {
            instance = new JobDao();
        }
        return instance;
    }

    public Job create(Job job)throws SQLException{
        String insertJob = "INSERT INTO Job(`Name`,LevelCap) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertJob, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1,job.getName());
            insertStmt.setInt(2,job.getLevelCap());

            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int jobID = -1;
            if(resultKey.next()) {
                jobID = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            job.setJobID(jobID);
            return job;

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

    public Job getJobById(int jobID) throws SQLException {
        String selectJob = "SELECT JobID, Name, LevelCap FROM Job WHERE JobID = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectJob);
            selectStmt.setInt(1, jobID);

            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultJobID = results.getInt("JobID");
                String name = results.getString("Name");
                int levelCap = results.getInt("LevelCap");

                Job job = new Job(resultJobID, name, levelCap);
                return job;
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
    public Job updateJob(int jobID, String newName, int newLevelCap) throws SQLException {
        String updateJob = "UPDATE Job SET Name = ?, LevelCap = ? WHERE JobID = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateJob);
            updateStmt.setString(1, newName);
            updateStmt.setInt(2, newLevelCap);
            updateStmt.setInt(3, jobID);

            updateStmt.executeUpdate();

            return new Job(jobID, newName, newLevelCap);
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
