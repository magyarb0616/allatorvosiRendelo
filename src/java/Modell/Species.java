/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bence
 */
@Entity
@Table(name = "species")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Species.findAll", query = "SELECT s FROM Species s")
    , @NamedQuery(name = "Species.findBySpeciesId", query = "SELECT s FROM Species s WHERE s.speciesId = :speciesId")
    , @NamedQuery(name = "Species.findByName", query = "SELECT s FROM Species s WHERE s.name = :name")
    , @NamedQuery(name = "Species.findByIsActive", query = "SELECT s FROM Species s WHERE s.isActive = :isActive")})
public class Species implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "species_id")
    private Integer speciesId;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "species")
    private Collection<Animal> animalCollection;

    public Species() {
    }

    public Species(Integer speciesId, String name, Boolean isActive) {
        this.speciesId = speciesId;
        this.name = name;
        this.isActive = isActive;
    }
    
    
    public Species(Integer speciesId) {
        this.speciesId = speciesId;
    }

    public Integer getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Integer speciesId) {
        this.speciesId = speciesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (speciesId != null ? speciesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Species)) {
            return false;
        }
        Species other = (Species) object;
        if ((this.speciesId == null && other.speciesId != null) || (this.speciesId != null && !this.speciesId.equals(other.speciesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modell.Species[ speciesId=" + speciesId + " ]";
    }
    
}
