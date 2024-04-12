package pm3.dao;

import pm3.model.*;
import pm3.model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CharacterCurrencyDao {
    protected ConnectionManager connectionManager;
    private static CharacterCurrencyDao instance = null;
    protected CharacterCurrencyDao(){
        connectionManager = new ConnectionManager();
    }
    public static CharacterCurrencyDao getInstance() {
        if(instance==null) {
            instance = new CharacterCurrencyDao();
        }
        return instance;
    }

    public CharacterCurrency create(CharacterCurrency characterCurrency) throws SQLException{
        String insertCharacterCurrency = "INSERT INTO CharacterCurrency(CharacterID,CurrencyID,Amount,AmountEarnedWeek) VALUES(?,?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCharacterCurrency);

            insertStmt.setInt(1,characterCurrency.getCharacter().getCharacterID());
            insertStmt.setInt(2,characterCurrency.getCurrency().getCurrencyID());
            insertStmt.setInt(3,characterCurrency.getAmount());
            insertStmt.setInt(4,characterCurrency.getAmountEarnedWeek());

            insertStmt.executeUpdate();

            return characterCurrency;
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
    public CharacterCurrency getByCharacterIdAndCurrencyId(int characterId, int currencyId) throws SQLException {
        String selectCharacterCurrency = "SELECT CharacterID, CurrencyID, Amount, AmountEarnedWeek FROM CharacterCurrency WHERE CharacterID = ? AND CurrencyID = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCharacterCurrency);
            selectStmt.setInt(1, characterId);
            selectStmt.setInt(2, currencyId);

            results = selectStmt.executeQuery();
            if(results.next()) {
                int foundCharacterId = results.getInt("CharacterID");
                int foundCurrencyId = results.getInt("CurrencyID");
                int amount = results.getInt("Amount");
                int amountEarnedWeek = results.getInt("AmountEarnedWeek");

                // Assuming CharacterDao and CurrencyDao have methods to fetch these entities by their IDs
                Character character = CharacterDao.getInstance().getCharacterById(foundCharacterId);
                Currency currency = CurrencyDao.getInstance().getCurrencyById(foundCurrencyId);

                CharacterCurrency characterCurrency = new CharacterCurrency(character, currency, amount, amountEarnedWeek);
                return characterCurrency;
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

    public CharacterCurrency updateCharacterCurrencyAmount(int characterId, int currencyId, int newAmount, int newAmountEarnedWeek) throws SQLException {
        String updateCharacterCurrency = "UPDATE CharacterCurrency SET Amount = ?, AmountEarnedWeek = ? WHERE CharacterID = ? AND CurrencyID = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCharacterCurrency);

            updateStmt.setInt(1, newAmount);
            updateStmt.setInt(2, newAmountEarnedWeek);
            updateStmt.setInt(3, characterId);
            updateStmt.setInt(4, currencyId);

            updateStmt.executeUpdate();

            Character character = CharacterDao.getInstance().getCharacterById(characterId);
            Currency currency = CurrencyDao.getInstance().getCurrencyById(currencyId);

            return new CharacterCurrency(character, currency, newAmount, newAmountEarnedWeek);
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
