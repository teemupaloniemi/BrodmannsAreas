package ba;

/**
 * Pitää yllä aluerekisteriä, eli osaa lisätä ja poistaa alueen                                 
 * TODO: Lukee ja kirjoittaa alue.dat tiedostoon        
 * Osaa etsiä alueita    
 *                   
 * @author Teemu
 * @version 21.2.2022
 *
 */
public class Areas {
    
    private static final int MAX      = 52;
    private int              lkm      = 0; 
    private Area[]           areas    = new Area[MAX];

    
    /**
     * Alustetaan uusi alueisto
     */
    public Areas() {}
    
    
    /**
     * Lisätään uusi alue aluistoon
     * @param area alue joka lisätään
     * @throws TilaException poikkeus jos taulukko täynnä
     */
    public void add(Area area) throws TilaException {
        if (this.lkm >= this.areas.length) throw new TilaException("Alkioita jo maksimi määrä.");
        this.areas[lkm] = area;
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
     */
    public Area get(int i) {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return areas[i];
    }
    
    
    /**
     * Testiohjelma alueille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Areas areas = new Areas();

        Area area = new Area().register().fillAreaInfo();
        Area area2 = new Area().register().fillAreaInfo();

        try {
            areas.add(area);
            areas.add(area2);  
        } catch (TilaException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("============= Areas test =================");
        
        for (int i = 0; i < areas.getSize(); i++) {
            areas.get(i).print(System.out);
        }
    }
}
