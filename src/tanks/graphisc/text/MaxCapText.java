package tanks.graphisc.text;

import tanks.graphisc.labels.MaxCapacity;

import javax.swing.*;

public class MaxCapText {

    JTextField field = new JTextField();

    public MaxCapText(JPanel panel){
        field.setBounds(150,5, 60,20);

        panel.add(field);
    }

    public String getText(){
        return field.getText();
    }

}
