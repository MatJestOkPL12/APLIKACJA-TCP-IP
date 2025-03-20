package rivers.graphisc.buttons;

import rivers.Main;
import rivers.River;
import rivers.graphisc.text.DelayText;
import rivers.graphisc.text.PortText;
import rivers.logic.RiverLife;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MakeRiver {
    River river;

    JButton button = new JButton("Stwórz rzeke");


    public MakeRiver (JPanel panel, PortText portText, DelayText delayText, Main main){

        button.setBounds(10,80, 130,20);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int port = Integer.parseInt(portText.getText());
                int delay = Integer.parseInt(delayText.getText());

                river = new River(port, delay);
                ExecutorService threeadPool = Executors.newFixedThreadPool(1);
                threeadPool.execute(() -> main.startRiverServer(river));

            }
        });


        panel.add(button);

    }
    public River getRiver(){
        return river;
    }
}
