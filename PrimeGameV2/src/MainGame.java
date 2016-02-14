//PRIME GAME MAIN
import javax.swing.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import java.awt.*;
import java.awt.event.*;

public class MainGame extends JFrame{
	private JPanel background1;
	public JLabel statusBar;
	public JLabel cursorSquare = new JLabel();
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^NEW FIELDS^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	Enemy enemy1 = new Enemy();
	Enemy enemy2 = new Enemy();
	Enemy enemy3 = new Enemy();
	Enemy enemy4 = new Enemy();
	Enemy enemy5;
	Enemy enemy6;
	Enemy enemy7;
	Enemy enemy8;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	int enemyPrimeStatus[] = {1,0,0,0,0,2,2,2,2};
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	Timer timer;
	Timer timer2;
	//********************************CHANGING VARIABLES*********************************************
	public static int speedProgress = 5;
	

	int gameModeVar;
	int previousScoreMode1Var;
	int previousScoreMode2Var;
	public static int progress;
	
	public static int score;
	public static int timeAttackScore;

	
	//******************************START OF CONSTRUCTOR****************************************
	public MainGame(int gameMode, int previousScoreMode1, int previousScoreMode2){
	
	setTitle("Prime Time!!");
	setSize(1350,700);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(null);
	
	score = 0;
	progress = 0;
	timeAttackScore = 0;
	
	enemies.add(enemy1);
	enemies.add(enemy2);
	enemies.add(enemy3);
	enemies.add(enemy4);
	
	background1 = new JPanel();
	background1.setBackground(new Color(228, 131, 255));
	add(background1);
	background1.setBounds(0,50,1350,650);
	background1.setLayout(null);
	
	statusBar = new JLabel("default");
	add(statusBar);
	statusBar.setBounds(0,0,300,50);


	background1.add(cursorSquare);
	cursorSquare.setBackground(Color.MAGENTA);
	cursorSquare.setOpaque(true);
	gameModeVar = gameMode;
	previousScoreMode1Var = previousScoreMode1;
	previousScoreMode2Var = previousScoreMode2;
	//*******************ENEMY CLASS TESTING**********************
	
	
	background1.add(enemy1);
	enemyPrimeStatus[1] = enemy1.randomValue();
	
	background1.add(enemy2);
	enemyPrimeStatus[2] = enemy2.randomValue();
	enemy2.setBounds(600,100,50,50);
	
	background1.add(enemy3);
	enemyPrimeStatus[3] =enemy3.randomValue();
	enemy3.setBounds(100,500,50,50);
	
	background1.add(enemy4);
	enemyPrimeStatus[4] = enemy4.randomValue();
	enemy4.setBounds(600,500,50,50);


	
	
	
	Handlerclass handler = new Handlerclass();
	background1.addMouseListener(handler);
	background1.addMouseMotionListener(handler);

	
	ReminderBeep(40);
	ProgressIndicator(speedProgress);

	}
	


	private class Handlerclass implements MouseListener, MouseMotionListener{
		int x1;
		int y1;
		public void mouseClicked(MouseEvent event){
		}
		public void mousePressed(MouseEvent event){
		}
		public void mouseReleased(MouseEvent event){
		}
		public void mouseEntered(MouseEvent event){
		}
		public void mouseExited(MouseEvent event){
		}
		
		public void mouseDragged(MouseEvent event){
			x1 = event.getX();
			y1 = event.getY();
			
			cursorSquare.setBounds(x1-25, y1-25, 50,50);
			
		}
		public void mouseMoved(MouseEvent event){
			x1 = event.getX();
			y1 = event.getY();
			
			cursorSquare.setBounds(x1-25, y1-25, 50,50);
		}
		}
	
	//***********************TIMER CREATIONS****************************************************************
	public void ReminderBeep(int seconds) {
	    timer = new Timer();
	    timer.schedule(new RemindTask(),50, seconds);
	  	}
	
	public void ProgressIndicator(int seconds) {
		timer2 = new Timer();
		timer2.schedule(new ProgressTask(),999, seconds*1000);
		}
	
  //******************************TIMER CLASS*************************************************************
  class RemindTask  extends TimerTask {
	    public void run() {
    		timeAttackScore++;
    		
    		int enemyNumber = 1;
    		for (Enemy e : enemies) {
    			collisionDetectorAndEvent(e, enemyNumber++);
    		}	
    		/*for (int i=0; i<enemies.size(); i++) {
    			collisionDetectorAndEvent(enemies.get(i), i);
    		}*/
    		
    		//collisionDetectorAndEvent(enemy1,1);
    		//collisionDetectorAndEvent(enemy2,2);
    		//collisionDetectorAndEvent(enemy3,3);
    		//collisionDetectorAndEvent(enemy4,4);
    		//collisionDetectorAndEvent(enemy5,5);
    		//collisionDetectorAndEvent(enemy6,6);
    		//collisionDetectorAndEvent(enemy7,7);
    		//collisionDetectorAndEvent(enemy8,8);
    		
	    	statusBar.setText(""+score);
	    	
	    	areAllEnemiesCompositeAction();
	    	
    		
	    }
  }

