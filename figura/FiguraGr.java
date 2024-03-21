package figura;
import reta.FiguraRetas;
import ponto.Ponto;
import ponto.FiguraPontos;
import circulo.FiguraCirculos;
import circulo.Circulo;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 * Classe que possui elementos graficos da figura como cor e espessura
 * 
 @author João Pedro de Souza Oliveira,Beatriz Lopes Rizzo,Guilherme Diniz Leocadio,Lucas Lombardi de Brito
 * @version 20220905
 */
public class FiguraGr extends Figura
{
    // Atributos da Figura grafica
    Color corFigura = Color.BLACK;   // cor da Figura
    Color cor1 = Color.BLACK; //cor dos circulos
    Color cor2 = Color.BLACK; //cor das retas
    String nomeFigura = ""; // nome da Figura
    Color corNomeFigura  = Color.BLACK;
    int espFigura = 1; // espessura da Figura
    int x1, y1, x2, y2;
    // Construtores
    /**
     * FiguraGr - Constroi uma Figura grafica
     *
     * @param x1 int. Coordenada x1
     * @param y1 int. Coordenada y1
     * @param x2 int. Coordenada x2
     * @param y2 int. Coordenada y2
     * @param cor1 Color. Cor dos circulos
     * @param cor2 Color. Cor das retas
     * @param nome String. Nome da Figura
     * @param esp int. Espessura da Figura
     */
    public FiguraGr(int x1, int y1, int x2, int y2, Color cor1, Color cor2, String nome, int esp){
        super(x1, y1, x2, y2);
        setCor1(cor1);
        setCor2(cor2);
        setNomeFigura(nome);
        setEspFigura(esp);
    }

    /**
     * Altera a cor da Figura.
     *
     * @param cor Color. Cor da Figura.
     */
    public void setCorFigura(Color cor) {
        this.corFigura = cor;
    }
    
    /**
     * Altera a cor dos Circulos.
     *
     * @param cor Color. Cor1.
     */
    public void setCor1(Color cor) {
        this.cor1 = cor;
    }
    
    /**
     * Altera a cor das retas.
     *
     * @param cor Color. Cor2.
     */
    public void setCor2(Color cor) {
        this.cor2 = cor;
    }

    /**
     * Altera o nome da Figura.
     *
     * @param str String. Nome da Figura.
     */
    public void setNomeFigura(String str) {
        this.nomeFigura = str;
    }

    public void setEspFigura(int esp) {
        this.espFigura = esp;
    }

    /**
     * Altera a espessura da Figura.
     *
     * @param esp int. Espessura da Figura.
     */
    public void setEsp(int esp) {
        this.espFigura = esp;
    }

    /**
     * Retorna a espessura da Figura.
     *
     * @return int. Espessura da Figura.
     */
    public int getEsp() {
        return(this.espFigura);
    }

    /**
     * Retorna a cor da Figura.
     *
     * @return Color. Cor da Figura.
     */
    public Color getCorAtual() {
        return corFigura;
    }

    /**
     * Retorna o nome da Figura.
     *
     * @return String. Nome da Figura.
     */
    public String getNomeFigura() {
        return nomeFigura;
    }

    /**
     * @return the cor1
     */
    public Color getCor1() {
        return cor1;
    }
    
    /**
     * @return the cor2
     */
    public Color getCor2() {
        return cor2;
    }
    
    /**
     * @param corNomeFigura the corNomeFigura to set
     */
    public void setCorNomeFigura(Color corNomeFigura) {
        this.corNomeFigura = corNomeFigura;
    }

