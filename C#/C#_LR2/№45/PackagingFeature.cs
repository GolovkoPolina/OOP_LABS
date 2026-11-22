
using System;

namespace laba1Nikitin
{
    internal sealed class PackagingFeature : IProductFeature
    {
        private readonly double packagingCost;

        public PackagingFeature(double packagingCost)
        {
            if (double.IsNaN(packagingCost) || double.IsInfinity(packagingCost) || packagingCost < 0)
                throw new ArgumentException("Стоимость упаковки не может быть отрицательной", nameof(packagingCost));

            this.packagingCost = packagingCost;
        }

        public bool RequiresCooling => false;

        public double CalculateExtraCost(Product product)
        {
            ArgumentNullException.ThrowIfNull(product);
            return packagingCost;
        }
    }
}
