package retangulo;
import reta.FiguraRetas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 * Escreva uma descrição da classe RetanguloGr aqui.
 * 
 * @author João Pedro de Souza Oliveira,Beatriz Lopes Rizzo,Guilherme Diniz Leocadio,Lucas Lombardi de Brito
 * @version (um número da versão ou uma data)
 */
public class RetanguloGr extends Retangulo
{
    // Atributos da reta grafica
    Color corReta = Color.BLACK;   // cor da reta
    String nomeReta = ""; // nome da reta
    Color corNomeReta  = Color.BLACK;
    int espReta = 1; // espessura da reta
    int x1, y1, x2,y2;
    // Construtores
    /**
     * RetaGr - Constroi uma reta grafica
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param cor Color. Cor da reta
     * @param nome String. Nome da reta
     * @param esp int. Espessura da reta
     */
    public RetanguloGr(int x1, int y1, int x2, int y2, Color cor, String nome, int esp){
        super (x1, y1, x2, y2);
        setCorReta(cor);
        setNomeReta(nome);
        setEspReta(esp);
    }    

    /**
     * Altera a cor da reta.
     *
     * @param cor Color. Cor da reta.
     */
    public void setCorReta(Color cor) {
        this.corReta = cor;
    }

    /**
     * Altera o nome da reta.
     *
     * @param str String. Nome da reta.
     */
    public void setNomeReta(String str) {
        this.nomeReta = str;
    }
    
    public void setEspReta(int esp) {
        this.espReta = esp;
    }
    
    /**
     * Altera a espessura da reta.
     *
     * @param esp int. Espessura da reta.
     */
    public void setEsp(int esp) {
        this.espReta = esp;
    }

    /**
     * Retorna a espessura da reta.
     *
     * @return int. Espessura da reta.
     */
    public int getEsp() {
        return(this.espReta);
    }

    /**
     * Retorna a cor da reta.
     *
     * @return Color. Cor da reta.
     */
    public Color getCorAtual() {
        return corReta;
    }

    /**
     * Retorna o nome da reta.
     *
     * @return String. Nome da reta.
     */
    public String getNomeReta() {
        return nomeReta;
    }

    /**
     * @return the corNomeReta
     */
    public Color getCorNomeReta() {
        return corNomeReta;
    }

    /**
     * @param corNomeReta the corNomeReta to set
     */
    public void setCorNomeReta(Color corNomeReta) {
        this.corNomeReta = corNomeReta;
    }
    
    /**
     * Método desenharRetangulo - desenha retangulo a partir de quatro retas graficas
     *
     * @param g Um parâmetro grafico
     */
    public void desenharRetangulo(Graphics g)
    {
        FiguraRetas.desenharReta(g,(int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY(),"",getEsp(),getCorAtual());        
        FiguraRetas.desenharReta(g,(int)p1.getX(),(int)p1.getY(),(int)p3.getX(),(int)p3.getY(),"",getEsp(),getCorAtual());
        FiguraRetas.desenharReta(g,(int)p2.getX(),(int)p2.getY(),(int)p4.getX(),(int)p4.getY(),"",getEsp(),getCorAtual());
        FiguraRetas.desenharReta(g,(int)p3.getX(),(int)p3.getY(),(int)p4.getX(),(int)p4.getY(),"",getEsp(),getCorAtual());
    }
}
