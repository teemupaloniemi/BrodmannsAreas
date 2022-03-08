package ba;

import java.util.ArrayList;

/**
 * @author Teemu
 * @version 8.3.2022
 *
 */
public class Lfs {
    private ArrayList<Lf> pairs = new ArrayList<Lf>();
    
    
    /**
     * lisätään uusi pari listaan
     * @param pair pari joka lisätään
     */
    public void add(Lf pair) {
        pairs.add(pair);
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
}
