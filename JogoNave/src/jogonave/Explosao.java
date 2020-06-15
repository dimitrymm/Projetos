
package jogonave;

import java.awt.Image;
import javax.imageio.ImageIO;

public class Explosao {
    Image imagemExplosao = null;
    Image imagemExplosao2 = null;
    
    //posicao da explosao
    private int x = 0;
    private int y = 0;
    private int tempoDeVida = 50;

    
    public Explosao(){
        try{
            
          imagemExplosao = ImageIO.read((getClass().getResource("/Graficos/e1.png")));  
         imagemExplosao2 = ImageIO.read((getClass().getResource("/Graficos/e2.png")));  
        }catch(Exception ex){
            
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

    public int getTempoDeVida() {
        return tempoDeVida;
    }

    public void setTempoDeVida(int tempoDeVida) {
        this.tempoDeVida = tempoDeVida;
    }
    
   
    
    
    
    
    
}
