import entities.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import entities.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        System.out.println("Добавлення ролі адмін");
        try {
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
           // Role role = new Role("Admin");
           // session.save(role);
          //  tx1.commit();
           // session.close();

            Scanner scanner = new Scanner(System.in);
            String userRole;
            System.out.println("Введіть роль:");
            userRole = scanner.nextLine();
            Role role = new Role(userRole);
            session.save(role);
            tx1.commit();
            session.close();

        }
        catch (Exception ex) {
            System.out.println("Проблема при додавані ролі "+ex.getMessage());
        }
        System.out.println("Якщо ви ще бачите. значить усе норм.");


    }
}
