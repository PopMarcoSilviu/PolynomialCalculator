package service;

import model.Monomial;
import model.Polynomial;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringToPolynomialTest
{

    @Test
    void transformTest1()
    {
        List<Monomial> monomialList = new ArrayList<>();
        monomialList.add(new Monomial(9.0, 6.0));
        monomialList.add(new Monomial(2.0, 4.0));
        monomialList.add(new Monomial(0.0, 1337.0));
        Polynomial expected = new Polynomial(monomialList);
        String polynomialAsString = "6  x^  9 +4x^  2 +  1337";

        Polynomial actual = null;
        try
        {
            actual = StringToPolynomial.transform(polynomialAsString);
        } catch (Throwable e)
        {
            fail("Error at transforming polynomial to string");
        }

        assertEquals(expected, actual);
    }

    @Test
    void transformTest2()
    {
        List<Monomial> monomialList = new ArrayList<>();
        monomialList.add(new Monomial(7.0, 5.0));
        monomialList.add(new Monomial(3.0, -103.0));
        monomialList.add(new Monomial(30.0, -1.0));
        monomialList.add(new Monomial(0.0, 666.0));
        Polynomial expected = new Polynomial(monomialList);
        String polynomialAsString = "5x ^7-103x  ^3 -  x^30 +666";

        Polynomial actual = null;
        try
        {
            actual = StringToPolynomial.transform(polynomialAsString);
        } catch (Throwable e)
        {
            fail("Error at transforming polynomial to string");
        }

        assertEquals(expected, actual);
    }

    @Test
    void transformTest3()
    {
        List<Monomial> monomialList = new ArrayList<>();
        monomialList.add(new Monomial(1.0, 1.0));
        Polynomial expected = new Polynomial(monomialList);

        String p = "x";
        Polynomial actual = null;
        try
        {
            actual = StringToPolynomial.transform(p);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        assertEquals(expected, actual);
    }
}