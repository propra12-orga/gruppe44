public class test{
  
  //Main
  public static void main(String[] args){
    test0();
  }
  
  //test0
  public static void test0(){
    LvlIni ini = new LvlIni("lvl0.ini");
	ini.addSection("lvl0");
	ini.addValue("lvl0","spieler",4);
	ini.addValue("lvl0","bomben",67);
	ini.addValue("lvl0","punkte",12);
	ini.save();
	ini = null;
	
	ini = new LvlIni("lvl0.ini");
	System.out.println(ini.readValue("lvl0","spieler") + "#" + ini.readValue("lvl0","bomben") + "#" + ini.readValue("lvl0","punkte"));
  }
  
}