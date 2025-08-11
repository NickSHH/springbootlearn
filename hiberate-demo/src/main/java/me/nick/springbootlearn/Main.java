package me.nick.springbootlearn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import me.nick.springbootlearn.entity.User;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        
        User user = new User();
        user.setUsername("Nick");
        
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();

        session.close();

    }
}