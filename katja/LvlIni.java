public class LvlIni{
  
  private LvlFile f;
  
  //Main
  public static void main(){
    //leer
  }
  
  //Konstruktor
  public LvlIni(){
    //leer
  }
  
  public LvlIni(String path){
    //Datei oeffnen
    this.f = new LvlFile(path);
	//Datei cachen
	this.f.readInitDataT();
  }
  
  //--- Length ---//
  
  //Section-Laenge auslesen inklusive ueberschrift also mindestens 1, -1 wenn section nicht vorhanden
  public int sectionLength(String sec){
    //Pruefen ob Section vorhanden
    if(checkSection(sec)==true){
	  int x = getPosSection(sec);
      int i=0;
      //Anzahl der Values zaehlen
      while(this.f.readln(x+i).equals("")==false) { i++; }
      return i;
    }
	else{ return -1; }
  }
  
  public int sectionLength(int sec){ return sectionLength(String.valueOf(sec)); }
  
  //--- Count ---//
  
  //countSections()
  public int countSections(){ return getSections().length; }
  
  //countValues()
  public int countValues(String sec){ return sectionLength(sec)-1; }
  public int countValues(int sec){ return countValues(String.valueOf(sec)); }
  
  //--- Add ---//
  
  //addSection()
  public void addSection(String name){
    //Pruefen ob Section schon vorhanden
	if(checkSection(name)==false){
      this.f.insertln(0,"");
      this.f.insertln(0,"[" + name + "]");
	}
	else{ System.out.println("LvlIni -> addSection() -> Section schon vorhanden!"); }
  }
  
  public void addSection(int x){ addSection(String.valueOf(x)); }
  
  //addValue()
  public void addValue(String sec, String val, String data){
    //Pruefen ob Value schon vorhanden
	if(checkSection(sec)==true && checkValue(sec,val)==false){
	  int x = -1;
	  //Section suchen
	  x = getPosSection(sec);
	  //Value hinzufuegen
	  String s = val + "=" + data;
	  this.f.insertln(x+1,s);
	}
	else{ System.out.println("LvlIni -> addValue() -> Section nicht bzw. Value schon vorhanden!"); }
  }
  
  public void addValue(String sec, String val){ addValue(sec,val,""); }
  
  //Varianten
  public void addValue(int sec, String val, String data){ addValue(String.valueOf(sec),val,data); }
  public void addValue(int sec, int val, String data){ addValue(String.valueOf(sec),String.valueOf(val),data); }  
  public void addValue(String sec, String val, int data){ addValue(sec,val,String.valueOf(data)); }
  public void addValue(String sec, int val, int data){ addValue(sec,String.valueOf(val),String.valueOf(data)); }
  public void addValue(int sec, int val, int data){ addValue(String.valueOf(sec),String.valueOf(val),String.valueOf(data)); }
  public void addValue(int sec, String val, int data){ addValue(String.valueOf(sec),val,String.valueOf(data)); }
  public void addValue(String sec, int val, String data){ addValue(sec,String.valueOf(val),data); }
  
  public void addValue(int sec, String val){ addValue(String.valueOf(sec),val,""); } 
  public void addValue(String sec, int val){ addValue(sec,String.valueOf(val),""); } 
  public void addValue(int sec, int val){ addValue(String.valueOf(sec),String.valueOf(val),""); } 
  
  //--- Get ---//
  
  //getSections()
  public String[] getSections(){
    int[] lines = this.f.findAll("[");
	//Pruefen ob Datei leer
	if(lines[0]!=-1){
      String[] sections = new String[lines.length];
      //Zeilen lesen
      for(int i=0; i<lines.length; i++){
        sections[i]=this.f.readln(lines[i]).substring(1,this.f.readln(lines[i]).length()-1);
      }
      return sections;
	}
	else{
      String[] sections = new String[0];
	  return sections;
	}
  }
  
  //getValues()
  public String[] getValues(String sec){
    //Pruefen ob Section vorhanden
	if(checkSection(sec)==true){
	  //Pruefen ob es ueberhaupt Values gibt
	  if(countValues(sec)>0){
	    int pos = getPosSection(sec);
	    int l = countValues(sec);
	    String[] s = new String[l];
	    String[] tmp;
	    //Zeilen lesen
	    for(int i=0; i<l; i++){
	      tmp=this.f.readln(pos+i+1).split("=");
		  s[i]=tmp[0];
	    }
		return s;
	  }
	  else{
	    System.out.println("LvlIni -> getValues() -> Keine Values vorhanden!");
	    String[] s = new String[0];
	    return s;
	  }
	}
	else{
	  System.out.println("LvlIni -> getValues() -> Section nicht vorhanden!");
	  String[] s = new String[0];
	  return s;
	}
  }
  
  public String[] getValues(int sec){ return getValues(String.valueOf(sec)); }
  
  //--- Check ---//
  
  //checkSection()
  public boolean checkSection(String sec){
    //Pruefen ob Section vorhanden
	if(getPosSection(sec)!=-1){ return true; }
	else{ return false; }
  }
  
  public boolean checkSection(int sec){ return checkSection(String.valueOf(sec)); }
  
  //checkValue()
  public boolean checkValue(String sec, String val){
    //Pruefen ob Section vorhanden
	if(checkSection(sec)==true){
	  //Pruefen ob Value vorhanden
	  int pos = getPosSection(sec);
      int x = -1;
	  x = sectionLength(sec)-1;
	  //Anzahl pruefen
	  if(x>0){
	    String[] tmp;
        //Zeilen lesen
        for(int i=0; i<x; i++){
          tmp = this.f.readln(pos+i+1).split("=");
		  //Value pruefen
		  if(tmp[0].equals(val)==true){ return true; }
		  else{ /*leer*/ }
        }
        return false;
	  }
	  else{ return false; }
	}
	else{ return false; }
  }
  
  public boolean checkValue(int sec, int val){ return checkValue(String.valueOf(sec),String.valueOf(val)); }
  public boolean checkValue(String sec, int val){ return checkValue(sec,String.valueOf(val)); }
  public boolean checkValue(int sec, String val){ return checkValue(String.valueOf(sec),val); }
  
  //--- Read ---//
  
  //readValue()
  public String readValue(String sec, String val){
    //Pruefen ob Value vorhanden
	if(checkValue(sec,val)==true){
	  int x = -1;
	  x = getPosValue(sec,val);
	  //Zeilen lesen/splitten
	  String[] s = this.f.readln(x).split("=");
	  //Daten ausgeben
	  if(s.length==2){ return s[1]; }
	  else{ return ""; }
	}
	else{ System.out.println("LvlIni -> readValue() -> Value nicht vorhanden!"); return ""; }
  }
  
  public String readValue(String sec, int val){ return readValue(sec,String.valueOf(val)); }
  public String readValue(int sec, String val){ return readValue(String.valueOf(sec),val); }
  public String readValue(int sec, int val){ return readValue(String.valueOf(sec),String.valueOf(val)); }
  
  //--- Write ---//
  
  //writeValue()
  public void writeValue(String sec, String val, String data){
    //Pruefen ob Value vorhanden
	if(checkValue(sec,val)==true){
	  //Position holen
	  int pos = getPosValue(sec,val);
	  //Zeile lesen
	  String[] s = this.f.readln(pos).split("=");
	  String tmp = s[0];
	  tmp = tmp + "=" + data;
	  //Zeile speichern
	  this.f.writeln(pos,tmp);
	}
	else{ System.out.println("LvlIni -> writeValue() -> Value nicht vorhanden! Wurde neu hinzugefuegt!"); addValue(sec,val,data); }
  }
  
  //--- Del ---//
  
  //delSection()
  public void delSection(String sec){
    //Pruefen ob Section vorhanden
	if(checkSection(sec)==true){
	  //Position holen
	  int pos = getPosSection(sec);
	  //Sectionlaenge holen
	  int l = sectionLength(sec);
	  //Zeile loeschen
	  this.f.delln(pos,l+1);
	}
	else{ System.out.println("LvlIni -> delSection() -> Value nicht vorhanden!"); }
  }
  
  //delValue()
  public void delValue(String sec, String val){
    //Pruefen ob Value vorhanden
	if(checkValue(sec,val)==true){
	  //Position holen
	  int pos = getPosValue(sec,val);
	  //Zeile loeschen
	  this.f.delln(pos);
	}
	else{ System.out.println("LvlIni -> delValue() -> Value nicht vorhanden!"); }
  }
  
  //--- Clear ---//
  
  //clear()
  public void clear(){
    String[] s = getSections();
    //Alle Sections loeschen
	for(int i=0; i<s.length; i++){
	  delSection(s[i]);
	}
  }
  
  //--- Rename ---//
  
  //renameSection()
  public void renameSection(String sec, String name){
    //Pruefen ob Section vorhanden
	if(checkSection(sec)==true){
      this.f.writeln(getPosSection(sec),"[" + name + "]");
	}
  }
  
  public void renameSection(int sec, String name){ renameSection(String.valueOf(sec),name); }
  public void renameSection(String sec, int name){ renameSection(sec,String.valueOf(name)); }
  public void renameSection(int sec, int name){ renameSection(String.valueOf(sec),String.valueOf(name)); }
  
  //renameValue()
  public void renameValue(String sec, String val, String name){
    //Pruefen ob Value existiert
	if(checkValue(sec,val)==true){
	  //Position von Value suchen
	  this.f.writeln(getPosValue(sec,val),name + "=" + readValue(sec,val));
	}
	else{ System.out.println("LvlIni -> renameValue() -> Value nicht vorhanden!"); }
  }
  
  public void renameValue(String sec, int val, int name){ renameValue(sec,String.valueOf(val),String.valueOf(name)); }
  public void renameValue(String sec, String val, int name){ renameValue(sec,val,String.valueOf(name)); }
  public void renameValue(int sec, int val, int name){ renameValue(String.valueOf(sec),String.valueOf(val),String.valueOf(name)); }
  public void renameValue(int sec, int val, String name){ renameValue(String.valueOf(sec),String.valueOf(val),name); }
  public void renameValue(int sec, String val, String name){ renameValue(String.valueOf(sec),val,name); }
  public void renameValue(int sec, String val, int name){ renameValue(String.valueOf(sec),val,String.valueOf(name)); }
  public void renameValue(String sec, int val, String name){ renameValue(sec,String.valueOf(val),name); }
  
  //renameValueAll()
  public void renameValueAll(String val, String name){
    String[] sections = getSections();
	//Alle Value ersetzen
	for(int i=0; i<sections.length; i++){
	  renameValue(sections[i],val,name);
	}
  }
  
  public void renameValueAll(int val, String name){ renameValueAll(String.valueOf(val),name); }
  public void renameValueAll(String val, int name){ renameValueAll(val,String.valueOf(name)); }
  public void renameValueAll(int val, int name){ renameValueAll(String.valueOf(val),String.valueOf(name)); }
  
  //--- Pos ---//
  
  //getPosSection()
  private int getPosSection(String sec){
    return this.f.find("[" + sec + "]");
  }
  
  private int getPosSection(int sec){ return getPosSection(String.valueOf(sec)); }
  
  //getPosSectionAll()
  private int[] getPosSectionAll(){
    return this.f.findAll("[");
  }
  
  //getPosValue()
  private int getPosValue(String sec, String val){
    //Pruefen ob Value existiert
	if(checkValue(sec,val)==true){
	  int posS = getPosSection(sec);
	  int[] posV = this.f.findAll(val + "=");
	  //Richtige Position suchen
	  for(int i=0; i<posV.length; i++){
	    if(posV[i]>posS){ return posV[i]; }
	  }
	  return -1;
	}
	else{ System.out.println("LvlIni -> getPosValue() -> Value nicht vorhanden!"); return -1; }
  }
  
  private int getPosValue(int sec, int val){ return getPosValue(String.valueOf(sec),String.valueOf(val)); }
  private int getPosValue(String sec, int val){ return getPosValue(sec,String.valueOf(val)); }
  private int getPosValue(int sec, String val){ return getPosValue(String.valueOf(sec),val); }
  
  //getPosValueAll()
  private int[] getPosValueAll(String val){
    return this.f.findAll(val + "=");
  }
  
  private int[] getPosValueAll(int val){ return getPosValueAll(String.valueOf(val)); }
  
  //--- Find ---//
  
  //find()
  public int find(String s){
    return this.f.find(s);
  }
  
  public int find(int x){ return find(String.valueOf(x)); }
  
  //--- Save ---//
  
  //save()
  public void save(){
	this.f.save();
  }
  
}