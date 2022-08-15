package buscaNoVetor;

public class BuscarNoVetor {
	
	public static void main(String[] args) {
		int[] vetorInteiro = {10, 12, 25, 40, 50};
		int procurarPor = 10;
		
		System.out.println("Posição do valor " + procurarPor + " dentro do vetor é " + buscarDentroNoVetor(vetorInteiro, procurarPor));
		
	}
	
	static int buscarDentroNoVetor(int[] vetorInteiro, int procurarPor) {
		int posicaoDovalor = -1;
		
		for(int i = 0; i < vetorInteiro.length; i++) {
			if(procurarPor == vetorInteiro[i]) {
				return i;
			}
		}
		return posicaoDovalor;
	}

}
