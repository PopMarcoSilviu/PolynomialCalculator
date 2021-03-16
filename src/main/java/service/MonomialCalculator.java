package service;

import exceptions.IncompatibleMonomialsForOperationException;
import model.Monomial;
import model.ResultMessageWrapper;
import model.TestInterface;
import validator.OperationValidation;

public class MonomialCalculator
{

    private static final OperationValidation operationValidation = new OperationValidation();

    private static Monomial genericOperation(TestInterface<Double, ResultMessageWrapper<Double, String>> f,
                                             TestInterface<Double, ResultMessageWrapper<Double, String>> f1,
                                             Monomial firstMonomial, Monomial secondMonomial) throws IncompatibleMonomialsForOperationException
    {
        Monomial result = new Monomial(0.0, 0.0);
        var resultMessageWrapper = f.apply(firstMonomial.getCoefficient(), secondMonomial.getCoefficient());
        var resultMessageWrapper1 = f1.apply(firstMonomial.getPower(), secondMonomial.getPower());

        if (!resultMessageWrapper1.getS().isEmpty())
        {
            throw new IncompatibleMonomialsForOperationException(resultMessageWrapper1.getS());
        } else
        {
            result.setPower(resultMessageWrapper1.getT());
        }

        if (!resultMessageWrapper.getS().isEmpty())
        {
            throw new IncompatibleMonomialsForOperationException(resultMessageWrapper.getS());
        } else
        {
            result.setCoefficient(resultMessageWrapper.getT());
        }

        return result;
    }

    public static Monomial add(Monomial monomial1, Monomial monomial2) throws IncompatibleMonomialsForOperationException
    {
        return genericOperation((a, b) -> new ResultMessageWrapper<>(a + b, ""),
                operationValidation.addPower, monomial1, monomial2);
    }

    public static Monomial subtract(Monomial monomial1, Monomial monomial2) throws IncompatibleMonomialsForOperationException
    {
        return genericOperation((a, b) -> new ResultMessageWrapper<>(a - b, ""),
                operationValidation.subtractPower, monomial1, monomial2);
    }

    public static Monomial multiply(Monomial monomial1, Monomial monomial2) throws IncompatibleMonomialsForOperationException
    {

        return genericOperation((a, b) -> new ResultMessageWrapper<>(a * b, ""),
                (a, b) -> new ResultMessageWrapper<>(a + b, ""), monomial1, monomial2);

    }

    public static Monomial divide(Monomial monomial1, Monomial monomial2) throws IncompatibleMonomialsForOperationException
    {
        return genericOperation(operationValidation.divisionCheck,
                (a, b) -> new ResultMessageWrapper<>(a - b, ""), monomial1, monomial2);
    }

    public static Monomial derive(Monomial monomial)
    {
        return new Monomial(monomial.getPower() - 1.0, monomial.getCoefficient() * monomial.getPower());
    }

    public static Monomial integrate(Monomial monomial)
    {
        return new Monomial(monomial.getPower() + 1.0, monomial.getCoefficient() / (1.0 + monomial.getPower()));
    }

}
