package pm3.dao;

import pm3.model.Character;
import pm3.model.CharacterInventory;
import pm3.model.Item;

import java.sql.*;

public class CharacterInventoryDao {
    protected ConnectionManager connectionManager;
    private static CharacterInventoryDao instance = null;

    protected CharacterInventoryDao() {
        connectionManager = new ConnectionManager();
    }

    public static CharacterInventoryDao getInstance() {
        if (instance == null) {
            instance = new CharacterInventoryDao();
        }
        return instance;
    }

    // Initially create the inventory with CharacterID only
    public CharacterInventory create(CharacterInventory characterInventory) throws SQLException {
        String insertCharacterInventory = "INSERT INTO CharacterInventory(CharacterID,ItemID,stackSize) VALUES(?,?,?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertCharacterInventory, Statement.RETURN_GENERATED_KEYS)) {

            insertStmt.setInt(1, characterInventory.getCharacter().getCharacterID());
            if(characterInventory.getItem()==null){
                insertStmt.setNull(2,Types.INTEGER);
            }else{
                insertStmt.setInt(2,characterInventory.getItem().getItemID());
            }
            if(characterInventory.getStackSize()==null){
                insertStmt.setNull(3,Types.INTEGER);
            }else{
                insertStmt.setInt(3,characterInventory.getStackSize());
            }

            insertStmt.executeUpdate();

            try (ResultSet resultKey = insertStmt.getGeneratedKeys()) {
                if (resultKey.next()) {
                    int inventorySlotID = resultKey.getInt(1);
                    characterInventory.setInventorySlotID(inventorySlotID);
                } else {
                    throw new SQLException("Unable to retrieve auto-generated key.");
                }
            }
            return characterInventory;
        }
    }

    // Update an inventory slot to add an Item and StackSize
    public boolean updateInventoryItemAndSize(int inventorySlotID, int itemID, int stackSize) throws SQLException {
        String updateInventory = "UPDATE CharacterInventory SET ItemID = ?, StackSize = ? WHERE InventorySlotID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement updateStmt = connection.prepareStatement(updateInventory)) {

            updateStmt.setInt(1, itemID);
            updateStmt.setInt(2, stackSize);
            updateStmt.setInt(3, inventorySlotID);

            int affectedRows = updateStmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // Fetch an inventory slot by its ID
    public CharacterInventory getByInventorySlotID(int inventorySlotID) throws SQLException {
        String selectCharacterInventory = "SELECT InventorySlotID, CharacterID, ItemID, StackSize FROM CharacterInventory WHERE InventorySlotID = ?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCharacterInventory);
            selectStmt.setInt(1, inventorySlotID);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int characterID = results.getInt("CharacterID");
                Integer itemID = (Integer) results.getObject("ItemID"); // ItemID can be null
                Integer stackSize = results.getInt("StackSize");
                Character character = CharacterDao.getInstance().getCharacterById(characterID);
                Item item = itemID != null ? ItemDao.getInstance().getItemByID(itemID) : null;
                CharacterInventory characterInventory = new CharacterInventory(character);
                characterInventory.setInventorySlotID(inventorySlotID);
                characterInventory.setItem(item);
                characterInventory.setStackSize(stackSize);

                return characterInventory;
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
        return null;
    }
}
