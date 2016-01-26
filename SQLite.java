/* * 
 * 
 *  Lucas Brito <lucas@libertynet.com.br>
 * 
 */
package cifras;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
/**
 *
 * @author Lucas Brito <lucas@libertynet.com.br>
 */
public class SQLite {
    
    public static String sDriveName = "org.sqlite.JDBC";
    public static String sJdbc = "jdbc:sqlite";
    
    public Connection conn;
    public Statement state;
    
    
    public void create_table(){
        executeUpdate("CREATE TABLE temp_f (dados LONGTEXT)");
    }
    
    
    public static SQLite connect(String file){
        try{
            // Registra driver
            Class.forName(sDriveName);
            // Realiza conexão
            Connection c = DriverManager.getConnection(SQLite.sJdbc+":"+file);
            
            SQLite s = new SQLite();
            s.conn = c;
            System.out.println("Connectado!");
            return s;
            
        }catch(Exception e){
            System.out.println("Erro SQLite:"+e.toString());
        }
        return null;
    
    }
    
    /**
     * Executa update,insert,create,delete (Alterações nos dados da tabela)
     * @param sql 
     */
    public void executeUpdate(String sql){
        try{            
            Statement stmt = this.conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println("Erro SQLite:"+e.toString());
        }
    }
    /**
     * Executa SELECT
     * @param sql
     * @return ResultSet
     */
    public ResultSet query(String sql){
        try{
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }catch(Exception e){
            System.out.println("Erro SQLite:"+e.toString());
        }
        return null;
    }
    
}
