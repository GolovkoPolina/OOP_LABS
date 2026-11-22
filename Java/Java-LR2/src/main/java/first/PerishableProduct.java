package first;
   final class PerishableProduct extends Product
    {
        private final ExpiryFeature expiry;

        public PerishableProduct(
            String article, String name, double price,
            int quantity, int rest, double weight, double volume,
            boolean fragile, int expiryDays)
            
        {
            this(article, name, price, quantity, rest, weight, volume,
                fragile, new ExpiryFeature(expiryDays));
        }

        private PerishableProduct(
            String article, String name, double price,
            int quantity, int rest, double weight, double volume,
            boolean fragile, ExpiryFeature expiry)
            
        {
            super(article, name, price, quantity, rest, weight, volume, fragile);
            this.expiry = expiry;
        }
        @Override 
        public boolean requiresCooling() {
             return expiry.requiresCooling();
        }
        @Override 
        public double calculateCost()
        {
            return expiry.calculateExtraCost(this);
        }
    }

