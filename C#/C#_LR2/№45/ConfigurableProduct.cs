
using System;
using System.Collections.Generic;

namespace laba1Nikitin
{
    internal sealed class ConfigurableProduct : Product
    {
        private readonly IReadOnlyList<IProductFeature> features; ////интерфейс списка с доступом только для чтения через него(не дает доб и ремув)

        public ConfigurableProduct(
            string article, string name, double price,
            int quantity, int rest, double weight, double volume,
            bool fragile, params IProductFeature[] features)           //////params позволяет передавать разное количество аргументов указанного типа
            : this(article, name, price, quantity, rest, weight, volume,
                fragile, ValidateFeatures(features))   
        {   ////метод проверяет массив характеристик и подготавливает защищённую коллекцию
        }

        private ConfigurableProduct(
            string article, string name, double price,
            int quantity, int rest, double weight, double volume,
            bool fragile, IReadOnlyList<IProductFeature> validatedFeatures)
            : base(article, name, price, quantity, rest, weight, volume, fragile)
        {
            this.features = validatedFeatures;
        }

        private static IReadOnlyList<IProductFeature> ValidateFeatures(IProductFeature[] features)
        {
            ArgumentNullException.ThrowIfNull(features);

            IProductFeature[] copy = (IProductFeature[])features.Clone();

            foreach (IProductFeature feature in copy)
            {
                if (feature == null)
                    throw new ArgumentException("Характеристика товара не может быть null", nameof(features));
            }

            return Array.AsReadOnly(copy);
        }

        public override bool RequiresCooling
        {
            get
            {
                foreach (IProductFeature feature in features)
                {
                    if (feature.RequiresCooling)
                        return true;
                }

                return false;
            }
        }

        public override double CalculateCost()
        {
            double total = 0;

            foreach (IProductFeature feature in features)
            {
                total += feature.CalculateExtraCost(this); ///общ сумма
            }

            return total;
        }
    }
}
