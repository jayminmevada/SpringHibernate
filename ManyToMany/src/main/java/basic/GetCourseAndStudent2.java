 package basic;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Entity.Course;
import Entity.Instructor;
import Entity.Student;

public class GetCourseAndStudent2
{
	public static void main(String[] args)
	{
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		
		session.beginTransaction();
		int theID=3;
		Student tempStudent=(Student) session.get(Student.class,theID);
		System.out.println("\nLoaded Student is ..."+tempStudent);
		System.out.println("\nCourses of selected Student is .."+tempStudent.getCourse());
		
		session.getTransaction().commit();	
	}
}
