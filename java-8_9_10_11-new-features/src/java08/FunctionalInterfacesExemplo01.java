package java08;

//Functional interface
/*A anotação @FunctionalInterface não é obrigatória, 
 * mas é uma boa prática o uso dela porque evitamos 
 * que a interface venha ter mais um método abstrato 
 * acidentalmente. 
*/
@FunctionalInterface
interface Figura2D_2 {
	void desenha(Double largura, Double altura);
	
}

class Retangulo_2 {
	
	public void desenhaRetangulo(Double largura, Double altura) {
		System.out.println("Desenha retângulo de Largura: " + largura + " e Altura: " + altura);
	}
}

public class FunctionalInterfacesExemplo01 {

	public static void main(String[] args) {
		
		//Lambda expression
		Figura2D_2 fig1 = (l, a) -> System.out.println("Desenha retângulo de Largura: " + l + " e Altura: " + a);
		fig1.desenha(8.0, 1.5);
		
		/********************************************************/
		
		//Objeto específico
		Retangulo_2 ret = new Retangulo_2();

		//Method reference
		Figura2D_2 fig2 = ret::desenhaRetangulo;
		
		fig2.desenha(10.5, 7.0);
		
	}

}
