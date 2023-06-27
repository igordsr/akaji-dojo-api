package br.com.akaji.dojo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.akaji.dojo.interfaces.DataTransferObject;

@Entity
@Table(name = "instructors")
@PrimaryKeyJoinColumn(name = "id")
public class Instructor extends User {

	@OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Classroom> classes;

	@Override
	public DataTransferObject toDto() {
		// TODO Auto-generated method stub
		return null;
	}

}
