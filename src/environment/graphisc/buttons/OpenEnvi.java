package environment.graphisc.buttons;

import environment.Environment;
import environment.Main;
import environment.graphisc.textfields.PortText;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OpenEnvi {

    JButton button = new JButton("Otwórz środowisko");

    public OpenEnvi(JPanel panel, PortText portText, Main main){

        button.setBounds(160,5, 150,20);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int port = Integer.parseInt(portText.getText());
                Environment environment = new Environment(port);
                ExecutorService threeadPool = Executors.newFixedThreadPool(1);
                threeadPool.execute(() -> main.startEnvironmentServer(port,environment));
            }
        });




        panel.add(button);
    }

}
