package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the BLOGUSER database table.
 * 
 */
@Entity
@Table(name="Bloguser",schema="TESTDB")
@NamedQuery(name="Bloguser.findAll", query="SELECT b FROM Bloguser b")
public class Bloguser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private long userId;

	private String email;

	@Column(name="USER_PASSWORD")
	private String userPassword;

	//bi-directional many-to-one association to Bullhorn
	@OneToMany(mappedBy="bloguser")
	private List<Bullhorn> bullhorns;

	public Bloguser() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<Bullhorn> getBullhorns() {
		return this.bullhorns;
	}

	public void setBullhorns(List<Bullhorn> bullhorns) {
		this.bullhorns = bullhorns;
	}

	public Bullhorn addBullhorn(Bullhorn bullhorn) {
		getBullhorns().add(bullhorn);
		bullhorn.setBloguser(this);

		return bullhorn;
	}

	public Bullhorn removeBullhorn(Bullhorn bullhorn) {
		getBullhorns().remove(bullhorn);
		bullhorn.setBloguser(null);

		return bullhorn;
	}

}