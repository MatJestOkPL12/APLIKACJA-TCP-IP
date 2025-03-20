package rivers.graphisc.buttons;

import rivers.graphisc.text.EnvironmentText;
import rivers.graphisc.text.PortText;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class AddToEnvi {


    JButton button = new JButton("Dodaj do środowiska");

    public AddToEnvi(JPanel panel, EnvironmentText environmentText, PortText portText){

        button.setBounds(335,80,160,20);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [] data = environmentText.getText();
                String host = data[0];
                int port = Integer.parseInt(data[1]);
                int riverPort = Integer.parseInt(portText.getText());

                try {

                    Socket socket = new Socket(host,port);

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.write("ars:"+riverPort+",localhost\n");
                    writer.flush();



                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        panel.add(button);

    }

}
