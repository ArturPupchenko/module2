package ua.com.alevel.nix.module2.main;

import ua.com.alevel.nix.module2.utilclasses.DatesUtil;
import ua.com.alevel.nix.module2.utilclasses.GraphsUtil;
import ua.com.alevel.nix.module2.utilclasses.NamesUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

////    1. Дан список дат (строковая запись) в форматах типа “2020/04/05”, “05/04/2020”, “04-05-2020”
////    (все даты в примере - 5е апреля 2020) Вернуть список дат (строковая запись) в формате “20200405”.
////    Даты с неверным форматом - игнорировать.

    public static void doTask1() {
        System.out.println("\nЗадание 1\n");
        System.out.println("Дан список дат (строковая запись) в форматах типа \"2020/04/05\", \"05/04/2020\", \"04-05-2020\"" +
                "\n(все даты в примере - 5е апреля 2020). Вернуть список дат (строковая запись) в формате \"20200405\"" +
                "\nДаты с неверным форматом - игнорировать.\n");
        System.out.println("Дан список дат: " + DatesUtil.defaultDatesInputAsStringList);
        System.out.println("Список допустимых форматов вводимых дат: " + DatesUtil.defaultValidDateFormats);
        List<String> validAndFormatedDates = new ArrayList<>();
        validAndFormatedDates = DatesUtil.makeDateList(DatesUtil.defaultDatesInputAsStringList, DatesUtil.defaultValidDateFormats);
        System.out.println("Список дат после форматирования: " + validAndFormatedDates);
        System.out.println("\n-----------------------------------------------");
    }


//    Task 2
//    2. Дан список имен. Найти первое уникальное имя.
//    Допустимая временная сложность - O(n) при условии, что доступ к элементу списка по индексу - O(1).

    public static void doTask2() {
        List<String> namesList = new ArrayList<>();
        namesList.add("Anna");
        namesList.add("Anna");
        namesList.add("John");
        namesList.add("Anna");
        namesList.add("Vince");
        namesList.add("John");
        namesList.add("Vince");
        namesList.add("Vince");
        namesList.add("Vince");
        namesList.add("Tim");
        namesList.add("Casey");
        System.out.println("Задание 2\n");
        System.out.println("Дан список имен. Найти первое уникальное имя.\n" +
                "Допустимая временная сложность - O(n) при условии, что доступ к элементу списка по индексу - O(1).\n");
        System.out.println("Дан список имен: = " + namesList);
        String firstUniqueName = NamesUtil.getFirstUniqueNameFromStringList(namesList);
        System.out.println("Первое уникальное имя: " + firstUniqueName);
        System.out.println("\n-----------------------------------------------");
    }


//    3. Дан список городов. Каждый путь между городами имеет цену (целое положительное число).
//    Задача - найти самый выгодный путь между двумя городами. Максимально возможная цена пути - 200000.
//    Данные условия необходимо считать из файла input.txt
//    Результат надо записать в файл output.txt


    public static void doTask3() {
        System.out.println("Задание 2\n");
        System.out.println("Дан список городов. Каждый путь между городами имеет цену (целое положительное число)." +
                "\nЗадача - найти самый выгодный путь между двумя городами. Максимально возможная цена пути - 200000." +
                "\nДанные условия необходимо считать из файла input.txt" +
                "\nРезультат надо записать в файл output.txt\n");

        File textFrom = new File("input.txt");
        String textFilePath = textFrom.getPath();
        List<String> linesAsArrayList = new ArrayList<>();
        String lineFromFile;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFilePath))) {
            while ((lineFromFile = bufferedReader.readLine()) != null) {
                linesAsArrayList.add(lineFromFile);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Текст из файла \"input.txt\":\n");
        linesAsArrayList.forEach(System.out::println);
        GraphsUtil.buildGraphFromTextFile("input.txt");
        System.out.println("\nФайл output.txt был успешкно создан");
        File textTo = new File("output.txt");
        String textFilePath2 = textTo.getPath();
        List<String> linesAsArrayList2 = new ArrayList<>();
        String lineFromFile2;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFilePath2))) {
            while ((lineFromFile2 = bufferedReader.readLine()) != null) {
                linesAsArrayList2.add(lineFromFile2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Текст из файла \"output.txt\":\n");
        linesAsArrayList2.forEach(System.out::println);
        System.out.println("\n-----------------------------------------------");
    }


    public static void main(String[] args) {
        doTask1();
        doTask2();
        doTask3();
    }
}


