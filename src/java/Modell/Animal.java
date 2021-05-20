/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bence
 */
@Entity
@Table(name = "animal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a")
    , @NamedQuery(name = "Animal.findByAnimalId", query = "SELECT a FROM Animal a WHERE a.animalId = :animalId")
    , @NamedQuery(name = "Animal.findByName", query = "SELECT a FROM Animal a WHERE a.name = :name")
    , @NamedQuery(name = "Animal.findByBirthdate", query = "SELECT a FROM Animal a WHERE a.birthdate = :birthdate")
    , @NamedQuery(name = "Animal.findBySex", query = "SELECT a FROM Animal a WHERE a.sex = :sex")
    , @NamedQuery(name = "Animal.findByLastVaccinated", query = "SELECT a FROM Animal a WHERE a.lastVaccinated = :lastVaccinated")
    , @NamedQuery(name = "Animal.findByIsActive", query = "SELECT a FROM Animal a WHERE a.isActive = :isActive")})
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "animal_id")
    private Integer animalId;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Column(name = "sex")
    private Boolean sex;
    @Column(name = "last_vaccinated")
    @Temporal(TemporalType.DATE)
    private Date lastVaccinated;
    @Column(name = "is_active")
    private Boolean isActive;
    @JoinColumn(name = "species", referencedColumnName = "species_id")
    @ManyToOne
    private Species species;
    @JoinColumn(name = "owner", referencedColumnName = "owner_id")
    @ManyToOne
    private Owner owner;

    public Animal() {
    }

    public Animal(Integer animalId) {
        this.animalId = animalId;
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getLastVaccinated() {
        return lastVaccinated;
    }

    public void setLastVaccinated(Date lastVaccinated) {
        this.lastVaccinated = lastVaccinated;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (animalId != null ? animalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.animalId == null && other.animalId != null) || (this.animalId != null && !this.animalId.equals(other.animalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modell.Animal[ animalId=" + animalId + " ]";
    }
    
}
