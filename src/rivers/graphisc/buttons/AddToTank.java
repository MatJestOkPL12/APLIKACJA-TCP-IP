package rivers.graphisc.buttons;

import rivers.River;
import rivers.graphisc.text.PortText;
import rivers.graphisc.text.TankText;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class AddToTank {

    JButton button = new JButton("Dodaj do zbiornika");

    public AddToTank(JPanel panel, TankText tankText, PortText portText, MakeRiver makeRiver){

        button.setBounds(185,80,140,20);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [] data = tankText.getText();
                String host = data[0];
                int port = Integer.parseInt(data[1]);
                int riverPort = Integer.parseInt(portText.getText());

                try {
                    Socket socket = new Socket(host,port);

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    writer.write("arb:"+riverPort+",localhost\n");
                    writer.flush();
                    River river = makeRiver.getRiver();
                    river.setTankPort(port);
                    river.changeAddToTank();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


        panel.add(button);

    }


}
