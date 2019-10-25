CREATE TABLE IF NOT EXISTS purposes (
  id serial primary key not null,
  name text not null,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on purposes for each row execute procedure set_update_time();