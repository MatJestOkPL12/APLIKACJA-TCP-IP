package tanks.logic;

import tanks.Tank;
import tanks.graphisc.labels.HowIn;
import tanks.graphisc.labels.HowOut;
import tanks.graphisc.labels.Percents;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TankLife extends Thread {

    Tank tank;
    HowIn howIn;
    HowOut howOut;
    Percents percents;

    public TankLife(Tank tank, HowIn howIn, HowOut howOut, Percents percents){
        this.tank = tank;
        this.howOut = howOut;
        this.howIn = howIn;
        this.percents = percents;

    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (true){
            dischargeWater(tank);
            addToRiver(tank);
            percents.uptadeText(String.valueOf(tank.getFillingPercentage()));
            howOut.uptadeText(String.valueOf(tank.getDrop()));
            howIn.uptadeText(String.valueOf(tank.getHowIn()));
            tank.resetHowIn();
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private synchronized void dischargeWater(Tank tank){

            try {
                Socket socket = new Socket("localhost", tank.getPort());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                writer.write("gwd:" + String.valueOf(tank.getWaterDischarge2() + "\n"));
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }

    private synchronized void addToRiver(Tank tank) {
        if (tank.getAddToRiver()) {
                try {
                    Socket socket = new Socket(tank.getOutHost(), tank.getOutPort());
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    writer.write("srd:" + String.valueOf(tank.getDrop()) + "\n");
                    writer.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

        }


    }

}
