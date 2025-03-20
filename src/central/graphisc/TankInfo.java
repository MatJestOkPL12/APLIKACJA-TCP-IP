package central.graphisc;

import javax.swing.*;

public class TankInfo {

    JLabel counter = new JLabel("L.p");
    JLabel name = new JLabel("Nazwa");
    JLabel waterLvl = new JLabel("Procent zapełnienia");
    JLabel discharge = new JLabel("Nastawy");

    public TankInfo(JPanel panel){

        counter.setBounds(5,40,20,20);
        name.setBounds(50,40,50,20);
        waterLvl.setBounds(100,40, 130,20);
        discharge.setBounds(250,40,80,20);


        panel.add(counter);
        panel.add(name);
        panel.add(waterLvl);
        panel.add(discharge);

    }


}
