package validator;

import model.Monomial;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckPowerAlreadyExistsValidationTest
{

    @Test
    void existsTest1()
    {
        List<Monomial> monomialList = new ArrayList<Monomial>();
        Monomial monomial1 = new Monomial(2.0, -3.0);
        Monomial monomial2 = new Monomial(5.0, 4.0);
        Monomial monomial3 = new Monomial(3.0, 9.0);
        Monomial addedMonomial = new Monomial(1.0, 4.0);
        monomialList.add(monomial1);
        monomialList.add(monomial2);
        monomialList.add(monomial3);

        assertFalse(CheckPowerAlreadyExistsValidation.exists(monomialList, addedMonomial));
    }

    @Test
    void existsTest2()
    {
        List<Monomial> monomialList = new ArrayList<Monomial>();
        Monomial monomial1 = new Monomial(2.0, -3.0);
        Monomial monomial2 = new Monomial(5.0, 4.0);
        Monomial monomial3 = new Monomial(3.0, 9.0);
        Monomial addedMonomial = new Monomial(3.0, 4.0);

        monomialList.add(monomial1);
        monomialList.add(monomial2);
        monomialList.add(monomial3);

        assertTrue(CheckPowerAlreadyExistsValidation.exists(monomialList, addedMonomial));

    }
}