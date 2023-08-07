package ca.ulaval.glo4002.devoir1.clinique;

import org.junit.jupiter.api.Test;
import ca.ulaval.glo4002.devoir1.clinique.enumeration.VisibleSymptom;

import static org.junit.jupiter.api.Assertions.*;

class CliniqueTest {
@Test
    public void QuandCliniqueInitialementVide_FileRadilogyEmpty(){
    Clinique clinique = new Clinique();

    boolean statutClinique = clinique.queueRadiology_isEmpty();

    assertTrue(statutClinique);
}
@Test
    public void QuandCliniqueInitialementVide_FileMedecinEmpty(){
    Clinique clinique = new Clinique();

    boolean statutClinique = clinique.queueMedecin_isEmpty();

    assertTrue(statutClinique);
}
@Test
public void unPatientMedecinQuandPlacement_FileMedecinNotEmpty(){
    Clinique clinique = new Clinique();
    String nom =" jean";
    int gravity = 3;
    clinique.triage(nom, gravity, VisibleSymptom.FLU);

    assertFalse(clinique.queueMedecin_isEmpty());
}

@Test
   public void unPatientRadiologieQuandPlacement_FileRadiologyNotEmpty(){
        Clinique clinique = new Clinique();
        String nom =" jean";
        int gravity = 3;
        clinique.triage(nom, gravity, VisibleSymptom.BROKEN_BONE);

        assertFalse(clinique.queueMedecin_isEmpty());
    }

    @Test
    public void unPatientMedecinQuandPlacement_FileRadiology_shouldbeEmpty(){
        Clinique clinique = new Clinique();
        String nom =" jean";
        int gravity = 3;
        clinique.triage(nom, gravity, VisibleSymptom.FLU);

        assertTrue(clinique.queueRadiology_isEmpty());
    }
@Test
    public void unPatientMedecinEtRadiologieQuandPlacement_FileRadiology_shouldbeNotEmpty(){
        Clinique clinique = new Clinique();
        String nom =" jean";
        int gravity = 3;
        clinique.triage(nom, gravity, VisibleSymptom.BROKEN_BONE);

        assertFalse(clinique.queueRadiology_isEmpty());
        assertFalse(clinique.queueMedecin_isEmpty());
    }

    @Test
    public void unPatientMedecinQuandPlacement_shouldbeFirstPositionQueueMedecin(){
        Clinique clinique = new Clinique();
        String nom =" jean";
        int position_expected =0;
        int gravity = 3;
        clinique.triage(nom, gravity, VisibleSymptom.FLU);

        int position = clinique.positionQueueMedecin(nom, gravity, VisibleSymptom.FLU);

        assertEquals(position_expected, position);
    }

    @Test
    public void plusieursPatientsMedecinQuandPlacement_shouldbeInOrderFIFO(){
        Clinique clinique = new Clinique();
        String nom1 =" jean";
        int position1_expected =0;
        int gravity = 3;
        clinique.triage(nom1, gravity, VisibleSymptom.FLU);

        String nom2 =" pierre";
        int position2_expected =1;


        clinique.triage(nom2, gravity, VisibleSymptom.FLU);

        int positionName1 = clinique.positionQueueMedecin(nom1, gravity, VisibleSymptom.FLU);
        int positionName2 = clinique.positionQueueMedecin(nom2, gravity, VisibleSymptom.FLU);

        assertEquals(position1_expected, positionName1 );
        assertEquals(position2_expected, positionName2 );
    }

    @Test
    public void unPatientRadiologyQuandPlacement_shouldbeFirstPositionRadiology(){
        Clinique clinique = new Clinique();
        String nom =" jean";
        int position_expected =0;
        int gravity = 3;
        clinique.triage(nom, gravity, VisibleSymptom.SPRAIN);

        int position = clinique.positionQueueRadiology(nom, gravity, VisibleSymptom.SPRAIN);

        assertEquals(position_expected, position);
    }

    @Test
    public void plusieursPatientsRadiologieQuandPlacement_shouldbeInOrderFIFO(){
        Clinique clinique = new Clinique();
        String nom1 =" jean";
        int position1_expected =0;
        int gravity = 3;
        clinique.triage(nom1, gravity, VisibleSymptom.SPRAIN);

        String nom2 =" pierre";
        int position2_expected =1;


        clinique.triage(nom2, gravity, VisibleSymptom.BROKEN_BONE);

        int positionName1 = clinique.positionQueueRadiology(nom1, gravity, VisibleSymptom.SPRAIN);
        int positionName2 = clinique.positionQueueRadiology(nom2, gravity, VisibleSymptom.BROKEN_BONE);

        assertEquals(position1_expected, positionName1 );
        assertEquals(position2_expected, positionName2 );
    }

}