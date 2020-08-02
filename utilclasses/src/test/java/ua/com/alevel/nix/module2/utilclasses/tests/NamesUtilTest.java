package ua.com.alevel.nix.module2.utilclasses.tests;

import org.junit.jupiter.api.Test;
import ua.com.alevel.nix.module2.utilclasses.NamesUtil;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class NamesUtilTest {
    @Test
    public void getFirstUniqueNameFromStringList() {
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
        String  actualName  = "Tim";
        String expectedName = NamesUtil.getFirstUniqueNameFromStringList(namesList);
        assertEquals(actualName, expectedName);
    }
}