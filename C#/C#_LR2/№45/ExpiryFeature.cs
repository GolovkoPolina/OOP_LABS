
using System;

namespace laba1Nikitin
{
    internal sealed class ExpiryFeature : IProductFeature
    {
        private readonly int expiryDays;

        public ExpiryFeature(int expiryDays)
        {
            if (expiryDays <= 0)
                throw new ArgumentException("Срок годности должен быть больше нуля", nameof(expiryDays));

            this.expiryDays = expiryDays;
        }

        public bool RequiresCooling => true;

        public double CalculateExtraCost(Product product)
        {
            ArgumentNullException.ThrowIfNull(product);
            return expiryDays < 3 ? 50 : 0; ///е сли да 50 нет 0
        }
    }
}
