package central.graphisc;

import central.Central;
import central.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OpenCentral {

    JButton button = new JButton("Otwórz centrale");

    public OpenCentral(JPanel panel, PortText portText, Main main){

        button.setBounds(120,5,140,20);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int port = Integer.parseInt(portText.getText());
                Central central = new Central(port);
                ExecutorService threeadPool = Executors.newFixedThreadPool(1);
                threeadPool.execute(() -> main.startCentralServer(port,central));

            }
        });


        panel.add(button);


    }
}
