package first;

import java.util.Objects;

final class BulkPricingFeature implements IProductFeature
    {
        private final double pricePerKg;
        private static final double HandlingRate = 0.05;

        public BulkPricingFeature(double pricePerKg)
        {
            if (Double.isNaN(pricePerKg) || Double.isInfinite(pricePerKg) || pricePerKg <= 0)
                throw new IllegalArgumentException("Цена за килограмм должна быть больше нуля");

            this.pricePerKg = pricePerKg;
        }

        public boolean requiresCooling()
        { 
            return false;
        }
        public double calculateExtraCost(Product product)
        {
            Objects.requireNonNull(product);
            return pricePerKg * product.getDimensions().getWeight() * HandlingRate;
        }
    }

 //////вынесла расчёт доплаты в отдельный класс который реализуе IProductFeature
//это позволяет сочетать разные свойства товара через композицию не созд нью класс для каждого сочетания характ