import java.util.ArrayList;
import java.util.List;

class Laptop {
    private String warehouseId;
    private String manufacturer;
    private String model;
    private String processor;
    private String memory;
    private String diskPorts;
    public List<Hdd> harddisks;

    public Laptop() {
        this.harddisks = new ArrayList<Hdd>();

    }

    public Laptop(String warehouseId, String manufacturer, String model, String processor, String memory,
            String diskPorts) {
        this.warehouseId = warehouseId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.processor = processor;
        this.memory = memory;
        this.diskPorts = diskPorts;
        this.harddisks = new ArrayList<Hdd>();
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

    public String getprocessor() {
        return processor;
    }

    public void setprocessor(String processor) {
        this.processor = processor;
    }

    public String getmemory() {
        return memory;
    }

    public void setmemory(String memory) {
        this.memory = memory;
    }

    public int getdiskPorts() {
        return Integer.valueOf(this.diskPorts);
    }

    public void setdiskPorts(String diskPorts) {
        this.diskPorts = diskPorts;
    }

    public void addHdd() {
        utility utility = new utility();
        HddModels hddReader = new HddModels();
        System.out.println("You can attach upto : " + this.getdiskPorts()
                + " harddisks to your laptop. \n How many would you like to add");
        int numberOfHdds;
        int DiskIdx = 0;
        int selectedNumberOfHdds = utility.getInput(this.getdiskPorts() + 1);
        numberOfHdds = selectedNumberOfHdds;
        for (int index = 0; index < numberOfHdds; index++) {
            hddReader.LoadHdd();
            System.out.println("\nSelect Disk to attach [ Disk #" + DiskIdx + "]:");
            DiskIdx++;
            utility.printChoices(hddReader.getSataDisks());
            int selectedsataDiskModelInt = utility.getInput(hddReader.getSataDisks().size());
            Hdd userSata = (Hdd) hddReader.getSataDisks().get(selectedsataDiskModelInt);
            this.harddisks.add(userSata);
            System.out.println("\nDisk Attached");
        }
    }

    @Override
    public String toString() {
        String LaptopSpec = "laptop [warehouseId =" + warehouseId + ", manufacturer =" + manufacturer
                + ", model =" + model + ", processor =" + processor + ", memory =" + memory + ", diskPorts ="
                + diskPorts + "]";

        if (this.harddisks != null && this.harddisks.size() != 0) {
            LaptopSpec += "\n\t The Disks attached to this Laptop are [" + this.harddisks.size() + "] :";

            for (Hdd hdd : this.harddisks) {
                LaptopSpec += "\n\t\t" + hdd.toString();
            }
        } else {
            LaptopSpec += "\n\t this laptop does not have any disk";

        }

        return LaptopSpec;
    }
}