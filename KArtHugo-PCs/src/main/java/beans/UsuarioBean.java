package beans;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entities.Usuario;
import services.UserService;

@ViewScoped
@Named
public class UsuarioBean implements Serializable {

	@Inject
	private UserService service;

	private String autPassword;

	private Usuario entidade;

	protected Collection<Usuario> entidades;

	public UsuarioBean() {
	}

	@PostConstruct
	public void init() {
		entidade = newEntidade();
		entidades = getService().getAll();
		entidades.add(new Usuario());
	}

	public void cadastro() {
		boolean pass = false;
		if (!entidade.getPassword().equals(autPassword)) {
			FacesContext.getCurrentInstance().addMessage("ERROR", new FacesMessage("Confira sua senha!"));
		} else {
			pass = true;
		}
		boolean unique = false;
		for (Usuario u : entidades) {
			if (entidade.getEmail().equals(u.getEmail())) {
				FacesContext.getCurrentInstance().addMessage("ERROR", new FacesMessage("Email j� cadastrado"));
			} else {
				unique = true;
			}
		}
		if (pass && unique) {
			entidade.setPassword(service.hash(entidade.getPassword()));
			entidade.setGrupo("OUTROS");
			service.save(entidade);
			entidades = service.getAll();
			FacesContext.getCurrentInstance().addMessage("Sucesso!",
					new FacesMessage("Conta cadastrada: " + entidade.getUsername()));
		}
	}

	public String getUserLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		if (userPrincipal == null) {
			return "";
		}
		return userPrincipal.getName();
	}

	public void efetuarLogout() throws IOException, ServletException {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.invalidate();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		request.logout();
		ec.redirect(ec.getApplicationContextPath());
	}

	public boolean isUserInRole(String role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role);
	}

	public void remove(Usuario entidade) {
		getService().remove(entidade);
		limpar();
	}

	public Usuario getEntidade() {
		return entidade;
	}

	public void setEntidade(Usuario entidade) {
		this.entidade = entidade;
	}

	public Collection<Usuario> getEntidades() {
		return entidades;
	}

	public void setEntidades(Collection<Usuario> entidades) {
		this.entidades = entidades;
	}

	public void save() {
		getService().save(entidade);
		limpar();
	}

	public void editar(Long id) {
		this.getEntidade().setId(id);
		save();
	}

	public void limpar() {
		entidades = getService().getAll();
		entidade = newEntidade();
	}

	protected Usuario newEntidade() {
		return new Usuario();
	}

	public UserService getService() {
		return service;
	}

	public String getAutPassword() {
		return autPassword;
	}

	public void setAutPassword(String autPassword) {
		this.autPassword = autPassword;
	}

}