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
 * @example
 * <pre name="test">
 * try {
 *  Neighbours neighbours = new Neighbours();
 *  Neighbour neighbour1 = new Neighbour(1,2);
 *  Neighbour neighbour2 = new Neighbour(1,3);
 *  neighbours.getSize() === 0;
 *  neighbours.add(neighbour1); neighbours.getSize() === 1;
 *  neighbours.add(neighbour2); neighbours.getSize() === 2;
 *  neighbours.add(neighbour1); neighbours.getSize() === 3;
 *  neighbours.add(new Neighbour(2,1)); #THROWS TilaException
 *  neighbours.add(new Neighbour(3,3)); #THROWS TilaException 
 *  neighbours.get(0) === neighbour1;
 *  neighbours.get(1) === neighbour2;
 *  neighbours.get(2) === neighbour1; 
 *  neighbours.get(1) == neighbour1 === false;
 *  neighbours.get(1) == neighbour2 === true; 
 *  neighbours.add(neighbour1); neighbours.getSize() === 4;
 *  neighbours.add(neighbour2); neighbours.getSize() === 5;
 *  } catch (TilaException e) { // 
 *  }
 * </pre>
 */
public class Neighbours implements Tietorakenne {
    private ArrayList<Neighbour> pairs = new ArrayList<Neighbour>();
    private boolean altered = false;
    private String fileName = "neighbour.dat";
    
    
    /**
     * lisätään uusi pari listaan
     * @param pair pari joka lisätään
     * @throws TilaException jos yritetään lisätä väärillä aroilla oleva pari
     */
    @Override
    public void add(Object pair) throws TilaException {
        if (this.containsPair((Neighbour) pair)) throw new TilaException("Nämä ovat jo pari " + pair.toString());
        pairs.add((Neighbour) pair);
        this.altered = true;
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
    
    
    /**
     * @param aid alue jonka naapureita etsitään
     * @return naapurien idt
     */
    public ArrayList<Integer> findNeighbourIDs(int aid) {
        var a = new ArrayList<Integer>();
        for (int i = 0; i < pairs.size(); i++) 
            if (pairs.get(i).contains(aid)) 
                a.add(pairs.get(i).getOpposite(aid)); 
        return a;
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
     * @return palautetaan tiedostonimi
     */
    @Override
    public String getFileName() {
         return this.fileName;
     }
    
    
    /**
     * @return parien lukumäärä
     */
    @Override
    public int getSize() {
        return this.pairs.size();
    }
    
    
    /**
     * @return onko tiedostoa muutettu, true jos on 
     */
    @Override
    public boolean isAltered() {
        return this.altered;
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
     * @param name hakemiston nimi josta luetaan
     * @throws TilaException jos ongelmia hakemisessa
     */
    public void readFile(String name) throws TilaException {
        String file = name + "\\" + this.getFileName();
        File f = new File(file);
        try (Scanner fi = new Scanner(new FileInputStream(f))) { 
            while ( fi.hasNext() ) {
                String s = fi.nextLine().trim();
                if ( s == null || "".equals(s) || s.charAt(0) == '#' ) continue;
                this.add(new Neighbour(this.parse(s, 1),  this.parse(s, 2)));
            }
        } catch ( FileNotFoundException e ) {
            throw new TilaException("Ei saa luettua tiedostoa " + file);
        }
    }
    
    
    /**
     * palutetaan alkutilanteeseen
     */
    @Override
    public void resetAltered() {
        this.altered = false;
    }
    
    
    /**
     * vaihdetaan tiedostonimiä (lähinnä testitiedoston luomiseen)
     * @param s tiednimi
     */
    public void setFileName(String s) {
        this.fileName = s;
    }
}
