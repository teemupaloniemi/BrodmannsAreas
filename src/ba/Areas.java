package ba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Pitää yllä aluerekisteriä, eli osaa lisätä ja poistaa alueen                                 
 * Lukee ja kirjoittaa alue.dat tiedostoon        
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
    private boolean altered = false;
    private String fileName = "area.dat";
    
    
    
    /**
     * @return palautetaan tiedoston nimi
     */
    public String getFileName() {
        return this.fileName;
    }
    
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
        altered = true;
    }
    
    
    private void kasvata() {
        this.koko *= 2;
        Area[] n = new Area[koko];
        for (int i = 0; i < this.getSize(); i++) n[i] = this.areas[i];
        this.areas = n;
        System.gc();
        altered = true;
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
     * @param name hakemiston nimi josta luetaan
     * @throws TilaException jos ongelmia hakemisessa
     */
    public void readFile(String name) throws TilaException {
        String file = name + "\\" + this.getFileName();
        File f = new File(file);
        try (Scanner fi = new Scanner(new FileInputStream(f))) { // Jotta UTF8/ISO-8859 toimii'
            while ( fi.hasNext() ) {
                String s = fi.nextLine().trim();
                if ( s == null || "".equals(s) || s.charAt(0) == '#' ) continue;
                Area area = new Area();
                area.parse(s);
                this.add(area);
            }
            this.get(0).setNextAid(this.get(this.getSize()-1).getAid()+1);  // muutetaa nextAid takisin
        } catch ( FileNotFoundException e ) {
            throw new TilaException("Ei saa luettua tiedostoa " + file);
        }
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
                Area area = this.get(i);
                ps.println(area.toString());
            }
        } catch (FileNotFoundException e) {
            throw new TilaException("Tiedosto " + file.getAbsolutePath() + " ei aukea!");
        }
        altered = false;
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
        
        try {
            areas.save();
        } catch (TilaException e) {
            e.printStackTrace();
        }
    }
}
