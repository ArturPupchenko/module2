package ua.com.alevel.nix.module2.utilclasses;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class NamesUtil {

//    Task 2
//    2. Дан список имен. Найти первое уникальное имя.
//    Допустимая временная сложность - O(n) при условии, что доступ к элементу списка по индексу - O(1).

    public static String getFirstUniqueNameFromStringList(List<String> namesList) {
        try {
            if (!namesList.isEmpty()) {
                Set<String> namesSet = new LinkedHashSet<>();
                Set<String> notUniqueNamesSet = new LinkedHashSet<>();
                for (String name : namesList) {
                    if (!namesSet.add(name)) notUniqueNamesSet.add(name);
                }
                namesSet.removeAll(notUniqueNamesSet);
                String firstUniqueName = namesSet.iterator().next();
                return firstUniqueName;
            } else throw new Exception("List can not be empty!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
