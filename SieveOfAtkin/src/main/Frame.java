package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.print.attribute.standard.JobPrioritySupported;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

public class Frame extends JFrame implements ActionListener
{
	
	private final static int DEFAULT_WIDTH = 600;
	private final static int DEFAULT_HEIGHT = 400;
	private static int limit = (int) Math.pow(2, 16);
	BigInteger biLastPrimaryNumber;
	BigInteger biLimit = BigInteger.valueOf(limit);
	
	JTextArea jtaResult, jtaQuery;
	JPanel jpControlPanel;
	JButton bStart, bStop, bQuery, bGetNumber, bSendQuery;
	JTextField jtfLastNumber, jtfRange;
	JLabel jlLastNumber, jlRange, jlTitle, jlQuery;
	
	
	
	public DBConect connection = new DBConect();
	
	public Frame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Znajdywanie liczb pierwszych - Kacper Piotrowicz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setResizable(false);
		setLayout(new BorderLayout());
		addResultPanel();
		addControlPanel();
		welcome();
	
		
	}
	
	void addResultPanel()
	{
		jtaResult = new JTextArea("");
		jtaResult.setPreferredSize(new Dimension(200, 400));      
        jtaResult.setBorder(new LineBorder(Color.BLACK, 1, true));
        jtaResult.setWrapStyleWord(true);
        jtaResult.setLineWrap(true);
       
        
        this.add(jtaResult, BorderLayout.WEST);
        
       
        
        
	}
	
	void addControlPanel()
	{
		jpControlPanel = new JPanel();
		jpControlPanel.setPreferredSize(new Dimension(400, 400));
		jpControlPanel.setLayout(null);
		
		jlTitle = new JLabel("Wyznaczanie liczb pierwszych");
		jlTitle.setBounds(120, 10, 200, 20);
		jpControlPanel.add(jlTitle);
		
		jlLastNumber = new JLabel("Ostatnia wyznaczona liczba:");
		jlLastNumber.setBounds(10, 50, 200, 20);
		jpControlPanel.add(jlLastNumber);
		
		jtfLastNumber = new JTextField("");
		jtfLastNumber.setBounds(10, 70, 250, 20);
		jtfLastNumber.setEditable(false);
		jpControlPanel.add(jtfLastNumber);
		
		bGetNumber = new JButton("Pobierz");
		bGetNumber.setBounds(275, 60, 100, 30);
		bGetNumber.addActionListener(this);
		jpControlPanel.add(bGetNumber);
		
		jlRange = new JLabel("Zakres:");
		jlRange.setBounds(10, 100, 250, 20);
		jpControlPanel.add(jlRange);
		
		jtfRange = new JTextField();
		jtfRange.setBounds(10, 120, 250, 20);
		jpControlPanel.add(jtfRange);
		
		jlQuery = new JLabel("Zapytanie SQL:");
		jlQuery.setBounds(10, 150, 250, 20);
		jpControlPanel.add(jlQuery);
		
		jtaQuery = new JTextArea("SELECT * FROM `pn` ORDER BY `ID` DESC LIMIT 10");
		jtaQuery.setBounds(10, 170, 250, 60);
		jtaQuery.setBorder(new LineBorder(Color.BLACK, 1, true));
		jtaQuery.setWrapStyleWord(true);
		jtaQuery.setLineWrap(true);
		jpControlPanel.add(jtaQuery);
		
		bSendQuery = new JButton("<html><center>Wyœlij<br>zapytanie</center></html>");
		bSendQuery.setBounds(275, 170, 100, 60);
		bSendQuery.addActionListener(this);
		jpControlPanel.add(bSendQuery);
		
		bStart = new JButton("START");
		bStart.setBounds(25, 275, 155, 40);
		bStart.setEnabled(false);
		bStart.addActionListener(this);
		jpControlPanel.add(bStart);
		
		bStop = new JButton("STOP");
		bStop.setBounds(200, 275, 155, 40);
		bStop.addActionListener(this);
		jpControlPanel.add(bStop);
		
		
		this.add(jpControlPanel, BorderLayout.EAST);
		
	}
	
	void welcome()
	{
		jtaResult.setText("Aby rozpocz¹æ wyznaczanie liczb pierwszych:\n\n");
		jtaResult.append("1. Pobierz z bazy danych ostatni¹ wyznaczon¹ liczbê\n");
		jtaResult.append("2. Wybierz maksymalny zakres szukania.\n");
		jtaResult.append("3. Naciœnij START. Je¿eli faktoryzacja trwa bardzo d³ugo (liczby "
				+ "powy¿ej wartoœci int mo¿na przerwaæ operacjê za pomoc¹ przycisku STOP.\n\n");
		
		jtaResult.append("Aby wys³aæ zapytanie do bazy danych, uzupe³nij kwerendê w jêzyku SQL "
				+ ", a nastêpnie naciœnij przycisk WYŒLIJ");
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if(source == bGetNumber)
		{
			biLastPrimaryNumber = new BigInteger(connection.getLastPrimaryNumber());
			jtfLastNumber.setText(biLastPrimaryNumber.toString());
			
			BigInteger newRange = biLastPrimaryNumber.add(BigInteger.valueOf(1000));
			jtfRange.setText(newRange.toString());
			
			bStart.setEnabled(true);
		}
		
		if(source == bStart)
		{
			BigInteger range = new BigInteger(jtfRange.getText());
			
			if (range.compareTo(biLastPrimaryNumber) == 1)
			{
				if (range.compareTo(biLimit) == 1)
				{
					// TODO: BigInt calculation
				}
				
				else Calculations.SieveOfAtkin(Integer.parseInt(jtfRange.getText()));
				
			}
			
			else jtaResult.setText("Podano niew³aœciwy zakres!");
			
		}
		
		if(source == bSendQuery)
		{
			// TODO: send query
			
			jtaResult.setText("TODO: send query");
		}
		
	}

}
