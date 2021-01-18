package Basic;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class BiDirectional {

	public static void main(String[] args) {
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.buildSessionFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		
		int theID = 2;
		InstructorDetail tempDetail=(InstructorDetail) session.get(InstructorDetail.class, theID);
		if(tempDetail != null)
		{
			System.out.println("Instructor is:"+tempDetail.getInstructor());
			System.out.println("Instrucor Detail is :"+tempDetail);
			session.delete(tempDetail);
			System.out.println("Delete Success .!!");
		}
		System.out.println("Delete");
		session.getTransaction().commit();
		session.close();
	}

}
