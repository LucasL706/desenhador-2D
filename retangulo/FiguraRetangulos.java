package retangulo;
import java.awt.Graphics;
import java.awt.Color;


/**
 * Escreva uma descri��o da classe FiguraRetangulos aqui.
 * 
 * @author Jo�o Pedro de Souza Oliveira,Beatriz Lopes Rizzo,Guilherme Diniz Leocadio,Lucas Lombardi de Brito
 * @version (um n�mero da vers�o ou uma data)
 */
public class FiguraRetangulos
{
    public static void desenharRetangulo(Graphics g, int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
        RetanguloGr r = new RetanguloGr(x1, y1, x2, y2, cor, nome, esp);
       r.desenharRetangulo(g);
    }
}
