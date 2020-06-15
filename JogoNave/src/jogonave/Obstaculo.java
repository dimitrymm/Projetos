
package jogonave;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Obstaculo {
    private int x;
    private int y;
    
    Image imagemObstaculo = null;
    
    public Obstaculo(){
        try{
            imagemObstaculo = ImageIO.read((getClass().getResource("/Graficos/obs_2.jpg")));
            
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    
    
    
    
    
    
}
