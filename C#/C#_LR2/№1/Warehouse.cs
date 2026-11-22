
using System;
using System.Collections.Generic;

namespace laba1Nikitin
{
    internal sealed class Warehouse
    {
        private readonly Product[] products;
        private int count;

        public Warehouse(int capacity)
        {
            if (capacity <= 0)
                throw new ArgumentException("Вместимость должна быть больше нуля", nameof(capacity));

            products = new Product[capacity];
        }

        public void DoseProduct(Product product, IStorageRule storageRule)
        {
            ArgumentNullException.ThrowIfNull(product);
            ArgumentNullException.ThrowIfNull(storageRule);

            if (FindByArticle(product.Article) != null)
                throw new InvalidOperationException("Товар с таким артикулом уже есть на складе");

            if (count >= products.Length)
                throw new InvalidOperationException("Склад переполнен");

            if (!storageRule.CanStore(product))                                           /////доб
                throw new InvalidOperationException("Товар не соответствует условиям хранения");

            products[count] = product;
            count++;
        }

        public Product? FindByArticle(string article)
        {
            if (string.IsNullOrWhiteSpace(article))
                throw new ArgumentException("Артикул не может быть пустым", nameof(article));

            for (int i = 0; i < count; i++)
            {
                if (products[i].Article == article)
                    return products[i];
            }

            return null;
        }

        public bool ShipByArticle(string article, int amount)
        {
            if (amount <= 0)
                throw new ArgumentException("Количество для отгрузки должно быть больше нуля", nameof(amount));

            Product? product = FindByArticle(article);

            if (product == null)
                return false;

            product.Ship(amount);
            return true;
        }

        public List<Product> GetLowRestProducts()
        {
            List<Product> result = new List<Product>();

            for (int i = 0; i < count; i++)
            {
                if (products[i].Quantity < products[i].Rest)
                    result.Add(products[i]);
            }

            return result;
        }

        public double GetMnogoValue()
        {
            double total = 0;

            for (int i = 0; i < count; i++)
            {
                total += products[i].Price * products[i].Quantity;
            }

            return total;
        }

        public Product[] GetProducts()
        {
            Product[] copy = new Product[count];
            Array.Copy(products, copy, count);
            return copy;
        }
    }
}
