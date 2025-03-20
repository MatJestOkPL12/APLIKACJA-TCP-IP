package rivers.graphisc.text;

import javax.swing.*;

public class TankText {


    JTextField host = new JTextField();
    JTextField port = new JTextField();


    public TankText(JPanel panel){

        host.setBounds(250,5, 60,20);
        port.setBounds(250,40,60,20);

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
