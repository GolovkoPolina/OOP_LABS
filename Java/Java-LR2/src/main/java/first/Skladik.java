
package first;

public class Skladik {
    public static void main(String[] args) {

        Product imperialPorcelain = new FragileProduct(
                "P001", "Императорский фарфор", 45000, 10, 3,
                2.5, 0.03, true, 1500);

        Product blackCaviar = new PerishableProduct(
                "P002", "Черная икра", 12000, 2, 5,
                0.1, 0.001, false, 2);

        Product daHongPao = new BulkProduct(
                "P003", "Да Хун Пао", 8000, 4, 2,
                1, 0.005, false, 8000);

        Warehouse warehouse = new Warehouse(10);

        IStorageRule safeRule = new SafeRule();
        IStorageRule refrigeratorRule = new RefrigeratorRule();
        IStorageRule shelfRule = new ShelfRule();
        IStorageRule freezerRule = new FreezerRule();

        warehouse.doseProduct(imperialPorcelain, safeRule);
        warehouse.doseProduct(blackCaviar, refrigeratorRule);
        warehouse.doseProduct(daHongPao, shelfRule);

        Product frozenFish = new FreezeProduct(
                "P005", "Замороженная рыба", 700, 5, 1,
                2, 0.01, false);

        System.out.println("Проверка замороженной рыбы:");
        System.out.println("Требуемая температура: "
                + frozenFish.getRequiredStorageTemperature() + " °C");

        System.out.println("Холодильник: "
                + refrigeratorRule.canStore(frozenFish));

        System.out.println("Морозильная камера: "
                + freezerRule.canStore(frozenFish));

        warehouse.doseProduct(frozenFish, freezerRule);

        System.out.println("\nТовары на складе:");

        for (Product product : warehouse.getProducts()) {
            System.out.println(product);
        }

        System.out.println("\nСоздано товаров: "
                + Product.getCreatedProducts());

        System.out.printf(
                "Общая стоимость товаров на складе: %.2f руб.%n",
                warehouse.getMnogoValue());

        System.out.println("\nДополнительная стоимость хранения:");

        for (Product product : warehouse.getProducts()) {
            System.out.printf(
                    "%s: доплата %.2f руб., хранение %.2f руб.%n",
                    product.getName(),
                    product.calculateCost(),
                    product.calculateStorageCost());
        }

        System.out.printf(
                "%nЦена фарфора с НДС: %.2f руб.%n",
                imperialPorcelain.priceWithTax());

        System.out.printf(
                "Цена фарфора с налогом 20%%: %.2f руб.%n",
                imperialPorcelain.priceWithTax(0.20));

        Product found = warehouse.findByArticle("P001");
        System.out.println("\nПоиск P001: " + found);

        warehouse.shipByArticle("P001", 2);
        System.out.println("\nПосле отгрузки: " + imperialPorcelain);

        System.out.println("\nТовары ниже минимального остатка:");

        for (Product product : warehouse.getLowRestProducts()) {
            System.out.println(product);
        }

        System.out.println("\nПроверка защиты от ошибок:");

        try {
            warehouse.shipByArticle("P001", 100);
        } catch (IllegalStateException ex) {
            System.out.println("Ошибка отгрузки: " + ex.getMessage());
        }

        try {
            imperialPorcelain.changePrice(-1);
        } catch (IllegalArgumentException ex) {
            System.out.println("Ошибка цены: " + ex.getMessage());
        }

        try {
            new FragileProduct(
                    "", "Неверный товар", 100, 1, 0,
                    2.5, 0.03, true, 500);
        } catch (IllegalArgumentException ex) {
            System.out.println("Ошибка артикула: " + ex.getMessage());
        }

        System.out.println("Создано успешных товаров: "
                + Product.getCreatedProducts());

        Product[] outsideProducts = warehouse.getProducts();
        outsideProducts[0] = blackCaviar;

        System.out.println(
                "Первый товар склада после изменения копии массива: "
                        + warehouse.getProducts()[0].getName());

        Dimensions receivedDimensions = blackCaviar.getDimensions();
        receivedDimensions = new Dimensions(100, 5, true);

        System.out.println(
                "Вес локальных характеристик: "
                        + receivedDimensions.getWeight());

        System.out.println(
                "Вес икры остался: "
                        + blackCaviar.getDimensions().getWeight());

        daHongPao.changeDimensions(1.5, 0.015, false);

        System.out.println(
                "Вес чая после замены характеристик: "
                        + daHongPao.getDimensions().getWeight());

        System.out.println("\nПравила хранения:");

        IStorageRule[] rules = {
                shelfRule,
                refrigeratorRule,
                safeRule,
                freezerRule
        };

        String[] ruleNames = {
                "Полка",
                "Холодильник",
                "Сейф",
                "Морозильная камера"
        };

        for (Product product : warehouse.getProducts()) {
            System.out.println("\n" + product.getName() + ":");

            for (int i = 0; i < rules.length; i++) {
                System.out.println(
                        ruleNames[i] + ": "
                                + (rules[i].canStore(product)
                                ? "Можно"
                                : "Нельзя"));
            }
        }

        System.out.println("\nПроверка запрета хранения молока на полке:");

        Product milk = new PerishableProduct(
                "P004", "Молоко", 150, 5, 2,
                1, 0.001, false, 5);

        try {
            warehouse.doseProduct(milk, shelfRule);
        } catch (IllegalStateException ex) {
            System.out.println("Отказ: " + ex.getMessage());
        }

        Lab2Demo.run();
    }
}
