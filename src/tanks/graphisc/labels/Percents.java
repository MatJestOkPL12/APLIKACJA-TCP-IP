package tanks.graphisc.labels;

import javax.swing.*;

public class Percents {

    JLabel label = new JLabel("0%");
    JPanel panel;


    public Percents(JPanel panel){
        label.setBounds(550,150,60,20);
        this.panel = panel;
        panel.add(label);
    }

    public void uptadeText(String info){
        label.setText(info+"%");

        panel.revalidate();
        panel.repaint();
    }

}
