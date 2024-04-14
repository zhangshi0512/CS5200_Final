package pm3.tools;

import pm3.dao.*;
import pm3.model.*;
import pm3.model.Character;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {
	public static void main(String[] args) throws SQLException {
		PlayerDao playerDao = PlayerDao.getInstance();
		CharacterCurrencyDao characterCurrencyDao = CharacterCurrencyDao.getInstance();
		CharacterDao characterDao = CharacterDao.getInstance();
		CharacterEquippedGearDao characterEquippedGearDao = CharacterEquippedGearDao.getInstance();
		CharacterInventoryDao characterInventoryDao = CharacterInventoryDao.getInstance();
		CharacterJobDao characterJobDao = CharacterJobDao.getInstance();
		ConsumableDao consumableDao = ConsumableDao.getInstance();
		CurrencyDao currencyDao = CurrencyDao.getInstance();
		GearDao gearDao = GearDao.getInstance();
		GearSlotDao gearSlotDao = GearSlotDao.getInstance();
		GearTypeDao gearTypeDao = GearTypeDao.getInstance();
		GearTypeJobDao gearTypeJobDao = GearTypeJobDao.getInstance();
		ItemDao itemDao = ItemDao.getInstance();
		JobDao jobDao = JobDao.getInstance();
		WeaponDao weaponDao = WeaponDao.getInstance();
		WeaponTypeDao weaponTypeDao = WeaponTypeDao.getInstance();
		WeaponTypeJobDao weaponTypeJobDao = WeaponTypeJobDao.getInstance();

		// create
		Player player1 = null, player2 = null, player3 = null;

		if (!playerDao.emailExists("link@hyrule.com")) {
		    player1 = new Player("Link", "link@hyrule.com");
		    playerDao.create(player1);
		    System.out.println("Created Player: " + player1.getName() + " with email: " + player1.getEmailAddress());
		} else {
		    player1 = playerDao.getPlayerByEmail("link@hyrule.com");
		    System.out.println("Email already exists: link@hyrule.com");
		}

		if (!playerDao.emailExists("mario@1.com")) {
		    player2 = new Player("Mario", "mario@1.com");
		    playerDao.create(player2);
		    System.out.println("Created Player: " + player2.getName() + " with email: " + player2.getEmailAddress());
		} else {
		    player2 = playerDao.getPlayerByEmail("mario@1.com");
		    System.out.println("Email already exists: mario@1.com");
		}

		if (!playerDao.emailExists("lara@croftmanor.com")) {
		    player3 = new Player("Lara Lara", "lara@croftmanor.com");
		    playerDao.create(player3);
		    System.out.println("Created Player: " + player3.getName() + " with email: " + player3.getEmailAddress());
		} else {
		    player3 = playerDao.getPlayerByEmail("lara@croftmanor.com");
		    System.out.println("Email already exists: lara@croftmanor.com");
		}

		Job job1 = new Job("Warrior", 50);
		jobDao.create(job1);
		System.out.println("Created Job: " + job1.getName() + " with Level Cap: " + job1.getLevelCap());
		Job job2 = new Job("Mage", 50);
		jobDao.create(job2);
		System.out.println("Created Job: " + job2.getName() + " with Level Cap: " + job2.getLevelCap());

		Item item5 = new Item("Leather Eyepatch", 1, true, 12);
		item5.setVendorPrice(250.00);
		itemDao.create(item5);
		System.out.println("Created Item: "+item5.getName()+" "+item5.getItemLevel()+" "+item5.getVendorPrice());

		System.out.println("Inserted GearSlot:");
		for (GearSlot gearSlot : GearSlot.values()) {
			gearSlotDao.create(gearSlot);
			System.out.println(" - " + gearSlot.name());
		}

		System.out.println("Inserted GearType: ");
		for (GearType gearType : GearType.values()) {
			gearTypeDao.create(gearType);
			System.out.println(" - " + gearType.name());
		}

		System.out.println("Inserted weapon type: ");
		for (WeaponType type : WeaponType.values()) {
			WeaponType inserted = weaponTypeDao.create(type);
			System.out.println(" - " + inserted.name());
		}

		Weapon weapon1 = new Weapon("Sword of Might", 1, true, 10,
				"SWORD", 10, 50, 25.00,1.5);
		weapon1.setVendorPrice(200.00);
		weapon1.setStrengthBonus(5);
		weapon1.setVitalityBonus(0);
		weapon1.setDeterminationBonus(3);
		weapon1.setDirectHitRateBonus(2);
		weapon1.setSkillSpeedBonus(0);
		weapon1.setTenacityBonus(0);
		weapon1.setCriticalHitBonus(2);
		weaponDao.create(weapon1);
		System.out.println("Weapon1 okay");

		Gear gear1 = new Gear("Leather Eyepatch", 1, true, 12, "Light", "Head", 10, 20, 10);
		gear1.setVendorPrice(250.00);
		gear1.setStrengthBonus(5);
		gear1.setVitalityBonus(0);
		gear1.setCriticalHitBonus(2);
		gear1.setDeterminationBonus(3);
		gear1.setDexterityBonus(0);
		gear1.setMindBonus(0);
		gear1.setTenacityBonus(5);
		gear1.setSkillSpeedBonus(0);
		gear1.setCriticalHitRateBonus(0);
		gearDao.create(gear1);

		GearTypeJob gearTypeJob1 = new GearTypeJob(job1,GearType.Light);
		gearTypeJobDao.create(gearTypeJob1);
		System.out.println("Created gearTypeJob: "+gearTypeJob1.getJob().getName()+" "+gearTypeJob1.getGearType().name());

		List<GearType> gearTypesToAssociate = Arrays.asList(GearType.Medium, GearType.Heavy);
		boolean addGearTypeJob = gearTypeJobDao.addGearTypesForJobId(job1.getJobID(),gearTypesToAssociate);
		if(addGearTypeJob) {
			System.out.println("Gear types added for Job ID: " + job1.getJobID());
		} else {
			System.out.println("Failed to add gear types for Job ID: " + job1.getJobID());
		}

		WeaponTypeJob weaponTypeJob1 = new WeaponTypeJob(job1,WeaponType.Books);
		weaponTypeJobDao.create(weaponTypeJob1);
		System.out.println("Created weaponTypeJob: "+weaponTypeJob1.getJob().getName()+" "+weaponTypeJob1.getWeaponType().name());


		List<WeaponType> weaponTypesToAssociate = Arrays.asList(WeaponType.Axe, WeaponType.Bow,WeaponType.Dagger);
		boolean addWeaponTypeJob = weaponTypeJobDao.addWeaponTypesForJobId(job1.getJobID(),weaponTypesToAssociate);
		if(addWeaponTypeJob) {
			System.out.println("Weapon types added for Job ID: " + job1.getJobID());
		} else {
			System.out.println("Failed to add weapon types for Job ID: " + job1.getJobID());
		}


		Consumable consumable1 = new Consumable(3, "Healing Potion", 99,
				true, 0, "Restores 100 HP");
		consumable1.setVendorPrice(5.00);
		consumableDao.create(consumable1);

		Character character1 = new Character(player1, "Cloud", "Ava", 999, 999, job1, weapon1, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100);
		characterDao.create(character1);
		System.out.println("Created Character: " + character1.getFirstName() + " " + character1.getLastName());

		Currency currency1 = new Currency("Gold", 10000, 500);
		currencyDao.create(currency1);
		System.out.println("Created Currency: " + currency1.getName() + " " + currency1.getTotalCap()+" "+currency1.getWeeklyCap());

		CharacterCurrency characterCurrency1 = new CharacterCurrency(character1, currency1, 500, 50);
		characterCurrencyDao.create(characterCurrency1);
		System.out.println("Created CharacterCurrency: Character ID " + character1.getCharacterID() + ", Currency ID " + currency1.getCurrencyID() + ", Amount: " + characterCurrency1.getAmount());

		CharacterEquippedGear characterEquippedGear1 = new CharacterEquippedGear("Head", character1.getCharacterID(), gear1.getItemID());
		characterEquippedGearDao.create(characterEquippedGear1);
		System.out.println("Created CharacterEquippedGear"+characterEquippedGear1.getCharacterID()+" "+characterEquippedGear1.getSlotName());

		CharacterInventory newInventory = new CharacterInventory(character1);
		newInventory.setItem(item5);
		newInventory.setStackSize(99);
		newInventory = characterInventoryDao.create(newInventory);
		System.out.println("New inventory slot created with Inventory Slot ID: " + newInventory.getInventorySlotID()+" "+newInventory.getItem().getName());

		CharacterJob characterJob1 = new CharacterJob(character1,job1,25);
		characterJobDao.create(characterJob1);
		System.out.println("Created CharacterJob: "+characterJob1.getCharacter().getCharacterID()+" "+characterJob1.getJob().getName()+" "+characterJob1.getLevel());

		// read
		Player retrievedPlayer = playerDao.getPlayerById(player1.getPlayerID());
		System.out.println("Retrieved Player by ID: " + retrievedPlayer.getName() + " with ID: " + retrievedPlayer.getPlayerID());

		Job retrievedJob = jobDao.getJobById(job2.getJobID());
		if(retrievedJob != null) {
			System.out.println("Retrieved Job by ID: " + retrievedJob.getJobID() + " Name: " + retrievedJob.getName() + " Level Cap: " + retrievedJob.getLevelCap());
		} else {
			System.out.println("Job with ID: " + job2.getJobID() + " not found.");
		}

		Item retrieved_item = itemDao.getItemByID(item5.getItemID());
		System.out.println("Retrieved item: "+retrieved_item.getItemID()+" "+retrieved_item.getName());

		CharacterInventory retrieved_Inventory = characterInventoryDao.getByInventorySlotID(newInventory.getInventorySlotID());
		System.out.println("Retrieved inventory: Item ID = " + retrieved_Inventory.getItem().getName() +
				", Stack Size = " + retrieved_Inventory.getStackSize());

		String sampleSlotTypeName = GearSlot.Head.name();
		GearSlot retrievedGearSlot = gearSlotDao.getGearSlotByTypeName(sampleSlotTypeName);
		if (retrievedGearSlot != null) {
			System.out.println("Retrieved GearSlot by TypeName: " + retrievedGearSlot.name());
		} else {
			System.out.println("GearSlot with TypeName: " + sampleSlotTypeName + " not found.");
		}
		List<GearSlot> allGearSlots = gearSlotDao.getAllGearSlots();
		System.out.println("All GearSlots: ");
		for (GearSlot slot : allGearSlots) {
			System.out.println(" - " + slot.name());
		}

		String sampleGearTypeName = GearType.Light.name();
		GearType retrievedGearType = gearTypeDao.getGearTypeByTypeName(sampleGearTypeName);
		if (retrievedGearType != null) {
			System.out.println("Retrieved GearType by TypeName: " + retrievedGearType.name());
		} else {
			System.out.println("GearType with TypeName: " + sampleGearTypeName + " not found.");
		}

		List<GearType> allGearTypes = gearTypeDao.getAllGearTypes();
		System.out.println("All GearTypes:");
		allGearTypes.forEach(gt -> System.out.println(" - " + gt.name()));

		String typeNameToFetch = "SWORD";
		WeaponType fetchedType = weaponTypeDao.getWeaponTypeByName(typeNameToFetch);
		if (fetchedType != null) {
			System.out.println("Successfully fetched weapon type: " + fetchedType.name());
		} else {
			System.out.println("Weapon type not found: " + typeNameToFetch);
		}
		List<WeaponType> allWeaponTypes = weaponTypeDao.getAllWeaponTypes();
		System.out.println("All weapon types:");
		allWeaponTypes.forEach(type -> System.out.println(type.name()));

		Weapon retrieved_weapon = weaponDao.getWeaponByID(weapon1.getItemID());
		System.out.format("Reading weapon: n:%s t:%s \n", retrieved_weapon.getName(),
				retrieved_weapon.getWeaponType());

		Gear retrieved_gear = gearDao.getGearByID(gear1.getItemID());
		System.out.format("Reading gear: n:%s t:%s s:%s\n", retrieved_gear.getName(),
				retrieved_gear.getGearType(), retrieved_gear.getEquippableSlot());

		Consumable retrieved_consumable = consumableDao.getConsumableByID(consumable1.getItemID());
		System.out.format("Reading consumable: n:%s d:%s \n", retrieved_consumable.getName(),
				retrieved_consumable.getDescription());

		Character retrievedCharacter = characterDao.getCharacterById(character1.getCharacterID());
		if(retrievedCharacter != null) {
			System.out.println("Retrieved Character by ID: " + retrievedCharacter.getFirstName() + " " + retrievedCharacter.getLastName());
		} else {
			System.out.println("Character with ID: " + character1.getCharacterID() + " not found.");
		}

		Currency retrieved_curreny = currencyDao.getCurrencyById(currency1.getCurrencyID());
		System.out.println("Retrieved Currency by ID: " + retrieved_curreny.getName() + " " + retrieved_curreny.getTotalCap()+" "+retrieved_curreny.getWeeklyCap());

		CharacterCurrency retrievedCharacterCurrency = characterCurrencyDao.getByCharacterIdAndCurrencyId(character1.getCharacterID(), currency1.getCurrencyID());
		if(retrievedCharacterCurrency != null) {
			System.out.println("Retrieved CharacterCurrency: " + "Character ID: " + retrievedCharacterCurrency.getCharacter().getCharacterID() + ", Currency ID: " + retrievedCharacterCurrency.getCurrency().getCurrencyID() + ", Amount: " + retrievedCharacterCurrency.getAmount() + ", Amount Earned This Week: " + retrievedCharacterCurrency.getAmountEarnedWeek());
		} else {
			System.out.println("CharacterCurrency not found for Character ID: " + character1.getCharacterID() + " and Currency ID: " + currency1.getCurrencyID());
		}

		CharacterEquippedGear retrieved_characterEquippedGear =
				characterEquippedGearDao.getCharacterEquippedGearBySlotNameAndID("Head", characterEquippedGear1.getCharacterID());
		System.out.format("Reading Character Equipped Gear: s:%s c_id:%d g_id:%d\n",
				retrieved_characterEquippedGear.getSlotName(), retrieved_characterEquippedGear.getCharacterID(),
				retrieved_characterEquippedGear.getGearID());

		CharacterJob retrievedCharacterJob = characterJobDao.getByCharacterIdAndJobId(character1.getCharacterID(),job1.getJobID());
		System.out.println("Retrieved CharacterJob: "+retrievedCharacterJob.getCharacter().getCharacterID()+" "+retrievedCharacterJob.getJob().getName()+" "+retrievedCharacterJob.getLevel());

		List<GearType> fetchedGearTypes = gearTypeJobDao.getGearTypesByJobId(job1.getJobID());
		System.out.println("Fetched gear types for job ID " + job1.getJobID() + ":");
		for (GearType gt : fetchedGearTypes) {
			System.out.println(gt.name());
		}

		List<WeaponType> fetchedWeaponTypes = weaponTypeJobDao.getWeaponTypesByJobId(job1.getJobID());
		System.out.println("Fetched weapon types for job ID " + job1.getJobID() + ":");
		for (WeaponType wt : fetchedWeaponTypes) {
			System.out.println(wt.name());
		}

		// update
		job1.setName("Paladin");
		job1.setLevelCap(60);
		jobDao.updateJob(job1.getJobID(), job1.getName(), job1.getLevelCap());
		System.out.println("Updated Job ID: " + job1.getJobID() + " to Name: " + job1.getName() + " and Level Cap: " + job1.getLevelCap());

		currencyDao.updateCurrency(currency1.getCurrencyID(), "Bronze",99999,999);
		currency1 = currencyDao.getCurrencyById(currency1.getCurrencyID());
		System.out.println("Updated Currency: " + currency1.getName() + " " + currency1.getTotalCap()+" "+currency1.getWeeklyCap());


		characterCurrencyDao.updateCharacterCurrencyAmount(character1.getCharacterID(), currency1.getCurrencyID(), 600, 60);
		characterCurrency1 = characterCurrencyDao.getByCharacterIdAndCurrencyId(character1.getCharacterID(),currency1.getCurrencyID());
		System.out.println("Updated CharacterCurrency: New Amount: "+characterCurrency1.getAmount()+", New Amount Earned Week: "+characterCurrency1.getAmountEarnedWeek());

		boolean updateCharacterInventory = characterInventoryDao.updateInventoryItemAndSize(retrieved_Inventory.getInventorySlotID(), item5.getItemID(), 150);
		if(updateCharacterInventory){
			CharacterInventory updated = characterInventoryDao.getByInventorySlotID(retrieved_Inventory.getInventorySlotID());
			System.out.println("Update successfully, new itemID: "+updated.getItem().getItemID()+" new stackSize: "+updated.getStackSize());
		}else{
			System.out.println("Update failed");
		}

		CharacterJob updatedCharacterJob = characterJobDao.updateCharacterJobLevel(characterJob1.getCharacter().getCharacterID(),characterJob1.getJob().getJobID(),99);
		System.out.println("Updated CharacterJob: "+updatedCharacterJob.getCharacter().getCharacterID()+" "+updatedCharacterJob.getJob().getName()+" "+updatedCharacterJob.getLevel());

		// delete

		boolean deleteSuccessPlayer = playerDao.delete(player3.getPlayerID());
		if(deleteSuccessPlayer) {
			System.out.println("Deleted Player: " + player3.getName());
		} else {
			System.out.println("Could not delete Player: " + player3.getName());
		}

		Player checkDeletionPlayer = playerDao.getPlayerById(player3.getPlayerID());
		if(checkDeletionPlayer == null) {
			System.out.println("Player " + player3.getName() + " successfully deleted.");
		} else {
			System.out.println("Player " + player3.getName() + " was not deleted properly.");
		}

		Item deleted_tmp = itemDao.delete(item5);
		if(deleted_tmp == null) {
			System.out.format("Deleting item: n:%s \n", item5.getName());
		}

		boolean deleteSuccessCharacter = characterDao.deleteCharacter(character1.getCharacterID());
		if(deleteSuccessCharacter) {
			System.out.println("Deleted Character: " + character1.getFirstName() + " " + character1.getLastName());
		} else {
			System.out.println("Failed to delete Character: " + character1.getFirstName() + " " + character1.getLastName());
		}

		Character checkDeletionCharacter = characterDao.getCharacterById(character1.getCharacterID());
		if(checkDeletionCharacter == null) {
			System.out.println("Character " + character1.getFirstName() + " " + character1.getLastName() + " successfully deleted.");
		} else {
			System.out.println("Character " + character1.getFirstName() + " " + character1.getLastName() + " was not deleted properly.");
		}

		boolean deleted = gearTypeJobDao.deleteGearTypesByJobId(job1.getJobID());
		if(deleted) {
			System.out.println("Gear types deleted for Job ID: " + job1.getJobID());
		} else {
			System.out.println("Failed to delete gear types for Job ID: " + job1.getJobID());
		}

	}
}
