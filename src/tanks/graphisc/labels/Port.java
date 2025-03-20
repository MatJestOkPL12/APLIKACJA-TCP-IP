package tanks.graphisc.labels;

import javax.swing.*;

public class Port {
    JLabel label = new JLabel("Port");

    public Port(JPanel panel){
        label.setBounds(5,30,60,20);

        panel.add(label);
    }
}
