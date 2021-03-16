package view;

import model.Operations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenu
{
    private final int xSize = 1000;
    private final int ySize = 600;
    private final float superThickBorderThickness = 5.0f;
    private final List<String> listOfOperatorsSymbols = new ArrayList<>(Arrays.
            asList("x", "+", "-", "*", "^", "space",
                    "Integration", "Division", "Addition", "Multiplication", "Subtraction", "Derivation"));
    private int selectedValue = 0;
    private List<JButton> numbersButtonList;
    private JFrame mainFrame;
    private JPanel textPanel;
    private JPanel buttonsPanel;
    private JPanel numbersButtonsPanel;
    private List<JTextField> operandsAndResults;
    private List<JButton> buttonList;
    private JTextField selected;
    private JButton reset;
    private JButton deleteLastCharacter;

    public MainMenu()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainFrame = new JFrame();
        mainFrame.setBackground(Color.black);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Polynomial Calculator");
        mainFrame.getContentPane().setLayout(new GridLayout(3,1));
        mainFrame.setSize(new Dimension(xSize, ySize));

        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

        mainFrame.setLocation((screenDimension.width - xSize) / 2, (screenDimension.height - ySize) / 2);


        textPanel = new JPanel(new GridLayout(2, 2));
        buttonsPanel = new JPanel(new GridLayout(2, 6));
        numbersButtonsPanel = new JPanel(new GridLayout(2, 5));

        textPanel.setBackground(Color.black);
        numbersButtonsPanel.setBackground(Color.black);
        numbersButtonsPanel.setBackground(Color.black);
        
        operandsAndResults = new ArrayList<>();
        buttonList = new ArrayList<>();
        numbersButtonList = new ArrayList<>();

        reset = new JButton("reset");
        reset.setBackground(Color.black);
        deleteLastCharacter = new JButton("<--");
        deleteLastCharacter.setBackground(Color.black);
        for (int i = 0; i < 4; i++)
        {
            JTextField jTextField = new JTextField();
            jTextField.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(superThickBorderThickness)));
            operandsAndResults.add(jTextField);
            if (i % 2 == 1)
            {
                jTextField.setEditable(false);
            }
        }

        for (int i = 0; i < 10; i++)
        {
            JButton jButton = new JButton(Integer.toString(i));
            jButton.setBackground(Color.black);
            numbersButtonList.add(jButton);
        }

        numbersButtonList.add(deleteLastCharacter);
        numbersButtonList.add(reset);

        selected = operandsAndResults.get(0);

        for (var item : operandsAndResults)
        {
            textPanel.add(item);
        }

        for (int i = 0; i < 12; i++)
        {
            JButton jButton = new JButton();
            jButton.setBackground(Color.black);
            jButton.setText(listOfOperatorsSymbols.get(i));
            buttonList.add(jButton);
        }

        setFontSize((xSize - 400) * (ySize) / 20000);

        for (var item : buttonList)
        {
            buttonsPanel.add(item);
        }

        for (var item : numbersButtonList)
        {
            numbersButtonsPanel.add(item);
        }

        mainFrame.add(textPanel);
        mainFrame.add(buttonsPanel);
        mainFrame.add(numbersButtonsPanel);
        mainFrame.setVisible(true);

    }

    public void setFontSize(int fontSize)
    {
        Font font = new Font("Arial", Font.BOLD, fontSize);
        for (var item : buttonList)
        {
            item.setFont(font);
        }

        for (var item : operandsAndResults)
        {
            item.setFont(font);
        }

        for (var item : numbersButtonList)
        {
            item.setFont(font); 
        }
    }

    public void setResizable()
    {
        for (var item : buttonList)
        {
            item.addComponentListener(new ComponentAdapter()
            {
                public void componentResized(ComponentEvent componentEvent)
                {
                    Dimension currSize = mainFrame.getSize();

                    setFontSize((currSize.height) * (currSize.width - 400) / 20000);
                }
            });
        }
    }

    public void setActionListenerForInput(ActionListener e)
    {

        for (int i = 0; i < 6; i++)
        {
            buttonList.get(i).addActionListener(e);
        }
    }

    public String getStringForPressedButton()
    {
        for (int i = 0; i < buttonList.size(); i++)
        {
            if (buttonList.get(i).getModel().isArmed())
            {
                if (i == Operations.space.ordinal())
                {
                    return " ";
                }
                return listOfOperatorsSymbols.get(i);
            }
        }
        return "";
    }

    public void setActionListenerForNumberButtons(ActionListener e)
    {
        for (var item : numbersButtonList)
        {
            item.addActionListener(e);
        }
    }

    public String getStringForPressedNumber()
    {
        for (int i = 0; i < 10; i++)
        {
            if (numbersButtonList.get(i).getModel().isArmed())
            {
                return Integer.toString(i);
            }
        }
        return "";
    }


    public void setTextForSelectedInputTextField(String value)
    {
        selected.setText(selected.getText() + value);
    }

    public void setSelectedTextField()
    {
        JTextField jTextField1 = operandsAndResults.get(0);
        JTextField jTextField2 = operandsAndResults.get(2);

        jTextField1.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                selected = jTextField1;
                selectedValue = 0;
            }

            @Override
            public void focusLost(FocusEvent e)
            {

            }
        });

        jTextField2.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                selected = jTextField2;
                selectedValue = 2;
            }

            @Override
            public void focusLost(FocusEvent e)
            {

            }
        });

    }

    public int getSelected()
    {
        return selectedValue;
    }

    public String getInput(int textFieldIndex)
    {
        return operandsAndResults.get(textFieldIndex).getText();
    }

    public void setOutput(String output, int textFieldIndex)
    {
        operandsAndResults.get(textFieldIndex).setText(output);
    }

    public void setActionListenerForMultiplication(ActionListener e)
    {
        buttonList.get(Operations.Multiplication.ordinal()).addActionListener(e);
    }

    public void setActionListenerForAddition(ActionListener e)
    {
        buttonList.get(Operations.Addition.ordinal()).addActionListener(e);
    }

    public void setActionListenerForSubtraction(ActionListener e)
    {
        buttonList.get(Operations.Subtraction.ordinal()).addActionListener(e);
    }

    public void setActionListenerForIntegration(ActionListener e)
    {
        buttonList.get(Operations.Integration.ordinal()).addActionListener(e);
    }

    public void setActionListenerForDerivation(ActionListener e)
    {
        buttonList.get(Operations.Derivation.ordinal()).addActionListener(e);
    }

    public void setActionListenerForDivision(ActionListener e)
    {
        buttonList.get(Operations.Division.ordinal()).addActionListener(e);
    }

    public void setActionListenerForReset(ActionListener e)
    {
        reset.addActionListener(e);
    }

    public void setActionListenerForDeleteLastCharacter(ActionListener e)
    {
        deleteLastCharacter.addActionListener(e);
    }

    public void setInputTextField(String value, int index)
    {
        operandsAndResults.get(index).setText(value);
    }
}
