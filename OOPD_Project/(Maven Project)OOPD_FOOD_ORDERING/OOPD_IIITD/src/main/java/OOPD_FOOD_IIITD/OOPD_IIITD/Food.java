package OOPD_FOOD_IIITD.OOPD_IIITD;

/**
 * Entry point of the project
 */
import java.util.*;
import javax.lang.model.util.ElementScanner14;
import java.time.*;
import java.net.PasswordAuthentication;
import java.sql.*;
import java.sql.Date;

/**
 * @brief This is the main class
 * It is the welcome portal for the user
 */

public class Food 
{
    /**
     * Introductory message to the user
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("*****************###############***********************");
        System.out.println("...Welcome to Online Food ordering system...");
        System.out.println("*****************###############***********************");
        home_page hp=new home_page();
        while(true)
        {
            int choice=hp.starter_options();
            registration reg=new registration();
            if(choice==1)
            {
                
                reg.new_user();
            }
            else if(choice==2)
            {
                reg.existing();
            }
            else
            {
				System.out.println("*****************###############***********************");
                System.out.println("...Thank you for visiting! See you soon !!!...");
				System.out.println("*****************###############***********************");
                break;
            }
        }
    }
}

/**
 * @brief Used for displaying of login and sign in options
 */
class home_page
{
    Scanner sc=new Scanner(System.in);
    public int starter_options()
    {
        int choice; /*!< Gives choice to user */
        System.out.println("1.New user registration");
        System.out.println("2.Existing user login");
        System.out.println("3.Exit");
		System.out.println("###########################################################");
        System.out.println(".. Please enter your option from above to proceed ..");
		System.out.println("###########################################################");
        choice=sc.nextInt();
        return choice;
    }
}
/**
 * @brief Contains implementation of sign in/login options
 * User can create a new registration by giving email id and password
 * or
 * CAn login if already registered
 * Here we implemented encapsulation
 */
class registration
{
    private String name;        /*!< Name of the new user */               /* encapsulation*/
    private String password;    /*!< Password for a new user */
    private String email;       /*!< Email of new user */
    private int address;        /*!< Address of the new user */
    Scanner sc=new Scanner(System.in);
    /**
     * Used for new user registration
     */
    public void new_user()
    {
        db d=new db();
        System.out.println("Please enter your name");
        name=sc.nextLine();
        System.out.println("Create a password");
        password=sc.nextLine();
        System.out.println("Enter your email id");
        email=sc.nextLine();
        int match;
        match=d.check_name(email);
        if(match==1)
        {            
            while(true)
            {
                System.out.println("email already exists, try another email");
                email=sc.nextLine();
                match=d.check_name(email);
                if(match==1){}
                else break;
            }
        }
        System.out.println("Pick your location from the below options");
        System.out.println("1.Ameerpet");
        System.out.println("2.Begumpet");
        System.out.println("3.Nampally");
        System.out.println("4.Abids");
        System.out.println("5.Punjagutta");
        address=sc.nextInt();
        d.set_userdetails(name, password, email, address);
		System.out.println("###########################################################");
		System.out.println(" ... User registration successfull ... ");
		System.out.println("###########################################################");
		
    }
    public int user_id;   /*!< Assign user id */
    /**
     * Used for existing user login
     */
    public void existing()
	{
		System.out.println("Enter your email to verify");
		String email=sc.nextLine();
		System.out.println("Enter your password to verify");
		String password=sc.nextLine();
        db d=new db();
        int p=0;
        p=d.check_login(email,password);
        if(p!=0)
        {
            user_id=p;
			System.out.println("###########################################################");
            System.out.println(" ... login successful ... ");
			System.out.println("###########################################################");
            after_login_home(user_id);
        }
        else
        {
			System.out.println("###########################################################");
            System.out.println(" ... User not found or incorrect credentials ... ");
			System.out.println("###########################################################");
        }
    }
    /**
     * Displays the options once user login successfully
     * @param user_id
     */
    public void after_login_home(int user_id)
    {
        int yorn;    /*!< yes or no */
        int it_id;   /*!< initialize variable item id to store in database*/
        while(true)
        {
            System.out.println("Choose from the following options");
            System.out.println("1.View Restaurants");
            System.out.println("2.Cart");
            System.out.println("3.Wishlist");
            System.out.println("4.view order status");
            System.out.println("5.Exit");
			System.out.println("###########################################################");
			System.out.println(" ... Please enter your choice ... ");
			System.out.println("###########################################################");
            int choice=sc.nextInt();
            if(choice==1)
            {
                restaurant res=new restaurant();
                res.print_rest(user_id);
            }
            else if(choice==2)
            {
                db d=new db();
                cart c1=new cart();
                checkout ch=new checkout();
                while(true)
                {
                    c1.view_cart(user_id);
                    System.out.println("1. Delete a particular item");
                    System.out.println("2. Discard entire cart");
                    System.out.println("3.Do none of the above");
                    yorn=sc.nextInt();
                    if(yorn==1)
                    {
                        System.out.println("Enter the item id that you want to delete");
                        it_id=sc.nextInt();
                        d.cart_delete(it_id, user_id);
                    }
                    else if(yorn==2)
                    {
                        System.out.println("All the items in the cart are discarded");
                        d.entire_cart_delete(user_id);
                    }
                    else
                    {
                        break;
                    }
                }
                System.out.println("Do you want to checkout? 1 if yes and 0 if no.");
                yorn=sc.nextInt();
                if(yorn==1)
                {
                    
                    ch.cout(user_id);
                }
            }
            else if(choice==3)
            {
                db d=new db();
                d.db_view_wish();
            }
            else if(choice==4)
            {
                db d=new db();
                d.status_check(user_id);
            }
            else
            {
                break;
            }
        }
    }
    /**
     * @brief Handles the existing user login by verifying the user in the database
     * Here we used polymorphism
     * @param mail
     * @param pass
     */
    public void existing(String mail,String pass)       /*polymorphism*/
	{
        db d=new db();
        int p=0;   /*!< check login */
        p=d.check_login(mail,pass);
        if(p!=0)
        {
            user_id=p;
			System.out.println("###########################################################");
            System.out.println(" ... login successful ... ");
			System.out.println("###########################################################");
            after_login_home(user_id);
        }
        else
        {
			System.out.println("###########################################################");
            System.out.println(" ... User not found or incorrect credentials ... ");
			System.out.println("###########################################################");
        }  
    }
    /**
     * @brief Verifying the user credentials
     * Here we implemented polyorphism
     * @param user_id
     * @param pass
     */
    public void existing(int user_id,String pass)       /*polymorphism*/
	{
        int p=0;
        String usr_id="";         /*!< initialize user id */
        db d=new db();
        p=d.check_login(usr_id,pass);
        if(p!=0)
        {
            user_id=p;
			System.out.println("###########################################################");
            System.out.println(" ... login successful ... ");
			System.out.println("###########################################################");
            after_login_home(user_id);
        }
        else
        {
			System.out.println("###########################################################");
            System.out.println(" ... User not found or incorrect credentials ... ");
			System.out.println("###########################################################");
        }
    }
}
	
/**
 * @brief To print the restaurant names and choose the restaurant
 * According to the restaurant display their menu and helps in adding items to the cart
 */
class restaurant
{
    Scanner sc=new Scanner(System.in);
    public void print_rest(int user_id)
    {
        String resname;   /*!< For storing restaurant name */
        db d=new db();
        int res_id=d.choose_res();
        if(res_id==1)
        {
            resname="subbaya_gari_tiffins";
        }
        else if(res_id==2)
        {
            resname="pista_bakerys_house";
        }
        else if(res_id==3)
        {
            resname="moghals_paradise_biryani";
        }
        else if(res_id==4)
        {
            resname="dominos_pizza";
        }
        else
        {
            resname="kfc";
        }
        d.choose_menu(resname);
        int it_id;            /*!< item id */
        int it_count;         /*!< item count */
        while(true)
        {
            System.out.println("Enter an item number of your choice");
            it_id=sc.nextInt();
            if(it_id==199) break;
            cart c=new cart();
            System.out.println("How many of these do you want?");
            it_count=sc.nextInt();
            c.insert_cart(it_id,user_id,it_count);
            System.out.println("Do you still want to add more items? press 1 if yes and 0 if no");
            int s=sc.nextInt();
            int proceed_checkout;
            if(s==0)
            {
                System.out.println("Do you want to check out? Press 1 if yes or 0 if no.");
                proceed_checkout=sc.nextInt();
                if(proceed_checkout==1)
                {
                    checkout ch=new checkout();
                    ch.cout(user_id);
                }
                break;
            }
        }
    }
}

/**
 * @brief Abstract class 
 * shows polymorphism and inheritance
 */
abstract class cart_wishlist{
    abstract void insert_cart(int it_id,int user_id,int it_count);
    abstract void view_cart(int user_id);
}

/**
 * @brief Contains insert cart and view cart options
 * Here polymorphism is shown
 */
class cart extends cart_wishlist                      /*polymorphism*/
{
    void insert_cart(int it_id,int user_id,int it_count)
    {
        db d=new db();
        d.set_insert_cart(it_id, user_id,it_count);
    }
    void view_cart(int user_id)
    {
        db d=new db();
        d.cart_summ(user_id);
    }
}
/**
 * Used to insert food item in wishlist
 */
class wishlist
{
    public void insert_wish()
    {
        System.out.println("Inserted successfully into wishlist");
        db d=new db();
        d.db_view_wish();
    }
}
/**
 * @brief Displays the final bill and gives promo code options
 * Promo codes are applied to the final bill
 * Final payment updated after applying codes
 */
class checkout
{
    Scanner sc=new Scanner(System.in);
    public void cout(int user_id)
    {
        int pro;          /*!< Used for promo codes */
        db d=new db();
        int tprice=d.cart_summ(user_id);          /*!< store total price */
		System.out.println("######################################");
		System.out.println(" ... Proceeded to checkout ... ");
		System.out.println("######################################");
        if(tprice<100) System.out.println("please add more items to cross rs.100");
        else
        {
            System.out.println("Do you want to apply promo code?");
            System.out.println("1.Save50");
            System.out.println("2.Save20");
            System.out.println("3.I don't want any promocode.");
            pro=sc.nextInt();
            if(pro==1)
            {
                int found=d.check_promo(user_id, 1);
                if(found==1)
                {
                    tprice/=2;
                    d.reset_promo(user_id, 1);
                    System.out.println("Now the new amount is "+tprice);
                }
            }
            else if(pro==2)
            {
                int found=d.check_promo(user_id, 2);
                if(found==1)
                {
                    tprice-=tprice/5;
                    d.reset_promo(user_id, 2);
                    System.out.println("Now the new amount is "+tprice);
                }
            }
            else
            {
                System.out.println("amount is "+tprice);
            }
            payment pm=new payment();
            pm.cart_pay(tprice,user_id);
        }
    }
}
/**
 * @brief Implements the payment options
 * Add delivery charges according to the distance to the final amount
 * Base class
 * Implemented inheritance
 */
class payment
{
    /**
     * calculate final bill adding delivery charges  
     * @param tbill
     * @param user_id
     * @return
     */
    public int cal_bill_del(int tbill,int user_id)
    {
        db d=new db();
        int add=d.get_user_address(user_id);       /*!< add charge */
        int charge=d.get_charge(add, 2);           /*!< Initialize charge on the basis of user address*/   
        tbill+=charge;
        return tbill;
    }  
    /**
     * Gives payment options to user
     * @param tbill
     * @param user_id
     */  
    public void cart_pay(int tbill,int user_id)
    {
        Scanner sc=new Scanner(System.in);
        int payment_choice;              /*!< takes users payment choice */
		System.out.println("########################################################");
        System.out.println("Select one of the following payment methods to proceed");
		System.out.println("########################################################");
        System.out.println("1.Debit/Credit card");
        System.out.println("2.Net Banking");
        System.out.println("3.UPI");
        System.out.println("4.Cash on Delivery");
		System.out.println("########################################################");
		System.out.println(" ... Please Enter your choice ... ");
		System.out.println("########################################################");
        payment_choice=sc.nextInt();
        db d=new db();
        if(payment_choice==1)
        {
            card_payment cp=new card_payment();
            cp.card_payment_method(tbill,user_id);
        }
        else if(payment_choice==2)
        {
            net_banking nb=new net_banking();
            nb.net_banking_method(tbill,user_id);
        }
        else if(payment_choice==3)
        {
            upi up=new upi();
            up.upi_method(tbill,user_id);
        }
        else
        {
            System.out.println("Choose online payment only because of covid (for your safety)");
        }
        d.insert_tracking(user_id);
        d.delete_cart_after_payment(user_id);
        System.out.println("Please rate our service from 1 to 5 with 5 being excellent.");
        int rat=sc.nextInt();
        System.out.println("Thank you for rating!");
    }
}
/**
 * @brief Implements payment by card option
 * Display the amount paid by the user
 * Child class 
 */
class card_payment extends payment
{
    Scanner sc=new Scanner(System.in);
    void card_payment_method(int tbill,int user_id)
    {
        tbill=cal_bill_del(tbill, user_id);
        System.out.println("enter your card number");
        long card_no=sc.nextLong();
        String dummy=sc.nextLine();
        System.out.println("Enter card holder name");
        String card_name=sc.nextLine();
        System.out.println("enter your card expiry");
        String card_exp=sc.nextLine();
        System.out.println("enter your cvv");
        int card_cvv=sc.nextInt();
        System.out.println("Processing.....");
		System.out.println("#######################################################################");
        System.out.println("Payment of "+tbill+" rupess made successfully using debit/credit card");
		System.out.println("#######################################################################");
    }
}
/**
 * @brief Implements payment by net banking mode
 * Display the amount paid by the user
 * Child class
 */
class net_banking extends payment
{
    Scanner sc=new Scanner(System.in);
    void net_banking_method(int tbill,int user_id)
    {
        tbill=cal_bill_del(tbill, user_id);
        System.out.println("enter your user name");
        String net_user=sc.nextLine();
        System.out.println("Enter password");
        String net_password=sc.next();
        System.out.println("Processing.....");
		System.out.println("#####################################################################");
        System.out.println("Payment of "+tbill+" rupess made successfully using net banking");
		System.out.println("#####################################################################");
    }
}
/**
 * @brief Implements payment by upi mode
 * Display the amount paid by the user
 * Child class
 */
class upi extends payment
{
    Scanner sc=new Scanner(System.in);
    void upi_method(int tbill,int user_id)
    {
        tbill=cal_bill_del(tbill, user_id);
        System.out.println("enter your upi id");
        String upi_id=sc.next();
        System.out.println("Enter upi pin");
        int upi_pin=sc.nextInt();
        System.out.println("Processing.....");
		System.out.println("############################################################");
        System.out.println("Payment of "+tbill+" rupess made successfully using UPI");
		System.out.println("############################################################");
    }
}
/**
 * Used for all the databse related operations
 */
class db
{
    /**
     * saving user details into database when registered
     * @param name
     * @param password
     * @param email
     * @param address
     */
    public void set_userdetails(String name,String password,String email,int address)
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from final_user_details");  
            int id=0;
            while(rs.next())
            {
                int num=rs.getInt(1);
                if(num>id) id=num;
            }
            id=id+1;  
            stmt.executeUpdate("insert into final_user_details values ('"+id+"','"+name+"','"+password+"','"+email+"','"+address+"')");
            stmt.executeUpdate("insert into user_promo values ('"+id+"',1,1)");   
            con.close();  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }  
    }
    /**
     * verify whether a user is existing in databse or not
     * @param check_name
     * @return
     */
    public int check_name(String check_name)
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from final_user_details");  
            while(rs.next())
            {
                String str=rs.getString(4);
                if(check_name.equals(str))
                {
                    con.close();
                    return 1;
                }
            } 
            con.close();
            return 0;  
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
        return 0; 
    }
    /**
     * checked logged user from the database
     * @param check_name
     * @param pass
     * @return
     */
    public int check_login(String check_name,String pass)
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from final_user_details");  
            while(rs.next())
            {
                String str=rs.getString(4);
                String password=rs.getString(3);
                if(check_name.equals(str) && pass.equals(password))
                {
                    int rrr=rs.getInt(1);
                    con.close();
                    return rrr;
                }
            } 
            con.close();
            return 0;  
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
        return 0; 
    }
    /**
     * Store selected restaurants in the database
     * @return
     */
    public int choose_res()
    {
        Scanner sc=new Scanner(System.in);
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from restaurant");
            int count=1;  
			System.out.println("###########################################################");
			System.out.println("... The restaurants available are ...");
			System.out.println("###########################################################");
            while(rs.next())
            {
                System.out.println(count+"."+"  "+rs.getString(2));
                count++;
            }
			System.out.println("###########################################################");
			System.out.println("... Choose one restaurant from above ... "); 
			System.out.println("###########################################################");
            int opt=sc.nextInt();
            con.close();
            return opt;  
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
        return 0;
    }
    /**
     * Store selected food item in the database
     * @param resname
     */
    public void choose_menu(String resname)
    {
        Scanner sc=new Scanner(System.in);
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs;
			System.out.println("######################################");
			System.out.println(" ... Menu of the restaurant ...");
			System.out.println("######################################");
            if(resname=="subbaya_gari_tiffins")
            {
                rs=stmt.executeQuery("select * from menu where res_name='"+resname+"'"); 
            }
            else if(resname=="pista_bakerys_house")
            {
                rs=stmt.executeQuery("select * from menu where res_name='"+resname+"'");
            }
            else if(resname=="moghals_paradise_biryani")
            {
                rs=stmt.executeQuery("select * from menu where res_name='"+resname+"'");
            }
            else if(resname=="dominos_pizza")
            {
                rs=stmt.executeQuery("select * from menu where res_name='"+resname+"'");
            }
            else
            {
                rs=stmt.executeQuery("select * from menu where res_name='"+resname+"'");
            }
            System.out.println();
            System.out.println("Choose the items you like from below or press 199 if you want to exit");
            while(rs.next())
            {
                System.out.println(rs.getString(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
    }
    /**
     * Implements all the cart related database operations
     * @param it_id
     * @param user_id
     * @param it_count
     */
    public void set_insert_cart(int it_id,int user_id,int it_count)
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from menu");  
            String it_name="";           /*!< add item name */
            int it_price=0;              /*!< add item price */
            while(rs.next())
            {
                int item_id=rs.getInt(1);
                if(item_id==it_id)
                {
                    it_name=rs.getString(2);
                    it_price=rs.getInt(3);
                    it_price*=it_count;
                }
            }
            ResultSet rs1=stmt.executeQuery("select * from cart");  
            int comp1=0;     /*!< comapres if the order is from same restaurant*/
            String compres1="",compres2="";
            int nothing=0;   /*!< count variable */
            while(rs1.next())
            {
                if(user_id==rs1.getInt(1))
                {
                    nothing=1;
                    comp1=rs1.getInt(2);
                    break;
                }
            }
            if(nothing==1)
            {
                ResultSet rs2=stmt.executeQuery("select * from menu");
                while(rs2.next())
                {
                    if(comp1==rs2.getInt(1))
                    {
                        compres1=rs2.getString(4);
                        break;
                    }
                }
                ResultSet rs3=stmt.executeQuery("select * from menu");
                while(rs3.next())
                {
                    if(it_id==rs3.getInt(1))
                    {
                        compres2=rs3.getString(4);
                        break;
                    }
                }
                int yorn;   /*!< yes or no */
                if(!compres1.equals(compres2))
                {
                    stmt.executeUpdate("delete from cart where user_id='"+user_id+"'");
                    stmt.executeUpdate("insert into cart values ('"+user_id+"','"+it_id+"','"+it_name+"','"+it_price+"','"+it_count+"')");
                }
                else
                {
                    stmt.executeUpdate("insert into cart values ('"+user_id+"','"+it_id+"','"+it_name+"','"+it_price+"','"+it_count+"')");
                }
            }
            else
            {
                int coun=stmt.executeUpdate("insert into cart values ('"+user_id+"','"+it_id+"','"+it_name+"','"+it_price+"','"+it_count+"')");
            }
            con.close();  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }  
    }
    /**
     * Displays cart summary from database 
     * @param user_id
     * @return
     */
    public int cart_summ(int user_id)
    {
        Scanner sc=new Scanner(System.in);
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs6=stmt.executeQuery("select * from cart");
            int price=0; 
			System.out.println("#################################################");
			System.out.println(".... Items present in cart are ....");
			System.out.println("#################################################");
            while(rs6.next())
            {
                if(user_id==rs6.getInt(1))
                {
                    System.out.println(rs6.getInt(2)+"  "+rs6.getString(3)+"  "+rs6.getInt(4)+"  "+rs6.getInt(5));
                    price+=rs6.getInt(4);
                }
            }
            System.out.println();
            System.out.println("The total amount for the cart products is "+price);
            con.close();
            return price;
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
        return 0;
    }
    /**
     * Check if promo code available for the user
     * @param user_id
     * @param ind
     * @return
     */
    public int check_promo(int user_id,int ind)
    {
        Scanner sc=new Scanner(System.in);
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs6=stmt.executeQuery("select * from user_promo");
            while(rs6.next())
            {
                if(user_id==rs6.getInt(1))
                {
                    if(ind==1)
                    {
                        return rs6.getInt(2);
                    }
                    else
                    {
                        return rs6.getInt(3);
                    }
                }
            }
            con.close();
            return 0;
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
        return 0;
    }
    /**
     * Reset promo once used
     * @param user_id
     * @param ind
     */
    public void reset_promo(int user_id,int ind)
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            if(ind==1)
            {
                int cnn=stmt.executeUpdate("update user_promo set save50=0 where user_id='"+user_id+"'");
            }
            else
            {
                int cnn=stmt.executeUpdate("update user_promo set save20=0 where user_id='"+user_id+"'");
            }
               
            con.close();  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }  
    }
    /**
     * Implemets tracking 
     * @param user_id
     */
    public void insert_tracking(int user_id)
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement(); 
            ResultSet rs=stmt.executeQuery("select * from track");  
            int id=0;
            while(rs.next())
            {
                int num=rs.getInt(2);
                if(num>id) id=num;
            }
            id=id+1;  
            Calendar c = Calendar.getInstance();
            long timeInSecs = c.getTimeInMillis();
            Time tb=new Time(timeInSecs);
            int delivery_time=1;
            Time te = new Time(timeInSecs + (delivery_time * 60 * 1000));
            int penality= delivery_time + (delivery_time *10 )/100;
            Time tp = new Time(timeInSecs + (penality * 60 * 1000));
            
            int coun=stmt.executeUpdate("insert into track values ('"+user_id+"','"+id+"',0,'"+tb+"','"+te+"','"+tp+"')"); 
            con.close();  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }  
    }
    /**
     * Delete the items while placing the order
     * @param it_id
     * @param user_id
     */
    public void cart_delete(int it_id,int user_id)
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt1=con.createStatement();
            ResultSet rs101=stmt1.executeQuery("select * from cart");  
            while(rs101.next())
            {
                if(it_id==rs101.getInt(2)&&user_id==rs101.getInt(1))
                {
                    stmt1.executeUpdate("delete from cart where user_id='"+user_id+"' and item_id='"+it_id+"'");
                    break;
                }
            }
			System.out.println("######################################################");
			System.out.println(" .. The Item is successfully deleted from the cart ..");
			System.out.println("######################################################");
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
    }
    /**
     * delete entire cart once order is made successfully
     * @param user_id
     */
    public void entire_cart_delete(int user_id)
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt1=con.createStatement();
            ResultSet rs174=stmt1.executeQuery("select * from cart");  
            while(rs174.next())
            {
                if(user_id==rs174.getInt(1))
                {
                    stmt1.executeUpdate("delete from cart where user_id='"+user_id+"'");
                    break;
                }
            }
			System.out.println("######################################################");
			System.out.println(" .. All items are successfully deleted from the cart ..");
			System.out.println("######################################################");
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
    }
    /**
     * Check status of the order
     * @param user_id
     */
    public void status_check(int user_id)
    {
        Scanner sc=new Scanner(System.in);
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs102=stmt.executeQuery("select * from track");
            Time tp=null;    /*!< time */
            int oid=0;       /*!< order id */
            int s=0;
            while(rs102.next())
            {
                if(user_id==rs102.getInt(1))
                {
                    System.out.println(rs102.getInt(2)+"  "+rs102.getInt(3)+"  "+rs102.getTime(4)+"  "+rs102.getTime(5)+"  "+rs102.getTime(6));
                    tp=rs102.getTime(6);
                    oid=rs102.getInt(2);
                    s=rs102.getInt(3);
                }
            }
            Calendar c = Calendar.getInstance();
            long tms = c.getTimeInMillis();
            Time t=new Time(tms);
            String ct=t.toString();
            String pt=tp.toString();
            LocalTime time1 = LocalTime.parse(ct);
            LocalTime time2 = LocalTime.parse(pt);
            int value = time1.compareTo(time2);
            int yorn;   /*!< yes or no */
            if(value>0 && s==0)
            {
                System.out.println("Do you want to cancel the order? 1 yes 2 no");
                yorn=sc.nextInt();
                if(yorn==1)
                {
                    stmt.executeUpdate("delete from track where user_id='"+user_id+"' and order_id='"+oid+"'");
                    System.out.println("Your order has been cancelled successfully and refund will be initiated soon");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
    }
    /**
     * Delete cart once payment is completed
     * @param user_id
     */
    public void delete_cart_after_payment(int user_id)
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt1=con.createStatement();
            ResultSet rs104=stmt1.executeQuery("select * from cart");  
            while(rs104.next())
            {
                if(user_id==rs104.getInt(1))
                {
                    stmt1.executeUpdate("delete from cart where user_id='"+user_id+"'");
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
    /**
     * View wishlist
     */
    public void db_view_wish()
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs112 = stmt.executeQuery("select * from wishlist");
            while(rs112.next())
            {
                System.out.println(rs112.getInt(1)+"  "+rs112.getInt(2));
            }
            con.close();  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }  
    }
    /**
     * Charge based on delivery address details
     * @param addr_1
     * @param addr_2
     * @return
     */
    public int get_charge(int addr_1, int addr_2)
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs119 = stmt.executeQuery("select * from delivery_details");
            while(rs119.next())
            {
                if(addr_1 == rs119.getInt(1))
                {
                    int cha=rs119.getInt(4);
                    return cha;
                }
            }
            con.close();  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }  
        return 0;
    }
    /**
     * Show delivery details of the user 
     * @param user_id
     * @return
     */
    public int get_user_address(int user_id)
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/final_oopd_schema","root","root");    
            Statement stmt=con.createStatement();
            ResultSet rs145 = stmt.executeQuery("select * from delivery_details");
            while(rs145.next())
            {
                if(user_id == rs145.getInt(1))
                {
                    int cha=rs145.getInt(5);
                    return cha;
                }
            }
            con.close();  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }  
        return 0;
    }

}