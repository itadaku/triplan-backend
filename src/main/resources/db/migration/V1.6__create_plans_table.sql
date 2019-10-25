CREATE TABLE IF NOT EXISTS plans (
  id serial primary key not null,
  title text not null,
  days_nights integer not null,
  min_budget integer not null,
  max_budget integer not null,
  number_of_people integer not null,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on plans for each row execute procedure set_update_time();