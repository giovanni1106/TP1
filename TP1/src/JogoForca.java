import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class JogoForca {

	public static int MAX = 52;
	public static String[][] matrizGeral = new String[MAX][MAX + 1];

	public static void main(String[] args) {
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

	// REALIZA AS ALTERAÇÕES NA MATRIZ
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

// ---------------------------------------------------------------------
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

// ---------------------------------------------------------------------			
		case 5: { // Buscar tema
			for (int a = 0; a < MAX - 1; a++)
				if (matrizGeral[a][0] == null && validador == 1) {
					return 5; // Tema não encontrado
				} else if (validador == 1 && matrizGeral[a][0].equals(tema) == true) {
					System.out.print(" " + matrizGeral[a][0] + "| ");
					validador = 0;
					for (int j = 1; j < MAX - 1; j++)
						System.out.print(" " + (j) + "- " + matrizGeral[a][j] + " / ");
				}
		}
			break;

// ---------------------------------------------------------------------
		case 6: { // Excluir palavra
			for (int j = 1; j < MAX - 1; j++)
				if (matrizGeral[escolha][j] != null)
					if (matrizGeral[escolha][j].equals(palavra) == true)
						matrizGeral[escolha][j] = null;
		}
			break;

// ---------------------------------------------------------------------			
		case 7: { // Buscar palavra
			for (int a = 0; a < MAX - 1; a++)
				for (int j = 0; j < MAX - 1; j++)
					if (matrizGeral[a][j] != null) {
						if (a != 0 && matrizGeral[a][j].equals(palavra) == true) {
							System.out.print(
									" A palavra " + palavra + " está cadastrada no tema " + matrizGeral[a][0] + "! \n");
							validador = 0;
							return 0;
						}
					} else if (matrizGeral[a][0] == null)
						return 6; // Palavra não encontrada

		}
			break;

// ---------------------------------------------------------------------
		case 8: { // Escolher uma palavra aleatória
			Random gerador = new Random();
			int totalPalavras = 0;
			int escolhida = 0;

			for (int j = 1; j < MAX - 1; j++)
				if (matrizGeral[cont][j] != null)
					totalPalavras = totalPalavras + 1;

			if (totalPalavras != 0) {
				escolhida = gerador.nextInt(totalPalavras);
				return escolhida;
			} else {
				return -1; // Não existem palavras cadastradas nesse tema
			}
		}
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

	// GERENCIAR TEMAS
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

	// CADASTRAR TEMA
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

	// EXCLUIR TEMA
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

	// BUSCAR TEMA
	public static void Opcao13() {

		Scanner ler = new Scanner(System.in);
		String tema;
		int escolha = 0;

		while (escolha != 2) {
			int validador = 0;
			System.out.print("=================================================\n\n");
			System.out.print("                  BUSCAR TEMAS\n");
			System.out.print("-------------------------------------------------\n\n");
			System.out.print("  Digite o tema que deseja buscar: ");
			tema = ler.next();
			System.out.print("\n");

			switch (BancoDados(5, 0, tema, null)) {
			case 0:
				while (validador == 0) {
					System.out.print("\n  Deseja buscar novamente? \n\n ");
					System.out.print("  1- Sim \n ");
					System.out.print("  2- Não \n\n ");
					System.out.print("  R:  ");
					escolha = ler.nextInt();
					if (escolha != 1 && escolha != 2) {
						System.out.print("  Favor escolher entre as opções 1 e 2! \n");
					} else {
						validador = 1;
					}
				}
				break;
			case 5:
				System.out.print("  Tema não encontrado! \n");
				while (validador == 0) {
					System.out.print("\n  Deseja buscar novamente? \n\n ");
					System.out.print("  1- Sim \n ");
					System.out.print("  2- Não \n\n ");
					System.out.print("  R:  ");
					escolha = ler.nextInt();
					if (escolha != 1 && escolha != 2) {
						System.out.print("  Favor escolher entre as opções 1 e 2! \n");
					} else {
						validador = 1;
					}
				}
				break;
			}

			if (escolha != 1 && escolha != 2)
				System.out.print("  Favor escolher entre as opções 1 e 2! \n");
		}
	}

	// GERENCIAR PALAVRAS
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
				Opcao22();
				repeat = 0;
				break;
			case 3:
				Opcao23();
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

	// CADASTRAR PALAVRA
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

	// EXCLUIR PALAVRA
	public static void Opcao22() {

		Scanner ler = new Scanner(System.in);
		String palavra;
		int escolha;

		System.out.print("=================================================\n\n");
		System.out.print("                 EXCLUIR PALAVRA\n");
		System.out.print("-------------------------------------------------\n\n");
		switch (BancoDados(3, 0, null, null)) {
		case 0: {
			System.out.print("-------------------------------------------------\n\n");
			System.out.print("  Escolha o tema que deseja excluir a palavra: ");
			escolha = ler.nextInt();
			System.out.print("\n");
			BancoDados(5, 0, matrizGeral[escolha - 1][0], null);
			System.out.print("\n\n");
			System.out.print("  Digite por extenso a palavra que deseja excluir: ");
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

	// BUSCAR PALAVRA
	public static void Opcao23() {

		Scanner ler = new Scanner(System.in);
		String palavra;
		int escolha = 0;

		while (escolha != 2) {
			int validador = 0;
			System.out.print("=================================================\n\n");
			System.out.print("                  BUSCAR PALAVRAS\n");
			System.out.print("-------------------------------------------------\n\n");
			System.out.print("  Digite a palavra que deseja buscar: ");
			palavra = ler.next();
			System.out.print("\n");

			switch (BancoDados(7, 0, null, palavra)) {
			case 0:
				while (validador == 0) {
					System.out.print("\n  Deseja buscar novamente? \n\n ");
					System.out.print("  1- Sim \n ");
					System.out.print("  2- Não \n\n ");
					System.out.print("  R:  ");
					escolha = ler.nextInt();
					if (escolha != 1 && escolha != 2) {
						System.out.print("  Favor escolher entre as opções 1 e 2! \n");
					} else {
						validador = 1;
					}
				}
				break;
			case 6:
				System.out.print("  Palavra não encontrado! \n");
				while (validador == 0) {
					System.out.print("\n  Deseja buscar novamente? \n\n ");
					System.out.print("  1- Sim \n ");
					System.out.print("  2- Não \n\n ");
					System.out.print("  R:  ");
					escolha = ler.nextInt();
					if (escolha != 1 && escolha != 2) {
						System.out.print("  Favor escolher entre as opções 1 e 2! \n");
					} else {
						validador = 1;
					}
				}
				break;
			}

			if (escolha != 1 && escolha != 2)
				System.out.print("  Favor escolher entre as opções 1 e 2! \n");
		}
	}

	// JOGAR
	public static int Opcao3() {
		int repeat = 0;
		int tema;
		int palavra;

		Scanner ler = new Scanner(System.in);

		System.out.print("=================================================\n\n");
		System.out.print("                       JOGAR\n");
		System.out.print("-------------------------------------------------\n\n");
		switch (BancoDados(3, 0, null, null)) {
		case 0: {
			System.out.print("-------------------------------------------------\n\n");
			System.out.print("  Escolha o tema que deseja jogar: ");
			tema = ler.nextInt();
			System.out.print("\n");

			palavra = BancoDados(8, tema, null, null);

			switch (palavra) {
			case -1: {
				System.out.print(" Não existem palavras cadastradas nesse tema! \n");
			}
				break;

			default: {
				Opcao31(palavra, tema - 1);
			}
			}
		}
			break;
		case 3: {
			System.out.print("  Não existem temas cadastrados!\n\n ");
		}
			break;
		}
		return 0;
	}

	public static void Opcao31(int palavra, int tema) {
		String escolhida = matrizGeral[tema][palavra];
		String[] escolhida1 = escolhida.split("(?!^)");

		System.out.print(" Escolhida: " + escolhida + "\n" + "Escolhida1 :" + escolhida1 + "\n");
		
		int tamString = escolhida.length();
		char[] comparador = new char[tamString];
		char[] letrasEscolhidas = new char[MAX];

		for (int i = 0; i < tamString; i++)
			comparador[i] = '_';

		int tentativas = 0;
		int cont = 0;
		int erros = 0;
		int acerto = 0;
		int ganhou = 0;

		char letra;

		while (erros < 5 || ganhou != 1) {
			tentativas = tentativas + 1;
			System.out.print("=================================================\n\n");
			System.out.print("                     " + matrizGeral[tema][0] + "\n");
			System.out.print(" Erros: " + erros);
			System.out.print("-------------------------------------------------\n\n");

			switch (erros) {
			case 0: {
				Boneco1();
			}
				break;
			case 1: {
				Boneco2();
			}
				break;
			case 2: {
				Boneco3();
			}
				break;
			case 3: {
				Boneco4();
			}
				break;
			case 4: {
				Boneco5();
			}
				break;
			case 5: {
				Boneco6();
			}
				break;
			}

			System.out.print(comparador);

			System.out.print("\n\n =================================================\n");
			System.out.print(" Letras escolhidas: ");

			for (int i = 0; i < MAX - 1; i++) 
				System.out.print(letrasEscolhidas[i] + " ");

			System.out.print("\n-------------------------------------------------\n\n");
			if (comparador.equals(escolhida) == true) {
				ganhou = 1;
			} else {
				System.out.print(" Escolha uma letra: ");
				Scanner ler = new Scanner(System.in);
				letra = ler.next().charAt(0);
				letrasEscolhidas[tentativas] = letra;

				for (int i = 0; i < tamString; i++) {
					cont = cont + 1;
					if (escolhida1[i].equals(letra) == true) {
						comparador[i] = letra;
						acerto = 1;
					} else if (i == tamString - 1 && acerto == 0) {
						erros = erros + 1;
					}
				}
			}
		}

		if (ganhou == 1) {
			System.out.print(" Parabens!!!\n\n");
			System.out.print(" Gostaria de jogar novamente?\n\n");
			System.out.print(" 1- Sim!\n");
			System.out.print(" 2- Não!\n\n");
			System.out.print(" R: ");

		} else {
			System.out.print(" Infelizmente você perdeu..... \n\n");
			System.out.print(" Gostaria de jogar novamente?\n\n");
			System.out.print(" 1- Sim!\n");
			System.out.print(" 2- Não!\n\n");
			System.out.print(" R: ");
		}
	}

	// DESENHO DOS BONECOS
	public static void Boneco1() {
		System.out.print("\n\n");
		System.out.print("  ___________    \n");
		System.out.print(" /          |    \n");
		System.out.print(" |          |    \n");
		System.out.print(" |              \n");
		System.out.print(" |           \n");
		System.out.print(" |          \n");
		System.out.print(" |\n");
		System.out.print(" |     ");
	}

	public static void Boneco2() {
		System.out.print("\n\n");
		System.out.print("  ___________    \n");
		System.out.print(" /          |    \n");
		System.out.print(" |          |    \n");
		System.out.print(" |          O    \n");
		System.out.print(" |           \n");
		System.out.print(" |          \n");
		System.out.print(" |\n");
		System.out.print(" |     ");
		System.out.print("================================================= \n\n");
	}

	public static void Boneco3() {
		System.out.print("\n\n");
		System.out.print("  ___________    \n");
		System.out.print(" /          |    \n");
		System.out.print(" |          |    \n");
		System.out.print(" |          O    \n");
		System.out.print(" |         /| \n");
		System.out.print(" |          \n");
		System.out.print(" |\n");
		System.out.print(" |     ");
		System.out.print("================================================= \n\n");
	}

	public static void Boneco4() {
		System.out.print("\n\n");
		System.out.print("  ___________    \n");
		System.out.print(" /          |    \n");
		System.out.print(" |          |    \n");
		System.out.print(" |          O    \n");
		System.out.print(" |         /|\\  \n");
		System.out.print(" |          \n");
		System.out.print(" |\n");
		System.out.print(" |     ");
		System.out.print("================================================= \n\n");
	}

	public static void Boneco5() {
		System.out.print("\n\n");
		System.out.print("  ___________    \n");
		System.out.print(" /          |    \n");
		System.out.print(" |          |    \n");
		System.out.print(" |          O    \n");
		System.out.print(" |         /|\\  \n");
		System.out.print(" |         /   \n");
		System.out.print(" |\n");
		System.out.print(" |     ");
		System.out.print("================================================= \n\n");
	}

	public static void Boneco6() {
		System.out.print("\n\n");
		System.out.print("  ___________    \n");
		System.out.print(" /          |    \n");
		System.out.print(" |          |    \n");
		System.out.print(" |          O    \n");
		System.out.print(" |         /|\\  \n");
		System.out.print(" |         / \\  \n");
		System.out.print(" |\n");
		System.out.print(" |     ");
		System.out.print("================================================= \n\n");
	}
}
