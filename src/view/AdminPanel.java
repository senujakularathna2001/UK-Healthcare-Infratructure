package view;

import javax.swing.*;

public class AdminPanel extends JPanel {

    public AdminPanel() {
        JButton reportBtn = new JButton("Generate Report");

        reportBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Operational Report Generated")
        );

        add(reportBtn);
    }
}
