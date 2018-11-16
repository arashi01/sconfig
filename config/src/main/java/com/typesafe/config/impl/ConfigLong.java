/**
 *   Copyright (C) 2011-2012 Typesafe Inc. <http://typesafe.com>
 */
package com.typesafe.config.impl;

import java.io.ObjectStreamException;
import java.io.Serializable;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValueType;

final class ConfigLong extends ConfigNumber implements Serializable {

    private static final long serialVersionUID = 2L;

    final private long value;

    ConfigLong(ConfigOrigin origin, long value, String originalText) {
        super(origin, originalText);
        this.value = value;
    }

    @Override
    public ConfigValueType valueType() {
        return ConfigValueType.NUMBER();
    }

    @Override
    public Long unwrapped() {
        return value;
    }

    @Override
    public String transformToString() {
        String s = super.transformToString();
        if (s == null)
            return Long.toString(value);
        else
            return s;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public ConfigLong newCopy(ConfigOrigin origin) {
        return new ConfigLong(origin, value, originalText());
    }

    // serialization all goes through SerializedConfigValue
    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue(this);
    }
}
