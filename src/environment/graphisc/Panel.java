package environment.graphisc;

import javax.swing.*;

public class Panel {
    JPanel panel = new JPanel();

    public Panel(JFrame frame){


        panel.setLayout(null);
        frame.add(panel);

    }


    public JPanel getPanel(){
        return panel;
    }

}
