package com.check.api.response;

import java.time.Instant;


public class GenericResponse {
    protected Integer code;
    protected String description;
    protected Instant dateResponse;
    public GenericResponse (){
    }
    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDateResponse() {
        return this.dateResponse;
    }

    public void setDateResponse(Instant dateResponse) {
        this.dateResponse = dateResponse;
    }

    public GenericResponse(Integer code, String description, Instant dateResponse) {
        this.code = code;
        this.description = description;
        this.dateResponse = dateResponse;
    }

    public GenericResponse code(Integer code) {
        this.code = code;
        return this;
    }

    public GenericResponse description(String description) {
        this.description = description;
        return this;
    }

    public GenericResponse dateResponse(Instant dateResponse) {
        this.dateResponse = dateResponse;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", dateResponse='" + getDateResponse() + "'" +
            "}";
    }

}