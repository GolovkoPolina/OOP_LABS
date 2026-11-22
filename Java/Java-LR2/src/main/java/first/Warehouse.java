package first;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

final class Warehouse
    {
        private final Product[] products;
        private int count;

        public Warehouse(int capacity)
        {
            if (capacity <= 0)
                throw new IllegalArgumentException("Вместимость должна быть больше нуля");

            products = new Product[capacity];
        }

        public void doseProduct(Product product, IStorageRule storageRule) //з
        {
            Objects.requireNonNull(product);
            Objects.requireNonNull(storageRule);

            if (findByArticle(product.getArticle()) != null)
                throw new IllegalStateException("Товар с таким артикулом уже есть на складе");

            if (count >= products.length)
                throw new IllegalStateException("Склад переполнен");

            if (!storageRule.canStore(product))                                           /////доб
                throw new IllegalStateException("Товар не соответствует условиям хранения");

            products[count] = product;
            count++;
        }

        public Product findByArticle(String article)
        {
            if (article == null || article.isBlank())
                throw new IllegalArgumentException("Артикул не может быть пустым");

            for (int i = 0; i < count; i++)
            {
                if (products[i].getArticle().equals(article))
                    return products[i];
            }

            return null;
        }

        public boolean shipByArticle(String article, int amount)
        {
            if (amount <= 0)
                throw new IllegalArgumentException("Количество для отгрузки должно быть больше нуля");

            Product product = findByArticle(article);

            if (product == null)
                return false;

            product.ship(amount);
            return true;
        }

        public List<Product> getLowRestProducts()
        {
            List<Product> result = new ArrayList<>();

            for (int i = 0; i < count; i++)
            {
                if (products[i].getQuantity() < products[i].getRest())
                    result.add(products[i]);
            }

            return result;
        }

        public double getMnogoValue()
        {
            double total = 0;

            for (int i = 0; i < count; i++)
            {
                total += products[i].getPrice() * products[i].getQuantity();
            }

            return total;
        }

        public Product[] getProducts()
        {
            Product[] copy = new Product[count];
            System.arraycopy(products, 0, copy, 0, count);
            return copy;
        }
    }

