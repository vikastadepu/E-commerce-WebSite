package dev.vikas.productservice.Repository;

import dev.vikas.productservice.models.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Lazy
public interface CategoryRepository
        extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String categoryName);

//    Optional<Category> findById(Long uuid);
//
//    @Override
//    List<Category> findAllById(Iterable<Long> ids);
//
//    Optional<Category> findByName(String category);
}