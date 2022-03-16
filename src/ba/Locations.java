package ba;


/**
 * Pitää yllä lohkorekisteriä eli osaa lisätä ja poistaa sijainteja                              
 * Lukee ja kirjoittaa location.dat tiedostoa      
 * osaa etsiä ja lajitella                         
 *                   
 * @author Teemu
 * @version 21.2.2022
 * 
 * @example
 * <pre name="test"> 
 * Locations locations = new Locations();
 * Location a1 = new Location(), a2 = new Location();
 * locations.getSize() === 0;
 * locations.add(a1); locations.getSize() === 1;
 * locations.add(a2); locations.getSize() === 2;
 * locations.add(a1); locations.getSize() === 3;
 * locations.get(0) === a1;
 * locations.get(1) === a2;
 * locations.get(2) === a1;
 * locations.get(1) == a1 === false;
 * locations.get(1) == a2 === true;
 * locations.get(3) === a1; #THROWS IndexOutOfBoundsException 
 * locations.add(a1); locations.getSize() === 4;
 * locations.add(a1); locations.getSize() === 5;
 * </pre>
 *
 */
public class Locations implements TietorakenneJuoksevallaID {
    
    private int koko          = 5;
    private int lkm           = 0; 
    private Location[] locations = new Location[koko];
    private String fileName = "location.dat";
    private boolean altered = false;
    
    
    /**
     * Lisätään uusi alue aluistoon
     * @param location alue joka lisätään
     */
    @Override
    public void add(Object location) {
        if (this.lkm >= this.locations.length) this.kasvata();
        this.locations[lkm] = (Location) location;
        this.lkm++;
        this.altered = true;
    }
    
    
    /**
     * @param i alkion indeksi
     * @return halutun alkion
     * @throws IndexOutOfBoundsException jos kutsitaan laittomalla indeksillä
     */
    @Override
    public Location get(int i) throws IndexOutOfBoundsException {
        if (0 > i || i >= this.lkm) throw new IndexOutOfBoundsException("laiton indeksi l: " + i);
        return locations[i];
    }
    
    
    /**
     * @return palautetaan tiedostonimi
     */
    @Override
    public String getFileName() {
         return this.fileName;
     }
    
    
    /**
     * @return paljonko alkiota 
     */
    @Override
    public int getSize() {
        return this.lkm;
    }
    
    
    /**
     * @return onko tiedostoa muutettu, true jos on 
     */
    @Override
    public boolean isAltered() {
        return this.altered;
    }
    
    
    @Override
    public Location newT(String s) {
        return new Location().parse(s);
    }
    
    
    private void kasvata() {
        this.koko += 5;
        Location[] n = new Location[koko];
        for (int i = 0; i < this.getSize(); i++) n[i] = this.get(i);
        this.locations = n;
        System.gc();
        this.altered = true;
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

    
    @Override
    public void setNextID(int id) {
        Location.setNextLid(id);
    }
    
    
    /**
     * Testiohjelma alueille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Locations locations = new Locations();
        locations.setFileName("locationsTest.txt");

        Location location  = new Location(); 
        Location location2 = new Location();
        
        location.register();
        location.fillLocationInfo();
        location2.register();
        location2.fillLocationInfo();

        locations.add(location);
        locations.add(location2);
            
        System.out.println("============= Jäsenet testi =================");
        
        for (int i = 0; i < locations.getSize(); i++) {
            Location ilocation;
            try {
                ilocation = locations.get(i);
                ilocation.print(System.out);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            System.out.println("Jäsen nro: " + i);
        }
    }
}
