/*
 * openwms.org, the Open Warehouse Management System.
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software. If not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.core.domain.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * An ApplicationPreference is used to store a setting in application scope.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
@XmlType(name = "applicationPreference", namespace = "http://www.openwms.org/schema/preferences")
@Entity
@Table(name = "COR_APP_PREFERENCE", uniqueConstraints = @UniqueConstraint(columnNames = { "C_TYPE", "C_KEY" }))
@NamedQueries({ @NamedQuery(name = ApplicationPreference.NQ_FIND_ALL, query = "select ap from ApplicationPreference ap") })
public class ApplicationPreference extends AbstractPreference {

    private static final long serialVersionUID = -2942285512161603092L;
    /**
     * Query to find all <code>ApplicationPreference</code>s.
     */
    public static final String NQ_FIND_ALL = "ApplicationPreference" + FIND_ALL;

    /**
     * Type of this preference. Default is {@value} .
     */
    @XmlTransient
    @Enumerated(EnumType.STRING)
    @Column(name = "C_TYPE")
    private PropertyScope type = PropertyScope.APPLICATION;

    /**
     * Key value of the preference.
     */
    @XmlAttribute(name = "key", required = true)
    @Column(name = "C_KEY")
    private String key;

    /**
     * Create a new ApplicationPreference. Defined for the JAXB implementation.
     */
    ApplicationPreference() {
        super();
    }

    /**
     * Create a new ApplicationPreference.
     * 
     * @param key
     *            the key
     */
    public ApplicationPreference(String key) {
        // Called from the client.
        super();
        this.key = key;
    }

    /**
     * Get the key.
     * 
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.openwms.core.domain.system.AbstractPreference#getType()
     */
    @Override
    public PropertyScope getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.openwms.core.domain.system.AbstractPreference#getFields()
     */
    @Override
    protected Object[] getFields() {
        return new Object[] { this.getType(), this.getKey() };
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.openwms.core.domain.system.AbstractPreference#getPrefKey()
     */
    @Override
    public PreferenceKey getPrefKey() {
        return new PreferenceKey(this.getType(), this.getKey());
    }

    /**
     * {@inheritDoc}
     * 
     * Uses key and type for hashCode calculation.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * Comparison done with key and type fields. Not delegated to super class.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ApplicationPreference other = (ApplicationPreference) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }

}
