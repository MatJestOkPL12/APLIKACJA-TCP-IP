package tanks.graphisc.labels;

import javax.swing.*;

public class Host {
    JLabel label = new JLabel("Host");

    public Host(JPanel panel){

        label.setBounds(250,5,50,20);

        panel.add(label);

    }
}
