--id, name, city, state, description, and category.

--ALTER SEQUENCE category_id_seq RESTART 1000;
--ALTER SEQUENCE city_id_seq RESTART 1000;
--ALTER SEQUENCE space_id_seq RESTART 1000;
--ALTER SEQUENCE venue_id_seq RESTART 1000;


--select venue.id as venue_id, venue.name as venue_name, city.name as city, city.state_abbreviation as state, category.name as category_name
select *
from venue
join city on venue.city_id = city.id 
join state on city.state_abbreviation = state.abbreviation
join category_venue on category_venue.venue_id = venue.id
join category on category_venue.category_id = category.id
--group by venue.id
where venue.id = 1
order by venue.id asc
;


select * from reservation;

select space_id from reservation where start_date = to_date('2021-02-20', 'yyyy-mm-dd');
select to_date('2021-02-20', 'yyyy-mm-dd') from reservation;
select ADDDATE("2021-02-10", INTERVAL 10 DAY) from reservation;
ADDDATE("2017-06-15", INTERVAL 10 DAY);
DATEADD(year, 1, '2017/08/25')




select *
from category_venue;