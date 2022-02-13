package com.example.cinema.controller.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class NowEpochSeconds extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;

    }
}