package rivers.graphisc.labels;

import javax.swing.*;

public class Port1 {

    JLabel label = new JLabel("Port");

    public Port1(JPanel panel){
        label.setBounds(5,5,60,20);

        panel.add(label);
    }

}
