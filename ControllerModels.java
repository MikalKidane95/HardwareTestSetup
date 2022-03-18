import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ControllerModels {
    List<Controller> controllerList = new ArrayList<>();

    public void LoadController() {

        this.controllerList = readControllerFromCSV("data/hbas.csv");
    }

    private static List<Controller> readControllerFromCSV(String fileName) {
        List<Controller> controllerList = new ArrayList<>();
        Path filePath = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            line = null;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                Controller controller = createController(attributes);
                controllerList.add(controller);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return controllerList;
    }

    private static Controller createController(String[] hbasInfo) {
        String warehouseId = hbasInfo[0];
        String manufacturer = hbasInfo[1];
        String model = hbasInfo[2];
        String bus = hbasInfo[3];
        String diskPort = hbasInfo[4];
        String diskPorts = hbasInfo[5];

        return new Controller(warehouseId, manufacturer, model, bus, diskPort, diskPorts);
    }

    @Override

    public String toString() {
        int n = 0;
        // print all the servers
        String controllerListString = "";
        for (Controller controller : this.controllerList) {
            controllerListString += n++ + " " + controller + "\n";
        }
        return controllerListString;
    }

    public List getSCSIControllerDisks() {
        List scsiControllerDisks = new ArrayList();
        for (Controller controller : this.controllerList) {
            if (controller.getdiskPortType().trim().equals("SCSI")) {
                scsiControllerDisks.add(controller);
            }
        }
        return (scsiControllerDisks);
    }

    public List getPCIControllerBuses() {
        List PCIControllerBuses = new ArrayList();
        for (Controller controller : this.controllerList) {
            if (controller.getbus().trim().equals("PCI")) {
                PCIControllerBuses.add(controller);
            }
        }
        return (PCIControllerBuses);
    }

    public List getPCIXControllerBuses() {
        List PCIXControllerBuses = new ArrayList();
        for (Controller controller : this.controllerList) {
            if (controller.getbus().trim().equals("PCI-X")) {
                PCIXControllerBuses.add(controller);
            }
        }
        return (PCIXControllerBuses);
    }

    public List getPCIEControllerBuses() {
        List PCIEControllerBuses = new ArrayList();
        for (Controller controller : this.controllerList) {
            if (controller.getbus().trim().equals("PCI-e")) {
                PCIEControllerBuses.add(controller);
            }
        }
        return (PCIEControllerBuses);
    }

}
