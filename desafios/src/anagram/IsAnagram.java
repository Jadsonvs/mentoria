package anagram;

import java.util.Scanner;

public class IsAnagram {
	
	public static void main(String[] args) {
		System.out.println("                  (          IS A ANAGRAMA???          )\n\n");
		System.out.println("                           WHAT IS AN ANAGRAM?\n");
		System.out.println(" An anagram is a word or phrase formed by rearranging the letters of a different \n"
				+ "word or phrase, typically using all the original letters exactly once.[1] For example, \n"
				+ "the word anagram itself can be rearranged into nag a ram, also the word binary into \n"
				+ "brainy and the word adobe into abode.\r\n"
				+ "\r\n"
				+ "The original word or phrase is known as the subject of the anagram. Any word or phrase \n"
				+ "that exactly reproduces the letters in another order is an anagram.\n\n");
		
		System.out.println("                               ALGORITHM           \n\n");
		
		Scanner input = new Scanner(System.in);
		System.out.print("Type a word: ");
		String a = input.next();
		
		System.out.print("Type another word: ");
		String b = input.next();
		

		char[] letraA = a.toCharArray();// Transformando String em Array de char.
		char[] letraB = b.toCharArray();

		char letraDoPrimeiroFor = 'l';
		char letraDoSegundoFor = 'l';

		boolean comparar = false;
		int quantidadeTrueSegundoFor = 0;
		int quantidadeLetraPrimeiroFor = 0;

		for (int i = 0; i < letraA.length; i++) {//FOR para varrer o Array LetraA.
			letraDoPrimeiroFor = letraA[i];
			String letraMauisculaDoArrayLetraA = String.valueOf(letraDoPrimeiroFor);//Transformando uma variável char em String.

			quantidadeLetraPrimeiroFor = quantidadeLetraPrimeiroFor + 1;//Incrementando 1 em cada volta do FOR, para achar o número d letras no Array.

			for (int y = 0; y < letraB.length; y++) {//FOR para varrer o Array LetraB.
				letraDoSegundoFor = letraB[y];
				String letraMauisculaDoArrayLetraB = String.valueOf(letraDoSegundoFor);

				comparar = letraMauisculaDoArrayLetraA.equalsIgnoreCase(letraMauisculaDoArrayLetraB);// Comparando as letras do array LetraA com as letras do array LetraB.

				if (comparar) {//Incrementando 1 em todos os casos que as letras foram iguais.
					quantidadeTrueSegundoFor = quantidadeTrueSegundoFor + 1;

					if (quantidadeTrueSegundoFor > quantidadeLetraPrimeiroFor) {//Decrementando 1 nos casos que a comparação for TRUE mais de uma vez, evitando que o número de TRUE seja acima do tamanho da Arrey.
						quantidadeTrueSegundoFor = quantidadeTrueSegundoFor - 1;
					}

				}
			}
		}

		if (letraB.length == quantidadeTrueSegundoFor) {
			System.out.println("\nIt's an anagram!");
		} else {
			System.out.println("\nIt's not an anagram!");
		}

	}

}
