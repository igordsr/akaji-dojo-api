package br.com.akaji.dojo.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "registrations")
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar enrollmentDate;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "group_id", nullable = false)
	private Group group;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@OneToMany(mappedBy = "registration", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AttendanceList> attendanceList;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar created;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Calendar updated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Calendar enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<AttendanceList> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<AttendanceList> attendanceList) {
		this.attendanceList = attendanceList;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public Calendar getUpdated() {
		return updated;
	}

	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}

}
