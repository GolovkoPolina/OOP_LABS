
namespace laba1Nikitin
{
    internal sealed class FragileProduct : Product
    {
        private readonly PackagingFeature packaging;

        public FragileProduct(
            string article, string name, double price,
            int quantity, int rest, double weight, double volume,
            bool fragile, double packagingCost)
            : this(article, name, price, quantity, rest, weight, volume,
                fragile, new PackagingFeature(packagingCost))
        {
        }

        private FragileProduct(
            string article, string name, double price,
            int quantity, int rest, double weight, double volume,
            bool fragile, PackagingFeature packaging)
            : base(article, name, price, quantity, rest, weight, volume, fragile)
        {
            this.packaging = packaging;
        }

        public override double CalculateCost()
        {
            return packaging.CalculateExtraCost(this);
        }
    }
}
