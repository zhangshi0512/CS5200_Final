package pm3.model;

public class CharacterCurrency {
    protected Character character;
    protected Currency currency;
    protected int amount;
    protected int amountEarnedWeek;

    public CharacterCurrency(Character character, Currency currency) {
        this.character = character;
        this.currency = currency;
    }

    public CharacterCurrency(int amount, int amountEarnedWeek) {
        this.amount = amount;
        this.amountEarnedWeek = amountEarnedWeek;
    }

    public CharacterCurrency(Character character, Currency currency, int amount, int amountEarnedWeek) {
        this.character = character;
        this.currency = currency;
        this.amount = amount;
        this.amountEarnedWeek = amountEarnedWeek;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountEarnedWeek() {
        return amountEarnedWeek;
    }

    public void setAmountEarnedWeek(int amountEarnedWeek) {
        this.amountEarnedWeek = amountEarnedWeek;
    }
}
