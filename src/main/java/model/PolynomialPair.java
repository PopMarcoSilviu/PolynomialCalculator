package model;

public class PolynomialPair
{
    private Polynomial quotient;
    private Polynomial reminder;


    public PolynomialPair()
    {
        quotient = new Polynomial();
        reminder = new Polynomial();
    }

    public Polynomial getQuotient()
    {
        return quotient;
    }

    public void setQuotient(Polynomial quotient)
    {
        this.quotient = quotient;
    }

    public Polynomial getReminder()
    {
        return reminder;
    }

    public void setReminder(Polynomial reminder)
    {
        this.reminder = reminder;
    }

    @Override
    public String toString()
    {
        String result;
        result ="Q: "+ this.quotient.toString() + "||" + "R:" + this.reminder.toString();
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!obj.getClass().equals(this.getClass()))
        {
            return false;
        }

        PolynomialPair pair = (PolynomialPair) obj;

        return pair.getReminder().equals(this.reminder) && pair.getQuotient().equals(this.quotient);
    }
}
