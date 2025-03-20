package rivers.graphisc.labels;

import javax.swing.*;

public class Delay {

    JLabel label = new JLabel("Opóźnienie");

    public Delay(JPanel panel){
        label.setBounds(5,40,80,20);

        panel.add(label);
    }

}
