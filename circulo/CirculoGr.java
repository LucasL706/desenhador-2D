package circulo;
import reta.FiguraRetas;
import ponto.PontoGr;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 * Escreva uma descrição da classe CIrculoGr aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class CirculoGr extends Circulo
{
    
    Color corCirculo = Color.BLACK;   // cor do circulo.
    String nomeCirculo = ""; // nome do circulo.
    Color corNomeCirculo  = Color.BLACK;
    int espCirculo = 1;
    
    /**
     * RetaGr - Constroi uma reta grafica
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param cor Color. Cor do circulo.
     * @param nome String. Nome do circulo.
     * @param esp int. Espessura do circulo.
     */
    public CirculoGr(int x1, int y1, int x2, int y2, Color cor, String nome, int esp){
        super(x1, y1, x2, y2);
        setCorCirculo(cor);
        setNomeCirculo(nome);
        setEspCirculo(esp);
    }
    
    /**
     * Altera a cor do circulo.
     *
     * @param cor Color. do circulo.
     */
    public void setCorCirculo(Color cor) {
        this.corCirculo = cor;
    }
    
    /**
     * Altera o nome do circulo.
     *
     * @param str String. Nome do circulo.
     */
    public void setNomeCirculo(String str) {
        this.nomeCirculo = str;
    }

    /**
     * Altera a espessura do circulo.
     *
     * @param esp int. Espessura do circulo.
     */
    public void setEspCirculo(int esp) {
        this.espCirculo = esp;
    }

    /**
     * Retorna a espessura do circulo.
     *
     * @return int. Espessura do circulo.
     */
    public int getEspCirculo() {
        return(this.espCirculo);
    }

    /**
     * Retorna a cor do circulo.
     *
     * @return Color. Cor do circulo.
     */
    public Color getCorCirculo() {
        return corCirculo;
    }
    
    /**
     * Retorna o nome da do circulo.
     *
     * @return String. Nome do circulo.
     */
    public String getNomeCirculo() {
        return nomeCirculo;
    }

    /**
     * @return the corNomeCirculo
     */
    public Color getCorNomeCirculo() {
        return corNomeCirculo;
    }

    /**
     * @param corNomeCirculo the corNomeCirculo to set
     */
    public void setCorNomeCirculo(Color corNomeCirculo) {
        this.corNomeCirculo = corNomeCirculo;
    }
    
    public double getRaio()
    {
        double r;
        r= Math.sqrt(Math.pow(p2.getX()-p1.getX(),2)+Math.pow(p2.getY()-p1.getY(),2));
        return r;
    }
   
    
    private void desenharPontosSimetricos(Graphics g, int x, int y, PontoGr ponto)
    {
        
        int cx = (int)getP1().getX();
        int cy = (int)getP1().getY();
        
        ponto.setCorPto(getCorCirculo());
        ponto.setDiametro(getEspCirculo());
        
        g.setColor(getCorNomeCirculo());
        g.drawString(getNomeCirculo(), cx, cy);
        
        desenharPontoSimetrico(g, cx + x, cy + y, ponto);
        
        desenharPontoSimetrico(g, cx + y, cy + x, ponto);
        
        desenharPontoSimetrico(g, cx - y, cy + x, ponto);
        
        desenharPontoSimetrico(g, cx - x, cy + y, ponto);
        
        desenharPontoSimetrico(g, cx - x, cy - y, ponto);
        
        desenharPontoSimetrico(g, cx - y, cy - x, ponto);
        
        desenharPontoSimetrico(g, cx + y, cy - x, ponto);
        
        desenharPontoSimetrico(g, cx + x, cy - y, ponto);
        
        
    }
    
    private void desenharPontoSimetrico(Graphics g, int x, int y, PontoGr ponto){
        ponto.setX(x);
        ponto.setY(y);
        ponto.desenharPonto(g);
    }
    
    public void desenharCirculo(Graphics g){
        PontoGr ponto = new PontoGr();
        double x,y;

        double angIni=0;
        double angFim=45;
        double incr = 0.1;
        double alfa=0;

        for(alfa=angIni;alfa<=angFim;alfa=alfa +incr){

            x=getRaio() * Math.sin((alfa*Math.PI)/180.);
            y=getRaio() * Math.cos((alfa*Math.PI)/180.);

            desenharPontosSimetricos(g,(int)x ,(int)y, ponto);

        }

    }
    
}
