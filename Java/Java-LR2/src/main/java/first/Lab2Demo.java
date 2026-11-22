
package first;

final class Lab2Demo {

    public static void run() {
        System.out.println();
        System.out.println("///////////////////////////////////////////////////////////22222222222222222222");

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

        for (Product product : products) {
            System.out.printf(
                    "%s: доплата %.2f руб., стоимость хранения %.2f руб.%n",
                    product.getName(),
                    product.calculateCost(),
                    product.calculateStorageCost());
        }

        IStorageRule[] rules = {
                new ShelfRule(),
                new RefrigeratorRule(),
                new SafeRule(),
                new FreezerRule()
        };

        String[] ruleNames = {
                "Полка", "Холодильник", "Сейф", "Морозильная камера"
        };

        for (Product product : products) {
            System.out.println("\n" + product.getName() + ":");

            for (int i = 0; i < rules.length; i++) {
                System.out.println(
                        ruleNames[i] + ": "
                                + (rules[i].canStore(product) ? "Можно" : "Нельзя"));
            }
        }

        Warehouse warehouse = new Warehouse(5);

        warehouse.doseProduct(fragile, rules[2]);
        warehouse.doseProduct(perishable, rules[1]);
        warehouse.doseProduct(bulk, rules[0]);

        System.out.println(
                "\nРазмещено товаров на складе: "
                        + warehouse.getProducts().length);

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

        printProduct(twoFeatures);
        printProduct(threeFeatures);

        Product[] combinedProducts = { twoFeatures, threeFeatures };

        for (Product product : combinedProducts) {
            System.out.println(
                    product.getName() + ": охлаждение = "
                            + product.requiresCooling());
        }

        System.out.println("\nПроверка изменения веса весового товара:");

        double before = bulk.calculateCost();
        bulk.changeDimensions(3, 0.005, false);
        double after = bulk.calculateCost();

        System.out.printf("Доплата до изменения: %.2f руб.%n", before);
        System.out.printf("Доплата после изменения: %.2f руб.%n", after);

        System.out.println("\nПроверка защиты от ошибок:");
        checkInvalidFeatures();

        System.out.println("конецц");
    }

    private static void printProduct(Product product) {
        System.out.printf(
                "%s: доплата %.2f руб., хранение %.2f руб., охлаждение = %b%n",
                product.getName(),
                product.calculateCost(),
                product.calculateStorageCost(),
                product.requiresCooling());
    }

    private static void checkInvalidFeatures() {
        int before = Product.getCreatedProducts();

        try {
            new PackagingFeature(-100);
        } catch (IllegalArgumentException ex) {
            System.out.println("Упаковка: " + ex.getMessage());
        }

        try {
            new ExpiryFeature(0);
        } catch (IllegalArgumentException ex) {
            System.out.println("Срок годности: " + ex.getMessage());
        }

        try {
            new BulkPricingFeature(-500);
        } catch (IllegalArgumentException ex) {
            System.out.println("Цена за килограмм: " + ex.getMessage());
        }

        try {
            new ConfigurableProduct(
                    "C003", "Некорректный товар", 100, 1, 0,
                    1, 0.01, false, (IProductFeature[]) null);
        } catch (NullPointerException ex) {
            System.out.println("Характеристика: " + ex.getMessage());
        }

        System.out.println(
                "Количество созданных товаров не изменилось: "
                        + (Product.getCreatedProducts() == before));
    }
    
}
