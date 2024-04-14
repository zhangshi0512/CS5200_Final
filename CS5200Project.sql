-- Drop the existing CS5200Project schema if it exists
DROP SCHEMA IF EXISTS CS5200Project;

-- Create the CS5200Project schema
CREATE SCHEMA CS5200Project;
USE CS5200Project;

-- Player Table
CREATE TABLE Player (
    PlayerID INT AUTO_INCREMENT PRIMARY KEY,
    `Name` CHAR(64) NOT NULL,
    EmailAddress VARCHAR(255) NOT NULL UNIQUE
);

-- Job Table
CREATE TABLE Job (
	JobID INT AUTO_INCREMENT PRIMARY KEY,
    `Name` CHAR(32) NOT NULL,
    LevelCap INT NOT NULL
);

-- Item Table
CREATE TABLE Item (
    ItemID INT AUTO_INCREMENT PRIMARY KEY,
    `Name` CHAR(64) NOT NULL,
    MaxStackSize INT NOT NULL,
    VendorPrice DECIMAL(10,2),
    ForSale BOOLEAN NOT NULL,
    ItemLevel INT NOT NULL
);

-- Currency Table
CREATE TABLE Currency (
    CurrencyID INT AUTO_INCREMENT PRIMARY KEY,
    `Name` CHAR(32) NOT NULL,
    TotalCap INT NOT NULL,
    WeeklyCap INT
);

-- Gear Type Enum
CREATE TABLE GearType (
	TypeName CHAR(32) PRIMARY KEY
);

-- Weapon Type Enum
CREATE TABLE WeaponType (
	TypeName CHAR(32) PRIMARY KEY
);

-- Gear Slot Enum
CREATE TABLE GearSlot (
	SlotName CHAR(32) PRIMARY KEY
);

