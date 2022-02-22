package ba;

/**
 * Pitää yllä sijaintirekisteriä eli osaa lisätä ja poistaa sijainteja                              
 * Lukee ja kirjoittaa location.dat tiedostoa      
 * osaa etsiä ja lajitella                         
 *                   
 * @author Teemu
 * @version 21.2.2022
 *
 */
public class Locations {
    
    private static final int MAX       = 30;
    private int              lkm       = 0; 
    private Location[]       locations = new Location[MAX];

    
    /**
     * Alustetaan uusi alueisto
     */
    public Locations() {
        //
    }
    
    
    /**
     * Lisätään uusi alue aluistoon
     * @param location alue joka lisätään
     * @throws TilaException poikkeus jos taulukko täynnä
     */
    public void add(Location location) throws TilaException {
        if (this.lkm >= this.locations.length) throw new TilaException("Alkioita jo maksimi määrä.");
        this.locations[lkm] = location;
        this.lkm++;
    }
    
    
    /**
     * @return paljonko alkiota 
     */
    public int getSize() {
        return this.lkm;
    }
    
    
    /**
     * @param i alkion indeksi
     * @return halutun alkion
     * @throws IndexOutOfBoundsException jos kutsitaan laittomalla indeksillä
     */
    public Location get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i) throw new IndexOutOfBoundsException("laiton indeksi: " + i);
        return locations[i];
    }
    
    
    /**
     * Testiohjelma alueille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Locations locations = new Locations();

        Location location  = new Location(); 
        Location location2 = new Location();
        
        location.register();
        location.fillLocationInfo();
        location2.register();
        location2.fillLocationInfo();

        try {
            locations.add(location);
            locations.add(location2);
        } catch (TilaException e) {
            System.out.println(e.getMessage());
        }

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
