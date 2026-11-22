package first;
    final class BulkProduct extends Product ////запрещает дальнейшее наследование класса или переопределение метода
    {
        private final BulkPricingFeature bulkPricing; ///вместо того чтобы писать формулу прямо внутри BulkProduct, передачка расчёта отдельному классу

        public BulkProduct(
            String article, String name, double price,
            int quantity, int rest, double weight, double volume,
            boolean fragile, double pricePerKg)
            {
                
            this(article, name, price, quantity, rest, weight, volume,
                fragile, new BulkPricingFeature(pricePerKg));
            }
        

        private BulkProduct(
            String article, String name, double price,
            int quantity, int rest, double weight, double volume,
            boolean fragile, BulkPricingFeature bulkPricing)
        {
            super(article, name, price, quantity, rest, weight, volume, fragile);
            this.bulkPricing = bulkPricing;
        }
         @Override 
        public double calculateCost()
        {
            return bulkPricing.calculateExtraCost(this);
        }
    }

