package validator;

import model.Monomial;

import java.util.List;

public class CheckPowerAlreadyExistsValidation
{
    public static boolean exists(List<Monomial> monomialList, Monomial monomial)
    {
        Monomial result;
        result = monomialList.stream().filter(monomialPower ->
                monomial.getPower().
                        equals(monomialPower.getPower())).findAny().orElse(null);

        return result != null;
    }
}
