package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest
{
    @Test
    void sortPowersTest1()
    {
        List<Monomial> monomialList = new ArrayList<Monomial>();
        List<Monomial> monomialSortedList = new ArrayList<Monomial>();
        Monomial monomial1 = new Monomial(2.0, -3.0);
        Monomial monomial2 = new Monomial(5.0, 4.0);
        Monomial monomial3 = new Monomial(3.0, 9.0);
        monomialList.add(monomial1);
        monomialList.add(monomial2);
        monomialList.add(monomial3);

        monomialSortedList.add(monomial2);
        monomialSortedList.add(monomial3);
        monomialSortedList.add(monomial1);
        boolean expectedTrue = true;
        Polynomial polynomial = new Polynomial(monomialList);
        polynomial.sortPowers();

        for (int i = 0; i < monomialList.size(); i++)
        {
            if (!monomialList.get(i).getPower().equals(monomialSortedList.get(i).getPower()))
            {
                expectedTrue = false;
                break;
            }
        }

        assertTrue(expectedTrue);
    }

    @Test
    void equalsTest1()
    {
        List<Monomial> monomialList = new ArrayList<>();
        Monomial monomial1 = new Monomial(2.0, -3.0);
        Monomial monomial2 = new Monomial(5.0, 4.0);
        Monomial monomial3 = new Monomial(3.0, 9.0);
        monomialList.add(monomial1);
        monomialList.add(monomial2);
        monomialList.add(monomial3);

        List<Monomial> monomialList2 = new ArrayList<>();
        Monomial monomial12 = new Monomial(2.0, -3.0);
        Monomial monomial22 = new Monomial(5.0, 4.0);
        Monomial monomial32 = new Monomial(3.0, 9.0);
        monomialList2.add(monomial12);
        monomialList2.add(monomial22);
        monomialList2.add(monomial32);

        Polynomial polynomial1 = new Polynomial(monomialList);
        Polynomial polynomial2 = new Polynomial(monomialList2);

        assertEquals(polynomial1, polynomial2, "Polynomials should be equal ");
    }

    @Test
    void addMonomialTermTest1()
    {
        List<Monomial> monomialList1 = new ArrayList<>();
        Monomial monomial1 = new Monomial(2.0, -3.0);
        monomialList1.add(monomial1);
        Polynomial actual = new Polynomial(monomialList1);
        actual.addMonomialTerm(new Monomial(2.0,1.0));

        List<Monomial> monomialList2 = new ArrayList<>();
        Monomial monomial2 = new Monomial(2.0,-2.0);
        monomialList2.add(monomial2);
        Polynomial expected = new Polynomial(monomialList2);

        assertEquals(expected,actual);
    }

}