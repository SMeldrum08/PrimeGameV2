//PRIME GAME HOMESCREEN
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;









public class HomeScreen extends JFrame {
	
	public JLabel gameModeScoreLabel;
	public JButton startGame;
	public JButton startSurvivalGame;
	public JLabel gameModeSurvivalScoreLabel;
	public int gameMode1ScoreVar;
	public int gameMode2ScoreVar;
	public JLabel gameIntroLabel;
	
	public HomeScreen(int gameMode1Score, int gameMode2Score){
	
	setTitle("Prime Time Home Screen");
	setSize(1350,700);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(null);
	
	gameMode1ScoreVar =  gameMode1Score;
	gameMode2ScoreVar = gameMode2Score;
	
	
	gameIntroLabel = new JLabel("<html> Welcome to Prime Collecter, a game where your task is to collect the primes on screen.<br><br>"
			+"There are two modes to pick from: <br><br>"
			+"<ul> <li> Time Attack: Your task is to get to 100 points as quick as possible, how quick can you manage?</li>"
			+"<li> Survival Mode: Your task is to survive as long as possible while collecting as many primes as you can!</li></ul></html>");
	add(gameIntroLabel);
	Dimension gameIntroLabelSize = gameIntroLabel.getPreferredSize();
	gameIntroLabel.setBounds(100,100,gameIntroLabelSize.width, gameIntroLabelSize.height);
	
	startGame = new JButton("Start Game: Game Mode Time Attack");
	add(startGame);
	Dimension startGameButtonSize = startGame.getPreferredSize();
	startGame.setBounds(400,400,startGameButtonSize.width,startGameButtonSize.height);
	gameStarterClass1 gameStarter1 = new gameStarterClass1();
	startGame.addActionListener(gameStarter1);

	gameModeScoreLabel = new JLabel("Time Attack score : " + gameMode1Score);
	add(gameModeScoreLabel);
	Dimension gameModeScoreLabelSize = gameModeScoreLabel.getPreferredSize();
	gameModeScoreLabel.setBounds(400,500,gameModeScoreLabelSize.width, gameModeScoreLabelSize.height);
	
	startSurvivalGame = new JButton("Start Game: Survival Mode");
	add(startSurvivalGame);
	Dimension startSurvivalGameButtonSize = startSurvivalGame.getPreferredSize();
	startSurvivalGame.setBounds(800,400,startSurvivalGameButtonSize.width,startSurvivalGameButtonSize.height);
	gameStarterClass2 gameStarter2 = new gameStarterClass2();
	startSurvivalGame.addActionListener(gameStarter2);
	
	gameModeSurvivalScoreLabel = new JLabel("Survival score : " + gameMode2Score);
	add(gameModeSurvivalScoreLabel);
	Dimension gameModeSurvivalScoreLabelSize = gameModeSurvivalScoreLabel.getPreferredSize();
	gameModeSurvivalScoreLabel.setBounds(800,500,gameModeSurvivalScoreLabelSize.width, gameModeSurvivalScoreLabelSize.height);
	
	
	
	}
	
//**********************GAME STARTING CLASS************************************************
	
public class gameStarterClass1 implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			MainGame go = new MainGame(1,gameMode1ScoreVar,gameMode2ScoreVar);

			dispose();

		}
	
	}

public class gameStarterClass2 implements ActionListener{
	
	public void actionPerformed(ActionEvent event){
		MainGame go = new MainGame(2,gameMode1ScoreVar,gameMode2ScoreVar);

		dispose();

	}

}}
	
