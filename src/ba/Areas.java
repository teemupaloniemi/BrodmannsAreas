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
    
    private int    koko     = 5;
    private int    lkm      = 0; 
    private Area[] areas    = new Area[koko];
    
    
    /**
     * @return paljonko alkiota 
     */
    public int getSize() {
        return this.lkm;
    }
    
    
    /**
     * Lisätään uusi alue aluistoon
     * @param area alue joka lisätään
     * @example
     * <pre name="test">
     * Areas areas = new Areas();
     * Area a1 = new Area(), a2 = new Area();
     * areas.getSize() === 0;
     * areas.add(a1); areas.getSize() === 1;
     * areas.add(a2); areas.getSize() === 2;
     * areas.add(a1); areas.getSize() === 3;
     * areas.get(0) === a1;
     * areas.get(1) === a2;
     * areas.get(2) === a1;
     * areas.get(1) == a1 === false;
     * areas.get(1) == a2 === true;
     * areas.get(3) === a1; #THROWS IndexOutOfBoundsException 
     * areas.add(a1); areas.getSize() === 4;
     * areas.add(a1); areas.getSize() === 5;
     * </pre>
     */
    public void add(Area area) {
        if (this.lkm >= this.areas.length) this.kasvata();
        this.areas[lkm] = area;
        this.lkm++;
    }
    
    
    private void kasvata() {
        this.koko *= 2;
        Area[] n = new Area[koko];
        for (int i = 0; i < this.getSize(); i++) n[i] = this.areas[i];
        this.areas = n;
        System.gc();
    }
    
    
    /**
     * @param i alkion indeksi
     * @return halutun alkion
     */
    public Area get(int i) {
        if (0 > i || i >= this.lkm) throw new IndexOutOfBoundsException("Laiton indeksi a: " + i);
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
        Area area3 = new Area().register().fillAreaInfo();
        Area area4 = new Area().register().fillAreaInfo();
        Area area5 = new Area().register().fillAreaInfo();
        Area area6 = new Area().register().fillAreaInfo();
        Area area7 = new Area().register().fillAreaInfo();
        Area area8 = new Area().register().fillAreaInfo();

        areas.add(area);
        areas.add(area2);  
        areas.add(area3);  
        areas.add(area4);  
        areas.add(area5);  
        areas.add(area6);  
        areas.add(area7);  
        areas.add(area8);   

        System.out.println("============= Areas test =================");
        
        for (int i = 0; i < areas.getSize(); i++) {
            areas.get(i).print(System.out);
        }
    }
}
