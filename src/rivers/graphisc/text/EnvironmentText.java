package rivers.graphisc.text;

import javax.swing.*;

public class EnvironmentText {

    JTextField host = new JTextField();
    JTextField port = new JTextField();


    public EnvironmentText(JPanel panel){

        host.setBounds(400,5, 60,20);
        port.setBounds(400,40,60,20);

        panel.add(host);
        panel.add(port);


    }

    public String[] getText(){

        String[] buff = new String[2];

        buff[0] = host.getText();
        buff[1] = port.getText();

        return buff;
    }




}
