package service;

import model.Monomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompareMonomialPowerTest
{

    @Test
    void compareTest1()
    {
        Monomial monomial1 = new Monomial(2.0, -3.7);
        Monomial monomial2 = new Monomial(2.0, -3.0);

        assertEquals(0.0,new CompareMonomialPower().compare(monomial1, monomial2));
    }


    @Test
    void compareTest2()
    {
        Monomial monomial1 = new Monomial(3.0, -3.7);
        Monomial monomial2 = new Monomial(2.0, -3.0);

        assertEquals(-1.0, new CompareMonomialPower().compare(monomial1, monomial2));
    }

    @Test
    void compareTest3()
    {
        Monomial monomial1 = new Monomial(2.0, -3.7);
        Monomial monomial2 = new Monomial(4.0, -3.0);

        assertEquals(2.0,new CompareMonomialPower().compare(monomial1, monomial2));
    }
}