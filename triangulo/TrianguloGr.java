package triangulo;

import reta.FiguraRetas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import ponto.FiguraPontos;
/**
 * Escreva uma descrição da classe RetanguloGr aqui.
 * 
 * @author João Pedro de Souza Oliveira,Beatriz Lopes Rizzo,Guilherme Diniz Leocadio,Lucas Lombardi de Brito
 * @version (um número da versão ou uma data)
 */
public class TrianguloGr extends Triangulo
{
    // Atributos da reta grafica
    Color corReta = Color.BLACK;   // cor da reta
    String nomeReta = ""; // nome da reta
    Color corNomeReta  = Color.BLACK;
    int espReta = 1; // espessura da reta
    int x1, y1, x2,y2, x3, y3;

    double xNovo,yNovo;
    // Construtores
    /**
     * RetaGr - Constroi uma reta grafica
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param y3 int. Coordenada y3
     * @param x3 int. Coordenada x3
     * @param cor Color. Cor da reta
     * @param nome String. Nome da reta
     * @param esp int. Espessura da reta
     */
    public TrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3, Color cor, String nome, int esp){
        super (x1, y1, x2, y2, x3, y3);
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

    public void setNovoPontoX(double xNovo)
    {
        this.xNovo = xNovo;
    }

    public void setNovoPontoY(double yNovo)
    {
        this.yNovo = yNovo;
    }

    public double getNovoPontoX()
    {
        return xNovo;
    }

    public double getNovoPontoY()
    {
        return yNovo;
    }

    /**
     * Método desenharTriangulo grafico a partir de 3 retas
     *
     * @param g Um parâmetro
     */
    public void desenharTriangulo(Graphics g)
    {
        double p1X = p1.getX();
        double p1Y = p1.getY();
        double p2X = p2.getX();
        double p2Y = p2.getY();
        double p3X = p3.getX();
        double p3Y = p3.getY();

        FiguraRetas.desenharReta(g,(int)p1X,(int)p1Y,(int)p2X,(int)p2Y,"",getEsp(),getCorAtual());
        FiguraRetas.desenharReta(g,(int)p2X,(int)p2Y,(int)p3X,(int)p3Y,"",getEsp(),getCorAtual());
        FiguraRetas.desenharReta(g,(int)p1X,(int)p1Y,(int)p3X,(int)p3Y,"",getEsp(),getCorAtual()); 
    }

     
}
