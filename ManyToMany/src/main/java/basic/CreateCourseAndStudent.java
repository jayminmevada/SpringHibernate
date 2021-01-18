 package basic;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Entity.Course;
import Entity.Instructor;
import Entity.Student;

public class CreateCourseAndStudent
{
	public static void main(String[] args)
	{
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		
		session.beginTransaction();
		//First Instructor Fetch ..For Course;
		int instructorId=4;
		Instructor tempInstructor=(Instructor) session.get(Instructor.class,instructorId);
		
		//Create Course ....
		Course tempCourse=new Course("Java Begin 1");
		Course tempCourse2=new Course("Dot Net Begin2");
		Course tempCourse3=new Course("Angular Begin3");
		tempInstructor.add(tempCourse);
		tempInstructor.add(tempCourse2);
		tempInstructor.add(tempCourse3);
		session.save(tempInstructor);
		
		//With Instructor Id Course is Created ...	
		
		System.out.println("Course is .."+tempCourse);
		session.save(tempCourse);
		session.save(tempCourse2);
		session.save(tempCourse3);
		System.out.println("Course is .save."+tempCourse);
		
		Student tempStudent=new Student("John","Doe","john@luv.com");
		Student tempStudent2=new Student("Rihan","Doe","Rihan@luv.com");
		
		tempCourse.addStudent(tempStudent);
		tempCourse.addStudent(tempStudent2);
		
		session.save(tempStudent);
		session.save(tempStudent2);
		
		System.out.println("Save Student...."+tempCourse.getStudents());
		
		//Create Courses For Student..
		tempStudent.addCourse(tempCourse2);
		tempStudent.addCourse(tempCourse3);
		session.save(tempStudent);
		session.save(tempCourse2);
		session.save(tempCourse3);
		
		System.out.println("Save Course ..."+tempStudent.getCourse());
		session.getTransaction().commit();
	
	}

}
