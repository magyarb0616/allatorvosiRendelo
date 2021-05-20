/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bence
 */
@Entity
@Table(name = "medicalhistory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicalhistory.findAll", query = "SELECT m FROM Medicalhistory m")
    , @NamedQuery(name = "Medicalhistory.findByHistoryId", query = "SELECT m FROM Medicalhistory m WHERE m.historyId = :historyId")
    , @NamedQuery(name = "Medicalhistory.findByRegistry", query = "SELECT m FROM Medicalhistory m WHERE m.registry = :registry")
    , @NamedQuery(name = "Medicalhistory.findByIsActive", query = "SELECT m FROM Medicalhistory m WHERE m.isActive = :isActive")})
public class Medicalhistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "history_id")
    private Integer historyId;
    @Size(max = 255)
    @Column(name = "registry")
    private String registry;
    @Column(name = "is_active")
    private Boolean isActive;
    @JoinColumn(name = "animal_id", referencedColumnName = "animal_id")
    @ManyToOne
    private Animal animalId;

    public Medicalhistory() {
    }

    public Medicalhistory(Integer historyId, String registry, Boolean isActive, Animal animalId) {
        this.historyId = historyId;
        this.registry = registry;
        this.isActive = isActive;
        this.animalId = animalId;
    }
    
    

    public Medicalhistory(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Animal getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Animal animalId) {
        this.animalId = animalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historyId != null ? historyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicalhistory)) {
            return false;
        }
        Medicalhistory other = (Medicalhistory) object;
        if ((this.historyId == null && other.historyId != null) || (this.historyId != null && !this.historyId.equals(other.historyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modell.Medicalhistory[ historyId=" + historyId + " ]";
    }
    
}
