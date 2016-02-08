package br.com.erudio.model;

import java.io.Serializable

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey

import com.fasterxml.jackson.annotation.JsonIgnore;

import groovy.transform.EqualsAndHashCode;

import br.com.erudio.model.AddressType;
import br.com.erudio.model.City;
import br.com.erudio.model.PublicAreaType;
import br.com.erudio.model.State;

@SuppressWarnings("deprecation")
@Entity
@Table(name="address")
@EqualsAndHashCode(includes = 'idAddress')
class Address implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name="IdAddress", nullable=false)
    Integer idAddress;
    
    @Column(name="Neighborhood", length=80)
    String neighborhood;
    
    @Column (name="StreetName", length=80)
    String streetName;
    
    @Column (name="PostalCode", length=9)
    String postalCode;
    
    @Column (name="Number")
    Integer number;
    
    @Column (name="Complement")
    String complement;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @ForeignKey(name="AddressPublicAreaType")
    @JoinColumn(name = "IdPublicAreaType", referencedColumnName = "IdPublicAreaType")
    PublicAreaType publicAreaType;
    
    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @ForeignKey(name="AddressState")
    @JoinColumn(name = "IdState", nullable = false)
    State state;
        
    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @ForeignKey(name="AddressAddressType")
    @JoinColumn(name = "IdAddressType", referencedColumnName="IdAddressType")
    AddressType addressType;
    
    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @ForeignKey(name="AddressCity")
    @JoinColumn(name = "IdCity", referencedColumnName="IdCity")
    City city;   
	
	@ManyToMany (cascade = CascadeType.ALL)
	@JsonIgnore
	@ForeignKey(name="AddressPersonAddress")
	@JoinTable(name="person_address", joinColumns=@JoinColumn(name="IdAddress"), inverseJoinColumns=@JoinColumn(name="IdPerson"))
	List<Person> persons;

    Address() {
        this.city = new City();
        this.state = new State();
        this.publicAreaType = new PublicAreaType();
        this.addressType = new AddressType();
    }
}