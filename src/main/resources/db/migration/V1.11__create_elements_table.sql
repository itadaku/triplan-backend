CREATE TABLE IF NOT EXISTS elements (
  id serial primary key not null,
  name text not null,
  body text not null,
  link_url text not null,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on elements for each row execute procedure set_update_time();