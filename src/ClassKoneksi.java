import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Erryza Nur Alif
 */
public class ClassKoneksi {
    private static Connection koneksi;
    public static Connection getkoneksi(){
        if(koneksi==null){
            try {
                String url=new String();
                String user=new String();
                String password=new String();
                url="jdbc:mysql://localhost:3306/db_jadwalmatakuliah";
                user="root";
                password="";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi=DriverManager.getConnection(url,user,password);
                            
            } catch (SQLException t) {
                System.out.println("Error Membuat Koneksi");
            }
            }
        return koneksi;
    }
    
}
