
namespace laba1Nikitin
{
    internal sealed class BulkProduct : Product ////запрещает дальнейшее наследование класса или переопределение метода
    {
        private readonly BulkPricingFeature bulkPricing; ///вместо того чтобы писать формулу прямо внутри BulkProduct, передачка расчёта отдельному классу

        public BulkProduct(
            string article, string name, double price,
            int quantity, int rest, double weight, double volume,
            bool fragile, double pricePerKg)
            : this(article, name, price, quantity, rest, weight, volume,
                fragile, new BulkPricingFeature(pricePerKg))
        {
        }

        private BulkProduct(
            string article, string name, double price,
            int quantity, int rest, double weight, double volume,
            bool fragile, BulkPricingFeature bulkPricing)
            : base(article, name, price, quantity, rest, weight, volume, fragile)
        {
            this.bulkPricing = bulkPricing;
        }

        public override double CalculateCost()
        {
            return bulkPricing.CalculateExtraCost(this);
        }
    }
}
