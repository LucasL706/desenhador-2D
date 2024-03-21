package figura;

import ponto.Ponto;
/**
 * Classe que possui os dois pontos principais da figura
 * 
 * @author João Pedro de Souza Oliveira,Beatriz Lopes Rizzo,Guilherme Diniz Leocadio,Lucas Lombardi de Brito
 * @version 20220905
 */
public class Figura
{
    public Ponto p1,p2;
    /**
     * Construtor para objetos da classe Figura
     */
    public Figura(int x1, int y1, int x2, int y2)
    {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
    }
    
    /**
     * Altera valor de p1 de acordo com o parametro
     *
     * @param p valor de p1 (externo)
     */
    public void setP1(Ponto p){
        this.p1 = p;
    }
    
    /**
     * Altera valor de p2 de acordo com o parametro
     *
     * @param p valor de p2 (externo)
     */
    public void setP2(Ponto p){
        this.p2 = p;
    }
    
    /**
     * Retorna valor de p1
     *
     * @return valor de p1
     */
    public Ponto getP1(){
        return this.p1;
    }

    /**
     * Retorna p2
     *
     * @return valor de p2
     */
    public Ponto getP2(){
        return this.p2;
    }
    
    public String toString(){
        String s = "P1: " + getP1().toString() + " P2: " + getP2().toString();
        return s;
    }
}
