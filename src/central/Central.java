package central;

import central.graphisc.Tank;

import java.util.ArrayList;
import java.util.List;

public class Central implements IControlerCenter{

    int port;
    String host;
    int counter = 0;
    boolean hasport = false;

    List<Integer> ports = new ArrayList<>();
    List<String> hosts = new ArrayList<>();


    public Central(int port){
        this.port = port;

    }

    @Override
    public void assignRetensionBasin(int port, String host) {
        ports.add(port);
        hosts.add(host);

    }
    public int getPort(){
        return port;
    }




    public void changeSetPorts(){
        hasport = true;
    }
    public boolean getHasPorts(){
        return  hasport;
    }

}
