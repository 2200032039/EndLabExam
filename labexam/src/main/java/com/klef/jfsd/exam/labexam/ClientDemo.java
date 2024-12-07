package com.klef.jfsd.exam.labexam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
       
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

    
        Customer customer1 = new Customer();
        customer1.setName("Alice");
        customer1.setEmail("alice@example.com");
        customer1.setAge(25);
        customer1.setLocation("New York");

        Customer customer2 = new Customer();
        customer2.setName("Bob");
        customer2.setEmail("bob@example.com");
        customer2.setAge(30);
        customer2.setLocation("London");

        session.save(customer1);
        session.save(customer2);

    
        Criteria criteria = session.createCriteria(Customer.class);

     
        criteria.add(Restrictions.eq("location", "New York"));
        List<Customer> equalResult = criteria.list();
        System.out.println("Customers in New York: " + equalResult);

       
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.ne("location", "London"));
        List<Customer> notEqualResult = criteria.list();
        System.out.println("Customers not in London: " + notEqualResult);

       
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.gt("age", 28));
        List<Customer> greaterThanResult = criteria.list();
        System.out.println("Customers older than 28: " + greaterThanResult);

  
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.between("age", 20, 30));
        List<Customer> betweenResult = criteria.list();
        System.out.println("Customers aged between 20 and 30: " + betweenResult);

        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.like("name", "A%"));
        List<Customer> likeResult = criteria.list();
        System.out.println("Customers whose name starts with A: " + likeResult);

        transaction.commit();
        session.close();
        factory.close();
    }
}

