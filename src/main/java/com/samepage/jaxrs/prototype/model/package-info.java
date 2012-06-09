@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type = Date.class, value = DateAdapter.class),
    @XmlJavaTypeAdapter(type = DateMidnight.class, value = DateMidnightAdapter.class),
    @XmlJavaTypeAdapter(type = DateTime.class, value = DateTimeAdapter.class),
    @XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class),
    @XmlJavaTypeAdapter(type = LocalTime.class, value = LocalTimeAdapter.class),
    @XmlJavaTypeAdapter(type = LocalDateTime.class, value = LocalDateTimeAdapter.class)
}) package com.samepage.jaxrs.prototype.model;

import com.samepage.jaxb.java.DateAdapter;
import com.samepage.jaxb.joda.DateMidnightAdapter;
import com.samepage.jaxb.joda.DateTimeAdapter;
import com.samepage.jaxb.joda.LocalDateAdapter;
import com.samepage.jaxb.joda.LocalDateTimeAdapter;
import com.samepage.jaxb.joda.LocalTimeAdapter;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.util.Date;
