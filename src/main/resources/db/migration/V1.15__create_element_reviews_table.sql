CREATE TABLE IF NOT EXISTS element_reviews (
  id serial primary key not null,
  element_id integer not null,
  user_id integer not null,
  title text not null,
  body text not null,
  evaluation float,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on element_reviews for each row execute procedure set_update_time();