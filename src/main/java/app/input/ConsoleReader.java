package app.input;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleReader {

    public String read() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input: ");
        return in.nextLine();
    }
}
