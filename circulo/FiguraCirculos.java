package circulo;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Escreva uma descrição da classe FiguraRetangulos aqui.
 * 
 * @author João Pedro de Souza Oliveira,Beatriz Lopes Rizzo,Guilherme Diniz Leocadio,Lucas Lombardi de Brito
 * @version (um número da versão ou uma data)
 */
public class FiguraCirculos
{
    public static void desenharCirculo(Graphics g, int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
        CirculoGr r = new CirculoGr(x1, y1, x2, y2, cor, nome, esp);
        r.desenharCirculo(g);
    }
}
