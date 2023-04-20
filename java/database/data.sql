BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role, first_name, last_name, email) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'baselineUser', 'baselineUser', 'userc@gmail.com');
INSERT INTO users (username,password_hash,role, first_name, last_name, email) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'baselineAdmin', 'baselineAdmin', 'admin@gmail.com');

INSERT INTO users (username,password_hash,role, first_name, last_name, email, approved) VALUES ('dat','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_RENTER', 'Dat', 'Chung', 'dchung@gmail.com', true);
INSERT INTO users (username,password_hash,role, first_name, last_name, email, approved) VALUES ('david','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_RENTER', 'David', 'Jackson','djackson@gmail.com', true);


INSERT INTO users (username,password_hash,role, first_name, last_name, email, approved) VALUES ('josh','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_LANDLORD', 'Josh', 'Mack', 'jmack@gmail.com', true);
INSERT INTO users (username,password_hash,role, first_name, last_name, email, approved) VALUES ('jane','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_LANDLORD', 'Jane', 'Smith', 'jsmith@gmail.com', true);
INSERT INTO users (username,password_hash,role, first_name, last_name, email, approved) VALUES ('pinky','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_LANDLORD', 'Pinky', 'Brain', 'pbrain@gmail.com', true);

INSERT INTO users (username,password_hash,role, first_name, last_name, email, approved) VALUES ('michael','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_EMPLOYEE', 'Michael', 'Long', 'mlong@gmail.com', true);
INSERT INTO users (username,password_hash,role, first_name, last_name, email, approved) VALUES ('hunter','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_EMPLOYEE', 'Hunter', 'Cohen', 'hcohen@gmail.com', false);

INSERT INTO amenity (amenity_description) VALUES
    ('washer'),
    ('dryer'),
    ('fenced-in yard'),
    ('pool'),
    ('waterfront'),
    ('fitness center'),
    ('wifi'),
    ('air conditioning'),
    ('utilities included'),
    ('backyard'),
    ('balcony'),
    ('gas stove'),
    ('gated access'),
    ('finished basement'),
    ('finished attic'),
    ('tornado shelter'),
    ('patio'),
    ('deck'),
    ('detached garage'),
    ('attached garage'),
    ('carport'),
    ('fireplace'),
    ('cable'),
    ('satellite'),
    ('garbage disposal'),
    ('backup generator'),
    ('EV charger'),
    ('natural gas heating'),
    ('septic tank'),
    ('carbon monoxide detectors'),
    ('clubhouse'),
    ('community garden'),
    ('detached shed'),
    ('dog park');

