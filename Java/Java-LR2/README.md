# Лабораторная работа №2 — ООП (Java)

**Вариант 2: «Товары и склад».** Тема: связи между классами, композиция,
наследование, интерфейсы и полиморфизм.

Проект перенесён из исходной папки `first` в стандартную структуру Maven
без изменения логики Java-классов.

## Структура

- `src/main/java/first/Skladik.java` — точка входа `main`, примеры работы.
- `src/main/java/first/Product.java` — абстрактный базовый класс товара.
- `FragileProduct`, `PerishableProduct`, `BulkProduct`, `FreezeProduct` — виды товаров.
- `ConfigurableProduct` и реализации `IProductFeature` — композиция признаков.
- `Warehouse` и реализации `IStorageRule` — склад и стратегии размещения.
- `Lab2Demo.java` — дополнительные сценарии ЛР2; вызывается из `Skladik`.

## Запуск

Требуются JDK 17+ и Apache Maven.

В терминале **из папки, где расположен `pom.xml`**:

```powershell
mvn clean compile
mvn exec:java
```

Альтернативный запуск без Maven (PowerShell):

```powershell
New-Item -ItemType Directory -Force -Path out | Out-Null
javac -encoding UTF-8 -d out src\main\java\first\*.java
if ($LASTEXITCODE -eq 0) { java -cp out first.Skladik }
```

Важно: исходный пакет `first` оставлен без изменений, поэтому
главный класс запуска — `first.Skladik`, а не `first.Lab2Demo`.

## Что было адаптировано

- Исходники перенесены в `src/main/java/first`.
- `DEMO2.java` переименован в `Lab2Demo.java` по имени класса.
- `Perishable.java` переименован в `PerishableProduct.java` по имени класса.
- Добавлены `pom.xml`, `.gitignore` и документация.
- Файлы `.class` из исходного ZIP не копировались: они получаются при компиляции.

Модульные тесты по заданию ЛР3 будут добавлены отдельно.
