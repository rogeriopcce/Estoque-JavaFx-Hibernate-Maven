package clipper;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import clipper.entities.Usuarios;
import clipper.errorsys.CriptografiaHash;
import clipper.infra.DAO;

public class TesteConsultar {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		/*
		 * Usuarios usuario = new Usuarios(null, "Miguel Menezes",
		 * "miguelmenezes@gmail.com", CriptografiaHash.criptoSenha("123456"),
		 * "(74) 98845-2314", "Usuario"); DAO<Usuarios> dao = new DAO<>(Usuarios.class);
		 * dao.abrirT().incluir(usuario).fecharT();
		 */
		
		String snh = CriptografiaHash.criptoSenha("123456");
		DAO<Usuarios> dao = new DAO<>(Usuarios.class);
		List<Usuarios> usuarios = dao.consultar("buscarSenha", "senha", 
				snh);

		
		if( ! usuarios.isEmpty() ) {
			Usuarios usuario = usuarios.get(0);
			System.out.println("Registro encontrado com sucesso EMAIL: " +
			usuario.getEmail() + " Usuario: " +
			usuario.getNome());
		}else {
			System.out.println("Infezmente Usuario não encontrado verifique o email /n"
					+ "e tente novamente!!!");
		}
		
		for(Usuarios u: usuarios) {
			System.out.println(u.getNome());
		}

		System.out.println("Pronto");
	}

}
	