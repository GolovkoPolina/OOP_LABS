package laba1;

/** Товар: инварианты защищены конструкторами и методами. */
public class Product {
    private final String article;
    private final String name;
    private double price;
    private int quantity;
    private final int rest;
    private Dimensions dimensions;

    private static int createdProducts = 0;
    public static final double VAT = 0.13;

    public Product(String article, String name, double price, int quantity, int rest, Dimensions dimensions) {
        if (article == null || article.isBlank()) {
            throw new IllegalArgumentException("Артикул не может быть пустым");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        if (!Double.isFinite(price) || price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Количество не может быть отрицательным");
        }
        if (rest < 0) {
            throw new IllegalArgumentException("Минимальный остаток не может быть отрицательным");
        }
        if (dimensions == null) {
            throw new IllegalArgumentException("Характеристики товара не могут отсутствовать");
        }
        this.article = article;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.rest = rest;
        this.dimensions = dimensions;
        createdProducts++;
    }

    public Product(String article, String name, double price, Dimensions dimensions) {
        this(article, name, price, 0, 0, dimensions);
    }

    public String getArticle() { return article; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getRest() { return rest; }
    public Dimensions getDimensions() { return dimensions; }
    public static int getCreatedProducts() { return createdProducts; }

    public double priceWithTax() { return priceWithTax(VAT); }

    public double priceWithTax(double rate) {
        if (!Double.isFinite(rate) || rate < 0) {
            throw new IllegalArgumentException("Ставка налога не может быть отрицательной");
        }
        return price * (1 + rate);
    }

    public void changePrice(double newPrice) {
        if (!Double.isFinite(newPrice) || newPrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }
        price = newPrice;
    }

    public void dose(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше нуля");
        }
        quantity = Math.addExact(quantity, amount);
    }

    public void ship(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Количество для отгрузки должно быть больше нуля");
        }
        if (amount > quantity) {
            throw new IllegalStateException("Нельзя отгрузить больше, чем есть на складе");
        }
        quantity -= amount;
    }

    public void changeDimensions(Dimensions newDimensions) {
        if (newDimensions == null) {
            throw new IllegalArgumentException("Характеристики товара не могут отсутствовать");
        }
        dimensions = newDimensions;
    }

    @Override
    public String toString() {
        return "Артикул: " + article + ", Название: " + name + ", Цена: " + price
                + ", Количество: " + quantity + ", Минимальный остаток: " + rest
                + ", Вес: " + dimensions.getWeight() + ", Объём: " + dimensions.getVolume()
                + ", Хрупкий: " + dimensions.isFragile();
    }
}
