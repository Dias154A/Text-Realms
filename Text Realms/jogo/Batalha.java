package jogo;

import java.util.Scanner;

public class Batalha {
    private Heroi heroi;
    private Inimigo inimigo;
    private Scanner scanner;

    // Códigos ANSI para colorir o texto no terminal
    private static final String RESET = "\u001B[0m";
    private static final String VERMELHO = "\u001B[31m";

    public Batalha(Heroi heroi, Inimigo inimigo) {
        this.heroi = heroi;
        this.inimigo = inimigo;
        this.scanner = new Scanner(System.in);
    }

    public boolean iniciar() {
        System.out.println("\n⚔️ COMBATE: " + heroi.getNome() + " VS " + inimigo.getNome());

        while (heroi.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\n[Sua Vida: " + heroi.getVidaAtual() + " | Mana: " + heroi.getManaAtual() + "]");
            System.out.println("[Vida do Inimigo: " + inimigo.getVidaAtual() + "]");
            System.out.println("1. Ataque Físico ⚔️");
            System.out.println("2. Raio Arcano (15 Mana) ⚡");
            System.out.println("3. Abrir Mochila 🎒");
            System.out.print("Escolha: ");
            
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                inimigo.receberDano(heroi.atacar());
            } else if (escolha == 2) {
                heroi.usarHabilidadeEspecial(inimigo);
            } else if (escolha == 3) {
                heroi.exibirInventario();
                System.out.print("Selecione o número do item para usar (ou 0 para voltar): ");
                int itemEsc = scanner.nextInt();
                if (itemEsc > 0) {
                    heroi.usarItemDoInventario(itemEsc - 1);
                } else {
                    continue; 
                }
            } else {
                System.out.println("Comando confuso! Você perdeu o tempo de reação.");
            }

            if (!inimigo.estaVivo()) {
                System.out.println("\n🏆 Vitória! " + inimigo.getNome() + " caiu.");
                heroi.ganharExperiencia(inimigo.getXpRecompensa());
                return true;
            }

            // Turno do Inimigo
            System.out.println("\n--- Turno de " + inimigo.getNome() + " ---");
            heroi.receberDano(inimigo.atacar());

            if (!heroi.estaVivo()) {
                // Mensagem de morte estilizada em Vermelho Negrito
                System.out.println("\n" + VERMELHO + "☠️======================================================☠️" + RESET);
                System.out.println(VERMELHO + "             VOCÊ MORREU! FIM DE JOGO...                  " + RESET);
                System.out.println(VERMELHO + "   Sua jornada terminou nas profundezas de Grimgar.      " + RESET);
                System.out.println(VERMELHO + "☠️======================================================☠️" + RESET);
                return false;
            }
        }
        return false;
    }
}
