package ba;

/**
 * @author Teemu
 * @version 15.3.2022
 *
 */
public interface TietorakenneJuoksevallaID extends Tietorakenne {
    /**
     * @param i id joka lisätään
     */
    public void setNextID(int i);

    /**
     * @param s merkkijono josta tiedot pilkotaan
     * @return luodaan uusi
     */
    public Tietue newT(String s);
}
