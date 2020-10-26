package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

//        Map<String, Integer> map =  new HashMap<>();
//        map.put("0", 1);
//        map.put("const", 199);
//        map.forEach((s, integer) -> System.err.println(s + " " + integer));
//        System.err.println(map.size());
//
//        while (true) {
//            ConsoleReader consoleReader = new ConsoleReader();
//            String input = consoleReader.read();
//
//            PrepareInput prepareInput = new PrepareInput();
//            input = prepareInput.prepare(input);
//
//            InputValidator inputValidator = new InputValidator();
//            boolean validate = inputValidator.validate(input);
//            System.out.println("Validate: " + validate);
//
//            InputParser inputParser = new InputParser();
//            inputParser.parse(input);
//
//            ConsolePrinter consolePrinter = new ConsolePrinter();
//            consolePrinter.printNormalizeInput(inputParser.getClearElements());
//        }

        SpringApplication.run(Application.class, args);
    }
}
