package environment;

public class Environment implements IEnvironmentSection{

    public Environment(int  port){
        this.port = port;
    }

    int port;
    String host;
    int counter = 0;

    String [] hosts = new String[10];
    int [] ports = new int[10];

    @Override
    public void assignRiverSection(int port, String host) {
        if(counter < 10){
            hosts[counter] = host;
            ports[counter] = port;
            counter++;
            if(counter == 10){
                counter = 0;
            }
        }
    }

    public String [] getHosts(){
        return hosts;
    }

    public int [] getPorts(){
        return ports;
    }
}
