package hfDonStefanoPizzeria;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class FajlKezelo {
	private static String fejlec = "";
	public List<Pizza> fajlBeolvas(String fajlNev){
		List<Pizza> Pizzak = new ArrayList<Pizza>();
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(fajlNev), "UTF-8"));
			fejlec = br.readLine();
			
			while (br.ready()) {
				String sor = br.readLine();
				String[] sorAdatok = sor.split(";");
				
				Pizza ujPizza = new Pizza(
						sorAdatok[0],
						sorAdatok[1],
						Integer.parseInt(sorAdatok[2]),
						Integer.parseInt(sorAdatok[3]),
						sorAdatok[4].equals("1"));
				Pizzak.add(ujPizza);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return Pizzak;
}
	public void akciosPizzakFajlbaIr(List<Pizza> Pizzak) {
		try {
			FileOutputStream fs = new FileOutputStream("akcios.csv", false);
			OutputStreamWriter out = new OutputStreamWriter(fs, "UTF-8");
			out.write(fejlec);
			out.write("\n");
			for (Pizza pizza : Pizzak) {
				if(pizza.getAkciosSzovegesen().equalsIgnoreCase("igen")) {
					out.write(pizza.getAzonosito());
					out.write(";");
					out.write(pizza.getNev());
					out.write(";");
					out.write(pizza.getEgysegAr().toString());
					out.write(";");
					out.write(pizza.getEladottDb().toString());
					out.write(";");
					if(pizza.getAkcios()) {
						out.write("1");
					} else {
						out.write("0");
					}
					out.write("\n");
				}	
			}
			out.close();
			fs.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}	
	}
	public void fajlMegnyit() {
		File file = new File("C:\\Users\\stony\\Desktop\\Workspace\\webler_13\\akcios.csv");
		if(!Desktop.isDesktopSupported()) {
		System.out.println("Nem támogatott ezen az OP rendszeren az asztali megnyitás mód!!!");
		return;
		}
	Desktop desktop = Desktop.getDesktop();
	if (file.exists()) {
		try {
			desktop.open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}	
	}	
	}
}