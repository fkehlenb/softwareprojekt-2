package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.ProzessKettenVorlage;
import de.unibremen.sfb.Model.ProzessSchrittVorlage;
import java.util.Set;

public class ProzessKettenVorlageController {
    public ProzessKettenVorlage pkv;

    /**
     * Setzt die ID dieser ProzessKettenVorlage.
     *
     * @param id Die neue ID
     */
    public void setID(int id) {}

    /**
     * Gibt die ID dieser ProzessKettenVorlage zurück.
     *
     * @return Die ID
     */
    public int getID() { return 0; }

    /**
     * Setzt die ProzessSchrittVorlagen, aus denen diese ProessKettenVorlage besteht.
     *
     * @param psv Ein Set mit den neuen ProzessSchrittVorlagen
     */
    public void setPSV(Set<ProzessSchrittVorlage> psv) {}

    /**
     * Gibt die ProzessSchrittVorlagen zurück, aus denen diese ProzessKettenVorlage besteht.
     *
     * @return Ein Set mit den ProzessSchrittVorlagen.
     */
    public Set<ProzessSchrittVorlage> getPSV() { return null; }
}
