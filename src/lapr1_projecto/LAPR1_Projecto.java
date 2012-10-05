package LAPR1_Projecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class LAPR1_Projecto {

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		char opcao;

		opcao = Menu();
		while (opcao != 0) {
			ChamarMetodos(opcao);
			System.out.println("\n");
			opcao = Menu();
		}
	}

	public static char Menu() {
		String n;
		char opcao;

		do {
			System.out.println("\t\t\tMenu");
			System.out.println("1 - Representação da Reta");
			System.out.println("2 - Determinação da equação vetorial da reta.");
			System.out
					.println("3 - Determinação da equação vetorial do plano.");
			System.out
					.println("4 - Determinação de um ponto e vetor diretor de uma reta.");
			System.out
					.println("5 - Determinação da distância de um ponto a uma reta.");
			System.out.println("6 - Representação do plano.");
			System.out
					.println("7 - Determinar a distância de um ponto a um plano.");
			System.out
					.println("8 - Representação de uma reta num intervalo x,y.");
			System.out
					.println("9 - Representação de um plano num intervalo x,y.");
			System.out.println("0 - Sair.");
			System.out
					.println("-------------------------------------------------------------");
			System.out.println("Introduza a opção que deseja!");
			System.out.println();
			n = in.next();
		} while (n.length() != 1);
		opcao = n.charAt(0);

		return opcao;
	}
	
	private static void ChamarMetodos(char opcao) throws FileNotFoundException {
		switch (opcao) {
		case '1':
			RepresentarRecta();
			break;
		case '2':
			EquacaoReta();
			break;
		case '3':
			EquacaoPlano();
			break;
		case '4':
			PontoVetorReta();
			break;
		case '5':
			DistanciaPontoReta();
			break;
		case '6':
			RepresentarPlano();
			break;
		case '7':
			DistanciaPontoPlano();
			break;
		case '8':
			RetaIntervaloXY();
			break;
		case '9':
			PlanoIntervalorXY();
			break;
		case '0':
			System.out.println("Programa Terminado!");
			System.exit(0);
			break;
		default:
			System.out.println("Opção não existente");
			break;
		}

	}

	private static void RepresentarRecta() throws FileNotFoundException {
		String n;
		char op;
		int ex = 1;

		do {
			System.out
					.println("Escolha o tipo de equação que pretende transformar.");
			System.out.println("1 - Equação Vetorial");
			System.out.println("2 - Equação Paramétrica");
			System.out.println("3 - Equação Cartesiana");
			System.out.println("Insira qualquer outro valor para voltar ao menu anterior");
			n = in.next();
		} while (n.length() != 1);
		op = n.charAt(0);
		switch (op) {
		case '1':
			vectorial(ex);
			break;
		case '2':
			parametrica(ex);
			break;
		case '3':
			cartesiana(ex);
			break;
		default:
			System.out.println("Voltando menu anterior...");
			return;
		}

		return;
	}

	private static void vectorial(int ex) throws FileNotFoundException {
		float x0 = 0, y0 = 0, z0 = 0, a = 0, b = 0, c = 0, px0=0, py0=0, pz0=0;
		String[] test;
		String vect = "";
		int op = 0, opout = 0;

		opout = MenuInput();
		if (opout == 1) {
			System.out.println("Insira a equação, sem colocar espaços.");
			System.out.println("Insira sob a máscara: (x,y,z)=(x0,y0,z0)+k(a,b,c)");
			vect = in.next();
		}
		if (opout == 2) {
			vect = DescodificadorHTML.eqremov(9, ex);
			if (ex==5){
				px0=DescodificadorHTML.prem(10, 5, 0);
				py0=DescodificadorHTML.prem(10, 5, 1);
				pz0=DescodificadorHTML.prem(10, 5, 2);				
			}
		}
		if (vect.matches("^\\(x,y,z\\)=\\({1}[-]?[0-9]+([.]{1}[0-9]+)?,{1}[-]?[0-9]+([.]{1}[0-9]+)?,{1}[-]?[0-9]+([.]{1}[0-9]+)?\\)\\+k\\({1}[-]?[0-9]+([.]{1}[0-9]+)?,{1}[-]?[0-9]+([.]{1}[0-9]+)?,{1}[-]?[0-9]+([.]{1}[0-9]+)?\\){1}$")) {
			vect = vect.replaceAll("\\(x,y,z\\)=\\(", "");
			vect = vect.replaceAll("\\)\\+k\\(", ",");
			vect = vect.replaceAll("\\)", "");
			test = vect.split(",");
			x0 = Float.parseFloat(test[0]);
			y0 = Float.parseFloat(test[1]);
			z0 = Float.parseFloat(test[2]);
			a = Float.parseFloat(test[3]);
			b = Float.parseFloat(test[4]);
			c = Float.parseFloat(test[5]);
		} else {
			System.out.println("Equação Incorreta");

			return;
		}
		op = MenuOutput();
		while (op != '1' || op != '2') {
			if (op == '1') {
				if (ex == 1) {
					convertParametrica(x0, y0, z0, a, b, c, 1);
					convertCartesiana(x0, y0, z0, a, b, c, 1);
				}
				if (ex == 4) {
					convertpontos(x0, y0, z0, a, b, c);
					break;
				}
				if (ex == 5) {
					calcDist(x0, y0, z0, a, b, c, px0, py0, pz0, opout);
					break;
				}
				if (ex == 8) {
					CSV(x0, y0, z0, a, b, c);
					break;
				}
				break;
			} else if (op == '2') {
				if (ex == 1) {
					Formatter write = new Formatter(new File(ex + ".html"));
					CodificadorHTML.CorpoInicioHTML(write);
					CodificadorHTML.convertVectorial(x0, y0, z0, a, b, c, write);
					CodificadorHTML.convertParametrica(x0, y0, z0, a, b, c, 1,write);
					CodificadorHTML.convertCartesiana(x0, y0, z0, a, b, c, 1,write);
					CodificadorHTML.CorpoFimHTML(write);
					write.close();
					break;
				}
				if (ex == 4) {
					CodificadorHTML.convertpontos(x0, y0, z0, a, b, c, 1);
					break;
				}
				if (ex == 5) {
					CodificadorHTML.calcDist(x0, y0, z0, a, b, c, 1);
					break;
				}
				if (ex == 8) {
					Formatter write = new Formatter(new File(ex + ".html"));
					CodificadorHTML.CorpoInicioHTML(write);
					CodificadorHTML.convertVectorial(x0, y0, z0, a, b, c, write);
					CodificadorHTML.CSV(x0, y0, z0, a, b, c, write);
					CodificadorHTML.CorpoFimHTML(write);
					write.close();
					break;
				}
			} else {
				System.out.println("Operação não válida! Escolha novamente!");
				op = MenuOutput();
			}
		}

		return;
	}
	
	private static void parametrica(int ex) throws FileNotFoundException {
		float x0 = 0, y0 = 0, z0 = 0, a = 0, b = 0, c = 0, px0=0, py0=0, pz0=0;
		String[] test;
		String vectx = "", vecty = "", vectz = "";
		int op, opout;

		opout = MenuInput();
		if (opout == 1) {
			System.out.println("Insira a equação dos XX, sem colocar espaços.");
			System.out.println("Insira sob a máscara: x=x0+ak");
			vectx = in.next();
			vectx.replace(' ', (char) 0);
			System.out.println("Insira a equação dos YY, sem colocar espaços.");
			System.out.println("Insira sob a máscara: y=x0+ak");
			vecty = in.next();
			vecty.replace(' ', (char) 0);
			System.out.println("Insira a equação dos ZZ, sem colocar espaços.");
			System.out.println("Insira sob a máscara: z=x0+ak");
			vectz = in.next();
			vectz.replace(' ', (char) 0);
		}
		if (opout == 2) {
			vectx = DescodificadorHTML.eqremov(9, ex);
			vecty = DescodificadorHTML.eqremov(10, ex);
			vectz = DescodificadorHTML.eqremov(11, ex);
			if (ex==5){
				px0=DescodificadorHTML.prem(12, 5, 0);
				py0=DescodificadorHTML.prem(12, 5, 1);
				pz0=DescodificadorHTML.prem(12, 5, 2);				
			}
		}

		if (vectx.matches("^x={1}[-]?[0-9]+([.]{1}[0-9]+)?[+]{1}[0-9]+([.]{1}[0-9]+)?k{1}$")) {
			vectx = vectx.replaceAll("x=", "");
			vectx = vectx.replace('k', (char) 0);
			test = vectx.split("[+]");
			x0 = Float.parseFloat(test[0]);
			a = Float.parseFloat(test[1]);
		} else if (vectx.matches("^x={1}[-]?[0-9]+([.]{1}[0-9]+)?[-]{1}[0-9]+([.]{1}[0-9]+)?k{1}$")) {
			vectx = vectx.replaceAll("x=", "");
			vectx = vectx.replace('k', (char) 0);
			test = vectx.split("[-]");
			if (test.length == 2) {
				x0 = Float.parseFloat(test[0]);
				a = -1 * Float.parseFloat(test[1]);
			}
			if (test.length == 3) {
				x0 = -1 * Float.parseFloat(test[1]);
				a = -1 * Float.parseFloat(test[2]);
			}
		} else {
			System.out.println("Equação Incorreta");

			return;
		}

		if (vecty.matches("^y={1}[-]?[0-9]+([.]{1}[0-9]+)?[+]{1}[0-9]+([.]{1}[0-9]+)?k{1}$")) {
			vecty = vecty.replaceAll("y=", "");
			vecty = vecty.replace('k', (char) 0);
			test = vecty.split("[+]");
			y0 = Float.parseFloat(test[0]);
			b = Float.parseFloat(test[1]);
		} else if (vecty.matches("^y={1}[-]?[0-9]+([.]{1}[0-9]+)?[-]{1}[0-9]+([.]{1}[0-9]+)?k{1}$")) {
			vecty = vecty.replaceAll("y=", "");
			vecty = vecty.replace('k', (char) 0);
			test = vecty.split("[-]");
			if (test.length == 2) {
				y0 = Float.parseFloat(test[0]);
				b = -1 * Float.parseFloat(test[1]);
			}
			if (test.length == 3) {
				y0 = -1 * Float.parseFloat(test[1]);
				b = -1 * Float.parseFloat(test[2]);
			}
		} else {
			System.out.println("Equação Incorreta");

			return;
		}

		if (vectz.matches("^z={1}[-]?[0-9]+([.]{1}[0-9]+)?[+]{1}[0-9]+([.]{1}[0-9]+)?k{1}$")) {
			vectz = vectz.replaceAll("z=", "");
			vectz = vectz.replace('k', (char) 0);
			test = vectz.split("[+]");
			z0 = Float.parseFloat(test[0]);
			c = Float.parseFloat(test[1]);
		} else if (vectz.matches("^z={1}[-]?[0-9]+([.]{1}[0-9]+)?[-]{1}[0-9]+([.]{1}[0-9]+)?k{1}$$")) {
			vectz = vectz.replaceAll("z=", "");
			vectz = vectz.replace('k', (char) 0);
			test = vectz.split("[-]");
			if (test.length == 2) {
				z0 = Float.parseFloat(test[0]);
				c = -1 * Float.parseFloat(test[1]);
			}
			if (test.length == 3) {
				z0 = -1 * Float.parseFloat(test[1]);
				c = -1 * Float.parseFloat(test[2]);
			}
		} else {
			System.out.println("Equação Incorreta");

			return;
		}

		op = MenuOutput();
		while (op != '1' || op != '2') {
			if (op == '1') {
				if (ex == 1) {
					convertVectorial(x0, y0, z0, a, b, c);
					convertCartesiana(x0, y0, z0, a, b, c, 1);
					break;
				}
				if (ex == 4) {
					convertpontos(x0, y0, z0, a, b, c);
					break;
				}
				if (ex == 5) {
					calcDist(x0, y0, z0, a, b, c, px0,py0,pz0,opout);
					break;
				}
				if (ex == 8) {
					CSV(x0, y0, z0, a, b, c);
					break;
				}
				break;
			} else if (op == '2') {
				if (ex == 1) {
					Formatter write = new Formatter(new File(ex + ".html"));
					CodificadorHTML.CorpoInicioHTML(write);
					CodificadorHTML.convertParametrica(x0, y0, z0, a, b, c, 1,write);
					CodificadorHTML.convertVectorial(x0, y0, z0, a, b, c, write);
					CodificadorHTML.convertCartesiana(x0, y0, z0, a, b, c, 1,write);
					CodificadorHTML.CorpoFimHTML(write);
					write.close();
					break;
				}
				if (ex == 4) {
					CodificadorHTML.convertpontos(x0, y0, z0, a, b, c, 2);
					break;
				}
				if (ex == 5) {
					CodificadorHTML.calcDist(x0, y0, z0, a, b, c, 2);
					break;
				}
				if (ex == 8) {
					Formatter write = new Formatter(new File(ex + ".html"));
					CodificadorHTML.CorpoInicioHTML(write);
					CodificadorHTML.convertParametrica(x0, y0, z0, a, b, c, 1,write);
					CodificadorHTML.CSV(x0, y0, z0, a, b, c, write);
					CodificadorHTML.CorpoFimHTML(write);
					write.close();
					break;
				}
				break;
			} else {
				System.out.println("Operação não válida! Escolha novamente!");
				op = MenuOutput();
			}

		}

		return;
	}

	private static void cartesiana(int ex) throws FileNotFoundException {
		float x0 = 0, y0 = 0, z0 = 0, a = 0, b = 0, c = 0, px0=0,py0=0,pz0=0 ;
		String vect = "";
		String[] subvect, subvectdiv;
		int op, opout;

		opout = MenuInput();
		if (opout == 1) {
			System.out.println("Insira a equação, sem colocar espaços.");
			System.out.println("Insira sob a máscara: (x-x0)/a=(y-y0)/b=(z-z0)/c");
			vect = in.next();
			vect = vect.replace(' ', (char) 0);
		}
		if (opout == 2) {
			vect = DescodificadorHTML.eqremov(9, ex);
			if (ex==5){
				px0=DescodificadorHTML.prem(10, 5, 0);
				py0=DescodificadorHTML.prem(10, 5, 1);
				pz0=DescodificadorHTML.prem(10, 5, 2);
			}
		}

		if (vect.matches("^\\(x{1}[-|+]{1}[0-9]+([.]{1}[0-9]+)?\\)/[-]?[0-9]+([.]{1}[0-9]+)?=\\(y{1}[-|+]{1}[0-9]+([.]{1}[0-9]+)?\\)/[-]?[0-9]+([.]{1}[0-9]+)?=\\(z{1}[-|+]{1}[0-9]+([.]{1}[0-9]+)?\\)/[-]?[0-9]+([.]{1}[0-9]+)?$")) {
			subvect = vect.split("=");
			subvectdiv = subvect[0].split("/");
			a = Float.parseFloat(subvectdiv[1]);
			subvectdiv[0] = subvectdiv[0].replaceAll("\\(x", "");
			subvectdiv[0] = subvectdiv[0].replace(')', (char) 0);
			subvectdiv[0] = subvectdiv[0].replaceAll("\\-", "");
			subvectdiv[0] = subvectdiv[0].replaceAll("\\+", "-");
			x0 = Float.parseFloat(subvectdiv[0]);

			subvectdiv = subvect[1].split("/");
			b = Float.parseFloat(subvectdiv[1]);
			subvectdiv[0] = subvectdiv[0].replaceAll("\\(y", "");
			subvectdiv[0] = subvectdiv[0].replace(')', (char) 0);
			subvectdiv[0] = subvectdiv[0].replaceAll("\\-", "");
			subvectdiv[0] = subvectdiv[0].replaceAll("\\+", "-");
			y0 = Float.parseFloat(subvectdiv[0]);

			subvectdiv = subvect[2].split("/");
			c = Float.parseFloat(subvectdiv[1]);
			subvectdiv[0] = subvectdiv[0].replaceAll("\\(z", "");
			subvectdiv[0] = subvectdiv[0].replace(')', (char) 0);
			subvectdiv[0] = subvectdiv[0].replaceAll("\\-", "");
			subvectdiv[0] = subvectdiv[0].replaceAll("\\+", "-");
			z0 = Float.parseFloat(subvectdiv[0]);
		} else {
			System.out.println("Equação Incorreta");
			return;
		}

		op = MenuOutput();
		while (op != '1' || op != '2') {
			if (op == '1') {
				if (ex == 1) {
					convertVectorial(x0, y0, z0, a, b, c);
					convertParametrica(x0, y0, z0, a, b, c, 1);
					break;
				}
				if (ex == 4) {
					convertpontos(x0, y0, z0, a, b, c);
					break;
				}
				if (ex == 5) {
					calcDist(x0, y0, z0, a, b, c, px0, py0, pz0, opout);
					break;
				}
				if (ex == 8) {
					CSV(x0, y0, z0, a, b, c);
					break;
				}
				break;
			} else if (op == '2') {
				if (ex == 1) {
					Formatter write = new Formatter(new File(ex + ".html"));
					CodificadorHTML.CorpoInicioHTML(write);
					CodificadorHTML.convertCartesiana(x0, y0, z0, a, b, c, 1,write);
					CodificadorHTML.convertVectorial(x0, y0, z0, a, b, c, write);
					CodificadorHTML.convertParametrica(x0, y0, z0, a, b, c, 1,write);
					CodificadorHTML.CorpoFimHTML(write);
					write.close();
					break;
				}
				if (ex == 4) {
					CodificadorHTML.convertpontos(x0, y0, z0, a, b, c, 3);
					break;
				}
				if (ex == 5) {
					CodificadorHTML.calcDist(x0, y0, z0, a, b, c, 3);
					break;
				}
				if (ex == 8) {
					Formatter write = new Formatter(new File(ex + ".html"));
					CodificadorHTML.CorpoInicioHTML(write);
					CodificadorHTML.convertCartesiana(x0, y0, z0, a, b, c, 1,write);
					CodificadorHTML.CSV(x0, y0, z0, a, b, c, write);
					CodificadorHTML.CorpoFimHTML(write);
					write.close();
					break;
				}
				break;
			} else {
				System.out.println("Operação não válida! Escolha novamente!");
				op = MenuOutput();
			}
		}

		return;
	}

	private static void convertParametrica(float x0, float y0, float z0,float a, float b, float c, int ex) {

		if (ex == 1) {
			System.out.println("Equação Paramétrica:");
		}
		System.out.print("x=" + x0);
		if (a >= 0) {
			System.out.println("+" + a + "k");
		} else {
			System.out.println(a + "k");
		}
		System.out.print("y=" + y0);
		if (b >= 0) {
			System.out.println("+" + b + "k");
		} else {
			System.out.println(b + "k");
		}
		System.out.print("z=" + z0);
		if (c >= 0) {
			System.out.println("+" + c + "k");
		} else {
			System.out.println(c + "k");
		}

		return;
	}

	private static void convertCartesiana(float x0, float y0, float z0,float a, float b, float c, int ex) {

		if (ex == 1) {
			System.out.println("Equação Cartesiana:");
		}
		if (x0 > 0) {
			System.out.print("(x-" + x0 + ")/" + a + "=");
		} else {
			System.out.print("(x+" + (x0 * -1) + ")/" + a + "=");
		}
		if (y0 > 0) {
			System.out.print("(y-" + y0 + ")/" + b + "=");
		} else {
			System.out.print("(y+" + (y0 * -1) + ")/" + b + "=");
		}
		if (x0 > 0) {
			System.out.print("(z-" + z0 + ")/" + c);
		} else {
			System.out.println("(z+" + (z0 * -1) + ")/" + c);
		}
		if (a == 0 || b == 0 || c == 0) {
			System.out.println("\nNão é a maneira mais correcta de representar!");
		}

		return;
	}

	private static void convertVectorial(float x0, float y0, float z0, float a,float b, float c) {
		System.out.println("Equação Vetorial:");
		System.out.println("(x,y,z)=(" + x0 + "," + y0 + "," + z0 + ")+k(" + a	+ "," + b + "," + c + ")");

		return;
	}

	private static void EquacaoReta() throws FileNotFoundException {
		char opcao, op2;
		String n;
		int op;
		float f, e, d, a, b, c, r1, r2, r3;

		do {
			System.out.println("Métodos de Calculo: ");
			System.out.println("1 - Fornecer dois pontos na recta");
			System.out.println("2 - Fornecer um ponto e um vector");
			System.out.println("3 - Voltar ao menu anterior");
			n = in.next();
		} while (n.length() != 1);
		opcao = n.charAt(0);
		op = MenuInput();
		switch (opcao) {
		case '1':
			if (op == 1) {
				System.out.print("Introduza as coordenadas do ponto A: ");
				System.out.print("1ª:");
				a = in.nextFloat();
				System.out.print("2ª:");
				b = in.nextFloat();
				System.out.print("3ª:");
				c = in.nextFloat();
				System.out.print("Introduza as coordenadas do ponto B: ");
				System.out.print("1ª:");
				d = in.nextFloat();
				System.out.print("2ª:");
				e = in.nextFloat();
				System.out.print("3ª:");
				f = in.nextFloat();
				System.out.println("\n");
				r1 = d - a;
				r2 = e - b;
				r3 = f - c;
				op2 = MenuOutput();
				while (op2 != '1' || op2 != '2') {
					if (op2 == '1') {
						ConverterRetaVectorial(a, b, c, d, e, f, r1, r2, r3);
						ConverterRetaParametrica(a, b, c, r1, r2, r3);
						ConverterRetaCartesiana(a, b, c, r1, r2, r3);
						break;
					} else if (op2 == '2') {
						CodificadorHTML.codificarHTML2(opcao, op2, a, b, c, d,e, f, r1, r2, r3);
						break;
					} else {
						System.out.println("Operação não válida! Escolha novamente!");
						op2 = MenuOutput();
					}
				}
			} else {
				a = DescodificadorHTML.plrem(8, 2, 0, "A");
				b = DescodificadorHTML.plrem(8, 2, 1, "A");
				c = DescodificadorHTML.plrem(8, 2, 2, "A");
				d = DescodificadorHTML.plrem(9, 2, 0, "B");
				e = DescodificadorHTML.plrem(9, 2, 1, "B");
				f = DescodificadorHTML.plrem(9, 2, 2, "B");
				op2 = MenuOutput();
				r1 = d - a;
				r2 = e - b;
				r3 = f - c;
				while (op2 != '1' || op2 != '2') {
					if (op2 == '1') {
						ConverterRetaVectorial(a, b, c, d, e, f, r1, r2, r3);
						ConverterRetaParametrica(a, b, c, r1, r2, r3);
						ConverterRetaCartesiana(a, b, c, r1, r2, r3);
						break;
					} else if (op2 == '2') {
						CodificadorHTML.codificarHTML2(opcao, op2, a, b, c, d,e, f, r1, r2, r3);
						break;
						} else {
							System.out.println("Operação não válida! Escolha novamente!");
							op2 = MenuOutput();
					}
				}
			}
			break;
		case '2':
			if (op == 1) {
				System.out.print("Introduza as coordenadas do ponto A: ");
				System.out.print("1ª:");
				a = in.nextFloat();
				System.out.print("2ª:");
				b = in.nextFloat();
				System.out.print("3ª:");
				c = in.nextFloat();
				System.out.print("Introduza as coordenadas do vetor diretor: ");
				System.out.print("1ª:");
				d = in.nextFloat();
				System.out.print("2ª:");
				e = in.nextFloat();
				System.out.print("3ª:");
				f = in.nextFloat();
				r1 = d;
				r2 = e;
				r3 = f;
				op2 = MenuOutput();
				while (op2 != '1' || op2 != '2') {
					if (op2 == '1') {
						ConverterRetaVetorial(a, b, c, d, e, f, r1, r2, r3);
						ConverterRetaParametrica(a, b, c, r1, r2, r3);
						ConverterRetaCartesiana(a, b, c, r1, r2, r3);
						break;
					} else if (op2 == '2') {
						CodificadorHTML.codificarHTML2(opcao, op2, a, b, c, d,e, f, r1, r2, r3);
						break;
					} else {
						System.out.println("Operação não válida! Escolha novamente!");
						op2 = MenuOutput();
					}
				}
			} else {
				a = DescodificadorHTML.eqrem(8, 2, 0);
				b = DescodificadorHTML.eqrem(8, 2, 1);
				c = DescodificadorHTML.eqrem(8, 2, 2);
				d = DescodificadorHTML.eqrem(8, 2, 3);
				e = DescodificadorHTML.eqrem(8, 2, 4);
				f = DescodificadorHTML.eqrem(8, 2, 5);
			}
			break;
		case '3':
			Menu();
			break;
		default:
			System.out.println("Opção Desconhecida");
			break;
		}

	}

	private static void EquacaoPlano() throws FileNotFoundException {
		char opcao, op2;
		String n;
		String[][] matriz = new String[3][3];
		int op;

		float a, b, c, d, e, f, g, h, i, r1, r2, r3, r4, r5, r6;
		@SuppressWarnings("unused")
		boolean verificar = false;
		
		do {
			System.out.println("Métodos de Calculo: ");
			System.out.println("1 - Fornecer três pontos não colineares");
			System.out.println("2 - Fornecer um ponto e dois vetores não colineares");
			System.out.println("3 - Fornecer dois pontos e um vetor não colineares");
			System.out.println("4 - Voltar ao menu anterior");
			n = in.next();
		} while (n.length() != 1);
		opcao = n.charAt(0);
		op = MenuInput();
		switch (opcao) {
		case '1':
			if (op == 1) {
				do {
					System.out.print("Introduza as coordenadas do ponto A: ");
					System.out.print("1ª:");
					a = in.nextFloat();
					System.out.print("2ª:");
					b = in.nextFloat();
					System.out.print("3ª:");
					c = in.nextFloat();
					System.out.print("Introduza as coordenadas do ponto B: ");
					System.out.print("1ª:");
					d = in.nextFloat();
					System.out.print("2ª:");
					e = in.nextFloat();
					System.out.print("3ª:");
					f = in.nextFloat();
					System.out.print("Introduza as coordenadas do ponto C: ");
					System.out.print("1ª:");
					g = in.nextFloat();
					System.out.print("2ª:");
					h = in.nextFloat();
					System.out.print("3ª:");
					i = in.nextFloat();
					verificar = VerificiarColineares(a, b, c, d, e, f, g, h, i);
				} while (verificar = false);
				matriz = valoresMatriz(matriz, a, b, c, d, e, f, g, h, i);
				op2 = MenuOutput();
				while (op2 != '1' || op2 != '2') {
					if (op2 == '1') {
						System.out.println("Ponto A: (" + a + "," + b + "," + c	+ ")");
						System.out.println("Ponto B: (" + d + "," + e + "," + f	+ ")");
						System.out.println("Ponto C: (" + g + "," + h + "," + i	+ ")");
						System.out.println("\n");
						r1 = a - d;
						r2 = b - e;
						r3 = c - f;
						r4 = d - g;
						r5 = e - h;
						r6 = f - i;
						System.out.print("Vetor criado a partir do Ponto A e B: ");
						System.out.println("u = (" + r1 + "," + r2 + "," + r3 + ")");
						System.out.print("Vetor criado a partir do Ponto B e C: ");
						System.out.println("v = (" + r4 + "," + r5 + "," + r6 + ")");
						System.out.println("\n");
						ConverterPlanoVetorial(a, b, c, r1, r2, r3, r4, r5, r6);
						ConverterPlanoParametrica(a, b, c, r1, r2, r3, r4, r5, r6);
						ConverterPlanoCartesiana(a, b, c, r1, r2, r3, r4, r5,r6);
						break;
					} else if (op2 == '2') {
						r1 = a - d;
						r2 = b - e;
						r3 = c - f;
						r4 = d - g;
						r5 = e - h;
						r6 = f - i;
						CodificadorHTML.codificarHTML3(opcao, op2, a, b, c, d,e, f, g, h, i, r1, r2, r3, r4, r5, r6, matriz);
						break;
					} else {
						System.out.println("Opção não válida! Escolha novamente");
						op2 = MenuOutput();
					}
				}
			} else {
				a = DescodificadorHTML.plrem(7, 3, 0, "A");
				b = DescodificadorHTML.plrem(7, 3, 1, "A");
				c = DescodificadorHTML.plrem(7, 3, 2, "A");
				d = DescodificadorHTML.plrem(8, 3, 0, "B");
				e = DescodificadorHTML.plrem(8, 3, 1, "B");
				f = DescodificadorHTML.plrem(8, 3, 2, "B");
				g = DescodificadorHTML.plrem(9, 3, 0, "C");
				h = DescodificadorHTML.plrem(9, 3, 1, "C");
				i = DescodificadorHTML.plrem(9, 3, 2, "C");
				matriz = valoresMatriz(matriz, a, b, c, d, e, f, g, h, i);
				op2 = MenuOutput();
				while (op2 != '1' || op2 != '2') {
					if (op2 == '1') {
						System.out.println("Ponto A: (" + a + "," + b + "," + c	+ ")");
						System.out.println("Ponto B: (" + d + "," + e + "," + f	+ ")");
						System.out.println("Ponto C: (" + g + "," + h + "," + i	+ ")");
						System.out.println("\n");
						r1 = a - d;
						r2 = b - e;
						r3 = c - f;
						r4 = d - g;
						r5 = e - h;
						r6 = f - i;
						System.out.print("Vetor criado a partir do Ponto A e B: ");
						System.out.println("u = (" + r1 + "," + r2 + "," + r3 + ")");
						System.out.print("Vetor criado a partir do Ponto B e C: ");
						System.out.println("v = (" + r4 + "," + r5 + "," + r6 + ")");
						System.out.println("\n");
						ConverterPlanoVetorial(a, b, c, r1, r2, r3, r4, r5, r6);
						ConverterPlanoParametrica(a, b, c, r1, r2, r3, r4, r5,r6);
						ConverterPlanoCartesiana(a, b, c, r1, r2, r3, r4, r5,r6);
						break;
					} else if (op2 == '2') {
						r1 = a - d;
						r2 = b - e;
						r3 = c - f;
						r4 = d - g;
						r5 = e - h;
						r6 = f - i;
						CodificadorHTML.codificarHTML3(opcao, op2, a, b, c, d,e, f, g, h, i, r1, r2, r3, r4, r5, r6, matriz);
						break;
					} else {
						System.out.println("Opção não válida! Escolha novamente");
						op2 = MenuOutput();
					}
				}

			}
			break;
		case '2':
			if (op == 1) {
				do {
					System.out.print("Introduza as coordenadas do ponto A: ");
					System.out.print("1ª:");
					a = in.nextFloat();
					System.out.print("2ª:");
					b = in.nextFloat();
					System.out.print("3ª:");
					c = in.nextFloat();
					System.out.print("Introduza as coordenadas do vetor B: ");
					System.out.print("1ª:");
					d = in.nextFloat();
					System.out.print("2ª:");
					e = in.nextFloat();
					System.out.print("3ª:");
					f = in.nextFloat();
					System.out.print("Introduza as coordenadas do vetor C: ");
					System.out.print("1ª:");
					g = in.nextFloat();
					System.out.print("2ª:");
					h = in.nextFloat();
					System.out.print("3ª:");
					i = in.nextFloat();
					verificar = VerificiarColineares(a, b, c, d, e, f, g, h, i);
				} while (verificar = false);
				matriz = valoresMatriz(matriz, a, b, c, d, e, f, g, h, i);
				op2 = MenuOutput();
				while (op2 != '1' || op2 != '2') {
					if (op2 == '1') {
						System.out.println("Ponto A: (" + a + "," + b + "," + c	+ ")");
						System.out.println("Vetor B: (" + d + "," + e + "," + f	+ ")");
						System.out.println("Vetor C: (" + g + "," + h + "," + i + ")");
						ConverterPlanoVetorial(a, b, c, d, e, f, g, h, i);
						ConverterPlanoParametrica(a, b, c, d, e, f, g, h, i);
						ConverterPlanoCartesiana(a, b, c, d, e, f, g, h, i);
						break;
					} else if (op2 == '2') {
						r1 = a - d;
						r2 = b - e;
						r3 = c - f;
						r4 = d - g;
						r5 = e - h;
						r6 = f - i;
						CodificadorHTML.codificarHTML3(opcao, op2, a, b, c, d,e, f, g, h, i, r1, r2, r3, r4, r5, r6, matriz);
						break;
					} else {
						System.out.println("Opção Inválida! Escolha novamente!");
						op2 = MenuOutput();
					}
				}
			} else {
				a = DescodificadorHTML.plrem(7, 3, 0, "A");
				b = DescodificadorHTML.plrem(7, 3, 1, "A");
				c = DescodificadorHTML.plrem(7, 3, 2, "A");
				d = DescodificadorHTML.vlrem(8, 3, 0, "B");
				e = DescodificadorHTML.vlrem(8, 3, 1, "B");
				f = DescodificadorHTML.vlrem(8, 3, 2, "B");
				g = DescodificadorHTML.vlrem(8, 3, 0, "C");
				h = DescodificadorHTML.vlrem(8, 3, 1, "C");
				i = DescodificadorHTML.vlrem(8, 3, 2, "C");
				matriz = valoresMatriz(matriz, a, b, c, d, e, f, g, h, i);
				op2 = MenuOutput();
				while (op2 != '1' || op2 != '2') {
					if (op2 == '1') {
						System.out.println("Ponto A: (" + a + "," + b + "," + c	+ ")");
						System.out.println("Ponto B: (" + d + "," + e + "," + f	+ ")");
						System.out.println("Ponto C: (" + g + "," + h + "," + i	+ ")");
						System.out.println("\n");
						r1 = a - d;
						r2 = b - e;
						r3 = c - f;
						r4 = d - g;
						r5 = e - h;
						r6 = f - i;
						System.out.print("Vetor criado a partir do Ponto A e B: ");
						System.out.println("u = (" + r1 + "," + r2 + "," + r3 + ")");
						System.out.print("Vetor criado a partir do Ponto B e C: ");
						System.out.println("v = (" + r4 + "," + r5 + "," + r6 + ")");
						System.out.println("\n");
						ConverterPlanoVetorial(a, b, c, r1, r2, r3, r4, r5, r6);
						ConverterPlanoParametrica(a, b, c, r1, r2, r3, r4, r5,r6);
						ConverterPlanoCartesiana(a, b, c, r1, r2, r3, r4, r5,r6);
						break;
					} else if (op2 == '2') {
						r1 = a - d;
						r2 = b - e;
						r3 = c - f;
						r4 = d - g;
						r5 = e - h;
						r6 = f - i;
						CodificadorHTML.codificarHTML3(opcao, op2, a, b, c, d,e, f, g, h, i, r1, r2, r3, r4, r5, r6, matriz);
						break;
					} else {
						System.out.println("Opção não válida! Escolha novamente");
						op2 = MenuOutput();
					}
				}
			}
			break;
		case '3':
			if (op == 1) {
				do {
					System.out.print("Introduza as coordenadas do ponto A: ");
					System.out.print("1ª:");
					a = in.nextFloat();
					System.out.print("2ª:");
					b = in.nextFloat();
					System.out.print("3ª:");
					c = in.nextFloat();
					System.out.print("Introduza as coordenadas do ponto B: ");
					System.out.print("1ª:");
					d = in.nextFloat();
					System.out.print("2ª:");
					e = in.nextFloat();
					System.out.print("3ª:");
					f = in.nextFloat();
					System.out.print("Introduza as coordenadas do vetor C: ");
					System.out.print("1ª:");
					g = in.nextFloat();
					System.out.print("2ª:");
					h = in.nextFloat();
					System.out.print("3ª:");
					i = in.nextFloat();
					verificar = VerificiarColineares(a, b, c, d, e, f, g, h, i);
				} while (verificar = false);
				matriz = valoresMatriz(matriz, a, b, c, d, e, f, g, h, i);
				op2 = MenuOutput();
				while (op2 != '1' || op2 != '2') {
					if (op2 == '1') {
						System.out.println("Ponto A: (" + a + "," + b + "," + c	+ ")");
						System.out.println("Ponto B: (" + d + "," + e + "," + f	+ ")");
						System.out.println("Vetor C: (" + g + "," + h + "," + i	+ ")");
						System.out.println("\n");
						r1 = a - d;
						r2 = b - e;
						r3 = c - f;
						System.out.print("Vetor criado a partir do ponto A e B: ");
						System.out.println("u = (" + r1 + "," + r2 + "," + r3 + ")");
						ConverterPlanoVetorial(a, b, c, g, h, i, r1, r2, r3);
						ConverterPlanoParametrica(a, b, c, g, h, i, r1, r2, r3);
						ConverterPlanoCartesiana(a, b, c, g, h, i, r1, r2, r3);
						break;
					} else if (op2 == '2') {
						r1 = a - d;
						r2 = b - e;
						r3 = c - f;
						r4 = d - g;
						r5 = e - h;
						r6 = f - i;
						CodificadorHTML.codificarHTML3(opcao, op2, a, b, c, d, e, f, g, h, i, r1, r2, r3, r4, r5, r6, matriz);
						break;
					} else {
						System.out.println("Opção não válida! Escolha novamente!");
						op2 = MenuOutput();
					}
				}
			} else {
				a = DescodificadorHTML.plrem(7, 3, 0, "A");
				b = DescodificadorHTML.plrem(7, 3, 1, "A");
				c = DescodificadorHTML.plrem(7, 3, 2, "A");
				d = DescodificadorHTML.plrem(8, 3, 0, "B");
				e = DescodificadorHTML.plrem(8, 3, 1, "B");
				f = DescodificadorHTML.plrem(8, 3, 2, "B");
				g = DescodificadorHTML.vlrem(8, 3, 0, "C");
				h = DescodificadorHTML.vlrem(8, 3, 1, "C");
				i = DescodificadorHTML.vlrem(8, 3, 2, "C");
				matriz = valoresMatriz(matriz, a, b, c, d, e, f, g, h, i);
				op2 = MenuOutput();
				while (op2 != '1' || op2 != '2') {
					if (op2 == '1') {
						System.out.println("Ponto A: (" + a + "," + b + "," + c	+ ")");
						System.out.println("Ponto B: (" + d + "," + e + "," + f	+ ")");
						System.out.println("Ponto C: (" + g + "," + h + "," + i	+ ")");
						System.out.println("\n");
						r1 = a - d;
						r2 = b - e;
						r3 = c - f;
						r4 = d - g;
						r5 = e - h;
						r6 = f - i;
						System.out.print("Vetor criado a partir do Ponto A e B: ");
						System.out.println("u = (" + r1 + "," + r2 + "," + r3 + ")");
						System.out.print("Vetor criado a partir do Ponto B e C: ");
						System.out.println("v = (" + r4 + "," + r5 + "," + r6 + ")");
						System.out.println("\n");
						ConverterPlanoVetorial(a, b, c, r1, r2, r3, r4, r5, r6);
						ConverterPlanoParametrica(a, b, c, r1, r2, r3, r4, r5, r6);
						ConverterPlanoCartesiana(a, b, c, r1, r2, r3, r4, r5, r6);
						break;
					} else if (op2 == '2') {
						r1 = a - d;
						r2 = b - e;
						r3 = c - f;
						r4 = d - g;
						r5 = e - h;
						r6 = f - i;
						CodificadorHTML.codificarHTML3(opcao, op2, a, b, c, d, e, f, g, h, i, r1, r2, r3, r4, r5, r6, matriz);
						break;
					} else {
						System.out.println("Opção não válida! Escolha novamente");
						op2 = MenuOutput();
					}
				}
			}
			break;
		case '4':
			Menu();
			break;
		}
	}

	private static void ConverterRetaParametrica(float a, float b, float c,	float r1, float r2, float r3) {
		System.out.println("Equação Paramétrica: ");
		if (r1 >= 0) {
			System.out.println("x = " + a + " + " + (r1) + "k");
		} else {
			System.out.println("x = " + a + (r1) + "k");
		}
		if (r2 >= 0) {
			System.out.println("y = " + b + " + " + (r2) + "k");
		} else {
			System.out.println("y = " + b + (r2) + "k");
		}
		if (r3 >= 0) {
			System.out.println("z = " + c + " + " + (r3) + "k");
		} else {
			System.out.println("z = " + c + (r3) + "k");
		}
	}

	private static void ConverterRetaVectorial(float a, float b, float c,float d, float e, float f, float r1, float r2, float r3) {
		System.out.println("Equação Vectorial: ");
		System.out.println("PontoA = (" + a + "," + b + "," + c + ")");
		System.out.println("PontoB = (" + d + "," + e + "," + f + ")");
		System.out.println("u = B - A = (" + d + "," + e + "," + f + ") - (" + a + "," + b + "," + c + ") = (" + (r1) + "," + (r2) + "," + (r3) + ")");
		System.out.print("Recta: ");
		System.out.println("(x,y,z) = (" + a + "," + b + "," + c + ") + k("	+ (r1) + "," + (r2) + "," + (r3) + ")");
	}

	private static void ConverterRetaVetorial(float a, float b, float c, float d, float e, float f, float r1, float r2, float r3) {
		System.out.println("Equação Vectorial: ");
		System.out.println("(x,y,z) = (" + a + "," + b + "," + c + ") + k("	+ (r1) + "," + (r2) + "," + (r3) + ")");
	}

	private static void ConverterRetaCartesiana(float a, float b, float c, float r1, float r2, float r3) {
		System.out.println("Equação Cartesiana: ");
		System.out.println("u = (" + (r1) + "," + (r2) + "," + (r3) + ")");
		System.out.println("P = (" + a + "," + b + "," + c + ")");
		ConverterValores(a, b, c, r1, r2, r3);
		if (r1 == 0 || r2 == 0 || r3 == 0) {
			System.out.println("Não é a forma mais correcta de apresentar!!!");
		}
	}

	private static void ConverterValores(float a, float b, float c, float r1,float r2, float r3) {
		if (a >= 0) {
			System.out.println("X - " + a + "/" + r1);
		} else {
			System.out.println("X + " + a + "/" + r1);
		}
		if (b >= 0) {
			System.out.println("Y - " + b + "/" + r2);
		} else {
			System.out.println("Y + " + b + "/" + r2);
		}
		if (c >= 0) {
			System.out.println("Z - " + c + "/" + r3);
		} else {
			System.out.println("Z + " + c + "/" + r3);
		}
	}

	private static void ConverterPlanoVetorial(float a, float b, float c,float d, float e, float f, float g, float h, float i) {
		System.out.println("Equação vetorial no plano: ");
		System.out.println("a: (x,y,z) = (" + a + "," + b + "," + c + ") + k1("	+ d + "," + e + "," + f + ") + k2(" + g + "," + h + "," + i	+ ")");
	}

	private static void ConverterPlanoParametrica(float a, float b, float c,float d, float e, float f, float g, float h, float i) {
		System.out.println("Equação Paramétrica no plano: ");
		if (d >= 0 && g >= 0) {
			System.out.println("X = " + a + " + " + d + "k1 + " + g + "k2");
		} else if (d < 0 && g >= 0) {
			System.out.println("X = " + a + " " + d + "k1 + " + g + "k2");
		} else if (d < 0 && g < 0) {
			System.out.println("X = " + a + " " + d + "k1 " + g + "k2");
		} else {
			System.out.println("X = " + a + " + " + d + "k1 " + g + "k2");
		}
		if (e >= 0 && h >= 0) {
			System.out.println("Y = " + b + " + " + e + "k1 + " + h + "k2");
		} else if (e < 0 && h >= 0) {
			System.out.println("Y = " + b + " " + e + "k1 + " + h + "k2");
		} else if (e < 0 && h < 0) {
			System.out.println("Y = " + b + " " + e + "k1 " + h + "k2");
		} else {
			System.out.println("Y = " + b + " + " + e + "k1 " + h + "k2");
		}
		if (f >= 0 && i >= 0) {
			System.out.println("Z = " + c + " + " + f + "k1 + " + i + "k2");
		} else if (f < 0 && i >= 0) {
			System.out.println("Z = " + c + " " + f + "k1 + " + i + "k2");
		} else if (f < 0 && i < 0) {
			System.out.println("Z = " + c + " " + f + "k1 " + i + "k2");
		} else {
			System.out.println("Z = " + c + " + " + f + "k1 " + i + "k2");
		}
	}

	private static void ConverterPlanoCartesiana(float a, float b, float c,	float d, float e, float f, float g, float h, float i) {
		float[] num = { d, e, f, g, h, i };
		String[][] matriz = new String[3][3];
		int cont = 0;

		if (a >= 0) {
			matriz[0][0] = "x-" + a;
		} else {
			matriz[0][0] = "x+" + Math.abs(a);
		}
		if (b >= 0) {
			matriz[0][1] = "y-" + b;
		} else {
			matriz[0][1] = "y+" + Math.abs(b);
		}
		if (c >= 0) {
			matriz[0][2] = "z-" + c;
		} else {
			matriz[0][2] = "z+" + Math.abs(c);
		}
		for (int j = 1; j < matriz.length; j++) {
			for (int k = 0; k < matriz[0].length; k++) {
				matriz[j][k] = String.valueOf(num[cont]);
				cont++;
			}
		}
		apresentarCartesiana(matriz);
		System.out.println();
		CalcularCartesiana(matriz, a, b, c);
	}

	public static String mensagem1(int res4, int res5, int z2, int z3, int z8, int z9) {
		String mens1;

		if (res4 >= res5) {
			mens1 = z2 + "s  %" + z3 + "s|";
		} else {
			mens1 = z8 + "s  %" + z9 + "s|";
		}

		return mens1;
	}

	public static String mensagem2(int res6, int res7, int z1, int z3, int z7, int z9) {
		String mens1;

		if (res6 >= res7) {
			mens1 = z1 + "s  %" + z3 + "s|";
		} else {
			mens1 = z7 + "s  %" + z9 + "s|";
		}

		return mens1;
	}

	public static String mensagem3(int res8, int res9, int z1, int z2, int z7,int z8) {
		String mens1;

		if (res8 >= res9) {
			mens1 = z1 + "s  %" + z2 + "s|";
		} else {
			mens1 = z7 + "s  %" + z8 + "s|";
		}

		return mens1;
	}

	public static void apresentarCartesiana(String[][] matriz) {
		float x1, x2, x3;
		int z1, z2, z3, z4, z5, z6, z7, z8, z9, res1, res2, res3, res4, res5, res6, res7, res8, res9, tam1, tam2, tam3;
		String mensa1, mensa2, mensa3;
		String v1, v2, v3, v4, v5, v6, v7, v8, v9, for1, for2, for3;

		x1 = Float.parseFloat(matriz[1][0]);
		x2 = Float.parseFloat(matriz[1][1]);
		x3 = Float.parseFloat(matriz[1][2]);
		System.out.println("Equação Cartesiana no plano: ");
		v1 = matriz[0][0];
		v2 = matriz[0][1];
		v3 = matriz[0][2];
		v4 = matriz[1][0];
		v5 = matriz[1][1];
		v6 = matriz[1][2];
		v7 = matriz[0][2];
		v8 = matriz[1][2];
		v9 = matriz[2][2];
		z1 = v1.length();
		z2 = v2.length();
		z3 = v3.length();
		z4 = v4.length();
		z5 = v5.length();
		z6 = v6.length();
		z7 = v7.length();
		z8 = v8.length();
		z9 = v9.length();
		res1 = z1 + z2 + z3 + 2;
		res2 = z4 + z5 + z6 + 2;
		res3 = z7 + z8 + z9 + 2;
		res4 = z2 + z3 + 1;
		res5 = z8 + z9 + 1;
		res6 = z1 + z3 + 1;
		res7 = z7 + z9 + 1;
		res8 = z1 + z2 + 1;
		res9 = z7 + z8 + 1;
		if (x1 >= 0) {
			for1 = "=0<=>" + matriz[1][0] + "*(-1)^2+1*";
		} else {
			for1 = "=0<=>" + matriz[1][0] + "*(-1)^2+1*";
		}
		if (x2 >= 0) {
			for2 = "+" + matriz[1][1] + "*(-1)^2+1*";
		} else {
			for2 = matriz[1][1] + "*(-1)^2+1*";
		}
		if (x3 >= 0) {
			for3 = "+" + matriz[1][2] + "*(-1)^2+1*";
		} else {
			for3 = matriz[1][2] + "*(-1)^2+1*";
		}
		mensa1 = mensagem1(res4, res5, z2, z3, z8, z9);
		mensa2 = mensagem2(res6, res7, z1, z3, z7, z9);
		mensa3 = mensagem3(res8, res9, z1, z2, z7, z8);
		tam1 = for1.length();
		tam2 = for2.length();
		tam3 = for3.length();
		if (res1 >= res2 && res1 >= res3) {
			String men1 = String.format("|%" + z1 + "s %" + z2 + "s %" + z3	+ "s|%" + tam1 + "s|%" + mensa1 + " %" + tam2 + "s| %" + mensa2 + " %" + tam3 + "s| %" + mensa3, matriz[0][0],matriz[0][1], matriz[0][2], " ", matriz[1][1],matriz[1][2], " ", matriz[1][0], matriz[1][2], " ",	matriz[1][0], matriz[1][1]);
			String men2 = String.format("|%" + z1 + "s %" + z2 + "s %" + z3	+ "s|%" + tam1 + "s|%" + mensa1 + " %" + tam2 + "s| %" + mensa2 + " %" + tam3 + "s| %" + mensa3, matriz[1][0],matriz[1][1], matriz[1][2], for1, " ", " ", for2, " ", " ",for3, " ", " ");
			String men3 = String.format("|%" + z1 + "s %" + z2 + "s %" + z3	+ "s|%" + tam1 + "s|%" + mensa1 + " %" + tam2 + "s| %" + mensa2 + " %" + tam3 + "s| %" + mensa3, matriz[2][0],matriz[2][1], matriz[2][2], " ", matriz[2][1],matriz[2][2], " ", matriz[2][0], matriz[2][2], " ",	matriz[2][0], matriz[2][1]);
			System.out.println(men1 + "\n" + men2 + "\n" + men3);
		} else if (res2 >= res1 && res2 >= res3) {
			String men1 = String.format("|%" + z4 + "s %" + z5 + "s %" + z6	+ "s|%" + tam1 + "s|%" + mensa1 + " %" + tam2 + "s| %" + mensa2 + " %" + tam3 + "s| %" + mensa3, matriz[0][0],matriz[0][1], matriz[0][2], " ", matriz[1][1],matriz[1][2], " ", matriz[1][0], matriz[1][2], " ",matriz[1][0], matriz[1][1]);
			String men2 = String.format("|%" + z4 + "s %" + z5 + "s %" + z6	+ "s|%" + tam1 + "s|%" + mensa1 + " %" + tam2 + "s| %" + mensa2 + " %" + tam3 + "s| %" + mensa3, matriz[1][0],matriz[1][1], matriz[1][2], for1, " ", " ", for2, " ", " ",for3, " ", " ");
			String men3 = String.format("|%" + z4 + "s %" + z5 + "s %" + z6	+ "s|%" + tam1 + "s|%" + mensa1 + " %" + tam2 + "s| %" + mensa2 + " %" + tam3 + "s| %" + mensa3, matriz[2][0],matriz[2][1], matriz[2][2], " ", matriz[2][1],matriz[2][2], " ", matriz[2][0], matriz[2][2], " ",matriz[2][0], matriz[2][1]);
			System.out.println(men1 + "\n" + men2 + "\n" + men3);
		} else if (res3 >= res1 && res3 >= res2) {
			String men1 = String.format("|%" + z7 + "s %" + z8 + "s %" + z9	+ "s|%" + tam1 + "s|%" + mensa1 + " %" + tam2 + "s| %" + mensa2 + " %" + tam3 + "s| %" + mensa3, matriz[0][0],matriz[0][1], matriz[0][2], " ", matriz[1][1],matriz[1][2], " ", matriz[1][0], matriz[1][2], " ",matriz[1][0], matriz[1][1]);
			String men2 = String.format("|%" + z7 + "s %" + z8 + "s %" + z9	+ "s|%" + tam1 + "s|%" + mensa1 + " %" + tam2 + "s| %" + mensa2 + " %" + tam3 + "s| %" + mensa3, matriz[1][0],matriz[1][1], matriz[1][2], for1, " ", " ", for2, " ", " ",for3, " ", " ");
			String men3 = String.format("|%" + z7 + "s %" + z8 + "s %" + z9	+ "s|%" + tam1 + "s|%" + mensa1 + " %" + tam2 + "s| %" + mensa2 + " %" + tam3 + "s| %" + mensa3, matriz[2][0],matriz[2][1], matriz[2][2], " ", matriz[2][1],matriz[2][2], " ", matriz[2][0], matriz[2][2], " ",matriz[2][0], matriz[2][1]);
			System.out.println(men1 + "\n" + men2 + "\n" + men3);
		}
	}

	private static void CalcularCartesiana(String[][] matriz, float p1,float p2, float p3) {
		String v1, v2, v3, v4, v5, v6;
		float res1, res2, res3, val1, val2, val3, val4, val5, val6, resultado1, resultado2, resultado3, resultadoD;

		v1 = matriz[1][0];
		v2 = matriz[1][1];
		v3 = matriz[1][2];
		v4 = matriz[2][0];
		v5 = matriz[2][1];
		v6 = matriz[2][2];
		val1 = Float.parseFloat(v1);
		val2 = Float.parseFloat(v2);
		val3 = Float.parseFloat(v3);
		val4 = Float.parseFloat(v4);
		val5 = Float.parseFloat(v5);
		val6 = Float.parseFloat(v6);
		res1 = (val2 * val6) - (val5 * val3);
		res2 = (val3 * val4) - (val1 * val6);
		res3 = (val1 * val5) - (val2 * val4);
		resultado1 = res1 * p1;
		resultado2 = res2 * p2;
		resultado3 = res3 * p3;
		resultadoD = resultado1 - resultado2 - resultado3;
		System.out.print("Fórmula para calcular Laplace:");
		System.out.println("A(x-x0) + B(y-y0) + C(z-z0)=0");
		System.out.print("Simplificando fica da seguinte forma:");
		System.out.println("Ax + By + Cz - D");
		System.out.println("Em que D é calculado a partir de: Ax0 - By0 - Cz0");
		System.out.println("Fazendo os cálculos obtemos o seguinte resultado:");
		if (resultadoD >= 0) {
			if (res2 >= 0 && res3 >= 0) {
				System.out.println(res1 + "x + " + res2 + "y + " + res3 + "z - " + resultadoD + "=0");
			} else if (res2 >= 0 && res3 < 0) {
				System.out.println(res1 + "x + " + res2 + "y " + res3 + "z - " + resultadoD + "=0");
			} else if (res2 < 0 && res3 >= 0) {
				System.out.println(res1 + "x " + res2 + "y + " + res3 + "z - " + resultadoD + "=0");
			} else {
				System.out.println(res1 + "x " + res2 + "y " + res3 + "z - " + resultadoD + "=0");
			}
		} else {
			if (res2 >= 0 && res3 >= 0) {
				System.out.println(res1 + "x + " + res2 + "y + " + res3	+ "z + " + Math.abs(resultadoD) + "=0");
			} else if (res2 >= 0 && res3 < 0) {
				System.out.println(res1 + "x + " + res2 + "y " + res3 + "z + " + Math.abs(resultadoD) + "=0");
			} else if (res2 < 0 && res3 >= 0) {
				System.out.println(res1 + "x " + res2 + "y + " + res3 + "z + " + Math.abs(resultadoD) + "=0");
			} else {
				System.out.println(res1 + "x " + res2 + "y " + res3 + "z + " + Math.abs(resultadoD) + "=0");
			}
		}
	}

	private static char MenuOutput() {
		char op2;
		String opc2;

		do {
			System.out.println("Escolha o tipo de output:");
			System.out.println("1 - Consola");
			System.out.println("2 - Ficheiro HTML");
			opc2 = in.next();
		} while (opc2.length() != 1);
		op2 = opc2.charAt(0);

		return op2;
	}

	private static int MenuInput() {
		int op = 0;

		do {
			System.out.println("Escolha o tipo de input:");
			System.out.println("1 - Consola");
			System.out.println("2 - Ficheiro HTML");
			op = in.nextInt();
		} while (op != 1 && op != 2);

		return op;
	}

	private static void PontoVetorReta() throws FileNotFoundException {
		String n;
		char op;
		int ex = 4;

		do {
			System.out.println("Escolha o tipo de equação que pretende obter o ponto e o vetor director.");
			System.out.println("1 - Equação Vetorial");
			System.out.println("2 - Equação Paramétrica");
			System.out.println("3 - Equação Cartesiana");
			System.out.println("Insira qualquer outro valor para voltar ao menu anterior");
			n = in.next();
		} while (n.length() != 1);
		op = n.charAt(0);
		switch (op) {
		case '1':
			vectorial(ex);
			break;
		case '2':
			parametrica(ex);
			break;
		case '3':
			cartesiana(ex);
			break;
		default:
			System.out.println("Voltando menu anterior...");
			return;
		}

		return;
	}

	private static void convertpontos(float x0, float y0, float z0, float a, float b, float c) {
		System.out.println("Pontos da reta:(" + x0 + "," + y0 + "," + z0 + ")");
		System.out.println("Vetor diretor:(" + a + "," + b + "," + c + ")");

		return;
	}

	private static void DistanciaPontoReta() throws FileNotFoundException {
		String n;
		char op;
		int ex = 5;

		do {
			System.out.println("Escolha o tipo de equação sobre o qual pretende calcular a distância.");
			System.out.println("1 - Equação Vetorial");
			System.out.println("2 - Equação Paramétrica");
			System.out.println("3 - Equação Cartesiana");
			System.out.println("Insira qualquer outro valor para voltar ao menu anterior");
			n = in.next();
		} while (n.length() != 1);
		op = n.charAt(0);
		switch (op) {
		case '1':
			vectorial(ex);
			break;
		case '2':
			parametrica(ex);
			break;
		case '3':
			cartesiana(ex);
			break;
		default:
			System.out.println("Voltando menu anterior...");
			return;
		}
		return;
	}

	static private void calcDist(float x0, float y0, float z0, float a,float b, float c, float px0, float py0, float pz0, int op) {
		float qx0, qy0, qz0, vx, vy, vz, d, k, dist;

		if (op == 1) {
			System.out.println("Insira as coordenadas do ponto.");
			System.out.print("x=");
			px0 = in.nextFloat();
			System.out.print("y=");
			py0 = in.nextFloat();
			System.out.print("z=");
			pz0 = in.nextFloat();
		}
		System.out.println("Dist=|PQ|");
		System.out.println("Determinar plano que passa por P, perpendicular à reta.");
		System.out.println("Ax+By+Cz+D=0, em que A,B,C é o vetor diretor da reta e x,y,z o ponto P.");
		System.out.println("D=-Ax-By-Cz");
		System.out.print("D=-" + a * -1 + "*(" + px0 + ")");
		if (b > 0) {
			System.out.print(b * -1 + "*(" + py0 + ")");
		} else {
			System.out.print("+" + b * -1 + "*(" + py0 + ")");
		}
		if (c > 0) {
			System.out.print(c * -1 + "*(" + pz0 + ")");
		} else {
			System.out.print("+" + c * -1 + "*(" + pz0 + ")");
		}
		d = -a * px0 - b * py0 - c * pz0;
		System.out.println("D=" + d);
		System.out.println("\nSistema de Equações para determinar K");
		convertplCartesiana(a, b, c, d, 5);
		convertParametrica(x0, y0, z0, a, b, c, 5);
		System.out.println("\nDeterminar K:");
		System.out.println("A(x0+ak)+B(y0+bk)+C(z0+ck)+D=0");
		System.out.println("k=(-Ax0-By0-Cz0-D)/(Aa+Bb+Cc");
		if (a > 0) {
			System.out.print("k=(-" + a + "*(" + x0 + ")");
		} else {
			System.out.print("k=(" + a + "*(" + x0 + ")");
		}
		if (b > 0) {
			System.out.print("-" + b + "*(" + y0 + ")");
		} else {
			System.out.print(b + "*(" + y0 + ")");
		}
		if (c > 0) {
			System.out.print("-" + c + "*(" + z0 + ")");
		} else {
			System.out.print(c + "*(" + z0 + ")");
		}
		if (d > 0) {
			System.out.print("-" + d + ")/(" + a + "*(" + a + ")");
		} else {
			System.out.print(d + ")/(" + a + "*(" + a + ")");
		}
		if (b >= 0) {
			System.out.print("+" + b + "*(" + b + ")");
		} else {
			System.out.print(b + "*(" + b + ")");
		}
		if (c >= 0) {
			System.out.println("+" + c + "*(" + c + "))");
		} else {
			System.out.println(c + "*(" + c + "))");
		}
		k = (-a * x0 - b * y0 - c * z0 - d) / (a * a + b * b + c * c);
		System.out.println("K=" + k);
		System.out.println("\nDeterminar Q");
		System.out.println("x=x0+ka\ny=y0+kb\nz=z0+kc");
		System.out.print("x=" + x0);
		if (k >= 0) {
			System.out.println("+" + k + "*(" + a + ")");
		} else {
			System.out.println(k + "*(" + a + ")");
		}
		System.out.print("y=" + y0);
		if (k >= 0) {
			System.out.println("+" + k + "*(" + b + ")");
		} else {
			System.out.println(k + "*(" + b + ")");
		}
		System.out.print("z=" + z0);
		if (k >= 0) {
			System.out.println("+" + k + "*(" + c + ")");
		} else {
			System.out.println(k + "*(" + c + ")");
		}
		qx0 = x0 + k * a;
		qy0 = y0 + k * b;
		qz0 = z0 + k * c;
		System.out.println("x=" + qx0);
		System.out.println("y=" + qy0);
		System.out.println("z=" + qz0);
		System.out.println("\nDeterminar o vetor PQ");
		System.out.println("PQ=Q-P");
		System.out.println("PQ=(xq,yq,zq)-(xp,yp,zp)");
		System.out.println("PQ=(xq-xp,yq-yp,zq-zp)");
		System.out.print("PQ=(" + qx0);
		if (px0 < 0) {
			System.out.print("+" + px0 * -1 + "," + qy0);
		} else {
			System.out.print(px0 * -1 + "," + qy0);
		}
		if (py0 < 0) {
			System.out.print("+" + py0 * -1 + "," + qz0);
		} else {
			System.out.print(py0 * -1 + "," + qz0);
		}
		if (pz0 < 0) {
			System.out.println("+" + pz0 * -1 + ")");
		} else {
			System.out.println(pz0 * -1 + ")");
		}
		vx = qx0 - px0;
		vy = qy0 - py0;
		vz = qz0 - pz0;
		System.out.println("PQ=(" + vx + "," + vy + "," + vz + ")");
		System.out.println("\nMódulo do vetor PQ:");
		System.out.println("|PQ|=v(x²+y²+z²)");
		System.out.println("|PQ|=v((" + vx + ")²+(" + vy + ")²+(" + vz + ")²)");
		dist = (float) Math.sqrt(vx * vx + vy * vy + vz * vz);
		System.out.println("\n\nA distância é de: " + dist + ".");

		return;
	}

	private static void RepresentarPlano() throws FileNotFoundException {
		String n;
		char op;
		int ex = 6;

		do {
			System.out.println("Escolha o tipo de equação que pretende transformar.");
			System.out.println("1 - Equação Vetorial");
			System.out.println("2 - Equação Paramétrica");
			System.out.println("3 - Equação Cartesiana");
			System.out.println("Insira qualquer outro valor para voltar ao menu anterior");
			n = in.next();
		} while (n.length() != 1);
		op = n.charAt(0);
		switch (op) {
		case '1':
			plvectorial(ex);
			break;
		case '2':
			plparametrica(ex);
			break;
		case '3':
			plcartesiana(ex);
			break;
		default:
			System.out.println("Voltando menu anterior...");
			return;
		}

		return;
	}

	private static void plvectorial(int ex) throws FileNotFoundException{
    	float x0=0,y0=0,z0=0,a=0,b=0,c=0,a1=0,b1=0,c1=0,px0=0,py0=0,pz0=0;
    	String vect="";
    	String [] test;
    	int op,opout;
    	
    	opout=MenuInput();
    	if (opout==1){
    		System.out.println("Insira a equação, sem colocar espaços.");
        	System.out.println("Insira sob a máscara: (x,y,z)=(x0,y0,z0)+k(a,b,c)+k1(a1,b1,c1)");
        	vect=in.next();
        	vect=vect.replace(' ', (char) 0);
	    }
	    if (opout==2){
	    	vect=DescodificadorHTML.eqremov(9,ex);
	    	if (ex==7){
		    	px0=DescodificadorHTML.prem(10,ex,0);
		    	py0=DescodificadorHTML.prem(10,ex,1);
		    	pz0=DescodificadorHTML.prem(10,ex,2);}
	    }
    	if(vect.matches("^\\(x,y,z\\)=\\({1}[-]?[0-9]+([.]{1}[0-9]+)?,{1}[-]?[0-9]+([.]{1}[0-9]+)?,{1}[-]?[0-9]+([.]{1}[0-9]+)?\\)\\+k\\({1}[-]?[0-9]+([.]{1}[0-9]+)?,{1}[-]?[0-9]+([.]{1}[0-9]+)?,{1}[-]?[0-9]+([.]{1}[0-9]+)?\\){1}\\+k1\\({1}[-]?[0-9]+([.]{1}[0-9]+)?,{1}[-]?[0-9]+([.]{1}[0-9]+)?,{1}[-]?[0-9]+([.]{1}[0-9]+)?\\){1}$")){
    		vect=vect.replaceAll("\\(x,y,z\\)=\\(", "");
    		vect=vect.replaceAll("\\)\\+k\\(", ",");
    		vect=vect.replaceAll("\\)\\+k1\\(", ",");
    		vect=vect.replaceAll("\\)", "");
    		test=vect.split(",");
    		x0=Float.parseFloat(test[0]);
    		y0=Float.parseFloat(test[1]);
    		z0=Float.parseFloat(test[2]);
    		a=Float.parseFloat(test[3]);
    		b=Float.parseFloat(test[4]);
    		c=Float.parseFloat(test[5]);
    		a1=Float.parseFloat(test[6]);
    		b1=Float.parseFloat(test[7]);
    		c1=Float.parseFloat(test[8]); 		
    	}
    	else{
    		System.out.println("Equação Incorreta!");
    		
    		return;    		
    	}
    	op = MenuOutput();
    	while (op != '1' || op != '2') {
            if (op == '1') {
            	if (ex==6){
            		convertplParametrica(x0,y0,z0,a,b,c,a1,b1,c1);
            		calcplCartesiana(x0,y0,z0,a,b,c,a1,b1,c1,ex,opout,px0,py0,pz0);
            		break;}
            	if (ex==7){
            		calcplCartesiana(x0,y0,z0,a,b,c,a1,b1,c1,ex,opout,px0,py0,pz0);
            		break;}
            	if(ex==9){
            		calcplCartesiana(x0,y0,z0,a,b,c,a1,b1,c1,ex,opout,px0,py0,pz0);
            		break;}
                break;
            } else if (op == '2') {
            	if (ex==6){
            		Formatter write = new Formatter(new File(ex+".html"));
            		CodificadorHTML.CorpoInicioHTML(write);
            		CodificadorHTML.convertplVectorial(x0, y0, z0, a,b,c,a1, b1, c1, write);
    	    		CodificadorHTML.convertplParametrica(x0,y0,z0,a,b,c,a1,b1,c1,write);
            		CodificadorHTML.calcplCartesiana(x0,y0,z0,a,b,c,a1,b1,c1,ex,write);
    	    		CodificadorHTML.CorpoFimHTML(write);
    	        	write.close();            		
            		break;}
            	if (ex==7){
            		Formatter write = new Formatter(new File(ex+".html"));
            		CodificadorHTML.CorpoInicioHTML(write);
            		CodificadorHTML.convertplVectorial(x0, y0, z0, a,b,c,a1, b1, c1, write);
            		CodificadorHTML.calcplCartesiana(x0,y0,z0,a,b,c,a1,b1,c1,ex,write);
            		CodificadorHTML.CorpoFimHTML(write);
                	write.close();
            		break;}
            	if(ex==9){
            		Formatter write = new Formatter(new File(ex+".html"));
            		CodificadorHTML.CorpoInicioHTML(write);
            		CodificadorHTML.convertplVectorial(x0, y0, z0, a,b,c,a1, b1, c1, write);
            		CodificadorHTML.calcplCartesiana(x0,y0,z0,a,b,c,a1,b1,c1,ex,write);
            		CodificadorHTML.CorpoFimHTML(write);
    	        	write.close();
            		break;}        	    		
                break;
            } else {
                System.out.println("Operação não válida! Escolha novamente!");
                op = MenuOutput();
            }   		
    	}
    	   		
    	return;
    }
    
    private static void plparametrica(int ex) throws FileNotFoundException{
    	float x0=0,y0=0,z0=0,a=0,b=0,c=0,a1=0,b1=0,c1=0,px0=0,py0=0,pz0=0;
    	String vectx="",vecty="",vectz="";
    	String [] test;
    	int op,opout;
    	
    	opout=MenuInput();
    	if (opout==1){
    		System.out.println("Insira a equação dos XX, sem colocar espaços.");
        	System.out.println("Insira sob a máscara: x=x0+ak+a1k1");
        	vectx=in.next();
        	vectx=vectx.replace(' ', (char) 0);
        	System.out.println("Insira a equação dos YY, sem colocar espaços.");
        	System.out.println("Insira sob a máscara: y=y0+bk+b1k1");
        	vecty=in.next();
        	vecty=vecty.replace(' ', (char) 0);
        	System.out.println("Insira a equação dos ZZ, sem colocar espaços.");
        	System.out.println("Insira sob a máscara: z=z0+ck+c1k1");
        	vectz=in.next();
        	vectz=vectz.replace(' ', (char) 0);
	    }
	    if (opout==2){
	    	vectx=DescodificadorHTML.eqremov(9,ex);
	    	vecty=DescodificadorHTML.eqremov(10,ex);
	    	vectz=DescodificadorHTML.eqremov(11,ex);
	    	if (ex==5){
		    	px0=DescodificadorHTML.prem(12,ex,0);
		    	py0=DescodificadorHTML.prem(12,ex,1);
		    	pz0=DescodificadorHTML.prem(12,ex,2);}
	    }
        	if (vectx.matches("^x={1}[-]?[0-9]+([.]{1}[0-9]+)?[+|-]{1}[0-9]+([.]{1}[0-9]+)?k{1}[+|-]{1}[0-9]+([.]{1}[0-9]+)?k1{1}$")){
    		vectx=vectx.replaceAll("x=","");
			vectx=vectx.replaceAll("k1", "");
			vectx=vectx.replace('k', (char) 0);
			vectx=vectx.replaceAll("\\+", ",");
			vectx=vectx.replaceAll("\\-", ",-");
			test=vectx.split(",");
			if(test.length==3){
				x0=Float.parseFloat(test[0]);
				a=Float.parseFloat(test[1]);
				a1=Float.parseFloat(test[2]);}
			if(test.length==4){
				x0=Float.parseFloat(test[1]);
				a=Float.parseFloat(test[2]);
				a1=Float.parseFloat(test[3]);}
    	}
    	else{
    		System.out.println("Equação Incorreta!");
    		
    		return;    		
    	}
    	
    	if (vecty.matches("^y={1}[-]?[0-9]+([.]{1}[0-9]+)?[+|-]{1}[0-9]+([.]{1}[0-9]+)?k{1}[+|-]{1}[0-9]+([.]{1}[0-9]+)?k1{1}$")){
    		vecty=vecty.replaceAll("y=","");
			vecty=vecty.replaceAll("k1", "");
			vecty=vecty.replace('k', (char) 0);
			vecty=vecty.replaceAll("\\+", ",");
			vecty=vecty.replaceAll("\\-", ",-");
			test=vecty.split(",");
			if(test.length==3){
				y0=Float.parseFloat(test[0]);
				b=Float.parseFloat(test[1]);
				b1=Float.parseFloat(test[2]);}
			if(test.length==4){
				y0=Float.parseFloat(test[1]);
				b=Float.parseFloat(test[2]);
				b1=Float.parseFloat(test[3]);}
    	}
    	else{
    		System.out.println("Equação Incorreta!");
    		
    		return;    		
    	}    	    	
    	if (vectz.matches("^z={1}[-]?[0-9]+([.]{1}[0-9]+)?[+|-]{1}[0-9]+([.]{1}[0-9]+)?k{1}[+|-]{1}[0-9]+([.]{1}[0-9]+)?k1{1}$")){
    		vectz=vectz.replaceAll("z=","");
			vectz=vectz.replaceAll("k1", "");
			vectz=vectz.replace('k', (char) 0);
			vectz=vectz.replaceAll("\\+", ",");
			vectz=vectz.replaceAll("\\-", ",-");
			test=vectz.split(",");
			if(test.length==3){
				z0=Float.parseFloat(test[0]);
				c=Float.parseFloat(test[1]);
				c1=Float.parseFloat(test[2]);}
			if(test.length==4){
				z0=Float.parseFloat(test[1]);
				c=Float.parseFloat(test[2]);
				c1=Float.parseFloat(test[3]);}
    	}
    	else{
    		System.out.println("Equação Incorreta!");
    		
    		return;    		
    	}    	
    	op = MenuOutput();
    	while (op != '1' || op != '2') {
            if (op == '1') {
            	if (ex==6){
            		convertplVectorial(x0,y0,z0,a,b,c,a1,b1,c1);
            		calcplCartesiana(x0,y0,z0,a,b,c,a1,b1,c1,ex,opout,px0,py0,pz0);
            		break;}
            	if (ex==7){
            		calcplCartesiana(x0,y0,z0,a,b,c,a1,b1,c1,ex,opout,px0,py0,pz0);
            		break;}
            	if(ex==9){
            		calcplCartesiana(x0,y0,z0,a,b,c,a1,b1,c1,ex,opout,px0,py0,pz0);
            		break;}
                break;
            } else if (op == '2') {
            	if (ex==6){
            		Formatter write = new Formatter(new File(ex+".html"));
            		CodificadorHTML.CorpoInicioHTML(write);
            		CodificadorHTML.convertplParametrica(x0,y0,z0,a,b,c,a1,b1,c1,write);
    	    		CodificadorHTML.convertplVectorial(x0,y0,z0,a,b,c,a1,b1,c1,write);
            		CodificadorHTML.calcplCartesiana(x0,y0,z0,a,b,c,a1,b1,c1,ex,write);
    	    		CodificadorHTML.CorpoFimHTML(write);
    	        	write.close();            		
            		break;}
            	if (ex==7){
            		Formatter write = new Formatter(new File(ex+".html"));
            		CodificadorHTML.CorpoInicioHTML(write);
            		CodificadorHTML.convertplParametrica(x0,y0,z0,a,b,c,a1,b1,c1,write);
            		CodificadorHTML.calcplCartesiana(x0,y0,z0,a,b,c,a1,b1,c1,ex,write);
            		CodificadorHTML.CorpoFimHTML(write);
                	write.close();
            		break;}
            	if(ex==9){
            		Formatter write = new Formatter(new File(ex+".html"));
            		CodificadorHTML.CorpoInicioHTML(write);
            		CodificadorHTML.convertplParametrica(x0,y0,z0,a,b,c,a1,b1,c1,write);
            		CodificadorHTML.calcplCartesiana(x0,y0,z0,a,b,c,a1,b1,c1,ex,write);
            		CodificadorHTML.CorpoFimHTML(write);
    	        	write.close();
            		break;}        	    		
                break;
            } else {
                System.out.println("Operação não válida! Escolha novamente!");
                op = MenuOutput();
            }   		
    	}
    	    	    	
    	return;
    }

    private static void plcartesiana(int ex) throws FileNotFoundException{
    	float a,b,c,d,px0=0,py0=0,pz0=0;
    	String vect="";
    	String [] test;
    	int op,opout;
    	
    	opout=MenuInput();
    	if (opout==1){
    		System.out.println("Insira a equação, sem colocar espaços.");
        	System.out.println("Insira sob a máscara: Ax+By+Cz+D=0");
        	vect=in.next();
        	vect=vect.replace(' ', (char) 0);
	    }
	    if (opout==2){
	    	vect=DescodificadorHTML.eqremov(9,ex);
	    	if (ex==5){
		    	px0=DescodificadorHTML.prem(10,ex,0);
		    	py0=DescodificadorHTML.prem(10,ex,1);
		    	pz0=DescodificadorHTML.prem(10,ex,2);}
	    }    	    	
    	if (vect.matches("^[-|+]{0,1}[0-9]+([.]{1}[0-9]+)?x{1}[-|+]{1}[0-9]+([.]{1}[0-9]+)?y{1}[-|+]{1}[0-9]+([.]{1}[0-9]+)?z{1}[-|+]{1}[0-9]+([.]{1}[0-9]+)?=0{1}$")){
    		vect=vect.replaceAll("x", "");
    		vect=vect.replaceAll("y", "");
    		vect=vect.replaceAll("z", "");
    		vect=vect.replaceAll("=0", "");
    		vect=vect.replaceAll("\\+", ",");
    		vect=vect.replaceAll("\\-", "\\,-");
    		test=vect.split(",");
    		a=Float.parseFloat(test[0]);
    		b=Float.parseFloat(test[1]);
    		c=Float.parseFloat(test[2]);
    		d=Float.parseFloat(test[3]);
    	}
    	else{
    		System.out.println("Equação não válida.");
    		
    		return;}    	
    	op = MenuOutput();
    	while (op != '1' || op != '2') {
            if (op == '1') {
            	if (ex==6){
            		calcInvplCartesiana(a,b,c,d);
            		break;}
            	if (ex==7){
            		calcplDist(a,b,c,d,opout,px0,py0,pz0);
            		break;}
            	if(ex==9){
            		plCSV(a,b,c,d);
            		break;}
                break;
            } else if (op == '2') {
            	if (ex==6){
            		Formatter write = new Formatter(new File(ex+".html"));
            		CodificadorHTML.CorpoInicioHTML(write);
            		CodificadorHTML.convertplCartesiana(a,b,c,d,6,write);
            		CodificadorHTML.calcInvplCartesiana(a,b,c,d,write);
    	    		CodificadorHTML.CorpoFimHTML(write);
    	        	write.close();            		
            		break;}
            	if (ex==7){
            		Formatter write = new Formatter(new File(ex+".html"));
            		CodificadorHTML.CorpoInicioHTML(write);
            		CodificadorHTML.convertplCartesiana(a,b,c,d,6,write);
            		CodificadorHTML.calcplDist(a,b,c,d,write);
            		CodificadorHTML.CorpoFimHTML(write);
                	write.close();
            		break;}
            	if(ex==9){
            		Formatter write = new Formatter(new File(ex+".html"));
            		CodificadorHTML.CorpoInicioHTML(write);
            		CodificadorHTML.convertplCartesiana(a,b,c,d,6,write);
            		CodificadorHTML.plCSV(a,b,c,d,write);
            		CodificadorHTML.CorpoFimHTML(write);
    	        	write.close();
            		break;}        	    		
                break;
            } else {
                System.out.println("Operação não válida! Escolha novamente!");
                op = MenuOutput();
            }   		
    	}
          	
    	return;    	
    }

    private static void convertplParametrica(float x0,float y0,float z0, float a, float b, float c, float a1, float b1, float c1){
    	System.out.println("Equação Paramétrica:");
    	System.out.print("x="+x0);
    	if(a>=0)
    		System.out.print("+"+a+"k");
    	else
    		System.out.print(a+"k");
    	if(a1>=0)
    		System.out.println("+"+a1+"k1");
    	else
    		System.out.println(a1+"k1");
    	System.out.print("y="+y0);
    	if(b>=0)
    		System.out.print("+"+b+"k");
    	else
    		System.out.print(b+"k");
    	if(b1>=0)
    		System.out.println("+"+b1+"k1");
    	else
    		System.out.println(b1+"k1");
    	System.out.print("z="+z0);
    	if(c>=0)
    		System.out.print("+"+c+"k");
    	else
    		System.out.print(c+"k");
    	if(c1>=0)
    		System.out.println("+"+c1+"k1");
    	else
    		System.out.println(c1+"k1");
    	    	
    	return;
    }

    private static void calcplCartesiana(float x0,float y0,float z0, float a, float b, float c, float a1, float b1, float c1, int ex, int opout, float px0, float py0, float pz0) throws FileNotFoundException{
    	float ac,bc,cc,dc;
    	String [][]matriz=new String[3][3];
    	
    	ac=(b*c1-(c*b1));
    	bc=-(a*c1-(a1*c));
    	cc=(a*b1-(a1*b));
    	dc=(-ac*x0-b*y0-c*z0);
    	System.out.print("Fórmula para calcular Laplace:");
        System.out.println("A(x-x0) + B(y-y0) + C(z-z0)=0");
        if (x0 >= 0) {
            matriz[0][0] = "x-" + x0;
        } else {
            matriz[0][0] = "x+" + Math.abs(x0);
        }
        if (y0 >= 0) {
            matriz[0][1] = "y-" + y0;
        } else {
            matriz[0][1] = "y+" + Math.abs(y0);
        }
        if (z0 >= 0) {
            matriz[0][2] = "z-" + z0;
        } else {
            matriz[0][2] = "z+" + Math.abs(z0);
        }
    	matriz[1][0]=String.valueOf(a);
    	matriz[1][1]=String.valueOf(b);
    	matriz[1][2]=String.valueOf(c);
    	matriz[2][0]=String.valueOf(a1);
    	matriz[2][1]=String.valueOf(b1);
    	matriz[2][2]=String.valueOf(c1);
    	apresentarCartesiana(matriz);
    	System.out.println("A="+ac);
    	System.out.println("B="+bc);
    	System.out.println("C="+cc);
    	System.out.println("D=-Ax-By-Cz");		
		System.out.print("D=-"+a*-1+"*("+x0+")");		
		if (b>0)
			System.out.print(b*-1+"*("+y0+")");
		else
			System.out.print("+"+b*-1+"*("+y0+")");
		if (c>0)
			System.out.print(c*-1+"*("+z0+")");
		else
			System.out.print("+"+c*-1+"*("+z0+")");
        System.out.println("D="+dc);
    	
    	
    	if(ex==6)
    		convertplCartesiana(ac,bc,cc,dc, 6);
    	if(ex==7)
    		calcplDist(ac,bc,cc,dc,opout,px0,py0,pz0);
    	if (ex==9)
    		plCSV(ac,bc,cc,dc);
    		
    	return;
    }

    private static void calcInvplCartesiana(float ac, float bc, float cc, float dc){
    		int pontos [][] = new int [3][3];
    		boolean check=true;
    			
       		do{	
       			for(int i=0;i<pontos.length;i++)
       				for(int j=0;j<pontos[i].length-1;j++)
       					pontos[i][j]=(int) (1+(Math.random()*10));		
       			for(int i=0;i<pontos.length-1;i++)
       				pontos[i][2]=(int) ((-ac*pontos[i][0]-bc*pontos[i][1]-dc)/cc);
       			check=VerificiarColineares(pontos[0][0],pontos[0][1],pontos[0][2],pontos[1][0],pontos[1][1],pontos[1][2],pontos[2][0],pontos[2][1],pontos[2][2]);	    			
       		}while (check==true);
       		System.out.println("P1=(x,y,z)\tP2=(x,y,z)\tP3=(x,y,z)");
       		System.out.println("x e y serão valores aleatórios sendo que z será calculado na equação cartesiana.");
       		System.out.println("P1=("+pontos[0][0]+","+pontos[0][1]+"z)\tP2=("+pontos[1][0]+","+pontos[1][1]+",z)\tP3=("+pontos[2][0]+","+pontos[2][1]+",z)");
       		System.out.println("\nAx+By+Cz+D=0");
       		System.out.println("z=(-A*x-B*y-D)/C");
       		System.out.print("Pz1=(");
       		System.out.print(ac*-1+"*("+pontos[0][0]+")");		
       		if (bc>0)
       			System.out.print(bc*-1+"*("+pontos[0][1]+")");
       		else
       			System.out.print("+"+bc*-1+"*("+pontos[0][1]+")");
       		if (dc>0)
       			System.out.println(dc*-1+")/"+cc);
       		else
       			System.out.println("+"+dc*-1+")/"+cc);
       		System.out.print("Pz2=(");
       		System.out.print(ac*-1+"*("+pontos[1][0]+")");		
       		if (bc>0)
       			System.out.print(bc*-1+"*("+pontos[1][1]+")");
       		else
       			System.out.print("+"+bc*-1+"*("+pontos[1][1]+")");
       		if (dc>0)
       			System.out.println(dc*-1+")/"+cc);
       		else
       			System.out.println("+"+dc*-1+")/"+cc);	
       		System.out.print("Pz3=(");
       		System.out.print(ac*-1+"*("+pontos[2][0]+")");		
       		if (bc>0)
       			System.out.print(bc*-1+"*("+pontos[2][1]+")");
       		else
       			System.out.print("+"+bc*-1+"*("+pontos[2][1]+")");
       		if (dc>0)
       			System.out.println(dc*-1+")/"+cc);
       		else
       			System.out.println("+"+dc*-1+")/"+cc);	
       		System.out.println("\nP1=("+pontos[0][0]+","+pontos[0][1]+","+pontos[0][0]+")\tP2=("+pontos[1][0]+","+pontos[1][1]+","+pontos[1][2]+")\tP3=("+pontos[2][0]+","+pontos[2][1]+","+pontos[2][2]+")");	
       		System.out.println("x0="+pontos[0][0]);	
       		System.out.println("y0="+pontos[0][1]);
       		System.out.println("z0="+pontos[0][2]);
       		System.out.println("(a,b,c)=P1-P2");
       		System.out.println("a="+(pontos[0][0]-pontos[1][0]));
       		System.out.println("b="+(pontos[0][1]-pontos[1][1]));
       		System.out.println("c="+(pontos[0][2]-pontos[1][2]));
       		System.out.println("(a1,b1,c1)=P2-P3");
       		System.out.println("a1="+(pontos[1][0]-pontos[2][0]));
       		System.out.println("b1="+(pontos[1][1]-pontos[2][1]));
       		System.out.println("c1="+(pontos[1][2]-pontos[2][2]));
       		
        	convertplVectorial(pontos[0][0],pontos[0][1],pontos[0][2],pontos[0][0]-pontos[1][0],pontos[0][1]-pontos[1][1],pontos[0][2]-pontos[1][2],pontos[1][0]-pontos[2][0],pontos[1][1]-pontos[2][1],pontos[1][2]-pontos[2][2]);
        	convertplParametrica(pontos[0][0],pontos[0][1],pontos[0][2],pontos[0][0]-pontos[1][0],pontos[0][1]-pontos[1][1],pontos[0][2]-pontos[1][2],pontos[1][0]-pontos[2][0],pontos[1][1]-pontos[2][1],pontos[1][2]-pontos[2][2]);
    			
    			return;
    		} 
    public static boolean VerificiarColineares(float a, float b, float c, float d, float e, float f, float g, float h, float i) {
    	        boolean verifica = false;
    	        float r1, r2, r3, r4, r5, r6;

    	        r1 = a - d;
    	        r2 = b - e;
    	        r3 = c - f;
    	        r4 = d - g;
    	        r5 = e - h;
    	        r6 = f - i;
    	        if (r4 / r1 != r5 / r2 && r4 / r1 != r6 / r3 && r5 / r2 != r6 / r3) 
    	            
    	        	return verifica;
    	        else {
    	            verifica = true;    	            
    	            return verifica;
    	        }
    	    }
    		
    private static void convertplCartesiana(float a, float b, float c, float d, int ex){    	
    	if (ex==6)
    		System.out.println("Equação Cartesiana:");
    	System.out.print(a+"x");
    	if (b>=0)
    		System.out.print("+"+b);
    	else
    		System.out.print(b);
    	if (c>=0)
    		System.out.print("+"+c);
    	else
    		System.out.print(c);
    	if (d>=0)
    		System.out.println("+"+d+"=0");
    	else
    		System.out.println(d+"=0");
    	
    	return;
    }

    private static void convertplVectorial(float x0,float y0,float z0, float a, float b, float c, float a1, float b1, float c1){
    	System.out.println("Equação Vetorial:");
    	System.out.println("(x,y,z)=("+x0+","+y0+","+z0+")+k("+a+","+b+","+c+")+k1("+a1+","+b1+","+c1+")");

    	return;   	
    }

    private static void DistanciaPontoPlano() throws FileNotFoundException {
    	String n;
    	char op;
    	int ex=7;
    	
    	do {
    		System.out.println("Escolha o tipo de equação sobre o qual pretende calcular a distância.");
    		System.out.println("1 - Equação Vetorial");
    		System.out.println("2 - Equação Paramétrica");
    		System.out.println("3 - Equação Cartesiana");
    		System.out.println("Insira qualquer outro valor para voltar ao menu anterior");
    		n=in.next();
    	} while (n.length() != 1);
        op = n.charAt(0);        
        switch (op){
        	case '1':
        		plvectorial(ex);
        		break;
        	case '2':
        		plparametrica(ex);
        		break;
        	case '3':
        		plcartesiana(ex);
        		break;
        	default:
        		System.out.println("Voltando menu anterior...");
        		return;       
        }
        
        return;  
    }
    
    private static void calcplDist(float a,float b, float c, float d,int op, float px0,float py0,float pz0){
    	float k,qx0,qy0,qz0,vx,vy,vz,dist;
    	
    	if (op==1){
	    	System.out.println("Insira as coordenadas do ponto."); 
	    	System.out.print("x=");
	    	px0=in.nextFloat();
	    	System.out.print("y=");
	    	py0=in.nextFloat();
	    	System.out.print("z=");
	    	pz0=in.nextFloat();}    	
    	System.out.println("Dist=|PQ|");
		System.out.println("Determinar reta que passa por P, perpendicular ao plano.");
		System.out.println("A,B,C é o vetor diretor da reta e P(x0,y0,z0)");
		convertParametrica(px0,py0,pz0,a,b,c,7);
		System.out.println("\nSistema de Equações para determinar K");
		convertplCartesiana(a,b,c,d,7);
		convertParametrica(px0,py0,pz0,a,b,c,7);
		System.out.println("\nDeterminar K:");
		System.out.println("A(x0+ak)+B(y0+bk)+C(z0+ck)+D=0");
		System.out.println("k=(-Ax0-By0-Cz0-D)/(Aa+Bb+Cc");
		if (a>0)
			System.out.print("k=(-"+a+"*("+px0+")");
		else
			System.out.print("k=("+a+"*("+px0+")");
		if (b>0)
			System.out.print("-"+b+"*("+py0+")");
		else
			System.out.print(b+"*("+py0+")");
		if (c>0)
			System.out.print("-"+c+"*("+pz0+")");
		else
			System.out.print(c+"*("+pz0+")");
		if (d>0)
			System.out.print("-"+d+")/("+a+"*("+a+")");
		else
			System.out.print(d+")/("+a+"*("+a+")");
		if (b>=0)
			System.out.print("+"+b+"*("+b+")");
		else
			System.out.print(b+"*("+b+")");
		if (c>=0)
			System.out.println("+"+c+"*("+c+"))");
		else
			System.out.println(c+"*("+c+"))");		
		k=(-a*px0-b*py0-c*pz0-d)/(a*a+b*b+c*c);
		System.out.println("K="+k);
		System.out.println("\nDeterminar Q");
		System.out.println("x=x0+ka\ny=y0+kb\nz=z0+kc");
		System.out.print("x="+px0);
		if (k>=0)
			System.out.println("+"+k+"*("+a+")");
		else
			System.out.println(k+"*("+a+")");
		System.out.print("y="+py0);
		if (k>=0)
			System.out.println("+"+k+"*("+b+")");
		else
			System.out.println(k+"*("+b+")");
		System.out.print("z="+pz0);
		if (k>=0)
			System.out.println("+"+k+"*("+c+")");
		else
			System.out.println(k+"*("+c+")");
		qx0=px0+k*a;
		qy0=py0+k*b;
		qz0=pz0+k*c;
		System.out.println("x="+qx0);
		System.out.println("y="+qy0);
		System.out.println("z="+qz0);
		System.out.println("\nDeterminar o vetor PQ");
		System.out.println("PQ=Q-P");
		System.out.println("PQ=(xq,yq,zq)-(xp,yp,zp)");
		System.out.println("PQ=(xq-xp,yq-yp,zq-zp)");
		System.out.print("PQ=("+qx0);
		if (px0<0)
			System.out.print("+"+px0*-1+","+qy0);
		else
			System.out.print(px0*-1+","+qy0);
		if (py0<0)
			System.out.print("+"+py0*-1+","+qz0);
		else
			System.out.print(py0*-1+","+qz0);
		if (pz0<0)
			System.out.println("+"+pz0*-1+")");
		else
			System.out.println(pz0*-1+")");
		vx=qx0-px0;
		vy=qy0-py0;
		vz=qz0-pz0;
		System.out.println("PQ=("+vx+","+vy+","+vz+")");
		System.out.println("\nMódulo do vetor PQ:");
		System.out.println("|PQ|=v(x²+y²+z²)");
		System.out.println("|PQ|=v(("+vx+")²+("+vy+")²+("+vz+")²)");
		dist=(float)Math.sqrt(vx*vx+vy*vy+vz*vz);
		System.out.println("\n\nA distância é de: "+dist+".");
    	    	
    	return;
    }
    
    private static void RetaIntervaloXY() throws FileNotFoundException {
    	String n;
    	char op;
    	int ex=8;
    	
    	do {
    		System.out.println("Escolha o tipo de equação que pretende transformar.");
    		System.out.println("1 - Equação Vetorial");
    		System.out.println("2 - Equação Paramétrica");
    		System.out.println("3 - Equação Cartesiana");
    		System.out.println("Insira qualquer outro valor para voltar ao menu anterior");
    		n=in.next();
    	} while (n.length() != 1);
        op = n.charAt(0);        
        switch (op){
        	case '1':
        		vectorial(ex);
        		break;
        	case '2':
        		parametrica(ex);
        		break;
        	case '3':
        		cartesiana(ex);
        		break;
        	default:
        		System.out.println("Voltando menu anterior...");}
        		
        		return;  
    }
    
    private static void CSV(float x0, float y0, float z0, float a, float b, float c) throws FileNotFoundException{
    	float em, em1, eb, eb1,tmpx,tmpy,zcont;
    	int zmin,zmax,zpon;
    	        	
    	em=a/c;
    	eb=-(z0*a)/c+x0;  
    	em1=b/c;
    	eb1=-(z0*b)/c+y0; 
    	System.out.println("Insira o Limite Inferior do eixo de ZZ.");
    	zmin=in.nextInt();
    	System.out.println("Insira o Limite Superior do eixo de ZZ.");
    	zmax=in.nextInt();
    	System.out.println("Insira o número de pontos que pretende visualizar no eixo de XX.");
    	zpon=in.nextInt();
    	zcont=(zmax-zmin+1)/zpon;
    	
    	Formatter ficheiro = new Formatter(new File("Grafico reta.csv"));
    	ficheiro.format("Grupo DKL04 - LAPR1 2011-12;;;;;\n;;;;;\n");
    	ficheiro.format("Representação da recta x=%fz",em);
    	if (eb>=0)
    		ficheiro.format("+%f",eb);
    	else
    		ficheiro.format("%f",eb);
    	ficheiro.format("\ty=%fz",em1);
    	if (eb1>=0)
    		ficheiro.format("+%f;;;;;\n;;;;;",eb1);
    	else
    		ficheiro.format("%f;;;;;\n;;;;;",eb1);
    	ficheiro.format("Dimensões;;;;;\n;;;;;\n;Eixo ZZ;;;;\n");
    	ficheiro.format("Limite Inferior;%d;;;;\n",zmin);
    	ficheiro.format("Limite Superior;%d;;;;\n",zmax);
    	ficheiro.format("Número de pontos;%d;;;;\n;;;;;\n",zpon);
    	ficheiro.format("Projeção no eixo XZ;;;;;\n;;;;;\n");
    	for(int x=zmin;x<zmax;x+=zcont)
    		ficheiro.format(";%d",x);	
    	ficheiro.format("\nx");	
    	for(int x=zmin;x<zmax;x+=zcont){	
    		tmpx=em*x+eb;
    		ficheiro.format(";%f",tmpx);}
    	ficheiro.format("\n;;;;;\nProjeção no eixo do YZ;;;;;\n;;;;;\n");	
    	for(int y=zmin;y<zmax;y+=zcont)
    		ficheiro.format(";%d",y);	
    	ficheiro.format("\ny");	
    	for(int y=zmin;y<zmax;y+=zcont){
    		tmpy=em1*y+eb1;
    		ficheiro.format(";%f",tmpy);}
    	ficheiro.close();
    
    	return;
    }
    
    private static void PlanoIntervalorXY() throws FileNotFoundException {
    	String n;
    	char op;
    	int ex=9;
    	
    	do {
    		System.out.println("Escolha o tipo de equação que pretende representar.");
    		System.out.println("1 - Equação Vetorial");
    		System.out.println("2 - Equação Paramétrica");
    		System.out.println("3 - Equação Cartesiana");
    		System.out.println("Insira qualquer outro valor para voltar ao menu anterior");
    		n=in.next();
    	} while (n.length() != 1);
        op = n.charAt(0);        
        switch (op){
        	case '1':
        		plvectorial(ex);
        		break;
        	case '2':
        		plparametrica(ex);
        		break;
        	case '3':
        		plcartesiana(ex);
        		break;
        	default:
        		System.out.println("Voltando menu anterior...");}
        		
        		return;	    
    }
    
    private static void plCSV(float a, float b, float c, float d) throws FileNotFoundException{
    	int xmin,xmax,ymin,ymax,xpon,ypon;
    	float contx,conty;
    	
    	System.out.println("Insira o Limite Inferior do eixo de XX.");
    	xmin=in.nextInt();
    	System.out.println("Insira o Limite Superior do eixo de XX.");
    	xmax=in.nextInt();
    	System.out.println("Insira o número de pontos que pretende visualizar no eixo de XX.");
    	xpon=in.nextInt();
    	contx=(xmax-xmin+1)/xpon;
    	System.out.println("Insira o Limite Inferior do eixo de YY.");
    	ymin=in.nextInt();
    	System.out.println("Insira o Limite Superior do eixo de YY.");
    	ymax=in.nextInt();
    	System.out.println("Insira o número de pontos que pretende visualizar no eixo de YY.");
    	ypon=in.nextInt();
    	conty=(ymax-ymin+1)/ypon;
    	Formatter ficheiro = new Formatter(new File("Grafico plano.csv"));
    	ficheiro.format("Grupo DKL04 - LAPR1 2011-12;;;;;\n;;;;;\n");
    	ficheiro.format("Representação do plano z=(%f/%f)x+(%f/%f)y+(%f/%f);;;;;;;\n",a,c,b,c,d,c);  
    	ficheiro.format(";;;;;;;\nDimensões;;;;;;;\n;;;;;;;\n;Eixo XX;Eixo YY;;;;;\n");
    	ficheiro.format("Limite Inferior;%d;%d;;;;;\nLimite Superior;%d;%d;;;;;\n",xmin,ymin,xmax,ymax);
    	ficheiro.format("Número de pontos;%d;%d;;;;;\n;;;;;;;\n",xpon,ypon);
    	ficheiro.format("Valores;;;;;;;\n;;;;;;;\n;");    	
    	for (float y=ymin;y<ypon;y+=conty)
    		ficheiro.format("%f;",y);
    	ficheiro.format("\n");
    	for (float x=xmin;x<xpon;x+=contx){
    		ficheiro.format("%f;",x);
    		for (float y=ymin;y<ypon;y+=conty)
    			ficheiro.format("%f;",((a/c)*x+(b/c)*y+(d/c)));
    		ficheiro.format("\n");   		
    	}    	
    	ficheiro.close();
    	
    	return;
    }

	private static String[][] valoresMatriz(String[][] matriz, float a,
			float b, float c, float d, float e, float f, float g, float h,
			float i) {

		float[] num = { d, e, f, g, h, i };
		int cont = 0;

		if (a >= 0) {
			matriz[0][0] = "x-" + a;
		} else {
			matriz[0][0] = "x+" + Math.abs(a);
		}
		if (b >= 0) {
			matriz[0][1] = "y-" + b;
		} else {
			matriz[0][1] = "y+" + Math.abs(b);
		}
		if (c >= 0) {
			matriz[0][2] = "z-" + c;
		} else {
			matriz[0][2] = "z+" + Math.abs(c);
		}
		for (int j = 1; j < matriz.length; j++) {
			for (int k = 0; k < matriz[0].length; k++) {
				matriz[j][k] = String.valueOf(num[cont]);
				cont++;
			}
		}

		return matriz;
	}
}