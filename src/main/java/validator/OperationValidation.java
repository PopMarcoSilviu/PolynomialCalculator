package validator;

import model.ForMessage;
import model.ResultMessageWrapper;
import model.TestInterface;

public class OperationValidation
{
    public TestInterface<Double, ResultMessageWrapper<Double, String>> addPower;
    public TestInterface<Double, ResultMessageWrapper<Double, String>> subtractPower;
    public TestInterface<Double, ResultMessageWrapper<Double, String>> divisionCheck;

    public static ForMessage<Double, ResultMessageWrapper<Double, String>> checkPower =(a, b, s)->
    {
        if (!a.equals(b))
        {
            return new ResultMessageWrapper<>(0.0, s);
        }
        else
        {
            return new ResultMessageWrapper<>(a, "");
        }
    } ;

    public static ForMessage<Double, ResultMessageWrapper<Double, String>> checkDivisionBy0 = (a,b,s)->
    {
        if(b.equals(0.0))
        {
            return new ResultMessageWrapper<>(0.0, s);
        }
        else
        {
            return new ResultMessageWrapper<>(a / b, "");
        }
    } ;

    public OperationValidation()
    {
        addPower = (a,b) -> checkPower.apply(a,b,"Addition of monomials failed");
        subtractPower = (a,b) -> checkPower.apply(a,b,"Subtraction of monomials failed");
        divisionCheck = (a,b) -> checkDivisionBy0.apply(a,b,"Division by 0 not allowed");

    }
}
