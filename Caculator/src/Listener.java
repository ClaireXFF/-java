import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;

public class Listener implements ActionListener{
	private JieMian jm;
	private String text="";
	//用来判断是否在点击完运算符和=号后
	private boolean initText;
	
	public Listener(JieMian jm) {
		super();
		this.jm = jm;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		//获得产生事件e的按钮
		JButton button=(JButton)e.getSource();
		
		if(button.getText().equals("="))
		{
			ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
			try {
				text=jse.eval(text).toString();
				jm.jtf.setText(text);
			}
			catch (Exception ex) {}
			System.out.println(text);
			
			jm.setFontSize();
			initText=false;
		}
		
		if(button.getText().equals("+")||button.getText().equals("-")||button.getText().equals("*")||button.getText().equals("/")||button.getText().equals("%"))
		{
			text=text+button.getText();
			jm.jtfup.setText(text);
			System.out.println(text);
			initText=false;
		}
		
		if(button.getText().matches("^[0-9]*$")||button.getText().equals("."))
		{
			jm.setFontSize();
			
			if(initText==false)
			{
				jm.jtf.setText(button.getText());
				initText=true;
			}
			else {
				if(jm.jtf.getText().equals("0"))
					jm.jtf.setText("");
				jm.jtf.setText(jm.jtf.getText()+button.getText());
			}
			text=text+button.getText();
			
			System.out.println(text);
		}
		
		if(button.getText().equals("MR"))
		{
			text="";
			jm.jtf.setText("0");
			jm.jtfup.setText("");
			System.out.println(text);
			
			jm.setFontSize();
		}
		
		if(button.getText().equals("←"))
		{
			if(initText==false)
			{
				jm.jtf.setText("");
				text=jm.jtfup.getText();
				initText=true;
			}
			else {
				String t=jm.jtf.getText().substring(0,text.length()-1);
				jm.jtf.setText(t);
				text=jm.jtfup.getText()+t;
				
			}
			System.out.println(text);
			
			jm.setFontSize();
		}
	}
}
