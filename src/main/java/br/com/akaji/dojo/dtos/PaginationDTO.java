package br.com.akaji.dojo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaginationDTO {
    private Integer totalPages;
    private Integer previousPage;
    private Integer currentPage;
    private Integer nextPage;

    public PaginationDTO(int totalPages, int previousPage, int currentPage, int nextPage) {
        this.totalPages = totalPages;
        this.previousPage = previousPage;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
    }
}
