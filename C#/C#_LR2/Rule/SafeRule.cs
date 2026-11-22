

namespace laba1Nikitin
{
    internal sealed class SafeRule : IStorageRule
    {
        public bool CanStore(Product product)
        {
            return product != null
                && product.Dimensions.Fragile
                && !product.RequiresCooling
                && !product.RequiredStorageTemperature.HasValue;
        }
    }
}


////сначала логичнее было использовать сейф (охрана) для дорогих предметов, тк допустим от 30к
/// предмет считается особо ценным 
/// но допустим проверка если больше 30к то сейф а если привалят 150кг икры она стоит 5млн => нужен сейф но она же там сдохнет 
/// поэтому чтобы без усложнений и гемора 
/// РЕАЛИЗАЦИЯ типо "предостеречь особо хрупкие товары от повреждений"