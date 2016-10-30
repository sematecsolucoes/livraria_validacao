package br.com.sematec.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import br.com.sematec.livraria.dao.AutorDAO;
import br.com.sematec.livraria.dao.LivroDAO;
import br.com.sematec.livraria.modelo.Autor;
import br.com.sematec.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	private Long autorId;

	public void adicionarAutor() {
		Autor autor = AutorDAO.getInstance().buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Escrito por: " + autor.getNome());
	}

	public void comecaCom978(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		if (!StringUtils.startsWithIgnoreCase((String) value, "978")) {
			throw new ValidatorException(new FacesMessage("ISBN deveria começar com 978"));
		}
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public Long getAutorId() {
		return autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	public List<Livro> getLivros() {
		return LivroDAO.getInstance().listaTodos();
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());
		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		LivroDAO.getInstance().adiciona(this.livro);
		init();
	}

	@PostConstruct
	private void init() {
		livro = new Livro();
		autorId = null;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}
}
