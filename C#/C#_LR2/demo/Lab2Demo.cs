
using System;

namespace laba1Nikitin
{
    internal static class Lab2Demo
    {
        public static void Run()
        {
            Console.WriteLine();
            Console.WriteLine("///////////////////////////////////////////////////////////22222222222222222222");

            Product fragile = new FragileProduct(
                "L201", "Стеклянная ваза", 2500, 4, 1,
                1.2, 0.02, true, 300);

            Product perishable = new PerishableProduct(
                "L202", "Йогурт", 180, 12, 3,
                0.2, 0.001, false, 2);

            Product bulk = new BulkProduct(
                "L203", "Весовой чай", 1000, 8, 2,
                2, 0.005, false, 1000);

            Product[] products = { fragile, perishable, bulk };


            foreach (Product product in products)
            {
                Console.WriteLine($"{product.Name}: " +
                    $"доплата {product.CalculateCost():F2} руб., " +
                    $"стоимость хранения {product.CalculateStorageCost():F2} руб.");
            }


            IStorageRule[] rules =
            {
                new ShelfRule(),
                new RefrigeratorRule(),
                new SafeRule(),
                new FreezerRule()
            };

            string[] ruleNames =
            {
                "Полка", "Холодильник", "Сейф", "Морозильная камера"
            };

            foreach (Product product in products)
            {
                Console.WriteLine($"\n{product.Name}:");

                for (int i = 0; i < rules.Length; i++)
                {
                    Console.WriteLine($"{ruleNames[i]}: " +
                        (rules[i].CanStore(product) ? "Можно" : "Нельзя"));
                }
            }

            Warehouse warehouse = new Warehouse(5);
            warehouse.DoseProduct(fragile, rules[2]);
            warehouse.DoseProduct(perishable, rules[1]);
            warehouse.DoseProduct(bulk, rules[0]);

            Console.WriteLine($"\nРазмещено товаров на складе: {warehouse.GetProducts().Length}");


            ConfigurableProduct twoFeatures = new ConfigurableProduct(
                "C001", "Хрупкий скоропортящийся товар", 5000, 10, 2,
                1.5, 0.02, true,
                new PackagingFeature(300),
                new ExpiryFeature(2));

            ConfigurableProduct threeFeatures = new ConfigurableProduct(
                "C002", "Хрупкий скоропортящийся весовой товар", 10000, 5, 2,
                2, 0.03, true,
                new PackagingFeature(1500),
                new ExpiryFeature(2),
                new BulkPricingFeature(8000));

            PrintProduct(twoFeatures);
            PrintProduct(threeFeatures);

            Product[] combinedProducts = { twoFeatures, threeFeatures };

            foreach (Product product in combinedProducts)
            {
                Console.WriteLine($"{product.Name}: охлаждение = {product.RequiresCooling}");
            }


            Console.WriteLine("\nПроверка изменения веса весового товара:");
            double before = bulk.CalculateCost();
            bulk.ChangeDimensions(3, 0.005, false);
            double after = bulk.CalculateCost();
            Console.WriteLine($"Доплата до изменения: {before:F2} руб.");
            Console.WriteLine($"Доплата после изменения: {after:F2} руб.");

            Console.WriteLine("\nПроверка защиты от ошибок:");
            CheckInvalidFeatures();

            Console.WriteLine("конецц");
        }

        private static void PrintProduct(Product product)
        {
            Console.WriteLine($"{product.Name}: " +
                $"доплата {product.CalculateCost():F2} руб., " +
                $"хранение {product.CalculateStorageCost():F2} руб., " +
                $"охлаждение = {product.RequiresCooling}");
        }

        private static void CheckInvalidFeatures()
        {
            int before = Product.GetCreatedProducts();

            try
            {
                new PackagingFeature(-100);
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine($"Упаковка: {ex.Message}");
            }

            try
            {
                new ExpiryFeature(0);
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine($"Срок годности: {ex.Message}");
            }

            try
            {
                new BulkPricingFeature(-500);
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine($"Цена за килограмм: {ex.Message}");
            }

            try
            {
                new ConfigurableProduct(
                    "C003", "Некорректный товар", 100, 1, 0,
                    1, 0.01, false, null!);
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine($"Характеристика: {ex.Message}");
            }

            Console.WriteLine("Количество созданных товаров не изменилось: " +
                (Product.GetCreatedProducts() == before));
        }
    }
}
