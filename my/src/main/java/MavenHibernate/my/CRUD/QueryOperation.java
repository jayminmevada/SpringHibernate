package MavenHibernate.my.CRUD;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryOperation {

	public static void main(String[] args)
	{
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session=factory.getCurrentSession();
		
		session.beginTransaction();
		//Query Get All student 
		List<Student> theStudent=session.createQuery("from Student").list();
		//Retrive ALl student using for loop
		displayStudent(theStudent);
		
		//query student :firstName:jaymin
		theStudent=session.createQuery("from Student where first_name='jaymin'").list();
		displayStudent(theStudent);
		
		//query student :first_name or last_name
		
		theStudent=session.createQuery("from Student s where"
				+" s.first_name='kajal' OR s.last_name='mevada'").list();
		System.out.println("=====OR Query Perform ===");
		displayStudent(theStudent);
		
		//query student :Like first_name
		theStudent=session.createQuery("from Student s where s.first_name LIKE 'jaymin'").list();
		System.out.println("==========Like Operation ......");
		displayStudent(theStudent);
				
		session.getTransaction().commit();
	}

	private static void displayStudent(List<Student> theStudent) {
		for(Student temp :theStudent)
		{
			System.out.println("Check All :"+temp);
		}
	}

}
