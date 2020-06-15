
package jogonave;

import java.awt.Image;
import javax.imageio.ImageIO;

public class Disparo {
    Image imagemDisparo = null;
    
    //coordenadas do disparo
    private int x =0;
    private int y =0;
    
    //Definicao de um Atributo para representar o dano do tiro
    
    private int dano = 50;
    
    public Disparo(){
        try{
            
            imagemDisparo = ImageIO.read((getClass().getResource("/Graficos/disparo.png")));
        }catch(Exception ex){
            
        }
    }
    
    
    public int getX(){
        return x;
        
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    
    public int getDano(){
        return dano;
    }
}

