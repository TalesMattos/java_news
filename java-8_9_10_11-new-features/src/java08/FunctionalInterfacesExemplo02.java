package java08;

/**
 * 
 * Interface funcional deve ter apenas um m�todo abstrado
 * Pode conter outros m�todos, desde que sejam implementados dentro da pr�pria interface
 * 		(por exemplo: m�todos default e/ou privados)
 *
 */
@FunctionalInterface
public interface FunctionalInterfacesExemplo02 {
	
	public void desenha(Double largura, Double altura);

	
	default void imprime() {
		System.out.println("#imprime");
		reposicionaToner();
		FunctionalInterfacesExemplo02.desligar();
	}
	
	private void reposicionaToner() {
		System.out.println("#reposicionaToner");
	};

	private static void desligar() {
		System.out.println("#desligar");
	};
}