    /**
     * Método desenharFigura - desenha figura a partir de dois pontos graficos
     *
     * @param g Um parâmetro grafico
     */
    public void desenharFigura(Graphics g)
    {
        Circulo circulo = new Circulo(p1,p2);
        Ponto[] circulos = new Ponto[6];
        Ponto[] interseccao = new Ponto[6];
        int cont = 0;
        int xNovo = 0;
        int yNovo = 0;
        FiguraCirculos.desenharCirculo(g, (int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY(), "", getEsp(), getCor1());
        for(int i = 0; i < 360; i++){
            if((i % 60) == 0){
                xNovo = (int)(p1.getX() + circulo.getRaio() * Math.cos(Math.toRadians(i)));
                yNovo =  (int)(p1.getY() + circulo.getRaio() * Math.sin(Math.toRadians(i)));
                FiguraCirculos.desenharCirculo(g, xNovo, yNovo, (int)(xNovo + circulo.getRaio()), yNovo, "", getEsp(), getCor1());
                circulos[cont] = new Ponto(xNovo, yNovo);
                interseccao[cont] = new Ponto ((int)(xNovo + circulo.getRaio()), yNovo);
                cont++;
            }
        }
       
        //diagonais do hexagono
        FiguraRetas.desenharReta(g, (int)p1.getX(), (int)p1.getY(), (int)circulos[0].getX(), (int)circulos[0].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)p1.getX(), (int)p1.getY(), (int)circulos[1].getX(), (int)circulos[1].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)p1.getX(), (int)p1.getY(), (int)circulos[2].getX(), (int)circulos[2].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)p1.getX(), (int)p1.getY(), (int)circulos[3].getX(), (int)circulos[3].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)p1.getX(), (int)p1.getY(), (int)circulos[4].getX(), (int)circulos[4].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)p1.getX(), (int)p1.getY(), (int)circulos[5].getX(), (int)circulos[5].getY(), "", getEsp(), getCor2());
        //hexagono
        FiguraRetas.desenharReta(g, (int)circulos[0].getX(), (int)circulos[0].getY(), (int)circulos[1].getX(), (int)circulos[1].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)circulos[1].getX(), (int)circulos[1].getY(), (int)circulos[2].getX(), (int)circulos[2].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)circulos[2].getX(), (int)circulos[2].getY(), (int)circulos[3].getX(), (int)circulos[3].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)circulos[3].getX(), (int)circulos[3].getY(), (int)circulos[4].getX(), (int)circulos[4].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)circulos[4].getX(), (int)circulos[4].getY(), (int)circulos[5].getX(), (int)circulos[5].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)circulos[5].getX(), (int)circulos[5].getY(), (int)circulos[0].getX(), (int)circulos[0].getY(), "", getEsp(), getCor2());
        
        //pontos das interseccoes externas
        xNovo = (int)(circulos[1].getX() + circulo.getRaio() * Math.cos(Math.toRadians(120)));
        yNovo = (int)(circulos[1].getY() + circulo.getRaio() * Math.sin(Math.toRadians(120)));
        interseccao[0] = new Ponto(xNovo,yNovo);
        xNovo = (int)(circulos[2].getX() + circulo.getRaio() * Math.cos(Math.toRadians(180)));
        yNovo = (int)(circulos[2].getY() + circulo.getRaio() * Math.sin(Math.toRadians(180)));
        interseccao[2] = new Ponto(xNovo,yNovo);
        xNovo = (int)(circulos[3].getX() + circulo.getRaio() * Math.cos(Math.toRadians(240)));
        yNovo = (int)(circulos[3].getY() + circulo.getRaio() * Math.sin(Math.toRadians(240)));
        interseccao[3] = new Ponto(xNovo,yNovo);
        xNovo = (int)(circulos[5].getX() + circulo.getRaio() * Math.cos(Math.toRadians(240)));
        yNovo = (int)(circulos[5].getY() + circulo.getRaio() * Math.sin(Math.toRadians(240)));
        interseccao[4] = new Ponto(xNovo,yNovo);
        /*
         *  0 - baixo
         *  1 - baixo direito
         *  2 - baixo esquerda
         *  3 - cima esquerda
         *  4 - cima
         *  5 - cima direita
         */
        
        //retas entre os pontos das interseccoes externas
        FiguraRetas.desenharReta(g, (int)interseccao[5].getX(), (int)interseccao[5].getY(), (int)interseccao[0].getX(), (int)interseccao[0].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)interseccao[3].getX(), (int)interseccao[3].getY(), (int)interseccao[0].getX(), (int)interseccao[0].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)interseccao[3].getX(), (int)interseccao[3].getY(), (int)interseccao[5].getX(), (int)interseccao[5].getY(), "", getEsp(), getCor2());      
        FiguraRetas.desenharReta(g, (int)interseccao[2].getX(), (int)interseccao[2].getY(), (int)interseccao[4].getX(), (int)interseccao[4].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)interseccao[2].getX(), (int)interseccao[2].getY(), (int)interseccao[1].getX(), (int)interseccao[1].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)interseccao[1].getX(), (int)interseccao[1].getY(), (int)interseccao[4].getX(), (int)interseccao[4].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)p1.getX(), (int)p1.getY(), (int)interseccao[5].getX(), (int)interseccao[5].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)p1.getX(), (int)p1.getY(), (int)interseccao[1].getX(), (int)interseccao[1].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)interseccao[1].getX(), (int)interseccao[1].getY(), (int)interseccao[5].getX(), (int)interseccao[5].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)p1.getX(), (int)p1.getY(), (int)interseccao[3].getX(), (int)interseccao[3].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)p1.getX(), (int)p1.getY(), (int)interseccao[4].getX(), (int)interseccao[4].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)interseccao[4].getX(), (int)interseccao[4].getY(), (int)interseccao[5].getX(), (int)interseccao[5].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)p1.getX(), (int)p1.getY(), (int)interseccao[2].getX(), (int)interseccao[2].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)p1.getX(), (int)p1.getY(), (int)interseccao[0].getX(), (int)interseccao[0].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)interseccao[4].getX(), (int)interseccao[4].getY(), (int)interseccao[3].getX(), (int)interseccao[3].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)interseccao[2].getX(), (int)interseccao[2].getY(), (int)interseccao[3].getX(), (int)interseccao[3].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)interseccao[2].getX(), (int)interseccao[2].getY(), (int)interseccao[0].getX(), (int)interseccao[0].getY(), "", getEsp(), getCor2());
        FiguraRetas.desenharReta(g, (int)interseccao[0].getX(), (int)interseccao[0].getY(), (int)interseccao[1].getX(), (int)interseccao[1].getY(), "", getEsp(), getCor2());
    }
}
