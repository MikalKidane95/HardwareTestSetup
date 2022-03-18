import java.util.ArrayList;
import java.util.List;

class Controller implements Cloneable {
    public static final String PCIList = null;
    private String warehouseId;
    private String manufacturer;
    private String model;
    private String bus;
    private String diskPortType;
    public String diskPorts;
    public List<Hdd> controllerHarddisks;

    public Controller() {
        this.controllerHarddisks = new ArrayList<Hdd>();
    }

    public Controller(Controller controller) {
        this.warehouseId = controller.getWarehouseId();
        this.manufacturer = controller.getManufacturer();
        this.model = controller.getModel();
        this.bus = controller.getbus();
        this.diskPortType = controller.getdiskPortType();
        this.diskPorts = controller.diskPorts;
        this.controllerHarddisks = new ArrayList<Hdd>();
    }

    public Controller(String warehouseId, String manufacturer, String model, String bus, String diskPort,
            String diskPorts) {
        this.warehouseId = warehouseId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.bus = bus;
        this.diskPortType = diskPort;
        this.diskPorts = diskPorts;
        this.controllerHarddisks = new ArrayList<Hdd>();
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

    public String getbus() {
        return bus;
    }

    public void setbus(String bus) {
        this.bus = bus;
    }

    public String getdiskPortType() {
        return diskPortType;
    }

    public void setdiskPortType(String diskPort) {
        this.diskPortType = diskPort;
    }

    public int getdiskPorts() {
        return Integer.valueOf(this.diskPorts);
    }

    public void setdiskPorts(String diskPorts) {
        this.diskPorts = diskPorts;
    }

    public void addDisks() {
        utility utility = new utility();
        System.out.println(
                "You can attach upto : " + this.getdiskPorts() + " " + this.getdiskPortType()
                        + " harddisks to your controller. \n How many would you like to add");
        int numberOfHdds = utility.getInput(this.getdiskPorts() + 1);
        for (int index = 0; index < numberOfHdds; index++) {
            attachDisks(index);
        }

    }

    private void attachDisks(int DiskIdx) {
        utility utility = new utility();
        HddModels hddReader = new HddModels();
        hddReader.LoadHdd();
        List harddisksList = null;
        switch (this.getdiskPortType()) {
            case "SAS":
                harddisksList = hddReader.getSasAndSataDisks();
                break;
            case "SATA":
                harddisksList = hddReader.getSataDisks();
                break;
            case "SCSI":
                harddisksList = hddReader.getScsiDisks();
                break;
            default:
                break;
        }
        System.out.println("\nSelect Disk to attach [ Disk #" + DiskIdx + "]:");

        utility.printChoices(harddisksList);

        int selectedDiskModelInt = utility.getInput(harddisksList.size());
        Hdd selectDisk = (Hdd) harddisksList.get(selectedDiskModelInt);
        this.controllerHarddisks.add(new Hdd(selectDisk));
        System.out.println("Disk Attached \n");

    }

    @Override
    public String toString() {
        String controllerSpec = "hbas [warehouseId =" + warehouseId + ", manufacturer =" + manufacturer
                + ", model =" + model + ", bus =" + bus + ", diskPortType =" + diskPortType + ", diskPorts ="
                + diskPorts
                + "]";
        controllerSpec += "\n\t The Disks attached to this Controller are [" + this.controllerHarddisks.size() + "] :";
        if (this.controllerHarddisks != null && this.controllerHarddisks.size() != 0) {
            for (Hdd hdd : this.controllerHarddisks) {
                controllerSpec += "\n\t\t" + hdd.toString();
            }

        } else {
            controllerSpec += "\n\t this controller does not have any disk";

        }
        return controllerSpec;

    }

    @Override
    protected Controller clone() throws CloneNotSupportedException {
        return new Controller(this);
    }
}