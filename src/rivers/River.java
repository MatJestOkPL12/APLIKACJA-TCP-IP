package rivers;

public class River implements IRiverSection{
    int tankPort;
    int delay;
    boolean addToTank = false;

    public River(int port,int delay){
        this.port = port;
        this.delay = delay;
    }
    int port;
    String host = "localhost";
    int rainfall = 0;
    int realDischarge = 0;
    @Override
    public void setRealDischarge(int realDischarge) {
    this.realDischarge = realDischarge;
    }

    @Override
    public void setRainfall(int rainfall) {

        this.rainfall = rainfall;
    }

    @Override
    public void assignRetensionBasin(int port, String host) {
        tankPort = port;
    }
    public int getRainfall(){
        return rainfall;
    }
    public int getPort(){
        return port;
    }
    public int getTankPort(){
        return tankPort;
    }
    public int getRealDischarge(){return realDischarge;}
    public int getDelay(){
        return delay*1000;
    }
    public void changeAddToTank(){
        addToTank = true;
    }
    public boolean getAddToTank(){
        return addToTank;
    }
    public void setTankPort(int port){
        tankPort = port;
    }
}
