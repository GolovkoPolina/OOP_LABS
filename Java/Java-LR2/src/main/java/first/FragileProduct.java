package first;
    final class FragileProduct extends Product
    {
        private final PackagingFeature packaging;

        public FragileProduct(
            String article, String name, double price,
            int quantity, int rest, double weight, double volume,
            boolean fragile, double packagingCost) 
            {
             this(article, name, price, quantity, rest, weight, volume,
                fragile, new PackagingFeature(packagingCost));
            }
        

        private FragileProduct(
            String article, String name, double price,
            int quantity, int rest, double weight, double volume,
            boolean fragile, PackagingFeature packaging)
            
        {
            super(article, name, price, quantity, rest, weight, volume, fragile);
            this.packaging = packaging;
        }
        @Override 
        public double calculateCost()
        {
            return packaging.calculateExtraCost(this);
        }
    }
