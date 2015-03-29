package com.example.EMR_Admin.authentication.data;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;


import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "physician")
public class Physician {
	@Id
	@Column(name = "physician_id")
	@GeneratedValue
	private int physicianId;

	@Column(name = "physician_name")
	private String physicianName;

	@Column(name = "physician_gender")
	private String physicianGender;


	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name = "physician_birthday")
	//@JsonSerialize(using = CustomDateSerializer.class)
	private Date physicianBirthday;

	@Column(name = "account")
	private String account;

	@Column(name = "password")
	private String password;

	public int getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}

	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public String getPhysicianGender() {
		return physicianGender;
	}

	public void setPhysicianGender(String physicianGender) {
		this.physicianGender = physicianGender;
	}

	public Date getPhysicianBirthday() {
		return physicianBirthday;
	}

	public void setPhysicianBirthday(Date physicianBirthday) {
		this.physicianBirthday = physicianBirthday;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//CustomDateSerializer class
	public class CustomDateSerializer extends JsonSerializer<Date> {    
	    @Override
	    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws 
	        IOException, JsonProcessingException {      

	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedDate = formatter.format(value);

	        gen.writeString(formattedDate);

	    }
	}

}