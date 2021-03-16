package service;

import model.Monomial;
import model.Polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringToPolynomial
{

    public static Polynomial transform(String stringPolynomial)
    {
            List<Monomial> partialResult = new ArrayList<>();
        List<String> monomialStrings;
        String pattern = "[+-]?[^-+]+";
        stringPolynomial = formatString(stringPolynomial);
        monomialStrings = Pattern.compile(pattern).matcher(stringPolynomial).results()
                .map(MatchResult::group).collect(Collectors.toList());

        for (var item : monomialStrings)
        {
            List<Integer> temp = new ArrayList<>();
            item = item.replaceAll("([+-])[ ]+([0-9])", "$1$2");
            item = item.replaceAll("\\^", " ^ ");
            Scanner scanner = new Scanner(item);
            boolean power = false;

            while (scanner.hasNext())
            {
                if (scanner.hasNextInt())
                {
                    temp.add(scanner.nextInt());
                } else
                {
                    scanner.next();
                    power = true;
                }
            }

            Monomial monomial = new Monomial(0.0, 0.0);

            if (temp.size() > 1)
            {
                monomial.setPower((double)temp.get(1));
                monomial.setCoefficient((double)temp.get(0));
            } else
            {
                if (power)
                {
                    monomial.setCoefficient(1.0);
                    monomial.setPower((double) temp.get(0));
                } else
                {
                    monomial.setCoefficient((double)temp.get(0));
                    monomial.setPower(0.0);
                }
            }
            partialResult.add(monomial);
        }
        return new Polynomial(partialResult);
    }

    private static String formatString(String stringPolynomial)
    {
        stringPolynomial = stringPolynomial.replaceAll("([x])[ ]*([-+])", "$1^1$2");
        stringPolynomial = stringPolynomial.replaceAll("([-+])[ ]*([x])","$11$2" );
        stringPolynomial= stringPolynomial.replaceAll("x[ ]*([+-])","x ^ 1$1") ;
        stringPolynomial = stringPolynomial.replaceAll("x[ ]*$", "x ^ 1");
        stringPolynomial = stringPolynomial.replaceFirst("^x", "1x" );
        stringPolynomial = stringPolynomial.replaceAll("[x]", " ");

        return stringPolynomial;
    }
}

