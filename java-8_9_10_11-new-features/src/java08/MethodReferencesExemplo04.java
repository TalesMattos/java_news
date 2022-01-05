package java08;

//Functional interface
interface Figura2Dx {
	Retangulo1 desenha(Double largura, Double altura);
}

class Retangulo1 {
	
	public Retangulo1(Double largura, Double altura) {
		System.out.println("Desenha retângulo de Largura: " + largura + " e Altura: " + altura);
	}
}

public class MethodReferencesExemplo04 {

	public static void main(String[] args) {
		
		/*Method reference 
		* Referência a um construtor
		*/
		Figura2Dx fig1 = Retangulo1::new;
		
		fig1.desenha(10.5, 7.0);

	}

}
