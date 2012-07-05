package de.bomberman.lvl;
/**
  * @author Gruppe 44
  * die ini Datei wird erstellt, der Lvl mit dem Namen und Werten wird gespeichert
  * die Werte werden wieder ausgelesen
  */
public class test{
  
  //Main
  public static void main(String[] args){
    test0();
  }
  
  //test0
  public static void test0(){
	  /**
	   * ini erstellen
	   */
    LvlIni ini = new LvlIni("lvl0.ini");
    /**
     * die Überschrift hinzufügen
     */
	ini.addSection("lvl0");
	 /**
	  * die einzelnen Werte für den Bereich einfügen
	  * der Name `lvl0` spricht die Überschrift an, dann kommt 
	  * die Beschreibung(was gespeichert werden soll)
	  * der eigentliche Wert am Ende
	  */
	ini.addValue("lvl0","spieler",2);
	ini.addValue("lvl0","bomben",7);
	ini.addValue("lvl0","punkte",12);
	 /**
	  * alles wird gespeichert
	  */
	ini.save();
	 /**
	  * der Verweis wird gelöscht, sodass die Variable wieder leer ist
	  */
	ini = null;
	 /**
	  * die Datei wird wieder geöffnet (in Klammern steht der Name der Datei)
	  */
	ini = new LvlIni("lvl0.ini");
	 /**
	  * die Werte werden ausgelesen
	  */
	System.out.println(ini.readValue("lvl0","spieler") + "#" + ini.readValue("lvl0","bomben") + "#" + ini.readValue("lvl0","punkte"));
  }
  
}