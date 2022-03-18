import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LaptopModels {
    List<Laptop> laptopList = new ArrayList<>();

    public void LoadLaptops() {

        this.laptopList = readLaptopsFromCSV("data/laptops.csv");
    }

    private static List<Laptop> readLaptopsFromCSV(String fileName) {
        List<Laptop> laptopList = new ArrayList<>();
        Path filePath = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            line = null;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                Laptop laptop = createLaptop(attributes);
                laptopList.add(laptop);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return laptopList;
    }

    private static Laptop createLaptop(String[] laptopInfo) {
        String warehouseId = laptopInfo[0];
        String manufacturer = laptopInfo[1];
        String model = laptopInfo[2];
        String processor = laptopInfo[3];
        String memory = laptopInfo[4];
        String diskPorts = laptopInfo[5];
        return new Laptop(warehouseId, manufacturer, model, processor, memory, diskPorts);
    }

    @Override

    public String toString() {
        int n = 0;
        String laptopListString = "";
        for (Laptop Lp : this.laptopList) {
            laptopListString += n++ + " " + Lp + "\n";
        }
        return laptopListString;
    }
}
