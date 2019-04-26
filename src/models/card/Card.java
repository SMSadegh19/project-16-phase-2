package models.card;

public abstract class Card {
    private int manaCost;
    private int sellPrice;
    private int buyPrice;
    private String name;
    private String cardID;

    public int getPrice() {
        return buyPrice;
    }

    protected Card(String name, int manaCost, int buyPrice, int sellPrice) {
        this.manaCost = manaCost;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.name = name;
    }

    public static abstract class CardBuilder {
        private int manaCost;
        private int sellPrice;
        private int buyPrice;
        private String name;


        public CardBuilder setManaCost(int manaCost) {
            this.manaCost = manaCost;
            return this;
        }

        public CardBuilder setSellPrice(int sellPrice) {
            this.sellPrice = sellPrice;
            return this;
        }

        public CardBuilder setBuyPrice(int buyPrice) {
            this.buyPrice = buyPrice;
            return this;
        }

        public CardBuilder setName(String name) {
            this.name = name;
            return this;
        }

        int getManaCost() {
            return manaCost;
        }

        int getSellPrice() {
            return sellPrice;
        }

        int getBuyPrice() {
            return buyPrice;
        }

        String getName() {
            return name;
        }

        public abstract Card create();
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public String getName() {
        return this.name;
    }

    public String getCardID() {
        return cardID;
    }
}
