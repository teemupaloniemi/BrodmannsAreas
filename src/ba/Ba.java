package ba;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Huolehtii Locations, Functions, Lfs ja Neighbours luokkien
 * välisestä yhteistyöstä ja välittää näitä tietoja pyydettäessä                       
 * @author Teemu
 * @version 22.2.2022
 *
 */
public class Ba {
    
    private Areas areas = new Areas();
    private Locations locations = new Locations();
    private ba.Functions functions = new ba.Functions();
    private Lfs lfs = new Lfs();
    private Neighbours neighbours = new Neighbours();
        
    
    /**
     * @return alueiden lukumäärän
     */
    public int getAreaCount() {
        return areas.getSize();
    }
    
    
    /**
     * @return naapuriparien joukon lukumäärä
     */
    public int getNeighbourCount() {
        return this.neighbours.getSize();
    }
    
    
    /**
     * @return tiedettävien tehtävien määrä  
     */
    public int getFunctionCount() {
        return this.functions.getSize();
    }
    
    
    /**
     * @return alueiden lukumäärän
     */
    public int getLocationCount() {
        return this.locations.getSize();
    }
    
    
    /**
     * @return parien lukumäärän
     */
    public int getLfCount() {
        return this.lfs.getSize();
    }


    /**
     * @param area lisattava alue
     * @example
     * <pre name="test">
     * Ba ba = new Ba();
     * Area a1 = new Area(), a2 = new Area();
     * a1.register(); a2.register();
     * ba.getAreaCount() === 0;
     * ba.add(a1); ba.getAreaCount() === 1;
     * ba.add(a2); ba.getAreaCount() === 2;
     * ba.add(a1); ba.getAreaCount() === 3;
     * ba.getArea(0) === a1;
     * ba.getArea(1) === a2;
     * ba.getArea(2) === a1;
     * ba.getArea(3) === a1; #THROWS IndexOutOfBoundsException 
     * ba.add(a1); ba.getAreaCount() === 4;
     * ba.add(a1); ba.getAreaCount() === 5;
     * </pre>
     */
    public void add(Area area) {
         this.areas.add(area);
    }
    
    
    /**
     * @param location lisattava sijainti
     */
    public void add(Location location) {
         this.locations.add(location);
    }
    
    
    /**
     * @param function Lisättävä tehtävä
     */
    public void add(Function function) {
        this.functions.add(function);
    }
    
    
    /**
     * @param lf lisattava pari
     */
    public void add(Lf lf){
        this.lfs.add(lf);
    }
    
    
    
    /**
     * Lukee ba tiedot tiedostosta
     * @throws TilaException jos lukeminen epäonnistuu
     */
    public void readFile() throws TilaException {
        String name = "tiedostot";
        File dir = new File(name);
        dir.mkdir();
        this.areas = new Areas(); // jos luetaan olemassa olevaan niin helpoin tyhjentää näin
        this.functions = new Functions();
        this.locations = new Locations();
        this.lfs = new Lfs();
        this.neighbours = new Neighbours();
        System.gc();
        
        this.areas.readFile(name);
        this.functions.readFile(name);
        this.locations.readFile(name);
        this.lfs.readFile(name);
        this.neighbours.readFile(name);
    }

    
    
