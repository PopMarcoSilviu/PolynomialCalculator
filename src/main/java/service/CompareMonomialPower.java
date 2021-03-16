package service;

import model.Monomial;

import java.util.Comparator;

public class CompareMonomialPower implements Comparator<Monomial>
{

    @Override
    public int compare(Monomial o1, Monomial o2)
    {
        return (int)(o2.getPower()-o1.getPower());
    }
}
