package tanks.graphisc.buttons;

import tanks.graphisc.text.CentralText;
import tanks.graphisc.text.PortText;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class AddToCentral {

    JButton jButton = new JButton("Podepnij do centrali");

    public AddToCentral(JPanel panel, CentralText centralText, PortText portText){

        jButton.setBounds(250,60,160,20);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [] data = centralText.getText();

                String host = data[0];
                int port = Integer.parseInt(data[1]);
                int tankPort = Integer.parseInt(portText.getText());

                try {
                    Socket socket = new Socket(host,port);
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    bufferedWriter.write("ars:"+tankPort+",localhost\n");
                    bufferedWriter.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });



        panel.add(jButton);


    }


}
