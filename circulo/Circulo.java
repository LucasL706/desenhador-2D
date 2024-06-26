package circulo;
import ponto.Ponto;
public class Circulo{
 
    // Atributos da reta
    public Ponto p1, p2;
    double raio;

    /**
     * Constroi uma reta com valores (int) de x1, y1 e x2, y2
     *
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     */
    public Circulo(int x1, int y1, int x2, int y2) {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        setRaio(p1,p2);
    }
    
    /**
     * Constroi uma reta com valores (double) de x1, y1 e x2, y2
     *
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     */
    public Circulo(double x1, double y1, double x2, double y2) {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        setRaio(p1,p2);
    }
    
    private void setRaio(Ponto p1, Ponto p2){
        this.raio = Math.sqrt(Math.pow(p2.getX()-p1.getX(),2)+Math.pow(p2.getY()-p1.getY(),2));
    }
    public double getRaio(){
        return this.raio;
    }
    
    /**
     * Controi uma reta com valores de p1 e p2 (externos)
     *
     * @param p1 Um par�metro
     * @param p2 Um par�metro
     */
    public Circulo(Ponto p1, Ponto p2) {
        setP1(p1);
        setP2(p2);
        setRaio(p1,p2);
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
    
    
    /**
     * Imprime a equacao de reta no formato (y = mx + b)
     *
     * @return string que representa a reta
     */
    public String toString(){
        String s = "P1: " + getP1().toString() + " P2: " + getP2().toString();
        return s;
    }
   
}
