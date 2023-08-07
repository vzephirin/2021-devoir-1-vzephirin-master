package ca.ulaval.glo4002.devoir1.clinique.enumeration;

import ca.ulaval.glo4002.devoir1.clinique.Clinique;

public class CommunityCenter extends Clinique {

    public CommunityCenter(TriageType triageType) {
        super(triageType);
    }

    public void triagePatient(String name, int gravity) {
        this.triage(name,gravity,VisibleSymptom.NAN);
    }

    public boolean fileisEmpty() {
       return  this.queueMedecin_isEmpty();
    }

}
