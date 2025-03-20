package tanks.graphisc.labels;

import javax.swing.*;

public class HowIn {
    JLabel label = new JLabel("0");
    JPanel panel;


    public HowIn(JPanel panel){
        label.setBounds(80,150,60,20);
        this.panel = panel;

        panel.add(label);
    }

    public void uptadeText(String info){
        label.setText(info);

        panel.revalidate();
        panel.repaint();
    }
}
