package miu.edu.attendance.domain;

import javax.persistence.Embeddable;

import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class Address implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}