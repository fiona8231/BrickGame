
import javax.swing.*;


public class Main extends JFrame {
    public static void main(String[] args){

        JFrame ob = new JFrame();
        GamePlay game = new GamePlay();

        ob.setBounds(10,10,700,600);
        ob.setTitle("My Game");
        ob.setResizable(false);

        ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ob.add(game);
        ob.setVisible(true);


    }
}