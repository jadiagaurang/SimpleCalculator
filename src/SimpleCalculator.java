package src;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleCalculator extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	int intState;
	String strOperand1;
	String strOperand2;
	String strOperation;
	boolean blnDot = false;
	JTextField jtxtfieldDisplayBar;
	JFrame jfCalculator = this;

	public SimpleCalculator(){
		super("Simple Calculator");
		this.setSize(300, 175);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		FocusEvent focusEvent = new FocusEvent(this, FocusEvent.FOCUS_GAINED);
		dispatchEvent(focusEvent);

		intState = 0;
		strOperand1 = "";
		strOperand2 = "";
		strOperation = "";

		JPanel jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());
		this.add(jpMain);

		JPanel jpNorth = new JPanel();
		jpNorth.setLayout(new BorderLayout());
		jpMain.add(jpNorth, BorderLayout.NORTH);

		jtxtfieldDisplayBar = new JTextField("0");
		jtxtfieldDisplayBar.setHorizontalAlignment(JTextField.RIGHT);
		jtxtfieldDisplayBar.setEditable(false);
		jpNorth.add(jtxtfieldDisplayBar, BorderLayout.NORTH);

		JPanel jpNorthSouth = new JPanel();
		jpNorthSouth.setLayout(new GridLayout(1, 2));
		jpNorth.add(jpNorthSouth, BorderLayout.SOUTH);

		JButton jbtnBackspace = new JButton("Backspace");
		jbtnBackspace.addActionListener(this);
		jpNorthSouth.add(jbtnBackspace);

		JButton jbtnC = new JButton("C");
		jbtnC.addActionListener(this);
		jpNorthSouth.add(jbtnC);

		JPanel jpSouth = new JPanel();
		jpSouth.setLayout(new GridLayout(4, 5));
		jpMain.add(jpSouth, BorderLayout.CENTER);

		JButton jbtn7 = new JButton("7");
		jbtn7.addActionListener(this);
		jpSouth.add(jbtn7);
		JButton jbtn8 = new JButton("8");
		jbtn8.addActionListener(this);
		jpSouth.add(jbtn8);
		JButton jbtn9 = new JButton("9");
		jbtn9.addActionListener(this);
		jpSouth.add(jbtn9);
		JButton jbtnDivide = new JButton("/");
		jbtnDivide.addActionListener(this);
		jpSouth.add(jbtnDivide);
		JButton jbtnSquareRoot = new JButton("sqrt");
		jbtnSquareRoot.addActionListener(this);
		jpSouth.add(jbtnSquareRoot);

		JButton jbtn4 = new JButton("4");
		jbtn4.addActionListener(this);
		jpSouth.add(jbtn4);
		JButton jbtn5 = new JButton("5");
		jbtn5.addActionListener(this);
		jpSouth.add(jbtn5);
		JButton jbtn6 = new JButton("6");
		jbtn6.addActionListener(this);
		jpSouth.add(jbtn6);
		JButton jbtnMultiply = new JButton("*");
		jbtnMultiply.addActionListener(this);
		jpSouth.add(jbtnMultiply);
		JButton jbtnSquare = new JButton("x^2");
		jbtnSquare.addActionListener(this);
		jpSouth.add(jbtnSquare);

		JButton jbtn1 = new JButton("1");
		jbtn1.addActionListener(this);
		jpSouth.add(jbtn1);
		JButton jbtn2 = new JButton("2");
		jbtn2.addActionListener(this);
		jpSouth.add(jbtn2);
		JButton jbtn3 = new JButton("3");
		jbtn3.addActionListener(this);
		jpSouth.add(jbtn3);
		JButton jbtnSubstrate = new JButton("-");
		jbtnSubstrate.addActionListener(this);
		jpSouth.add(jbtnSubstrate);
		JButton jbtnUpon = new JButton("1/x");
		jbtnUpon.addActionListener(this);
		jpSouth.add(jbtnUpon);

		JButton jbtn0 = new JButton("0");
		jbtn0.addActionListener(this);
		jpSouth.add(jbtn0);
		JButton jbtnPM = new JButton("+/-");
		jbtnPM.addActionListener(this);
		jpSouth.add(jbtnPM);
		JButton jbtnDot = new JButton(".");
		jbtnDot.addActionListener(this);
		jpSouth.add(jbtnDot);
		JButton jbtnAdd = new JButton("+");
		jbtnAdd.addActionListener(this);
		jpSouth.add(jbtnAdd);
		JButton jbtnEqual = new JButton("=");
		jbtnEqual.addActionListener(this);
		jpSouth.add(jbtnEqual);
	}

	public void actionPerformed(ActionEvent e){
		String strCommand = e.getActionCommand();

		if(strCommand.equals("Backspace")){
			intState = 0;
			if(jtxtfieldDisplayBar.getText().length() == 0){
				jtxtfieldDisplayBar.setText("0");
			}
			else{
				String strTemp = jtxtfieldDisplayBar.getText(); 
				jtxtfieldDisplayBar.setText(strTemp.substring(0, (strTemp.length()-1)));
				strTemp = jtxtfieldDisplayBar.getText();
				if(strTemp.length() == 0){
					jtxtfieldDisplayBar.setText("0");
				}
			}
			return;
		}

		if(strCommand.equals("C")){
			intState = 0;
			strOperand1 = "";
			strOperand2 = "";
			strOperation = "";
			jtxtfieldDisplayBar.setText("0");
			return;
		}

		if(strCommand.equals(".")){
			blnDot = true;
		}

		if(strCommand.equals("+/-")){
			if(strOperand1 == ""){
				intState = 1;
			}
			else{
				if(jtxtfieldDisplayBar.getText().equals(strOperand1)){
					intState = 1;
					Double dblTemp = -1 * Double.parseDouble(strOperand1);
					strOperand1 = "" + dblTemp;
					jtxtfieldDisplayBar.setText(strOperand1);
					return;
				}
				else if(jtxtfieldDisplayBar.getText().equals(strOperand2)){
					intState = 3;
					Double dblTemp = -1 * Double.parseDouble(strOperand2);
					strOperand2 = "" + dblTemp;
					jtxtfieldDisplayBar.setText(strOperand2);
					return;
				}
			}
		}

		if(strCommand.equals("sqrt")){
			intState = 1;
			if(Double.parseDouble(strOperand1) < 0){
				System.err.println("Square Root of negative is imaginary number");
			}
			else{
				Double dblTemp = Math.sqrt(Double.parseDouble(strOperand1));
				strOperand1 = "" + dblTemp;
				jtxtfieldDisplayBar.setText(strOperand1);
				return;
			}
		}

		if(strCommand.equals("x^2")){
			intState = 1;
			Double dblTemp = Math.pow(Double.parseDouble(strOperand1), 2);
			strOperand1 = "" + dblTemp;
			jtxtfieldDisplayBar.setText(strOperand1);
			return;
		}

		if(strCommand.equals("1/x")){
			intState = 1;
			Double dblTemp = 1 / Double.parseDouble(strOperand1);
			strOperand1 = "" + dblTemp;
			jtxtfieldDisplayBar.setText(strOperand1);
			return;
		}

		switch(intState){

		case 0:{
			if(strCommand.equals("1")){
				strOperand1 = "1";
				intState = 1;
				jtxtfieldDisplayBar.setText(strOperand1);
			}
			if(strCommand.equals("2")){
				strOperand1 = "2";
				intState = 1;
				jtxtfieldDisplayBar.setText(strOperand1);
			}
			if(strCommand.equals("3")){
				strOperand1 = "3";
				intState = 1;
				jtxtfieldDisplayBar.setText(strOperand1);
			}
			if(strCommand.equals("4")){
				strOperand1 = "4";
				intState = 1;
				jtxtfieldDisplayBar.setText(strOperand1);
			}
			if(strCommand.equals("5")){
				strOperand1 = "5";
				intState = 1;
				jtxtfieldDisplayBar.setText(strOperand1);
			}
			if(strCommand.equals("6")){
				strOperand1 = "6";
				intState = 1;
				jtxtfieldDisplayBar.setText(strOperand1);
			}
			if(strCommand.equals("7")){
				strOperand1 = "7";
				intState = 1;
				jtxtfieldDisplayBar.setText(strOperand1);
			}
			if(strCommand.equals("8")){
				strOperand1 = "8";
				intState = 1;
				jtxtfieldDisplayBar.setText(strOperand1);
			}
			if(strCommand.equals("9")){
				strOperand1 = "9";
				intState = 1;
				jtxtfieldDisplayBar.setText(strOperand1);
			}
			if(blnDot){
				strOperand1 = "0";
				intState = 1;
				jtxtfieldDisplayBar.setText("0");
			}
			break;
		}

		case 1:{
			if(blnDot){
				strOperand1 += ".";		
				blnDot = false;
			}
			if(strCommand.equals("1")){
				strOperand1 += "1";
			}
			if(strCommand.equals("2")){
				strOperand1 += "2";
			}
			if(strCommand.equals("3")){
				strOperand1 += "3";
			}
			if(strCommand.equals("4")){
				strOperand1 += "4";
			}
			if(strCommand.equals("5")){
				strOperand1 += "5";
			}
			if(strCommand.equals("6")){
				strOperand1 += "6";
			}
			if(strCommand.equals("7")){
				strOperand1 += "7";
			}
			if(strCommand.equals("8")){
				strOperand1 += "8";
			}
			if(strCommand.equals("9")){
				strOperand1 += "9";
			}
			if(strCommand.equals("0")){
				strOperand1 += "0";
			}
			if(strCommand.equals("+")){
				strOperation = "+";
				intState = 2;
			}
			if(strCommand.equals("-")){
				strOperation = "-";
				intState = 2;
			}
			if(strCommand.equals("*")){
				strOperation = "*";
				intState = 2;
			}
			if(strCommand.equals("/")){
				strOperation = "/";
				intState = 2;
			}
			jtxtfieldDisplayBar.setText(strOperand1);
			break;
		}

		case 2:{
			if(strCommand.equals("1")){
				strOperand2 = "1";
				intState = 3;
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("2")){
				strOperand2 = "2";
				intState = 3;
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("3")){
				strOperand2 = "3";
				intState = 3;
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("4")){
				strOperand2 = "4";
				intState = 3;
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("5")){
				strOperand2 = "5";
				intState = 3;
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("6")){
				strOperand2 = "6";
				intState = 3;
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("7")){
				strOperand2 = "7";
				intState = 3;
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("8")){
				strOperand2 = "8";
				intState = 3;
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("9")){
				strOperand2 = "9";
				intState = 3;
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(blnDot){
				strOperand2 = "0";
				intState = 3;
				jtxtfieldDisplayBar.setText("0");
			}
			break;
		}

		case 3:{
			if(blnDot){
				strOperand2 += ".";		
				blnDot = false;
			}
			if(strCommand.equals("1")){
				strOperand2 += "1";
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("2")){
				strOperand2 += "2";
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("3")){
				strOperand2 += "3";
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("4")){
				strOperand2 += "4";
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("5")){
				strOperand2 += "5";
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("6")){
				strOperand2 += "6";
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("7")){
				strOperand2 += "7";
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("8")){
				strOperand2 += "8";
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("9")){
				strOperand2 += "9";
				jtxtfieldDisplayBar.setText(strOperand2);
			}
			if(strCommand.equals("0")){
				if(Double.parseDouble(strOperand2) == 0){
				}
				else{
					strOperand2 += "0";
					jtxtfieldDisplayBar.setText(strOperand2);
				}
			}
			if(strCommand.equals("+") || strCommand.equals("-") || strCommand.equals("*") || strCommand.equals("/")){
				if(strOperation.equals("+")){
					if(strOperand2 == ""){
						strOperation = strCommand;
					}
					else{
						double d = Double.parseDouble(strOperand1) + Double.parseDouble(strOperand2);
						strOperand1 = "" + d;
						strOperand2 = "";
						strOperation = strCommand;
						intState = 3;
						jtxtfieldDisplayBar.setText(strOperand1);
					}
				}
				if(strOperation.equals("-")){
					if(strOperand2 == ""){
						strOperation = strCommand;
					}
					else{
						double d = Double.parseDouble(strOperand1) - Double.parseDouble(strOperand2);
						strOperand1 = "" + d;
						strOperand2 = "";
						strOperation = strCommand;
						intState = 3;
						jtxtfieldDisplayBar.setText(strOperand1);
					}
				}
				if(strOperation.equals("*")){
					if(strOperand2 == ""){
						strOperation = strCommand;
					}
					else{
						double d = Double.parseDouble(strOperand1) * Double.parseDouble(strOperand2);
						strOperand1 = "" + d;
						strOperand2 = "";
						strOperation = strCommand;
						intState = 3;
						jtxtfieldDisplayBar.setText(strOperand1);
					}
				}
				if(strOperation.equals("/")){
					if(strOperand2 == ""){
						strOperation = strCommand;
					}
					else{
						double d = Double.parseDouble(strOperand1) / Double.parseDouble(strOperand2);
						strOperand1 = "" + d;
						strOperand2 = "";
						strOperation = strCommand;
						intState = 3;
						jtxtfieldDisplayBar.setText(strOperand1);
					}
				}
			}

			if(strCommand.equals("=")){
				if(strOperation.equals("+")){
					double d = Double.parseDouble(strOperand1) + Double.parseDouble(strOperand2);
					strOperand1 = "" + d;
					strOperand2 = "";
					intState = 1;
					jtxtfieldDisplayBar.setText(strOperand1);
				}
				if(strOperation.equals("-")){
					double d = Double.parseDouble(strOperand1) - Double.parseDouble(strOperand2);
					strOperand1 = "" + d;
					strOperand2 = "";
					intState = 1;
					jtxtfieldDisplayBar.setText(strOperand1);
				}
				if(strOperation.equals("*")){
					double d = Double.parseDouble(strOperand1) * Double.parseDouble(strOperand2);
					strOperand1 = "" + d;
					strOperand2 = "";
					intState = 1;
					jtxtfieldDisplayBar.setText(strOperand1);
				}
				if(strOperation.equals("/")){
					if(Double.parseDouble(strOperand2) == 0){
						System.err.println("Can not divid by zero.");
					}
					else{
						double d = Double.parseDouble(strOperand1) / Double.parseDouble(strOperand2);
						strOperand1 = "" + d;
						strOperand2 = "";
						intState = 1;
						jtxtfieldDisplayBar.setText(strOperand1);
					}
				}
			}
			break;
		}
		default:
			System.err.println("Into invalid state!");
		}
	}
}