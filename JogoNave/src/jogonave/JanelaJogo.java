/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogonave;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.Timer;


import java.awt.Font;
import java.awt.event.MouseEvent;


/**
 *
 * @author dimi_
 */
public class JanelaJogo extends javax.swing.JFrame {

    static int larguraJanela = 700;
    static int alturaJanela = 600;
    int pontos = 0;
    int pontos2 = 0;
    int vida1 = 300;
    int vida2 = 300;
    boolean teclaPausa = false;
    int obsX = 250;
    int obsY = 1;
    int obs2X = 250;
    int obs2Y = 100;
    int posEstrelaX = 0;
    int posEstrelaY = 0;
    int cont = 0;

//Aqui definimos o buffer para fazer os Graficos

    BufferedImage backBuffer = null;

//Declaracao dos objetos do tipo Nave
    NaveJogador nave1 = new NaveJogador(larguraJanela);
    NaveJogador nave2 = new NaveJogador(larguraJanela);
    Obstaculo obs = new Obstaculo();
    Obstaculo obs2 = new Obstaculo();
    Obstaculo obs3 = new Obstaculo();

//Variaveis booleanas para controle do movimento das Naves
    boolean bt_direito = false;
    boolean bt_esquerdo = false;
    boolean bt_direito2 = false;
    boolean bt_esquerdo2 = false;

    boolean dir_cima = false;
    boolean dir_baixo = false;

    public Timer getTemporizador() {
        return temporizador;
    }

    public void setTemporizador(Timer temporizador) {
        this.temporizador = temporizador;
    }



ArrayList<Disparo> listaDisparos = new ArrayList();
ArrayList<Disparo> listaDisparosInimigo = new ArrayList();
Disparo disparoAux;
Disparo disparoAux2;


ArrayList<Explosao> listaExplosoes = new ArrayList();


int contadorTempo = 0;

//Controle do fim da Partida
boolean gameOver = false;

