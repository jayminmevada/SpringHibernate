 package basic;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Entity.Course;
import Entity.Instructor;
import Entity.Student;

public class DeleteCourseAndStudent
{
	public static void main(String[] args)
	{
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		
		session.beginTransaction();
		int theID=19;
		Course tempCourse=(Course) session.get(Course.class,theID);
		System.out.println(tempCourse);
		System.out.println("Students Related .."+tempCourse.getStudents().toString());
		session.delete(tempCourse);
		session.getTransaction().commit();	
	}
}
