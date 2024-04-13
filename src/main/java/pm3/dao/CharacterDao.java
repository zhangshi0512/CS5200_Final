package pm3.dao;

import pm3.model.*;
import pm3.model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class CharacterDao {
    protected ConnectionManager connectionManager;

    private static CharacterDao instance = null;
    protected CharacterDao(){
        connectionManager = new ConnectionManager();
    }
    public static CharacterDao getInstance() {
        if(instance==null) {
            instance = new CharacterDao();
        }
        return instance;
    }

    public Character create(Character character) throws SQLException{
        String insertCharacter =
                "INSERT INTO `Character` (FirstName, LastName, HPMax, MPMAX, CurrentJobID, MainHandWeaponID, Strength, Dexterity, Vitality, Intelligence, Mind, CriticalHit, Determination, DirectHitRate, Defense, MagicDefense, AttackPower, SkillSpeed, AttackMagicPotency, HealingMagicPotency, SpellSpeed, AverageItemLevel, Tenacity, Piety) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCharacter, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1, character.getFirstName());
            insertStmt.setString(2, character.getLastName());
            insertStmt.setInt(3, character.getMaxHP());
            insertStmt.setInt(4, character.getMaxMP());
            insertStmt.setInt(5, character.getCurrentJob().getJobID());
            insertStmt.setInt(6, character.getMainHandWeapon().getItemID());
            insertStmt.setInt(7, character.getStrength());
            insertStmt.setInt(8, character.getDexterity());
            insertStmt.setInt(9, character.getVitality());
            insertStmt.setInt(10, character.getIntelligence());
            insertStmt.setInt(11, character.getMind());
            insertStmt.setInt(12, character.getCriticalHit());
            insertStmt.setInt(13, character.getDetermination());
            insertStmt.setInt(14, character.getDirectHitRate());
            insertStmt.setInt(15, character.getDefense());
            insertStmt.setInt(16, character.getMagicDefense());
            insertStmt.setInt(17, character.getAttackPower());
            insertStmt.setInt(18, character.getSkillSpeed());
            insertStmt.setInt(19, character.getAttackMagicPotency());
            insertStmt.setInt(20, character.getHealingMagicPotency());
            insertStmt.setInt(21, character.getSpellSpeed());
            insertStmt.setInt(22, character.getAverageItemLevel());
            insertStmt.setInt(23, character.getTenacity());
            insertStmt.setInt(24, character.getPiety());

            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int characterID = -1;
            if(resultKey.next()) {
                characterID = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            character.setCharacterID(characterID);
            return character;

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

    public Character getCharacterById(int characterID) throws SQLException {
        String selectCharacter = "SELECT CharacterID, PlayerID, FirstName, LastName, HPMax, MPMAX, CurrentJobID, MainHandWeaponID, Strength, Dexterity, Vitality, Intelligence, Mind, CriticalHit, Determination, DirectHitRate, Defense, MagicDefense, AttackPower, SkillSpeed, AttackMagicPotency, HealingMagicPotency, SpellSpeed, AverageItemLevel, Tenacity, Piety FROM `Character` WHERE CharacterID = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCharacter);
            selectStmt.setInt(1, characterID);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultCharacterID = results.getInt("CharacterID");
                Player player = PlayerDao.getInstance().getPlayerById(results.getInt("PlayerID"));
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                int maxHP = results.getInt("HPMax");
                int maxMP = results.getInt("MPMAX");
                int currentJobId = results.getInt("CurrentJobID");
                Job currentJob = JobDao.getInstance().getJobById(currentJobId);
                int mainHandWeaponID = results.getInt("MainHandWeaponID");
                Weapon mainHandWeapon = WeaponDao.getInstance().getWeaponByID(mainHandWeaponID);
                int strength = results.getInt("Strength");
                int dexterity = results.getInt("Dexterity");
                int vitality = results.getInt("Vitality");
                int intelligence = results.getInt("Intelligence");
                int mind = results.getInt("Mind");
                int criticalHit = results.getInt("CriticalHit");
                int determination = results.getInt("Determination");
                int directHitRate = results.getInt("DirectHitRate");
                int defense = results.getInt("Defense");
                int magicDefense = results.getInt("MagicDefense");
                int attackPower = results.getInt("AttackPower");
                int skillSpeed = results.getInt("SkillSpeed");
                int attackMagicPotency = results.getInt("AttackMagicPotency");
                int healingMagicPotency = results.getInt("HealingMagicPotency");
                int spellSpeed = results.getInt("SpellSpeed");
                int averageItemLevel = results.getInt("AverageItemLevel");
                int tenacity = results.getInt("Tenacity");
                int piety = results.getInt("Piety");

                Character character = new Character(resultCharacterID, player, firstName, lastName, maxHP, maxMP, currentJob, mainHandWeapon, strength, dexterity, vitality, intelligence, mind, criticalHit, determination, directHitRate, defense, magicDefense, attackPower, skillSpeed, attackMagicPotency, healingMagicPotency, spellSpeed, averageItemLevel, tenacity, piety);
                return character;
            }
        }  catch (SQLException e) {
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

    public boolean deleteCharacter(int characterID) throws SQLException {
        String deleteCharacter = "DELETE FROM `Character` WHERE CharacterID = ?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCharacter);
            deleteStmt.setInt(1, characterID);

            int affectedRows = deleteStmt.executeUpdate();

            // Return true if a row was deleted.
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
    
    public List<Character> getAllCharacters() throws SQLException {
        List<Character> characters = new ArrayList<>();
        String selectAllCharacters = "SELECT `Character`.*, Job.Name AS JobName, Job.LevelCap FROM `Character` " +
                "INNER JOIN Job ON `Character`.CurrentJobID = Job.JobID " +
                "ORDER BY LastName ASC, FirstName ASC;";

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAllCharacters);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int characterID = results.getInt("CharacterID");
                Player player = PlayerDao.getInstance().getPlayerById(results.getInt("PlayerID"));
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                int maxHP = results.getInt("HPMax");
                int maxMP = results.getInt("MPMax");
                int strength = results.getInt("Strength");
                int dexterity = results.getInt("Dexterity");
                int vitality = results.getInt("Vitality");
                int intelligence = results.getInt("Intelligence");
                int mind = results.getInt("Mind");
                int criticalHit = results.getInt("CriticalHit");
                int determination = results.getInt("Determination");
                int directHitRate = results.getInt("DirectHitRate");
                int defense = results.getInt("Defense");
                int magicDefense = results.getInt("MagicDefense");
                int attackPower = results.getInt("AttackPower");
                int skillSpeed = results.getInt("SkillSpeed");
                int attackMagicPotency = results.getInt("AttackMagicPotency");
                int healingMagicPotency = results.getInt("HealingMagicPotency");
                int spellSpeed = results.getInt("SpellSpeed");
                int averageItemLevel = results.getInt("AverageItemLevel");
                int tenacity = results.getInt("Tenacity");
                int piety = results.getInt("Piety");
                Job currentJob = JobDao.getInstance().getJobById(results.getInt("CurrentJobID"));
                Weapon mainHandWeapon = WeaponDao.getInstance().getWeaponByID(results.getInt("MainHandWeaponID"));
                
                // Assume Job class has a constructor that takes name and levelCap.
                String jobName = results.getString("JobName");
                int levelCap = results.getInt("LevelCap");
                Job job = new Job(jobName, levelCap);

                // Create a new Character instance.
                Character character = new Character(characterID, player, firstName, lastName, maxHP, maxMP,
                        currentJob, mainHandWeapon, strength, dexterity, vitality, intelligence, mind, criticalHit,
                        determination, directHitRate, defense, magicDefense, attackPower, skillSpeed, attackMagicPotency,
                        healingMagicPotency, spellSpeed, averageItemLevel, tenacity, piety);
                // Add this character to the list.
                characters.add(character);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (results != null) results.close();
            if (selectStmt != null) selectStmt.close();
            if (connection != null) connection.close();
        }
        return characters;
    }

    
    public List<Character> getCharactersByFirstName(String firstName) throws SQLException {
        List<Character> characters = new ArrayList<>();
        String selectCharacters = "SELECT Character.CharacterID, PlayerID, FirstName, LastName, HPMax, MPMAX, " +
        	    "CurrentJobID, MainHandWeaponID, Strength, Dexterity, Vitality, Intelligence, " +
        	    "Mind, CriticalHit, Determination, DirectHitRate, Defense, MagicDefense, " +
        	    "AttackPower, SkillSpeed, AttackMagicPotency, HealingMagicPotency, " +
        	    "SpellSpeed, AverageItemLevel, Tenacity, Piety, Job.Name AS JobName, Job.LevelCap " +
        	    "FROM `Character` " +
        	    "INNER JOIN Job ON Character.CurrentJobID = Job.JobID " +
        	    "WHERE Character.FirstName LIKE ? " +
        	    "ORDER BY LastName;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCharacters);
            selectStmt.setString(1, "%" + firstName + "%");
            results = selectStmt.executeQuery();
            while (results.next()) {
                int characterID = results.getInt("CharacterID");
                // Additional field for the job name.
                String jobName = results.getString("JobName");
                // Assume getPlayerById and getJobById are implemented correctly.
                Player player = PlayerDao.getInstance().getPlayerById(results.getInt("PlayerID"));
                Job currentJob = new Job(results.getInt("CurrentJobID"), jobName, results.getInt("LevelCap"));
                Weapon mainHandWeapon = WeaponDao.getInstance().getWeaponByID(results.getInt("MainHandWeaponID"));
                
                // Instantiate a new Character object with the job object that includes the name.
                Character character = new Character(
                    characterID,
                    player,
                    results.getString("FirstName"),
                    results.getString("LastName"),
                    results.getInt("HPMax"),
                    results.getInt("MPMAX"),
                    currentJob,
                    mainHandWeapon,
                    results.getInt("Strength"),
                    results.getInt("Dexterity"),
                    results.getInt("Vitality"),
                    results.getInt("Intelligence"),
                    results.getInt("Mind"),
                    results.getInt("CriticalHit"),
                    results.getInt("Determination"),
                    results.getInt("DirectHitRate"),
                    results.getInt("Defense"),
                    results.getInt("MagicDefense"),
                    results.getInt("AttackPower"),
                    results.getInt("SkillSpeed"),
                    results.getInt("AttackMagicPotency"),
                    results.getInt("HealingMagicPotency"),
                    results.getInt("SpellSpeed"),
                    results.getInt("AverageItemLevel"),
                    results.getInt("Tenacity"),
                    results.getInt("Piety")
                );
                // Add the character with the job name to the list.
                characters.add(character);
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
        return characters;
    }

    
    public Character updateCharacter(Character character) throws SQLException {
        String updateQuery = "UPDATE `Character` SET FirstName = ?, LastName = ? WHERE CharacterID = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateQuery);

            updateStmt.setString(1, character.getFirstName());
            updateStmt.setString(2, character.getLastName());
            updateStmt.setInt(3, character.getCharacterID());

            updateStmt.executeUpdate();
            return character;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(updateStmt != null) updateStmt.close();
            if(connection != null) connection.close();
        }
    }

}
