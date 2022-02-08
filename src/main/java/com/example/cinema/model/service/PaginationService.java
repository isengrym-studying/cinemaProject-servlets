package com.example.cinema.model.service;

public class PaginationService {
    private static PaginationService paginationService;

    public static synchronized PaginationService getInstance() {
        if (paginationService == null) {
            paginationService = new PaginationService();
        }
        return paginationService;
    }

    public int сountPagesQuantity(int totalOnPage, int itemsQuantity) {
        if (itemsQuantity % totalOnPage != 0) return itemsQuantity/totalOnPage + 1;
        else return itemsQuantity/totalOnPage;
    }
}
