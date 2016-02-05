package br.com.erudio.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import groovy.transform.EqualsAndHashCode;

import org.hibernate.annotations.ForeignKey;


@SuppressWarnings("deprecation")
@Entity
@Table (name="person")
@EqualsAndHashCode(includes = 'idPerson')
class Person implements Serializable {
    
    private static final long serialVersionUID =  1L;
    
    @Id
    @GeneratedValue
    @Column(name="IdPerson", nullable=false)
    Integer idPerson;
    
    @Column (name="Name", nullable = false, length = 80 )
    String name;
    
    @Column (name="Email", nullable = false, length = 80 )
    String email;
    
    @Column (name="Phone", nullable = false, length = 16 )//(034)-98888-8888
    String phone;
    
    @Column (name="CPF", nullable = false, length = 14 )
    String cpf;
    
    @Column (name="BirthDayDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    Date birthDayDate;
    
    @Column (name="InsertDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    Date insertDate;
    
        
    @Column(name = "Login", unique=true, length = 25)
    String login;
    
    @Column(name = "Password", length = 40)
    String password;
    
    @Column(name = "Permission", length = 36)
    String permission;
    
    @OneToOne(cascade = CascadeType.ALL)
    @ForeignKey(name="PersonAddress")
    @JoinColumn(name="IdAddress", referencedColumnName = "IdAddress")
    Address address;
    
    @ManyToOne(optional=false)
    @ForeignKey(name = "PersonGender") 
    @JoinColumn(name="IdGender", referencedColumnName = "IdGender")
    Gender gender;

    Person() {
        this.gender = new Gender();
    }  
}