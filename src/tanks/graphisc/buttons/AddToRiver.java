package tanks.graphisc.buttons;

import tanks.Tank;
import tanks.graphisc.text.PortText;
import tanks.graphisc.text.RiverAddText;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class AddToRiver {

    JButton button = new JButton("Dodaj do rzeki");

    public AddToRiver(JPanel panel, PortText portText, RiverAddText riverAddText, MakeBasin makeBasin){

        button.setBounds(490,60,150,20);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int tankPort = Integer.parseInt(portText.getText());
                String [] data = riverAddText.getText();
                String host = data[0];
                int port = Integer.parseInt(data[1]);

                try {
                    Socket socket = new Socket(host,port);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.write("ars:"+tankPort+",localhost\n");
                    writer.flush();
                    Tank tank = makeBasin.getTank();
                    tank.setOutData(host,port);
                    tank.setAddToRiver();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


        panel.add(button);

    }

}
