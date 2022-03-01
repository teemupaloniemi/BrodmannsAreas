package ba;

/**
 * @author Teemu
 * @version 21.2.2022
 *
 */
public class Functions {
        
     private static final int MAX       = 5;
     private int              lkm       = 0; 
     private Function[]       functions = new Function[MAX];
     
     
    /**
     * Lisätään uusi alue aluistoon
     * @param function alue joka lisätään
     * @throws TilaException poikkeus jos taulukko täynnä
     * @example
     * <pre name="test">
     * #THROWS TilaException 
     * Functions functions = new Functions();
     * Function a1 = new Function(), a2 = new Function();
     * functions.getSize() === 0;
     * functions.add(a1); functions.getSize() === 1;
     * functions.add(a2); functions.getSize() === 2;
     * functions.add(a1); functions.getSize() === 3;
     * functions.get(0) === a1;
     * functions.get(1) === a2;
     * functions.get(2) === a1;
     * functions.get(1) == a1 === false;
     * functions.get(1) == a2 === true;
     * functions.get(3) === a1; #THROWS IndexOutOfBoundsException 
     * functions.add(a1); functions.getSize() === 4;
     * functions.add(a1); functions.getSize() === 5;
     * functions.add(a1); #THROWS TilaException
     * </pre>
     */
     public void add(Function function) throws TilaException {
         if (this.lkm >= this.functions.length) throw new TilaException("Alkioita jo maksimi määrä f.");
         this.functions[lkm] = function;
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
     public Function get(int i) {
         if (0 > i || i >= this.lkm) throw new IndexOutOfBoundsException("Laiton indeksi f: " + i);
         return functions[i];
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
         } catch (TilaException e) {
             System.out.println(e.getMessage());
         }

         System.out.println("============= Function testi =================");
         
         for (int i = 0; i < functions.getSize(); i++) {
             System.out.println("Teht. nro: " + i);
             functions.get(i).print(System.out);
         }
     }
}
