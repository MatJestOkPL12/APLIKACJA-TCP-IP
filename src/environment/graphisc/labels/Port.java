package environment.graphisc.labels;

import javax.swing.*;

public class Port {
    JLabel label = new JLabel("Port");

    public Port(JPanel panel){
        label.setBounds(5,5,50,20);


        panel.add(label);

    }
}
