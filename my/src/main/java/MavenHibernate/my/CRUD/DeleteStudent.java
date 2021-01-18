package MavenHibernate.my.CRUD;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.getCurrentSession();
		
		session.beginTransaction();
		session.createQuery("delete from Student where id=2").executeUpdate();
		
		List<Student> myList=session.createQuery("from Student").list();
		
		for(Student temp:myList)
		{
			System.out.println("New Data :"+temp);
		}
		
		session.beginTransaction();
		int id=1;
		Student std=(Student) session.get(Student.class, id);
		session.delete(std);
		myList=session.createQuery("from Student").list();
		
		for(Student temp:myList)
		{
			System.out.println("New Data 1 :"+temp);
		}
		session.getTransaction().commit();
		
	
	}

}
