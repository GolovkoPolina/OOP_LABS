package laba1;

/** Запуск и демонстрация возможностей ЛР1. */
public class Skladik {
    public static void main(String[] args) {
        Dimensions keyboardDimensions = new Dimensions(0.8, 0.01, false);
        Dimensions mouseDimensions = new Dimensions(0.2, 0.003, false);
        Dimensions monitorDimensions = new Dimensions(5.5, 0.08, true);

        Product keyboard = new Product("A001", "Клавиатура", 5000, 10, 3, keyboardDimensions);
        Product mouse = new Product("A002", "Мышь", 2000, 2, 5, mouseDimensions);
        Product monitor = new Product("A003", "Монитор", 25000, 4, 2, monitorDimensions);

        Warehouse warehouse = new Warehouse(10);
        warehouse.doseProduct(keyboard);
        warehouse.doseProduct(mouse);
        warehouse.doseProduct(monitor);

        System.out.println("Товары на складе:");
        for (Product product : warehouse.getProducts()) {
            System.out.println(product);
        }

        System.out.println("\nСоздано товаров: " + Product.getCreatedProducts());
        System.out.println("Общая стоимость склада: " + warehouse.getMnogoValue());
        System.out.printf("Цена клавиатуры с НДС: %.2f%n", keyboard.priceWithTax());
        System.out.printf("Цена клавиатуры с налогом 20%%: %.2f%n", keyboard.priceWithTax(0.20));

        Product found = warehouse.findByArticle("A001");
        System.out.println("\nПоиск A001: " + found);

        System.out.println("\nОтгружаем две клавиатуры:");
        warehouse.shipByArticle("A001", 2);
        System.out.println(keyboard);

        System.out.println("\nТовары ниже минимального остатка:");
        for (Product product : warehouse.getLowRestProducts()) {
            System.out.println(product);
        }

        System.out.println("\nПроверка защиты от ошибок:");
        try { warehouse.shipByArticle("A001", 100); }
        catch (RuntimeException ex) { System.out.println("Ошибка отгрузки: " + ex.getMessage()); }
        try { keyboard.changePrice(-1); }
        catch (RuntimeException ex) { System.out.println("Ошибка цены: " + ex.getMessage()); }
        try { new Product("", "Неверный товар", 100, 1, 0, keyboardDimensions); }
        catch (RuntimeException ex) { System.out.println("Ошибка артикула: " + ex.getMessage()); }

        System.out.println("Количество успешно созданных товаров: " + Product.getCreatedProducts());
        Product[] outsideProducts = warehouse.getProducts();
        outsideProducts[0] = monitor;
        System.out.println("\nПосле подмены элемента копии первый товар склада: " + warehouse.getProducts()[0].getName());

        Dimensions receivedDimensions = keyboard.getDimensions();
        receivedDimensions = new Dimensions(100, 5, true);
        System.out.println("Вес в новой локальной переменной: " + receivedDimensions.getWeight());
        System.out.println("Вес клавиатуры не изменился: " + keyboard.getDimensions().getWeight());
        keyboard.changeDimensions(new Dimensions(1.0, 0.015, false));
        System.out.println("Вес клавиатуры после замены Dimensions целиком: " + keyboard.getDimensions().getWeight());
    }
}
