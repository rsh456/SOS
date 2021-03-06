/*
 * Copyright (C) 2012-2019 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public
 * License version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 */
package org.n52.sos.config.sqlite.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.n52.faroe.SettingValue;

import com.google.common.base.MoreObjects;

/**
 * @param <T> settings type
 * <p/>
 * @author Christian Autermann <c.autermann@52north.org>
 */
@Entity(name = "settings")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractSettingValue<T> implements SettingValue<T>, Serializable {
    private static final long serialVersionUID = -6354179246534360077L;

    @Id
    private String identifier;


    public AbstractSettingValue(String identifier) {
        this.identifier = identifier;
    }

    public AbstractSettingValue() {
        this(null);
    }

    @Override
    public String getKey() {
        return this.identifier;
    }

    @Override
    public void setKey(String key) {
        this.identifier = key;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SettingValue<?>)) {
            return false;
        }

        SettingValue<?> that = (SettingValue<?>) obj;
        return Objects.equals(this.getType(), that.getType()) &&
               Objects.equals(this.getKey(), that.getKey()) &&
               Objects.equals(this.getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getKey(), getValue());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", getType())
                .add("key", getKey())
                .add("value", getValue())
                .toString();
    }
}