INSERT INTO property (rent, property_type, bathrooms, bedrooms, square_footage, date_available, street_number, street_name, apartment_unit, city, state_abbreviation, zip, pets_allowed, property_description)
VALUES
    (14170.00, 'house', 6, 9, 5662, '2023-06-01', '261', 'Western Promenade', NULL, 'Portland', 'ME', 04102, true, 'Experience the charm and allure of life on Portland''s historic Western Promenade. You step out on the front porch, bathed in the glow of another sunset. The music from the summer concert series echoes from the Western Promenade Park, just steps from your front door. Portland''s vibrant food scene awaits you at one of the renowned restaurants in the Old Port. This elegant Colonial-Revival style home has been carefully restored and boasts original architectural details including ornate moldings, wainscotting, high ceilings, grand bay windows and six custom finished fireplaces. Appreciate over 6,000 square feet of living space including 9 bedrooms and 7 bathrooms. Imagine relaxing with a book in the stately black walnut library, or perhaps make it the ultimate home office. Enjoy a cocktail in the sitting room while surrounded by a mural reported to be done by John Calvin Stevens, and you feel like you are stepping back into the past. A trip through the butler''s pantry to an updated kitchen with modern amenities brings you back to the current time. Upstairs the elegant master bedroom has a sitting room, walk-in closet, full bath and private porch deck to better enjoy the last rays of that sunset. As a nationally registered historic place, this property offers the perfect blend of history and the comfort of modern living, and offers rental opportunities if this is your luxury second home. Don''t miss out on this once in a lifetime opportunity to own a piece of Portland''s past, in one of the city''s most sought after neighborhoods, close to everything this vibrant city has to offer. Contact us for a showing today.'),

    (12975.00, 'apartment', 3, 3, 1795, '2023-05-15', '383', 'Commercial Street', 'Unit 610', 'Portland', 'ME', 04101, false, 'Experience premiere city living at Portland''s newest condominium community, located across from Portland''s downtown waterfront. Hobson''s Landing sets a new bar for downtown living, with a location and amenities beyond compare.The full-year monthly lease rate of $12,975 is fully inclusive of all utilities and Spectrum high-speed wireless internet and expanded cable tv, plus one parking spot in the secured 2nd floor garage, which can only be accessed with the remote control. Additional garage parking space available right behind the building at $120/month. Flexible start date. One-year leases available on 6/15/23 at $12,975/mo, fully inclusive.  This beautiful new condominium complex sets a new bar for downtown Portland living. The building includes a workout facility, a community room for community and private events, weekly resident happy hours and other social events, it''s a very social community, secure 2nd floor designated stall garage parking, a car charging station, shared bikes and even a dog washrooms for residents. Secured entries, staffed with a concierge weekdays.'),

    (6028.00, 'condo', 3, 2, 1705, '2023-04-15', '305', 'Chandlers Wharf', 'Unit 305', 'Portland', 'ME', 04101, true, 'Unique opportunity to own this spacious harbor front condo and boat slip that can accommodate your 30 ft boat! Chandler''s Wharf is ideally located in the heart of the Old Port- renowned for it''s charming cobblestone streets, working waterfront, world acclaimed restaurants and trendy boutiques. With direct entry from the courtyard, this townhouse style boater''s oasis is in the end building affording you unobstructed views of Portland Harbor from the interior living spaces, adjoining deck and Primary bedroom. Relax in front of the cozy gas fireplace in the Living Room or enjoy creating your favorite foods in the kitchen while entertaining your family and friends. Other amenities include 2 covered parking spaces, a 24/7 gated guard house and central air conditioning. This spacious Old Port home and boat slip are ready for your summer enjoyment!'),

    (16846.00, 'house', 4, 6, 5930, '2023-06-01', '375', 'Spring Street', NULL, 'Portland', 'ME', 04102, true, 'This stunning home on the corner of Spring & Neal St, across from Waynflete, has long been one of Portland''s most admired homes, and offers a singular opportunity for gracious living in the West End. It is sure to impress from the very moment you enter.  This brick Italianate masterpiece was commissioned by former Maine Governor and founding member of the Republican party, Israel Washburn, Jr., who also lent Abraham Lincoln support for his Emancipation Proclamation. The attention to detail is clear in every facet of the home. The entryway features 15-ft ceilings w/decorative crown moldings, cornices, a grand staircase w/ a beautifully carved finial, plus a stained-glass window that welcomes guests. This home is elegant, yet comfortable. The first floor includes a formal liv rm, a spectacular library w/pocket door, a spacious din rm perfect for entertaining, plus a den, each rm with its own fireplace. An eat-in kitchen & 1/2 bath complete the first floor. The second floor has a grand center foyer, along w/4 BRs, including a primary BR w/an attached sunroom to bring the outdoors in - the perfect place to relax and read a book. In addition to an en-suite primary bath, there is a family bath, laundry & lots of closet space. The third floor offers flexible space w/its 2 additional BRs, a kitchenette, bath & central family rm, and is an ideal space for guests, family or staff. Step outside and enjoy a wonderful yard, expertly landscaped to provide privacy.  Bask in the sun on your brick patio or enjoy your favorite beverage on the side covered porch. There is an original brick carriage house, designed to match the main house, now used as a garage. Minutes to Portland''s Old Port & Jetport, walking distance to a plethora of restaurants, museums and shopping, and just a 2-hour drive to Boston. This is a unique opportunity to own one of Portland''s most exceptional homes in one of its most desirable neighborhoods.'),

    (2150.00, 'apartment', 1, 2, 987, '2023-04-15', '132', 'Marginal Way', NULL, 'Portland', 'ME', 04101, false, 'Newly remodeled apartments in a refurbished building in the heart of West Bayside. The Linden offers a prime location, with everything you need right outside your doorstep. You are steps away from the Back Cove, Trader Joes, Whole Foods Market, gyms, restaurants, cafes, the Post Office, medical facilities, and more! The modern open-concept apartments feature great natural light and Back Cove, City and courtyard views. The modern kitchens feature updated cabinetry, fixtures, appliances, granite countertops, and dishwashers. New flooring throughout. The spacious bathrooms feature modern vanities, full tubs, and stacked washer/dryer units.'),

    (1868.00, 'condo', 1, 1, 445, '2023-05-01', '207', 'Fore Street', 'Unit 9', 'Portland', 'ME', 04101, true, 'Portland Old Port living at it''s best. Welcome to 207 Fore Street. This two bedroom, two bath downtown condo is comprised of 12 privately owned units on the top floor of a prominent hotel that has dedicated elevator access to the upper level. Once inside your unit you''ll be met with the warmth of a southern exposure through your windows that overlook the hustle and bustle of Portland''s business district and working wharfs. Snuggle up in front of the fireplace, entertain guests with the open concept eat-in kitchen. Fresh carpet in both bedrooms. Two full baths, one with laundry and the other located in the primary bedroom, alongside the deep walk-in closet. Why wait for new construction nearby, when you can call this one home right now! The association is a completely separate entity from the hotel.');

