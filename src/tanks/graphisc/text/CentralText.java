package tanks.graphisc.text;

import javax.swing.*;

public class CentralText {

    JTextField host = new JTextField();
    JTextField port = new JTextField();

    public CentralText(JPanel panel){

        host.setBounds(300,5,80,20);
        port.setBounds(300,30, 80,20);

        panel.add(host);
        panel.add(port);

    }


    public String[] getText(){
        String [] buff = new String[2];
        buff[0] = host.getText();
        buff[1] = port.getText();
        return buff;
    }

}
