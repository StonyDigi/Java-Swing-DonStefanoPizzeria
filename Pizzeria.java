package hfDonStefanoPizzeria;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Pizzeria {

	private JFrame framePizzeria;
	private JTable tablePizzeria;
	private List<Pizza> Pizzak;
	private JScrollPane scrollPanePizzeria;
	private JButton btnLegdragbbPizza;
	private JButton btnosszesBevetelFt;
	private JButton btnDragabb2500Szazalekosan;
	private JButton btnAkciosakFajlbair;
	private JLabel lblOsszesBevetel;
	private JButton btnFajlBeolvas;
	private JButton btnKilep;
	private JButton btnHibasAdatTorlese;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pizzeria window = new Pizzeria();
					window.framePizzeria.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pizzeria() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		framePizzeria = new JFrame();
		framePizzeria.setFont(new Font("Dialog", Font.BOLD, 18));
		framePizzeria.setTitle("Don Stefano Pizz\u00E9ria");
		framePizzeria.setBounds(100, 100, 1077, 703);
		framePizzeria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePizzeria.getContentPane().setLayout(null);
		
		scrollPanePizzeria = new JScrollPane();
		scrollPanePizzeria.setBounds(22, 22, 1010, 308);
		framePizzeria.getContentPane().add(scrollPanePizzeria);
		
		tablePizzeria = new JTable();
		scrollPanePizzeria.setViewportView(tablePizzeria);
		
		btnFajlBeolvas = new JButton("F\u00E1jlbeolvas");
		btnFajlBeolvas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFajlBeolvas.setBackground(SystemColor.activeCaption);
		btnFajlBeolvas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FajlKezelo fkObj = new FajlKezelo();
				String fajlNev = "pizzeria.csv";
				Pizzak = fkObj.fajlBeolvas(fajlNev);
				
				DefaultTableModel tablePizzakModel = new DefaultTableModel(new Object[] {
						"Azonosító",
						"Név",
						"Egységár",
						"Eladott db",
						"Akciós"},0);
				for (Pizza pizza : Pizzak) {
					tablePizzakModel.addRow(new Object[] {
							pizza.getAzonosito(),
							pizza.getNev(),
							pizza.getEgysegAr().toString(),
							pizza.getEladottDb().toString(),
							pizza.getAkciosSzovegesen()
					});	
				}
				tablePizzeria.setModel(tablePizzakModel);
				DefaultTableCellRenderer kozepreIgazit = new DefaultTableCellRenderer();
				kozepreIgazit.setHorizontalAlignment(JLabel.CENTER);
				for (int i = 0; i < tablePizzakModel.getColumnCount(); i++) {
					tablePizzeria.getColumnModel().getColumn(i).setCellRenderer(kozepreIgazit);
				}
				btnFajlBeolvas.setEnabled(false);
				btnHibasAdatTorlese.setEnabled(true);
			}
		});
		btnFajlBeolvas.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFajlBeolvas.setBounds(841, 355, 188, 37);
		framePizzeria.getContentPane().add(btnFajlBeolvas);
		
		btnKilep = new JButton("KIL\u00C9P");
		btnKilep.setFocusCycleRoot(true);
		btnKilep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnKilep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnKilep.setBackground(Color.RED);
		btnKilep.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnKilep.setBounds(844, 572, 188, 37);
		framePizzeria.getContentPane().add(btnKilep);
		
		btnLegdragbbPizza = new JButton("Legdr\u00E1g\u00E1bb Pizza");
		btnLegdragbbPizza.setFocusCycleRoot(true);
		btnLegdragbbPizza.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLegdragbbPizza.setBackground(SystemColor.activeCaption);
		btnLegdragbbPizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int max = 0;
				Pizza legDragabbPizza = Pizzak.stream()//Ahhoz, hogy az adatforrás elemein mûveletsorozatot hajtsunk végre, és az eredményeket összesítsük,
						//három részre van szükségünk: a forrásra , a köztes mûvelet(ek) re és egy terminálmûveletre.
						.filter(c->c.getEgysegAr()>max)
						.max(Comparator.comparing(Pizza::getEgysegAr))
						.orElseThrow(NoSuchElementException::new);
				
				JOptionPane.showMessageDialog(framePizzeria, 
						"Legdrágább pizza neve / egységára: "+legDragabbPizza.getNev()+"/"+legDragabbPizza.getEgysegAr(),
						"Legdrágább pizza neve és egységára",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnLegdragbbPizza.setEnabled(false);
		btnLegdragbbPizza.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLegdragbbPizza.setBounds(22, 355, 241, 37);
		framePizzeria.getContentPane().add(btnLegdragbbPizza);
		
		btnosszesBevetelFt = new JButton("\u00D6sszes bev\u00E9tel FT");
		btnosszesBevetelFt.setFocusCycleRoot(true);
		btnosszesBevetelFt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnosszesBevetelFt.setBackground(SystemColor.activeCaption);
		btnosszesBevetelFt.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				Integer osszesBevetel = Pizzak.stream()//adatfolyam átalakítása gyûjteménybe!
						  .map(x -> x.getEgysegAr()*x.getEladottDb())
						  .collect(Collectors.summingInt(Integer::intValue)); /* változtatható redukciós mûveletet nevezzük .collect -nek!!!
                 * mivel a kívánt eredményeket egy eredménytárolóba gyûjti össze.
                 * collectmûvelethez három funkcióra van szükség: egy beszállítói függvényre az eredménytároló új példányainak létrehozására
                 * , egy gyûjtõfunkcióra, amely egy bemeneti elemet épít be egy eredménytárolóba, és egy kombináló függvényre, amely az egyik
                 *  eredménytároló tartalmát egy másikba egyesíti. Ennek formája nagyon hasonló a közönséges redukció általános formájához
                 * 
                 */
				String pattern = "#,###,###";
				DecimalFormat decimalFormat = new DecimalFormat(pattern);
				decimalFormat.setGroupingSize(3);
				DecimalFormat df = new DecimalFormat(pattern);

				JOptionPane.showMessageDialog(framePizzeria, 
						"Összes bevétel : "+df.format(osszesBevetel) + " Ft",
						"Összbevétel",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnosszesBevetelFt.setEnabled(false);
		btnosszesBevetelFt.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnosszesBevetelFt.setBounds(22, 422, 241, 37);
		framePizzeria.getContentPane().add(btnosszesBevetelFt);
		
		btnDragabb2500Szazalekosan = new JButton("Dr\u00E1g\u00E1bb 2500 Ft-n\u00E1l %");
		btnDragabb2500Szazalekosan.setFocusCycleRoot(true);
		btnDragabb2500Szazalekosan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDragabb2500Szazalekosan.setBackground(SystemColor.activeCaption);
		btnDragabb2500Szazalekosan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ha a számodra fontos elemet 1-re, minden mást pedig 0-ra rendelsz, akkor az átlagos érték  százalékos aránya lesz az adatfolyamban.
				double pizzakDragabbMint2500FtSzazalekosan = Pizzak.stream().mapToDouble(obj ->obj.getEgysegAr()>2500 ? 1 : 0)
						.summaryStatistics()
						.getAverage();
				DecimalFormat df = new DecimalFormat("0.0#");
				JOptionPane.showMessageDialog(framePizzeria, 
						"A pizzák ennyi százaléka drágább mint 2500 Ft: "+df.format(pizzakDragabbMint2500FtSzazalekosan*100) + " % \n",
						"Drágább, mint 2500 Ft - %",
						JOptionPane.PLAIN_MESSAGE);
			}		
		});
		btnDragabb2500Szazalekosan.setEnabled(false);
		btnDragabb2500Szazalekosan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDragabb2500Szazalekosan.setBounds(22, 488, 241, 37);
		framePizzeria.getContentPane().add(btnDragabb2500Szazalekosan);
		
		btnAkciosakFajlbair = new JButton("Akci\u00F3sak f\u00E1jlba\u00EDr");
		btnAkciosakFajlbair.setFocusCycleRoot(true);
		btnAkciosakFajlbair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FajlKezelo fkObj = new FajlKezelo();
				fkObj.akciosPizzakFajlbaIr(Pizzak);
				fkObj.fajlMegnyit();
				JOptionPane.showMessageDialog(framePizzeria, 
						"Sikeres mûvelet: létrejött az akcios.csv a workspace-ben!!!",
						"Fájl létrehozva!!!",
						JOptionPane.INFORMATION_MESSAGE
						);
			}
		});
		btnAkciosakFajlbair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAkciosakFajlbair.setBackground(SystemColor.activeCaption);
		btnAkciosakFajlbair.setEnabled(false);
		btnAkciosakFajlbair.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAkciosakFajlbair.setBounds(22, 550, 188, 37);
		framePizzeria.getContentPane().add(btnAkciosakFajlbair);
		
		lblOsszesBevetel = new JLabel("");
		lblOsszesBevetel.setFocusCycleRoot(true);
		lblOsszesBevetel.setVisible(false);
		lblOsszesBevetel.setEnabled(false);
		lblOsszesBevetel.setOpaque(true);
		lblOsszesBevetel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOsszesBevetel.setBackground(Color.GREEN);
		lblOsszesBevetel.setBounds(283, 422, 211, 37);
		framePizzeria.getContentPane().add(lblOsszesBevetel);
		
		btnHibasAdatTorlese = new JButton("Hib\u00E1s adat t\u00F6rl\u00E9se");
		btnHibasAdatTorlese.setFocusCycleRoot(true);
		btnHibasAdatTorlese.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHibasAdatTorlese.setBackground(SystemColor.activeCaption);
		btnHibasAdatTorlese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int hibasIndex = -1;
				for (int i = 0; i < Pizzak.size(); i++) {
					if(Pizzak.get(i).getAzonosito().toString().length() !=9) {
						hibasIndex = i;
					}
				}
				Pizzak.remove(hibasIndex);
				((DefaultTableModel)tablePizzeria.getModel()).removeRow(hibasIndex);
				btnFajlBeolvas.setEnabled(false);
				btnHibasAdatTorlese.setEnabled(false);
				btnLegdragbbPizza.setEnabled(true);
				btnosszesBevetelFt.setEnabled(true);
				btnDragabb2500Szazalekosan.setEnabled(true);
				btnAkciosakFajlbair.setEnabled(true);
			}
		});
		btnHibasAdatTorlese.setEnabled(false);
		btnHibasAdatTorlese.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHibasAdatTorlese.setBounds(841, 403, 188, 31);
		framePizzeria.getContentPane().add(btnHibasAdatTorlese);
	}
}