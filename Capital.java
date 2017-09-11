
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Capital {

    private String capitalName;
    private String countryName;

    public Capital(String filename) {
        ArrayList<String> lineList = readFile(filename);
        String randomLine = getRandomLine(lineList);
        this.capitalName = getRandomName(randomLine, "capital");
        this.countryName = getRandomName(randomLine, "country");
    }

    public static ArrayList<String> readFile(String fileName) {
        ArrayList<String> lineList = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String nextLine;
            while((nextLine = br.readLine()) != null) {
                lineList.add(nextLine);
            }

        } catch (IOException e) {
            System.out.format("File '%s' not found.\n", fileName);
        }
        return lineList;
    }

    public static String getRandomLine(ArrayList<String> lineList) {
        ArrayList<String> capitalAndCountry = new ArrayList<>();
        int randomNum;
        int listSize = lineList.size();
        randomNum = ThreadLocalRandom.current().nextInt(0, listSize + 1);
        String randomLine = lineList.get(randomNum);
        return randomLine;
    }

    public static String getRandomName(String lineToFormat, String typeToCreate) {
        String[] dividedLine = lineToFormat.split("\\|");
        if (typeToCreate.equals("capital")) {
            String capitalName = dividedLine[1].trim();
            return capitalName;
        }
        else if (typeToCreate.equals("country")) {
            String countryName = dividedLine[0].trim();
            return countryName;
        }
        else {
            System.out.println("E1 Wrong choice of name to create.");
            System.exit(0);
            return null;
        }
    }

    public String getCapitalName() {
        return this.capitalName;
    }

    public String getCountryName() {
        return this.countryName;
    }
}
