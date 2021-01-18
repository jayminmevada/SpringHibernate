package Basic;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class DeleteData {

	public static void main(String[] args) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();

		int theID=1;
		Instructor temp=(Instructor) session.get(Instructor.class,theID);
		session.beginTransaction();
		if(temp != null)
		{
			System.out.println("Deleting Instructor :"+temp);
			// Note : Also Delete INstructor_detail Object becuase we select Cascade_ALL
			session.delete(temp);
		}
		session.getTransaction().commit();
	}

}
