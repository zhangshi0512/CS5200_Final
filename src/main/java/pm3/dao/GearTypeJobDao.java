package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GearTypeJobDao {
    protected ConnectionManager connectionManager;
    private static GearTypeJobDao instance = null;
    protected GearTypeJobDao(){
        connectionManager = new ConnectionManager();
    }
    public static GearTypeJobDao getInstance() {
        if(instance==null) {
            instance = new GearTypeJobDao();
        }
        return instance;
    }

    public GearTypeJob create(GearTypeJob gearTypeJob) throws SQLException{
        String insertGearTypeJob = "INSERT INTO GearTypeJob(JobID, GearType) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertGearTypeJob);

            insertStmt.setInt(1,gearTypeJob.getJob().getJobID());
            insertStmt.setString(2,gearTypeJob.getGearType().toString());

            insertStmt.executeUpdate();

            return gearTypeJob;
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

    public List<GearType> getGearTypesByJobId(int jobId) throws SQLException {
        List<GearType> allowedGearTypes = new ArrayList<>();
        String selectQuery = "SELECT GearType FROM GearTypeJob WHERE JobID = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectQuery);
            selectStmt.setInt(1, jobId);

            results = selectStmt.executeQuery();
            while(results.next()) {
                String gearTypeName = results.getString("GearType");
                try {
                    GearType gearType = GearType.valueOf(gearTypeName.trim());
                    allowedGearTypes.add(gearType);
                } catch (IllegalArgumentException e) {
                    System.err.println("Unknown GearType: " + gearTypeName);
                }
            }
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
        return allowedGearTypes;
    }

    public boolean deleteGearTypesByJobId(int jobId) throws SQLException {
        String deleteQuery = "DELETE FROM GearTypeJob WHERE JobID = ?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, jobId);

            int affectedRows = deleteStmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (deleteStmt != null) {
                deleteStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public boolean addGearTypesForJobId(int jobId, List<GearType> gearTypes) throws SQLException {
        String insertQuery = "INSERT INTO GearTypeJob(JobID, GearType) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            connection.setAutoCommit(false);

            insertStmt = connection.prepareStatement(insertQuery);
            for (GearType gearType : gearTypes) {
                insertStmt.setInt(1, jobId);
                insertStmt.setString(2, gearType.name());
                insertStmt.addBatch();
            }

            int[] affectedRows = insertStmt.executeBatch();
            connection.commit(); // Commit transaction
            return affectedRows.length == gearTypes.size();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    e.addSuppressed(ex);
                }
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

}
