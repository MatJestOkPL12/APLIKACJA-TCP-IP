package environment.graphisc.labels;

import javax.swing.*;

public class RiverInfo {

    public RiverInfo(JPanel panel){

        JLabel label = new JLabel("L.p");
        label.setBounds(5,50,80,20);
        JLabel name = new JLabel("Nazwa");
        name.setBounds(70,50,80,20);
        JLabel rainfall = new JLabel("Opady");
        rainfall.setBounds(150,50,80,20);

        panel.add(label);
        panel.add(name);
        panel.add(rainfall);

    }
}
