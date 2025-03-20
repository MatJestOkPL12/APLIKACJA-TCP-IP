package tanks;

import java.io.IOException;

public class Tank implements IRetensionBasin {


    boolean addToRiver = false;
    int port;
    String host;
    private final int maxCapacity;
    private int currentCapacity = 0;
    private long percentOfCapacity = 0;
    private int waterDischarge;
    private int outPort;
    private String outHost;
    private int waterInflow=0;
    private int howIn = 0;
    int drop = 0;

    public Tank(int maxCapacity, int port){
        this.maxCapacity = maxCapacity;
        this.port = port;

    }


    @Override
    public synchronized int getWaterDischarge() {

        if (currentCapacity > maxCapacity){
            drop = currentCapacity - maxCapacity + waterDischarge;
            currentCapacity = maxCapacity - waterDischarge;
            return drop;
        }
        else if(currentCapacity<waterDischarge){
            drop = currentCapacity;
            currentCapacity =0;
            return drop;
        }
        else if(currentCapacity>waterDischarge && currentCapacity<maxCapacity){
            drop = waterDischarge;
            currentCapacity = currentCapacity-drop;
            return drop;
        }
        return drop ;
    }

    @Override
    public synchronized long getFillingPercentage() {

        return percentOfCapacity;
    }

    @Override
    public synchronized void setWaterDischarge(int waterDischarge) {
        this.waterDischarge = waterDischarge;

    }

    @Override
    public synchronized void setWaterInflow(int waterInflow, int port) throws IOException {
            howIn = howIn + waterInflow;
            this.waterInflow = waterInflow;
            currentCapacity = currentCapacity + waterInflow;
            percentOfCapacity = (long) (((double)currentCapacity/maxCapacity)*100);

    }

    @Override
    public void assignRiverSection(int port, String host) {
        this.outPort = port;
        this.outHost = host;
        setAddToRiver();
    }

    public int getPort(){
        return port;
    }
    public int getOutPort(){
        return outPort;
    }

    public int getDrop(){
        return drop;
    }
    public int getWaterDischarge2(){
        return waterDischarge;
    }


    public synchronized int getHowIn(){
        return howIn;
    }
    public synchronized void resetHowIn(){
        howIn = 0;
    }
    public  String getHost(){
        return host;
    }
    public String getOutHost(){
        return outHost;
    }

    public void setAddToRiver(){
        addToRiver = true;
    }
    public boolean getAddToRiver(){
        return addToRiver;
    }
    public void setOutData(String host, int port){
        outHost = host;
        outPort = port;
    }

}
