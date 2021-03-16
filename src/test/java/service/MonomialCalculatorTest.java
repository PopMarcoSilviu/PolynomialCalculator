package service;

import exceptions.IncompatibleMonomialsForOperationException;
import model.Monomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonomialCalculatorTest
{

    @Test
    void addTest1()
    {
        Monomial monomial1 = new Monomial(1.0,2.0);
        Monomial monomial2 = new Monomial(1.0,3.0);
        Monomial expected = new Monomial(1.0,5.0);
        Monomial actual;

        try
        {
            actual = MonomialCalculator.add(monomial1, monomial2);
            assertEquals(expected, actual, "Monomial should be added");

        } catch (IncompatibleMonomialsForOperationException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    void addTest2()
    {
        Monomial monomial1 = new Monomial(1.0,2.0);
        Monomial monomial2 = new Monomial(3.0,4.0);

        assertThrows(IncompatibleMonomialsForOperationException.class, () ->
                MonomialCalculator.add(monomial1, monomial2));
    }

    @Test
    void subtractTest1()
    {
        Monomial monomial1 = new Monomial(1.0,2.0);
        Monomial monomial2 = new Monomial(1.0,4.0);
        Monomial expected = new Monomial(1.0,-2.0);

        try
        {
            Monomial actual = MonomialCalculator.subtract(monomial1, monomial2);
            assertEquals(expected, actual, "Monomials should be subtracted");

        } catch (IncompatibleMonomialsForOperationException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    void subtractTest2()
    {
        Monomial monomial1 = new Monomial(1.0,-3.0);
        Monomial monomial2 = new Monomial(3.0,7.0);

        assertThrows(IncompatibleMonomialsForOperationException.class, () ->
                MonomialCalculator.subtract(monomial1, monomial2));
    }

    @Test
    void multiplyTest1()
    {
        Monomial monomial1 = new Monomial(2.0,-3.0);
        Monomial monomial2 = new Monomial(3.0,7.0);
        Monomial expected = new Monomial(5.0,-21.0);
        Monomial actual ;
        try
        {
            actual = MonomialCalculator.multiply(monomial1, monomial2);
            assertEquals(expected, actual, "Monomials should be multiplied");
        } catch (IncompatibleMonomialsForOperationException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    void multiplyTest2()
    {
        Monomial monomial1 = new Monomial(-4.0,2.0);
        Monomial monomial2 = new Monomial(3.0,4.0);
        Monomial expected = new Monomial(-1.0,8.0);
        Monomial actual;
        try
        {
            actual = MonomialCalculator.multiply(monomial1, monomial2);
            assertEquals(expected, actual, "Monomials should be multiplied");

        } catch (IncompatibleMonomialsForOperationException e)
        {
            e.printStackTrace();
        }


    }

    @Test
    void divideTest1()
    {
        Monomial monomial1 = new Monomial(-4.0,4.0);
        Monomial monomial2 = new Monomial(3.0,3.0);
        Monomial expected = new Monomial(-7.0,1.3333333333333333);
        try
        {
            Monomial actual = MonomialCalculator.divide(monomial1, monomial2);
            assertEquals(actual, expected, "Monomials should be divided");
        } catch (IncompatibleMonomialsForOperationException e)
        {
            fail(e.getMessage());
        }

    }

    @Test
    void divideTest2()
    {
        Monomial monomial1 = new Monomial(2.0,2.0);
        Monomial monomial2 = new Monomial(3.0,0.0);

        assertThrows(IncompatibleMonomialsForOperationException.class, () -> MonomialCalculator.divide(monomial1, monomial2));
    }

    @Test
    void deriveTest1()
    {
        Monomial monomial = new Monomial(2.0,-2.0);
        Monomial expected = new Monomial(1.0,-4.0);
        Monomial actual = MonomialCalculator.derive(monomial);

        assertEquals(expected, actual);
    }

    @Test
    void deriveTest2()
    {
        Monomial monomial = new Monomial(5.0,3.0);
        Monomial expected = new Monomial(4.0,15.0);
        Monomial actual = MonomialCalculator.derive(monomial);

        assertEquals(expected, actual);
    }

    @Test
    void integrateTest1()
    {
        Monomial monomial = new Monomial(5.0,3.0);
        Monomial expected = new Monomial(6.0,0.5);
        Monomial actual = MonomialCalculator.integrate(monomial);

        assertEquals(expected, actual);
    }

    @Test
    void integrateTest2()
    {
        Monomial monomial = new Monomial(-5.0,2.0);
        Monomial expected = new Monomial(-4.0,-0.5);
        Monomial actual = MonomialCalculator.integrate(monomial);

        assertEquals(expected, actual);
    }
}