package gameDevelopement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
//this interface 
public class TicTacToe extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int BOARD_SIZE=3;
	public TicTacToe() {
		
		super.setTitle("TicTacToe");
		super.setSize(800,800);
		GridLayout grid= new GridLayout(BOARD_SIZE,BOARD_SIZE);
		super.setLayout(grid);
		Font font =new Font("Comic Sans",1,150);
		// Adding button on each and every row
		for(int row=0;row<BOARD_SIZE;row++)
		{
			for(int col=0;col<BOARD_SIZE;col++)
			{
				
				JButton button= new JButton("");// Button is not having any value.
				buttons[row][col]=button;
				//set font of this button 
			
				
				button.setFont(font);
				button.addActionListener(this);// attach every button with my current object ,means we have attached listner for every button with a current object.
				super.add(button);//adding button to my frame
				  
			}
		}
		super.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton clickedButton=(JButton)e.getSource();
		makeMove(clickedButton);
		GameStatus gs=this.getGameStatus();
		
		if(gs==GameStatus.Incomplete)
		{
			return;
		}
		declareWinner(gs);
		
		int choice=JOptionPane.showConfirmDialog(this,"Do you want to restart the game");
		if(choice==JOptionPane.YES_OPTION)
		{
			for(int row=0;row<BOARD_SIZE;row++)
			{
				for(int col=0;col<BOARD_SIZE;col++)
				{
					buttons[row][col].setText("");
					  
				}
			}
			crossTurn=true;
			
		}
		else 
		{
			super.dispose();//terminate my application
		}
		
	}
	
	 
	private void makeMove(JButton clickedButton)
	{
		String btntext=clickedButton.getText();
		if(btntext.length()>0)
		{
			JOptionPane.showMessageDialog(this,"Invalid");
		}
		else 
		{
			if(crossTurn)
			{
				clickedButton.setText("X");
			}
			else 
			{
				clickedButton.setText("0");
			}
			crossTurn=!crossTurn;
		}
	}
	
	private GameStatus getGameStatus()
	{
		String text1="", text2="";
		int row=0,col=0;
		while(row<BOARD_SIZE)
		{
			col=0;
			while(col<BOARD_SIZE-1)//else index out of bound exception
			{
				text1=buttons[row][col].getText();
				text2=buttons[row][col+1].getText();
				if(!text1.equals(text2)||text1.length()==0)
				{
					break;
				}
				col++;
			}
			if(col==BOARD_SIZE-1)
			{
				if(text1.equals("X"))
						{
					     return GameStatus.XWINS;
						}
				else  {
					return GameStatus.ZWINS;
				}
			}
			row++;
		}
		
		
		col=0;
		while(col<BOARD_SIZE)
		{
			row=0;
			while(row<BOARD_SIZE-1)//else index out of bound exception
			{
				text1=buttons[row][col].getText();
				text2=buttons[row+1][col].getText();
				if(!text1.equals(text2)||text1.length()==0)
				{
					break;
				}
				row++;
			}
			if(row==BOARD_SIZE-1)
			{
				if(text1.equals("X"))
						{
					     return GameStatus.XWINS;
						}
				else  {
					return GameStatus.ZWINS;
				}
			}
			col++;
		}
		
		//test in diagonal
		
		row=0;
		col=0;
		while(row<BOARD_SIZE-1)
		{
			text1=buttons[row][col].getText();
			text2=buttons[row+1][col+1].getText();
			if(!text1.equals(text2)||text1.length()==0)
			{
				break;
			}
			row++;
			col++;
			if(row==BOARD_SIZE-1)
			{
				if(text1.equals("X"))
						{
					     return GameStatus.XWINS;
						}
				else  {
					return GameStatus.ZWINS;
				}
			}
		}
		
		//test in second diagonal
		
		row=BOARD_SIZE-1;
		col=0;
		while(row>0)
		{
			text1=buttons[row][col].getText();
			text2=buttons[row-1][col+1].getText();
			if(!text1.equals(text2)||text1.length()==0)
			{
				break;
			}
			row--;
			col++;
			if(row==0)
			{
				if(text1.equals("X"))
						{
					     return GameStatus.XWINS;
						}
				else  {
					return GameStatus.ZWINS;
				}
			}
		}
		// when no one has won the game yet
		String txt="";
		for(row=0;row<BOARD_SIZE;row++) {
			for(col=0;col<BOARD_SIZE;col++)
			{
				txt=buttons[row][col].getText();
				if(txt.length()==0)
				{
					return GameStatus.Incomplete;
				}
			}
		}
		
		return GameStatus.TIE;
		
		
		
	}
	private void declareWinner(GameStatus gs)
	{
		if(gs==GameStatus.XWINS)
		{
			JOptionPane.showMessageDialog(this,"X wins");
		}
		else if(gs==GameStatus.ZWINS)
		{
			JOptionPane.showMessageDialog(this,"Z wins");
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Tie");
		}
	}
	
	
	
	private JButton[][] buttons= new  JButton[BOARD_SIZE][BOARD_SIZE];
	boolean crossTurn =true;
	public static enum GameStatus
	{
		Incomplete, XWINS ,ZWINS ,TIE 
	}
}
