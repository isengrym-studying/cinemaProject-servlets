package com.example.cinema.service;

/**
 * The class contains all the logic for pagination.
 * This layer is located between DAO and Controller layers
 *
 */
public class PaginationService {
    private static PaginationService paginationService;

    public static synchronized PaginationService getInstance() {
        if (paginationService == null) {
            paginationService = new PaginationService();
        }
        return paginationService;
    }

    /**
     * Method for getting quantity of pages. Simply dividing
     * itemsQuantity on totalOnPage. If it has no remainder, method
     * simply returns the result of division. If it has remainder, method returns
     * the result of division plus 1
     *
     * @param totalOnPage quantity of items on one page
     * @param itemsQuantity overall quantity of items
     * @return Returns true (If there were no issues).
     * Returns false (If there are issues).
     *
     */
    public int сountPagesQuantity(int totalOnPage, int itemsQuantity) {
        if (itemsQuantity % totalOnPage != 0) return itemsQuantity/totalOnPage + 1;
        else return itemsQuantity/totalOnPage;
    }
}
