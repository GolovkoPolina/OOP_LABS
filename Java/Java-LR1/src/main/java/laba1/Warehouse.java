package laba1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Склад хранит товары в закрытом массиве. */
public class Warehouse {
    private final Product[] products;
    private int count;

    public Warehouse(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть больше нуля");
        }
        products = new Product[capacity];
        count = 0;
    }

    public void doseProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Нельзя добавить пустой товар");
        }
        if (count >= products.length) {
            throw new IllegalStateException("Склад переполнен");
        }
        if (findByArticle(product.getArticle()) != null) {
            throw new IllegalStateException("Товар с таким артикулом уже есть на складе");
        }
        products[count++] = product;
    }

    /** Возвращает null, если товар не найден. */
    public Product findByArticle(String article) {
        for (int i = 0; i < count; i++) {
            if (products[i].getArticle().equals(article)) {
                return products[i];
            }
        }
        return null;
    }

    public boolean shipByArticle(String article, int amount) {
        Product product = findByArticle(article);
        if (product == null) {
            return false;
        }
        product.ship(amount);
        return true;
    }

    public List<Product> getLowRestProducts() {
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (products[i].getQuantity() < products[i].getRest()) {
                result.add(products[i]);
            }
        }
        return result;
    }

    public double getMnogoValue() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getPrice() * products[i].getQuantity();
        }
        return total;
    }

    /** Не отдаём ссылку на внутренний массив. */
    public Product[] getProducts() {
        return Arrays.copyOf(products, count);
    }
}
