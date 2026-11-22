package first;
    final class RefrigeratorRule implements IStorageRule
    {
        private static final double RefrigeratorTemperature = 4;
        @Override 
        public boolean canStore(Product product)
        {
            if (product == null)
                return false;

            if (product.getRequiredStorageTemperature() != null)
                return RefrigeratorTemperature <= product.getRequiredStorageTemperature();

            return product.requiresCooling();
        }
    }

