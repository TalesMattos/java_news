package java08;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
	Onde você deve evitar usar o Optional?
	Optional não deve ser sua bala de prata para evitar todos os NullPointerException 
	da sua aplicação/API, ele deve ser encarado como um facilitador. 
	
	A JSR-335 não recomenda que você utilize o recurso nos contextos abaixo:
		-> Como atributo da sua entidade (lembre-se ele não é serializável)
		-> Como atributo do seu DTO (lembre-se ele não é serializável)
		-> Como parâmetro das suas funções
		-> Como parâmetro nos construtores
	
	
	Para ajudar a entender onde usar Optional e onde não usar, siga as sete regras de Stuart Marks:
	 -> Nunca, nunca, use null para uma variável Optional ou valor de retorno.
	 -> Nunca use Optional.get() a menos que você possa provar que o Optional está presente.
	 -> Prefira métodos alternativos do que Optional.isPresent() e Optional.get().
	 -> Geralmente é uma má ideia criar um Optional para o propósito específico dos métodos de encadeamento e a partir dele obter um valor.
	 -> Usar um Optional com um outro Optional aninhado, ou que tenha um resultado intermediário de Optional, provavelmente é uma solução muito complexa.
	 -> Evite usar Optional em campos, parâmetros de método e coleções.
	 -> Não use Optional para envolver qualquer tipo de coleção (List, Set, Map). Em vez disso, use uma coleção vazia para representar a ausência de valores.
 */


class Curso {
	private String nome;
	
	private List<Aluno> listaAlunos;
	
	public Curso(String nome) {
		this.nome = nome;
		this.listaAlunos = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aluno> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}
}

class Aluno {
	private Optional<Matricula> matricula;
	private String nome;
	
	public Aluno(String nome) {
		this.nome = nome;
		//Inicializa Optional como um container vazio
		this.matricula = Optional.empty();
	}
	
	public Optional<Matricula> getMatricula() {
		return matricula;
	}
	
	public void setMatricula(Matricula matricula) {
		this.matricula = Optional.of(matricula);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}	
}

class Matricula {
	private String numero;
	
	public Matricula(String numero){
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}

public class OptionalExemplo {
	
	public static void main(String[] args) {
		
		Curso cursoAdm = new Curso("Administração");
		
		Matricula m = new Matricula("11100");
		
		Aluno jose = new Aluno("José");
		jose.setMatricula(m);
		//Adiciona aluno ao curso
		cursoAdm.getListaAlunos().add(jose);
		
		Aluno maria = new Aluno("Maria");
		maria.setMatricula(new Matricula("12010"));
		//Adiciona aluno ao curso
		cursoAdm.getListaAlunos().add(maria);
		
		//SEM MATRÍCULA !!!
		Aluno ana = new Aluno("Ana");
		//Adiciona aluno ao curso
		cursoAdm.getListaAlunos().add(ana);
		
		Aluno paulo = new Aluno("Paulo");
		paulo.setMatricula(new Matricula("14010"));
		//Adiciona aluno ao curso
		cursoAdm.getListaAlunos().add(paulo);
		
		//Exibe o nome do aluno e o número de sua matrícula no curso
		cursoAdm.getListaAlunos().stream()
				.filter( a -> a.getMatricula().isPresent() )
				.forEach( a -> System.out.println(a.getNome() + " - " + a.getMatricula().get().getNumero() ));

		cursoAdm.getListaAlunos().stream()
		.forEach( a -> { 
			System.out.print(a.getNome() + " - "); 
			a.getMatricula().ifPresent(mat -> System.out.print(mat.getNumero())); 
			System.out.println();
		});
		
		cursoAdm.getListaAlunos().forEach(a -> {
			System.out.println(a.getMatricula().orElse(new Matricula("Não Iformada")).getNumero());
			System.out.println(a.getNome() + ":" + a.getMatricula().map(Matricula::getNumero).orElse("Não informada"));
			a.getMatricula().ifPresentOrElse(mat -> System.out.println("caiu no if " + mat.getNumero()), () -> System.out.println("caiu no else"));
			a.getMatricula().orElseGet(Constantes::getUnknow);
			a.getMatricula().orElseGet(() -> new Matricula(""));
			System.out.println(a.getMatricula().or(Constantes::getUnknowOpt).get().getNumero());
			System.out.println(a.getMatricula().stream().toArray());

//			a.getMatricula().orElseThrow(IllegalArgumentException::new);
		});
	}
	
}

class Constantes {
	public static Matricula getUnknow() {
		return new Matricula("DESCONHECIDO");
	}
	
	public static Optional<Matricula> getUnknowOpt() {
		return Optional.of( new Matricula("DESCONHECIDO OPT") );
	}
}
