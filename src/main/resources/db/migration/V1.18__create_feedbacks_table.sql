CREATE TABLE IF NOT EXISTS feedbacks(
  id serial primary key not null,
  user_id integer not null,
  text text not null,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on feedbacks for each row execute procedure set_update_time();