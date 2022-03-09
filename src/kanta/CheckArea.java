package kanta;

/**
 * @author Teemu
 * @version 21.2.2022
 *
 */
public class CheckArea {

    /**
     * Arvotaan satunnainen kokonaisluku välille [ala,yla]
     * @param ala arvonnan alaraja
     * @param yla arvonnan yläraja
     * @return satunnainen luku väliltä [ala,yla]
     */
    public static int rand(int ala, int yla) {
      double n = (yla-ala)*Math.random() + ala;
      return (int)Math.round(n);
    }
    

     /**
     * @param i sijainnin indeksi
     * @return sijainnin nimen
     */
     public static String getLocation(int i) {
         String[] locations = {"Primary somatosensory cortex",
                               "Primary motor cortex",
                               "Secondary sensorimotor cortex",
                               "Premotor cortex",
                               "Superior parietal lobule"};
         return locations[i];
     }
     
     /**
     * @param i funktion indeksi
     * @return funktion nimen
     */
     public static String getFunction(int i) {
         String[] functions = {"Localize pain",
                               "Localize touch and vibration",
                               "Localize temperature",
                               "Sense of fingers",
                               "Sense of body",
                               
                               "Move hands",
                               "Move mouth and tongue",
                               "Swallowing",
                               "Anticipate pain",
                               "Anticipate tickling",
                               
                               "Mirror neurons",
                               "Semantic & phonological processing",  
                               "Verbal fluency",                      
                               "Lexical search",                      
                               "Phonemes",                            
                               
                               "Grammar", 
                               "Attend to speech", 
                               "Syntactic working memory", 
                               "Working memory",         
                               "Episodic memory", 
                               
                               "Declarative memory", 
                               "Mirror other's movements", 
                               "Speech programs" };                          
         return functions[i];            
     }                                   
}                                        
                                         
                                         
                                         