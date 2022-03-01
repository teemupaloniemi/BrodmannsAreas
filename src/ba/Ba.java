package ba;

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
     * Testiohjelma Ba-luokalle
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Ba ba = new Ba();
        try {
            ba.add(new Area().register().fillAreaInfo());
            ba.add(new Area().register().fillAreaInfo());
        } catch (TilaException e) {
            e.printStackTrace();
        }
 
        System.out.println("============= Ba-luokan testi =================");

        for (int i = 0; i < ba.getAreaCount(); i++) {
            Area area = ba.getArea(i);
            System.out.println("Area paikassa: " + i);
            area.print(System.out);
        }
    }
}
