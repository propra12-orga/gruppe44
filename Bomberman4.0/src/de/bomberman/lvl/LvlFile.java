package de.bomberman.lvl;
import java.io.*;
import javax.swing.*;

public class LvlFile{
  
  //Allgemein/Binary
  private File f;
  private FileInputStream inB;
  private FileOutputStream outB;
  private long length;
  
  private int buffer=65535;  //64KB = 65535 | 3MB = 3145728
  
  //Spezial fuer Texte
  private InputStreamReader inSR;
  private BufferedReader inT;
  private OutputStreamWriter outSR;
  private BufferedWriter outT;
  
  private int lengthT;    //Zeilenanzahl
  private String[] data;  //Komplette-Datei Zeilenweise (nur wenn mit initDataT() initialisiert!)
  
  //Main
  public static void main(String[] args){
    //leer
  }
  
  //Konstruktor
  public LvlFile(File file){
    try{
      this.f = file;
	  this.f.createNewFile();
      this.length=this.f.length();
	}
	catch(IOException e){ System.out.println("LvlFile -> IO Fehler!"); }
  }
  
  public LvlFile(String path){
    try{
      this.f = new File(path);
	  this.f.createNewFile();
      this.length=this.f.length();
	}
	catch(IOException e){ System.out.println("LvlFile -> IO Fehler-2!"); }
  }
  
  //##################### DEL #####################//
  
  //--- Binary & String ---//
  
  //delFile()
  public void delFile(File file){
    //Pruefen ob Datei existiert
    if(file.exists()==true){
	  //Datei loeschen
      file.delete();
    }
	else{ System.out.println("LvlFile -> delFile() -> Datei existiert nicht!"); }
  }
  
  public void delFile(String path){
    File file = new File(path);
	delFile(file);
  }
  
  public void delFile(){ delFile(this.f); }
  
  //##################### GET #####################//
  
  //--- Binary ---/
  
  //getName()
  public String getName(){ return this.f.getName(); }
  
  //getLength()
  public long getLength(){ return this.length; }
  
  //getBuffer()
  public int getBuffer(){ return this.buffer; }
  
  //--- String ---//
  
  //getLengthT()
  public int getLengthT(){ return this.lengthT; }
  
  //##################### SET #####################//
  
  //--- Binary ---//
  
  //setBuffer()
  public void setBuffer(int x){
    this.buffer=x;
  }
  
  //##################### INIT #####################//
  
  //--- Binary ---//
  
  //InputStream initialisieren (lesen)
  public void readInitB(){
    try{
      this.inB = new FileInputStream(this.f);
    }
    catch(FileNotFoundException e){ System.out.println("LvlFile -> readInitB() -> File not found"); }
  }
  
  //OutputStream initialisieren (schreiben)
  public void writeInitB(){
    try{
      this.outB = new FileOutputStream(this.f);
    }
    catch(FileNotFoundException e){ System.out.println("LvlFile -> writeInitB() -> File not found"); }
  }
  
  public void writeInitAppendB(){
    try{
      this.outB = new FileOutputStream(this.f,true);
    }
    catch(FileNotFoundException e){ System.out.println("LvlFile -> writeInitAppendB -> File not found"); }
  }
  
  //--- String ---/
  
  //BufferedReader initialisieren (lesen)
  public void readInitT(){
    readInitB();
	this.inSR = new InputStreamReader(this.inB);
	this.inT = new BufferedReader(this.inSR);
  }
  
  //Normale init (lesen) nur mit Daten als String[] im Speicher
  public synchronized void readInitDataT(){
	//Daten lesen
	this.data = getData();
	this.lengthT = this.data.length;
  }
  
  //BufferedWriter initialisieren (schreiben)
  public void writeInitT(){
    writeInitB();
	this.outSR = new OutputStreamWriter(this.outB);
	this.outT = new BufferedWriter(this.outSR);
  }
  
  //##################### SCHLIESSEN #####################//
  
  //--- Binary ---//
  
  //InputStream schliessen
  public void closeIn(){
    try{
      //Datei schliessen
      this.inB.close();
    }
    catch(IOException e){ System.out.println("LvlFile -> closeIn() -> IO Fehler!"); }
  }
  
  //OutputStream schliessen
  public void closeOut(){
    try{
      //Datei schliessen
      this.outB.close();
    }
    catch(IOException e){ System.out.println("LvlFile -> closeOut() -> IO Fehler!"); }
  }
  
  //--- String ---//
  
  //BufferedReader schliessen
  public void closeInT(){
    try{
      //Datei schliessen
      this.inT.close();
    }
    catch(IOException e){ System.out.println("LvlFile -> closeInT() -> IO Fehler!"); }
  }
  
