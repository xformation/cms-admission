package com.synectiks.admission.domain.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CmsCommonVo  implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String createdBy;
    private LocalDate createdOn;
    private String updatedBy;
    private LocalDate updatedOn;
    private String status;
    private String strCreatedOn;
    private String strUpdatedOn;
    private Long errorCode;
    private String errorDescription;


	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDate getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStrCreatedOn() {
		return strCreatedOn;
	}
	public void setStrCreatedOn(String strCreatedOn) {
		this.strCreatedOn = strCreatedOn;
	}
	public String getStrUpdatedOn() {
		return strUpdatedOn;
	}
	public void setStrUpdatedOn(String strUpdatedOn) {
		this.strUpdatedOn = strUpdatedOn;
	}
	public Long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Long errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CmsCommonVo that = (CmsCommonVo) o;
        return Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdOn, that.createdOn) &&
            Objects.equals(updatedBy, that.updatedBy) &&
            Objects.equals(updatedOn, that.updatedOn) &&
            Objects.equals(status, that.status) &&
            Objects.equals(strCreatedOn, that.strCreatedOn) &&
            Objects.equals(strUpdatedOn, that.strUpdatedOn) &&
            Objects.equals(errorCode, that.errorCode) &&
            Objects.equals(errorDescription, that.errorDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdBy, createdOn, updatedBy, updatedOn, status, strCreatedOn, strUpdatedOn, errorCode, errorDescription);
    }
}
