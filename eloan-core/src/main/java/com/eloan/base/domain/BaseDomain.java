package com.eloan.base.domain;

import java.io.Serializable;

/**
 * @author Dingying
 * @create 2016-12-25 0:49
 **/
public class BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
