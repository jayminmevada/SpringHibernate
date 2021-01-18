package MavenHibernate.my.CRUD;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudent 
{

	public static void main(String[] args)
	{
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.getCurrentSession();
		int studId=1;
		session.beginTransaction();
		
		Student myStudent=(Student) session.get(Student.class, studId);
		System.out.println("Get Complete :"+myStudent);
		
		System.out.println("Updating Student...");
		myStudent.setFirst_name("Jay");
		
		List<Student> myStud=session.createQuery("from Student").list();
		
		displayData(myStud);
		//Update All Student
		session=factory.getCurrentSession();
		session.beginTransaction();
		System.out.println("Udate ALl STudent Mail id....");
		session.createQuery("update Student set email='xyz@gmail.com'").executeUpdate();
		
		displayData(myStud);
		session.getTransaction().commit();
	}

	private static void displayData(List<Student> myStud) {
		for(Student temp:myStud)
		{
			System.out.println("Display :"+temp);
		}
	}

}
