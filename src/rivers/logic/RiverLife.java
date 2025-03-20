package rivers.logic;

import rivers.River;
import rivers.graphisc.labels.Info;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class RiverLife extends Thread {

    River river;
    Info info;

    public RiverLife(River river, Info info){

        this.river = river;
        this.info = info;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            while (true) {

                    sendWater(river);
                    int water = river.getRainfall()+river.getRealDischarge();
                    String infoS = String.valueOf(water);
                    info.updateWater(infoS);
                    try {
                        Thread.sleep(river.getDelay());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized void sendWater(River river){
        try {
            if(river.getAddToTank()) {
                Socket socket = new Socket("localhost", river.getTankPort());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                int waterToFlow = river.getRainfall()+river.getRealDischarge();
                writer.write("swi:" + String.valueOf(waterToFlow) + "," + river.getPort() + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
