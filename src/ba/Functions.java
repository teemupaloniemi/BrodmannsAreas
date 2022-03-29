package ba;

import java.util.ArrayList;

/**
 * @author Teemu
 * @version 21.2.2022
 * 
 * @example
 * <pre name="test">
 * #THROWS TilaException
 * Functions functions = new Functions();
 * Function a1 = new Function().setName("AA"), a2 = new Function().setName("AB");
 * functions.getSize() === 0;
 * functions.add(a1); functions.getSize() === 1;
 * functions.add(a2); functions.getSize() === 2;
 * functions.get(0) === a1;
 * functions.get(1) === a2;
 * functions.get(1) == a1 === false;
 * functions.get(1) == a2 === true; 
 * functions.add(new Function().setName("BB")); functions.getSize() === 3;
 * functions.add(new Function().setName("CC")); functions.getSize() === 4;
 * </pre>
 */
public class Functions implements TietorakenneJuoksevallaID {  
     private ArrayList<Function> functions = new ArrayList<Function>();
     private boolean altered = false;
     private String fileName = "function.dat";
     
     
    /**
     * Lisätään uusi alue aluistoon
     * @param function alue joka lisätään
     * @throws TilaException jos on jo olemassa
     */
     @Override
    public void add(Object function) throws TilaException{
         Function f = (Function) function;
         if (this.contains(f.getName())) throw new TilaException("Tämä on jo olemassa: " + f.getName());
         this.functions.add(f);
         altered = true;
     }
     
     
     /**
      * tarkistetaan onko nimi jo olemassa
      * @param name nimi jota etsitään
      * @return true jos nimi jo olemassa
      */
     public boolean contains(String name) { 
         for (int i = 0; i < this.getSize(); i++)
             if (this.get(i).getName().equalsIgnoreCase(name)) return true;
         return false;
     }
     
     
     /**
      * @param i alkion indeksi
      * @return halutun alkion
      */
     @Override
    public Function get(int i) {
         if (0 > i || i >= this.getSize()) {
             throw new IndexOutOfBoundsException("Laiton indeksi fuktiota etsittäessä: " + i);
         }
         return functions.get(i);
     }
     
     
     /**
     * @param name nimi jota etsitään
     * @return löydetyn funktion tai luo uuden jos ei löydy 
     */
    public Function get(String name) {
         for (int i = 0; i < this.getSize(); i++)
            if (this.get(i).getName().equals(name)) return this.get(i);
         Function f = new Function().register().setName(name);
         try {
            this.add(f);
         } catch (TilaException e) {
            //ei voi tapahtua sillä tarkistettiin juuri edellä
         }
         return f;
     }
     
    
    /**
     * @param fid fid jolle etsitään nimeä 
     * @return palauttaa funktion 
     */
     public Function getFunctionForId(int fid) {
         for (int i = 0; i < this.getSize(); i++) {
             Function f = this.get(i);
             if (f.getID() == fid) return f;
         }
         return null;
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
         return functions.size();
     }
     
     
     /**
      * @return onko tiedostoa muutettu, true jos on 
      */
     @Override
     public boolean isAltered() {
         return this.altered;
     }
     
     
     @Override
     public Function newT(String s) {
         return new Function().parse(s);
     }
     
     
     /**
      * palutetaan alkutilanteeseen
      */
     @Override
     public void resetAltered() {
         this.altered = false;
     }
     
     
     @Override
     public void setNextID(int id) {
         Function.setNextFid(id);
     }
     
     
     /**
      * Testiohjelma alueille
      * @param args ei käytössä
      */
     public static void main(String args[]) {
         Functions functions = new Functions();
         try {
            functions.add(new Function().register().fillFunctionInfo());
            functions.add(new Function().register().fillFunctionInfo());
            functions.add(new Function().register().fillFunctionInfo());
            functions.add(new Function().register().fillFunctionInfo());
        } catch (TilaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

         System.out.println("============= Function testi =================");
         
         for (int i = 0; i < functions.getSize(); i++) functions.get(i).print(System.out);
     }
}
