package app.input;

import app.manipulator.ElementsManipulator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InputParser {

    private final ElementsManipulator elementsManipulator;
    private Integer index = 1;

    public void parse(String input) {
        prepareDirtyElements(input);
    }

    private void prepareDirtyElements(String input) {
        int prevIndex = 0;
        char prevOperation = ' ';
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '=' || input.charAt(i) == '-' || input.charAt(i) == '+') {
                parseElement(input.substring(prevIndex, i).trim(), prevOperation);
                prevIndex = i + 1;
                prevOperation = input.charAt(i);
            }
        }
        parseElement(input.substring(prevIndex).trim(), prevOperation);
        index = 1;
        elementsManipulator.deleteElementsWithZeroCoef();
    }

    private void parseElement(String elementString, Character prevOperation) {
        if (prevOperation == '=')
            index = -1;
        int localIndex = prevOperation == '-' ? -1 : 1;
        int starIndex = elementString.indexOf('*');

        if (starIndex != -1) {
            Double coefficient = Double.parseDouble(elementString.substring(0, starIndex)) * index * localIndex;
            Integer degree = Integer.parseInt(elementString.substring(starIndex + 3));

            elementsManipulator.updateOrCreateElement(coefficient == -0 ? 0 : coefficient, degree);
        }
    }
}
