package com.adcubum.persistence.entity;

public interface State<H extends Head> {

    H getHead();

}
