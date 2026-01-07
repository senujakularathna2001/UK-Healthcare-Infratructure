package view;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel(MainFrame frame) {

        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5,5,5,5);

        String[] roles = {"Patient", "GP", "Specialist", "Nurse", "Admin"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        JButton loginBtn = new JButton("Login");

        g.gridx = 0; g.gridy = 0;
        add(new JLabel("Role:"), g);

        g.gridx = 1;
        add(roleBox, g);

        g.gridx = 1; g.gridy = 1;
        add(loginBtn, g);

        loginBtn.addActionListener(e ->
                frame.setRole(roleBox.getSelectedItem().toString())
        );
    }
}
