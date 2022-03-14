package ba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Teemu
 * @version 8.3.2022
 *
 */
public class Neighbours implements Tietorakenne {
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
     * @return onko tiedostoa muutettu, true jos on 
     */
    @Override
    public boolean isAltered() {
        return this.altered;
    }
    
    
    /**
     * @param i indeksi jota etsitään
     * @return löydetty pari
     */
    @Override
    public Neighbour get(int i) {
        return this.pairs.get(i);
    }

    
    /**
     * @return parien lukumäärä
     */
    @Override
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
     * @param name hakemiston nimi josta luetaan
     * @throws TilaException jos ongelmia hakemisessa
     */
    public void readFile(String name) throws TilaException {
        String file = name + "\\" + this.getFileName();
        File f = new File(file);
        try (Scanner fi = new Scanner(new FileInputStream(f))) { // Jotta UTF8/ISO-8859 toimii'
            while ( fi.hasNext() ) {
                String s = fi.nextLine().trim();
                if ( s == null || "".equals(s) || s.charAt(0) == '#' ) continue;
                Neighbour neighbour = new Neighbour(this.parse(s, 1),  this.parse(s, 2));
                this.add(neighbour);
            }
        } catch ( FileNotFoundException e ) {
            throw new TilaException("Ei saa luettua tiedostoa " + file);
        }
    }
    
    
    /**
     * muutetaan merkkijono luokan tiedoiksi
     * @param s merkkijono jota tutkitaan
     * @param i halutu id paikka (Iivo --> 1 tai 2)
     * @return halutun paikan id
     */
    public Integer parse(String s, int i) {
        int[] pair = new int[2];
        StringBuffer sb = new StringBuffer(s);
        pair[0] = Integer.valueOf(Mjonot.erota(sb, '|'));
        pair[1] = Integer.valueOf(Mjonot.erota(sb, '|'));
        return pair[i-1];
    }
    
    /**
     * @return palautetaan tiedostonimi
     */
    @Override
    public String getFileName() {
         return this.fileName;
     }
    
    
    /**
     * palutetaan alkutilanteeseen
     */
    @Override
    public void resetAltered() {
        this.altered = false;
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
