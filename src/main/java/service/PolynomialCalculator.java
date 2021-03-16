package service;

import exceptions.IncompatibleMonomialsForOperationException;
import exceptions.MonomialPowerAlreadyExists;
import model.Monomial;
import model.Polynomial;
import model.PolynomialPair;

import java.util.ArrayList;
import java.util.List;

public class PolynomialCalculator
{
    public static Polynomial add(Polynomial polynomial1, Polynomial polynomial2) throws MonomialPowerAlreadyExists
    {
        return addSubOperation(polynomial1, polynomial2, true);
    }

    public static Polynomial subtract(Polynomial polynomial1, Polynomial polynomial2) throws MonomialPowerAlreadyExists
    {
        return addSubOperation(polynomial1, polynomial2, false);
    }

    public static Polynomial multiply(Polynomial polynomial1, Polynomial polynomial2) throws IncompatibleMonomialsForOperationException
    {
        Polynomial result = new Polynomial();

        for (Monomial monomial1 : polynomial1.getMonomialList())
        {
            for (Monomial monomial2 : polynomial2.getMonomialList())
            {
                result.addMonomialTerm(MonomialCalculator.multiply(monomial1, monomial2));
            }
        }

        return result.sortPowers();
    }

    public static PolynomialPair divide(Polynomial polynomial1, Polynomial polynomial2) throws IncompatibleMonomialsForOperationException, MonomialPowerAlreadyExists
    {
        polynomial1.sortPowers();
        polynomial2.sortPowers();
        PolynomialPair polynomialPair = new PolynomialPair();


        while (polynomial1.getNumberOfMonomials() > 0 && polynomial2.getNumberOfMonomials() > 0 &&
                polynomial1.getMonomialList().get(0).getPower() >= polynomial2.getMonomialList().get(0).getPower())
        {
            Monomial currentMaxPower1 = polynomial1.getMonomialList().get(0);
            Monomial currentMaxPower2 = polynomial2.getMonomialList().get(0);

            Monomial Q = MonomialCalculator.divide(currentMaxPower1, currentMaxPower2);
            polynomialPair.getQuotient().addNewMonomialTerm(Q);

            Polynomial temp = new Polynomial();
            for (var item : polynomial2.getMonomialList())
            {
                temp.addNewMonomialTerm(MonomialCalculator.multiply(item, Q));
            }
            polynomial1 = PolynomialCalculator.subtract(polynomial1, temp);

        }
        polynomialPair.setReminder(polynomial1);
        changeNothingToZero(polynomialPair);

        return polynomialPair;
    }

    private static Polynomial addSubOperation(Polynomial polynomial1, Polynomial polynomial2, boolean addition) throws MonomialPowerAlreadyExists
    {
        Polynomial result = new Polynomial();

        for (Monomial monomial1 : polynomial1.getMonomialList())
        {
            result.addNewMonomialTerm(monomial1);
        }

        for (Monomial monomial2 : polynomial2.getMonomialList())
        {
            if (addition)
            {
                result.addMonomialTerm(monomial2);
            } else
            {
                result.addMonomialTerm(new Monomial(monomial2.getPower(), -monomial2.getCoefficient()));
            }
        }

        return result.sortPowers();
    }

    public static Polynomial integrate(Polynomial polynomial)
    {
        List<Monomial> list = new ArrayList<>();

        for (var item : polynomial.getMonomialList())
        {
            list.add(MonomialCalculator.integrate(item));
        }

        return new Polynomial(list).sortPowers();
    }

    public static Polynomial derive(Polynomial polynomial)
    {
        List<Monomial> list = new ArrayList<>();

        for (var item : polynomial.getMonomialList())
        {
            list.add(MonomialCalculator.derive(item));
        }

        return new Polynomial(list).sortPowers();
    }

    private static void changeNothingToZero(PolynomialPair polynomialPair) throws MonomialPowerAlreadyExists
    {
        if(polynomialPair.getQuotient().getNumberOfMonomials() ==0)
        {
            polynomialPair.getQuotient().addNewMonomialTerm(new Monomial(0.0,0.0));
        }

        if(polynomialPair.getReminder().getNumberOfMonomials() ==0)
        {
            polynomialPair.getReminder().addNewMonomialTerm(new Monomial(0.0,0.0));
        }
    }
}
