package basic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo
{
	public static void main(String[] args)
	{
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.buildSessionFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		int theId=3;
		Instructor tempInstructor=(Instructor) session.get(Instructor.class, theId);
		
		Course tempCourse=new Course("2Air1 Guitar -The ultimate Gui2de");
		Course tempCourse2=new Course("T33133e1 Pinb2all master");
		Course tempCourse3=new Course("Vact555or1 D2esign");
		Course tempCourse4=new Course("Mas44ter1 G2ames");
		
		tempInstructor.add(tempCourse);
		tempInstructor.add(tempCourse2);
		tempInstructor.add(tempCourse3);
		tempInstructor.add(tempCourse4);
		
		session.save(tempCourse);
		session.save(tempCourse2);
		session.save(tempCourse3);
		session.save(tempCourse4);
		
		session.getTransaction().commit();		
	}
}