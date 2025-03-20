package central.graphisc;

import javax.swing.*;
import javax.swing.text.TabExpander;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Tank {

   JPanel panel;
    JLabel waterLvll;
    JLabel dischargeL;
    JButton button = new JButton("Ustaw przepływ");
    JTextField textArea = new JTextField();
    int port;

    public Tank(JPanel panel ,int counter, int name, int y){
        JLabel counterl = new JLabel(String.valueOf(counter));
        JLabel namel = new JLabel(String.valueOf(name));
        waterLvll = new JLabel("0%");
        dischargeL = new JLabel(" - ");
        this.panel = panel;
        port = name;


        counterl.setBounds(5,y, 30,20);
        namel.setBounds(50, y, 50,20);
        waterLvll.setBounds(150,y, 60,20);
        dischargeL.setBounds(270,y,60,20);
        button.setBounds(325, y, 130,20);
        textArea.setBounds(480, y, 80,20);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String discharge = textArea.getText();
                dischargeL.setText(discharge);
                panel.revalidate();
                panel.repaint();

                try {
                    Socket socket = new Socket("localhost", name);

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    writer.write("swd:"+discharge+"\n");
                    writer.flush();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



        panel.add(counterl);
        panel.add(namel);
        panel.add(waterLvll);
        panel.add(dischargeL);
        panel.add(button);
        panel.add(textArea);
        panel.revalidate();
        panel.repaint();

    }


    public void updateWaterLvl(int waterLvl){
        waterLvll.setText(String.valueOf(waterLvl) + "%");
        panel.revalidate();
        panel.repaint();
    }

    public void uptadeDischarge(int discharge){
        dischargeL.setText(String.valueOf(discharge));
        panel.revalidate();
        panel.repaint();
    }

    public JLabel getLabel(){
        return waterLvll;
    }
    public int getPort(){
        return port;
    }

}
