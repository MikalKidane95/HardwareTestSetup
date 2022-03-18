import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HddModels {
    public List<Hdd> hddList = new ArrayList<>();

    public void LoadHdd() {

        this.hddList = readhddFromCSV("data/hdds.csv");
    }

    private static List<Hdd> readhddFromCSV(String fileName) {
        List<Hdd> hddList = new ArrayList<>();
        Path filePath = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Hdd hdd = createhdd(attributes);
                hddList.add(hdd);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            // TODO: handle exception
        }
        return hddList;
    }

    private static Hdd createhdd(String[] newHDD) {
        String warehouseId = newHDD[0];
        String manufacturer = newHDD[1];
        String model = newHDD[2];
        String size = newHDD[3];
        String port = newHDD[4];

        return new Hdd(warehouseId, manufacturer, model, size, port);
    }

    @Override

    public String toString() {
        int n = 0;
        // print all the servers
        String hddListString = "";
        for (Hdd Hd : this.hddList) {
            hddListString += n++ + " " + Hd + "\n";
        }
        return hddListString;
    }

    public List getSataDisks() {
        List sataDisks = new ArrayList();
        for (Hdd hdd : this.hddList) {
            // System.out.println(hdd.getPort());
            if (hdd.getPort().trim().equals("SATA")) {
                sataDisks.add(hdd);
            }
        }
        return (sataDisks);
    }

    public List getScsiDisks() {
        List scsiDisks = new ArrayList();
        for (Hdd hdd : this.hddList) {
            // System.out.println(hdd.getPort());
            if (hdd.getPort().trim().equals("SCSI")) {
                scsiDisks.add(hdd);
            }
        }
        return (scsiDisks);
    }

    public List getSasAndSataDisks() {
        List sasAndSataDisks = new ArrayList();
        for (Hdd hdd : this.hddList) {
            // System.out.println(hdd.getPort());
            if (hdd.getPort().trim().equals("SAS") || hdd.getPort().trim().equals("SATA")) {
                sasAndSataDisks.add(hdd);
            }
        }
        return (sasAndSataDisks);
    }

}
