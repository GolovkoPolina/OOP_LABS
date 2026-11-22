
using System;

namespace laba1Nikitin
{
    internal sealed class BulkPricingFeature : IProductFeature
    {
        private readonly double pricePerKg;
        private const double HandlingRate = 0.05;

        public BulkPricingFeature(double pricePerKg)
        {
            if (double.IsNaN(pricePerKg) || double.IsInfinity(pricePerKg) || pricePerKg <= 0)
                throw new ArgumentException("Цена за килограмм должна быть больше нуля", nameof(pricePerKg));

            this.pricePerKg = pricePerKg;
        }

        public bool RequiresCooling => false;

        public double CalculateExtraCost(Product product)
        {
            ArgumentNullException.ThrowIfNull(product);
            return pricePerKg * product.Dimensions.Weight * HandlingRate;
        }
    }
}
 //////вынесла расчёт доплаты в отдельный класс который реализуе IProductFeature
//это позволяет сочетать разные свойства товара через композицию не созд нью класс для каждого сочетания характ