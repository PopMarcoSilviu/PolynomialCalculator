package controller;

import exceptions.OperandNullException;
import exceptions.IncompatibleMonomialsForOperationException;
import exceptions.MonomialPowerAlreadyExists;
import model.Operations;
import model.Polynomial;
import model.PolynomialPair;
import model.State;
import service.CheckIfOperandNull;
import service.PolynomialCalculator;
import service.StringToPolynomial;
import view.MainMenu;

public class Controller
{
    private final MainMenu mainMenu = new MainMenu();
    private final State state = new State();

    public Controller()
    {
        mainMenu.setResizable();
        mainMenu.setActionListenerForInput(e -> inputOutput(0));
        mainMenu.setActionListenerForNumberButtons(e -> numberPressed());
        mainMenu.setActionListenerForMultiplication(e -> multiplicationAction());
        mainMenu.setActionListenerForAddition(e -> additionAction());
        mainMenu.setActionListenerForSubtraction(e -> subtractionAction());
        mainMenu.setActionListenerForIntegration(e -> integrationAction());
        mainMenu.setActionListenerForDerivation(e -> derivationAction());
        mainMenu.setActionListenerForDivision(e -> divisionAction());
        mainMenu.setActionListenerForReset(e -> resetAction());
        mainMenu.setActionListenerForDeleteLastCharacter(e -> deleteLastCharacterAction());
    }

    private void deleteLastCharacterAction()
    {
        int select = mainMenu.getSelected();
        String s = mainMenu.getInput(select);

        if(s.length()>0)
        {
            mainMenu.setInputTextField(s.substring(0, s.length() - 1), select);
        }


    }

    private void resetAction()
    {
        state.resetState();
        mainMenu.setInputTextField("", 0);
        mainMenu.setInputTextField("", 2);

    }

    private void divisionAction()
    {
        state.setOperationSelectedLast(Operations.Division);
        inputOutput(1);
    }

    private void derivationAction()
    {
        state.setOperationSelectedLast(Operations.Derivation);
        inputOutput(1);
    }

    private void integrationAction()
    {
        state.setOperationSelectedLast(Operations.Integration);
        inputOutput(1);
    }

    private void subtractionAction()
    {
        state.setOperationSelectedLast(Operations.Subtraction);
        inputOutput(1);
    }

    private void multiplicationAction()
    {
        state.setOperationSelectedLast(Operations.Multiplication);
        inputOutput(1);
    }

    private void additionAction()
    {
        state.setOperationSelectedLast(Operations.Addition);
        inputOutput(1);
    }

    private void numberPressed()
    {
        inputOutput(2);
    }

    public void inputOutput(int mode)
    {
        mainMenu.setSelectedTextField();

        if (mode == 0)
        {
            mainMenu.setTextForSelectedInputTextField(mainMenu.getStringForPressedButton());
        }
        if (mode == 2)
        {
            mainMenu.setTextForSelectedInputTextField(mainMenu.getStringForPressedNumber());
        }

        try
        {
            state.setInput1(StringToPolynomial.transform(mainMenu.getInput(0)));
            state.setInput2(StringToPolynomial.transform(mainMenu.getInput(2)));
        } catch (Throwable e)
        {
            mainMenu.setOutput("Cannot solve this", 1);
        }

        if (!state.getOperationSelectedLast().equals(Operations.filler1))
        {
            setOutputState();
        }

        setOutput(state.isException());

    }

    private void setOutput(boolean exceptionCase)
    {
        if (!exceptionCase)
        {
            if (state.getOutput1().getNumberOfMonomials() == 0 )
            {
                mainMenu.setOutput("0.0", 1);
            } else
            {
                mainMenu.setOutput(state.getOutput1().toString(), 1);

                if (state.getOperationSelectedLast().equals(Operations.Division))
                {
                    if (state.getOutput2().getNumberOfMonomials() == 0 && !state.isException())
                    {
                        mainMenu.setOutput("0.0", 3);
                    } else
                    {
                        mainMenu.setOutput(state.getOutput2().toString(), 3);
                    }
                } else
                {
                    mainMenu.setOutput(state.getOutput2().toString(), 3);
                }
            }

        } else
        {
            mainMenu.setOutput(state.getErrorMessage(), 1);
            mainMenu.setOutput("", 3);
        }
    }

    private void setOutputState()
    {
        try
        {
            state.setException(false);

            if(!state.getOperationSelectedLast().equals(Operations.Derivation) &&
                    !state.getOperationSelectedLast().equals(Operations.Integration))
            {
                CheckIfOperandNull.test(state.getInput1(), state.getInput2());
            }

            setStateForSelectedOperation();

        } catch (IncompatibleMonomialsForOperationException | MonomialPowerAlreadyExists | OperandNullException e)
        {
            state.setException(true);
            state.setErrorMessage(e.getMessage());
        }

        if(!state.getOperationSelectedLast().equals(Operations.Division))
        {
            state.setOutput2(new Polynomial());
        }
    }


    private void setStateForSelectedOperation() throws IncompatibleMonomialsForOperationException, MonomialPowerAlreadyExists
    {
        switch (state.getOperationSelectedLast())
        {
            case Multiplication:
                state.setOutput1(PolynomialCalculator.multiply(state.getInput1(), state.getInput2()));
                break;
            case Addition:
                state.setOutput1(PolynomialCalculator.add(state.getInput1(), state.getInput2()));
                break;
            case Subtraction:
                state.setOutput1(PolynomialCalculator.subtract(state.getInput1(), state.getInput2()));
                break;
            case Integration:
                state.setOutput1(PolynomialCalculator.integrate(state.getInput1()));
                break;
            case Derivation:
                state.setOutput1(PolynomialCalculator.derive(state.getInput1()));
                break;
            case Division:
                PolynomialPair pair = PolynomialCalculator.divide(state.getInput1(), state.getInput2());
                state.setOutput1(pair.getQuotient());
                state.setOutput2(pair.getReminder());
                break;

        }
    }
}
