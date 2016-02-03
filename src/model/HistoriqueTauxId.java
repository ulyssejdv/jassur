package model;
// Generated 6 janv. 2016 11:04:49 by Hibernate Tools 4.3.1.Final

import java.util.Date;

/**
 * HistoriqueTauxId generated by hbm2java
 */
public class HistoriqueTauxId implements java.io.Serializable {

	private Date date;
	private int idPret;

	public HistoriqueTauxId() {
	}

	public HistoriqueTauxId(Date date, int idPret) {
		this.date = date;
		this.idPret = idPret;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIdPret() {
		return this.idPret;
	}

	public void setIdPret(int idPret) {
		this.idPret = idPret;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HistoriqueTauxId))
			return false;
		HistoriqueTauxId castOther = (HistoriqueTauxId) other;

		return ((this.getDate() == castOther.getDate()) || (this.getDate() != null && castOther.getDate() != null
				&& this.getDate().equals(castOther.getDate()))) && (this.getIdPret() == castOther.getIdPret());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getDate() == null ? 0 : this.getDate().hashCode());
		result = 37 * result + this.getIdPret();
		return result;
	}

}