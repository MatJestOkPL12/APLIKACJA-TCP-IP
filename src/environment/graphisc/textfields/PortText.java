package environment.graphisc.textfields;

import environment.graphisc.labels.Port;

import javax.swing.*;

public class PortText {

    JTextField textField = new JTextField();

    public PortText(JPanel panel){

        textField.setBounds(60,5, 80,20);

        panel.add(textField);


    }


    public String getText(){
        return textField.getText();
    }

}
