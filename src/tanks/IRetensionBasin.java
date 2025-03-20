package tanks;

import java.io.IOException;

public interface IRetensionBasin {

    int getWaterDischarge();
    long getFillingPercentage();
    void setWaterDischarge(int waterDischarge);
    void setWaterInflow(int waterInflow, int port) throws IOException;
    void assignRiverSection(int port, String host);


}
