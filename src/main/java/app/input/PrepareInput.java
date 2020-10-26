package app.input;

import org.springframework.stereotype.Service;

@Service
public class PrepareInput {

    public String prepare(String input) {
        return input.replaceAll(" ", "");
    }
}
