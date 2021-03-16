package model;

public class State
{
    private Polynomial input1;
    private Polynomial input2;
    private Polynomial output1;
    private Polynomial output2;
    private Operations operationSelectedLast;
    private boolean exception;
    private String errorMessage;

    public State()
    {
        errorMessage = "";
        exception = true;
        output1 = new Polynomial();
        output2 = new Polynomial();
        operationSelectedLast = Operations.filler1;
    }

    public void resetState()
    {
        errorMessage = "";
        exception = true;
        output1.getMonomialList().clear();
        output2.getMonomialList().clear();
        input1.getMonomialList().clear();
        input2.getMonomialList().clear();
        operationSelectedLast = Operations.filler1;
    }

    public Polynomial getInput1()
    {
        return input1;
    }

    public void setInput1(Polynomial input1)
    {
        this.input1 = input1;
    }

    public Polynomial getInput2()
    {
        return input2;
    }

    public void setInput2(Polynomial input2)
    {
        this.input2 = input2;
    }

    public Polynomial getOutput1()
    {
        return output1;
    }

    public void setOutput1(Polynomial output1)
    {
        this.output1 = output1;
    }

    public Polynomial getOutput2()
    {
        return output2;
    }

    public void setOutput2(Polynomial output2)
    {
        this.output2 = output2;
    }

    public Operations getOperationSelectedLast()
    {
        return operationSelectedLast;
    }

    public void setOperationSelectedLast(Operations operationSelectedLast)
    {
        this.operationSelectedLast = operationSelectedLast;
    }

    public boolean isException()
    {
        return exception;
    }

    public void setException(boolean exception)
    {
        this.exception = exception;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
