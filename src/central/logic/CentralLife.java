package central.logic;

import central.Central;
import central.Main;
import central.graphisc.Tank;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class CentralLife extends Thread {
    Main main;
    JPanel panel;

    public CentralLife(Central central, central.Main main, JPanel panel){
       this.central = central;
       this.main = main;
       this.panel = panel;

    }

    Central central;


    @Override
    public void run() {
        while (true){


            uptadeLabel();


            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }



    public synchronized long getFillPercentage(String host, int port){

        try {
            Socket socket = new Socket(host, port);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.write("gfp:\n");
            writer.flush();
            String response = reader.readLine();
            return Long.parseLong(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized void uptadeLabel(){
        ArrayList<Tank> tanks = (ArrayList<Tank>) main.getTanks();
        ArrayList<JLabel> labels = (ArrayList<JLabel>) main.getLabels();

        for(int i = 0; i<main.getNumberOfTanks(); i++){
            Tank tank = tanks.get(i);
            int port = tank.getPort();
            long percent = getFillPercentage("localhost", port);
            JLabel label = labels.get(i);
            label.setText(String.valueOf(percent)+"%");
            panel.revalidate();
            panel.repaint();
        }


    }

}
