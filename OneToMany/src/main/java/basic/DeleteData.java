package basic;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteData {

	public static void main(String[] args) {
	SessionFactory factory=new Configuration().configure().buildSessionFactory();
	Session session=factory.openSession();
	
	session.beginTransaction();
	
	//get COurse
	int theID=3;
//	Course  tempCourse=(Course) session.get(Course.class, theID);
//	
//	//Delete Course ..
//	System.out.println("Course is Delete ..."+tempCourse);
//	session.delete(tempCourse);
	
	Instructor tempInst=(Instructor) session.get(Instructor.class,theID );
//	Course course=new Course();
	//List<Course> tempCourse2= course.setInstructor(tempInst);
	
	System.out.println();
	session.createQuery("delete from Course where instructor=:inst").setParameter("inst", tempInst).executeUpdate();
	
//	System.out.println(tempCourse2);
//	session.delete(tempCourse2);
	session.getTransaction().commit();
	}
}
