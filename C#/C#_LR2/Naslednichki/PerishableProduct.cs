
namespace laba1Nikitin
{
    internal sealed class PerishableProduct : Product
    {
        private readonly ExpiryFeature expiry;

        public PerishableProduct(
            string article, string name, double price,
            int quantity, int rest, double weight, double volume,
            bool fragile, int expiryDays)
            : this(article, name, price, quantity, rest, weight, volume,
                fragile, new ExpiryFeature(expiryDays))
        {
        }

        private PerishableProduct(
            string article, string name, double price,
            int quantity, int rest, double weight, double volume,
            bool fragile, ExpiryFeature expiry)
            : base(article, name, price, quantity, rest, weight, volume, fragile)
        {
            this.expiry = expiry;
        }

        public override bool RequiresCooling => expiry.RequiresCooling;

        public override double CalculateCost()
        {
            return expiry.CalculateExtraCost(this);
        }
    }
}
