package br.com.aurum.base.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.cloud.datastore.Key;

import br.com.aurum.base.dto.DefaultEntityDto;

public abstract class DefaultEntity {

    @Id
    private Key id;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date dtCreated;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date dtUpdated;

    private boolean active = true;
    
    public DefaultEntity() {}
    
    public DefaultEntity(DefaultEntityDto defaultEntity) {
    	this.setActive(defaultEntity.isActive());
    	this.dtCreated = defaultEntity.getDtCreated();
    	this.dtUpdated = defaultEntity.getDtUpdated();
    }

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public Date getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(Date dtCreated) {
        this.dtCreated = dtCreated;
    }

    public Date getDtUpdated() {
        return dtUpdated;
    }

    public void setDtUpdated(Date dtUpdated) {
        this.dtUpdated = dtUpdated;
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
