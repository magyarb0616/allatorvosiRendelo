/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bence
 */
@Entity
@Table(name = "owner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Owner.findAll", query = "SELECT o FROM Owner o")
    , @NamedQuery(name = "Owner.findByOwnerId", query = "SELECT o FROM Owner o WHERE o.ownerId = :ownerId")
    , @NamedQuery(name = "Owner.findByName", query = "SELECT o FROM Owner o WHERE o.name = :name")
    , @NamedQuery(name = "Owner.findByBirthdate", query = "SELECT o FROM Owner o WHERE o.birthdate = :birthdate")
    , @NamedQuery(name = "Owner.findByPhone", query = "SELECT o FROM Owner o WHERE o.phone = :phone")
    , @NamedQuery(name = "Owner.findByCity", query = "SELECT o FROM Owner o WHERE o.city = :city")
    , @NamedQuery(name = "Owner.findByAddress", query = "SELECT o FROM Owner o WHERE o.address = :address")
    , @NamedQuery(name = "Owner.findBySex", query = "SELECT o FROM Owner o WHERE o.sex = :sex")
    , @NamedQuery(name = "Owner.findByIsActive", query = "SELECT o FROM Owner o WHERE o.isActive = :isActive")})
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "owner_id")
    private Integer ownerId;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "phone")
    private String phone;
    @Size(max = 255)
    @Column(name = "city")
    private String city;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Column(name = "sex")
    private Boolean sex;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "owner")
    private Collection<Animal> animalCollection;

    public Owner() {
    }

    public Owner(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Collection<Animal> getAnimalCollection() {
        return animalCollection;
    }

    public void setAnimalCollection(Collection<Animal> animalCollection) {
        this.animalCollection = animalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ownerId != null ? ownerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Owner)) {
            return false;
        }
        Owner other = (Owner) object;
        if ((this.ownerId == null && other.ownerId != null) || (this.ownerId != null && !this.ownerId.equals(other.ownerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modell.Owner[ ownerId=" + ownerId + " ]";
    }
    
}