  //BufferedWriter schliessen
  public void closeOutT(){
    try{
      //Datei schliessen
      this.outT.close();
    }
    catch(IOException e){ System.out.println("LvlFile -> closeOutT() -> IO Fehler!"); }
  }
  
  //##################### READ #####################//
  
  //--- Binary ---//
  
  //Byte lesen
  public byte[] read(int x){
    byte[] data = new byte[x];
    return read(data);
  }
  
  public byte[] read(byte[] data){
    return read(data,0,data.length);
  }
  
  public byte[] read(byte[] data, int off, int len){
    try{
      //Byte-Array fuellen
      this.inB.read(data,off,len);
    }
    catch(IOException e){ System.out.println("LvlFile -> read() -> IO Fehler!"); }
    //Rueckgabe
    return data;
  }
  
  public byte[] read(){ return read(this.buffer); }
  
  //--- String ---//
  
  //Zeile lesen (aus Datei)
  public String readlnT(){
    String out = "";
    try{
      out = this.inT.readLine();
	}
	catch(IOException e){ System.out.println("LvlFile -> readlnT() -> IO Fehler!"); }
	return out;
  }
  
  //Zeile lesen (aus Speicher)
  public String readln(int index){
    if(index>=0 && index<getLengthT()){
	  return this.data[index];
	}
	return "";
  }
  
  public String[] readln(int x, int laenge){
    if(x>=0 && x<this.lengthT && laenge>0){
      String[] out = new String[laenge];
      //Zeilen lesen
      for(int i=0; i<laenge; i++){ out[i]=readln(x+i); }
      return out;
    }
    else{
      String[] out = new String[1];
      out[0]="";
      return out;
    }
  }
  
  //Komplette Datei Zeilenweise einlesen
  public String[] getData(){
    String[] text;
    int x = 0;
	//Lesen initialisieren
	readInitT();
    //Zeilen zaehlen
	while(readlnT()!=null){ x++; }
	this.lengthT=x;
	//Datei schliessen
	closeInT();
	//Datei wieder oeffnen
	readInitT();
	//Array erstellen
	text = new String[x];
	//Array fuellen
	for(int i=0; i<x; i++){ text[i]=readlnT(); }
	//Datei schliessen
	closeInT();
	//Ausgabe
	return text;
  }
  
  //##################### WRITE #####################//
  
  //--- Binary ---//
  
  //Byte schreiben
  public void write(byte[] data){
    write(data,0,data.length);
  }
  
  public void write(byte[] data, int off, int len){
    try{
      //Byte-Array schreiben
      this.outB.write(data,off,len);
    }
    catch(IOException e){ System.out.println("LvlFile -> write() -> IO Fehler!"); }
  }
  
  //--- String ---//
  
  //Zeile schreiben (ersetzen)
  public void writeln(int x, String input){
    if(x>=0 && x<this.data.length){ this.data[x]=input; }
  }
  
  //---------- ADD/INSERT ----------
  
  //Zeile hinten anfuegen
  public void addln(String data1) { insertln(getLengthT(),data1); }
  public void addln(String[] data1) { insertln(getLengthT(),data1); }
  
  //Zeilen zwischendrin hinzufuegen, Index gibt die Position der Zeilen beginnend bei 0 nach dem einfuegen an!
  public void insertln(int index, String data1){
    if(index>=0 && index<=getLengthT()){
      String[] ersatz = new String[getLengthT()+1];
      //Zeilenweise neues Array befuellen
      for(int i=0; i<getLengthT()+1; i++){
        if(i==index){ ersatz[i]=data1; }
        if(i<index){ ersatz[i]=readln(i); }
        if(i>index){ ersatz[i]=readln(i-1); }
      }
      this.data=ersatz;
      this.lengthT=this.data.length;
    }
  }
  
  public void insertln(int index, String[] data1){
    if(index>=0 && index<=getLengthT()){
      String[] ersatz = new String[getLengthT()+data1.length];
      //Zeilenweise neues Array befuellen
      int j=0;
      for(int i=0; i<getLengthT()+data1.length; i++){
        if(i>=index && i<=index+data1.length-1){ ersatz[i]=data1[j]; j++; }
        if(i<index){ ersatz[i]=readln(i); }
        if(i>index){ ersatz[i]=readln(i-data1.length); }
      }
      this.data=ersatz;
      this.lengthT=this.data.length;
    }
  }
  
  //---------- DEL ----------
  
  //Zeile loeschen
  public void delln(int index) { delln(index,1); }
  
