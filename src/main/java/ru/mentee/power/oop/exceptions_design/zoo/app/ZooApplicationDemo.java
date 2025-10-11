package ru.mentee.power.oop.exceptions_design.zoo.app;

import ru.mentee.power.oop.exceptions_design.zoo.domain.*;
import ru.mentee.power.oop.exceptions_design.zoo.manager.ZooManager;

public class ZooApplicationDemo {
    public static void main(String[] args) {
        ZooManager manager = new ZooManager();

        // Создаем животных
        Animal simba = new Lion("Simba");
        Animal rafiki = new Monkey("Rafiki");

        manager.addAnimal(simba);
        manager.addAnimal(rafiki);

        // Создаем вольеры
        Habitat savanna = new Habitat("Саванна");
        Habitat jungle = new Habitat("Джунгли");
        Habitat aquarium = new Habitat("Аквариум");

        manager.addHabitat(savanna);
        manager.addHabitat(jungle);
        manager.addHabitat(aquarium);

        // Весь демонстрационный сценарий обёрнут в защищающий блок, чтобы никакое неперехваченное исключение
        // не прерывало демонстрацию (на случай неожиданных ошибок).
        try {
            // Пример 1: успешное размещение
            manager.placeAnimalInHabitat("Simba", "Саванна");
            System.out.println("Simba успешно помещён в Саванну.");

            // Пример 2: попытка поместить льва в аквариум (ожидаем обработку внутри менеджера)
            manager.placeAnimalInHabitat("Simba", "Аквариум");
            System.out.println("Попытка поместить Simba в Аквариум выполнена (если несовместимо, это было залогировано).");

            // Пример 3: попытка поместить несуществующего животного
            manager.placeAnimalInHabitat("Unknown", "Джунгли");
            System.out.println("Попытка поместить Unknown выполнена (если не найден, это было залогировано).");

            // Пример 4: кормление; performEat уже обрабатывает UnsupportedFoodException внутри себя,
            // но feedAnimal логирует отсутствие животного
            manager.feedAnimal("Simba", "мясо");      // корректно
            manager.feedAnimal("Simba", "бананы");    // будет залогировано UnsupportedFoodException внутри performEat
            manager.feedAnimal("Rafiki", "бананы");   // корректно
            manager.feedAnimal("Rafiki", "мясо");     // будет залогировано UnsupportedFoodException внутри performEat

            // Пример 5: попытка накормить несуществующего животного
            manager.feedAnimal("NoSuch", "мясо");

            // Демонстрация: содержимое вольеров (без риска выброса исключений)
            System.out.println("Содержимое вольеров:");
            for (Habitat h : manager.getAllHabitats()) {
                System.out.println("Вольер: " + h.getType() + " -> " + h.getAnimalsInHabitat());
            }

        } catch (Exception unexpected) {
            // На случай непредвиденных ошибок — логируем, но не прерываем JVM (демонстрация должна быть устойчивой).
            System.err.println("Неожиданное исключение: " + unexpected.getClass().getSimpleName()
                    + " - " + unexpected.getMessage());
            unexpected.printStackTrace(System.err);
        }

        System.out.println("Демонстрация завершена.");
    }

}
