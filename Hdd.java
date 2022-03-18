
class Hdd implements Cloneable {
    private String warehouseId;
    private String manufacturer;
    private String model;
    private String size;
    private String port;

    public Hdd() {

    }

    Hdd(Hdd hdd) {
        this.warehouseId = hdd.getWarehouseId();
        this.manufacturer = hdd.getManufacturer();
        this.model = hdd.getModel();
        this.size = hdd.getSize();
        this.port = hdd.getPort();
    }

    public Hdd(String warehouseId, String manufacturer, String model, String size, String port) {
        this.warehouseId = warehouseId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.size = size;
        this.port = port;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "hdd [warehouseId =" + warehouseId + ", manufacturer =" + manufacturer
                + ", model =" + model + ", size =" + size + ", port =" + port + "]";

    }

    @Override
    protected Hdd clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return (Hdd) super.clone();
    }
}