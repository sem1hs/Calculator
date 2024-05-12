import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class HesapMakinesi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textField;
	private JButton[] btn = new JButton[17];
	private JTextArea textArea;
	
	private int sayi1,sayi2;
	private String islem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HesapMakinesi frame = new HesapMakinesi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HesapMakinesi() {
		setTitle("Hesap Makinesi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 520);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Text Field Olusturma Ve Ekleme
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(10,11,224,20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setBackground(Color.gray);
		textField.setForeground(Color.white);
		
		// Button Ekleme 1. Satir
		btn[0]= new JButton("7");
		btn[0].setBounds(10,159,68,64);
		contentPane.add(btn[0]);
		
		btn[1]= new JButton("8");
		btn[1].setBounds(88,159,68,64);
		contentPane.add(btn[1]);
		
		btn[2]= new JButton("9");
		btn[2].setBounds(166,159,68,64);
		contentPane.add(btn[2]);
		
		// Button Ekleme 2. Satir
		btn[3]= new JButton("4");
		btn[3].setBounds(10,234,68,64);
		contentPane.add(btn[3]);
				
		btn[4]= new JButton("5");
		btn[4].setBounds(88,234,68,64);
		contentPane.add(btn[4]);
				
		btn[5]= new JButton("6");
		btn[5].setBounds(166,234,68,64);
		contentPane.add(btn[5]);
		
		// Button Ekleme 3. Satir
		btn[6]= new JButton("1");
		btn[6].setBounds(10,309,68,64);
		contentPane.add(btn[6]);
						
		btn[7]= new JButton("2");
		btn[7].setBounds(88,309,68,64);
		contentPane.add(btn[7]);
						
		btn[8]= new JButton("3");
		btn[8].setBounds(166,309,68,64);
		contentPane.add(btn[8]);
		
		// Button Ekleme 0  
		btn[9] = new JButton("0");
		btn[9].setBounds(10,383,68,64);
		contentPane.add(btn[9]);
		
		// Text Area Ekleme
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10,42,224,106);
		contentPane.add(textArea);
		textArea.setBackground(Color.gray);
		textArea.setForeground(Color.white);
		
		// Operator Ekleme
		btn[10] = new JButton("=");
		btn[10].setBounds(88,383,146,64);
		btn[10].addActionListener(this::SonucHesapla);
		contentPane.add(btn[10]);
		
		btn[11] = new JButton("+");
		btn[11].setBounds(244,159,68,64);
		contentPane.add(btn[11]);
		
		btn[12] = new JButton("-");
		btn[12].setBounds(244,234,68,64);
		contentPane.add(btn[12]);
		
		btn[13] = new JButton("X");
		btn[13].setBounds(244,309,68,64);
		contentPane.add(btn[13]);
		
		btn[14] = new JButton("/");
		btn[14].setBounds(244,383,68,64);
		contentPane.add(btn[14]);
		
		// Silme Butonu 
		btn[15] = new JButton("C");
		btn[15].setBounds(244,10,68,23);
		// Direkt ActionListener Ekleme
		btn[15].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
			}
		});
		contentPane.add(btn[15]);
		
		// Hepsini Silme Butonu 
		btn[16] = new JButton("CA");
		btn[16].setBounds(244,42,68,106);
		// Direkt ActionListener Ekleme
		btn[16].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textField.setText("");
			}
		});
		contentPane.add(btn[16]);
		
		
		// Button Color Degisme
		for(int i=0;i<17;i++) { 
			btn[i].setBackground(Color.gray);
			btn[i].setForeground(Color.white);
			btn[i].addMouseListener(new RenklendirmeMouseListener());
		}
		// Islemler - Yazdirma 
		for(int i=0;i<10;i++) {
			btn[i].addActionListener(this::RakamGir);
		}
		// Islemler - Operator
		for(int i=11;i<15;i++) {
			btn[i].addMouseListener(new IslemMouseAdapter());
		}
		
		
	}
	
	// Fonksiyon Ile Kullanma
	public void RakamGir(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		textField.setText(textField.getText() + button.getText());
	}
	public void SonucHesapla(ActionEvent e) {
		sayi2 = Integer.parseInt(textField.getText());
		int sonuc = 0;
		
		switch(islem) {
			case "+":sonuc= sayi1+sayi2; break;
			case "-":sonuc= sayi1-sayi2; break;
			case "/":sonuc= sayi1/sayi2; break;
			case "X":sonuc= sayi1*sayi2; break;
			default:textField.setText("Gecersiz Islem"); break;
		}
		
		textArea.append(sayi1 + " "+ islem + " " +  sayi2 + " = " + sonuc + " \n");
		sayi1=sonuc;
		textField.setText(sonuc +"");
	}
	
	// Interface (Mouse Listener) Ile Kullanma
	private class RenklendirmeMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			JButton button = (JButton)e.getSource();
			button.setBackground(new Color(100,100,100));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton button = (JButton)e.getSource();
			button.setBackground(Color.gray);	}
	}
	
	// // Inheritance (Mouse Adapter) Ile Kullanma
	private class IslemMouseAdapter extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			HesapMakinesi.this.sayi1 = Integer.valueOf(HesapMakinesi.this.textField.getText());
			
			JButton button = (JButton)e.getSource();
			islem = button.getText();
			
			textField.setText("");
		}
		
	}
}
