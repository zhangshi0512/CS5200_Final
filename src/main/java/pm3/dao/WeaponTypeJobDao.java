package pm3.dao;

import pm3.model.Job;
import pm3.model.WeaponType;
import pm3.model.WeaponTypeJob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeaponTypeJobDao {
    protected ConnectionManager connectionManager;
    private static WeaponTypeJobDao instance = null;
    protected WeaponTypeJobDao(){
        connectionManager = new ConnectionManager();
    }
    public static WeaponTypeJobDao getInstance() {
        if(instance==null) {
            instance = new WeaponTypeJobDao();
        }
        return instance;
    }

    public WeaponTypeJob create(WeaponTypeJob weaponTypeJob) throws SQLException{
        String insertWeaponTypeJob = "INSERT INTO WeaponTypeJob(JobID, WeaponType) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertWeaponTypeJob);

            insertStmt.setInt(1,weaponTypeJob.getJob().getJobID());
            insertStmt.setString(2,weaponTypeJob.getWeaponType().toString());

            insertStmt.executeUpdate();

            return weaponTypeJob;
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

    public List<WeaponType> getWeaponTypesByJobId(int jobId) throws SQLException {
        List<WeaponType> weaponTypes = new ArrayList<>();
        String selectQuery = "SELECT WeaponType FROM WeaponTypeJob WHERE JobID = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectQuery);
            selectStmt.setInt(1, jobId);

            results = selectStmt.executeQuery();
            while (results.next()) {
                String weaponTypeName = results.getString("WeaponType");
                try {
                    WeaponType weaponType = WeaponType.valueOf(weaponTypeName);
                    weaponTypes.add(weaponType);
                } catch (IllegalArgumentException e) {
                    System.err.println("Unknown WeaponType: " + weaponTypeName);
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
        return weaponTypes;
    }

    public List<Job> getJobByWeaponType(String weaponType) throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String selectQuery = "SELECT JobID FROM WeaponTypeJob WHERE WeaponType = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        JobDao jobDao = JobDao.getInstance();
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectQuery);
            selectStmt.setString(1, weaponType);

            results = selectStmt.executeQuery();
            while (results.next()) {
                int jobID = results.getInt("JobID");
                try {
                    Job job = jobDao.getJobById(jobID);
                    jobs.add(job);
                } catch (IllegalArgumentException e) {
                    System.err.println(e);
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
        return jobs;
    }

    public boolean addWeaponTypesForJobId(int jobId, List<WeaponType> newWeaponTypes) throws SQLException {
        String insertQuery = "INSERT INTO WeaponTypeJob(JobID, WeaponType) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertQuery);
            for (WeaponType weaponType : newWeaponTypes) {
                insertStmt.setInt(1, jobId);
                insertStmt.setString(2, weaponType.name());
                insertStmt.addBatch();
            }

            int[] affectedRows = insertStmt.executeBatch();
            return affectedRows.length == newWeaponTypes.size();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


}
