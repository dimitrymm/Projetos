
package jogonave;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class NaveJogador {
    Image imagemNave = null; 
    Image imagemNave2 = null;  //cria o objeto que vai receber a imagem da nave 
    
    //Posicoes x e y da Nave
    
    private int x = 0;
    private int y = 0;
    
    private int larguraMax;
    private int larguraNave;
    
    private int vida = 300;
    
    private boolean naveViva = true;
    
    public NaveJogador (int largura){
        larguraMax = largura; // Aqui definimos que a largura max da nave
                                // a largura que foi passada como parametro neste metodo
        try{
            imagemNave = ImageIO.read((getClass().getResource("/Graficos/nave_2.jpg")));
            imagemNave2 =  ImageIO.read((getClass().getResource("/Graficos/nave2_2.jpg")));
            //Aqui passamos pro objeto da imagem da nave o endereco da imagem em si
            
        }catch(IOException ex){
            
        }
        larguraNave = imagemNave.getWidth(null);
        
    }
    
    public int  getX(){
        return x;
    }
    
    public void setX(int x){
        if(x < 0){
            x = 0;
        }
        if( x > larguraMax - larguraNave){
            x = larguraMax - larguraNave;
        }
        this.x = x;  
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    
    public int getLarguraNave(){
        return larguraNave;
    }

    public boolean isNaveViva() {
        return naveViva;
    }

    public void setNaveViva(boolean naveViva) {
        this.naveViva = naveViva;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    
    @Override
    public void finalize(){
        
        try {
            super.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(NaveJogador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
