package ca.ulaval.glo4002.devoir1.clinique.enumeration;

import ca.ulaval.glo4002.devoir1.clinique.CommunityCenter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommunityCenterTest {
    @Test
    public void QuandInitialiserFileAttente_shoulbeVide(){
        CommunityCenter comunity = new CommunityCenter(TriageType.FIFO);
        assertTrue(comunity.fileisEmpty());
    }
@Test
    public void unpatientCommunity_QuandTrier_shoulbeNotEmpty() throws Exception {
    CommunityCenter comunity = new CommunityCenter(TriageType.FIFO);
    String name ="Jean";
    int gravity = 1;
    comunity.triagePatient(name, gravity);
    assertFalse(comunity.fileisEmpty());
}
@Test
    public void plusieurspatientCommunity_QuandTrier_soulbeInOrderFIFO() throws Exception {
    CommunityCenter community = new CommunityCenter(TriageType.FIFO);
    String name ="Jean";
    int gravity = 4;
    int position_expeted = 0;
    community.triagePatient(name, gravity);
    String name2 ="Jean";
    int gravity2 = 7;
    int position_expeted_2 = 1;
    community.triagePatient(name2, gravity2);
    int position_1 = community.position(name, gravity);
    int position_2 = community.position(name2, gravity2);


    assertEquals(position_expeted,position_1);
    assertEquals(position_expeted_2,position_2);
}

    @Test
    public void plusieurspatientCommunity_QuandTrier_soulbeInOrderGravity() throws Exception {
        CommunityCenter community = new CommunityCenter(TriageType.GRAVITY);
        String name ="Jean";
        int gravity = 4;
        int position_expeted = 1;
        community.triagePatient(name, gravity);
        String name2 ="Pierre";
        int gravity2 = 7;
        int position_expeted_2 = 0;
        community.triagePatient(name2, gravity2);
        int position_1 = community.position(name, gravity);
        int position_2 = community.position(name2, gravity2);


        assertEquals(position_expeted,position_1);
        assertEquals(position_expeted_2,position_2);
    }
}