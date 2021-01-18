package Basic;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class InsertData {

	public static void main(String[] args) {
	
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
										.buildSessionFactory();
		Session session=factory.openSession();
		
		Instructor temp=new Instructor("Mevada","Jiger","sdm@mail.com");
		InstructorDetail tempDetail=new InstructorDetail("luv to code","FootBall");
		
		
		Instructor temp2=new Instructor("Mevada","Jaymin","luv@mail.com");
		InstructorDetail tempDetail2=new InstructorDetail("Play Game","FootBall");
		
		
		Instructor temp3=new Instructor("Mevada","Kajal","xyz@mail.com");
		InstructorDetail tempDetail3=new InstructorDetail("luv to cook","Cooking");
		
		temp.setInstructorDetail(tempDetail);
		temp2.setInstructorDetail(tempDetail2);
		temp3.setInstructorDetail(tempDetail3);
		session.beginTransaction();
		
		session.save(temp);
		session.save(temp2);
		session.save(temp3);
		System.out.println("Save Instructor...");
		session.getTransaction().commit();
	}
}
