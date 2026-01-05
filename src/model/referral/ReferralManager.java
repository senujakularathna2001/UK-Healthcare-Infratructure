package model.referral;

import model.Referral;
import util.FileUtil;
import java.util.ArrayList;
import java.util.List;

public class ReferralManager {

    private static ReferralManager instance;
    private List<Referral> referrals;

    private ReferralManager() {
        referrals = new ArrayList<>();
    }

    public static ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    public void addReferral(Referral referral) {
        referrals.add(referral);
        FileUtil.appendToFile("data/referrals.txt", referral.toFileString());
    }

    public List<Referral> getReferrals() {
        return referrals;
    }

    public void generateReferralEmail(Referral referral) {
        String email =
                "To: Specialist ID " + referral.getSpecialistID() + "\n" +
                        "From: GP ID " + referral.getGpID() + "\n" +
                        "Patient ID: " + referral.getPatientID() + "\n" +
                        "Status: " + referral.getStatus() + "\n" +
                        "Please review patient records.\n";

        FileUtil.appendToFile("data/referral_emails.txt", email);
    }

}
