package triangulo;

import ponto.Ponto;
import reta.Reta;
/**
 * Escreva uma descrição da classe Triangulo aqui.
 * 
 * @author João Pedro de Souza Oliveira,Beatriz Lopes Rizzo,Guilherme Diniz Leocadio,Lucas Lombardi de Brito
 * @version (um número da versão ou uma data)
 */
public class Triangulo
{
    public Ponto p1, p2, p3;
    public Ponto baricentro;
    public Reta r1,r2,r3;
    public Triangulo(int x1, int y1, int x2, int y2, int x3, int y3) {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        setP3(new Ponto(x3, y3));
        baricentro = new Ponto((x1+x2+x3)/3,(y1+y2+y3)/3);
        setR1(new Reta(p1,p2));
        setR2(new Reta(p1,p3));
        setR3(new Reta(p2,p3));
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
     * Altera valor de p3 de acordo com o parametro
     *
     * @param p valor de p3 (externo)
     */
    public void setP3(Ponto p)
    {
        this.p3 = p;
    }
    
    /**
     * Altera valor de r1 de acordo com o parametro
     *
     * @param p valor de r1 (externo)
     */
    public void setR1(Reta r)
    {
        this.r1 = r;
    }
    
    /**
     * Altera valor de r2 de acordo com o parametro
     *
     * @param p valor de r2 (externo)
     */
    public void setR2(Reta r)
    {
        this.r2 = r;
    }
    
    /**
     * Altera valor de r3 de acordo com o parametro
     *
     * @param p valor de r3 (externo)
     */
    public void setR3(Reta r)
    {
        this.r3 = r;
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
    
    /**
     * Retorna p3
     *
     * @return valor de p3
     */
    public Ponto getP3(){
        return this.p3;
    }
    
    public String toString(){
        String s = "P1: " + getP1().toString() + " P2: " + getP2().toString() + "P3: " + getP3().toString();
        return s;
    }
}
