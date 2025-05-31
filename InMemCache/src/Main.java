import javafx.util.Pair;
import repository.InMemoryRepository;
import service.InMemoryService;
import models.InputType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository memoryRepository = new InMemoryRepository();
        InMemoryService service = new InMemoryService(memoryRepository);
        while(true)
        {
            System.out.println("Please enter command");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if(Objects.equals(command, "exit"))
            {
                System.out.println("ADIOS");
                break;
            }
            String[] commands = command.split(" ");
            switch (commands[0].toUpperCase()) {
                case InputType.KEYS: {
            if(commands[0].equals(InputType.KEYS.toString().toLowerCase()))
            {
                System.out.println(service.getAllKeys());
            }
            else if(commands[0].equals(InputType.PUT.toString().toLowerCase()))
            {
                List<Pair<String, String>> listOfAttributePairs=new ArrayList<>();
                for (int i=2;i<commands.length;i+=2)
                    listOfAttributePairs.add(new Pair<>(commands[i],commands[i+1]));
                System.out.println(service.put(commands[1],listOfAttributePairs));
            }
        }
    }
}