package app.output;

import app.manipulator.ElementsManipulator;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Service
public class ConsolePrinter {

    private String REDUCED_FORM = "Reduced form: ";
    private String DISCRIMINANT_EQUAL_ZERO = "-> DISCRIMINANT equal zero";
    private String DISCRIMINANT_GREATER_THEN_ZERO = "-> DISCRIMINANT greater then zero";
    private String DISCRIMINANT_LESS_THEN_ZERO = "-> DISCRIMINANT less then zero";
    private String POLINOMIAL_DEGREE = "Polynomial degree: ";
    private String POSITIVE_DISC_SOLUTION = "Discriminant is strictly positive, the two solutions are:";
    private String NEGATIVE_DISC_SOLUTION = "Discriminant is strictly negative, the two complex solutions";
    private String ONE_SOLUTION_TEXT = "The solution is:";
    private String INFINUM_SOLUTION_TEXT = "An infinite number of solutions";
    private String NO_SOLUTION_TEXT = "No solutions";
    private String INVALID_INPUT = "INVALID INPUT";
    private String DISCRIMINANT_TEXT = "DISCRIMINANT: ";
    private String INVALID_DEGREE = "The polynomial degree is strictly greater than 2, I can't solve.";
    final private ElementsManipulator elementsManipulator;
    DecimalFormat format = new DecimalFormat("#.##########");


    public ConsolePrinter(ElementsManipulator elementsManipulator) {
        this.elementsManipulator = elementsManipulator;
        DecimalFormatSymbols dfs = format.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        format.setDecimalFormatSymbols(dfs);
    }

    public void printNormalizeInput() {

        StringBuilder stringBuilder = new StringBuilder().append(REDUCED_FORM);

        elementsManipulator.getClearElements().forEach(element -> {
            stringBuilder
                    .append(stringBuilder.length() != REDUCED_FORM.length() && element.getCoefficient() >= 0 ? "+ " : "")
                    .append(format.format(element.getCoefficient()))
                    .append(" * X^")
                    .append(element.getDegree())
                    .append(" ");
        });
        if (stringBuilder.length() == REDUCED_FORM.length())
            stringBuilder.append("0 ");
        stringBuilder.append("= 0");
        print(stringBuilder.toString());
    }

    public void print(String text) {
        System.out.println(text);
    }

    public void printInvalidInput() {
        print(INVALID_INPUT);
    }

    public void printDiscriminantStatus(Double discriminant) {
        if (discriminant == 0) {
            print(DISCRIMINANT_EQUAL_ZERO);
        } else if (discriminant > 0) {
            print(DISCRIMINANT_GREATER_THEN_ZERO);
        } else {
            print(DISCRIMINANT_LESS_THEN_ZERO);
        }
    }

    public void printDiscriminantInfo(Double coef2, Double coef1, Double coef0, Double discriminant) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(DISCRIMINANT_TEXT)
                .append(format.format(coef1))
                .append(" ^ 2 - 4 * ")
                .append(format.format(coef2))
                .append(" * ")
                .append(format.format(coef0))
                .append(" = ")
                .append(format.format(discriminant));
        print(stringBuilder.toString());
    }

    public void printSolutionWithZeroDisc(Double solution) {
        print(ONE_SOLUTION_TEXT);
        //TODO мб изменить
        print(format.format(solution));
    }

    public void printInfinumSolution() {
        print(INFINUM_SOLUTION_TEXT);
    }

    public void printNoSolution() {
        print(NO_SOLUTION_TEXT);
    }

    public void printSolutionWithPositiveDisc(Double solution, Double solution2) {
        print(POSITIVE_DISC_SOLUTION);
        //TODO мб изменить
        print(format.format(solution));
        print(format.format(solution2));
    }

    public void printSolutionWithNegativeDisc() {
        print(NEGATIVE_DISC_SOLUTION);
    }

    public void printPolinomialDegree(int polinomialLevel) {
        print(POLINOMIAL_DEGREE + polinomialLevel);
    }

    public void printInvalidDegree() {
        print(INVALID_DEGREE);
    }
}
