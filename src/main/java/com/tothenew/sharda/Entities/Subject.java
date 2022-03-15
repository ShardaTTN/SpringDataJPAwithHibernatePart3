package com.tothenew.sharda.Entities;

import javax.persistence.Embeddable;

@Embeddable
public class Subject {
	
	private String subjectname;

	Subject() {
		
	}
	
	public Subject (String subjectname) {
		this.subjectname = subjectname;
	}
	
	public String getSubjectname() {
		return subjectname;
	}
}