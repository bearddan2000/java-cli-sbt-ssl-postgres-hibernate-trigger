package example.entity;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.envers.Audited;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, optimisticLock = OptimisticLockType.ALL)
@Audited
@Table(name = "dog", uniqueConstraints = {@UniqueConstraint(columnNames = "ID") })
public class DogEntity implements Serializable {

  private static final long serialVersionUID = -1798070786993154676L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(nullable = false, length = 10)
  private String color;

  @Column(nullable = false, length = 100)
  private String breed;

  // Accessors and mutators for all three fields

  public Long getId(){ return id;}
  public void setId(Long value){id = value;}

  public String getColor(){ return color;}
  public void setColor(String value){color = value;}

  public String getBreed(){ return breed;}
  public void setBreed(String value){breed = value;}

  @Override
  public String toString(){return String.format("[OUTPUT] %d, breed=%s, color=%s", id, breed, color);}
}
