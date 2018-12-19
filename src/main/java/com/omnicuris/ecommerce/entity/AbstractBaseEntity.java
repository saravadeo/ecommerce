package com.omnicuris.ecommerce.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Abstract entiry initializer for setting new uuid and created and update date
 * 
 * @author Onkar Saravade
 *
 */
@MappedSuperclass
@EntityListeners({ AbstractBaseEntity.AbstractEntityListener.class })
public abstract class AbstractBaseEntity implements Serializable {

  public static class AbstractEntityListener {

    @PrePersist
    public void onPrePersist(final AbstractBaseEntity abstractEntity) {
      abstractEntity.getUuid();
      if (abstractEntity.getCreated() == null) {
        abstractEntity.setCreated(ZonedDateTime.now());
      }
      abstractEntity.setUpdated(ZonedDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate(final AbstractBaseEntity abstractEntity) {
      abstractEntity.setUpdated(ZonedDateTime.now());
    }
  }

  private static final long serialVersionUID = 1379292578095507955L;

  @Column(nullable = false, updatable = false)
  private ZonedDateTime created;

  @Column(nullable = false)
  private ZonedDateTime updated;

  @Id
  @Expose
  protected String uuid;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final AbstractBaseEntity other = (AbstractBaseEntity) obj;
    if (uuid == null) {
      if (other.uuid != null)
        return false;
    } else if (!uuid.equals(other.uuid)) {
      return false;
    }
    return true;
  }

  public ZonedDateTime getCreated() {
    return created;
  }

  public String getId() {
    return uuid;
  }

  public ZonedDateTime getUpdated() {
    return updated;
  }

  public String getUuid() {
    if (uuid == null) {
      uuid = UUID.randomUUID().toString();
    }
    return uuid;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
    return result;
  }

  public void setCreated(final ZonedDateTime created) {
    if (this.created == null && created != null) {
      this.created = created;
    }
  }

  public void setUpdated(final ZonedDateTime updated) {
    this.updated = updated;
  }

  public void setUuid(final String uuid) {
    this.uuid = uuid;
  }

}
