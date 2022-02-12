package com.example.cinema.controller.tags;

import com.example.cinema.model.entity.Seance;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class StartTimeTag extends TagSupport {
    private Seance seance;
    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    @Override
    public int doStartTag() throws JspException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(seance.getStartDate().getHour());
        stringBuilder.append(":");
        stringBuilder.append(seance.getStartDate().getMinute());
        if (seance.getStartDate().getMinute() == 0) stringBuilder.append("0");
        try {
            pageContext.getOut().write(stringBuilder.toString());
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;

    }
}
