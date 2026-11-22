package first;

    final class ShelfRule implements IStorageRule
    {
        @Override 
        public boolean canStore(Product product)
        {
            return product != null
                && !product.requiresCooling()
                && !product.getDimensions().isFragile()
                && product.getRequiredStorageTemperature() == null;
        }
    }


