package OOPD_FOOD_IIITD.OOPD_IIITD;

/**
 * Portal for delivery man
 */
import java.util.*;
import javax.lang.model.util.ElementScanner14;
import java.time.*;
import java.sql.*;
import java.sql.Date;
/**
 * @brief Delivery man can change the status of the order in the database once order is delivered
 * Additional feature
 */
public class delivery_man {
    public static void main(String[] args)
    {
	System.out.println("*****************###############***********************");
        System.out.println("Welcome to Delivery app");
        System.out.println("*****************###############***********************");
        Scanner sc=new Scanner(System.in);
        System.out.println("1.set delivery status");
        System.out.println("2.Exit");
	System.out.println("##################################################");
        System.out.println("..Please enter your choice among given..");
        System.out.println("##################################################");
        int choice=sc.nextInt();  /*!< for options */
        databse db=new databse();
        int user_id=-1;
        if(choice==1)
        {
            db.status_check();
            System.out.println("Enter the user id for whom delivery is made");
            user_id=sc.nextInt();
            db.change_status(user_id);
        }
        else
        {
            
        }
    }
}
/**
 * Change status of the order in the databse
 */
class databse
{
    public void status_check()
    {
        Scanner sc=new Scanner(System.in);
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs6=stmt.executeQuery("select * from track");
            
            while(rs6.next())
            {
                System.out.println(rs6.getInt(1)+"  "+rs6.getInt(2)+"  "+rs6.getInt(3));
                

            }
            con.close();
        }   
        catch(Exception e)
        {
            System.out.println(e);
        } 
    }


    public void change_status(int user_id)
    {
        Scanner sc=new Scanner(System.in);
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs6=stmt.executeQuery("select * from track");
            while(rs6.next())
            {
                if(user_id==rs6.getInt(1))
                {
                    stmt.executeUpdate("update track set status=1 where user_id='"+user_id+"'");
                    break;
                }
            }
            con.close();
        }   
        catch(Exception e)
        {
            System.out.println(e);
        } 
    }

}