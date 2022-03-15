package ba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Pitää yllä aluerekisteriä, eli osaa lisätä ja poistaa alueen                                 
 * Lukee ja kirjoittaa alue.dat tiedostoon        
 * Osaa etsiä alueita    
 *                   
 * @author Teemu
 * @version 21.2.2022
 * 
 * @example
 * <pre name="test">
 * Areas areas = new Areas();
 * Area a1 = new Area();
 * Area a2 = new Area();
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
public class Areas implements Tietorakenne {
    
    private int    size     = 5;
    private int    lkm      = 0; 
    private Area[] areas    = new Area[size];
    private boolean altered = false;
    private String fileName = "area.dat";
    
    
    /**
     * @return palautetaan tiedoston nimi
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
     * Lisätään uusi alue aluistoon
     * @param area alue joka lisätään
     */
    public void add(Area area) {
        if (this.lkm >= this.areas.length) this.kasvata(); // jos rupeaa olemaan täynnä 
        this.areas[lkm] = area; //lkm kertoo missä mennään ja sitten lisätään alkio 
        this.lkm++; // kasvatetaan ettei ensikerralla osu kohdille
        altered = true; // muutettu tietorakennetta 
    }
    
    
    /**
     * Kasvatetaan alkiotaulukkoa jos täynnä
     */
    private void kasvata() {
        this.size *= 2; // kasvatetaan size atribuuttia 2x jotta riittää jatkossa
        Area[] n = new Area[size]; // luodaan uusi tyhjä Area taulukko
        for (int i = 0; i < this.getSize(); i++) n[i] = this.areas[i]; // lisätään vanhat alkiot uuteen taulukkoon
        this.areas = n; // muutetaan taulukko atribuutti uudeksi isommaksi jossa vanha sisältö
        System.gc(); // nyt voi viedä roskat ulos (vanha taulukko)
        altered = true; // muutettiin tiedostoa
    }
    
    
    /**
     * @param i alkion indeksi
     * @return halutun alkion
     * @throws IndexOutOfBoundsException jos kutsutaan laittomalla indeksillä
     */
    @Override
    public Area get(int i) throws IndexOutOfBoundsException {
        if (0 > i || i >= this.lkm) throw new IndexOutOfBoundsException("Laiton indeksi a: " + i); // jos ei osu välille
        return areas[i];
    }
    

    /**
     * @param name hakemiston nimi josta luetaan
     * @throws TilaException jos ongelmia hakemisessa
     */
    public void readFile(String name) throws TilaException {
        String file = name + "\\" + this.getFileName(); // haetaan halutun kansion "tätä-luokkaa" vastaava tiedosto 
        File f = new File(file); // luodaan uusi tiedosto joka löydettiin 
        try (Scanner fi = new Scanner(new FileInputStream(f))) { // Jotta UTF8/ISO-8859 toimii'
            while ( fi.hasNext() ) { // niin kauan kun tavaraa riittää 
                String s = fi.nextLine().trim(); // otetaan rivi kerrallaan ja siivotaan se ylimääräisistä välilyönneistä
                if ( s == null || "".equals(s) || s.charAt(0) == '#' ) continue; // jos rivi on tyhjä tai kommentti "#"
                Area area = new Area(); // 
                area.parse(s);          // rivin tiedot uudeksi alueeksi alueistoon 
                this.add(area);         //
            }
            Area.setNextAid(this.get(this.getSize()-1).getAid()+1);  // muutetaa nextAid takisin mihin se jäi
        } catch ( FileNotFoundException e ) {
            throw new TilaException("Ei saa luettua tiedostoa " + file); // tiedostoa ei löydy
        }
    }
    
    
    /**
     * @return onko tiedostoa muutettu, true jos on 
     */
    @Override
    public boolean isAltered() {
        return this.altered;
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
    
    
    /**
     * Testiohjelma alueille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Areas areas = new Areas();
        areas.setFileName("areasTest.txt");

        Area area1 = new Area().register().fillAreaInfo();
        Area area2 = new Area().register().fillAreaInfo();
        Area area3 = new Area().register().fillAreaInfo();
        Area area4 = new Area().register().fillAreaInfo();
        Area area5 = new Area().register().parse("10|Brodmann's Area 17|7");
        Area area6 = new Area().register().parse("6|Brodmann's Area 12|7");
        Area area7 = new Area().register().parse("23|Brodmann's Area 7|3");
        Area area8 = new Area().register().parse("624|Brodmann's Area 47|16");

        areas.add(area1);
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
