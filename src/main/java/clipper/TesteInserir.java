package clipper;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import clipper.entities.Usuarios;
import clipper.errorsys.CriptografiaHash;
import clipper.infra.DAO;

public class TesteInserir {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		Scanner sc = new Scanner(System.in);

		System.out.println("Digite o Nome: ");
		String nome = sc.nextLine();
		System.out.println("Digite o Email: ");
		String email = sc.nextLine();
		System.out.println("Digite o Perfil: ");
		String perfil = sc.nextLine();
		System.out.println("Digite o Telefone: ");
		String telefone = sc.nextLine();
		System.out.println("Digite o Senha: ");
		String senha = sc.nextLine();
		
		Usuarios usuario = new Usuarios(null, 
		nome,
		email, 
		CriptografiaHash.criptoSenha(senha),
		telefone, 
		perfil);
		
		DAO<Usuarios> dao = new DAO<>(Usuarios.class);
		dao.abrirT().incluir(usuario).fecharT();
		
		System.out.println();
		System.out.println();
		System.out.println("Registro gravados com sucesso");
		System.out.println("Nome....: " + nome);
		System.out.println("Email...: " + email);
		System.out.println("Senha...: " + CriptografiaHash.criptoSenha(senha));
		System.out.println("Telefone: " + telefone);
		System.out.println("Perfil..: " + nome);
		
	}

}
