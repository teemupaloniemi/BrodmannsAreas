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
 *  Lfs lfs = new Lfs();
 *  Lf lf1 = new Lf(1,2);
 *  Lf lf2 = new Lf(1,3);
 *  Lf lf3 = new Lf(2,1); 
 *  lfs.getSize() === 0;
 *  lfs.add(lf1); lfs.getSize() === 1;
 *  lfs.add(lf2); lfs.getSize() === 2;
 *  lfs.add(lf3); lfs.getSize() === 3;
 *  lfs.get(0) === lf1;
 *  lfs.get(1) === lf2;
 *  lfs.get(2) === lf3; 
 *  lfs.get(1) == lf1 === false;
 *  lfs.get(1) == lf2 === true; 
 *  lfs.add(lf1); lfs.getSize() === 4;
 *  lfs.add(lf2); lfs.getSize() === 5;
 *  } catch (TilaException e) {//
 *  }
 * </pre>
 */
public class Lfs implements Tietorakenne {
    private ArrayList<Lf> pairs = new ArrayList<Lf>();
    private boolean altered = false;
    private String fileName = "lf.dat";
    
    
    /**
     * lisätään uusi pari listaan
     * @param pair pari joka lisätään
     * @throws TilaException jos pari on jo olemassa
     */
    public void add(Lf pair) throws TilaException {
        if (this.containsPair(pair)) throw new TilaException("Nämä ovat jo pari " + pair.toString());
        pairs.add(pair);
        this.altered = true;
    }

    
    /**
     * Tarkistetaan onko paria jo olemassa
     * @param lf pari jota etsitään
     * @return true jos pari on jo olemassa
     */
    public boolean containsPair(Lf lf) {
        for (int i = 0; i < this.getSize(); i++) 
            if (this.get(i).hashCode() == lf.hashCode()) return true;
        return false;
    }
    
    
    /**
     * vaihdetaan tiedostonimiä (lähinnä testitiedoston luomiseen)
     * @param s tiednimi
     */
    public void setFileName(String s) {
        this.fileName = s;
    }
    
    
    /**
     * @param i indeksi jota etsitään
     * @return löydetty pari
     * 
     */
    @Override
    public Lf get(int i) {
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
     * @param fid tehtävä jota vastaavaa aluetta etsitään
     * @return alue jolle tehtävä kuuluu
     */
    public int findLocationID(int fid) {
        for (int i = 0; i < pairs.size(); i++) {
            if (pairs.get(i).getFunction() == fid) return pairs.get(i).getLocation(); 
        }
        return -1;
    }
    
    
    /**
     * @param lid alue jonka tehtäviä etsitään
     * @return tehtävät jota alue hoitaa
     */
    public ArrayList<Integer> findFunctionIDs(int lid) {
        var l = new ArrayList<Integer>();
        for (int i = 0; i < pairs.size(); i++) {
            if (pairs.get(i).getLocation() == lid) l.add(pairs.get(i).getFunction()); 
        }
        return l;
    }
    
    
    /**
     * @return palautetaan tiedostonimi
     */
    @Override
    public String getFileName() {
         return this.fileName;
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
                Lf lf = new Lf(this.parse(s, 1),  this.parse(s, 2));
                this.add(lf);
            }
        } catch ( FileNotFoundException e ) {
            throw new TilaException("Ei saa luettua tiedostoa " + file);
        }
    }
    
    
    /**
     * @return onko tiedostoa muutettu, true jos on 
     */
    @Override
    public boolean isAltered() {
        return this.altered;
    }
    
    
    /**
     * palutetaan alkutilanteeseen
     */
    @Override
    public void resetAltered() {
        this.altered = false;
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
}
