package clipper.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import clipper.entities.Usuarios;
import clipper.errorsys.Alertas;
import clipper.errorsys.CriptografiaHash;
import clipper.infra.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private TextField txtUsuario;
	
	@FXML
	private PasswordField txtSenha;
	
	@FXML
	private Button btEntrar;
	
	@FXML
    private Button btFechar;

	@FXML
	void onEntrar(ActionEvent event) {
		if ( txtUsuario.getText().isEmpty() ||
				txtSenha.getText().isEmpty() ){
			Alertas.showAlerta("Bem-vindo", "Campos obrigatório estão vazios!!!", 
					"Verique os campos Usuario ou Senha, os campos não podem estar vazios.", 
					AlertType.ERROR);
		}else {
			EntityManagerFactory emf;
			EntityManager em;
			try {
				
				/*
				 * emf = emf = Persistence.createEntityManagerFactory ("estoque-jpa"); em =
				 * emf.createEntityManager();
				 * 
				 * 
				 * Query query =
				 * em.createQuery("SELECT u FROM Usuarios u WHERE u.email = :email");
				 * query.setParameter("email", txtUsuario.getText());
				 * 
				 * List<Usuarios> usuarios = query.getResultList();
				 */
				
				
				DAO<Usuarios> dao = new DAO<>(Usuarios.class);
				List<Usuarios> usuarios = dao.consultar("buscarEmail", "email", 
						txtUsuario.getText());
				String snh = CriptografiaHash.criptoSenha(txtSenha.getText());
				if (!usuarios.isEmpty()) {
				    Usuarios usuario = usuarios.get(0);
				    
					Alertas.showAlerta("Login", "Email: " +usuario.getEmail()+
							" \n "+ "Senha: " + 
							"Usuário: " +usuario.getNome()+"  \n "+ "Senha: " + 
							CriptografiaHash.criptoSenha(usuario.getSenha()), 
							"Login efetuado com sucesso!!!", AlertType.CONFIRMATION);
					
					List<Usuarios> usuariossenha = dao.consultar("buscarSenha", "senha", 
							snh);
					
					if ( !usuariossenha.isEmpty() ) {
						System.out.println("Senha correta!!");
					}else {
						System.out.println("Senha Incorreta!");
					}
					

				}else {
					Alertas.showAlerta("Erro", 
							"Usuario(Email): "+txtUsuario.getText() +
							" invalido, verifique e tente novamente.", 
							"Usuario não encontrado, verifique os dados inseridos e tentenovamente",
							AlertType.ERROR);
				}

				
				if (!usuarios.isEmpty()) {
				    Usuarios usuario = usuarios.get(0);
				    
					Alertas.showAlerta("Login", "Email: " +usuario.getEmail()+
							" \n "+ "Senha: " + 
							"Usuário: " +usuario.getNome()+"  \n "+ "Senha: " + 
							CriptografiaHash.criptoSenha(usuario.getSenha()), 
							"Login efetuado com sucesso!!!", AlertType.CONFIRMATION);
				}else {
					Alertas.showAlerta("Erro", 
							"Usuario(Email): "+txtUsuario.getText() +
							" invalido, verifique e tente novamente.", 
							"Usuario não encontrado, verifique os dados inseridos e tentenovamente",
							AlertType.ERROR);
				}				
				
				// System.out.println("implementar");
				
			}catch(Exception e) {
				Alertas.showAlerta("Erro", txtUsuario.getText(), e.getMessage(), AlertType.ERROR);
			}
//			Alertas.showAlerta("Bem-vindo", null, "Tela de logim em manutenção!!", 
//				AlertType.INFORMATION);
		}
		
	}
	
    @FXML
    void onFechar(ActionEvent event) {

    	Alert alerta = new Alert(AlertType.CONFIRMATION);
    	alerta.setTitle("Seja Bem-vindo.");
    	alerta.setHeaderText(null);
    	alerta.setContentText("Deseja realmente finalizar a Aplicação?");
    	
    	Optional<ButtonType> opcao = alerta.showAndWait();
    	if ( opcao.get() == ButtonType.OK ) {
    		System.exit(0);
    	}
    	
    }

}
