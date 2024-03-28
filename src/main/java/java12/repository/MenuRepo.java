package java12.repository;

import jakarta.transaction.Transactional;
import java12.dto.response.MenuResponse;
import java12.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepo extends JpaRepository<MenuItem, Long> {

    @Query("select new java12.dto.response.MenuResponse(m.id, m.name, m.image, m.price, m.description, m.isVegetarian) " +
           "from MenuItem m " +
           "where m.name ilike concat('%', :keyword, '%') ")
    List<MenuResponse> search(String keyword);

    @Query("select new java12.dto.response.MenuResponse(m.id, m.name, m.image, m.price, m.description, m.isVegetarian) " +
           "from MenuItem  m  " +
           "join m.name")
    List<MenuResponse> sort(String word);

    @Query("select m from MenuItem m where  m.id in (:menu)")
    List<MenuItem> getAllMenuId(List<Long> menu);

    @Modifying
    @Transactional
    @Query(value = "delete from sub_categories_menu_items where menu_items_id =:menuId ",nativeQuery = true)
    void deleteMenu(Long menuId);

    @Modifying
    @Transactional
    @Query(value = "delete from restaurant_menu_items where menu_items_id =:menuId ",nativeQuery = true)
    void deletRes(Long menuId);

//    @Query("select m from  MenuItem m where m.id in (:menuIDs)")
//    List<MenuItem> getAllMenuId(List<Long> menuIds);
}
