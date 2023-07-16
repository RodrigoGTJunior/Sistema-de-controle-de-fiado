package factory;

//faz as importações de classes necessárias para o funcionamento do programa 
import java.sql.Connection; 
//conexão SQL para Java 
import java.sql.DriverManager; 
//driver de conexão SQL para Java 
import java.sql.SQLException;

public class ConnectionFactory {
	
	 public Connection getConnection() {
		 try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/tcc_senai_esboco","root","5164");
		 }         
		 catch(SQLException excecao) {
			throw new RuntimeException(excecao);
		 }
     }

}
