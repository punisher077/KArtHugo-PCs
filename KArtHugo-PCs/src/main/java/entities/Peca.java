package entities;

import javax.persistence.*;

@Entity

public class Peca implements Identificavel {
	private String nome;
	private String categoria;
	private String marca;
	private String descricao;
	private double preco;
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "peca_seq_gen")
	@SequenceGenerator(name = "peca_seq_gen", sequenceName = "peca_id_seq")
	@Id
	private Long id;	// categorias: ram, hd, video etc

	public Peca(String nome, String categoria, double preco) {
		super();
		this.nome = nome;
		this.categoria = categoria;
		this.preco = preco;

	}

	public String getNome() {
		return nome;
	}

	public Peca() {
		super();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}
}