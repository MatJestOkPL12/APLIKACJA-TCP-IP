package central.graphisc;

import javax.swing.*;

public class PortText {
    JTextField textField = new JTextField();

    public PortText(JPanel panel){

        textField.setBounds(50,5,60,20);

        panel.add(textField);

    }


    public String getText(){
        return textField.getText();
    }

}
