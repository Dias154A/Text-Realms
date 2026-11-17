package jogo;

import java.util.Scanner;

public class Main {
    // Códigos ANSI para colorir o texto no terminal
    private static final String RESET = "\u001B[0m";
    private static final String AMARELO = "\u001B[33m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Loja loja = new Loja();

        System.out.println("=================================================================");
        System.out.println("                    BEM-VINDO AO TEXT REALMS                     ");
        System.out.println("=================================================================");
        
        // --- 1. RESUMO COMPLETO DA HISTÓRIA (INTRODUÇÃO) ---
        System.out.println("\n📜 A HISTÓRIA ATÉ AQUI:");
        System.out.println("   Há séculos, o equilíbrio de Text Realms era mantido pelas runas contidas");
        System.out.println("   nas profundezas das Minas Eternas de Grimgar. Porém, uma força oculta");
        System.out.println("   corrompeu os antigos golens de pedra e atraiu hordas de monstros.");
        System.out.println("   Cidades queimam, o pânico se espalha e Grimgar clama por um campeão.");
        System.out.println("   Sua missão é cruzar as terras devastadas, fortalecer-se e purificar");
        System.out.println("   o coração da montanha antes que toda a existência vire cinzas.");
        
        // --- 2. LISTA DE COMANDOS ---
        System.out.println("\n🎮 GUIA DE COMANDOS DO JOGO:");
        System.out.println("   • [Em Combate]:");
        System.out.println("     - '1. Ataque Físico': Causa dano com base na sua Força. Pode desferir Críticos.");
        System.out.println("     - '2. Raio Arcano': Gasta 15 de Mana para disparar um feitiço mágico poderoso.");
        System.out.println("     - '3. Abrir Mochila': Permite usar poções de cura/mana sem perder o turno ao olhar.");
        System.out.println("   • [No Acampamento]:");
        System.out.println("     - Permite gerenciar seus equipamentos, checar atributos e visitar o Mercador.");
        System.out.println("\nPressione ENTER para iniciar sua jornada e criar seu personagem...");
        scanner.nextLine();

        // --- Criação do Herói ---
        System.out.print("Insira o nome do seu Herói: ");
        String nomeHeroi = scanner.nextLine();
        Heroi heroi = new Heroi(nomeHeroi, new Atributos(9,7, 9, 6));

        System.out.println("\n📜 HISTÓRIA INICIAL:");
        System.out.println("Você acorda nos arredores do Reino de Grimgar. O céu está coberto de fumaça.");
        System.out.println("Um guarda ferido aponta para o norte: 'Os monstros invadiram as minas eternas...'");
        System.out.println("Você empunha sua espada quebrada e ruma ao norte sem hesitar.");
        
        // --- CAPÍTULO 1 ---
        System.out.println("\n[CAPÍTULO 1: O Cruzamento das Sombras]");
        System.out.println("No caminho para as minas, você encontra uma bifurcação. O que escolhe?");
        System.out.println("1. Cortar caminho pela Floresta de Espinhos (Risco de emboscada rápida)");
        System.out.println("2. Seguir pela Estrada Real Abandonada (Caminho longo, mas com vestígios de saques)");
        System.out.print("Sua decisão: ");
        int decisao1 = scanner.nextInt();

        if (decisao1 == 1) {
            System.out.println("\n🌲 Um Goblin salta das árvores gritando loucamente!");
            Inimigo goblin = new Inimigo("Goblin Emboscador", new Atributos(4, 7, 5, 1), 60);
            Batalha b1 = new Batalha(heroi, goblin);
            if (!b1.iniciar()) return;
            
            System.out.println("🎁 O Goblin carregava moedas roubadas dos viajantes!");
            heroi.adicionarOuro(30); 
        } else {
            System.out.println("\n🛣️ Você explora uma carroça quebrada e encontra suprimentos, mas um Orc vigiava o local!");
            Inimigo orc = new Inimigo("Orc Vigia", new Atributos(8, 2, 9, 1), 70);
            Batalha b1 = new Batalha(heroi, orc);
            if (!b1.iniciar()) return;
            
            System.out.println("🎁 Vasculhando os restos da carroça você encontra uma bolsa de ouro esquecida!");
            heroi.adicionarOuro(45);
        }

        // Acampamento intermediário antes do chefe
        menuGerenciamento(heroi, loja, scanner);

        // --- CAPÍTULO 2 ---
        System.out.println("\n[CAPÍTULO 2: Os Portões da Mina]");
        System.out.println("Você finalmente chega à entrada das Minas de Grimgar.");
        System.out.println("O portão está bloqueado por runas mágicas instáveis vigiadas por uma aberração.");
        
        // Mensagem de Alerta de Perigo em Amarelo
        System.out.println("\n" + AMARELO + "⚠️  AVISO DE PERIGO IMINENTE! ⚠️" + RESET);
        System.out.println(AMARELO + "Uma energia avassaladora emana do portão. Certifique-se de ter" + RESET);
        System.out.println(AMARELO + "equipado suas melhores armas e comprado poções antes de avançar!" + RESET);
        
        System.out.print("\nPressione ENTER quando estiver pronto para o confronto final...");
        scanner.nextLine(); // Limpa o buffer do teclado
        scanner.nextLine(); // Aguarda o enter real do jogador

        Inimigo chefe = new Inimigo("Golem Corrompido (CHEFE)", new Atributos(12, 1, 14, 5), 150);
        Batalha bFinal = new Batalha(heroi, chefe);
        
        if (bFinal.iniciar()) {
            System.out.println("\n=======================================================");
            System.out.println("🎉 PARABÉNS! As runas colapsam e o mal foi expurgado!");
            System.out.println("Você salvou as Minas de Grimgar e se tornou uma Lenda em Text Realms!");
            System.out.println("=======================================================");
        }
        
        scanner.close();
    }

    private static void menuGerenciamento(Heroi heroi, Loja loja, Scanner sc) {
        boolean organizando = true;
        while (organizando) {
            System.out.println("\n⛺ [PAUSA NA JORNADA] O que deseja fazer no acampamento?");
            System.out.println("1. Olhar Status do Herói 📊");
            System.out.println("2. Abrir Inventário / Equipar Itens 🎒");
            System.out.println("3. Visitar o Mercador Viajante 🛒");
            System.out.println("4. Avançar para o Desafio Final 🚀");
            System.out.print("Escolha: ");
            int op = sc.nextInt();
            
            switch (op) {
                case 1:
                    System.out.println("\n📊 --- STATUS ---");
                    System.out.println("Nome: " + heroi.getNome());
                    System.out.println("Nível: " + heroi.getNivel());
                    System.out.println("Vida: " + heroi.getVidaAtual() + "/" + heroi.getAtributos().getVidaMaxima());
                    System.out.println("Mana: " + heroi.getManaAtual() + "/" + heroi.getAtributos().getManaMaxima());
                    System.out.println("Ouro: 🪙 " + heroi.getOuro() + " moedas");
                    break;
                case 2:
                    heroi.exibirInventario();
                    System.out.print("Escolha o número do item para usar/equipar (ou 0 para voltar): ");
                    int itemOp = sc.nextInt();
                    if (itemOp > 0) heroi.usarItemDoInventario(itemOp - 1);
                    break;
                case 3:
                    loja.abrirMenuLoja(heroi, sc);
                    break;
                default:
                    organizando = false;
                    break;
            }
        }
    }
}


