package model.referral;

import controller.ReferralController;
import model.Referral;
import util.FileUtil;

public class ReferralManager {

    private static ReferralManager instance;
    private ReferralController controller;

    private ReferralManager() {
        controller = new ReferralController();
    }

    public static synchronized ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    public void submitReferral(Referral referral) {
        controller.addReferral(referral);
        generateReferralEmail(referral);
        logAudit(referral.getReferralId(), referral.getStatus());
    }

    public void updateReferralStatus(Referral referral, String status, String specialistNotes) {
        referral.setStatus(status);
        referral.setSpecialistNotes(specialistNotes);
        controller.updateReferral(referral);
        logAudit(referral.getReferralId(), status);
    }

    private void generateReferralEmail(Referral referral) {
        String email =
                "To: " + referral.getSpecialistId() + "\n" +
                        "From: " + referral.getGpId() + "\n" +
                        "Patient: " + referral.getPatientId() + "\n" +
                        "Reason: " + referral.getReason() + "\n";

        FileUtil.appendToFile("data/referral_emails.txt", email);
    }

    private void logAudit(String referralId, String status) {
        FileUtil.appendToFile("data/referral_audit.txt", referralId + "," + status);
    }
}