  public void collisionWithComposite() {
	  if(gameModeVar == 1){
		  score = score - 10;
	  }
	  else if(gameModeVar ==2){
	  	JOptionPane.showMessageDialog(null,  "You lose sucka!");
		timer.cancel();
		timer.purge();
		timer2.cancel();
		timer2.purge();
		dispose();
		if(score >= previousScoreMode2Var){
			new HomeScreen(previousScoreMode1Var,score);
		}
		else{
			new HomeScreen(previousScoreMode1Var,previousScoreMode2Var);
		}
	  }
  }//end of collisionWithComposite
  
  public void collisionDetectorAndEvent(Enemy label, int enemyNumber){
	  if(label != null){
		if(label.isOnEnemy(cursorSquare)==true){

			if(enemyPrimeStatus[enemyNumber] == 0){
				score = score + 5;
			}
			else{
				collisionWithComposite();
			}
			enemyPrimeStatus[enemyNumber] = label.randomValue();
			label.relocate(cursorSquare);
			label.changeEnemySpeed();
		}
	  }
  }//end of collisionDetectorAndEvent
  
  public void areAllEnemiesCompositeAction(){
	  if( areAllEnemiesComposite() == false  ){
		  int enemyNumber = 1;
		  for (Enemy e : enemies) {
  			e.makeEnemyPrime();
  			enemyPrimeStatus[enemyNumber++] = 0;
  		}	
		  
		 // enemy1.makeEnemyPrime();
		 // enemyPrimeStatus[1] = 0;
		 // enemy2.makeEnemyPrime();
		 // enemyPrimeStatus[2] = 0;
		 // enemy3.makeEnemyPrime();
		 // enemyPrimeStatus[3] = 0;
		 // enemy4.makeEnemyPrime();
		 // enemyPrimeStatus[4] = 0;
	  	/*for (int i=0;i<enemyPrimeStatus.length;i++){
	  		if (enemyPrimeStatus[i] < 2){
	  			//method to make numbers prime
	  		}
	  	}*/  //for end
	  }	
  }	  
 
  public boolean areAllEnemiesComposite(){
	  int noPrime = 0;
	  for (int i=0;i<enemyPrimeStatus.length;i++){
		  if (enemyPrimeStatus[i] == 0){
			  noPrime = 1;
		  }
	  }
	  if(noPrime == 1){
		  return true;
	  }
	  else{
		  return false;
	  }
	  
	  
  }
  
  
  
  //********************SLOW COUNTER******************************************
	    class ProgressTask  extends TimerTask {
		    public void run() {
		    	
		    	progress = progress+1;
		    	
		    	if(progress == 2){
		    		enemy5 = new Enemy();
		    		background1.add(enemy5);
		    		enemyPrimeStatus[5] = enemy5.randomValue();
		    		enemy5.relocate(cursorSquare);
		    		enemies.add(enemy5);
		    	}
		    	if(progress == 4){
		    		enemy6 = new Enemy();
		    		background1.add(enemy6);
		    		enemyPrimeStatus[6] = enemy6.randomValue();
		    		enemy6.relocate(cursorSquare);
		    		enemies.add(enemy6);
		    		
		    	}
		    	if(progress == 6){
		    		enemy7 = new Enemy();
		    		background1.add(enemy7);
		    		enemyPrimeStatus[7] = enemy7.randomValue();
		    		enemy7.relocate(cursorSquare);
		    		enemies.add(enemy7);
		    	}
		    	if(progress == 8){
		    		enemy8 = new Enemy();
		    		background1.add(enemy8);
		    		enemyPrimeStatus[8] = enemy8.randomValue();
		    		enemy8.relocate(cursorSquare);
		    		enemies.add(enemy8);
		    	}//end of progress8 enemy add
		    	
		    	if(gameModeVar==1){
		    		if(score >= 100){
		    			JOptionPane.showMessageDialog(null,  "Congrats! You made it to 100!");
		    			timer.cancel();
		                timer.purge();
		                timer2.cancel();
		                timer2.purge();
		    			dispose();
		    			new HomeScreen(timeAttackScore/25,previousScoreMode2Var);
		    		}
		    	}
		    	
		    	
		    }
	    }
}
  
	  
	

	
	