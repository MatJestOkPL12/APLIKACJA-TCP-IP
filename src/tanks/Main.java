package tanks;

import tanks.graphisc.Panel;
import tanks.graphisc.buttons.AddToCentral;
import tanks.graphisc.buttons.AddToRiver;
import tanks.graphisc.buttons.MakeBasin;
import tanks.graphisc.labels.*;
import tanks.graphisc.text.CentralText;
import tanks.graphisc.text.MaxCapText;
import tanks.graphisc.text.PortText;
import tanks.graphisc.text.RiverAddText;
import tanks.logic.TankLife;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static Main main = new Main();
    static Percents percents;
    static HowIn howIn;
    static HowOut howOut;
    public static void main(String[] args) {

        main.makeGui();




    }

    private void makeGui(){
        JFrame frame = new JFrame();
        Panel jPanel = new Panel(frame);
        JPanel panel = jPanel.getPanel();
        MaxCapacity maxCapacity = new MaxCapacity(panel);
        MaxCapText maxCapText = new MaxCapText(panel);
        Port port = new Port(panel);
        PortText portText = new PortText(panel);
        MakeBasin makeBasin = new MakeBasin(panel, maxCapText, portText, main );
        Host host = new Host(panel);
        Port2 port2 = new Port2(panel);
        CentralText centralText = new CentralText(panel);
        AddToCentral add = new AddToCentral(panel,centralText, portText);
        Host2 host2 = new Host2(panel);
        Port3 port3 = new Port3(panel);
        RiverAddText riverAddText = new RiverAddText(panel);
        AddToRiver addToRiver = new AddToRiver(panel, portText, riverAddText, makeBasin);
        Info info = new Info(panel);
        howIn = new HowIn(panel);
        howOut = new HowOut(panel);
        percents = new Percents(panel);




        frame.setTitle("Zbiornik");
        frame.setLocation(500,50);
        frame.setSize(700,350);
        frame.setVisible(true);
    }



    public void startTankServer(int port, Tank tank){
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            TankLife tankLife1 = new TankLife(tank, howIn, howOut, percents);
            tankLife1.start();


            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket, tank);
                } catch (SocketTimeoutException e) {
                }

            }
        } catch (IOException e) {

        }
    }

    private synchronized void handleClient(Socket clientSocket, Tank tank){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);


            String request = in.readLine();
            String response = handleRequest(request, tank);
            if(request.startsWith("gfp")) {
                out.println(response);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized String handleRequest(String request, Tank tank) throws IOException {
        if(request == null){
            return "Brak zapytania, błąd";
        }
        if (request.startsWith("gwd")){
            return String.valueOf(tank.getWaterDischarge());
        }
        if (request.startsWith("gfp")){
            return String.valueOf(tank.getFillingPercentage() + "\n");
        }
        if(request.startsWith("swd")){
            int discharge = Integer.parseInt(request.substring(4));
            tank.setWaterDischarge(discharge);
            return "";
        }
        if(request.startsWith("swi")){
            String [] buff = request.substring(4).split(",");
            int inflow = Integer.parseInt(buff[0]);
            int port = Integer.parseInt(buff[1]);
            tank.setWaterInflow(inflow, port);
            return "";
        }
        if (request.startsWith("ars")){
            String buff[] = request.substring(4).split(",");
            int port = Integer.parseInt(buff[0]);
            tank.assignRiverSection(port, buff[1]);
        }
        return "Nieznana komenda";
    }



}
