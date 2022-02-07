package com.example.cinema.controller.comand;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Interface for all Command implementations, that are used for getting
 * and sending data to View-layer. Part of command pattern.
 *
 */
public interface ActionCommand {
    String execute(HttpServletRequest req);

}