    Timer temporizador = new Timer(10, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(teclaPausa == false){
             gameLoop();
        }else{
            
        }
       
    }
});
    public int getLargura(){
        return larguraJanela;
    }
     public int getAltura(){
        return alturaJanela;
    }
    
     public void setCont(int x ){
         cont = x;
     }
     public int getCont(){
         return cont;
     }
     
    private void detecColisao(){
        int x = 0;
        int y = 0;
        Rectangle2D.Double retDisparo  = new Rectangle2D.Double();
        Rectangle2D.Double retDisparoInimigo  = new Rectangle2D.Double();
        Rectangle2D.Double retNave2 = new Rectangle2D.Double();
        Rectangle2D.Double retNave1 = new Rectangle2D.Double();
        Rectangle2D.Double retObs = new Rectangle2D.Double();
        Rectangle2D.Double retObs2 = new Rectangle2D.Double();
        Rectangle2D.Double retObs3 = new Rectangle2D.Double();
        
       retNave2.setFrame(nave2.getX(),nave2.getY(),nave2.imagemNave2.getWidth(null),nave2.imagemNave2.getHeight(null));
       retNave1.setFrame(nave1.getX(),nave1.getY(),nave1.imagemNave.getWidth(null),nave1.imagemNave.getHeight(null));
       retObs.setFrame(obs.getX(),obs.getY(),obs.imagemObstaculo.getWidth(null),obs.imagemObstaculo.getHeight(null));
       retObs2.setFrame(obs2.getX(),obs2.getY(),obs2.imagemObstaculo.getWidth(null),obs2.imagemObstaculo.getHeight(null));
       retObs3.setFrame(obs3.getX(),obs3.getY(),obs3.imagemObstaculo.getWidth(null),obs3.imagemObstaculo.getHeight(null));
        for(x =0;x< listaDisparos.size();x++){
            Disparo disp = listaDisparos.get(x);
            
           try{
            retDisparo.setFrame(disp.getX(),disp.getY(),disp.imagemDisparo.getWidth(null),disp.imagemDisparo.getHeight(null));
            if(retDisparo.intersects(retNave2)){
                Explosao ex = new Explosao();
                ex.setX(disp.getX()+10);
                ex.setY(disp.getY()+10);
                listaExplosoes.add(ex);
                
                listaDisparos.remove(x);
                pontos += 100;
            }
            if(retDisparo.intersects(retObs)){
                Explosao ex = new Explosao();
                 ex.setX(disp.getX()+10);
                ex.setY(disp.getY()+10);
                listaExplosoes.add(ex);
                
                listaDisparos.remove(x);
            }
            if(retDisparo.intersects(retObs2)){
                Explosao ex = new Explosao();
                 ex.setX(disp.getX()+10);
                ex.setY(disp.getY()+10);
                listaExplosoes.add(ex);
                
                listaDisparos.remove(x);
            }
            if(retDisparo.intersects(retObs3)){
                Explosao ex = new Explosao();
                 ex.setX(disp.getX()+10);
                ex.setY(disp.getY()+10);
                listaExplosoes.add(ex);
                
                listaDisparos.remove(x);
            }

           }catch(Exception ex){
               System.out.println(ex.getMessage());
           }
            
            
            
            
        }
         for(y=0;y< listaDisparosInimigo.size();y++){
            Disparo disp = listaDisparosInimigo.get(y);
            
           try{
            retDisparoInimigo.setFrame(disp.getX(),disp.getY(),disp.imagemDisparo.getWidth(null),disp.imagemDisparo.getHeight(null));
            if(retDisparoInimigo.intersects(retNave1)){
                Explosao ex = new Explosao();
                ex.setX(disp.getX()+10);
                ex.setY(disp.getY()+10);
                listaExplosoes.add(ex);
                
                listaDisparosInimigo.remove(y);
                pontos2 += 100;
            }
            if(retDisparoInimigo.intersects(retObs)){
                Explosao ex = new Explosao();
                 ex.setX(disp.getX()+10);
                ex.setY(disp.getY()+10);
                listaExplosoes.add(ex);
                
                listaDisparosInimigo.remove(y);
            }
            if(retDisparoInimigo.intersects(retObs2)){
                Explosao ex = new Explosao();
                 ex.setX(disp.getX()+10);
                ex.setY(disp.getY()+10);
                listaExplosoes.add(ex);
                
                listaDisparosInimigo.remove(y);
            }
            if(retDisparoInimigo.intersects(retObs3)){
                Explosao ex = new Explosao();
                 ex.setX(disp.getX()+10);
                ex.setY(disp.getY()+10);
                listaExplosoes.add(ex);
                
                listaDisparosInimigo.remove(y);
            }

           }catch(Exception ex){
               System.out.println(ex.getMessage());
           }
    }
    
    }    
    

   
    public JanelaJogo() {
        initComponents();

        this.setLocation(700, 250);
        this.setResizable(false);

        //Setamos as dimensoes da janela
        this.setSize(larguraJanela, alturaJanela);

        backBuffer = (BufferedImage) jPanel1.createImage(larguraJanela, alturaJanela);
        backBuffer.createGraphics();

        //inicio do jogo
        temporizador.start();
        //Posicionamento das Naves

        obs.setX(obsX);
        obs.setY(obsY);

        obs2.setX(obsX + 50);
        obs2.setY(obsY + 100);

        obs3.setX(350);
        obs3.setY(230);

        nave1.setX(0);
        nave1.setY(250);

        nave2.setX(680);
        nave2.setY(200);

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    bt_esquerdo = true;
//                   bt_direito = false;

                }
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
//                    bt_esquerdo = false;
                    bt_direito = true;

                }

                if ((evt.getKeyCode() == KeyEvent.VK_SPACE)) {
                    Disparo d = new Disparo();
                    d.setX(nave1.getX() + nave1.getLarguraNave() - d.imagemDisparo.getWidth(null) / 2);
                    d.setY(nave1.getY() + 7);

                    listaDisparos.add(d);
                }

            }

            @Override
            public void keyReleased(KeyEvent evt) {
                if ((evt.getKeyCode() != KeyEvent.VK_SPACE) && (evt.getKeyCode() != KeyEvent.VK_X)) {
                    bt_esquerdo = false;
                    bt_direito = false;
                }
                if ((evt.getKeyCode() != KeyEvent.VK_X) && (evt.getKeyCode() != KeyEvent.VK_SPACE)) {
                    bt_esquerdo2 = false;
                    bt_direito2 = false;
                }

            }

        });

    }
    
    
    
    Random rand = new Random();
    
    public void desenhaEstrelas(Graphics2D g2) {
        g2.setColor(Color.WHITE);

        posEstrelaX = 250;
        posEstrelaY = 250;
        g2.fillOval(posEstrelaX, posEstrelaY, 2, 2);

        g2.fillOval(rand.nextInt(500), rand.nextInt(500), 4, 4);

    }
    
   
    private void desenhaTexto(Graphics2D g2) {

        g2.setColor(Color.yellow);
        g2.setFont(new Font("SansSerif", Font.ITALIC, 30));
        g2.drawString(String.valueOf(pontos), 100, 50);
        g2.drawString("Jogador 1", 60, 28);
        g2.setColor(Color.yellow);
        g2.drawString(String.valueOf(pontos2), 575, 50);
        g2.drawString("Jogador 2", 530, 28);
    }
    
    private void desenhaPausa(Graphics2D g2) {
        g2.drawImage(obs.imagemObstaculo, obs.getX(), obs.getY(), null);

        g2.drawImage(obs2.imagemObstaculo, obs2.getX(), obs2.getY(), null);

        g2.drawImage(obs3.imagemObstaculo, obs3.getX(), obs3.getY(), null);

        obs.setY(obs.getY() + 8);
        obs2.setY(obs2.getY() + 13);
        obs3.setY(obs3.getY() + 3);

        if (obs.getY() > 600) {
            obs.setY(-50);
        } else if (obs2.getY() > 600) {
            obs2.setY(-50);
        } else if (obs3.getY() > 600) {
            obs3.setY(-50);
        }

    }
    
    private void desenhaNave(Graphics2D g2) {
        if (nave1.isNaveViva()) {
            g2.drawImage(nave1.imagemNave, nave1.getX(), nave1.getY(), null);
            if (bt_esquerdo) {
                nave1.setY(nave1.getY() - 2);
            } else if (bt_direito) {
                nave1.setY(nave1.getY() + 2);
            }
        }
        if (pontos2 == 30) {
            Explosao ex = new Explosao();
            ex.setX(nave1.getX());
            ex.setY(nave1.getY());
            listaExplosoes.add(ex);

            nave1.setNaveViva(false);
        }

    }
    
    private void desenhaNave3(Graphics2D g2) {
        if (bt_esquerdo2) {
            nave2.setY(nave2.getY() - 2);
        } else if (bt_direito2) {
            nave2.setY(nave2.getY() + 2);
        }
        if (pontos == 10) {
            Explosao ex = new Explosao();
            ex.setX(nave2.getX());
            ex.setY(nave2.getY());
            listaExplosoes.add(ex);
            nave2.setY(10000);

            nave2.setNaveViva(false);
        }
        g2.drawImage(nave2.imagemNave2, nave2.getX(), nave2.getY(), null);

    }
    
    private void desenhaNave2(Graphics2D g2) {

        if (nave2.isNaveViva()) {
            g2.drawImage(nave2.imagemNave2, nave2.getX(), nave2.getY(), null);
            if ((nave1.getY() == nave2.getY() - 10) || (nave1.getY() == nave2.getY() - 5) || (nave1.getY() == nave2.getY() - 15) || (nave1.getY() == nave2.getY() - 20) || (nave1.getY() == nave2.getY() - 30) && (listaDisparosInimigo.size() < 10)) {
                Disparo disp = new Disparo();
                disp.setX(nave2.getX() + nave2.getLarguraNave() - disp.imagemDisparo.getWidth(null) / 2);
                disp.setY(nave2.getY() + 7);

                listaDisparosInimigo.add(disp);

            } else if ((nave1.getY() == nave2.getY() + 10) || (nave1.getY() == nave2.getY() + 5) || (nave1.getY() == nave2.getY() + 15) || (nave1.getY() == nave2.getY() + 20) || (nave1.getY() == nave2.getY() + 30) && (listaDisparosInimigo.size() < 10)) {
                Disparo disp = new Disparo();
                disp.setX(nave2.getX() + nave2.getLarguraNave() - disp.imagemDisparo.getWidth(null) / 2);
                disp.setY(nave2.getY() + 7);

                listaDisparosInimigo.add(disp);
            } else if ((nave1.getY() == nave2.getY()) && (listaDisparosInimigo.size() < 10)) {
                Disparo disp = new Disparo();
                disp.setX(nave2.getX() + nave2.getLarguraNave() - disp.imagemDisparo.getWidth(null) / 2);
                disp.setY(nave2.getY() + 7);

                listaDisparosInimigo.add(disp);
            }

            if (nave1.getY() > nave2.getY()) {
                dir_cima = true;
                dir_baixo = false;

            } else if (nave1.getY() < nave2.getY()) {
                dir_baixo = true;
                dir_cima = false;
            }

            if ((dir_cima == true) && (nave2.getY() > -10)) {

                nave2.setY(nave2.getY() + 1);

            } else if ((dir_baixo == true) && (nave2.getY() <= 700)) {

                nave2.setY(nave2.getY() - 1);
            }
        }

        if (pontos == 30) {
            Explosao ex = new Explosao();
            ex.setX(nave2.getX());
            ex.setY(nave2.getY());
            listaExplosoes.add(ex);

            nave2.setNaveViva(false);
        }

    }
  
        
    
    private void desenhaDisparo(Graphics2D g2){
        for(int i = 0; i< listaDisparos.size(); i++){
            disparoAux = listaDisparos.get(i);
            disparoAux.setX(disparoAux.getX()+5);
            if(disparoAux.getX()<-10){ 
                listaDisparos.remove(i);
            }
            g2.drawImage(disparoAux.imagemDisparo,disparoAux.getX(),disparoAux.getY(),null);
        }
    }
    
    
     private void desenhaDisparoInimigo(Graphics2D g2){
        for(int i = 0; i< listaDisparosInimigo.size(); i++){
            disparoAux2 = listaDisparosInimigo.get(i);
            disparoAux2.setX(disparoAux2.getX()-5);
            if(disparoAux2.getX()<0){
                listaDisparosInimigo.remove(i);
            }
            g2.drawImage(disparoAux2.imagemDisparo,disparoAux2.getX(),disparoAux2.getY(),null);
        }
    }
    
    private void desenhaExplosao(Graphics2D g2) {
        for (int i = 0; i < listaExplosoes.size(); i++) {
            Explosao e = listaExplosoes.get(i);
            e.setTempoDeVida(e.getTempoDeVida() - 1);
            if (e.getTempoDeVida() > 25) {
                g2.drawImage(e.imagemExplosao, e.getX(), e.getY(), null);
            } else {
                g2.drawImage(e.imagemExplosao2, e.getX(), e.getY(), null);
            }
            if (e.getTempoDeVida() <= 0) {
                listaExplosoes.remove(i);
            }
        }
    }
    
    private void desenhaBarraDeVida(Graphics2D g2){
        g2.setColor(Color.RED);
        
        g2.fillRect(10, 550, (vida1 - pontos2*10),15);
        
        g2.setColor(Color.BLUE);
        g2.fillRect(385, 550, (vida2 - pontos*10),15);
    }
    
    
    private void atualizarContador(){
        contadorTempo ++;
        if(contadorTempo > 100){
            contadorTempo = 0;
        }
    }
    
    private void fimDaPartida(Graphics2D fim ){
        if(pontos == 50){
            gameOver = true;
            fim.drawString("JOGADOR PERDEU", 250, 250);
        }
        
        
        try{
            Image imagenLuser = ImageIO.read((getClass().getResource("/Graficos/luser.png")));
            fim.drawImage(imagenLuser, 100, 100, null);
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }
    
    
  private void fim(Graphics2D fim ){
        if(nave2.isNaveViva()!=false){
            
            
        } else {
            gameOver = true;
            
            try{
                Image imagenLuser = ImageIO.read((getClass().getResource("/Graficos/luser.png")));
                fim.drawImage(imagenLuser, 150, 100, null);
            }catch(IOException ex){
                throw new RuntimeException(ex);
            }
     }
          if(nave1.isNaveViva()!=false){
            
            
        } else {
            gameOver = true;
            
            try{
                Image imagenLuser = ImageIO.read((getClass().getResource("/Graficos/luser.png")));
                fim.drawImage(imagenLuser, 150, 100, null);
            }catch(IOException ex){
                throw new RuntimeException(ex);
            }
     }
        
        
        
       
    }

    private void gameLoop(){
           
        Graphics2D g2 = (Graphics2D) backBuffer.getGraphics();
        if (!gameOver) {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, larguraJanela, alturaJanela);

            //g2.drawImage(disparoAux.imagemDisparo,disparoAux.getX(),disparoAux.getY(),null);
            desenhaNave(g2);
            desenhaNave2(g2);
            desenhaDisparoInimigo(g2);
            desenhaDisparo(g2);
            desenhaBarraDeVida(g2);
            desenhaExplosao(g2);
            desenhaTexto(g2);
            atualizarContador();
            detecColisao();
            desenhaPausa(g2);
            fim(g2);

        } else {
            fim(g2);
        }

        g2 = (Graphics2D) jPanel1.getGraphics();
        g2.drawImage(backBuffer, 0, 0, null);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void formKeyPressed(java.awt.event.KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_LEFT){
            bt_esquerdo = true;
            bt_direito = false;
        }
        if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
            bt_esquerdo = false;
            bt_direito = true;
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            Disparo d = new Disparo();
            d.setX(nave1.getX()+nave1.getLarguraNave()/2 - d.imagemDisparo.getWidth(null)/2);
            d.setY(nave1.getY());
            
            listaDisparos.add(d);
            
        }
    }
    
    public void formKeyReleased(java.awt.event.KeyEvent evt) {                                 
        if (evt.getKeyCode() != KeyEvent.VK_SPACE)
        {   
           bt_esquerdo = false;
           bt_direito = false;
        }
    } 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaJogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
