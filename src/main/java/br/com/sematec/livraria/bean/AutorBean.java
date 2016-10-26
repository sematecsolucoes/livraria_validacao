package br.com.sematec.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.sematec.livraria.dao.AutorDAO;
import br.com.sematec.livraria.modelo.Autor;

@ManagedBean
public class AutorBean {
	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		AutorDAO.getInstance().adiciona(this.autor);
		this.autor = new Autor();
	}
}
