import entities.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import entities.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.postgresql.core.SqlCommand;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Добавлення ролі адмін");
        int action=0;
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory().openSession();
        try {
            do {
                System.out.println("0. Вихід.");
                System.out.println("1. Додати роль.");
                System.out.println("2. Список ролей.");
                System.out.println("3. Видалення ролей.");
                action = Integer.parseInt(scanner.nextLine());
                switch (action) {
                    case 1: {
                        try {
                            Transaction tx1 = session.beginTransaction();
                            String userRole;
                            System.out.println("Введіть роль:");
                            userRole = scanner.nextLine();
                            Role role = new Role(userRole);
                            session.save(role);
                            tx1.commit();
                            System.out.println("-----Додвання успішне-----");
                        } catch (Exception ex) {
                            System.out.println("Довання не успішне ОТВАЛ!!!");
                        }
                        break;
                    }

                    case 2: {
                        try {
                            System.out.println("Список ролей");
                            String hql = "FROM Role";
                            Query query = session.createQuery(hql);
                            List<Role> results = query.list();
                            for (Role role : results) {
                                System.out.println("Id = "+role.getId()+"\t"+role.getName());
                            }
                        } catch (Exception ex) {
                            System.out.println("Читання даних ОТВАЛ!!!");
                        }
                        break;
                    }

                    case 3: {
                        try {
                            Transaction tx1 = session.beginTransaction();
                            System.out.println("Введіть іd ролі:");
                            int id = Integer.parseInt(scanner.nextLine());
                            Role role =  HibernateSessionFactoryUtil.getSessionFactory()
                                    .openSession().get(Role.class, id);
                            session.delete(role);
                            tx1.commit();
                            System.out.println("Успішно видалено");
                        } catch (Exception ex) {
                            System.out.println("Видалення даних ОТВАЛ!!!"+ ex.getMessage());
                        }
                        break;
                    }
                }
            }
            while(action != 0);
        }
        catch (Exception ex) {
            System.out.println("Проблема при додавані ролі "+ex.getMessage());
        }
        session.close();


    }
}
