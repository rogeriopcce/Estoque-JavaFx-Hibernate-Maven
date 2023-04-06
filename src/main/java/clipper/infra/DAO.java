package clipper.infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import clipper.entities.Usuarios;
import clipper.errorsys.Alertas;
import javafx.scene.control.Alert.AlertType;

public class DAO<E> {

	/* Esta classe trabalha com generics podendo receber qualquer entidade 
	 * repassada como paramentro para a utilização das funões abaixo, evitando
	 * assim escrever para todas as Entidades do projeto.
	 */
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;
	
	static {
		try {
			emf =  emf = Persistence.createEntityManagerFactory("estoque-jpa");
		}catch ( Exception e) {
			Alertas.showAlerta("Erro ao inserir", null, 
					"Erro ao gravar dados em Usuário " + e, 
					AlertType.ERROR);
		}
	}
	
	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}
	
	public DAO<E> abrirT(){
		em.getTransaction().begin();
		return this;
	}

	public DAO<E> fecharT(){
		em.getTransaction().commit();
		return this;
	}
	
	public DAO<E> incluir(E entidade){
		em.persist(entidade);
		return this;
	}

	public void alterar(E entidade, Long id) {
		em.find(entidade.getClass(), id);
		em.merge(entidade);
	}
	
	public DAO<E> incluirAtomico(E entidade){
		return this.abrirT().incluir(entidade).fecharT();
	}

	public E obterPorID(Object id) {
		return em.find(classe, id);
	}
	public List<E> obterTodos(){
		return this.obterTodos(10,0);
	}
	
	/* Esta função pode ser adaptada para consultar por outros campos
	 * usando o JPQL para maiores duvidas consultar o manual.
	 */
	public List<E> obterTodos(int qtde, int deslocamento){
		if(classe == null) {
			throw new UnsupportedOperationException("A classe não pode ser nula");
		}
		
		String jpql = "select e from "+ classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(qtde);
		query.setFirstResult(deslocamento);
		return query.getResultList();
	}

	public List<E> consultar(String nomeConsulta, Object... params) {
		TypedQuery<E> query = em.createNamedQuery(nomeConsulta, classe);
		
		for (int i = 0; i < params.length; i += 2) {
			query.setParameter(params[i].toString(), params[i + 1]);
		}
		
		return query.getResultList();
	}
	
	public E consultarUm(String nomeConsulta, Object... params) {
		List<E> lista = consultar(nomeConsulta, params);
		return lista.isEmpty() ? null : lista.get(0);
	}
	
	public void fechar() {
		em.close();
		emf.close();
	}


}