    /**
     * @param n lisattava pari
     * @throws TilaException jos naapurit on jo olemassa
     */
    public void add(Neighbour n) throws TilaException {
        if (neighbours.containsPair(n)) throw new TilaException("Nämä ovat jo naapurit!"); 
        this.neighbours.add(n);
    }
    
    
    /**
     * @param i etsittävän alueen indeksi
     * @return etsitty alue
     */
    public Area getArea(int i) {
        return areas.get(i);
    }
    
    
    /**
     * @param i etsittävän alueen indeksi
     * @return etsitty alue
     * @throws IndexOutOfBoundsException jos kutsutaan laittomalla indeksillä
     */
    public Location getLocation(int i) throws IndexOutOfBoundsException {
        return locations.get(i);
    }
    
    
    /**
     * @param fid tehtävä jota etsitään
     * @return tehtävän jos se löytyy 
     */
    public Function getFunction(int fid) {
        return functions.get(fid);
    }
    
    
    /**
     * @param i kuinka mones pari 
     * @return yksi pari muiden joukosta
     */
    public Lf getLf(int i) {
        return lfs.get(i);
    }
    
    
    /**
     * @param i kuinka mones naapuripari 
     * @return naapur pari joka haluttiin
     */
    public Neighbour getNeighbour(int i) {
        return neighbours.get(i);
    }
    
    
    /**
     * @param fid tehtävä jonka paria etsitään
     * @return sijainti joka vastaa tehtävästä 
     */
    public Location findLocationID(int fid) {
        return this.getLocation(this.lfs.findLocationID(fid));
    }
    
    
    /**
     * @param lid sijainti jonka tehtäviä etsitää
     * @return tehtävät joita sijainti hoitaa 
     */
    public ArrayList<Function> findFunctionIDs(int lid) {
        ArrayList<Integer> ids = this.lfs.findFunctionIDs(lid);
        ArrayList<Function> f = new ArrayList<Function>();
        for (int i = 0; i < ids.size(); i++) f.add(this.getFunction(ids.get(i)));
        return f;
    }
    
    
    /**
     * @param aid sijainti jonka tehtäviä etsitää
     * @return tehtävät joita sijainti hoitaa 
     */
    public ArrayList<Area> findNeighbourIDs(int aid) {
        ArrayList<Integer> ids = this.neighbours.findNeighbourIDs(aid);
        ArrayList<Area> a = new ArrayList<Area>();
        for (int i = 0; i < ids.size(); i++) a.add(this.getArea(ids.get(i)));
        return a;
    }
   
    
    /**
     * Tallennetaan tiedot
     * @param fn hakemisto johon tallennetaan
     * @param t tietorakenne joka tallennetaan 
     * @throws TilaException jos tallennuksessa tapahtuu virheitä
     */
    private void save(String fn, Tietorakenne t) throws TilaException {
            if ( !t.isAltered() ) return; // jos ei muutetttu ei kannata tallentaa 
            File file = new File(fn + "//" + t.getFileName()); // uusi tiedosto nimellä varustettuna
            try (PrintStream ps = new PrintStream(new FileOutputStream(file, false))) { // avataan tiedosto kirjoittamista varten
                for (int i = 0; i < t.getSize(); i++) { // käydään alueiston alueet läpi  
                    ps.println(t.get(i).toString()); // valitaan kohdalla oleva alue ja tallennetaan se tiedostoon
                }
            } catch (FileNotFoundException e) {
                throw new TilaException("Tiedosto " + file.getAbsolutePath() + " ei aukea!");
            }
            t.resetAltered(); // nyt on tallennettu eli ei muutoksia 
    }

    
    /**
     * tallennetaan kaikki tämän luokan tietorakenteet
     * @param fn hakemisto johon tallennetaan 
     * @throws TilaException jos tallennuksessa ongelmia
     */
    public void saveAll(String fn) throws TilaException {
        try {
            this.save(fn, this.areas);
            this.save(fn, this.locations);
            this.save(fn, this.functions);
            this.save(fn, this.lfs);
            this.save(fn, this.neighbours);
        } catch (TilaException e) {
            throw e;
        }
    }

    
    /**
     * Testiohjelma Ba-luokalle
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Ba ba = new Ba();
        Area a1     = new Area().register().fillAreaInfo();
        Area a2     = new Area().register().fillAreaInfo();
        
        Location l1 = new Location().register().fillLocationInfo();
        Location l2 = new Location().register().fillLocationInfo();
        Location l3 = new Location().register().fillLocationInfo();
        
        Function f1 = new Function().register().fillFunctionInfo();
        Function f2 = new Function().register().fillFunctionInfo();
        Function f3 = new Function().register().fillFunctionInfo();
        Function f4 = new Function().register().fillFunctionInfo();
        Function f5 = new Function().register().fillFunctionInfo();
        Function f6 = new Function().register().fillFunctionInfo();
        
        // Tämä pitäisi tapahtua automaattisesti kun lisätään funktio
        Lf lf1 = new Lf(l1.getLid(), f1.getFid());
        
        Lf lf2 = new Lf(l2.getLid(), f2.getFid());
        Lf lf3 = new Lf(l2.getLid(), f3.getFid());
        Lf lf4 = new Lf(l2.getLid(), f4.getFid());
        
        Lf lf5 = new Lf(l3.getLid(), f5.getFid());
        Lf lf6 = new Lf(l3.getLid(), f6.getFid());
        
        a1.setLid(l2.getLid());
        a2.setLid(l3.getLid());
        
        ba.add(a1);
        ba.add(a2);
        
        ba.add(l1);
        ba.add(l2);
        ba.add(l3);
        
        ba.add(f1);
        ba.add(f2);
        ba.add(f3);
        ba.add(f4);
        ba.add(f5);
        ba.add(f6);
        
        // Tämä pitäisi tapahtua automaattisesti kun lisätään funktio
        ba.add(lf1);
        ba.add(lf2);
        ba.add(lf3);
        ba.add(lf4);
        ba.add(lf5);
        ba.add(lf6);

        for (int i = 0; i < ba.getAreaCount(); i++) {
            int lid = ba.areas.get(i).getLid();
            ba.areas.get(i).print(System.out);
            ba.locations.get(lid).print(System.out);
            ArrayList<Function> f = ba.findFunctionIDs(lid);
            for (int j = 0; j < f.size(); j++) f.get(j).print(System.out);
            System.out.println("-------------------------------");
        }
        
    }
}
