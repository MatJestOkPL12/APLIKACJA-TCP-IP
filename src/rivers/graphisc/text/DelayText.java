package rivers.graphisc.text;

import javax.swing.*;

public class DelayText {

    JTextField textField = new JTextField();


    public DelayText(JPanel panel){

        textField.setBounds(85,40,60,20);

        panel.add(textField);


    }

    public String getText(){
        return textField.getText();
    }


}
