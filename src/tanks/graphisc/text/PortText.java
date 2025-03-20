package tanks.graphisc.text;

import javax.swing.*;

public class PortText {

    JTextField field = new JTextField();

    public PortText (JPanel panel){
        field.setBounds(50,30,50,20);

        panel.add(field);
    }

    public String getText(){
        return field.getText();
    }

}
