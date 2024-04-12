package pm3.model;

public class Currency {
    protected int currencyID;
    protected String name;
    protected int totalCap;
    protected int weeklyCap;

    public Currency(int currencyID){
        this.currencyID = currencyID;
    }

    public Currency(int currencyID,String name,int totalCap, int weeklyCap){
        this.currencyID = currencyID;
        this.name = name;
        this.totalCap = totalCap;
        this.weeklyCap = weeklyCap;
    }

    public Currency(String name,int totalCap, int weeklyCap){
        this.name = name;
        this.totalCap = totalCap;
        this.weeklyCap = weeklyCap;
    }

    public Currency(String name,int totalCap){
        this.name = name;
        this.totalCap = totalCap;
    }

    public int getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(int currencyID) {
        this.currencyID = currencyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalCap() {
        return totalCap;
    }

    public void setTotalCap(int totalCap) {
        this.totalCap = totalCap;
    }

    public int getWeeklyCap() {
        return weeklyCap;
    }

    public void setWeeklyCap(int weeklyCap) {
        this.weeklyCap = weeklyCap;
    }
}
