package clipper.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuarios implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column( length = 100, nullable = false)
	private String nome;
	@Column( length = 100, nullable = false)
	private String email;
	@Column( length = 250, nullable = false)
	private String senha;
	@Column( length = 15, nullable = false)
	private String telefone; // campo para recuperação de senha
	@Column( length = 20)
	private String perfil;
	
	public Usuarios() {
	}
		
	public Usuarios(Long id, String nome, String email, String senha, String telefone, String perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.perfil = perfil;
	};

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuarios other = (Usuarios) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", telefone="
				+ telefone + ", perfil=" + perfil + "]";
	}
	
	
}
