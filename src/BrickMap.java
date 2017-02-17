import java.awt.*;
import java.awt.Graphics2D;

public class BrickMap {
    //making 2D array

    public int map[][];
    public  int brickwith;
    public int brikcheight;

    //constructor
    public BrickMap(int row, int col){
        map = new int [row][col];

        for(int i =0; i< map.length; i++){
            for(int j= 0; j< map[0].length; j++){
                map[i][j] = 1;
            }
        }
        brickwith = 540/col;
        brikcheight = 150/row;
    }


    public void draw(Graphics2D g){

        for(int i =0; i< map.length; i++){
            for(int j= 0; j< map[0].length; j++){
                if(map[i][j] > 0){
                    g.setColor(Color.white);
                    g.fillRect(j*brickwith+80, i*brikcheight+50, brickwith,brikcheight);
                    //adding black border
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    //can not use fillRect, use drawRect
                    g.drawRect(j*brickwith+80, i*brikcheight+50, brickwith,brikcheight);

                }
            }
        }

    }
    public void  setBrickValue(int value, int row, int col){
        map[row][col]=value;

    }


}
