package it.theboys.project0002api.dto.http.response;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PagedSetWithCardsResponseDto<T, I> {
    private int totalPages;
    private int currentPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private long totalItems;
    private int itemsPerPage;
    private int pageSize;
    I info;
    List<T> items;

    public void setPageStats(Page pg, List<T> docs) {
        isFirstPage = pg.isFirst();
        isLastPage = pg.isLast();
        currentPage = pg.getNumber() + 1;
        pageSize = pg.getSize();
        totalPages = pg.getTotalPages();
        totalItems = pg.getTotalElements();
        itemsPerPage = pg.getNumberOfElements();
        items=docs;


    }

}
