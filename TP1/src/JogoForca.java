import java.util.Scanner;
import java.io.IOException;
import java.util.Scanner;

public class JogoForca {

	public static int MAX = 52;
	public static String[][] matrizGeral = new String[MAX][MAX + 1];

	public static void main(String[] args){
		int escolha = 0;
		int repeat = 0;

		Scanner ler = new Scanner(System.in);

		do {
			Menu();

			do {
				System.out.print(" Escolha uma opção(1-4): ");
				escolha = ler.nextInt();
				System.out.print("\n");

				switch (escolha) {
				case 1:
					Opcao1();
					repeat = 0;
					break;
				case 2:
					Opcao2();
					repeat = 0;
					break;
				case 3:
					Opcao3();
					repeat = 0;
					break;
				case 4:
					repeat = 0;
					break;
				case 10: {
					for (int a = 0; a < MAX - 1; a++) {
						System.out.print("\n" + " " + (a + 1) + "- " + matrizGeral[a][0] + "| ");
						for (int j = 1; j < MAX; j++)
							System.out.print(" " + (j) + "- " + matrizGeral[a][j] + " / ");
					}
					repeat = 0;
				}
					break;
				default:
					System.out.print(" Favor inserir uma opção entre 1 e 4!\n\n");
					repeat = 1;
				}
			} while (repeat == 1);
			LimpaConsole();
		} while (escolha != 4);
	}

	public static void Menu() {
		System.out.print("=================================================\n\n");
		System.out.print("                 JOGO DA FORCA\n");
		System.out.print("-------------------------------------------------\n\n");
		System.out.print("  1- Gerenciar Temas\n");
		System.out.print("  2- Gerenciar Palavras\n");
		System.out.print("  3- Jogar\n");
		System.out.print("  4- Sair\n\n");
		System.out.print("-------------------------------------------------\n\n");
	}

	public static int BancoDados(int escolha, int cont, String tema, String palavra) {
		cont = cont - 1;

		int validador = 1;

		switch (escolha) {
// ---------------------------------------------------------------------
		case 1: { // Criar novo tema
			for (int a = 0; a < MAX - 1; a++) {
				if (matrizGeral[a][0] != null) {
					if (matrizGeral[a][0].equals(tema) == true)
						return 1; // Tema ja existe
				} else if (validador == 1) {
					matrizGeral[a][0] = tema; 
					validador = 0;
				} else if (a == MAX - 2 && validador == 1) {
					return 2; // Limite de temas excedido
				}
			}
		}
			break;
// ---------------------------------------------------------------------
		case 2: { // Criar nova palavra
			for (int j = 1; j < MAX - 1; j++) {
				if (matrizGeral[cont][j] != null) {
					if (matrizGeral[cont][j].equals(palavra) == true)
						return 1; // Palavra ja existe
				} else if (validador == 1) {
					matrizGeral[cont][j] = palavra; 
					validador = 0;
				} else if (j == MAX - 2 && validador == 1) {
					return 2; // Limite de palavras excedido
				}
			}
		}
			break;

// ---------------------------------------------------------------------
		case 3: { // Print dos temas disponiveis
			for (int a = 0; a < MAX - 1; a++) {
				if (matrizGeral[a][0] != null)
					System.out.print(" " + (a + 1) + "- " + matrizGeral[a][0] + "\n");
				else {
					if (a == 0)
						return 3; // Nenhum tema cadastrado
				}
			}
		}
			break;

		case 4: { // Exclusão do tema escolhido
			if (matrizGeral[cont][1] == null) {
				matrizGeral[cont][0] = null;
			} else {
				return 4; // Existem palavras cadastradas nesse tema
			}
			
			for (int a = cont; a < MAX - 2; a++) {
				for (int j = 0; j < MAX - 1; j++) {
					matrizGeral[a][j] = matrizGeral[a + 1][j];
				}
			}
		}
			break;
		}

// --------------------------- Imprime as informações da matriz ------------------
		/*
		 * for (int a = 0; a < MAX - 1; a++) { System.out.print("\n" + " " + (a + 1) +
		 * "- " + matrizGeral[a][0] + "| "); for (int j = 1; j < MAX; j++)
		 * System.out.print(" " + (j) + "- " + matrizGeral[a][j] + " / "); }
		 * System.out.print("\n\n");
		 */
		return 0; // Tudo ocorreu perfeitamente!
	}

	public static int Opcao1() {
		int repeat = 0;
		int escolha;

		Scanner ler = new Scanner(System.in);

		System.out.print("=================================================\n\n");
		System.out.print("                 GERENCIAR TEMAS\n");
		System.out.print("-------------------------------------------------\n\n");
		System.out.print("  1- Cadastrar\n");
		System.out.print("  2- Excluir\n");
		System.out.print("  3- Buscar\n");
		System.out.print("  4- Voltar ao Menu\n\n");
		System.out.print("-------------------------------------------------\n\n");

		do {
			System.out.print(" Escolha uma opção(1-4): ");
			escolha = ler.nextInt();
			System.out.print("\n");

			switch (escolha) {
			case 1:
				Opcao11();
				repeat = 0;
				break;
			case 2:
				Opcao12();
				repeat = 0;
				break;
			case 3:
				Opcao13();
				repeat = 0;
				break;
			case 4:
				return 0;
			default:
				System.out.print(" Favor inserir uma opção entre 1 e 4!\n\n");
				repeat = 1;
			}
		} while (repeat == 1);

		return 0;
	}

