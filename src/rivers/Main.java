package rivers;

import rivers.graphisc.Panel;
import rivers.graphisc.buttons.AddToEnvi;
import rivers.graphisc.buttons.AddToTank;
import rivers.graphisc.buttons.MakeRiver;
import rivers.graphisc.labels.*;
import rivers.graphisc.text.TankText;
import rivers.graphisc.text.DelayText;
import rivers.graphisc.text.EnvironmentText;
import rivers.graphisc.text.PortText;
import rivers.logic.RiverLife;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class Main {

    static Main main = new Main();
    static Info info;
    public static void main(String[] args) {

        main.makeGUI();

    }



    private void makeGUI(){
        JFrame frame = new JFrame();

        Panel panel1= new Panel(frame);
        JPanel panel = panel1.getPanel();
        Port1 port1 = new Port1(panel);
        Delay delay = new Delay(panel);
        PortText portText = new PortText(panel);
        DelayText delayText = new DelayText(panel);
        MakeRiver makeRiver = new MakeRiver(panel,portText,delayText,main);
        Host1 host1 = new Host1(panel);
        Port2 port2 = new Port2(panel);
        Host2 host2 = new Host2(panel);
        Port3 port3 = new Port3(panel);
        EnvironmentText environment = new EnvironmentText(panel);
        TankText tankText = new TankText(panel);
        AddToTank addToTank = new AddToTank(panel, tankText,portText, makeRiver);
        AddToEnvi addToEnvi = new AddToEnvi(panel, environment, portText);
        info = new Info(panel);


        frame.setTitle("Rzeka");
        frame.setLocation(800,200);
        frame.setSize(520,500);
        frame.setVisible(true);
    }

    public void startRiverServer(River river) {


        try {
            ServerSocket serverSocket = new ServerSocket(river.getPort());
            RiverLife riverLife = new RiverLife(river, info);
            riverLife.start();


            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket, river);
                } catch (SocketTimeoutException e) {

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void handleClient(Socket clientSocket, River river){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String request = in.readLine();
            String response = handleRequest(river, request);
            out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized String handleRequest(River river, String request){

        if(request == null){
            return "Brak zapytania";
        }
        if (request.startsWith("srd")){

            int discharge = Integer.parseInt(request.substring(4));
            river.setRealDischarge(discharge);
            return "";
        }
        if(request.startsWith("srf")){
            int rainfall = Integer.parseInt(request.substring(4));
            river.setRainfall(rainfall);
            return "";
        }
        if(request.startsWith("arb")){

            river.assignRetensionBasin(river.port, river.host);
        }
        return "Nieznane zapytanie";
    }




}
