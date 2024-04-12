package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class CurrencyDao {
    protected ConnectionManager connectionManager;
    private static CurrencyDao instance = null;
    protected CurrencyDao(){
        connectionManager = new ConnectionManager();
    }
    public static CurrencyDao getInstance() {
        if(instance==null) {
            instance = new CurrencyDao();
        }
        return instance;
    }

    public Currency create(Currency currency)throws SQLException{
        String insertCurrency = "INSERT INTO Currency(`Name`,TotalCap,WeeklyCap) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCurrency, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1,currency.getName());
            insertStmt.setInt(2,currency.getTotalCap());
            insertStmt.setInt(3,currency.getWeeklyCap());

            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int jobID = -1;
            if(resultKey.next()) {
                jobID = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            currency.setCurrencyID(jobID);
            return currency;

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
    public Currency getCurrencyById(int currencyID) throws SQLException {
        String selectCurrency = "SELECT CurrencyID, Name, TotalCap, WeeklyCap FROM Currency WHERE CurrencyID = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCurrency);
            selectStmt.setInt(1, currencyID);

            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultCurrencyID = results.getInt("CurrencyID");
                String name = results.getString("Name");
                int totalCap = results.getInt("TotalCap");
                int weeklyCap = results.getInt("WeeklyCap");

                Currency currency = new Currency(resultCurrencyID, name, totalCap, weeklyCap);
                return currency;
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
    public boolean updateCurrency(int currencyID, String newName, int newTotalCap, int newWeeklyCap) throws SQLException {
        String updateCurrency = "UPDATE Currency SET Name = ?, TotalCap = ?, WeeklyCap = ? WHERE CurrencyID = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCurrency);
            updateStmt.setString(1, newName);
            updateStmt.setInt(2, newTotalCap);
            updateStmt.setInt(3, newWeeklyCap);
            updateStmt.setInt(4, currencyID);

            int affectedRows = updateStmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (updateStmt != null) {
                updateStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
