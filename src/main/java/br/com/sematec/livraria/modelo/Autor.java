package br.com.sematec.livraria.modelo;

public class Autor extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;

	public Autor() {
		this("");
	}

	public Autor(String nome) {
		super();
		this.nome = nome;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