	public static void Opcao11() {

		Scanner ler = new Scanner(System.in);
		String tema;

		System.out.print("=================================================\n\n");
		System.out.print("                  CADASTRAR TEMA\n");
		System.out.print("-------------------------------------------------\n\n");
		System.out.print("  Digite o tema que deseja cadastrar: ");
		tema = ler.next();
		System.out.print("\n");

		switch (BancoDados(1, 0, tema, null)) {
		case 0:
			System.out.print("  Tema cadastrado com sucesso!\n ");
			break;
		case 1:
			System.out.print("  Tema ja existente! \n");
			break;
		case 2:
			System.out.print("  Capacidade maxima de temas excedente, operação não realizada! \n");
			break;
		}

	}

	public static void Opcao12() {
		Scanner ler = new Scanner(System.in);
		String palavra;
		int escolha;

		System.out.print("=================================================\n\n");
		System.out.print("               EXCLUIR TEMA\n");
		System.out.print("-------------------------------------------------\n\n");
		switch (BancoDados(3, 0, null, null)) {
		case 0: {
			System.out.print("-------------------------------------------------\n\n");
			System.out.print("  Escolha o tema que deseja excluir: ");
			escolha = ler.nextInt();
			System.out.print("\n");

			switch (BancoDados(4, escolha, null, null)) {
			case 0:
				System.out.print("  Tema excluido com sucesso!\n ");
				break;
			case 4:
				System.out.print("  Existem palavras cadastradas nesse tema!\n ");
				break;
			}
		}
			break;
		case 3:
			System.out.print("  Não existem temas cadastrados!\n\n ");
			break;
		}
	}

	public static void Opcao13() {

	}

	public static int Opcao2() {
		int repeat = 0;
		int escolha;

		Scanner ler = new Scanner(System.in);

		System.out.print("=================================================\n\n");
		System.out.print("               GERENCIAR PALAVRAS\n");
		System.out.print("-------------------------------------------------\n\n");
		System.out.print("  1- Cadastrar\n");
		System.out.print("  2- Excluir\n");
		System.out.print("  3- Buscar\n");
		System.out.print("  4- Voltar ao Menu\n\n");
		System.out.print("-------------------------------------------------\n\n");

		do {
			System.out.print(" Escolha uma opção(1-4): ");
			escolha = ler.nextInt();
			System.out.print("\n");

			switch (escolha) {
			case 1:
				Opcao21();
				repeat = 0;
				break;
			case 2:
				Opcao12();
				repeat = 0;
				break;
			case 3:
				Opcao13();
				repeat = 0;
				break;
			case 4:
				return 0;
			default:
				System.out.print(" Favor inserir uma opção entre 1 e 4!\n\n");
				repeat = 1;
			}
		} while (repeat == 1);

		return 0;
	}

	public static void Opcao21() {

		Scanner ler = new Scanner(System.in);
		String palavra;
		int escolha;

		System.out.print("=================================================\n\n");
		System.out.print("               CADASTRAR PALAVRA\n");
		System.out.print("-------------------------------------------------\n\n");
		switch (BancoDados(3, 0, null, null)) {
		case 0: {
			System.out.print("-------------------------------------------------\n\n");
			System.out.print("  Escolha o tema que deseja cadastrar a palavra: ");
			escolha = ler.nextInt();
			System.out.print("\n");

			System.out.print("  Digite a palavra que deseja cadastrar: ");
			palavra = ler.next();
			System.out.print("\n");

			switch (BancoDados(2, escolha, null, palavra)) {
			case 0:
				System.out.print("  Palavra cadastrado com sucesso!\n ");
				break;
			case 1:
				System.out.print("  Palavra ja existente!\n ");
				break;
			case 2:
				System.out.print("  Capacidade maxima de palavras excedente, operação não realizada! ");
				break;
			}
		}
			break;
		case 3:
			System.out.print("  Não existem temas cadastrados!\n\n ");
			break;
		}

	}

	public static void Opcao3() {

	}

	public static void LimpaConsole(){
		System.out.print("\n\n");
		System.out.print("  ___________");
		System.out.print(" /          |\n");
		System.out.print(" |          |\n");
		System.out.print(" |          O\n");
		System.out.print(" |         /|\\\n");
		System.out.print(" |         / \\\n");
		System.out.print(" |\n");
		System.out.print(" |\n");
		System.out.print("(_) ..:..:.:..:.::..:...:.:.::..:\n");
		System.out.print("================================================= \n\n");
	}
}
