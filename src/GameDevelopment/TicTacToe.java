package GameDevelopment;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener{ //To give functionality to Buttons, ActionListener is used
	
	public static int BOARD_SIZE=3;
	
	public static enum GameStatus //Different results of the game
	{
		Incomplete,XWins,ZWins,Tie
	}
	
	private JButton[][] buttons=new JButton[BOARD_SIZE][BOARD_SIZE]; //Button in application
	
	boolean crossTurn=true;
	
	public TicTacToe()
	{
		super.setTitle("TicTacToe");//Used to set the game name
		super.setSize(800,800);//Size of application
		GridLayout grid=new GridLayout(BOARD_SIZE,BOARD_SIZE); //Create a grid
		super.setLayout(grid); //Add grid in application
		Font font=new Font("Comic Sans",1, 150); //Font in frame
		for(int row=0;row<BOARD_SIZE;row++)
		{
			for(int col=0;col<BOARD_SIZE;col++)
			{
				JButton button=new JButton(""); //Add buttons								
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
				buttons[row][col]=button;
			}
		}
		super.setResizable(false); //Unable to change size of frame
		super.setVisible(true);//Make the frame visible
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton clickedButton=(JButton)e.getSource(); //Which button is clicked
		makeMove(clickedButton);
		GameStatus gs=this.getGameStatus();
		if(gs==GameStatus.Incomplete)
		{
			return;
		}
		
		declareWinner(gs);
		
		int choice=JOptionPane.showConfirmDialog(this,"Do you want to restart the game"); //Give choice to user
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
			super.dispose();
		}
	}
	
	private void declareWinner(GameStatus gs) 
	{
		if(gs==GameStatus.XWins)
			JOptionPane.showMessageDialog(this, "X Wins");
		else if(gs==GameStatus.ZWins)
			JOptionPane.showMessageDialog(this, "Z Wins");
		else
			JOptionPane.showMessageDialog(this, "It is a Tie");
	}

	private GameStatus getGameStatus() 
	{
		String text1="",text2="";
		int row=0,col=0;
		
		//text inside rows
		while(row<BOARD_SIZE)
		{
			col=0;
			while(col<BOARD_SIZE-1)
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
					return GameStatus.XWins;
				else
					return GameStatus.ZWins;
			}
			row++;
		}
		//text inside columns
		while(col<BOARD_SIZE)
		{
			row=0;
			while(row<BOARD_SIZE-1)
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
					return GameStatus.XWins;
				else
					return GameStatus.ZWins;
			}
			col++;
		}
		
		//test in diagonal 1
		row=0;
		col=0;
		while(row<BOARD_SIZE)
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
					return GameStatus.XWins;
				else
					return GameStatus.ZWins;
			}
		}
		
		//test in diagonal 2
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
					return GameStatus.XWins;
				else
					return GameStatus.ZWins;
			}
		}
		
		//If some blocks haven't been filled
		String txt="";
		for(row=0;row<BOARD_SIZE;row++)
		{
			for(col=0;col<BOARD_SIZE;col++)
			{
				txt=buttons[row][col].getText();
				if(txt.length()==0)
					return GameStatus.Incomplete;
			}
		}
		
		return GameStatus.Tie;
	}

	private void makeMove(JButton clickedButton) //This function will add 'X' and '0' in buttons
	{
		String btntext=clickedButton.getText();
		if(btntext.length()>0)
		{
			JOptionPane.showMessageDialog(this, "Invalid Move!"); //Display message
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
		
}
