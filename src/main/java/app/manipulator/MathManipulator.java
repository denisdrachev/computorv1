package app.manipulator;

import app.output.ConsolePrinter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MathManipulator {

    private final ElementsManipulator elementsManipulator;
    private final ConsolePrinter consolePrinter;

    private Double getDiscriminant() {

        Double coef2 = elementsManipulator.getCoefficientInElements(2);
        Double coef1 = elementsManipulator.getCoefficientInElements(1);
        Double coef0 = elementsManipulator.getCoefficientInElements(0);
        Double discriminant = coef1 * coef1 - 4 * coef2 * coef0;
        consolePrinter.printDiscriminantInfo(coef2, coef1, coef0, discriminant);
        consolePrinter.printDiscriminantStatus(discriminant);
        return discriminant;
    }

    public void solution() {

        int polinomLevel = elementsManipulator.getPolinomLevel();

        switch (polinomLevel) {
            case 0:
                solutionPolinom0();
                break;
            case 1:
                solutionPolinom1();
                break;
            case 2:
                solutionPolinom2();
                break;
        }
    }

    private void solutionPolinom0() {
        Double coef0 = elementsManipulator.getCoefficientInElements(0);
        if (coef0 != 0)
            consolePrinter.printNoSolution();
        else
            consolePrinter.printInfinumSolution();
    }

    private void solutionPolinom1() {
        Double coef1 = elementsManipulator.getCoefficientInElements(1);
        Double coef0 = elementsManipulator.getCoefficientInElements(0);

        if (coef1 == 0) {
            log.error(" b = 0!!!!");
            return;
        }

        double solution = (coef0 * -1) / coef1;
        solution = solution == -0 ? 0 : solution;
        consolePrinter.print("(" + coef0 + " * (-1)) / " + coef1 + " = " + solution);
        consolePrinter.printSolutionWithZeroDisc(solution);
    }

    private void solutionPolinom2() {
        double discriminant = getDiscriminant();
        if (discriminant == 0) {
            discIsZero();
        } else if (discriminant > 0) {
            discIsPositive(discriminant);
        } else if (discriminant < 0) {
            discIsNegative(discriminant);
        }
    }

    private void discIsZero() {
        Double coef2 = elementsManipulator.getCoefficientInElements(2);
        Double coef1 = elementsManipulator.getCoefficientInElements(1);

        if (coef2 == 0) {
            log.error(" a = 0!!!!");
            return;
        }
        double solution = (coef1 * -1) / (2 * coef2);
        solution = solution == -0 ? 0 : solution;
        consolePrinter.print("(" + coef1 + " * (-1)) / (2 * " + coef2 + ") = " + solution);
        consolePrinter.printSolutionWithZeroDisc(solution);
    }

    private void discIsNegative(Double discriminant) {
        consolePrinter.printSolutionWithNegativeDisc();

        Double coef2 = elementsManipulator.getCoefficientInElements(2);
        Double coef1 = elementsManipulator.getCoefficientInElements(1);

        consolePrinter.print("((" + coef1 + " * (-1)) + i * sqrt(| " + discriminant + " + |)) / (2 * " + coef2 + ")");
        consolePrinter.print("((" + coef1 + " * (-1)) - i * sqrt(| " + discriminant + " + |)) / (2 * " + coef2 + ")");
    }

    private void discIsPositive(Double discriminant) {
        Double coef2 = elementsManipulator.getCoefficientInElements(2);
        Double coef1 = elementsManipulator.getCoefficientInElements(1);

        if (coef2 == 0) {
            log.error(" a = 0!!!!");
            return;
        }

        Double sqrtValue = sqrt(discriminant);

        double solution1 = ((coef1 * -1) + sqrtValue) / (2 * coef2);
        solution1 = solution1 == -0 ? 0 : solution1;
        double solution2 = ((coef1 * -1) - sqrtValue) / (2 * coef2);
        solution2 = solution2 == -0 ? 0 : solution2;

        consolePrinter.print("((" + coef1 + " * (-1)) + " + sqrtValue + ") / (2 * " + coef2 + ") = " + solution1);
        consolePrinter.print("((" + coef1 + " * (-1)) - " + sqrtValue + ") / (2 * " + coef2 + ") = " + solution2);
        consolePrinter.printSolutionWithPositiveDisc(solution1, solution2);
    }

    //Метод ньютона
    private Double sqrt(Double c) {
        if (c < 0) return Double.NaN;
        Double t = c;
        Double err = 1e-15;
        while (abs(t, c) > err * t)
            t = (c / t + t) / 2.0;
        return t;
    }

    private Double abs(Double t, Double c) {
        if (t - c / t < 0) {
            return (t - c / t) * -1;
        } else {
            return (t - c / t);
        }
    }
}
