package ca.ulaval.glo4002.devoir1.clinique;

import ca.ulaval.glo4002.devoir1.clinique.enumeration.VisibleSymptom;

import java.util.ArrayList;
import java.util.List;


public class Clinique {
    List <Patient> queueListMedecin = new ArrayList<>();
    List<Patient>queueListRadilody= new ArrayList<>();

    public boolean queueRadiology_isEmpty() {
        return queueListRadilody.isEmpty();
    }

    public boolean queueMedecin_isEmpty() {
        return queueListMedecin.isEmpty();
    }

    public void triage(String nom, int gravity, VisibleSymptom visibleSymptom) {
       Patient patient = new Patient(nom,gravity,visibleSymptom);
        if (visibleSymptom.equals(VisibleSymptom.BROKEN_BONE) || visibleSymptom.equals(VisibleSymptom.SPRAIN)) {
            this.queueListRadilody.add(patient);
        }
        this.queueListMedecin.add(patient);

    }

    public int positionQueueMedecin(String nom, int gravity, VisibleSymptom visibleSymptom) {
        Patient patient = new Patient(nom,gravity,visibleSymptom);
        int position = this.queueListMedecin.indexOf(patient);
        return position;
    }

    public int positionQueueRadiology(String nom, int gravity, VisibleSymptom visibleSymptom) {
        Patient patient = new Patient(nom,gravity,visibleSymptom);
        int position = this.queueListRadilody.indexOf(patient);
        return position;
    }
}
