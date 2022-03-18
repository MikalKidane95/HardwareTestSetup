import java.util.ArrayList;
import java.util.List;

class Server {
    private String warehouseId;
    private String manufacturer;
    private String model;
    private String processor;
    private String memory;
    private String PCI;
    private String PCIX;
    private String PCIE;
    public List controllers;
    public List controllerBusTypeList;

    final int WAREHOUSEID = 0;
    final int MANUFACTURER = 1;
    final int MODEL = 2;
    final int PROCESSOR = 3;
    final int MEMORY = 4;
    final int pci = 5;
    final int pcix = 6;
    final int pcie = 7;

    public Server() {
        this.controllers = new ArrayList();
        this.controllerBusTypeList = new ArrayList();

    }

    public Server(String warehouseId, String manufacturer, String model, String processor, String memory, String PCI,
            String PCIX, String PCIE) {
        this.warehouseId = warehouseId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.processor = processor;
        this.memory = memory;
        this.PCI = PCI;
        this.PCIX = PCIX;
        this.PCIE = PCIE;
        this.controllers = new ArrayList();
        this.controllerBusTypeList = new ArrayList();
    }

    public Server(String[] serverInfo) {
        this.warehouseId = serverInfo[WAREHOUSEID];
        this.manufacturer = serverInfo[MANUFACTURER];
        this.model = serverInfo[MODEL];
        this.processor = serverInfo[PROCESSOR];
        this.memory = serverInfo[MEMORY];
        this.PCI = serverInfo[pci];
        this.PCIX = serverInfo[pcix];
        this.PCIE = serverInfo[pcie];
        this.controllers = new ArrayList();
        this.controllerBusTypeList = new ArrayList();

    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getPCI() {
        return PCI;
    }

    public void setPCI(String PCI) {
        this.PCI = PCI;
    }

    public String getPCIX() {
        return PCIX;
    }

    public void setPCIX(String PCIX) {
        this.PCIX = PCIX;
    }

    public String getPCIE() {
        return PCIE;
    }

    public void setPCIE(String PCIE) {
        this.PCIE = PCIE;
    }

    public void addControllers(int numberOfControllers, String controllerType) {
        utility utility = new utility();
        ControllerModels controllerReader = new ControllerModels();
        controllerReader.LoadController();
        System.out.println(
                "You can attach upto : " + numberOfControllers + " " + controllerType
                        + " Controllers. \n How many would you like to add");
        int selectedNumberOfControllers = utility.getInput(numberOfControllers + 1);
        for (int index = 0; index < selectedNumberOfControllers; index++) {
            attachContoller(controllerReader, controllerType, index);
        }
    }

    public void attachContoller(ControllerModels controllerReader,
            String controllerType, int ControllerIdx) {
        utility utility = new utility();
        List Controllers = null;
        switch (controllerType) {
            case "PCI":
                Controllers = controllerReader.getPCIControllerBuses();
                break;
            case "PCIX":
                Controllers = controllerReader.getPCIXControllerBuses();
                break;
            case "PCIE":
                Controllers = controllerReader.getPCIEControllerBuses();
                break;
            default:
                break;
        }
        System.out.println("\nSelect Controller to attach [ Controller #" + ControllerIdx + "]:");
        utility.printChoices(Controllers);
        int selectedInt = utility.getInput(Controllers.size());
        Controller userController = (Controller) Controllers.get(selectedInt);
        Controller newUserController = new Controller(userController);
        HddModels hddReader = new HddModels();
        hddReader.LoadHdd();
        newUserController.addDisks();
        this.controllers.add(newUserController);
        System.out.print("Controller Attached\n");

        // System.out.println("user Controller: " + userController);
    }

    @Override
    public String toString() {
        String serverSpec = "server [warehouseId =" + warehouseId + ", manufacturer =" + manufacturer
                + ", model =" + model + ", processor =" + processor + ", memory =" + memory + ", PCI =" + PCI
                + ", PCIX =" + PCIX + ", PCIE ="
                + PCIE + "]";
        if (this.controllers != null && this.controllers.size() != 0) {
            serverSpec += "\n  List of controllers [" + this.controllers.size() + "]:";

            for (Object controller : this.controllers) {
                serverSpec += "\n\t" + controller.toString();

            }

        } else {
            serverSpec += "\n\t this server does not have any controller";
        }
        return serverSpec;
    }
}