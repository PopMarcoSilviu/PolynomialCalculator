package service;

import exceptions.OperandNullException;
import model.Polynomial;

public class CheckIfOperandNull
{
    public static void test(Polynomial polynomial, Polynomial polynomial1) throws OperandNullException
    {
        if(polynomial==null || polynomial1==null || polynomial.getNumberOfMonomials() == 0 || polynomial1.getNumberOfMonomials() ==0)
        {
            throw new OperandNullException("One of the operands is null");
        }

    }
}
