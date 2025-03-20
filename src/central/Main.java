package central;

import central.graphisc.*;
import central.logic.CentralLife;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class Main {
    JPanel panel;
    int counter = 1;
    int y = 60;
    List<Tank> tanks = new ArrayList<>();
    List<JLabel> labels = new ArrayList<>();

    static Main main = new Main();
    public static void main(String[] args) {

        main.makeGui();




    }


    private void makeGui(){
        JFrame frame = new JFrame();
        frame.setTitle("Centrala");
        panel = new JPanel();
        panel.setLayout(null);
        TankInfo tankInfo = new TankInfo(panel);
        Port port = new Port(panel);
        PortText portText = new PortText(panel);
        OpenCentral openCentral = new OpenCentral(panel, portText, main);


        frame.add(panel);

        frame.setLocation(500,200);


        frame.setSize(600,300);
        frame.setVisible(true);
    }

    public void startCentralServer(int port, Central central){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            int i = 0;
            CentralLife centralLife = new CentralLife(central, main, panel);
            centralLife.start();


            while(true){
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket, central);
                if(i == 0) {
                    i++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleClient(Socket clientSocket, Central central){

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());


            String request = in.readLine();
            String response = handleRequest(request,central);
            out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized String handleRequest(String request, Central central){
        if (request == null){
            return "Brak zapytania";
        }
        if(request.startsWith("ars")){
            String [] buff = request.substring(4).split(",");
            central.assignRetensionBasin(Integer.parseInt(buff[0]), buff[1]);
            Tank tank = new Tank(panel, counter, Integer.parseInt(buff[0]), y);
            labels.add(tank.getLabel());
            tanks.add(tank);
            counter++;
            y = y+20;
            return "";
        }
        return "Nieznane zapytanie";
    }
    public int getNumberOfTanks(){
        return tanks.size();
    }
    public List<Tank> getTanks(){
        return tanks;
    }
    public List<JLabel> getLabels(){
        return labels;
    }
}
