package service;

import exceptions.OperandNullException;
import exceptions.IncompatibleMonomialsForOperationException;
import exceptions.MonomialPowerAlreadyExists;
import model.Monomial;
import model.Polynomial;
import model.PolynomialPair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialCalculatorTest
{

    @Test
    void multiplyTest1()
    {
        List<Monomial> monomialList1 = new ArrayList<>();
        monomialList1.add(new Monomial(4.0, 4.0));
        monomialList1.add(new Monomial(3.0, 4.0));
        monomialList1.add(new Monomial(2.0, -3.0));
        monomialList1.add(new Monomial(1.0, 4.0));

        List<Monomial> monomialList2 = new ArrayList<>();
        monomialList2.add(new Monomial(3.0, 2.0));
        monomialList2.add(new Monomial(2.0, -2.0));
        monomialList2.add(new Monomial(1.0, -3.0));
        monomialList2.add(new Monomial(0.0, 1.0));

        List<Monomial> monomialList3 = new ArrayList<>();
        monomialList3.add(new Monomial(7.0, 8.0));
        monomialList3.add(new Monomial(5.0, -26.0));
        monomialList3.add(new Monomial(4.0, 6.0));
        monomialList3.add(new Monomial(3.0, 5.0));
        monomialList3.add(new Monomial(2.0, -15.0));
        monomialList3.add(new Monomial(1.0, 4.0));

        Polynomial actual = new Polynomial();
        Polynomial expected = new Polynomial(monomialList3);

        try
        {
            actual = PolynomialCalculator.multiply(new Polynomial(monomialList1), new Polynomial(monomialList2));
        } catch (IncompatibleMonomialsForOperationException e)
        {
            fail(e.getMessage());
        }

        assertEquals(expected, actual);
    }

    @Test
    void additionTest1()
    {
        List<Monomial> monomialList1 = new ArrayList<>();
        monomialList1.add(new Monomial(4.0, 4.0));
        monomialList1.add(new Monomial(3.0, 4.0));
        monomialList1.add(new Monomial(2.0, -3.0));
        monomialList1.add(new Monomial(1.0, 4.0));

        List<Monomial> monomialList2 = new ArrayList<>();
        monomialList2.add(new Monomial(3.0, 2.0));
        monomialList2.add(new Monomial(2.0, -2.0));
        monomialList2.add(new Monomial(1.0, -3.0));
        monomialList2.add(new Monomial(0.0, 1.0));

        Polynomial polynomial1 = new Polynomial(monomialList1);
        Polynomial polynomial2 = new Polynomial(monomialList2);

        Polynomial actual = null;
        try
        {
            actual = PolynomialCalculator.add(polynomial1, polynomial2);
        } catch (MonomialPowerAlreadyExists monomialPowerAlreadyExists)
        {
            fail(monomialPowerAlreadyExists.getMessage());
        }

        List<Monomial> monomialList3 = new ArrayList<>();
        monomialList3.add(new Monomial(4.0, 4.0));
        monomialList3.add(new Monomial(3.0, 6.0));
        monomialList3.add(new Monomial(2.0, -5.0));
        monomialList3.add(new Monomial(1.0, 1.0));
        monomialList3.add(new Monomial(0.0, 1.0));
        Polynomial expected = new Polynomial(monomialList3);

        assertEquals(expected, actual);
    }

    @Test
    void subtractionTest1()
    {
        List<Monomial> monomialList1 = new ArrayList<>();
        monomialList1.add(new Monomial(4.0, 4.0));
        monomialList1.add(new Monomial(3.0, 4.0));
        monomialList1.add(new Monomial(2.0, -3.0));
        monomialList1.add(new Monomial(1.0, 4.0));

        List<Monomial> monomialList2 = new ArrayList<>();
        monomialList2.add(new Monomial(3.0, 2.0));
        monomialList2.add(new Monomial(2.0, -2.0));
        monomialList2.add(new Monomial(1.0, -3.0));
        monomialList2.add(new Monomial(0.0, 1.0));

        Polynomial polynomial1 = new Polynomial(monomialList1);
        Polynomial polynomial2 = new Polynomial(monomialList2);

        Polynomial actual = null;
        try
        {
            actual = PolynomialCalculator.subtract(polynomial1, polynomial2);
        } catch (MonomialPowerAlreadyExists monomialPowerAlreadyExists)
        {
            fail(monomialPowerAlreadyExists.getMessage());
        }

        List<Monomial> monomialList3 = new ArrayList<>();
        monomialList3.add(new Monomial(4.0, 4.0));
        monomialList3.add(new Monomial(3.0, 2.0));
        monomialList3.add(new Monomial(2.0, -1.0));
        monomialList3.add(new Monomial(1.0, 7.0));
        monomialList3.add(new Monomial(0.0, -1.0));
        Polynomial expected = new Polynomial(monomialList3);

        assertEquals(expected, actual);
    }

    @Test
    void derivationTest1()
    {
        List<Monomial> monomialList = new ArrayList<>();
        monomialList.add(new Monomial(3.0, 2.0));
        monomialList.add(new Monomial(2.0, 3.0));
        Polynomial polynomial1 = new Polynomial(monomialList);
        Polynomial actual = PolynomialCalculator.derive(polynomial1);

        List<Monomial> monomialList1 = new ArrayList<>();
        monomialList1.add(new Monomial(2.0, 6.0));
        monomialList1.add(new Monomial(1.0, 6.0));
        Polynomial expected = new Polynomial(monomialList1);

        assertEquals(expected, actual);

    }

    @Test
    void integrateTest1()
    {
        List<Monomial> monomialList = new ArrayList<>();
        monomialList.add(new Monomial(3.0, 2.0));
        monomialList.add(new Monomial(2.0, 3.0));
        Polynomial polynomial1 = new Polynomial(monomialList);
        Polynomial actual = PolynomialCalculator.integrate(polynomial1);

        List<Monomial> monomialList1 = new ArrayList<>();
        monomialList1.add(new Monomial(4.0, 0.5));
        monomialList1.add(new Monomial(3.0, 1.0));
        Polynomial expected = new Polynomial(monomialList1);

        assertEquals(expected, actual);
    }

    @Test
    void divisionTest1()
    {
        List<Monomial> monomialList = new ArrayList<>();
        monomialList.add(new Monomial(3.0, 1.0));
        monomialList.add(new Monomial(2.0, 2.0));
        monomialList.add(new Monomial(0.0, 5.0));
        Polynomial polynomial1 = new Polynomial(monomialList);

        List<Monomial> monomialList1 = new ArrayList<>();
        monomialList1.add(new Monomial(2.0, 1.0));
        monomialList1.add(new Monomial(0.0, 1.0));
        Polynomial polynomial2 = new Polynomial(monomialList1);

        Polynomial actualQ = null;
        Polynomial actualR = null;
        try
        {
            actualQ = PolynomialCalculator.divide(polynomial1, polynomial2).getQuotient();
            actualR = PolynomialCalculator.divide(polynomial1, polynomial2).getReminder();
        } catch (IncompatibleMonomialsForOperationException | MonomialPowerAlreadyExists e)
        {
            e.printStackTrace();
        }

        List<Monomial> monomialList2 = new ArrayList<>();
        monomialList2.add(new Monomial(1.0, 1.0));
        monomialList2.add(new Monomial(0.0, 2.0));
        Polynomial expectedQ = new Polynomial(monomialList2);

        List<Monomial> monomialList3 = new ArrayList<>();
        monomialList3.add(new Monomial(1.0, -1.0));
        monomialList3.add(new Monomial(0.0, 3.0));
        Polynomial expectedR = new Polynomial(monomialList3);

        assertEquals(expectedQ, actualQ);
        assertEquals(expectedR, actualR);

    }

    @Test
    void divisionTest2()
    {
        List<Monomial> monomialList1 = new ArrayList<>();
        monomialList1.add(new Monomial(1.0, 1.0));
        Polynomial polynomial = new Polynomial(monomialList1);

        List<Monomial> monomialList2 = new ArrayList<>();
        monomialList2.add(new Monomial(1.0, 1.0));
        Polynomial polynomial1 = new Polynomial(monomialList2);


        PolynomialPair actual = null;
        try
        {
            actual = PolynomialCalculator.divide(polynomial1, polynomial);
        } catch (IncompatibleMonomialsForOperationException | MonomialPowerAlreadyExists e)
        {
            e.printStackTrace();
        }
        PolynomialPair expected = new PolynomialPair();
        List<Monomial> monomialList = new ArrayList<>();
        monomialList.add(new Monomial(0.0, 1.0));
        expected.setQuotient(new Polynomial(monomialList));

        List<Monomial> monomialList3 = new ArrayList<>();
        monomialList3.add(new Monomial(0.0, 0.0));
        expected.setReminder(new Polynomial(monomialList3));

        assertEquals(expected, actual);

    }

    @Test
    void divisionTest3()
    {
        List<Monomial> monomialList1 = new ArrayList<>();
        monomialList1.add(new Monomial(1.0, 1.0));
        Polynomial polynomial1 = new Polynomial(monomialList1);

        List<Monomial> monomialList2 = new ArrayList<>();
        monomialList2.add(new Monomial(4.0, 1.0));
        Polynomial polynomial2 = new Polynomial(monomialList2);

        List<Monomial> monomialList3 = new ArrayList<>();
        monomialList2.add(new Monomial(0.0, 0.0));
        Polynomial polynomial3 = new Polynomial(monomialList2);

        List<Monomial> monomialList4 = new ArrayList<>();
        monomialList2.add(new Monomial(1.0, 1.0));
        Polynomial polynomial4 = new Polynomial(monomialList2);

        PolynomialPair expected = new PolynomialPair();
        expected.setReminder(polynomial4);
        expected.setQuotient(polynomial3);


        PolynomialPair actual = null;
        try
        {
            actual = PolynomialCalculator.divide(polynomial1, polynomial2);
        } catch (IncompatibleMonomialsForOperationException | MonomialPowerAlreadyExists e)
        {
            fail(e.getMessage());
        }

        assertEquals(expected, actual);
    }
}