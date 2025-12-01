package ru.mentee.power.technicalInterview;

import lombok.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    private String productName;
    private String category;
    private String customerName;
    private double amount;
    private LocalDate orderDate;

    public static Map<String, Double> groupOrdersByCategory(List<Order>orders){
    //Сгруппируйте заказы по категориям и для каждой категории вычислите общую сумму продаж.
        Map<String,Double>categorySum=orders.stream()//вызываем метод стрим для создания потока
                //вызываем метод collect для сбора коллекции
                .collect(
                        //вызываем метод groupingBy для группировки данных в коллекцию
                        Collectors.groupingBy(
                                Order::getCategory,// группируем данные по категориям
                                Collectors.summingDouble(// вызываем функцию для суммирования данных
                                        Order::getAmount)));//извлекаем сумму из функции getAmount

        categorySum.forEach((category,sum)-> System.out.println(category + ": "+ sum));

        return categorySum;//возвращаем Map с данными по категориям и продажам
}

    public static List<Order> getTop3Orders(List<Order>orders){
        //Найдите 3 самых дорогих заказа, отсортированных по убыванию суммы.
        //Присваиваем списку Order данные потока
        List<Order>top3Orders=orders.stream()//вызываем метод stream для создания потока из списка заказов
                .sorted(//вызываем метод sorted для сортировки заказов по убыванию
                        (o1, o2) ->
                                //Сравниваем суммы двух заказов в убывающем порядке
                                Double.compare(o2.getAmount(), o1.getAmount()))
                //вызов метода limit для ограничения потока до 3 элементов
                .limit(3)
                //вызываем метод collect для сбора результата в список
                .collect(
                        Collectors.toList());//создаем коллектор для сбора результата в список
                //выводим на экран данные
                top3Orders.forEach(
                        order ->//название функции
                                System.out.println(order.getProductName()+": " + order.getAmount()));
                //данные по названию продукта и цене

        return top3Orders;//возвращаем список из 3 самых дорогих заказов
    }

    // Объявляем метод groupCustomersByMonth, который принимает список заказов (orders) и возвращает карту,
// где ключи - номера месяцев, а значения - количество уникальных покупателей в каждом месяце
    public static Map<Integer, Long> groupCustomersByMonth(List<Order>orders){
        //Задание. Сгруппируйте заказы по месяцам (январь, февраль, март) и для каждого месяца покажите количество уникальных покупателей.
        // Создаем карту, в которой заказы сгруппированы по месяцам,
        // и для каждого месяца определяется набор уникальных покупателей
        Map<Integer,Long>customerCountsByMonth=orders.stream()//вызываем метод stream для создания потока
                .collect(//вызываем метод collect для сбора результатов
                        Collectors.groupingBy(//вызываем метод groupingBy для группировки результатов по месяцам
                                //order -название функции, с помощью которой получаем номер месяца из даты заказа
                                order->order.getOrderDate().getMonthValue(),
                                //вызываем метод mapping для преобразования данных
                                Collectors.mapping(
                                        //Извлекаем имя покупателя
                                        Order::getCustomerName,
                                        //Помещаем имена в коллекцию Set для создания уникальных покупателей
                                        Collectors.toSet())))
                //Вызываем метод entrySet().stream() для преобразования  Map в поток данных
                .entrySet().stream()
                .collect(//Вызываем метод collect для сбора результатов в новую карту
                        Collectors.toMap(//Вызываем метод toMap для преобразования набора записей в новую карту
                                Map.Entry::getKey,//Вызываем функцию  Map.Entry::getKey для извлечения ключа из записи

                                //Вызываем функцию entry для определения значения для каждого ключа
                                entry->(long)entry.getValue().size()));
        //Выводим на экран данные о количестве покупателей
        customerCountsByMonth.forEach((month,count)-> System.out.println("Месяц "+month + ": "+ count));

        //Возвращаем Map  номерами месяцев и количеством уникальных покупателей
        return customerCountsByMonth;
    }

    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order("iPhone 15", "Electronics", "Иван Петров", 89999.0, LocalDate.of(2024, 1, 15)),
                new Order("MacBook Pro", "Electronics", "Анна Смирнова", 199999.0, LocalDate.of(2024, 1, 20)),
                new Order("Кроссовки Nike", "Sports", "Петр Иванов", 12000.0, LocalDate.of(2024, 1, 18)),
                new Order("Футболка Adidas", "Sports", "Иван Петров", 3500.0, LocalDate.of(2024, 2, 5)),
                new Order("Samsung TV", "Electronics", "Мария Козлова", 75000.0, LocalDate.of(2024, 2, 10)),
                new Order("Беговая дорожка", "Sports", "Анна Смирнова", 45000.0, LocalDate.of(2024, 2, 12)),
                new Order("iPad", "Electronics", "Петр Иванов", 55000.0, LocalDate.of(2024, 2, 15)),
                new Order("Гантели", "Sports", "Иван Петров", 8000.0, LocalDate.of(2024, 3, 1)),
                new Order("Наушники Sony", "Electronics", "Мария Козлова", 15000.0, LocalDate.of(2024, 3, 5)),
                new Order("Велосипед", "Sports", "Анна Смирнова", 25000.0, LocalDate.of(2024, 3, 10))
        );

        groupOrdersByCategory(orders);
        System.out.println();
        groupCustomersByMonth(orders);
        System.out.println();
        getTop3Orders(orders);
    }
}
