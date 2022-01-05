package java08;

//Functional interface
/*A anota��o @FunctionalInterface n�o � obrigat�ria, 
 * mas � uma boa pr�tica o uso dela porque evitamos 
 * que a interface venha ter mais um m�todo abstrato 
 * acidentalmente. 
*/
@FunctionalInterface
interface Figura2D_2 {
	void desenha(Double largura, Double altura);
	
}

class Retangulo_2 {
	
	public void desenhaRetangulo(Double largura, Double altura) {
		System.out.println("Desenha ret�ngulo de Largura: " + largura + " e Altura: " + altura);
	}
}

public class FunctionalInterfacesExemplo01 {

	public static void main(String[] args) {
		
		//Lambda expression
		Figura2D_2 fig1 = (l, a) -> System.out.println("Desenha ret�ngulo de Largura: " + l + " e Altura: " + a);
		fig1.desenha(8.0, 1.5);
		
		/********************************************************/
		
		//Objeto espec�fico
		Retangulo_2 ret = new Retangulo_2();

		//Method reference
		Figura2D_2 fig2 = ret::desenhaRetangulo;
		
		fig2.desenha(10.5, 7.0);
		
	}

}
