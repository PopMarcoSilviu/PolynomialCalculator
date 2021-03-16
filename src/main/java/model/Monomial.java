package model;

import constants.Constants;

public class Monomial
{
    private Double power;
    private Double coefficient;

    public Monomial(Double power, Double coefficient)
    {
        this.power = power;
        this.coefficient = coefficient;
    }

    @Override
    public String toString()
    {
        if(coefficient.equals(0.0))
        {
            return "";
        }
        String print = " x" +" ^ " + power.toString();
        if(power.equals(0.0))
        {
            print = "";
        }
        if(power.equals(1.0))
        {
            print = " x";
        }
        return  coefficient.toString() + print  ;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj.getClass().equals(Monomial.class))
        {
            Monomial monomial = (Monomial) obj;
            boolean coefficientEquality = Math.abs(Double.compare(this.coefficient,monomial.coefficient)) < Constants.EPSILON;
            boolean powerEquality = Math.abs(Double.compare(this.power,monomial.power)) < Constants.EPSILON;
            return coefficientEquality && powerEquality;
        }
        else
        {
            return false;
        }
    }

    public Double getPower()
    {
        return power;
    }

    public void setPower(Double power)
    {
        this.power = power;
    }

    public Double getCoefficient()
    {
        return coefficient;
    }

    public void setCoefficient(Double coefficient)
    {
        this.coefficient = coefficient;
    }
}
