package model;

import exceptions.IncompatibleMonomialsForOperationException;
import exceptions.MonomialPowerAlreadyExists;
import service.CompareMonomialPower;
import service.MonomialCalculator;
import validator.CheckPowerAlreadyExistsValidation;

import java.util.ArrayList;
import java.util.List;

public class Polynomial
{
    private List<Monomial> monomialList;

    public Polynomial(List<Monomial> monomialList)
    {
        this.monomialList = monomialList;
    }

    public Polynomial()
    {
        monomialList = new ArrayList<>();
    }

    public void addNewMonomialTerm(Monomial monomial) throws MonomialPowerAlreadyExists
    {
        if(CheckPowerAlreadyExistsValidation.exists(monomialList, monomial))
        {
            throw new MonomialPowerAlreadyExists("Monomial already exists in polynomial");
        }
        else
        {
            monomialList.add(monomial);
        }
    }

    public Polynomial sortPowers()
    {
        monomialList.sort(new CompareMonomialPower());
        return this;
    }

    public void addMonomialTerm(Monomial monomial)
    {
            try
            {
                addNewMonomialTerm(monomial);
            }
            catch (MonomialPowerAlreadyExists monomialPowerAlreadyExists)
            {
                for(Monomial itMonomial: monomialList)
                {
                    if(itMonomial.getPower().equals( monomial.getPower() ))
                    {
                        try
                        {
                            monomialList.remove(itMonomial);
                            Monomial aux = MonomialCalculator.add(itMonomial,monomial);
                            if(aux.getCoefficient()!=0)
                            {
                                monomialList.add(MonomialCalculator.add( itMonomial, monomial ));
                            }
                        } catch (IncompatibleMonomialsForOperationException e)
                        {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
    }

    @Override
    public String toString()
    {
        if(monomialList == null || monomialList.size()==0)
        {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for(Monomial item: monomialList)
        {
            if(item.getCoefficient()>0 && monomialList.indexOf(item)!=0)
            {
                result.append("+");
            }
            result.append(item.toString()).append(" ");
        }

        return result.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        Polynomial polynomial = (Polynomial) obj;

        for (var item1:monomialList  )
        {
            boolean exists = false;

            for (var item2: polynomial.getMonomialList() )
            {
                if (item1.equals(item2))
                {
                    exists = true;
                    break;
                }
            }

            if(!exists)
            {
                return  false;
            }
        }

        return true;
    }

    public int getNumberOfMonomials()
    {
        return monomialList.size();
    }

    public List<Monomial> getMonomialList()
    {
        return monomialList;
    }
}
