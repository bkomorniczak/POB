package zad3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddTest {

    @Test
    void testAdd() {
        //given
        int a = 5;
        int b = 4;
        Add add = new Add();
        //when
        int result = add.add(a,b);
        //then
        Assertions.assertEquals(9, result);
    }
}