

namespace laba1Nikitin
{
    internal sealed class ShelfRule : IStorageRule
    {
        public bool CanStore(Product product)
        {
            return product != null
                && !product.RequiresCooling
                && !product.Dimensions.Fragile
                && !product.RequiredStorageTemperature.HasValue;
        }
    }
}


///тут выбраала просто раз он не хрупкий и не нужен холодос то пусть на полке