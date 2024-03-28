package java12.repository;

import jakarta.transaction.Transactional;
import java12.dto.response.SubCategoryResponse;
import java12.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {

    @Query("select new java12.dto.response.SubCategoryResponse(s.id, s.name) " +
           "from  Category  c " +
           "join c.subCategories s " +
           "where c.id = :categoryId " +
           "order by s.name")
    List<SubCategoryResponse> getAllSubs(Long categoryId);

    @Query("select c.name, new java12.dto.response.SubCategoryResponse(s.id, s.name) " +
           "from Category c " +
           "join c.subCategories s group by c.name, s.id, s.name")
    Map<String, List<Object[]>> group();


    @Modifying
    @Transactional
    @Query(value = "delete from categories_sub_categories where sub_categories_id =:subCategoryId ",nativeQuery = true)
    void deleteMenu(Long subCategoryId);

    @Modifying
    @Transactional
    @Query(value = "delete from restaurant_menu_items where menu_items_id IN (select menu_items_id from sub_categories_menu_items where sub_category_id = :subCategoryId)", nativeQuery = true)

    void deleteRes(Long subCategoryId);
}