INSERT INTO amenity_property (property_id, amenity_id)
VALUES
    (1, 1), (1, 2), (1, 3), (1, 7), (1, 8), (1, 10), (1, 14), (1, 20), (1, 25), (1, 30), (1, 33),
    (2, 4), (2, 6), (2, 7), (2, 23), (2, 9), (2, 30),
    (3, 1), (3, 2), (3, 20), (3, 8), (3, 16), (3, 7), (3, 18), (3, 34), (3, 27), (3, 26), (3, 31), (3, 30),
    (4, 1), (4, 2), (4, 5), (4, 10), (4, 11), (4, 22), (4, 12), (4, 29), (4, 19), (4, 33), (4, 30),
    (5, 11), (5, 4), (5, 7), (5, 8), (5, 13), (5, 22), (5, 21), (5, 6), (5, 34),
    (6, 4), (6, 6), (6, 7), (6, 23), (6, 9), (6, 30);

INSERT INTO image (property_id, image_url)
VALUES
    (1, 'https://photos.zillowstatic.com/fp/593939f2a12a6dc2d0a7879d6eaaa201-cc_ft_384.webp'),
    (1, 'https://photos.zillowstatic.com/fp/8bb83d96e40adb15a241d5882b60ace7-cc_ft_384.webp'),
    (1, 'https://photos.zillowstatic.com/fp/15957e9b599a34fa244fce930a4c8bad-cc_ft_384.webp'),
    (2, 'https://photos.zillowstatic.com/fp/7aeb6d078a04ebd04c2c04a5fd0a0527-cc_ft_384.webp'),
    (2, 'https://photos.zillowstatic.com/fp/3db350ff9908221ff1e1cb4a30c5b782-cc_ft_384.webp'),
    (2, 'https://photos.zillowstatic.com/fp/c459dbb20b3e048b4b4bfbe7ef3b7512-cc_ft_384.webp'),
    (3, 'https://photos.zillowstatic.com/fp/df9aea1a10ef3cbdbbb98044bb5860cc-cc_ft_384.webp'),
    (3, 'https://photos.zillowstatic.com/fp/1f4876409c0ca98de932582cdd419d72-cc_ft_384.webp'),
    (3, 'https://photos.zillowstatic.com/fp/4cb75eafaf574abfd79f7c2c37917cb4-cc_ft_384.webp'),
    (4, 'https://photos.zillowstatic.com/fp/37ff60c721bf2c8a9dee368f135592a1-cc_ft_384.webp'),
    (4, 'https://photos.zillowstatic.com/fp/39f137478e568bfa9f23a60f74081fd2-cc_ft_384.webp'),
    (4, 'https://photos.zillowstatic.com/fp/a282461d942eed6fe3cbf846c369453e-cc_ft_384.webp'),
    (5, 'https://photos.zillowstatic.com/fp/6bd06482b6f0a8496c817815594a63ce-cc_ft_384.webp'),
    (5, 'https://photos.zillowstatic.com/fp/c1d3385a0b7faccbf28d67e931d9b0e0-cc_ft_384.webp'),
    (5, 'https://photos.zillowstatic.com/fp/c3d0b68a76c471d1d7635c9501778e0d-cc_ft_384.webp'),
    (6, 'https://photos.zillowstatic.com/fp/670553d51caf7b19c92159a8167bea9b-cc_ft_384.webp'),
    (6, 'https://photos.zillowstatic.com/fp/5cc46df449ca52c1c79c1b6b07154413-cc_ft_384.webp'),
    (6, 'https://photos.zillowstatic.com/fp/555cb6c4a7f8520d357021e9bcb06cc8-cc_ft_384.webp');


INSERT INTO property_landlord (property_id, landlord_id)
VALUES
(1, 5),
(2, 5),
(3, 6),
(4, 6),
(5, 6),
(6, 5);

INSERT INTO property_renter (renter_id, property_id)
VALUES
(3, 1),
(4, 2);

INSERT INTO rent (rent_status, property_id, rent_due_date, balance)
VALUES
    ('DUE', 1, '2023-05-01', 1500.00),
    ('PAID', 2, '2023-05-15', 0),
    ('OVERDUE', 3, '2023-06-01', 2000.00);

INSERT INTO maintenance (property_id, renter_id, employee_id, maintenance_status, request_details, notes)
VALUES (1, 3, 8, 'IN PROGRESS', 'toilet leaking', 'replaced tank');

INSERT INTO maintenance (property_id, renter_id, maintenance_status, request_details)
VALUES (4, 4, 'REQUESTED', 'loose faucet');

COMMIT TRANSACTION;