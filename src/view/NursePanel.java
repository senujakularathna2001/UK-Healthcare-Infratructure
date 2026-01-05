package view;

import javax.swing.*;

public class NursePanel extends JPanel {

    public NursePanel() {
        JButton btn = new JButton("Update Treatment Note");

        btn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Treatment Note Updated")
        );

        add(btn);
    }
}
