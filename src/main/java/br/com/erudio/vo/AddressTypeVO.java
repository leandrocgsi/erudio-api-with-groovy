package br.com.erudio.vo;

import java.io.Serializable;

public class AddressTypeVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer idAddressType;
    private String description;

    public AddressTypeVO() {}

	public Integer getIdAddressType() {
		return idAddressType;
	}

	public void setIdAddressType(Integer idAddressType) {
		this.idAddressType = idAddressType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAddressType == null) ? 0 : idAddressType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressTypeVO other = (AddressTypeVO) obj;
		if (idAddressType == null) {
			if (other.idAddressType != null)
				return false;
		} else if (!idAddressType.equals(other.idAddressType))
			return false;
		return true;
	}
}