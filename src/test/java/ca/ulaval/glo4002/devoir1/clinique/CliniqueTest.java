package ca.ulaval.glo4002.devoir1.clinique;

import ca.ulaval.glo4002.devoir1.clinique.enumeration.TriageType;
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
public void unPatientMedecinQuandPlacement_FileMedecinNotEmpty() throws Exception {
    Clinique clinique = new Clinique();
    String nom =" jean";
    int gravity = 3;
    clinique.triage(nom, gravity, VisibleSymptom.FLU);

    assertFalse(clinique.queueMedecin_isEmpty());
}

@Test
   public void unPatientRadiologieQuandPlacement_FileRadiologyNotEmpty() throws Exception {
        Clinique clinique = new Clinique();
        String nom =" jean";
        int gravity = 3;
        clinique.triage(nom, gravity, VisibleSymptom.BROKEN_BONE);

        assertFalse(clinique.queueMedecin_isEmpty());
    }

    @Test
    public void unPatientMedecinQuandPlacement_FileRadiology_shouldbeEmpty() throws Exception {
        Clinique clinique = new Clinique();
        String nom =" jean";
        int gravity = 3;
        clinique.triage(nom, gravity, VisibleSymptom.FLU);

        assertTrue(clinique.queueRadiology_isEmpty());
    }
@Test
    public void unPatientMedecinEtRadiologieQuandPlacement_FileRadiology_shouldbeNotEmpty() throws Exception {
        Clinique clinique = new Clinique();
        String nom =" jean";
        int gravity = 3;
        clinique.triage(nom, gravity, VisibleSymptom.BROKEN_BONE);

        assertFalse(clinique.queueRadiology_isEmpty());
        assertFalse(clinique.queueMedecin_isEmpty());
    }

    @Test
    public void unPatientMedecinQuandPlacement_shouldbeFirstPositionQueueMedecin() throws Exception {
        Clinique clinique = new Clinique();
        String nom =" jean";
        int position_expected =0;
        int gravity = 3;
        clinique.triage(nom, gravity, VisibleSymptom.FLU);

        int position = clinique.positionQueueMedecin(nom, gravity, VisibleSymptom.FLU);

        assertEquals(position_expected, position);
    }

    @Test
    public void plusieursPatientsMedecinQuandPlacement_shouldbeInOrderFIFO() throws Exception {
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
    public void unPatientRadiologyQuandPlacement_shouldbeFirstPositionRadiology() throws Exception {
        Clinique clinique = new Clinique();
        String nom =" jean";
        int position_expected =0;
        int gravity = 3;
        clinique.triage(nom, gravity, VisibleSymptom.SPRAIN);

        int position = clinique.positionQueueRadiology(nom, gravity, VisibleSymptom.SPRAIN);

        assertEquals(position_expected, position);
    }

    @Test
    public void plusieursPatientsRadiologieQuandPlacement_shouldbeInOrderFIFO() throws Exception {
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
    @Test
    public void unPatientPlusPrioritaire_QuandTrier_soulbeFisrtpositionThanPatientLessprority() throws Exception {
    Clinique clinique = new Clinique(TriageType.GRAVITY,TriageType.FIFO);

        String nom1 =" jean";
        int position1_expected =1;
        int gravitynom1 = 2;

        clinique.triage(nom1, gravitynom1 , VisibleSymptom.FLU);

        String nom2 =" pierre";
        int position2_expected =0;
        int gravitynom2 = 7;

        clinique.triage(nom2, gravitynom2 , VisibleSymptom.BROKEN_BONE);

        int positionName1 = clinique.positionQueueMedecin(nom1, gravitynom1, VisibleSymptom.FLU);
        int positionName2 = clinique.positionQueueMedecin(nom2, gravitynom2 , VisibleSymptom.BROKEN_BONE);

        assertEquals(position1_expected, positionName1 );
        assertEquals(position2_expected, positionName2 );
    }

    @Test
    public void unPatientPlusPrioritaire_QuandTrier_soulbeFisrtpositionQuueMedecinAndSecondPositionQuueRadiology() throws Exception {
        Clinique clinique = new Clinique(TriageType.GRAVITY,TriageType.FIFO);

        String nom1 =" jean";
        int position1_expected_queue_Medecin =1;
        int position1_expected_queue_Radiologie =0;
        int gravitynom1 = 4;

        clinique.triage(nom1, gravitynom1 , VisibleSymptom.SPRAIN);

        String nom2 =" pierre";
        int position2_expected_queue_Medecin =0;
        int position2_expected_queue_Radiologie =1;
        int gravitynom2 = 7;

        clinique.triage(nom2, gravitynom2 , VisibleSymptom.BROKEN_BONE);

        int positionName1 = clinique.positionQueueMedecin(nom1, gravitynom1, VisibleSymptom.SPRAIN);
        int positionName2 = clinique.positionQueueMedecin(nom2, gravitynom2 , VisibleSymptom.BROKEN_BONE);
        int positionName1_r = clinique.positionQueueRadiology(nom1, gravitynom1, VisibleSymptom.SPRAIN);
        int positionName2_r = clinique.positionQueueRadiology(nom2, gravitynom2 , VisibleSymptom.BROKEN_BONE);

        assertEquals(position1_expected_queue_Medecin, positionName1 );
        assertEquals(position2_expected_queue_Medecin, positionName2 );
        assertEquals(position1_expected_queue_Radiologie, positionName1_r );
        assertEquals(position2_expected_queue_Radiologie, positionName2_r );
    }

    @Test
    public void unPatientPlusPrioritaire_QuandTrier_soulbeFisrtpositionQuueMedecinAndFirstPositionQuueRadiology() throws Exception {
        Clinique clinique = new Clinique(TriageType.GRAVITY,TriageType.GRAVITY);

        String nom1 =" jean";
        int position1_expected_queue_Medecin =1;
        int position1_expected_queue_Radiologie =1;
        int gravitynom1 = 4;

        clinique.triage(nom1, gravitynom1 , VisibleSymptom.SPRAIN);

        String nom2 =" pierre";
        int position2_expected_queue_Medecin =0;
        int position2_expected_queue_Radiologie =0;
        int gravitynom2 = 7;

        clinique.triage(nom2, gravitynom2 , VisibleSymptom.BROKEN_BONE);

        int positionName1 = clinique.positionQueueMedecin(nom1, gravitynom1, VisibleSymptom.SPRAIN);
        int positionName2 = clinique.positionQueueMedecin(nom2, gravitynom2 , VisibleSymptom.BROKEN_BONE);
        int positionName1_r = clinique.positionQueueRadiology(nom1, gravitynom1, VisibleSymptom.SPRAIN);
        int positionName2_r = clinique.positionQueueRadiology(nom2, gravitynom2 , VisibleSymptom.BROKEN_BONE);

        assertEquals(position1_expected_queue_Medecin, positionName1 );
        assertEquals(position2_expected_queue_Medecin, positionName2 );
        assertEquals(position1_expected_queue_Radiologie, positionName1_r );
        assertEquals(position2_expected_queue_Radiologie, positionName2_r );
    }
    @Test
    public void unpatientCoronavirus_quandTrier_devraitGenereException(){
    Clinique clinique = new Clinique();
    String name ="jean";
    int gravity = 1;
    Exception exception = assertThrows(Exception.class, () -> clinique.triage(name,gravity,VisibleSymptom.CORONAVIRUS));
    assertEquals("CORONAVIRUS_IS_NOT_ACCEPTED", exception.getMessage());

    }


}