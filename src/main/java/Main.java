import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    String fileName = "countries.csv";
    File file = new File(fileName);

    // nie zmieniaj nic w main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        main.run(scanner);
    }

    void run(Scanner scanner) {
        try {
            if (checkIfFileExist(file)) {
                Map<String, Country> countries = fillMapFromFile();
                String code = readCode(scanner);
                printCountryInfo(code, countries);
            } else {
                System.out.println("Brak pliku countries.csv.");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean checkIfFileExist(File file) {
        return file.exists();
    }

    private void printCountryInfo(String code, Map<String, Country> countries) {
        if (countries.containsKey(code)) {
            Country country = countries.get(code);
            System.out.println(country.getName() + " (" + country.getCode() + ") ma " + country.getPopulation() + " ludności.");
        } else {
            System.out.println("Kod kraju " + code + " nie został znaleziony.");

        }
    }

    private String readCode(Scanner scanner) {
        System.out.println("Podaj kod kraju, o którym chcesz zobaczyć informacje:");
        return scanner.nextLine();
    }

    private Map<String, Country> fillMapFromFile() throws FileNotFoundException {
        Map<String, Country> coutries = new HashMap<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String textLine = scanner.nextLine();
                Country county = createCountryFromTextLine(textLine);
                coutries.put(county.getCode(), county);
            }
        }
        return coutries;
    }

    private Country createCountryFromTextLine(String textLine) {
        String[] lineItems = textLine.split(";");
        String symbol = lineItems[0];
        String name = lineItems[1];
        long population = Long.parseLong(lineItems[2]);
        return new Country(symbol, name, population);
    }

}
