package first;

import java.util.Objects;

final class ExpiryFeature implements IProductFeature
    {
        private final int expiryDays;

        public ExpiryFeature(int expiryDays)
        {
            if (expiryDays <= 0)
                throw new IllegalArgumentException("Срок годности должен быть больше нуля");

            this.expiryDays = expiryDays;
        }

        public boolean requiresCooling()
        { 
            return true;
        }    

        public double calculateExtraCost(Product product)
        {
            Objects.requireNonNull(product);
            return expiryDays < 3 ? 50 : 0; ///е сли да 50 нет 0
        }
    }

