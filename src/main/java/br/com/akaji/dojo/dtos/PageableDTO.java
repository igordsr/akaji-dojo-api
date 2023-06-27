package br.com.akaji.dojo.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.akaji.dojo.interfaces.DataTransferObject;

@Getter
@Setter
@NoArgsConstructor
public class PageableDTO {

    private Integer page;
    private Integer size;
    private List<DataTransferObject> content = new ArrayList<>();
    private PaginationDTO pagination;
    private Long totalElements;

    public PageableDTO(Page<DataTransferObject> resultSet) {
        this.content = resultSet.getContent();
        this.totalElements = resultSet.getTotalElements();
        Pageable previousOrFirstPage = resultSet.getPageable().previousOrFirst();
        Pageable currentPage = resultSet.getPageable();
        Pageable nextPage = resultSet.getPageable().next();
        this.pagination = new PaginationDTO(resultSet.getTotalPages(), previousOrFirstPage.getPageNumber(),
                currentPage.getPageNumber(), nextPage.getPageNumber());
    }

    public static PageableDTO getNewInstancePageableDTO(Integer page, Integer size) {
        PageableDTO pageableDTO = new PageableDTO();
        pageableDTO.setPage(page);
        pageableDTO.setSize(size);
        return pageableDTO;
    }
}
