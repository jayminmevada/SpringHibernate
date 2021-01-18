package MavenHibernate.my.CRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InstructorOperations {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();

		session.beginTransaction();
		Instructor tempInstructor = new Instructor("jaymin", "mevada", "abc@gmail.com");
		InstructorDetail tempInsDetail = new InstructorDetail("luv to code", "Cooking");
		tempInstructor.setInstructorDetail(tempInsDetail);
		System.out.println("Saving Insrtuctor Detail ..");
		session.save(tempInstructor);

		session.getTransaction().commit();

	}

}
