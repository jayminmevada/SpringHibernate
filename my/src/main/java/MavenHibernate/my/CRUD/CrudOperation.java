package MavenHibernate.my.CRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CrudOperation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		System.out.println("Cnfig and HBM files loaded Successfull");
		Session session=factory.openSession();
		session.beginTransaction();
		System.out.println("Transaction Begin");
		Student tempStudent=new Student("jaymin","mevada","abc@gmail.com");
		Student tempStudent1=new Student("kajal","mevada","abc@gmail.com");
		System.out.println("Query Write"+tempStudent);
		System.out.println("Query Write"+tempStudent1);
		session.save(tempStudent);
		session.save(tempStudent1);
		session.getTransaction().commit();
		System.out.println("Create SuccessFull");
	}

}
