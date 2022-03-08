package ba;

import java.util.ArrayList;

/**
 * Huolehtii Locations, Functions, Lfs ja Neighbours luokkien
 * välisestä yhteistyöstä ja välittää näitä tietoja pyydettäessä                       
 * @author Teemu
 * @version 22.2.2022
 *
 */
public class Ba {
    
    private final Areas areas = new Areas();
    private final Locations locations = new Locations();
    private final ba.Functions functions = new ba.Functions();
    private final Lfs lfs = new Lfs();
        
    
    /**
     * @return alueiden lukumäärän
     */
    public int getAreaCount() {
        return areas.getSize();
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
     * @throws TilaException jos tila loppuu
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
     * ba.add(a1); #THROWS TilaException
     * </pre>
     */
    public void add(Area area) throws TilaException {
         this.areas.add(area);
    }
    
    
    /**
     * @param location lisattava sijainti
     * @throws TilaException jos tila loppuu
     */
    public void add(Location location) throws TilaException {
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
     * Testiohjelma Ba-luokalle
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Ba ba = new Ba();
        try {
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
            
        } catch (TilaException e) {
            e.printStackTrace();
        }
        
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
