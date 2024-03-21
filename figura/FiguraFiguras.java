package figura;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Chama a funcao de desenhar a Figura
 * 
 * @author João Pedro de Souza Oliveira,Beatriz Lopes Rizzo,Guilherme Diniz Leocadio,Lucas Lombardi de Brito
 * @version 20220905
 */
public class FiguraFiguras
{
    public static void desenharFigura(Graphics g, int x1, int y1, int x2, int y2, String nome, int diametro, Color cor1, Color cor2){
        // Color cor = new Color((int) (Math.random() * 256),  
        // (int) (Math.random() * 256),  
        // (int) (Math.random() * 256));
        FiguraGr p = new FiguraGr(x1, y1, x2, y2, cor1, cor2, nome, diametro);
        p.desenharFigura(g);
    }
}
