package example.model;

import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditQuery;

import java.util.List;
import example.entity.DogEntity;

public class Dog{
  Session session = null;
  public Dog(Session s){session = s;}

  public void insert(String breed, String color) throws Exception {
    if (color == null)
      throw new Exception("must provide color");
    else if (breed == null)
      throw new Exception("must provide breed");

		session.beginTransaction();

		DogEntity dog = new DogEntity();
    dog.setBreed(breed);
    dog.setColor(color);

		session.save(dog);

		session.getTransaction().commit();
  }
  public void selectAll(){

		session.beginTransaction();

    String hql = "FROM DogEntity";
    Query query = session.createQuery(hql);
    List<DogEntity> lst = query.list();
    for (DogEntity entity : lst)
      System.out.println(entity.toString());

		session.getTransaction();
  }
  public void selectAuditAll(){

	//	session.beginTransaction();
    AuditReader auditReader = AuditReaderFactory.get(session);

    AuditQuery query = auditReader.createQuery()
      .forRevisionsOfEntity(DogEntity.class, false, true);

    List<Object[]> lst = query.getResultList();

    lst.forEach( obj -> {
      DogEntity entity = (DogEntity) obj[0];
      DefaultRevisionEntity rev = (DefaultRevisionEntity) obj[1];
      RevisionType revType = (RevisionType) obj[2];
      String revResult = "RevId: " + rev.getId();
      revResult += " RevDate: " + rev.getRevisionDate();
      revResult += " RevOpt: " + revType.name();
      String entityResult = revResult + " " + entity.toString();
      System.out.println(entityResult);
    });

		//session.getTransaction();
  }
}
