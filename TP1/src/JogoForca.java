import java.util.Scanner;
import java.util.Random;

public class JogoForca {

	// ==============================================
	// MATRIZ DO BANCO DE DADOS
	public static int MAX = 51;
	public static String[][] matrizGeral = new String[MAX][MAX];

	// ==============================================
	// MENU
	public static void main(String[] args) {
		char escolha = 0;
		int repeat = 0;

		BancoDeDados(); // Preenche a matriz com as palavras do banco de dados.

		Scanner ler = new Scanner(System.in);

		do {
			Menu();

			do {
				System.out.print(" Escolha uma opção(1-5): ");
				escolha = ler.next().charAt(0);
				System.out.print("\n");

				switch (escolha) {
				case '1':
					Opcao1();
					repeat = 0;
					break;
				case '2':
					Opcao2();
					repeat = 0;
					break;
				case '3':
					Opcao3();
					repeat = 0;
					break;
				case '4':
					repeat = 0;
					break;

//========== CODIGOS PARA TESTES DA MATRIZ ===========
//====================================================	

				case 'T':
					teste();
					repeat = 0;
					break;
				case 'P':
					print();
					repeat = 0;
					break;

//====================================================
				default:
					System.out.print(" Favor inserir uma opção entre 1 e 4!\n\n");
					repeat = 1;
				}
			} while (repeat == 1);
		} while (escolha != '4');
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

	// ==============================================
	// REALIZA TODAS AS ALTERAÇÕES NA MATRIZ
	public static int Operador(int escolha, int cont, String tema, String palavra) {
		cont = cont - 1;

		int validador = 1;

		switch (escolha) {
// ---------------------------------------------------------------------
		case 1: { // Criar novo tema
			for (int a = 0; a < MAX - 1; a++) {
				if (matrizGeral[a][0] != null) {
					if (matrizGeral[a][0].equals(tema) == true)
						return 1; // Tema ja existe
					if (a == MAX - 2 && validador == 1)
						return 2; // Limite de temas excedido
				} else if (validador == 1) {
					matrizGeral[a][0] = tema.toUpperCase();
					validador = 0;
				}
			}
		}
			break;
// ---------------------------------------------------------------------
		case 2: { // Criar nova palavra
			for (int j = 1; j < MAX; j++) {
				if (matrizGeral[cont][j] != null) {
					if (matrizGeral[cont][j].equals(palavra) == true)
						return 1; // Palavra ja existe
					if (j == MAX - 1 && validador == 1)
						return 2; // Limite de palavras excedido
				} else if (validador == 1) {
					matrizGeral[cont][j] = palavra.toUpperCase();
					validador = 0;
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
						return -1; // Nenhum tema cadastrado
					else
						return a;
				}
			}
			return MAX - 1;
		}

// ---------------------------------------------------------------------
		case 4: { // Exclusão do tema escolhido
			if (matrizGeral[cont][1] == null) {
				matrizGeral[cont][0] = null;
			} else {
				return 4; // Existem palavras cadastradas nesse tema
			}

			for (int a = cont; a < MAX - 1; a++) {
				for (int j = 0; j < MAX; j++) {
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
					if (matrizGeral[a][1] != null) {
						System.out.print(" " + matrizGeral[a][0] + "| ");
						validador = 0;
						for (int j = 1; j < MAX - 1; j++)
							if (matrizGeral[a][j] != null)
								System.out.print(" " + (j) + "- " + matrizGeral[a][j] + " / ");
					} else
						return -1; // Não existem palavras cadastradas nesse tema
				}
		}
			break;

// ---------------------------------------------------------------------
		case 6: { // Excluir palavra
			for (int j = 1; j < MAX; j++) {
				if (matrizGeral[cont][j] != null)
					if (matrizGeral[cont][j].equals(palavra) == true) {
						matrizGeral[cont][j] = null;
						for (; j < MAX - 1; j++)
							matrizGeral[cont][j] = matrizGeral[cont][j + 1];
						return 0;
					}
			}
			return 6; // Palavra não encontrada
		}

// ---------------------------------------------------------------------			
		case 7: { // Buscar palavra
			for (int a = 0; a < MAX - 1; a++)
				for (int j = 0; j < MAX; j++)
					if (matrizGeral[a][j] != null) {
						if (j != 0 && matrizGeral[a][j].equals(palavra) == true) {
							System.out.print(" A palavra '" + palavra + "' está cadastrada no tema '"
									+ matrizGeral[a][0] + "'! \n");
							validador = 0;
						}
					} else if (matrizGeral[a][0] == null && validador != 0)
						return 6; // Palavra não encontrada

		}
			break;

// ---------------------------------------------------------------------
		case 8: { // Escolher uma palavra aleatória
			Random random = new Random();
			int totalPalavras = 0;

			for (int j = 1; j < MAX; j++)
				if (matrizGeral[cont][j] != null)
					totalPalavras = totalPalavras + 1;

			if (totalPalavras != 0) {
				int escolhida = random.nextInt(totalPalavras);
				if (escolhida == 0)
					escolhida = escolhida + 1;
				return escolhida;
			} else {
				return -1; // Não existem palavras cadastradas nesse tema
			}
		}
		}

		return 0; // Tudo ocorreu perfeitamente!
	}

	// ==============================================
	// GERENCIAR TEMAS
	public static int Opcao1() {
		int repeat = 0;
		char escolha;

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
			escolha = ler.next().charAt(0);
			System.out.print("\n");

			switch (escolha) {
			case '1':
				Opcao11();
				repeat = 0;
				break;
			case '2':
				Opcao12();
				repeat = 0;
				break;
			case '3':
				Opcao13();
				repeat = 0;
				break;
			case '4':
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
		// ler.nextLine();
		tema = ler.nextLine();
		System.out.print("\n");

		switch (Operador(1, 0, tema.toUpperCase(), null)) {
		case 0:
			System.out.print("  Tema cadastrado com sucesso!\n");
			break;
		case 1:
			System.out.print("  Tema ja existente! \n");
			break;
		case 2:
			System.out.print("  Capacidade maxima de temas excedida, operação não realizada! \n");
			break;
		}

	}

	// EXCLUIR TEMA
	public static void Opcao12() {
		Scanner ler = new Scanner(System.in);
		int escolha = 0;
		int retorno;
		int tentativas = 0;
		boolean validaErro = false;

		System.out.print("=================================================\n\n");
		System.out.print("               EXCLUIR TEMA\n");
		System.out.print("-------------------------------------------------\n\n");
		retorno = Operador(3, 0, null, null);
		switch (retorno) {
		case -1:
			System.out.print(" Não existem temas cadastrados!\n\n");
			break;
		default: {
			System.out.print("-------------------------------------------------\n\n");
			System.out.print(" Escolha o numero do tema que deseja excluir: ");

			do {
				if (tentativas != 0) {
					ler.nextLine();
				}
				tentativas = 1;

				if (ler.hasNextInt()) {
					escolha = ler.nextInt();
					System.out.print("\n");

					if (escolha <= retorno && escolha > 0)
						validaErro = true;
					else {
						System.out.print(" Favor escolher uma opção de 1 a " + retorno + "\n R: ");
					}
				} else {
					System.out.print(" Favor inserir um valor numérico! \n");
					System.out.print(" R: ");
				}
			} while (!validaErro);

			switch (Operador(4, escolha, null, null)) {
			case 0:
				System.out.print(" Tema excluido com sucesso!\n");
				break;
			case 4:
				System.out.print(
						" Não foi possível excluir o tema.\n Verifique se existem palavras cadastradas nesse tema.\n");
				break;
			}
		}
			break;
		}
	}

	// BUSCAR TEMA
	public static void Opcao13() {

		Scanner ler = new Scanner(System.in);
		String tema;
		int validador = 0;

		while (validador != 2) {
			boolean validaErro = false;
			System.out.print("=================================================\n\n");
			System.out.print("                  BUSCAR TEMAS\n");
			System.out.print("-------------------------------------------------\n\n");
			System.out.print(" Digite o tema que deseja buscar: ");
			tema = ler.next();
			System.out.print("\n");

			switch (Operador(5, 0, tema.toUpperCase(), null)) {
			case 0:

				break;
			case 5:
				System.out.print(" Tema não encontrado!");
				break;
			}

			System.out.print("\n\n Deseja buscar novamente? \n\n");
			System.out.print(" 1- Sim \n");
			System.out.print(" 2- Não \n\n");
			System.out.print(" R:  ");
			int escolha = 0;

			do {
				if (ler.hasNextInt()) {
					escolha = ler.nextInt();
					System.out.print("\n");

					switch (escolha) {
					case 1:
						validaErro = true;
						break;
					case 2:
						validaErro = true;
						validador = 2;
						break;
					default:
						System.out.print("\n Favor insererir uma opção entre 1 e 2! \n");
						System.out.print(" R: ");
					}
				} else {
					System.out.print(" Favor inserir um valor numérico! \n");
					System.out.print(" R: ");
					ler.nextLine();
				}
			} while (!validaErro);
		}
	}

	// ==============================================
	// GERENCIAR PALAVRAS
	public static int Opcao2() {
		char escolha = '0';
		boolean validaErro = false;

		Scanner ler = new Scanner(System.in);

		System.out.print("=================================================\n\n");
		System.out.print("               GERENCIAR PALAVRAS\n");
		System.out.print("-------------------------------------------------\n\n");
		System.out.print("  1- Cadastrar\n");
		System.out.print("  2- Excluir\n");
		System.out.print("  3- Buscar\n");
		System.out.print("  4- Voltar ao Menu\n\n");
		System.out.print("-------------------------------------------------\n\n");

		System.out.print("  Escolha uma opção(1-4): ");

		do {
			escolha = ler.next().charAt(0);

			switch (escolha) {
			case '1':
				validaErro = true;
				Opcao21();
				break;
			case '2':
				validaErro = true;
				Opcao22();
				break;
			case '3':
				validaErro = true;
				Opcao23();
				break;
			case '4':
				validaErro = true;
				break;
			default:
				System.out.print("\n Favor insererir uma opção entre 1 e 4! \n");
				System.out.print(" R: ");
			}
		} while (!validaErro);

		return 0;
	}

	// CADASTRAR PALAVRA
	public static void Opcao21() {

		Scanner ler = new Scanner(System.in);
		String palavra;
		int escolha = 0;
		boolean validaErro = false;
		int retorno;

		System.out.print("=================================================\n\n");
		System.out.print("               CADASTRAR PALAVRA\n");
		System.out.print("-------------------------------------------------\n\n");
		retorno = Operador(3, 0, null, null);
		switch (retorno) {
		case -1:
			System.out.print(" Não existem temas cadastrados!\n\n");
			break;
		default: {
			System.out.print("-------------------------------------------------\n\n");
			System.out.print(" Escolha o tema que deseja cadastrar a palavra: ");

			do {
				if (ler.hasNextInt()) {
					escolha = ler.nextInt();
					System.out.print("\n");

					if (escolha <= retorno && escolha > 0)
						validaErro = true;
					else
						System.out.print(" Favor escolher uma opção de 1 a " + retorno + "\n R: ");
				} else {
					ler.nextLine();
					System.out.print(" Favor inserir um valor numérico! \n");
					System.out.print(" R: ");
				}
			} while (!validaErro);

			System.out.print("\n");

			System.out.print(" Digite a palavra que deseja cadastrar: ");
			ler.nextLine();
			palavra = ler.nextLine();
			palavra = palavra.toUpperCase();
			System.out.print("\n");
			int tamPalavra = palavra.length();
			int palavraValida = 0;

			if (tamPalavra <= 1) {
				System.out.print(" Favor inserir uma palavra com mais de uma letra!\n");
				break;
			}

			for (int i = 0; i < tamPalavra; i++) {
				if (palavra.charAt(i) >= 65 && palavra.charAt(i) <= 90
						|| (palavra.charAt(i) >= 48 && palavra.charAt(i) <= 57)
						|| (palavra.charAt(i) == 'Ã' || palavra.charAt(i) == 'Á' || palavra.charAt(i) == 'À'
								|| palavra.charAt(i) == 'Â' || palavra.charAt(i) == 'É' || palavra.charAt(i) == 'Ê'
								|| palavra.charAt(i) == 'Í' || palavra.charAt(i) == 'Ç' || palavra.charAt(i) == 'Ô'
								|| palavra.charAt(i) == 'Ó' || palavra.charAt(i) == 'Õ' || palavra.charAt(i) == 'Ú'
								|| palavra.charAt(i) == ' ' || palavra.charAt(i) == '-')) {

				} else
					palavraValida = 1;
			}
			if (palavraValida == 1) {
				System.out.println("\n Palavra inválida!!\n");
				break;
			} else
				System.out.println("\n Sua palavra é: " + palavra);

			switch (Operador(2, escolha, null, palavra.toUpperCase())) {
			case 0:
				System.out.print(" Palavra cadastrado com sucesso!\n\n");
				break;
			case 1:
				System.out.print(" Palavra ja existente!\n\n");
				break;
			case 2:
				System.out.print(" Capacidade maxima de palavras excedida, operação não realizada!\n\n");
				break;
			}
		}
			break;
		}
	}

	// EXCLUIR PALAVRA
	public static void Opcao22() {

		Scanner ler = new Scanner(System.in);
		String palavra;

		int escolha = 0;
		boolean validaErro = false;
		int tentativas = 0;

		System.out.print("=================================================\n\n");
		System.out.print("                 EXCLUIR PALAVRA\n");
		System.out.print("-------------------------------------------------\n\n");
		int retorno = Operador(3, 0, null, null);
		switch (retorno) {
		case -1:
			System.out.print(" Não existem temas cadastrados!\n\n");
			break;
		default: {
			System.out.print("-------------------------------------------------\n\n");
			System.out.print(" Escolha o tema que deseja excluir a palavra: ");

			do {
				if (tentativas != 0)
					ler.nextLine();
				tentativas = 1;
				if (ler.hasNextInt()) {
					escolha = ler.nextInt();
					System.out.print("\n");

					if (escolha <= retorno && escolha > 0)
						validaErro = true;
					else
						System.out.print(" Favor escolher uma opção de 1 a " + retorno + "\n R: ");
				} else {
					System.out.print(" Favor inserir um valor numérico! \n");
					System.out.print(" R: ");
				}
			} while (!validaErro);

			if (Operador(5, 0, matrizGeral[escolha - 1][0], null) != -1) {
				System.out.print("\n\n");
				System.out.print(" Digite por extenso a palavra que deseja excluir: ");
				ler.nextLine();
				palavra = ler.nextLine();
				System.out.print("\n");

				switch (Operador(6, escolha, null, palavra.toUpperCase())) {
				case 0:
					System.out.print(" Palavra excluida com sucesso!\n");
					break;
				case 6:
					System.out.print(" Palavra não encontrada!\n");
					break;
				}
			} else
				System.out.print(" Não existem palavras cadastradas nesse tema! \n");

		}
			break;
		}
	}

	// BUSCAR PALAVRA
	public static void Opcao23() {

		Scanner ler = new Scanner(System.in);
		String palavra;
		int escolha = 0;

		while (escolha != '2') {
			boolean validaErro = false;

			System.out.print("=================================================\n\n");
			System.out.print("                  BUSCAR PALAVRAS\n");
			System.out.print("-------------------------------------------------\n\n");
			System.out.print(" Digite a palavra que deseja buscar: ");
			palavra = ler.nextLine();
			System.out.print("\n");

			switch (Operador(7, 0, null, palavra.toUpperCase())) {
			case 0:
				break;
			case 6:
				System.out.print(" Palavra não encontrada! \n");
				break;
			}
			System.out.print("\n Deseja buscar novamente? \n\n");
			System.out.print(" 1- Sim \n");
			System.out.print(" 2- Não \n\n");
			System.out.print(" R: ");

			do {
				escolha = ler.next().charAt(0);

				switch (escolha) {
				case '1':
					validaErro = true;
					break;
				case '2':
					validaErro = true;
					break;
				default:
					System.out.print("\n Favor insererir uma opção entre 1 e 2! \n");
					System.out.print(" R: ");
				}
			} while (!validaErro);
		}
	}

	// =============================================
	// JOGAR
	public static int Opcao3() {
		int escolha = 0;
		int palavra;
		int retorno;
		int tentativa = 0;
		boolean validaErro = false;

		Scanner ler = new Scanner(System.in);

		System.out.print("=================================================\n\n");
		System.out.print("                       JOGAR\n");
		System.out.print("-------------------------------------------------\n\n");
		retorno = Operador(3, 0, null, null);
		switch (retorno) {
		case -1: {
			System.out.print(" Não existem temas cadastrados!\n\n");
		}
			break;
		default: {
			System.out.print(
					"\n Obs: Caso digite mais de uma letra em conjunto,\n o programa assumirá sempre a primeira letra\n");
			System.out.print("-------------------------------------------------\n\n");
			System.out.print(" Escolha o tema que deseja jogar: ");

			do {

				if (tentativa != 0) // Limpa buffer
					ler.nextLine();
				else
					tentativa = 1;

				if (ler.hasNextInt()) {
					escolha = ler.nextInt();
					System.out.print("\n");

					if (escolha <= retorno && escolha > 0)
						validaErro = true;
					else
						System.out.print(" Favor escolher uma opção de 1 a " + retorno + "\n R: ");
				} else {
					System.out.print("\n Favor inserir um valor numérico! \n");
					System.out.print(" R: ");
				}
			} while (!validaErro);

			palavra = Operador(8, escolha, null, null);

			switch (palavra) {
			case -1: {
				System.out.print(" Não existem palavras cadastradas nesse tema! \n");
			}
				break;

			default: {
				Opcao31(palavra, escolha - 1);
			}
			}
		}
			break;
		}
		return 0;
	}

	// JOGAR - CONTINUAÇÃO
	public static void Opcao31(int palavra, int tema) {
		Scanner ler = new Scanner(System.in);
		String palavraChave = matrizGeral[tema][palavra];
		int tamString = palavraChave.length();
		String palavraAdivinhada = "";

		for (int i = 0; i < tamString; i++) {
			if (palavraChave.charAt(i) == ' ') {
				palavraAdivinhada += ' ';
			} else if (palavraChave.charAt(i) == '-') {
				palavraAdivinhada += '-';
			} else
				palavraAdivinhada += "_";
		}

		char[] letrasEscolhidas = new char[MAX];
		String letrasUsadas = "";

		int tentativas = 0;
		int erros = 0;
		int ganhou = 0;

		char letra;

		while (erros < 5 && ganhou != 1) {
			tentativas = tentativas + 1;
			System.out.print("\n=================================================\n\n");
			System.out.print("                     " + matrizGeral[tema][0] + "\n");
			System.out.print(" Erros: " + erros + " \\ 5\n");
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
			}

			for (int i = 0; i < (tamString * 2); i++)
				if (i == 0)
					System.out.print(palavraAdivinhada.charAt(i));
				else if (i % 2 == 0)
					System.out.print(palavraAdivinhada.charAt(i / 2));
				else
					System.out.print("  ");

			System.out.print("\n\n=================================================\n");
			System.out.print(" Letras escolhidas:");

			for (int i = 0; i < MAX - 1; i++)
				System.out.print(letrasEscolhidas[i] + " ");

			System.out.print("\n-------------------------------------------------\n\n");
			if (palavraChave.equals(palavraAdivinhada) == true) {
				ganhou = 1;
			} else {
				System.out.print(" Escolha uma letra: ");
				letra = ler.next().charAt(0);
				letra = Character.toUpperCase(letra);

				if (letrasUsadas.indexOf(letra) >= 0) {
					System.out.print("\n Letra ja utilizada!\n\n");
					tentativas = tentativas - 1;
				} else if (letra >= 65 && letra <= 90 || (letra >= 48 && letra <= 57) || (letra == 'Ã' || letra == 'Á' || letra == 'À' || letra == 'Â'
						|| letra == 'É' || letra == 'Ê' || letra == 'Í' || letra == 'Ç' || letra == 'Ô' || letra == 'Ó'
						|| letra == 'Õ' || letra == 'Ú')) {
					letrasUsadas += letra;
					letrasEscolhidas[tentativas] = letra;

					if (palavraChave.indexOf(letra) >= 0) {
						palavraAdivinhada = "";

						for (int i = 0; i < tamString; i++) {
							if (letrasUsadas.indexOf(palavraChave.charAt(i)) >= 0) {
								palavraAdivinhada += palavraChave.charAt(i);
							} else if (palavraChave.charAt(i) == ' ') {
								palavraAdivinhada += ' ';
							} else if (palavraChave.charAt(i) == '-') {
								palavraAdivinhada += '-';
							} else
								palavraAdivinhada += "_";
						}
					} else {
						erros = erros + 1;
					}
				} else
					System.out.print(" Favor inserir uma letra válida!! \n");
			}
		}

		if (ganhou == 1) {
			System.out.print(" Parabens!!!\n\n");
		} else {
			Boneco6();

			for (int i = 0; i < (tamString * 2); i++)
				if (i == 0)
					System.out.print(palavraAdivinhada.charAt(i));
				else if (i % 2 == 0)
					System.out.print(palavraAdivinhada.charAt(i / 2));
				else
					System.out.print("  ");

			System.out.print("\n\n Infelizmente você perdeu..... \n\n");

			System.out.println(" A palavra era: " + palavraChave + "\n");
		}

		System.out.print(" Gostaria de jogar novamente?\n\n");
		System.out.print(" 1- Sim!\n");
		System.out.print(" 2- Não!\n\n");
		System.out.print(" R: ");

		boolean validaErro = false;
		char escolha = '0';

		do {
			escolha = ler.next().charAt(0);

			switch (escolha) {
			case '1':
				validaErro = true;
				break;
			case '2':
				validaErro = true;
				break;
			default:
				System.out.print("\n Favor insererir uma opção entre 1 e 2! \n");
				System.out.print(" R: ");
				ler.nextLine();
			}
		} while (!validaErro);

		if (escolha == '1')
			Opcao3();
	}

	// ==============================================
	// ABASTECER MATRIZ COM TEMAS E PALAVRAS
	public static void BancoDeDados() {

		matrizGeral[0][0] = "ANIMAIS";

		matrizGeral[0][1] = "CACHORRO";
		matrizGeral[0][2] = "GATO";
		matrizGeral[0][3] = "PÁSSARO";
		matrizGeral[0][4] = "GIRAFA";
		matrizGeral[0][5] = "RATO";
		matrizGeral[0][6] = "GALINHA";
		matrizGeral[0][7] = "MORCEGO";
		matrizGeral[0][8] = "LEÃO";
		matrizGeral[0][9] = "MACACO ";
		matrizGeral[0][10] = "COBRA";

		matrizGeral[1][0] = "VEÍCULOS";

		matrizGeral[1][1] = "CARRO";
		matrizGeral[1][2] = "AVIAO";
		matrizGeral[1][3] = "ONIBUS";
		matrizGeral[1][4] = "SUBMARINO";
		matrizGeral[1][5] = "NAVIO";
		matrizGeral[1][6] = "HELICÓPTERO";
		matrizGeral[1][7] = "BICICLETA";
		matrizGeral[1][8] = "PATINETE";
		matrizGeral[1][9] = "PATINS ";
		matrizGeral[1][10] = "MOTO";

		matrizGeral[2][0] = "LUGARES";

		matrizGeral[2][1] = "LANCHONETE";
		matrizGeral[2][2] = "HOSPITAL";
		matrizGeral[2][3] = "ESCOLA";
		matrizGeral[2][4] = "HOSPICIO";
		matrizGeral[2][5] = "RESTAURANTE";
		matrizGeral[2][6] = "LOJA";
		matrizGeral[2][7] = "PRAIA";
		matrizGeral[2][8] = "PARQUE";
		matrizGeral[2][9] = "CLUBE";
		matrizGeral[2][10] = "SORVETERIA";

		matrizGeral[3][0] = "PAÍSES";

		matrizGeral[3][1] = "AFRICA DO SUL";
		matrizGeral[3][2] = "BRASIL";
		matrizGeral[3][3] = "BELGICA";
		matrizGeral[3][4] = "COLÔMBIA";
		matrizGeral[3][5] = "ESPANHA";
		matrizGeral[3][6] = "ESTADOS UNIDOS";
		matrizGeral[3][7] = "FRANÇA";
		matrizGeral[3][8] = "COREIA DO NORTE";
		matrizGeral[3][9] = "REINO UNIDO";
		matrizGeral[3][10] = "JAPÃO";

		matrizGeral[4][0] = "LOL";

		matrizGeral[4][1] = "LUX";
		matrizGeral[4][2] = "LEONA";
		matrizGeral[4][3] = "SYNDRA";
		matrizGeral[4][4] = "GAREN";
		matrizGeral[4][5] = "LULU";
		matrizGeral[4][6] = "MASTER YI";
		matrizGeral[4][7] = "IRELIA";
		matrizGeral[4][8] = "JANNA";
		matrizGeral[4][9] = "YASUO";
		matrizGeral[4][10] = "MORGANA";

		matrizGeral[5][0] = "COMIDAS";

		matrizGeral[5][1] = "MACARRÃO";
		matrizGeral[5][2] = "STROGONOFF";
		matrizGeral[5][3] = "PIZZA";
		matrizGeral[5][4] = "SALADA";
		matrizGeral[5][5] = "FEIJOADA";
		matrizGeral[5][6] = "CHURRASCO";
		matrizGeral[5][7] = "SUSHI";
		matrizGeral[5][8] = "HAMBÚRGUER";
		matrizGeral[5][9] = "MOQUECA";
		matrizGeral[5][10] = "CAMARÃO";

		matrizGeral[6][0] = "CORES";

		matrizGeral[6][1] = "LARANJA";
		matrizGeral[6][2] = "ROXO";
		matrizGeral[6][3] = "AMARELO";
		matrizGeral[6][4] = "VERMELHO";
		matrizGeral[6][5] = "AZUL";
		matrizGeral[6][6] = "MARROM";
		matrizGeral[6][7] = "PRETO";
		matrizGeral[6][8] = "BRANCO";
		matrizGeral[6][9] = "CINZA";
		matrizGeral[6][10] = "VERDE";

		matrizGeral[7][0] = "FRUTAS";

		matrizGeral[7][1] = "BANANA";
		matrizGeral[7][2] = "MAÇÃ";
		matrizGeral[7][3] = "MELANCIA";
		matrizGeral[7][4] = "ABACATE";
		matrizGeral[7][5] = "GOIABA";
		matrizGeral[7][6] = "JACA";
		matrizGeral[7][7] = "UVA";
		matrizGeral[7][8] = "MELÃO";
		matrizGeral[7][9] = "LARANJA";
		matrizGeral[7][10] = "ACEROLA";

		matrizGeral[8][0] = "PROFISSÕES";

		matrizGeral[8][1] = "CANTOR";
		matrizGeral[8][2] = "PROFESSOR";
		matrizGeral[8][3] = "COZINHEIRO";
		matrizGeral[8][4] = "ATENDENTE";
		matrizGeral[8][5] = "MÉDICO";
		matrizGeral[8][6] = "VENDEDOR";
		matrizGeral[8][7] = "CIENTISTA";
		matrizGeral[8][8] = "ENGENHEIRO";
		matrizGeral[8][9] = "VETERINARIO";
		matrizGeral[8][10] = "DENTISTA";

		matrizGeral[9][0] = "CAPITAIS";

		matrizGeral[9][1] = "BRASILIA";
		matrizGeral[9][2] = "OTTAWA";
		matrizGeral[9][3] = "SANTIAGO";
		matrizGeral[9][4] = "WASHINGTON DC";
		matrizGeral[9][5] = "PARIS";
		matrizGeral[9][6] = "MADRID";
		matrizGeral[9][7] = "TOQUIO";
		matrizGeral[9][8] = "CAMBERRA";
		matrizGeral[9][9] = "BERLIM";
		matrizGeral[9][10] = "PRETÓRIA";
	}

	// ==============================================
	// ENCHER A MATRIZ
	public static void teste() {

		for (int a = 0; a < MAX - 1; a++)
			for (int j = 0; j < MAX; j++) {
				String linha = "";
				linha += a;
				String coluna = "";
				coluna += j;
				String valor = "";
				valor += (linha + coluna);
				matrizGeral[a][j] = valor;
			}

	}

	// IMPRIMIR A MATRIZ
	public static void print() {
		System.out.print("\n");
		for (int a = 0; a < MAX - 1; a++) {
			if (matrizGeral[a][0] != null)
				System.out.print("\n" + " " + (a + 1) + "- " + matrizGeral[a][0] + "| ");
			for (int j = 1; j < MAX; j++)
				if (matrizGeral[a][j] != null)
					System.out.print(" " + (j) + "- " + matrizGeral[a][j] + " / ");
		}
		System.out.print("\n\n");
	}

	// ==============================================
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
		System.out.print(" |          | \n");
		System.out.print(" |          \n");
		System.out.print(" |\n");
		System.out.print(" |     ");
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
	}

}
