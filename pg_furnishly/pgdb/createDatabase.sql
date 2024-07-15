CREATE TABLE IF NOT EXISTS type (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(255) UNIQUE NOT NULL
); 
CREATE TABLE IF NOT EXISTS manufacturer (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(255) UNIQUE NOT NULL,
	address VARCHAR(255),
	country VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS furniture (
	id SERIAL NOT NULL PRIMARY KEY,
	type_id INT,
	name VARCHAR(255),
	description VARCHAR(255),
	manufacturer_id INT,
	price DECIMAL(10, 2),
    quantity INT,
	CONSTRAINT furniture_type_id FOREIGN KEY(type_id) REFERENCES type(id),
  	CONSTRAINT furniture_manufactourer_id FOREIGN KEY(manufacturer_id) REFERENCES manufacturer(id)
);

INSERT INTO type VALUES (1, 'Chair'), (2, 'Table'), (3, 'Bed'), (4, 'Desk'), (5, 'Sofa'), (6, 'Wardrobe');
INSERT INTO manufacturer VALUES 
(1, 'Studio Furniture', 'Pixie Hollow Lane, Enchanted Forest, FAE-123', 'Belarus'),
(2, 'DreamCraft', 'Starship Avenue, Nebula City, GAL-456', 'France'),
(3, 'Fantasia', 'Quantum Avenue, Parallel City, QUA-123', 'Italy'),
(4, 'Quantum', 'Stardust Street, Nebula Heights, STA-456', 'Russia'),
(5, 'Nook', 'Willow Lane, Enchanted Meadows, ELV-567', 'Greece');
INSERT INTO furniture VALUES 
(1, 1, 'Chairg', 'Whether you’re relaxing, working, or dining, a comfortable chair can make all the difference!', 2, 111.23, 3),
(2, 2, 'Tableg', 'Whether you’re sharing a meal, working on a project, or displaying items, a well-chosen table enhances functionality and aesthetics in any space!', 3, 4344.12, 5),
(3, 3, 'Bedg', 'Whether you prefer a cozy wooden bed with intricate carvings or a sleek modern platform bed, a comfortable and inviting bed is essential for a good night’s sleep!', 1, 123.43, 6),
(4, 4, 'Deskg', 'Whether you’re tackling tasks or pursuing creative endeavors, a well-organized and comfortable desk can enhance productivity!', 5, 54674.89, 2),
(5, 5, 'Sofag', 'Whether you’re relaxing with a book, hosting guests, or binge-watching your favorite show, a comfortable sofa adds warmth and style to your living space!', 4, 74534.23, 1),
(6, 6, 'Wardrobeg', 'Whether you need to organize your clothes, store shoes, or keep your winter coats, a well-designed wardrobe helps keep your space clutter-free!', 2, 3424.34, 4);