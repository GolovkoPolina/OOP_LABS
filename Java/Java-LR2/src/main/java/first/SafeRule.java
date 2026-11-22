package first;
   final class SafeRule implements IStorageRule
    {
        @Override 
        public boolean canStore(Product product)
        {
            return product != null
                && product.getDimensions().isFragile()
                && !product.requiresCooling()
                && product.getRequiredStorageTemperature() == null;
        }
    }
