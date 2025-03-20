package rivers.graphisc.labels;

import javax.swing.*;

public class Info {

    JLabel label = new JLabel("Ilość wody w rzece");
    JLabel water = new JLabel("0");
    JPanel panel;


    public Info(JPanel panel){

        this.panel = panel;
        label.setBounds(200,180,200,20);
        water.setBounds(250,210,80,20);
        panel.add(label);
        panel.add(water);


    }

    public void updateWater(String water){
        this.water.setText(water);

        panel.revalidate();
        panel.repaint();
    }

}
