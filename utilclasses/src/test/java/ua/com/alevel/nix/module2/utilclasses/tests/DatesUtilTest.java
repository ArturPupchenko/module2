package ua.com.alevel.nix.module2.utilclasses.tests;

import org.junit.jupiter.api.Test;
import ua.com.alevel.nix.module2.utilclasses.DatesUtil;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static ua.com.alevel.nix.module2.utilclasses.DatesUtil.defaultDatesInputAsStringList;
import static ua.com.alevel.nix.module2.utilclasses.DatesUtil.defaultValidDateFormats;

public class DatesUtilTest {

    @Test
    public void makeDateList() {
        List<String> expectedList = new ArrayList<>(Arrays.asList("20010405", "20020405", "20030405", "20040405"));
        List<String> actualList = new ArrayList<>();
        actualList = DatesUtil.makeDateList(defaultDatesInputAsStringList, defaultValidDateFormats);
        assertEquals(expectedList, actualList);
    }
}
