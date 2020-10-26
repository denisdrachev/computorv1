package app.input;

import app.exceptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InputValidator {

    public boolean validate(String input) {

        try {
            notEmptyValidation(input);
            correctChars(input);
            equallyExists(input);
//            operationsCheck(input);
        } catch (ValidateException e) {
            log.info("Invalid input. {}", e.getMessage());
            return false;
        }

        return true;
    }

    private void correctChars(String input) throws ValidateException {
        boolean xExists = false;
        for (char c : input.toCharArray()) {
            if (c == 'x' || c == 'X')
                xExists = true;
            if (!Character.isDigit(c) &&
                    c != ' ' &&
                    c != '=' &&
                    c != '*' &&
                    c != '-' &&
                    c != '+' &&
                    c != '/' &&
                    c != '^' &&
                    c != '.' &&
                    c != 'x' &&
                    c != 'X')
                throw new ValidateException();
        }
        if (!xExists)
            throw new ValidateException();
    }

    private void notEmptyValidation(String input) throws ValidateException {
        if (null == input || input.isEmpty())
            throw new ValidateException();
    }

    private void equallyExists(String input) throws ValidateException {
        if (!input.contains("="))
            throw new ValidateException();
        if (input.length() - 1 != input.replaceAll("=", "").length())
            throw new ValidateException();
    }

    private void operationsCheck(String input) throws ValidateException {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '=' || (input.charAt(i) == '-' && i != 0) || input.charAt(i) == '+') {
                if (input.charAt(i - 1) != ' ' || input.charAt(i + 1) != ' ')
                    throw new ValidateException();
            }
        }
        if (!input.contains("="))
            throw new ValidateException();
    }
}
