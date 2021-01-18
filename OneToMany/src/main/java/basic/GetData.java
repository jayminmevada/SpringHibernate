package basic;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetData {

	public static void main(String[] args) {
	SessionFactory factory=new Configuration().configure().buildSessionFactory();
	Session session=factory.openSession();
	
	session.beginTransaction();
	
	List<Course> tempCourse= session.createQuery("from Course").list();
	for(Course temp:tempCourse)
	{
		System.out.println("Course is :"+temp);
	}
	
	//================
	int theID=3;
	Instructor tempInstructor=(Instructor) session.get(Instructor.class, theID);
	System.out.println("..Instructor is ...."+tempInstructor);
	
	System.out.println("----Course of Above Instrucotr ..."+tempInstructor.getCourse() +"\n");
	
	
	session.getTransaction().commit();

	}

}
