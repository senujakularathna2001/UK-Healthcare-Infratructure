package view;

import model.Referral;
import model.referral.ReferralManager;

import javax.swing.*;

public class ReferralPanel extends JPanel {

    public ReferralPanel() {
        JButton btn = new JButton("Create Referral");

        btn.addActionListener(e -> {
            Referral r = new Referral(
                    "R001",     // referral ID
                    "C001",     // GP ID
                    "C005",     // Specialist ID
                    "P001"      // Patient ID
            );

            ReferralManager.getInstance().addReferral(r);
            ReferralManager.getInstance().generateReferralEmail(r);

            JOptionPane.showMessageDialog(this, "Referral Created");
        });

        add(btn);
    }
}
