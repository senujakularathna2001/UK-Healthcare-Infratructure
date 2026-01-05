package controller;

import model.Prescription;
import util.FileUtil;

public class PrescriptionController {

    public void savePrescription(Prescription p) {
        FileUtil.appendToFile("data/prescriptions.csv", p.toFileString());
    }
}