-- Gear Table (Inherits from Item)
CREATE TABLE Gear (
	GearID INT,
    GearType CHAR(32),
    EquippableSlot CHAR(32) NOT NULL,
    RequiredLevel INT NOT NULL,
    DefenseRating INT NOT NULL,
    MagicDefenseRating INT NOT NULL,
    StrengthBonus INT,
    VitalityBonus INT,
    CriticalHitBonus INT,
    DeterminationBonus INT,
    DexterityBonus INT,
    MindBonus INT,
	TenacityBonus INT,
    SkillSpeedBonus INT,
    CriticalHitRateBonus INT,
    PRIMARY KEY (GearID),
    FOREIGN KEY (GearID) REFERENCES Item(ItemID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (EquippableSlot) REFERENCES GearSlot(SlotName) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (GearType) REFERENCES GearType(TypeName) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Weapon Table (Inherits from Item)
CREATE TABLE Weapon (
	WeaponID INT,
    WeaponType CHAR(32) NOT NULL,
	RequiredLevel INT NOT NULL,
    DamageDone INT NOT NULL,
    AutoAttack DECIMAL(10,2) NOT NULL,
    AttackDelay DECIMAL(10,2) NOT NULL,
    StrengthBonus INT,
    VitalityBonus INT,
    DeterminationBonus INT,
    DirectHitRateBonus INT,
    SkillSpeedBonus INT,
    TenacityBonus INT,
    CriticalHitBonus INT,
    PRIMARY KEY (WeaponID),
    FOREIGN KEY (WeaponID) REFERENCES Item(ItemID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (WeaponType) REFERENCES WeaponType(TypeName) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Consumable Table (Inherits from Item)
CREATE TABLE Consumable (
    ConsumableID INT,
    `Description` VARCHAR(512) NOT NULL,
    TenacityBonus DECIMAL(10,2),
    VitalityBonus DECIMAL(10,2),
    DeterminationBonus DECIMAL(10,2),
    TenacityCap INT,
    VitalityCap INT,
    DeterminationCap INT,
    PRIMARY KEY (ConsumableID),
    FOREIGN KEY (ConsumableID) REFERENCES Item(ItemID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Table to map gear types to jobs
CREATE TABLE GearTypeJob (
	JobID INT,
    GearType CHAR(32),
    PRIMARY KEY (JobID, GearType),
    FOREIGN KEY (JobID) REFERENCES Job(JobID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (GearType) REFERENCES GearType(TypeName) ON DELETE CASCADE ON UPDATE CASCADE
);

-- table to map weapon types to jobs
CREATE TABLE WeaponTypeJob (
	JobID INT,
    WeaponType CHAR(32),
    PRIMARY KEY (JobID, WeaponType),
    FOREIGN KEY (JobID) REFERENCES Job(JobID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (WeaponType) REFERENCES WeaponType(TypeName) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Character Table
CREATE TABLE `Character` (
    CharacterID INT AUTO_INCREMENT PRIMARY KEY,
    PlayerID INT,
    FirstName CHAR(32) NOT NULL,
    LastName CHAR(32) NOT NULL,
	HPMax INT NOT NULL,
    MPMAX INT NOT NULL,
    CurrentJobID INT NOT NULL,
    MainHandWeaponID INT NOT NULL,
    Strength INT DEFAULT 0 NOT NULL,
    Dexterity INT DEFAULT 0 NOT NULL,
    Vitality INT DEFAULT 0 NOT NULL,
    Intelligence INT DEFAULT 0 NOT NULL,
    Mind INT DEFAULT 0 NOT NULL,
    CriticalHit INT DEFAULT 0 NOT NULL,
    Determination INT DEFAULT 0 NOT NULL,
    DirectHitRate INT DEFAULT 0 NOT NULL,
    Defense INT DEFAULT 0 NOT NULL,
    MagicDefense INT DEFAULT 0 NOT NULL,
    AttackPower INT DEFAULT 0 NOT NULL,
    SkillSpeed INT DEFAULT 0 NOT NULL,
    AttackMagicPotency INT DEFAULT 0 NOT NULL,
    HealingMagicPotency INT DEFAULT 0 NOT NULL,
    SpellSpeed INT DEFAULT 0 NOT NULL,	
    AverageItemLevel INT DEFAULT 0 NOT NULL,
    Tenacity INT DEFAULT 0 NOT NULL,
    Piety INT DEFAULT 0 NOT NULL,
    UNIQUE (FirstName, LastName),
    FOREIGN KEY (PlayerID) REFERENCES Player(PlayerID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (MainHandWeaponID) REFERENCES Weapon(WeaponID) ON UPDATE CASCADE,
    FOREIGN KEY (CurrentJobID) REFERENCES Job(JobID) ON UPDATE CASCADE
);

-- Jobs characters can play
CREATE TABLE CharacterJob (
    CharacterID INT,
    JobID INT,
    Level INT NOT NULL,
    PRIMARY KEY (CharacterID, JobID),
    FOREIGN KEY (CharacterID) REFERENCES `Character`(CharacterID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (JobID) REFERENCES Job(JobID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Currencies characters owned
CREATE TABLE CharacterCurrency (
    CharacterID INT,
    CurrencyID INT,
    Amount INT NOT NULL,
    AmountEarnedWeek INT NOT NULL,
    PRIMARY KEY (CharacterID, CurrencyID),
    FOREIGN KEY (CharacterID) REFERENCES `Character`(CharacterID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CurrencyID) REFERENCES Currency(CurrencyID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Charater's inventory table
CREATE TABLE CharacterInventory (
    InventorySlotID INT AUTO_INCREMENT PRIMARY KEY,
    CharacterID INT,
    ItemID INT,
    StackSize INT,
    FOREIGN KEY (CharacterID) REFERENCES `Character`(CharacterID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Gear characters equipped
CREATE TABLE ChacterEquippedGear (
    SlotName CHAR(32),
    CharacterID INT,
    GearID INT,
    PRIMARY KEY (SlotName, CharacterID),
    FOREIGN KEY (SlotName) REFERENCES GearSlot(SlotName) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (CharacterID) REFERENCES `Character`(CharacterID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (GearID) REFERENCES Gear(GearID) ON DELETE SET NULL ON UPDATE CASCADE
);

INSERT INTO GearSlot VALUES 
('Head'),
('Body'),
('Hands'),
('Legs'),
('Feet'),
('Off-Hand'),
('Earring'),
('Wrist'),
('Ring');

-- Sample data for Player table
INSERT INTO Player (`Name`, EmailAddress) VALUES
('John Doe', 'john@example.com'),
('Jane Smith', 'jane@example.com'),
('Bob Builder', 'bob.builder@example.com'),
('Charlie Brown', 'charlie.brown@example.com'),
('Alice Wonderland', 'alice.wonderland@example.com'),
('Eve Adams', 'eve.adams@example.com'),
('Frank Poe', 'frank.poe@example.com'),
('Grace Lee', 'grace.lee@example.com'),
('Harry Potter', 'harry.potter@example.com'),
('Iris West', 'iris.west@example.com');


-- Sample data for Job table
INSERT INTO Job (JobID, `Name`, LevelCap) VALUES
(1, 'Paladin', 90),
(2, 'Warrior', 90),
(3, 'Dark Knight', 90),
(4, 'Gunbreaker', 90),
(5, 'White Mage', 100),
(6, 'Scholar', 100),
(7, 'Astrologian', 100),
(8, 'Sage', 100),
(9, 'Monk', 90),
(10, 'Dragon', 100),
(11, 'Ninja', 90),
(12, 'Samurai', 90),
(13, 'Reaper', 90),
(14, 'Bard', 100),
(15, 'Machinist', 100),
(16, 'Dancer', 90),
(17, 'Black Mage', 100),
(18, 'Summoner', 90),
(19, 'Red Mage', 100);

-- Sample data for Currency table
INSERT INTO Currency (`Name`, TotalCap, WeeklyCap) VALUES
('Gold', 100000, 500),
('Silver', 100000, 1000),
('Copper', 1000000, NULL),
('Platinum', 50000, 300),
('Emerald', 200000, 800),
('Sapphire', 150000, 700),
('Ruby', 100000, 600),
('Diamond', 75000, 400),
('Crystal', 500000, 900),
('Obsidian', 300000, 1000);


-- Sample data for GearType table
INSERT INTO GearType (TypeName) VALUES
('Light'),
('Medium'),
('Heavy');

-- Sample data for WeaponType table
INSERT INTO WeaponType (TypeName) VALUES
('Axe'),
('Bow'),
('Books'),
('Dagger'),
('Firearm'),
('Hammer'),
('Sword'),
('Staff'),
('Spear'),
('Rapier'),
('Scythe');


-- Insert sample data into Item, Gear, Weapon, and Consumable
INSERT INTO Item (ItemID, `Name`, MaxStackSize, VendorPrice, ForSale, ItemLevel) VALUES 
(1, 'Sword of Might', 1, 200.00, TRUE, 10),
(2, 'Staff of Wisdom', 1, 150.00, TRUE, 8),
(3, 'Healing Potion', 99, 5.00, TRUE, 0),
(4, 'Mana Elixir', 99, 10.00, TRUE, 0),
(5, 'Leather Eyepatch', 1, 250.00, TRUE, 12),
(6, 'Giant Popoto Pancakes', 99, 30, TRUE, 20),
(7, 'Iron Chestplate', 1, 30.00, TRUE, 15),
(8, 'Silk Pants', 1, 25.00, TRUE, 20),
(9, 'Leather Boots', 1, 15.00, TRUE, 25),
(10, 'Leather Gloves', 1, 10.00, TRUE, 30),
(11, 'Feather Earrings', 1, 5.00, TRUE, 35),
(12, 'Silver Bracers', 1, 10.00, TRUE, 45),
(13, 'Brass Dagger', 1, 15.00, TRUE, 20),
(14, 'Ranger`s Hat', 1, 30, TRUE, 40),
(15, 'Cloak of Shadows', 1, 45.00, TRUE, 12),
(16, 'Gloves of Haste', 1, 40.00, TRUE, 20),
(17, 'Boots of Speed', 1, 35.00, TRUE, 18),
(18, 'Axe of the Giants', 1, 55.00, TRUE, 25),
(19, 'Bow of the Swift', 1, 50.00, TRUE, 30),
(20, 'Firearm of Accuracy', 1, 60.00, TRUE, 22),
(21, 'Healing Salve', 99, 7.00, TRUE, 0),
(22, 'Energy Drink', 99, 12.00, TRUE, 0),
(23, 'Strength Potion', 99, 20.00, TRUE, 0),
(24, 'Magic Potion', 99, 25.00, TRUE, 0),
(25, 'Hammer of Justice', 1, 70.00, TRUE, 28),
(26, 'Spear of Destiny', 1, 65.00, TRUE, 27),
(27, 'Rapier of the Quick', 1, 60.00, TRUE, 23),
(28, 'Scythe of Reaper', 1, 75.00, TRUE, 32),
(29, 'Invisibility Potion', 99, 35.00, TRUE, 0),
(30, 'Fireproof Lotion', 99, 15.00, TRUE, 0),
(31, 'Water Breathing Potion', 99, 40.00, TRUE, 0);

-- Assuming ItemIDs 1-14 correspond to the items inserted above
-- Insert data into Gear
INSERT INTO Gear (GearID, GearType, EquippableSlot, RequiredLevel, DefenseRating, MagicDefenseRating, StrengthBonus, VitalityBonus, CriticalHitBonus, DeterminationBonus, DexterityBonus, MindBonus, TenacityBonus, SkillSpeedBonus, CriticalHitRateBonus) VALUES 
(5, 'Light', 'Head', 10, 20, 10, 5, 0, 2, 3, 0, 0, 5, 0, 0),
(7, 'Heavy', 'Body', 25, 15, 5, 0, 3, 0, 2, 0, 0, 0, 0, 0),
(8, 'Light', 'Legs', 20, 25, 15, 0, 5, 0, 4, 0, 0, 0, 0, 0),
(9, 'Medium', 'Feet', 15, 30, 20, 0, 10, 0, 5, 0, 0, 0, 0, 0),
(10, 'Medium', 'Hands', 30, 10, 5, 2, 2, 0, 3, 0, 0, 0, 0, 0),
(11, 'Light', 'Earring', 5, 5, 5, 1, 3, 0, 1, 0, 0, 0, 0, 0),
(12, 'Light', 'Wrist', 10, 20, 10, 0, 5, 1, 2, 0, 0, 0, 0, 0),
(14, 'Heavy', 'Head', 10, 10, 5, 1, 2, 0, 2, 0, 0, 0, 0, 0),
(15, 'Light', 'Body', 12, 10, 15, 2, 1, 0, 0, 3, 0, 0, 0, 1),
(16, 'Medium', 'Hands', 20, 5, 5, 3, 2, 1, 1, 0, 0, 0, 2, 0),
(17, 'Medium', 'Feet', 18, 7, 8, 2, 2, 0, 0, 2, 0, 1, 1, 0);


-- Insert data into Weapon
INSERT INTO Weapon (WeaponID, WeaponType, RequiredLevel, DamageDone, AutoAttack, AttackDelay, StrengthBonus, VitalityBonus, DeterminationBonus, DirectHitRateBonus, SkillSpeedBonus, TenacityBonus, CriticalHitBonus) VALUES 
(1, 'Sword', 10, 50, 25.00, 1.5, 5, 0, 3, 2, 0, 0, 2),
(2, 'Staff', 15, 40, 20.00, 2.0, 0, 5, 4, 1, 0, 0, 0),
(13, 'Dagger', 20, 60, 30.00, 3.0, 10, 0, 0, 5, 0, 0, 3),
(18, 'Axe', 25, 70, 35.00, 2.2, 7, 5, 3, 2, 0, 1, 3),
(19, 'Bow', 30, 65, 33.00, 1.8, 6, 0, 2, 3, 2, 0, 4),
(20, 'Firearm', 22, 60, 28.00, 1.6, 4, 3, 5, 4, 1, 0, 2),
(25, 'Hammer', 28, 80, 40.00, 2.4, 9, 6, 4, 1, 0, 2, 3),
(26, 'Spear', 27, 75, 36.00, 2.0, 8, 4, 2, 3, 3, 1, 4),
(27, 'Rapier', 23, 70, 30.00, 1.4, 5, 3, 3, 5, 4, 0, 5),
(28, 'Scythe', 32, 85, 45.00, 2.6, 10, 7, 5, 2, 1, 3, 6);

-- Insert data into Consumable
INSERT INTO Consumable (ConsumableID, `Description`) VALUES 
(3, 'Restores 100 HP'),
(4, 'Restores 50 Mana'),
(21, 'Instantly restores 200 HP'),
(22, 'Boosts energy, reducing fatigue for 30 minutes'),
(23, 'Increases strength by 10% for 5 minutes'),
(24, 'Restores 100 Mana instantly'),
(29, 'Grants invisibility for 30 seconds'),
(30, 'Provides immunity to fire damage for 10 minutes'),
(31, 'Allows underwater breathing for 30 minutes');

INSERT INTO Consumable (ConsumableID, `Description`, TenacityBonus, VitalityBonus, DeterminationBonus, TenacityCap, VitalityCap, DeterminationCap) VALUES
(6, 'Giant popotoes are mashed, mixed with eggs, then cooked to create these light and fluffy treats.', 0.08, 0.08, 0.08, 61, 66, 37);

-- Sample data for `Character` table
INSERT INTO `Character` (PlayerID, FirstName, LastName, HPMax, MPMax, CurrentJobID, MainHandWeaponID, Strength, Dexterity, Vitality, Intelligence, Mind, CriticalHit, Determination, DirectHitRate, Defense, MagicDefense, AttackPower, SkillSpeed, AttackMagicPotency, HealingMagicPotency, SpellSpeed, AverageItemLevel, Tenacity, Piety) VALUES
(1, 'Eragon', 'Rider', 2000, 1000, 1, 1, 100, 50, 150, 50, 75, 10, 20, 5, 100, 80, 200, 100, 150, 100, 120, 15, 50, 60),
(2, 'Gandalf', 'Greenleaf', 3000, 1000, 11, 13, 50, 100, 120, 150, 80, 5, 15, 10, 70, 90, 150, 120, 100, 200, 80, 10, 70, 80),
(3, 'Bob', 'Builder', 2000, 1000, 5, 2, 30, 25, 35, 15, 18, 10, 12, 15, 50, 30, 55, 50, 40, 50, 45, 60, 20, 30),
(4, 'Lara', 'Croft', 2500, 800, 7, 19, 80, 120, 90, 45, 50, 15, 25, 20, 90, 75, 170, 110, 60, 70, 95, 20, 55, 40),
(5, 'Arthur', 'Pendragon', 2700, 750, 6, 18, 110, 60, 140, 30, 45, 20, 30, 25, 120, 100, 220, 130, 50, 60, 85, 25, 65, 55),
(6, 'John', 'Snow', 2200, 900, 3, 25, 70, 80, 100, 40, 65, 12, 18, 8, 85, 65, 140, 100, 45, 80, 75, 18, 45, 50),
(7, 'Diana', 'Prince', 3100, 500, 4, 27, 90, 110, 130, 70, 95, 8, 22, 12, 130, 110, 190, 105, 85, 110, 100, 12, 60, 65),
(8, 'Bruce', 'Wayne', 2900, 600, 8, 28, 100, 70, 115, 55, 40, 11, 20, 9, 115, 95, 180, 115, 70, 55, 90, 14, 50, 45),
(9, 'Clark', 'Kent', 3000, 950, 9, 26, 85, 95, 125, 65, 90, 14, 28, 16, 105, 85, 160, 90, 100, 95, 110, 22, 70, 70),
(10, 'Leia', 'Organa', 2800, 1100, 2, 20, 60, 85, 90, 100, 120, 13, 19, 17, 95, 105, 150, 85, 130, 140, 115, 24, 75, 80);

-- Sample data for CharacterJob table
INSERT INTO CharacterJob (CharacterID, JobID, `Level`) VALUES
(1, 1, 40),
(1, 5, 25),
(2, 11, 30),
(2, 2, 35),
(3, 5, 10),
(4, 7, 30),
(5, 6, 45),
(6, 3, 50),
(7, 8, 35),
(8, 9, 60),
(9, 10, 55),
(10, 2, 40),
(9, 14, 40),
(10, 17, 30);

-- Sample data for CharacterCurrency table
INSERT INTO CharacterCurrency (CharacterID, CurrencyID, Amount, AmountEarnedWeek) VALUES
(1, 1, 50, 15),
(1, 2, 130, 60),
(2, 2, 220, 75),
(2, 3, 600, 100),
(3, 4, 120, 40),
(3, 5, 80, 30),
(4, 6, 200, 50),
(4, 7, 90, 25),
(5, 8, 150, 45),
(5, 9, 110, 35),
(6, 10, 170, 55),
(6, 1, 160, 65),
(7, 2, 180, 70),
(7, 3, 190, 85),
(8, 4, 75, 20),
(8, 5, 95, 33),
(9, 6, 130, 60),
(9, 7, 110, 40),
(10, 8, 140, 50),
(10, 9, 125, 45);

-- Sample data for CharacterInventory table
INSERT INTO CharacterInventory (CharacterID, ItemID, StackSize) VALUES
(1, 3, 10),
(1, 4, 50),
(2, 3, 20),
(2, 6, 5),
(3, 21, 30),
(3, 22, 15),
(4, 23, 25),
(4, 24, 10),
(5, 3, 40),
(5, 4, 25),
(6, 21, 5),
(6, 22, 20),
(7, 23, 10),
(7, 24, 30),
(8, 6, 15),
(8, 21, 12),
(9, 22, 18),
(9, 23, 8),
(10, 24, 14),
(10, 3, 35);

INSERT INTO ChacterEquippedGear (SlotName, CharacterID, GearID) VALUES 
('Head', 1, 14),
('Body', 1, 7),
('Feet', 1, 9),
('Hands', 1, 10),
('Head', 2, 5),
('Legs', 2, 8),
('Wrist', 2, 12),
('Earring', 2, 11),
('Body', 3, 7),
('Feet', 3, 9),
('Hands', 3, 10),
('Head', 4, 5),
('Legs', 4, 8),
('Wrist', 4, 12),
('Earring', 4, 11),
('Body', 5, 7),
('Feet', 5, 9),
('Hands', 5, 16),
('Head', 6, 14),
('Legs', 6, 17),
('Wrist', 6, 12),
('Earring', 6, 11),
('Body', 7, 7),
('Feet', 7, 9),
('Hands', 7, 10),
('Head', 8, 15),
('Legs', 8, 17),
('Wrist', 8, 12),
('Earring', 8, 11),
('Body', 9, 7),
('Feet', 9, 9),
('Hands', 9, 10),
('Head', 10, 14),
('Legs', 10, 8),
('Wrist', 10, 12),
('Earring', 10, 11);

INSERT INTO WeaponTypeJob (JobID, WeaponType) VALUES
(2, 'Axe'),
(11, 'Axe'),
(14,'Bow'), 
(11,'Dagger'),
(13, 'Scythe'),
(3, 'Sword'),
(6, 'Books'),
(18, 'Books'),
(19, 'Rapier'),
(15, 'Firearm'),
(1, 'Sword'),
(5, 'Staff');

INSERT INTO GearTypeJob (JobID, GearType) VALUES
(2, 'Heavy'),
(11, 'Light'),
(15, 'Medium'),
(1, 'Heavy'),
(1, 'Medium'),
(3, 'Heavy'),
(4, 'Medium'),
(5, 'Light'),
(6, 'Medium'),
(7, 'Light'),
(8, 'Medium'),
(9, 'Light'),
(10, 'Heavy'),
(12, 'Light'),
(13, 'Heavy'),
(14, 'Light'),
(16, 'Medium'),
(17, 'Light'),
(18, 'Heavy'),
(19, 'Medium');

/*
-- Fetch for visualization
-- Fetch all content from Player
SELECT * FROM Player;


-- Fetch all content from Job
SELECT * FROM Job;


-- Fetch all content from Item
SELECT * FROM Item;

-- Fetch all content from Gear
SELECT * FROM Gear;

-- Fetch all content from Weapon
SELECT * FROM Weapon;

-- Fetch all content from Consumable
SELECT * FROM Consumable;

-- Fetch all content from `Character`
SELECT * FROM `Character`;


-- Fetch all content from CharacterAttribute
SELECT * FROM CharacterAttribute;

-- Fetch all content from CharacterJob
SELECT * FROM CharacterJob;

-- Fetch all content from Currency
SELECT * FROM Currency;

-- Fetch all content from CharacterCurrency
SELECT * FROM CharacterCurrency;

-- Fetch all content from GearWeaponAttributeBonuses
SELECT * FROM GearWeaponAttributeBonuses;

-- Fetch all content from ConsumableBonuses
SELECT * FROM ConsumableBonuses;

-- Fetch all content from CharacterInventory
SELECT * FROM CharacterInventory;
*/
