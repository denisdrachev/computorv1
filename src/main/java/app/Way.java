package app;

import app.input.ConsoleReader;
import app.input.InputParser;
import app.input.InputValidator;
import app.input.PrepareInput;
import app.manipulator.ElementsManipulator;
import app.manipulator.MathManipulator;
import app.output.ConsolePrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Way {

    @Value("${mode.debug}")
    private boolean isDebagMode = true;

    private ConsoleReader consoleReader;
    private PrepareInput prepareInput;
    private InputValidator inputValidator;
    private InputParser inputParser;
    private ConsolePrinter consolePrinter;
    private ElementsManipulator elementsManipulator;
    private MathManipulator mathManipulator;

    public Way(ConsoleReader consoleReader, PrepareInput prepareInput,
               InputValidator inputValidator, InputParser inputParser,
               ConsolePrinter consolePrinter, ElementsManipulator elementsManipulator,
               MathManipulator mathManipulator) {
        this.consoleReader = consoleReader;
        this.prepareInput = prepareInput;
        this.inputValidator = inputValidator;
        this.inputParser = inputParser;
        this.consolePrinter = consolePrinter;
        this.elementsManipulator = elementsManipulator;
        this.mathManipulator = mathManipulator;

        start();
    }

    public void start() {
        while (true) {
            String input = consoleReader.read();
            input = prepareInput.prepare(input);
            boolean validate = inputValidator.validate(input);
            if (!validate) {
                consolePrinter.printInvalidInput();
                ending();
                continue;
            }
            inputParser.parse(input);
            consolePrinter.printNormalizeInput();


            int polinomLevel = elementsManipulator.getPolinomLevel();
            consolePrinter.printPolinomialDegree(polinomLevel);

            if (polinomLevel > 2) {
                consolePrinter.printInvalidDegree();
                ending();
                continue;
            }

            mathManipulator.solution();
            ending();
        }
    }

    private void ending() {
        exitIfNeed();
        elementsManipulator.clearElements();
    }

    private void exitIfNeed() {
        if (!isDebagMode)
            System.exit(0);
    }
}
