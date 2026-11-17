package jogo;

public abstract class Personagem {
    private String nome;
    private int vidaAtual;
    private Atributos atributos;

    public Personagem(String nome, Atributos atributos) {
        this.nome = nome;
        this.atributos = atributos;
        this.vidaAtual = atributos.getVidaMaxima();
    }

    public boolean estaVivo() { return this.vidaAtual > 0; }

    public void receberDano(int dano) {
        int chanceEsquiva = atributos.getChanceEsquiva();
        if (Math.random() * 100 < chanceEsquiva) {
            System.out.println("💨 " + nome + " esquivou habilidosamente!");
            return;
        }

        this.vidaAtual -= dano;
        if (this.vidaAtual < 0) this.vidaAtual = 0;
        System.out.println("💥 " + nome + " recebeu " + dano + " de dano! (Vida: " + this.vidaAtual + "/" + atributos.getVidaMaxima() + ")");
    }

    public int atacar() {
        int dano = atributos.getDanoBase();
        if (Math.random() * 100 < 15) {
            System.out.println("🔥 ACERTO CRÍTICO!");
            dano *= 2;
        }
        return dano;
    }

    public String getNome() { return nome; }
    public int getVidaAtual() { return vidaAtual; }
    public void setVidaAtual(int vidaAtual) { this.vidaAtual = vidaAtual; }
    public Atributos getAtributos() { return atributos; }
}

