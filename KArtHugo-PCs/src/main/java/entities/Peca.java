package entities;

import javax.persistence.*;

@Entity

public class Peca {
	private String nome;
	private String categoria;
	private double preco;
	private int id;
	// categorias: ram, hd, video etc
	private int quant;
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "peca_seq_gen")
	@SequenceGenerator(name = "peca_seq_gen", sequenceName = "peca_id_seq")
	@Id
	private int id;	// categorias: ram, hd, video etc

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}