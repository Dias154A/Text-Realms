package jogo;

public class Atributos {
    private int forca;
    private int agilidade;
    private int vitalidade;
    private int inteligencia;

    // Bônus vindos de equipamentos equipados
    private int bonusForca;
    private int bonusAgilidade;
    private int bonusVitalidade;

    public Atributos(int forca, int agilidade, int vitalidade, int inteligencia) {
        this.forca = forca;
        this.agilidade = agilidade;
        this.vitalidade = vitalidade;
        this.inteligencia = inteligencia;
    }

    public int getDanoBase() {
        return (this.forca + bonusForca) * 3;
    }

    public int getDanoMagico() {
        return this.inteligencia * 4;
    }

    public int getChanceEsquiva() {
        return Math.min((this.agilidade + bonusAgilidade) * 2, 50);
    }

    public int getVidaMaxima() {
        return (this.vitalidade + bonusVitalidade) * 15;
    }

    public int getManaMaxima() {
        return this.inteligencia * 10;
    }

    // Métodos para aplicar/remover bônus de itens
    public void aplicarBonus(int f, int a, int v) {
        this.bonusForca += f;
        this.bonusAgilidade += a;
        this.bonusVitalidade += v;
    }

    public void limparBonus() {
        this.bonusForca = 0;
        this.bonusAgilidade = 0;
        this.bonusVitalidade = 0;
    }

    // Getters e Setters
    public int getForca() { return forca; }
    public void setForca(int forca) { this.forca = forca; }
    public int getAgilidade() { return agilidade; }
    public void setAgilidade(int agilidade) { this.agilidade = agilidade; }
    public int getVitalidade() { return vitalidade; }
    public void setVitalidade(int vitalidade) { this.vitalidade = vitalidade; }
    public int getInteligencia() { return inteligencia; }
    public void setInteligencia(int inteligencia) { this.inteligencia = inteligencia; }
}
