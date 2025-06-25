package com.keakimleang.demoelk.books;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

}
