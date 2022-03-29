package ba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

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
     * @param area lisattava alue
     * @throws TilaException jos jo olemassa
     * @example
     * <pre name="test">
     * #THROWS TilaException
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
    public void add(Area area) throws TilaException {
         this.areas.add(area);
    }
    
    
    /**
     * @param function Lisättävä tehtävä
     * @throws TilaException jos on jo olemassa
     * @example
     * <pre name="test">
     * #THROWS TilaException
     * Ba ba = new Ba();
     * Function f1 = new Function(), f2 = new Function();
     * f1.register(); f2.register();
     * ba.getFunctionCount() === 0;
     * ba.add(f1); ba.getFunctionCount() === 1;
     * ba.add(f2); ba.getFunctionCount() === 2;
     * ba.add(f1); ba.getFunctionCount() === 3;
     * ba.getFunction(0) === f1;
     * ba.getFunction(1) === f2;
     * ba.getFunction(2) === f1;
     * ba.getFunction(3) === f1; #THROWS IndexOutOfBoundsException 
     * ba.add(f1); ba.getFunctionCount() === 4;
     * ba.add(f1); ba.getFunctionCount() === 5;
     * </pre>
     */
    public void add(Function function) throws TilaException {
        this.functions.add(function);
    }
    
    
    /**
     * @param lf lisattava pari
     * @throws TilaException jos kutsutaan parilla joka on jo olemassa
     * @example
     * <pre name="test">
     * #THROWS TilaException
     * try {
     *  Ba ba = new Ba();
     *  Location l1 = new Location().register();
     *  Location l2 = new Location().register();
     *  Function f1 = new Function().register();
     *  Function f2 = new Function().register();
     *  Function f3 = new Function().register();
     *  ba.add(l1);
     *  ba.add(l2);
     *  ba.add(f1);
     *  ba.add(f2);
     *  ba.add(f3);
     *  Lf lf1 = new Lf(1,2);
     *  Lf lf2 = new Lf(2,5);
     *  Lf lf3 = new Lf(1,3);
     *  ba.getLfCount() === 0;
     *  ba.add(lf1); ba.getLfCount() === 1;
     *  ba.add(lf2); ba.getLfCount() === 2;
     *  ba.add(lf3); ba.getLfCount() === 3;
     *  ba.add(new Lf(1,2)); #THROWS TilaException //jo olemasso oleva pari
     *  ba.findLocationFor(5).getID() === 1;
     *  } catch (TilaException e) {//
     *  }
     * </pre>
     */
    public void add(Lf lf) throws TilaException{
        this.lfs.add(lf);
    }
    
   
    
    /**
     * @param location lisattava lohko
     * @throws TilaException jos nimi jo olemassa
     * @example
     * <pre name="test">
     * #THROWS TilaException
     * Ba ba = new Ba();
     * Location l1 = new Location(), l2 = new Location();
     * l1.register(); l2.register();
     * ba.getLocationCount() === 0;
     * ba.add(l1); ba.getLocationCount() === 1;
     * ba.add(l2); ba.getLocationCount() === 2;
     * ba.add(l1); ba.getLocationCount() === 3;
     * ba.getLocation(0) === l1;
     * ba.getLocation(1) === l2;
     * ba.getLocation(2) === l1;
     * ba.getLocation(3) === l1; #THROWS IndexOutOfBoundsException 
     * ba.add(l1); ba.getLocationCount() === 4;
     * ba.add(l1); ba.getLocationCount() === 5;
     * </pre>
     */
    public void add(Location location) throws TilaException {
         this.locations.add(location);
    }
    
    
    /**
     * @param n lisattava pari
     * @throws TilaException jos naapurit on jo olemassa
     * @example
     * <pre name="test">
     * #THROWS TilaException
     * try {
     *  Ba ba = new Ba();
     *  Area a1 = new Area().register();
     *  Area a2 = new Area().register();
     *  Area a3 = new Area().register();
     *  ba.add(a1); 
     *  ba.add(a2);
     *  ba.add(a3);
     *  Neighbour n2 = new Neighbour(0,1);
     *  Neighbour n3 = new Neighbour(2,1);
     *  ba.getNeighbourCount() === 0;
     *  ba.add(n2); ba.getNeighbourCount() === 1;
     *  ba.add(n3); ba.getNeighbourCount() === 2;
     *  ba.add(new Neighbour(0,0)); #THROWS TilaException // Ei voi olla itsensä naapuri
     *  ba.add(new Neighbour(1,2)); #THROWS TilaException // Jo olemasso oleva pari
     *  ba.add(new Neighbour(0,2)); ba.getNeighbourCount() === 3;
     *  } catch (TilaException e) {//
     *  }
     * </pre>
     */
    public void add(Neighbour n) throws TilaException {
        this.neighbours.add(n);
    }
    
    
    /**
     * onko aluetta jo olemassa
     * @param name alue jota etsitään
     * @param id id johon verrataan
     * @return true jos alue jo olemassa
     */
    public boolean duplicateCheck(int id, String name) {
         return this.areas.duplicateCheck(id, name);
    }
    
    
    /**
     * Poistetaan valittu alue
     * @param area alue joka poistetaan
     */
    public void delete(Area area) {
        int id = area.getID();
        this.neighbours.delete(id);
        this.areas.delete(area);
    }
    
    
    /**
     * poistetaan alueen naapurit
     * @param a alue jonka naapuri poistetaan 
     * @param b alue jonka naapuri poistetaan 
     */
    public void deleteNeighbour(int a, int b) {
        this.neighbours.delete(a,b);
    }
    
    
    /**
     * @param l lohkon id joka pistetaan 
     * @param f funktion id joka poistetaan 
     */
    public void deleteLf(int l, int f) {
        this.lfs.delete(l,f);
    }
    
    
    /**
     * @param lid lohko jonka tehtäviä etsitää
     * @return tehtävät joita lohko hoitaa 
     * Kohta1 == T /\ kohta2 == T ==> Ohjelma toimii oikeellisesti
     */
    public ArrayList<Function> findFunctions(int lid) {
        ArrayList<Integer> ids = this.lfs.findFunctionIDs(lid); //Kohta 1
        ArrayList<Function> f = new ArrayList<Function>(); // 
        for (int i = 0; i < ids.size(); i++) {
            Function func = this.getFunctionForId(ids.get(i));
            if (func != null) f.add(func); //Kohta 2
        }
        return f;
    }
    
    
    /**
     * @param fid tehtävä jonka paria etsitään
     * @return lohko joka vastaa tehtävästä 
     * @throws TilaException jos ei löydy
     */
    public Location findLocationFor(int fid) throws TilaException {
        int search = this.lfs.findLocationID(fid);
        for (int i = 0; i < this.getLocationCount(); i++)
            if (this.getLocation(i).getID() == search) return this.getLocation(i); 
        throw new TilaException("Haluttua lohkoa ei olemassa!");
    }
   
    
    /**
     * @param aid alue jonka naapureita etsitään
     * @return alueen naapurit 
     */
    public ArrayList<Area> findNeighbours(int aid) {
        ArrayList<Integer> ids = this.neighbours.findNeighbourIDs(aid);
        ArrayList<Area> a = new ArrayList<Area>();
        for (int i = 0; i < ids.size(); i++) a.add(this.getAreaForId(ids.get(i)));
        return a;
    }
    
    
    /**
     * @param i etsittävän alueen indeksi
     * @return etsitty alue
     */
    public Area getArea(int i) {
        return this.areas.get(i);
    }
    
    
    /**
     * @param name nimi jota etsitään
     * @return palauttaa etsityn alueen
     * @throws TilaException jos nimi väärässä muodossa
     */
    public Area getArea(String name) throws TilaException {
        return this.areas.get(name);
    }
    
    
    /**
     * Etsitään alue perustuen id numeroon
     * @param aid alueen id jota etsitään
     * @return alueen joka vastaa idtä
     */
    public Area getAreaForId(int aid) {
        return this.areas.getAreaForId(aid);
    }
    
    
    /**
     * @return alueiden lukumäärän
     */
    public int getAreaCount() {
        return areas.getSize();
    }
    
    
    /**
     * etsitään lohkon nimi idn perusteella
     * @param lid lid jolle etsitään nimeä
     * @return lohkon nimen 
     */
    public String getLocationName(int lid) {
        return this.locations.getLocationName(lid);
    }
    
    
    /**
     * @param i tehtävän indeksi
     * @return tehtävän tietorakenteen paikassa i  
     */
    public Function getFunction(int i) {
        return this.functions.get(i);
    }
    
    
    
    /**
     * Etsitään funktion jolle id kuuluu 
     * @param fid id jota etsitään
     * @return fuktio joka löydettiin 
     */
    public Function getFunctionForId(int fid) {
        return this.functions.getFunctionForId(fid);
    }
    
    
    /**
     * @param name funktion nimi
     * @return palauttaa funktion
     */
    public Function getFunction(String name) {
        return this.functions.get(name);
    }

    
    /**
     * @param name lohkon nimi
     * @return palauttaa lohko
     */
    public Location getLocation(String name) {
        return this.locations.get(name);
    }
    
    
    /**
     * @return tiedettävien tehtävien määrä  
     */
    public int getFunctionCount() {
        return this.functions.getSize();
    }
    
    
    /**
     * @param i kuinka mones pari 
     * @return pari paikassa i
     */
    public Lf getLf(int i) {
        return lfs.get(i);
    }
    
    
    /**
     * @return parien lukumäärän
     */
    public int getLfCount() {
        return this.lfs.getSize();
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
     * @return alueiden lukumäärän
     */
    public int getLocationCount() {
        return this.locations.getSize();
    }
    
    
    /**
     * @param i kuinka mones naapuripari 
     * @return naapur pari joka haluttiin
     */
    public Neighbour getNeighbour(int i) {
        return neighbours.get(i);
    }
    
    
    /**
     * @return naapuriparien joukon lukumäärä
     */
    public int getNeighbourCount() {
        return this.neighbours.getSize();
    }

    
 
    /**
     * @param area alue joka uudelleen kirjoitetaan tai lisätään
     */
    public void overWrite(Area area) {
        this.areas.overWrite(area); 
    }
    
    /**
     * @param name hakemiston nimi josta luetaan
     * @param t tietorakeene johon luetaan
     * @throws TilaException jos ongelmia hakemisessa
     */
    public void readFile(String name, TietorakenneJuoksevallaID t) throws TilaException {
        String file = name + "\\" + t.getFileName();
        File f = new File(file);
        try {
            f.createNewFile();
        }
        catch (IOException e) {
            throw new TilaException("Tiedoston luomisessa ongelmia " + file);
        }
        try (Scanner fi = new Scanner(new FileInputStream(f))) { // Jotta UTF8/ISO-8859 toimii'
            int tapahtumia = 0;
            while ( fi.hasNext() ) {
                String s = fi.nextLine().trim();
                if ( s == null || "".equals(s.trim()) || s.charAt(0) == '#' ) continue;
                tapahtumia++;
                t.add(t.newT(s));
            }
            if (tapahtumia != 0)
                t.setNextID(t.get(t.getSize()-1).getID()+1);  
        } catch ( FileNotFoundException e ) {
            throw new TilaException("Tiedostoa ei olemassa " + file);
        } 
    }
    
    
    /**
     * Lukee ba tiedot tiedostosta
     * @param folder folder name
     * @throws TilaException jos lukeminen epäonnistuu
     */
    public void readFileAll(String folder) throws TilaException {
        File dir = new File(folder);
        dir.mkdir();
        this.areas = new Areas(); 
        this.functions = new Functions();
        this.locations = new Locations();
        this.lfs = new Lfs();
        this.neighbours = new Neighbours();
        
        this.readFile(folder, this.areas);
        this.readFile(folder, this.locations);
        this.readFile(folder, this.functions);
        this.lfs.readFile(folder);
        this.neighbours.readFile(folder);
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
        Ba ba       = new Ba();
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
        Lf lf1      = new Lf(l1.getID(), f1.getID());
        Lf lf2      = new Lf(l2.getID(), f2.getID());
        Lf lf3      = new Lf(l2.getID(), f3.getID());
        Lf lf4      = new Lf(l2.getID(), f4.getID());
        Lf lf5      = new Lf(l3.getID(), f5.getID());
        Lf lf6      = new Lf(l3.getID(), f6.getID());
        
        a1.setLid(l2.getID());
        a2.setLid(l3.getID());
        

        try {
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
            
            ba.add(lf1);
            ba.add(lf2);
            ba.add(lf3);
            ba.add(lf4);
            ba.add(lf5);
            ba.add(lf6);
        } catch (TilaException e1) {
            e1.printStackTrace();
        }



        for (int i = 0; i < ba.getAreaCount(); i++) {
            int lid = ba.areas.get(i).getLid();
            ba.areas.get(i).print(System.out);
            ba.locations.get(lid).print(System.out);
            ArrayList<Function> f = ba.findFunctions(lid);
            for (int j = 0; j < f.size(); j++) f.get(j).print(System.out);
            System.out.println("-------------------------------");
        }
        
    }
}
