create table devora_user (
"id" serial primary key,
"user_name" varchar(255) not null unique,
"password" varchar(255) not null
);
create table devora_friend(
  "user_id" integer references devora_user("id"),
  "friend_id" integer references devora_user("id")
);
create table storage (
  "id" serial primary key,
  "name" varchar,
  "security_type" varchar not null
);
create table storage_credential (
  "id" serial primary key,
  "user_id" integer references devora_user("id"),
  "storage_id" integer references storage("id"),
  "user_name" varchar,
  "password" varchar,
  "token" varchar,
   unique(user_id,storage_id)
);
create table uploaded_file (
  "id" serial primary key,
  "file_name" varchar(255) not null,
  "uploader_id" integer references devora_user("id"),
  "storage_id" integer references storage("id"),
  "upload_date" date
);

