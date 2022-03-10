package ba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Teemu
 * @version 8.3.2022
 *
 */
public class Lfs {
    private ArrayList<Lf> pairs = new ArrayList<Lf>();
    private boolean altered = false;
    private String fileName = "lf.dat";
    
    
    /**
     * lisätään uusi pari listaan
     * @param pair pari joka lisätään
     */
    public void add(Lf pair) {
        pairs.add(pair);
        this.altered = true;
    }
    
    
    /**
     * @param i indeksi jota etsitään
     * @return löydetty pari
     */
    public Lf get(int i) {
        return this.pairs.get(i);
    }

    
    /**
     * @return parien lukumäärä
     */
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
     * tallennetaan tiedot
     * @throws TilaException jos tallennuksessa ongelmia
     */
    public void save() throws TilaException {
        if ( !altered ) return;
        File file = new File("tiedostot//" + this.getFileName());
        try (PrintStream ps = new PrintStream(new FileOutputStream(file, false))) {
            for (int i = 0; i < this.getSize(); i++) {
                Lf lf = this.get(i);
                ps.println(lf.toString());
            }
        } catch (FileNotFoundException e) {
            throw new TilaException("Tiedosto " + file.getAbsolutePath() + " ei aukea!");
        }
        altered = false;
    } 
}
