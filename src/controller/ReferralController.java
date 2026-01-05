package controller;

import model.Referral;
import model.referral.ReferralManager;

public class ReferralController {

    public void createReferral(Referral referral) {
        ReferralManager.getInstance().addReferral(referral);
    }
}
