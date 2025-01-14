package org.opentosca.container.core.next.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.opentosca.container.core.next.trigger.SituationListener;

@Entity
@Table(name = Situation.TABLE_NAME)
@EntityListeners({SituationListener.class})
public class Situation extends PersistenceObject {

    private static final long serialVersionUID = 1065969908430273145L;

    public static final String TABLE_NAME = "SITUATION";

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private String thingId;

    @Column(nullable = false)
    private String situationTemplateId;    
    
    public boolean isActive() {
        return this.active;
    }
    
    

    public void setActive(final boolean active) {
        this.active = active;
    }

    public String getThingId() {
        return this.thingId;
    }

    public void setThingId(final String thingId) {
        this.thingId = thingId;
    }

    public String getSituationTemplateId() {
        return this.situationTemplateId;
    }

    public void setSituationTemplateId(final String situationTemplateId) {
        this.situationTemplateId = situationTemplateId;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Situation entity = (Situation) o;
        if(entity.getId().equals(this.id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
