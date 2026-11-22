package first;

import java.util.Objects;


final class PackagingFeature implements IProductFeature
    {
        private final double packagingCost;

        public PackagingFeature(double packagingCost)
        {
            if (Double.isNaN(packagingCost) || Double.isInfinite(packagingCost) || packagingCost < 0)
                throw new IllegalArgumentException("Стоимость упаковки не может быть отрицательной");

            this.packagingCost = packagingCost;
        }

        public boolean requiresCooling(){
            return false;
        }
        @Override 
        public double calculateExtraCost(Product product)
        {
            Objects.requireNonNull(product);
            return packagingCost;
        }
    }
