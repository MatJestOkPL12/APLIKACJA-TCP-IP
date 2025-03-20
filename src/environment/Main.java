package environment;

import environment.graphisc.Panel;
import environment.graphisc.buttons.OpenEnvi;
import environment.graphisc.labels.*;
import environment.graphisc.textfields.PortText;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Main  {
    int counter = 1;
    JPanel panel;
    int y = 80;
    public static void main(String[] args) {
    Main main = new Main();
    main.makeGui(main);


    }


    private void makeGui( Main main){
        JFrame frame = new JFrame();
        frame.setTitle("Środowisko");
        Panel panel1 = new Panel(frame);
        panel = panel1.getPanel();
        Port port = new Port(panel);
        PortText portText = new PortText(panel);
        OpenEnvi openEnvi = new OpenEnvi(panel,portText, main);
        RiverInfo riverInfo = new RiverInfo(panel);








        frame.setLocation(800,200);


        frame.setSize(500,400);
        frame.setVisible(true);
    }


    public void startEnvironmentServer(int port, Environment environment){
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true){
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket,environment);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private void handleClient(Socket clientSocket, Environment environment){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());



            String request = in.readLine();
            String response = handleRequest(request,environment);
            out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized  String handleRequest(String request, Environment environment){
        if(request == null){
            return "Brak zapytania";
        }
        if(request.startsWith("ars")){
            String buff[] = request.substring(4).split(",");
            environment.assignRiverSection(Integer.parseInt(buff[0]), buff[1]);
            River river = new River(counter,buff[0],panel, y );
            y = y+30;
            counter++;
            return "";
        }
        return "Nieznana komenda";
    }
}
