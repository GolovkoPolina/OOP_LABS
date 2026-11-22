
using System;
using laba1Nikitin;

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

warehouse.DoseProduct(imperialPorcelain, safeRule);
warehouse.DoseProduct(blackCaviar, refrigeratorRule);
warehouse.DoseProduct(daHongPao, shelfRule);

Console.WriteLine("Товары на складе:");

foreach (Product product in warehouse.GetProducts())
{
    Console.WriteLine(product);
}

Console.WriteLine($"\nСоздано товаров: {Product.GetCreatedProducts()}");
Console.WriteLine($"Общая стоимость товаров на складе: {warehouse.GetMnogoValue():F2} руб.");

Console.WriteLine("\nДополнительная стоимость хранения:");

foreach (Product product in warehouse.GetProducts())
{
    Console.WriteLine($"{product.Name}: доплата {product.CalculateCost():F2} руб., " +
        $"хранение {product.CalculateStorageCost():F2} руб.");
}

Console.WriteLine($"\nЦена фарфора с НДС: {imperialPorcelain.priceWithTax():F2} руб.");
Console.WriteLine($"Цена фарфора с налогом 20%: {imperialPorcelain.priceWithTax(0.20):F2} руб.");

Product? found = warehouse.FindByArticle("P001");
Console.WriteLine($"\nПоиск P001: {found}");

warehouse.ShipByArticle("P001", 2);
Console.WriteLine($"\nПосле отгрузки: {imperialPorcelain}");

Console.WriteLine("\nТовары ниже минимального остатка:");

foreach (Product product in warehouse.GetLowRestProducts())
{
    Console.WriteLine(product);
}

Console.WriteLine("\nПроверка защиты от ошибок:");

try
{
    warehouse.ShipByArticle("P001", 100);
}
catch (InvalidOperationException ex)
{
    Console.WriteLine($"Ошибка отгрузки: {ex.Message}");
}

try
{
    imperialPorcelain.ChangePrice(-1);
}
catch (ArgumentException ex)
{
    Console.WriteLine($"Ошибка цены: {ex.Message}");
}

try
{
    new FragileProduct("", "Неверный товар", 100, 1, 0, 2.5, 0.03, true, 500);
}
catch (ArgumentException ex)
{
    Console.WriteLine($"Ошибка артикула: {ex.Message}");
}

Console.WriteLine($"Создано успешных товаров: {Product.GetCreatedProducts()}");

Product[] outsideProducts = warehouse.GetProducts();
outsideProducts[0] = blackCaviar;
Console.WriteLine($"Первый товар склада после изменения копии массива: " +
    warehouse.GetProducts()[0].Name);

Dimensions receivedDimensions = blackCaviar.Dimensions;
receivedDimensions = new Dimensions(100, 5, true);
Console.WriteLine($"Вес локальных характеристик: {receivedDimensions.Weight}");
Console.WriteLine($"Вес икры остался: {blackCaviar.Dimensions.Weight}");

daHongPao.ChangeDimensions(1.5, 0.015, false);
Console.WriteLine($"Вес чая после замены характеристик: {daHongPao.Dimensions.Weight}");

Console.WriteLine("\nПравила хранения:");

IStorageRule[] rules =
{
    shelfRule, refrigeratorRule, safeRule, freezerRule
};

string[] ruleNames =
{
    "Полка", "Холодильник", "Сейф", "Морозильная камера"
};

foreach (Product product in warehouse.GetProducts())
{
    Console.WriteLine($"\n{product.Name}:");

    for (int i = 0; i < rules.Length; i++)
    {
        Console.WriteLine($"{ruleNames[i]}: " +
            (rules[i].CanStore(product) ? "Можно" : "Нельзя"));
    }
}

Console.WriteLine("\nПроверка запрета хранения молока на полке:");

Product milk = new PerishableProduct(
    "P004", "Молоко", 150, 5, 2, 1, 0.001, false, 5);

try
{
    warehouse.DoseProduct(milk, shelfRule);
}
catch (InvalidOperationException ex)
{
    Console.WriteLine($"Отказ: {ex.Message}");
}

Lab2Demo.Run();
