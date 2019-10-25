CREATE TABLE IF NOT EXISTS element_images (
  id serial primary key not null,
  element_id integer not null,
  image_path text not null,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on element_images for each row execute procedure set_update_time();