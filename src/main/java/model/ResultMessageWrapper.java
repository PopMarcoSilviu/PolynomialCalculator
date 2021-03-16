package model;

public class ResultMessageWrapper <T, S>
{
    private T t;
    private S s;

    public ResultMessageWrapper(T t, S s)
    {
        this.t = t;
        this.s = s;
    }

    @Override
    public String toString()
    {
        return "ResultMessageWrapper{" +
                "t=" + t +
                ", s=" + s +
                '}';
    }

    public T getT()
    {
        return t;
    }

    public void setT(T t)
    {
        this.t = t;
    }

    public S getS()
    {
        return s;
    }

    public void setS(S s)
    {
        this.s = s;
    }
}
