package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException; */

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BookStoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookListCommand(BookRepository brepository, CategoryRepository crepository,
			UserRepository urepository) {
		return (args) -> {

			// Some demo data to db

			log.info("Few test book");

			Category history = new Category("History");
			Category biography = new Category("Biography");
			Category fiction = new Category("Fiction");
			Category philosophical = new Category("Philosophical");

			crepository.save(history);
			crepository.save(biography);
			crepository.save(fiction);
			crepository.save(philosophical);

			//>>>>>>>>>>>>>>>>The code below must be run only once.<<<<<<<<<<<<<<<<<<<<<<

/* 			brepository.save(new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011,
					"978-952-279-470-3", 12.00, history));
			brepository.save(new Book("Homo Deus: A Brief History of Tomorrow", "Yuval Noah Harari", 2016,
					"978-191-070-187-4", 15.00, history));
			brepository.save(new Book("Zen and the Art of Motorcycle Maintenance", "Robert M. Pirsig", 1974,
					"0-688-00230-7", 17.00, fiction)); */

			// Creating users: admin/ADMIN, user/USER
			// User user1 = new User("user", "$2y$10$.iS2KHPDEoh1sRqBWqq/mO5K6xi/hdFGc6UX5NeYyEY2MFmDTIzMa", "USER"); //***80/ hame kuchik
			// User user2 = new User("admin", "$2y$10$gAjGxeixeSXvFMW6SrJwHOSyu9A.ia1rmlX9XF0mq4S8vF24t99c6", "ADMIN"); // mese balai, eki dar miun
			// urepository.save(user1);
			// urepository.save(user2);

			log.info("Showing your books");

			for (Book book : brepository.findAll()) {
				log.info(book.toString());

			}

		};
	}
}
