package tanks.graphisc.buttons;

import tanks.Main;
import tanks.Tank;
import tanks.graphisc.text.MaxCapText;
import tanks.graphisc.text.PortText;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MakeBasin {

    JButton button = new JButton("Stwórz zbiornik");
    Tank tank;

    public MakeBasin(JPanel panel, MaxCapText maxCapText, PortText portText, Main  main){

        button.setBounds(15,55,140,20);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int port = Integer.parseInt(portText.getText());
                int maxCap = Integer.parseInt(maxCapText.getText());
                ExecutorService threeadPool = Executors.newFixedThreadPool(1);

                tank = new Tank(maxCap, port);
                threeadPool.execute(() -> main.startTankServer(port,tank ));
            }
        });


        panel.add(button);

    }

    public Tank getTank(){
        return tank;
    }
}
