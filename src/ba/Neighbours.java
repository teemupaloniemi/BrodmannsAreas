package ba;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * @author Teemu
 * @version 8.3.2022
 *
 */
public class Neighbours {
    private ArrayList<Neighbour> pairs = new ArrayList<Neighbour>();
    private boolean altered = false;
    private String fileName = "neighbour.dat";
    
    
    /**
     * lisätään uusi pari listaan
     * @param pair pari joka lisätään
     */
    public void add(Neighbour pair) {
        pairs.add(pair);
        this.altered = true;
    }
    
    
    /**
     * @param i indeksi jota etsitään
     * @return löydetty pari
     */
    public Neighbour get(int i) {
        return this.pairs.get(i);
    }

    
    /**
     * @return parien lukumäärä
     */
    public int getSize() {
        return this.pairs.size();
    }
    
    
    /**
     * @param aid alue jonka naapureita etsitään
     * @return naapurien idt
     */
    public ArrayList<Integer> findNeighbourIDs(int aid) {
        var a = new ArrayList<Integer>();
        for (int i = 0; i < pairs.size(); i++) {
            if (pairs.get(i).getAreaFirst() == aid) a.add(pairs.get(i).getAreaSecond()); 
        }
        return a;
    }
    
    
    /**
     * @return palautetaan tiedostonimi
     */
    public String getFileName() {
         return this.fileName;
     }
    
    
    /**
     * tallennetaan tiedot
     * @throws TilaException jos tallennuksessa ongelmia
     */
    public void save() throws TilaException {
        if ( !altered ) return;
        File file = new File("tiedostot//" + this.getFileName());
        try (PrintStream ps = new PrintStream(new FileOutputStream(file, false))) {
            for (int i = 0; i < this.getSize(); i++) {
                Neighbour neighbour = this.get(i);
                ps.println(neighbour.toString());
            }
        } catch (FileNotFoundException e) {
            throw new TilaException("Tiedosto " + file.getAbsolutePath() + " ei aukea!");
        }
        altered = false;
    } 


    /**
     * Tarkistetaan onko paria jo olemassa
     * @param n pari jota etsitään
     * @return true jos pari on jo olemassa
     */
    public boolean containsPair(Neighbour n) {
        for (int i = 0; i < this.getSize(); i++) if (this.get(i).hashCode() == n.hashCode()) return true;
        return false;
    }
}
