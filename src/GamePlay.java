import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener{

    private boolean play = false;
    private  int score = 0;
    private  int bricks = 21;

    //setting how fast the ball should move
    private  Timer timer;
    private  int delay =8;

    //starting X position of slider bar, Y keeps the same
    private int playerX = 310;

    //starting position of ball
    private int ballpoX = 120;
    private int ballpoY = 350;

    //driction of the ball
    private int ballXdir = -1 ;
    private int ballYdir = -2;

    private BrickMap map;

    //constructor
    public GamePlay(){

        map = new BrickMap(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(delay, this::actionPerformed);
        timer.start();

    }

    public void paint(Graphics g){
        //background
        g.setColor(Color.PINK);
        g.fillRect(1,1,692,592);
        //drawing map
         map.draw((Graphics2D) g);

        //border
        g.setColor(Color.ORANGE);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,593);

        //score
        g.setColor(Color.BLACK);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("Score= \n" + score,520,40 );

        //slider
        g.setColor(Color.GREEN);

        //X cordinator changes all the time
        g.fillRect(playerX, 550,100,8);

        //the ball

        g.setColor(Color.YELLOW);
        g.fillOval(ballpoX,ballpoY,20,20);

        //game over
        if(ballpoY > 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score\n" + score, 190,300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart" , 230,350);

        }

        //you won
        if(bricks ==0){
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won", 260,400);
            play = false;

        }

        g.dispose();

    }

    @Override
    //action performed automatically called!!!
    public void actionPerformed(ActionEvent e) {
          timer.start();

        //check if the play is true, if we press the left or right key, then the play would be true
        if(play){

            //detecting intersection, create new rectangle over the ball
            if(new Rectangle(ballpoX, ballpoY,20,20).intersects(playerX, 550,100,8)){
                ballYdir = -ballYdir;
            }

            //intersection with the mapping blocks
            A :for(int i = 0 ; i< map.map.length; i++){
                for (int j = 0 ; j< map.map[0].length; j++){

                    //if the particular position in the map >0, then can detect the intersections
                    if(map.map[i][j] > 0 ){
                        int brickX = j * map.brickwith +80;
                        int brickY = i * map.brikcheight +50;
                        int brickWith = map.brickwith;
                        int brickHeight = map.brikcheight;

                        //creating rectangle around the bricks
                        Rectangle rect = new Rectangle(brickX, brickY, brickWith, brickHeight);
                        Rectangle brickRect = rect;
                        Rectangle ballRect = new Rectangle(ballpoX, ballpoY, 20,20);
                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0,i,j);
                            bricks--;
                            score += 5;

                            //if ball touch the left side or ball touches the right side
                            if(ballpoX + 19 <= brickRect.x || ballpoX +1 >= brickRect.x + brickRect.width)
                            {
                                ballXdir = - ballXdir;
                                ballYdir = -ballYdir ;

                            }else {
                                ballYdir = -ballYdir ;
                            }

                            break A;
                        }
                    }
                }
            }


            ballpoX +=  ballXdir;
            ballpoY +=  ballYdir;
        }
        if(ballpoX <=0){
            ballXdir = -ballXdir;
        }
        if(ballpoY <= 0){
            ballYdir = -ballYdir;
        }
        if(ballpoX>= 670){
            ballXdir = -ballXdir;
        }


          repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            //slider doesn't go over the panel
            if(playerX >= 600){
                playerX = 600;
            }else {
                moveright();
            }

        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){

            if(playerX <= 10){
                playerX = 10;
            }else {
                moveleft();
            }

        }
        //Restart
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballpoX =120;
                ballpoY = 350;
                ballXdir = -1 ;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                bricks = 21;
                map = new BrickMap(3,7);
                repaint();

            }

        }
    }

    public void moveright(){
        play = true;
        playerX += 20; // if press right key, the slider move 20 pixels right
    }
    public void moveleft(){
        play = true;
        playerX -= 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
