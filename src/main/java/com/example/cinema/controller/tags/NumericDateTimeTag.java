package com.example.cinema.controller.tags;

import com.example.cinema.model.entity.Seance;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NumericDateTimeTag extends TagSupport {
    private LocalDateTime date;
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public int doStartTag() throws JspException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = date.format(formatter);
        try {
            pageContext.getOut().write(formattedDateTime);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;

    }
}