package environment.graphisc.labels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class River {

    public River(int counter, String name, JPanel panel, int y){

        JLabel counterL = new JLabel(String.valueOf(counter));
        JLabel nameL = new JLabel(name);
        JLabel rainfallL = new JLabel("-");
        JTextField textField = new JTextField();
        JButton button = new JButton("Ustaw Opady");

        counterL.setBounds(5,y,60,20);
        nameL.setBounds(75,y,80,20);
        rainfallL.setBounds(170, y, 80,20);
        textField.setBounds(230,y,80,20);
        button.setBounds(330,y,130,20);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rainfall = textField.getText();
                rainfallL.setText(rainfall);
                panel.revalidate();
                panel.repaint();

                try {
                    Socket socket = new Socket("localhost", Integer.parseInt(name));

                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    bufferedWriter.write("srf:"+rainfall+"\n");
                    bufferedWriter.flush();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


        panel.add(counterL);
        panel.add(nameL);
        panel.add(rainfallL);
        panel.add(textField);
        panel.add(button);

        panel.revalidate();
        panel.repaint();


    }
}
