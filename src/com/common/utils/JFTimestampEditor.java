package com.common.utils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class JFTimestampEditor extends PropertyEditorSupport  {
    private final SimpleDateFormat dateFormat;
    private final boolean allowEmpty;
    private final int exactDateLength;

    public JFTimestampEditor(SimpleDateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = -1;
    }

    public JFTimestampEditor(SimpleDateFormat dateFormat,
            boolean allowEmpty, int exactDateLength) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = exactDateLength;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        if ((this.allowEmpty)&&(!(org.springframework.util.StringUtils.hasText(text)))) {
            setValue(null);
        } else {
            if ((text != null) && (this.exactDateLength>= 0)
                    &&(text.length() != this.exactDateLength)) {
                throw new IllegalArgumentException(
                        "Could not parse date: it is not exactly"
                                + this.exactDateLength + "characters long");
            }
            try {
                setValue(new Timestamp(this.dateFormat.parse(text).getTime()));
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: "
                        + ex.getMessage(), ex);
            }
        }
    }

    public String getAsText() {
        Timestamp value = (Timestamp) getValue();
        return ((value != null) ? this.dateFormat.format(value) : "");
    }
}
