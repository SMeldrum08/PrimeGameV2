
import java.awt.Color;
import java.awt.Font;
import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;






public class Enemy extends JLabel {
	
	
	int[] nonPrimeArray = {
			1,4,6,8,9,10,12,14,15,16,18,20,21,22,24,25,26,27,28,30,32,
			33,34,35,36,38,39,40
		};
	int[] primeArray = {
			2,3,5,7,11,13,17,19,23,29,31,37
		};
	int xMovement = 10;
	int yMovement = 10;
	int enemySize = 50;
	int enemySizeIncreaseRate = 10;

	public Enemy(){
		

		setBackground(new Color(238,176,250));
		setOpaque(true);
		setBounds(100,100,enemySize,enemySize);
		setFont(new Font("Arial", Font.PLAIN, 30));
		setHorizontalAlignment(SwingConstants.CENTER);
		movementSpeedTimer(50);
		
		}
	
	public int randomValue(){
		int randomValue2 = (int )(Math.random()*2);
		if (randomValue2 == 0){
			setText(""+primeArray[(int )(Math.random()*12)]);
		}
		else {
			setText(""+nonPrimeArray[(int )(Math.random()*26)]);
		}
		return randomValue2;
	}//end of randomValue Method
	
	public boolean isOnEnemy(JLabel label){
		

		return label.getX() > (getX() - label.getWidth())
			&& label.getX() < (getX() + getWidth())
			&& label.getY() > (getY() - label.getHeight())
			&& label.getY() < (getY() + getHeight());
 
	}//end of isOnEnemy Method
	
	public boolean isNearEnemy(JLabel label){
		
		return label.getX() - 70 > (getX() - label.getWidth())
				&& label.getX() + 70 < (getX() + getWidth())
				&& label.getY() - 70 > (getY() - label.getHeight())
				&& label.getY() + 70 < (getY() + getHeight());
		
	}
	
	public void relocate(JLabel label){
		int randomXValue = (int )(Math.random()*1270+10);
		int randomYValue = (int )(Math.random()*550+10);
		setBounds(randomXValue,randomYValue, enemySize,enemySize);
		
		while(isNearEnemy(label) == true){
			randomXValue = (int )(Math.random()*1270+10);
			randomYValue = (int )(Math.random()*550+10);
			setBounds(randomXValue,randomYValue, enemySize,enemySize);
		}
	}
	

	public class MovementTask  extends TimerTask {
		int timerDuration;
		private JLabel label;
		public MovementTask(){
			
			this.label = label;
		}
		
	    public void run() {
	    	if(getX()>1299){
	    		xMovement = xMovement * -1;
	    	}
	    	else if(getX()<1){
	    		xMovement = xMovement * -1;
	    	}
	    	if(getY()>579){
	    		yMovement = yMovement * -1;
	    	}
	    	else if(getY()<1){
	    		yMovement = yMovement * -1;
	    	}
	    	
	    	setBounds(getX() + xMovement, getY()+yMovement, getWidth(), getHeight());
	    	
	    	timerDuration++;
	    	if(timerDuration % 100 == 0){
	    		enemySize = enemySize + enemySizeIncreaseRate;
	    	}
	    	

	    }
	}//end of MovementTaskClass
	
	public void movementSpeedTimer(int seconds) {
	    Timer timer = new Timer();
	    timer.schedule(new MovementTask(),50, seconds);
	  	}
	
}
