package jogo;

public class Inimigo extends Personagem {
    private int xpRecompensa;

    public Inimigo(String nome, Atributos atributos, int xpRecompensa) {
        super(nome, atributos);
        this.xpRecompensa = xpRecompensa;
    }

    public int getXpRecompensa() { return xpRecompensa; }
}
