public class HardwareSetup {

    public static void main(String[] args) {
        utility utility = new utility();
        System.out.print(
                "Please Choose either Labtop or Server: \n Enter 0 to select Server \n Enter 1 to select Labtop \n");
        int choice = utility.getInput(2);
        ControllerModels controllerReader = new ControllerModels();
        Server userServer = null;

        switch (choice) {
            case 0:
                System.out.println("You have choosen Server: Please Select your server model");
                ServerModels serverReader = new ServerModels();
                serverReader.LoadServers();
                utility.printChoices(serverReader.serverList);
                int selectedServerModelInt = utility.getInput(serverReader.serverList.size());
                userServer = serverReader.serverList.get(selectedServerModelInt);
                System.out.println(userServer);
                int PCInum = Integer.valueOf(userServer.getPCI());
                int PCIXnum = Integer.valueOf(userServer.getPCIX());
                int PCIEnum = Integer.valueOf(userServer.getPCIE());
                System.out.println("The selected server model has \nNumber of PCI: " + PCInum + "\n"
                        + "Number of PCIX: " + PCIXnum + "\n"
                        + "Number of PCIE: " + PCIEnum + "\n");
                controllerReader.LoadController();
                if (PCInum > 0) {
                    userServer.addControllers(PCInum, "PCI");
                }
                if (PCIXnum > 0) {
                    userServer.addControllers(PCIXnum, "PCIX");
                }
                if (PCIEnum > 0) {
                    userServer.addControllers(PCIEnum, "PCIE");
                }
                if (PCInum == 0 && PCIXnum == 0 && PCIEnum == 0) {
                    System.out.println("The selected server does not have a bus to connect with");
                }
                System.out.println("Your server spec is:");
                System.out.println(userServer.toString());

                break;

            case 1:
                System.out.println("You have choosen Laptop: Please Select your laptop model");
                LaptopModels laptopReader = new LaptopModels();
                laptopReader.LoadLaptops();
                System.out.println(laptopReader);
                int selectedLaptopModelInt = utility.getInput(laptopReader.laptopList.size());
                Laptop userLaptop = laptopReader.laptopList.get(selectedLaptopModelInt);
                System.out.println("The selected Laptop model is \n\t" + userLaptop);
                userLaptop.addHdd();
                System.out.println("Your laptop specs are:");
                System.out.println(userLaptop);
                break;
        }

    }

}