  //Zeile/n loeschen
  public void delln(int index, int laenge)
  {
    if(index>=0 && index<getLengthT() && laenge>0)
    {
      String[] ersatz = new String[getLengthT()-laenge];
      //Zeilenweise neues Array befuellen
      for(int i=0; i<getLengthT(); i++)
      {
        if(i>=index && i<=index+laenge-1) { continue; }
        if(i<index) { ersatz[i]=readln(i); }
        if(i>index) { ersatz[i-laenge]=readln(i); }
      }
      this.data=ersatz;
      this.lengthT=this.data.length;
    }
  }
  
  //---------- SAVE ----------
  
  //save() Speichert das String[] mit den Daten zeilenweise in Datei ab.
  public void save(){
    //Laenge pruefen
	if(getLengthT()>0){
	  //Datei oeffnen
	  writeInitT();
      //Daten speichern
	  for(int i=0; i<getLengthT(); i++){
	    try{
	      //Zeile speichern
		  this.outT.write(this.data[i]);
		  this.outT.newLine();
		}
		catch(IOException e){ System.out.println("LvlFile -> save() -> IO-Fehler!"); }
	  }
	  //Datei schliessen
	  closeOutT();
	  //Daten im Speicher erneuern
	  readInitDataT();
	}
	else if(getLengthT()==0){
	  try{
	    //Datei oeffnen
	    writeInitT();
		//Zeile speichern
	    this.outT.write("");
		//Datei schliessen
	    closeOutT();
		//Daten im Speicher erneuern
	    readInitDataT();
	  }
	  catch(IOException e){ System.out.println("LvlFile -> save() -> IO-Fehler-2!"); }
    }
	else{ System.out.println("LvlFile -> save() -> Keine Daten zum speichern vorhanden!"); }
  }
  
  //##################### SONSTIGES #####################//
  
  //--- Binary ---//
  
  //ueberspringt x Bytes der Datei
  public void skip(int x){ skip((long)x); }
  public void skip(long x){
    try{
      this.inB.skip(x);
	}
	catch(IOException e){ System.out.println("LvlFile -> skip() -> IO Fehler!"); }
  }
  
  //--- String ---//
  
  //mark()
  public void mark(int x){
    try{
	  this.inT.mark(x);
	}
	catch(IOException e){ System.out.println("LvlFile -> mark() -> IO Fehler!"); }
  }
  
  //reset()
  public void reset(){
    try{
	  this.inT.reset();
	}
	catch(IOException e){ System.out.println("LvlFile -> reset() -> IO Fehler!"); }
  }
  
  //--------------------------------- Suche ----------------------------------
  
  //Suche nur erste Fundstelle(Zeilenangabe) int,double,boolean,String
  public int find(int find)    { return findStr(String.valueOf(find)); }
  public int find(double find) { return findStr(String.valueOf(find)); }
  public int find(boolean find){ return findStr(String.valueOf(find)); }
  public int find(String find) { return findStr(find); }
  
  //Suche alle Fundstellen(Zeilenangabe) int,double,boolean,String
  public int[] findAll(int find)    { return findStrAll(String.valueOf(find)); }
  public int[] findAll(double find) { return findStrAll(String.valueOf(find)); }
  public int[] findAll(boolean find){ return findStrAll(String.valueOf(find)); }
  public int[] findAll(String find) { return findStrAll(find); }
  
  //Suche String in Datei und gebe die gefundene Zeile zurueck (immer nur erste Fundstellen)
  private int findStr(String find){
    int[] found = findStrAll(find);
    return found[0];
  }
  
  //Suche String in Datei, aber ALLE Fundstellen -1 = nichts gefunden!
  private int[] findStrAll(String find){
    if(getLengthT()>0){
      String found = "";
      //Zeilen durchsuchen
      for(int i=0; i<getLengthT(); i++){
        if(readln(i).indexOf(find)!=-1) { found=found + String.valueOf(i) + ";"; }
      }
	  //Pruefen ob was gefunden
	  if(found.equals("")==true){
	    int[] out = new int[1];
        out[0]=-1;
        return out;
	  }
	  else{
        //Letztes Zeichen entfernen
        found=found.substring(0,found.length()-1);
        //String parsen
        String[] tmp1 = found.split(";");
        int[] out = new int[tmp1.length];
        //Werte uebergeben und casten
        for(int i=0; i<tmp1.length; i++) { out[i]=Integer.parseInt(tmp1[i]); }
        return out;
	  }
    }
    int[] out = new int[1];
    out[0]=-1;
    return out;
  }
  
}