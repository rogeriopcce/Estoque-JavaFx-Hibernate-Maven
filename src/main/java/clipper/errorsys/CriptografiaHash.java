package clipper.errorsys;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografiaHash {
	public static String criptoSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String senhahex;

		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		return senhahex = hexString.toString();
		// Criptografia MD5 e SHA.
	}

	public static boolean comparaSenha(String senhaAdmin, String senhaUser)
			throws NoSuchAlgorithmException, UnsupportedEncodingException

	{
		// parametro senhaAdmin
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigestSenhaAdmin[] = algorithm.digest(senhaAdmin.getBytes("UTF-8"));

		StringBuilder hexStringSenhaAdmin = new StringBuilder();
		for (byte b : messageDigestSenhaAdmin) {
			hexStringSenhaAdmin.append(String.format("%02X", 0xFF & b));
		}
		String senhahexAdmin = hexStringSenhaAdmin.toString();

		// parametro senhaUser
		byte messageDigestSenhaUser[] = algorithm.digest(senhaUser.getBytes("UTF-8"));

		StringBuilder hexStringSenhaUser = new StringBuilder();
		for (byte b : messageDigestSenhaUser) {
			hexStringSenhaUser.append(String.format("%02X", 0xFF & b));
		}
		String senhahexUser = hexStringSenhaUser.toString();

		// --Comparando Senha User e Admin

		return senhahexUser.equals(senhahexAdmin);

	}
}
