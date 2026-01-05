package view;

import javax.swing.*;

public class LoginPanel extends JPanel {

    public LoginPanel(MainFrame frame) {
        String[] roles = {"Patient", "GP", "Specialist", "Nurse", "Admin"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        JButton loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {
            frame.setRole((String) roleBox.getSelectedItem());
        });

        add(new JLabel("Select Role:"));
        add(roleBox);
        add(loginBtn);
    }
}
