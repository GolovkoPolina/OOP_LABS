
using System;

namespace laba1Nikitin
{
    internal abstract class Product
    {
        private readonly string article;
        private readonly string name;
        private double price;
        private int quantity;
        private readonly int rest;
        private Dimensions dimensions;
        private static int createdProducts;

        public const double VAT = 0.13;

        protected Product(
            string article,
            string name,
            double price,
            int quantity,
            int rest,
            double weight,
            double volume,
            bool fragile)
        {
            if (string.IsNullOrWhiteSpace(article))
                throw new ArgumentException("Артикул не может быть пустым", nameof(article));

            if (string.IsNullOrWhiteSpace(name))
                throw new ArgumentException("Название не может быть пустым", nameof(name));

            if (double.IsNaN(price) || double.IsInfinity(price) || price <= 0)
                throw new ArgumentException("Цена должна быть больше нуля", nameof(price));

            if (quantity < 0)
                throw new ArgumentException("Количество не может быть отрицательным", nameof(quantity));

            if (rest < 0)
                throw new ArgumentException("Минимальный остаток не может быть отрицательным", nameof(rest));

            Dimensions newDimensions = new Dimensions(weight, volume, fragile);

            this.article = article;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.rest = rest;
            dimensions = newDimensions;
            createdProducts++;
        }

        protected Product(
            string article,
            string name,
            double price,
            double weight,
            double volume,
            bool fragile)
            : this(article, name, price, 0, 0, weight, volume, fragile)
        {
        }

        public string Article => article; //сокр гет
        public string Name => name;
        public double Price => price;
        public int Quantity => quantity;
        public int Rest => rest;
        public Dimensions Dimensions => new Dimensions(
            dimensions.Weight, dimensions.Volume, dimensions.Fragile);

        public virtual bool RequiresCooling => false; ///требуется ли охл но по умолч нет
        public virtual double? RequiredStorageTemperature => null; ////double? может хранить число или null

        public static int GetCreatedProducts()
        {
            return createdProducts;
        }

        public abstract double CalculateCost();

        public double CalculateStorageCost()
        {
            return dimensions.Volume * 100 + CalculateCost();
        }

        public double priceWithTax()
        {
            return priceWithTax(VAT);
        }

        public double priceWithTax(double rate)
        {
            if (double.IsNaN(rate) || double.IsInfinity(rate) || rate < 0)
                throw new ArgumentException("Ставка налога не может быть отрицательной", nameof(rate));

            return price * (1 + rate);
        }

        public void ChangePrice(double newPrice)
        {
            if (double.IsNaN(newPrice) || double.IsInfinity(newPrice) || newPrice <= 0)
                throw new ArgumentException("Цена должна быть больше нуля", nameof(newPrice));

            price = newPrice;
        }

        public void Dose(int amount)
        {
            if (amount <= 0)
                throw new ArgumentException("Количество должно быть больше нуля", nameof(amount));

            checked
            {
                quantity += amount;
            }
        }

        public void Ship(int amount)
        {
            if (amount <= 0)
                throw new ArgumentException("Количество для отгрузки должно быть больше нуля", nameof(amount));

            if (amount > quantity)
                throw new InvalidOperationException("Нельзя отгрузить больше, чем есть на складе");

            quantity -= amount;
        }

        public void ChangeDimensions(double weight, double volume, bool fragile)
        {
            dimensions = new Dimensions(weight, volume, fragile);
        }

        public sealed override string ToString()
        {
            return $"Артикул: {article}, Название: {name}, " +
                   $"Цена: {price:F2}, Количество: {quantity}, " +
                   $"Минимальный остаток: {rest}, " +
                   $"Вес: {dimensions.Weight}, Объём: {dimensions.Volume}, " +
                   $"Хрупкий: {dimensions.Fragile}";
        }
    }
}
