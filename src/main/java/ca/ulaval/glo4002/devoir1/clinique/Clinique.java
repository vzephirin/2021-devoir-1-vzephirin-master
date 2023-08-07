package ca.ulaval.glo4002.devoir1.clinique;

import ca.ulaval.glo4002.devoir1.clinique.enumeration.TriageType;
import ca.ulaval.glo4002.devoir1.clinique.enumeration.VisibleSymptom;

import java.util.ArrayList;
import java.util.List;


public class Clinique {
    private TriageType typeTriageMedecin;
    private TriageType typeTriageRadiokogy;
    List <Patient> queueListMedecin = new ArrayList<>();
    List<Patient>queueListRadilody= new ArrayList<>();

    public Clinique(TriageType triageType, TriageType triageType1) {
        this.typeTriageMedecin = triageType;
        this.typeTriageRadiokogy = triageType1;
    }
    public Clinique(){
        this.typeTriageMedecin = TriageType.FIFO;
        this.typeTriageRadiokogy = TriageType.FIFO;

    }

    public boolean queueRadiology_isEmpty() {
        return queueListRadilody.isEmpty();
    }

    public boolean queueMedecin_isEmpty() {
        return queueListMedecin.isEmpty();
    }

    public void triage(String nom, int gravity, VisibleSymptom visibleSymptom) {
       Patient patient = new Patient(nom,gravity,visibleSymptom);

        if (visibleSymptom.equals(VisibleSymptom.BROKEN_BONE) || visibleSymptom.equals(VisibleSymptom.SPRAIN)) {
            if (this.typeTriageRadiokogy == TriageType.FIFO) {
                triage_fifo(patient,this.queueListRadilody);
            }
            else{
                triage_gravity(patient,this.queueListRadilody);
            }
        }

        if (this.typeTriageMedecin == TriageType.FIFO) {
            triage_fifo(patient,this.queueListMedecin);
        }
        else{
            triage_gravity(patient,this.queueListMedecin);
        }
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

    private void triage_gravity (Patient p, List<Patient> queue){
        if(p.getGravity() < 5) {
            triage_fifo(p,queue);
        }
        else {
            for (int i=0; i< queue.size(); i++) {
                if(p.getGravity() > queue.get(i).getGravity()){
                    queue.add(i,p);
                    break;
                }
            }

            }
        }
    private void triage_fifo (Patient p, List<Patient> queue){
        queue.add(p);
    }
}
