package ba;

/**
 * @author Teemu
 * @version 21.2.2022
 *
 */
public class TilaException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
     * käytettävä viesti
     * @param viesti Poikkeuksen viesti
     */
    public TilaException(String viesti) {
        super(viesti);
    }

}
