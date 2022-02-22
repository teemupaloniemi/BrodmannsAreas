package ba;

/**
 * @author Teemu
 * @version 21.2.2022
 *
 */
public class Functions {
        
     private static final int MAX       = 15;
     private int              lkm       = 0; 
     private Function[]       functions = new Function[MAX];

     
     /**
      * Alustetaan uusi alueisto
      */
     public Functions() {
         //
     }
     
     
     /**
      * Lisätään uusi alue aluistoon
      * @param function alue joka lisätään
      * @throws TilaException poikkeus jos taulukko täynnä
      */
     public void add(Function function) throws TilaException {
         if (this.lkm >= this.functions.length) throw new TilaException("Alkioita jo maksimi määrä.");
         this.functions[lkm] = function;
         this.lkm++;
     }
     
     
     /**
      * @return paljonko alkiota 
      */
     public int getLkm() {
         return this.lkm;
     }
     
     
     /**
      * @param i alkion indeksi
      * @return halutun alkion
      */
     public Function get(int i) {
         if (i < 0 || lkm <= i)
             throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
         return functions[i];
     }
     
     
     /**
      * Testiohjelma alueille
      * @param args ei käytössä
      */
     public static void main(String args[]) {
         Functions functions = new Functions();

         Function function  = new Function(); 
         Function function2 = new Function();
         
         function.register();
         function.fillFunctionInfo();
         function2.register();
         function2.fillFunctionInfo();

         try {
             functions.add(function);
             functions.add(function2);
         } catch (TilaException e) {
             System.out.println(e.getMessage());
         }

         System.out.println("============= Jäsenet testi =================");
         
         for (int i = 0; i < functions.getLkm(); i++) {
             Function f = functions.get(i);
             System.out.println("Jäsen nro: " + i);
             f.print(System.out);
         }
     }
}
