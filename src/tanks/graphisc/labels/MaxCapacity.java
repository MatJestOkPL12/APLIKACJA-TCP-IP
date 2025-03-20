package tanks.graphisc.labels;

import javax.swing.*;

public class MaxCapacity {

    JLabel label = new JLabel("Maksymalna pojętość: ");

    public MaxCapacity (JPanel panel){
        label.setBounds(5, 5, 170,20);

        panel.add(label);
    }

}
