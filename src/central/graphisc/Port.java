package central.graphisc;

import javax.swing.*;

public class Port {

    JLabel port = new JLabel("Port");

    public Port(JPanel panel){
        port.setBounds(5,5,40,20);

        panel.add(port);
    }

}
