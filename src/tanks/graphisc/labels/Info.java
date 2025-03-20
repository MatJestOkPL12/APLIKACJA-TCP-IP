package tanks.graphisc.labels;

import javax.swing.*;

public class Info {

    JLabel label = new JLabel("Ile wpłyneło");
    JLabel label2 = new JLabel("Ile wypłyneło");
    JLabel label3 = new JLabel("Procent zapełnienia");



    public Info(JPanel panel){
        label.setBounds(50,100,80,20);
        label2.setBounds(290,100,80,20);
        label3.setBounds(500,100,120,20);



        panel.add(label);
        panel.add(label2);
        panel.add(label3);
    }



}
