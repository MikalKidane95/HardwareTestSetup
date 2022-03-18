import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServerModels {
    List<Server> serverList = new ArrayList<>();

    public void LoadServers() {
        this.serverList = readServersFromCSV("data/servers.csv");
    }

    private static List<Server> readServersFromCSV(String fileName) {
        List<Server> serverList = new ArrayList<>();
        Path filePath = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(filePath,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            line = null;
            while ((line = br.readLine()) != null) {
                Server server = lineToServer(line);
                serverList.add(server);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            // TODO: handle exception
        }
        return serverList;
    }

    public static Server lineToServer(String line) {
        String[] attributes = line.split(",");
        Server server = new Server(attributes);
        return server;
    }

    @Override
    public String toString() {
        int n = 0;
        String serverListString = "";
        for (Server Sv : this.serverList) {
            serverListString += n++ + " " + Sv + "\n";
        }
        return serverListString;
    }

}
