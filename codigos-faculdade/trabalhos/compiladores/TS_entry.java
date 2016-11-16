
/**
 * Write a description of class Paciente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TS_entry
{
   private String id;
   private int tipo;
   private int numeroElementos;
   private int tipoBase;


   public TS_entry(String umId, int umTipo, int ne, int umTBase) {
      id = umId;
      tipo = umTipo;
      numeroElementos = ne;
      tipoBase = umTBase;
   }

   public TS_entry(String umId, int umTipo) {
      this(umId, umTipo, -1, -1);
   }


   public String getId() {
       return id; 
   }

   public int getTipo() {
       return tipo; 
   }
   
   public int getNumElem() {
       return numeroElementos; 
   }

   public int getTipoBase() {
       return tipoBase; 
   }

   
   public String toString() {
       String aux = (numeroElementos != -1) ? "\t array(" + numeroElementos + "): "+tipoBase : "";
       return "Id: " + id + "\t tipo: " + tipo + aux;
   }


}
